package service;

import model.Appointment;
import repository.AppointmentRepository;
import repository.ClinicianRepository;
import repository.PatientRepository;
import util.Result;
import util.Validators;

import java.util.List;
import java.util.Optional;

public final class AppointmentService {
    private final AppointmentRepository repo;
    private final PatientRepository patients;
    private final ClinicianRepository clinicians;

    public AppointmentService(AppointmentRepository repo, PatientRepository patients, ClinicianRepository clinicians) {
        this.repo = repo; this.patients = patients; this.clinicians = clinicians;
    }

    public List<Appointment> all() { return repo.findAll(); }
    public Optional<Appointment> byId(String id) { return repo.findById(id); }

    public Result<Appointment> create(String patientId, String clinicianId, String dateTime, String reason) {
        Result<String> p = Validators.required(patientId, "Patient ID");
        if (!p.isOk()) return Result.fail(p.error());
        Result<String> c = Validators.required(clinicianId, "Clinician ID");
        if (!c.isOk()) return Result.fail(c.error());

        if (!patients.findById(p.value()).isPresent()) return Result.fail("Patient ID does not exist");
        if (!clinicians.findById(c.value()).isPresent()) return Result.fail("Clinician ID does not exist");

        String id = IdGenerator.newId("A");
        Appointment a = new Appointment(id, p.value(), c.value(), dateTime == null ? "" : dateTime.trim(), reason == null ? "" : reason.trim());
        repo.add(a);
        return Result.ok(a);
    }

    public Result<Appointment> update(String id, String patientId, String clinicianId, String dateTime, String reason) {
        Optional<Appointment> found = repo.findById(id);
        if (!found.isPresent()) return Result.fail("Appointment not found");

        Result<String> p = Validators.required(patientId, "Patient ID");
        if (!p.isOk()) return Result.fail(p.error());
        Result<String> c = Validators.required(clinicianId, "Clinician ID");
        if (!c.isOk()) return Result.fail(c.error());

        if (!patients.findById(p.value()).isPresent()) return Result.fail("Patient ID does not exist");
        if (!clinicians.findById(c.value()).isPresent()) return Result.fail("Clinician ID does not exist");

        Appointment a = found.get();
        a.setPatientId(p.value());
        a.setClinicianId(c.value());
        a.setDateTime(dateTime == null ? "" : dateTime.trim());
        a.setReason(reason == null ? "" : reason.trim());
        repo.update(a);
        return Result.ok(a);
    }

    public Result<Void> delete(String id) { repo.deleteById(id); return Result.ok(null); }
}
