package repository;

import model.Appointment;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public final class CsvAppointmentRepository extends AbstractCsvRepository<Appointment> implements AppointmentRepository {
    public CsvAppointmentRepository(Path path) { super(path); }

    @Override protected String idOf(Appointment e) { return e.id(); }

    @Override protected Appointment fromRow(Map<String, String> row) {
        String id = pick(row, "appointmentId", "appointment_id", "id");
        if (id.trim().isEmpty()) return null;
        String patientId = pick(row, "patientId", "patient_id");
        String clinicianId = pick(row, "clinicianId", "clinician_id");
        String dt = pick(row, "dateTime", "datetime", "date", "time");
        String reason = pick(row, "reason", "notes");
        return new Appointment(id, patientId, clinicianId, dt, reason);
    }

    @Override protected List<String> headers() {
        List<String> h = new ArrayList<>();
        h.add("appointmentId"); h.add("patientId"); h.add("clinicianId"); h.add("dateTime"); h.add("reason");
        return h;
    }

    @Override protected List<List<String>> toRows(List<Appointment> items) {
        List<List<String>> rows = new ArrayList<>();
        for (Appointment a : items) {
            List<String> r = new ArrayList<>();
            r.add(a.id()); r.add(a.patientId()); r.add(a.clinicianId()); r.add(a.dateTime()); r.add(a.reason());
            rows.add(r);
        }
        return rows;
    }
}
