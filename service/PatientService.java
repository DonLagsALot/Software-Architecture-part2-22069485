package service;

import model.Patient;
import repository.PatientRepository;
import util.Result;
import util.Validators;

import java.util.List;
import java.util.Optional;

public final class PatientService {
    private final PatientRepository repo;

    public PatientService(PatientRepository repo) { this.repo = repo; }

    public List<Patient> all() { return repo.findAll(); }
    public Optional<Patient> byId(String id) { return repo.findById(id); }

    public Result<Patient> create(String name, String dob, String phone) {
        Result<String> n = Validators.required(name, "Name");
        if (!n.isOk()) return Result.fail(n.error());
        String id = IdGenerator.newId("P");
        Patient p = new Patient(id, n.value(), dob == null ? "" : dob.trim(), phone == null ? "" : phone.trim());
        repo.add(p);
        return Result.ok(p);
    }

    public Result<Patient> update(String id, String name, String dob, String phone) {
        Optional<Patient> found = repo.findById(id);
        if (!found.isPresent()) return Result.fail("Patient not found");
        Result<String> n = Validators.required(name, "Name");
        if (!n.isOk()) return Result.fail(n.error());

        Patient p = found.get();
        p.setName(n.value());
        p.setDob(dob == null ? "" : dob.trim());
        p.setPhone(phone == null ? "" : phone.trim());
        repo.update(p);
        return Result.ok(p);
    }

    public Result<Void> delete(String id) {
        repo.deleteById(id);
        return Result.ok(null);
    }
}
