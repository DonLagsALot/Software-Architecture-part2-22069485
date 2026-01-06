package repository;

import model.Clinician;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public final class CsvClinicianRepository extends AbstractCsvRepository<Clinician> implements ClinicianRepository {
    public CsvClinicianRepository(Path path) { super(path); }

    @Override protected String idOf(Clinician e) { return e.id(); }

    @Override protected Clinician fromRow(Map<String, String> row) {
        String id = pick(row, "clinicianId", "clinician_id", "id");
        if (id.trim().isEmpty()) return null;
        String name = pick(row, "name", "clinicianName");
        String specialty = pick(row, "specialty", "department");
        String phone = pick(row, "phone", "telephone");
        return new Clinician(id, name, specialty, phone);
    }

    @Override protected List<String> headers() {
        List<String> h = new ArrayList<>();
        h.add("clinicianId"); h.add("name"); h.add("specialty"); h.add("phone");
        return h;
    }

    @Override protected List<List<String>> toRows(List<Clinician> items) {
        List<List<String>> rows = new ArrayList<>();
        for (Clinician c : items) {
            List<String> r = new ArrayList<>();
            r.add(c.id()); r.add(c.name()); r.add(c.specialty()); r.add(c.phone());
            rows.add(r);
        }
        return rows;
    }
}
