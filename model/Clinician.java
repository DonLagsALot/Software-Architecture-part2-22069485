package model;

public final class Clinician {
    private final String id;
    private String name;
    private String specialty;
    private String phone;

    public Clinician(String id, String name, String specialty, String phone) {
        this.id = id; this.name = name; this.specialty = specialty; this.phone = phone;
    }

    public String id() { return id; }
    public String name() { return name; }
    public String specialty() { return specialty; }
    public String phone() { return phone; }

    public void setName(String name) { this.name = name; }
    public void setSpecialty(String specialty) { this.specialty = specialty; }
    public void setPhone(String phone) { this.phone = phone; }
}
