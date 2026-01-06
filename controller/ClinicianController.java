package controller;

import model.Clinician;
import service.ClinicianService;
import util.Result;

import java.util.List;

public final class ClinicianController {
    private final ClinicianService service;
    public ClinicianController(ClinicianService service) { this.service = service; }

    public List<Clinician> all() { return service.all(); }
    public Result<Clinician> create(String name, String specialty, String phone) { return service.create(name, specialty, phone); }
    public Result<Clinician> update(String id, String name, String specialty, String phone) { return service.update(id, name, specialty, phone); }
    public Result<Void> delete(String id) { return service.delete(id); }
}
