package repository;

import java.util.List;
import java.util.Optional;

public interface Repository<T> {
    void load();
    void save();

    List<T> findAll();
    Optional<T> findById(String id);

    void add(T entity);
    void update(T entity);
    void deleteById(String id);
}
