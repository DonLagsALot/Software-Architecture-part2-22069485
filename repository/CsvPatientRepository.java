package repository;

import model.Patient;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public final class CsvPatientRepository extends AbstractCsvRepository<Patient> implements PatientRepository {
    public CsvPatientRepository(Path path) { super(path); }

    @Override protected String idOf(Patient e) { return e.id(); }

    @Override protected Patient fromRow(Map<String, String> row) {
        String id = pick(row, "patientId", "patient_id", "id");
        if (id.trim().isEmpty()) return null;
        String name = pick(row, "name", "patientName");
        String dob = pick(row, "dob", "dateOfBirth");
        String phone = pick(row, "phone", "telephone");
        return new Patient(id, name, dob, phone);
    }

    @Override protected List<String> headers() {
        List<String> h = new ArrayList<>();
        h.add("patientId"); h.add("name"); h.add("dob"); h.add("phone");
        return h;
    }

    @Override protected List<List<String>> toRows(List<Patient> items) {
        List<List<String>> rows = new ArrayList<>();
        for (Patient p : items) {
            List<String> r = new ArrayList<>();
            r.add(p.id()); r.add(p.name()); r.add(p.dob()); r.add(p.phone());
            rows.add(r);
        }
        return rows;
    }
}
