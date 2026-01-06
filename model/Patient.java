package model;

public final class Patient {
    private final String id;
    private String name;
    private String dob;
    private String phone;

    public Patient(String id, String name, String dob, String phone) {
        this.id = id; this.name = name; this.dob = dob; this.phone = phone;
    }

    public String id() { return id; }
    public String name() { return name; }
    public String dob() { return dob; }
    public String phone() { return phone; }

    public void setName(String name) { this.name = name; }
    public void setDob(String dob) { this.dob = dob; }
    public void setPhone(String phone) { this.phone = phone; }
}
