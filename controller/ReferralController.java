package controller;

import model.Referral;
import service.ReferralService;
import util.Result;

import java.util.List;

public final class ReferralController {
    private final ReferralService service;
    public ReferralController(ReferralService service) { this.service = service; }

    public List<Referral> all() { return service.all(); }
    public Result<Referral> create(String patientId, String clinicianId, String referredTo, String reason, String status) { return service.create(patientId, clinicianId, referredTo, reason, status); }
    public Result<Referral> update(String id, String patientId, String clinicianId, String referredTo, String reason, String status) { return service.update(id, patientId, clinicianId, referredTo, reason, status); }
    public Result<Void> delete(String id) { return service.delete(id); }
}
