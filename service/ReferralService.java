package service;

import model.Referral;
import repository.AppointmentRepository;
import repository.ClinicianRepository;
import repository.PatientRepository;
import util.Result;
import util.Validators;

import java.util.List;
import java.util.Optional;

public final class ReferralService {
    private final ReferralManager manager;
    private final PatientRepository patients;
    private final ClinicianRepository clinicians;
    private final AppointmentRepository appointments;

    public ReferralService(ReferralManager manager, PatientRepository patients, ClinicianRepository clinicians, AppointmentRepository appointments) {
        this.manager = manager; this.patients = patients; this.clinicians = clinicians; this.appointments = appointments;
    }

    public List<Referral> all() { return manager.all(); }
    public Optional<Referral> byId(String id) { return manager.byId(id); }

    public Result<Referral> create(String patientId, String clinicianId, String referredTo, String reason, String status) {
        Result<String> p = Validators.required(patientId, "Patient ID");
        if (!p.isOk()) return Result.fail(p.error());
        Result<String> c = Validators.required(clinicianId, "Clinician ID");
        if (!c.isOk()) return Result.fail(c.error());
        Result<String> rt = Validators.required(referredTo, "Referred To");
        if (!rt.isOk()) return Result.fail(rt.error());

        if (!patients.findById(p.value()).isPresent()) return Result.fail("Patient ID does not exist");
        if (!clinicians.findById(c.value()).isPresent()) return Result.fail("Clinician ID does not exist");

        String id = IdGenerator.newId("R");
        Referral r = new Referral(id, p.value(), c.value(), rt.value(),
                reason == null ? "" : reason.trim(),
                (status == null || status.trim().isEmpty()) ? "Pending" : status.trim());
        manager.add(r);
        return Result.ok(r);
    }

    public Result<Referral> update(String id, String patientId, String clinicianId, String referredTo, String reason, String status) {
        Optional<Referral> found = manager.byId(id);
        if (!found.isPresent()) return Result.fail("Referral not found");

        Result<String> p = Validators.required(patientId, "Patient ID");
        if (!p.isOk()) return Result.fail(p.error());
        Result<String> c = Validators.required(clinicianId, "Clinician ID");
        if (!c.isOk()) return Result.fail(c.error());
        Result<String> rt = Validators.required(referredTo, "Referred To");
        if (!rt.isOk()) return Result.fail(rt.error());

        if (!patients.findById(p.value()).isPresent()) return Result.fail("Patient ID does not exist");
        if (!clinicians.findById(c.value()).isPresent()) return Result.fail("Clinician ID does not exist");

        Referral r = found.get();
        r.setPatientId(p.value());
        r.setReferringClinicianId(c.value());
        r.setReferredTo(rt.value());
        r.setReason(reason == null ? "" : reason.trim());
        r.setStatus((status == null || status.trim().isEmpty()) ? "Pending" : status.trim());
        manager.update(r);
        return Result.ok(r);
    }

    public Result<Void> delete(String id) { manager.delete(id); return Result.ok(null); }
}
