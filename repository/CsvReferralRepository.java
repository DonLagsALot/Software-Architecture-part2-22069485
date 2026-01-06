package repository;

import model.Referral;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public final class CsvReferralRepository extends AbstractCsvRepository<Referral> implements ReferralRepository {
    public CsvReferralRepository(Path path) { super(path); }

    @Override protected String idOf(Referral e) { return e.id(); }

    @Override protected Referral fromRow(Map<String, String> row) {
        String id = pick(row, "referralId", "referral_id", "id");
        if (id.trim().isEmpty()) return null;
        String patientId = pick(row, "patientId", "patient_id");
        String refClinicianId = pick(row, "referringClinicianId", "clinicianId", "clinician_id");
        String referredTo = pick(row, "referredTo", "destination", "clinic");
        String reason = pick(row, "reason");
        String status = pick(row, "status");
        if (status.trim().isEmpty()) status = "Pending";
        return new Referral(id, patientId, refClinicianId, referredTo, reason, status);
    }

    @Override protected List<String> headers() {
        List<String> h = new ArrayList<>();
        h.add("referralId"); h.add("patientId"); h.add("referringClinicianId"); h.add("referredTo"); h.add("reason"); h.add("status");
        return h;
    }

    @Override protected List<List<String>> toRows(List<Referral> items) {
        List<List<String>> rows = new ArrayList<>();
        for (Referral r0 : items) {
            List<String> r = new ArrayList<>();
            r.add(r0.id()); r.add(r0.patientId()); r.add(r0.referringClinicianId());
            r.add(r0.referredTo()); r.add(r0.reason()); r.add(r0.status());
            rows.add(r);
        }
        return rows;
    }
}
