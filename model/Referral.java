package model;

public final class Referral {
    private final String id;
    private String patientId;
    private String referringClinicianId;
    private String referredTo;
    private String reason;
    private String status;

    public Referral(String id, String patientId, String referringClinicianId, String referredTo, String reason, String status) {
        this.id = id; this.patientId = patientId; this.referringClinicianId = referringClinicianId;
        this.referredTo = referredTo; this.reason = reason; this.status = status;
    }

    public String id() { return id; }
    public String patientId() { return patientId; }
    public String referringClinicianId() { return referringClinicianId; }
    public String referredTo() { return referredTo; }
    public String reason() { return reason; }
    public String status() { return status; }

    public void setPatientId(String patientId) { this.patientId = patientId; }
    public void setReferringClinicianId(String referringClinicianId) { this.referringClinicianId = referringClinicianId; }
    public void setReferredTo(String referredTo) { this.referredTo = referredTo; }
    public void setReason(String reason) { this.reason = reason; }
    public void setStatus(String status) { this.status = status; }
}
