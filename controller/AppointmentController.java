package controller;

import model.Appointment;
import service.AppointmentService;
import util.Result;

import java.util.List;

public final class AppointmentController {
    private final AppointmentService service;
    public AppointmentController(AppointmentService service) { this.service = service; }

    public List<Appointment> all() { return service.all(); }
    public Result<Appointment> create(String patientId, String clinicianId, String dateTime, String reason) { return service.create(patientId, clinicianId, dateTime, reason); }
    public Result<Appointment> update(String id, String patientId, String clinicianId, String dateTime, String reason) { return service.update(id, patientId, clinicianId, dateTime, reason); }
    public Result<Void> delete(String id) { return service.delete(id); }
}
