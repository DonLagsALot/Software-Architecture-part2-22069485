package service;

import model.Prescription;
import repository.ClinicianRepository;
import repository.PatientRepository;
import repository.PrescriptionRepository;
import util.Result;
import util.Validators;

import java.util.List;
import java.util.Optional;

public final class PrescriptionService {
    private final PrescriptionRepository repo;
    private final PatientRepository patients;
    private final ClinicianRepository clinicians;

    public PrescriptionService(PrescriptionRepository repo, PatientRepository patients, ClinicianRepository clinicians) {
        this.repo = repo; this.patients = patients; this.clinicians = clinicians;
    }

    public List<Prescription> all() { return repo.findAll(); }
    public Optional<Prescription> byId(String id) { return repo.findById(id); }

    public Result<Prescription> create(String patientId, String clinicianId, String medication, String dosage, String instructions) {
        Result<String> p = Validators.required(patientId, "Patient ID");
        if (!p.isOk()) return Result.fail(p.error());
        Result<String> c = Validators.required(clinicianId, "Clinician ID");
        if (!c.isOk()) return Result.fail(c.error());
        Result<String> m = Validators.required(medication, "Medication");
        if (!m.isOk()) return Result.fail(m.error());

        if (!patients.findById(p.value()).isPresent()) return Result.fail("Patient ID does not exist");
        if (!clinicians.findById(c.value()).isPresent()) return Result.fail("Clinician ID does not exist");

        String id = IdGenerator.newId("RX");
        Prescription pr = new Prescription(id, p.value(), c.value(), m.value(),
                dosage == null ? "" : dosage.trim(),
                instructions == null ? "" : instructions.trim());
        repo.add(pr);
        return Result.ok(pr);
    }

    public Result<Prescription> update(String id, String patientId, String clinicianId, String medication, String dosage, String instructions) {
        Optional<Prescription> found = repo.findById(id);
        if (!found.isPresent()) return Result.fail("Prescription not found");

        Result<String> p = Validators.required(patientId, "Patient ID");
        if (!p.isOk()) return Result.fail(p.error());
        Result<String> c = Validators.required(clinicianId, "Clinician ID");
        if (!c.isOk()) return Result.fail(c.error());
        Result<String> m = Validators.required(medication, "Medication");
        if (!m.isOk()) return Result.fail(m.error());

        if (!patients.findById(p.value()).isPresent()) return Result.fail("Patient ID does not exist");
        if (!clinicians.findById(c.value()).isPresent()) return Result.fail("Clinician ID does not exist");

        Prescription pr = found.get();
        pr.setPatientId(p.value());
        pr.setClinicianId(c.value());
        pr.setMedication(m.value());
        pr.setDosage(dosage == null ? "" : dosage.trim());
        pr.setInstructions(instructions == null ? "" : instructions.trim());
        repo.update(pr);
        return Result.ok(pr);
    }

    public Result<Void> delete(String id) { repo.deleteById(id); return Result.ok(null); }
}
