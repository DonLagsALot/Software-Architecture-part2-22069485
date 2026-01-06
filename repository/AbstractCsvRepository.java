package repository;

import util.Csv;

import java.nio.file.Path;
import java.util.*;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public abstract class AbstractCsvRepository<T> implements Repository<T> {
    protected final Path path;
    protected final List<T> items = new ArrayList<>();
    protected final ReentrantReadWriteLock lock = new ReentrantReadWriteLock();

    protected AbstractCsvRepository(Path path) { this.path = path; }

    @Override public void load() {
        lock.writeLock().lock();
        try {
            items.clear();
            for (Map<String,String> row : Csv.read(path)) {
                T t = fromRow(row);
                if (t != null) items.add(t);
            }
        } catch (Exception ignored) {
        } finally { lock.writeLock().unlock(); }
    }

    @Override public void save() {
        lock.readLock().lock();
        try { Csv.write(path, headers(), toRows(items)); }
        catch (Exception ignored) {
        } finally { lock.readLock().unlock(); }
    }

    @Override public List<T> findAll() {
        lock.readLock().lock();
        try { return new ArrayList<>(items); }
        finally { lock.readLock().unlock(); }
    }

    @Override public Optional<T> findById(String id) {
        lock.readLock().lock();
        try {
            for (T t : items) if (idOf(t).equals(id)) return Optional.of(t);
            return Optional.empty();
        } finally { lock.readLock().unlock(); }
    }

    @Override public void add(T entity) {
        lock.writeLock().lock();
        try { items.add(entity); }
        finally { lock.writeLock().unlock(); }
        save();
    }

    @Override public void update(T entity) {
        lock.writeLock().lock();
        try {
            for (int i=0;i<items.size();i++) {
                if (idOf(items.get(i)).equals(idOf(entity))) { items.set(i, entity); break; }
            }
        } finally { lock.writeLock().unlock(); }
        save();
    }

    @Override public void deleteById(String id) {
        lock.writeLock().lock();
        try { items.removeIf(t -> idOf(t).equals(id)); }
        finally { lock.writeLock().unlock(); }
        save();
    }

    protected static String pick(Map<String,String> row, String... keys) {
        for (String k : keys) {
            for (String real : row.keySet()) {
                if (real.equalsIgnoreCase(k)) return row.get(real);
            }
        }
        return "";
    }

    protected abstract String idOf(T entity);
    protected abstract T fromRow(Map<String,String> row);
    protected abstract List<String> headers();
    protected abstract List<List<String>> toRows(List<T> items);
}
