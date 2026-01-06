package model;

public final class Prescription {
    private final String id;
    private String patientId;
    private String clinicianId;
    private String medication;
    private String dosage;
    private String instructions;

    public Prescription(String id, String patientId, String clinicianId, String medication, String dosage, String instructions) {
        this.id = id; this.patientId = patientId; this.clinicianId = clinicianId;
        this.medication = medication; this.dosage = dosage; this.instructions = instructions;
    }

    public String id() { return id; }
    public String patientId() { return patientId; }
    public String clinicianId() { return clinicianId; }
    public String medication() { return medication; }
    public String dosage() { return dosage; }
    public String instructions() { return instructions; }

    public void setPatientId(String patientId) { this.patientId = patientId; }
    public void setClinicianId(String clinicianId) { this.clinicianId = clinicianId; }
    public void setMedication(String medication) { this.medication = medication; }
    public void setDosage(String dosage) { this.dosage = dosage; }
    public void setInstructions(String instructions) { this.instructions = instructions; }
}
