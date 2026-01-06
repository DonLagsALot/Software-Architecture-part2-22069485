package controller;

import model.Patient;
import service.PatientService;
import util.Result;

import java.util.List;

public final class PatientController {
    private final PatientService service;
    public PatientController(PatientService service) { this.service = service; }

    public List<Patient> all() { return service.all(); }
    public Result<Patient> create(String name, String dob, String phone) { return service.create(name, dob, phone); }
    public Result<Patient> update(String id, String name, String dob, String phone) { return service.update(id, name, dob, phone); }
    public Result<Void> delete(String id) { return service.delete(id); }
}
