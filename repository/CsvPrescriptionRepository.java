package repository;

import model.Prescription;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public final class CsvPrescriptionRepository extends AbstractCsvRepository<Prescription> implements PrescriptionRepository {
    public CsvPrescriptionRepository(Path path) { super(path); }

    @Override protected String idOf(Prescription e) { return e.id(); }

    @Override protected Prescription fromRow(Map<String, String> row) {
        String id = pick(row, "prescriptionId", "prescription_id", "id");
        if (id.trim().isEmpty()) return null;
        String patientId = pick(row, "patientId", "patient_id");
        String clinicianId = pick(row, "clinicianId", "clinician_id");
        String medication = pick(row, "medication", "drug");
        String dosage = pick(row, "dosage");
        String instructions = pick(row, "instructions", "notes");
        return new Prescription(id, patientId, clinicianId, medication, dosage, instructions);
    }

    @Override protected List<String> headers() {
        List<String> h = new ArrayList<>();
        h.add("prescriptionId"); h.add("patientId"); h.add("clinicianId"); h.add("medication"); h.add("dosage"); h.add("instructions");
        return h;
    }

    @Override protected List<List<String>> toRows(List<Prescription> items) {
        List<List<String>> rows = new ArrayList<>();
        for (Prescription p : items) {
            List<String> r = new ArrayList<>();
            r.add(p.id()); r.add(p.patientId()); r.add(p.clinicianId()); r.add(p.medication()); r.add(p.dosage()); r.add(p.instructions());
            rows.add(r);
        }
        return rows;
    }
}
