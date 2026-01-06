package model;

public final class Appointment {
    private final String id;
    private String patientId;
    private String clinicianId;
    private String dateTime;
    private String reason;

    public Appointment(String id, String patientId, String clinicianId, String dateTime, String reason) {
        this.id = id; this.patientId = patientId; this.clinicianId = clinicianId;
        this.dateTime = dateTime; this.reason = reason;
    }

    public String id() { return id; }
    public String patientId() { return patientId; }
    public String clinicianId() { return clinicianId; }
    public String dateTime() { return dateTime; }
    public String reason() { return reason; }

    public void setPatientId(String patientId) { this.patientId = patientId; }
    public void setClinicianId(String clinicianId) { this.clinicianId = clinicianId; }
    public void setDateTime(String dateTime) { this.dateTime = dateTime; }
    public void setReason(String reason) { this.reason = reason; }
}
