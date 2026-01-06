package service;

import model.Clinician;
import repository.ClinicianRepository;
import util.Result;
import util.Validators;

import java.util.List;
import java.util.Optional;

public final class ClinicianService {
    private final ClinicianRepository repo;

    public ClinicianService(ClinicianRepository repo) { this.repo = repo; }

    public List<Clinician> all() { return repo.findAll(); }
    public Optional<Clinician> byId(String id) { return repo.findById(id); }

    public Result<Clinician> create(String name, String specialty, String phone) {
        Result<String> n = Validators.required(name, "Name");
        if (!n.isOk()) return Result.fail(n.error());
        String id = IdGenerator.newId("C");
        Clinician c = new Clinician(id, n.value(), specialty == null ? "" : specialty.trim(), phone == null ? "" : phone.trim());
        repo.add(c);
        return Result.ok(c);
    }

    public Result<Clinician> update(String id, String name, String specialty, String phone) {
        Optional<Clinician> found = repo.findById(id);
        if (!found.isPresent()) return Result.fail("Clinician not found");
        Result<String> n = Validators.required(name, "Name");
        if (!n.isOk()) return Result.fail(n.error());

        Clinician c = found.get();
        c.setName(n.value());
        c.setSpecialty(specialty == null ? "" : specialty.trim());
        c.setPhone(phone == null ? "" : phone.trim());
        repo.update(c);
        return Result.ok(c);
    }

    public Result<Void> delete(String id) {
        repo.deleteById(id);
        return Result.ok(null);
    }
}
