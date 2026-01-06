package controller;

import model.Prescription;
import service.PrescriptionService;
import util.Result;

import java.util.List;

public final class PrescriptionController {
    private final PrescriptionService service;
    public PrescriptionController(PrescriptionService service) { this.service = service; }

    public List<Prescription> all() { return service.all(); }
    public Result<Prescription> create(String patientId, String clinicianId, String medication, String dosage, String instructions) { return service.create(patientId, clinicianId, medication, dosage, instructions); }
    public Result<Prescription> update(String id, String patientId, String clinicianId, String medication, String dosage, String instructions) { return service.update(id, patientId, clinicianId, medication, dosage, instructions); }
    public Result<Void> delete(String id) { return service.delete(id); }
}
