import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class HealthcareSystem {

    static List<Patients> patients = new Arraylist<>();
    static List<Clinicians> clinicians = new Arraylist<>();
    static List<Facilities> facilities = new ArrayList<>();
    static List<Appointments> appointments = new ArrayList<>();
    static List<Prescriptions> prescriptions = new ArrayList<>();
    static List<Referrals> referrals = new ArrayList<>();
    static List<Staff> staff = new ArrayList<>();
    
    public static void main (String[] args) {
        loadAllData();

        Scanner scan = new Scanner(System.in);
        boolean running = true;

        while (running) {
            System.out.println("Healthcare Menu");
            System.out.println("1. View Patients");
            System.out.println("2. View Clinicians");
            System.out.println("3. View Facilities");
            System.out.println("4. View Staff");
            System.out.println("5. View Appointments");
            System.out.println("6. View Percsriptions");
            System.out.println("7. View Refferals");
            System.out.println("8. Search by Patient ID");
            System.out.println("0. Exit");
            System.out.print("Select an option: ");

            String choice = scan.nextLine();

            if (choice.equals("1")) printPatients();
            else if (choice.equals("2")) printClinicians();
            else if (choice.equals("3")) printFacilities();
            else if (choice.equals("4")) printStaff();
            else if (choice.equals("5")) printAppointments();
            else if (choice.equals("6")) printPrescriptions();
            else if (choice.equals("7")) printReferrals();
            else if (choice.equals("8")) {
                System.out.print("Enter Patient ID: ");
                findPatient(scan.nextLine());
            }
            else if (choice.equals("0")) {
                running = false;
                System.out.println("Exiting.");
            }
            else {
                System.out.println("Invalid selection.");
            }
        }
    }

    static void printPatients() {
        System.out.println("PATIENTS");
        for (Patients p : patients) {
            System.out.printf("%s %s %s (DOB: %s)%n", p.id, p.firstName, p.lastName, p.dob);
        }
    }
    
    static void printClinicians() {
        System.out.println(CLINICIANS);
        for (Clinicians c : clinicians) {
            System.out.printf("%s %s %s (%s)%n", c.id, c.firstName, c.lastName, c.specialty);
        }
    }
    
    static void printFacilities() {
        System.out.println("FACILITIES");
        for (Facilities f : facilities) {
            System.out.printf("%s %s %s (%s)%n", f.id, f.Name, f.type);
        }
    }
    
    static void printStaff() {
        System.out.println("STAFF");
        for (Staff s : staff) {
            System.out.printf("%s %s %s ()%n", s.id, s.firstName, s.lastName, s.role);
        }
    }
    
    static void printAppointments() {
        System.out.println("APPOINTMENTS");
        for (Appointments a : appointments) {
            System.out.printf("Date: %s, Time: %s, Patient: %s, Reason: %s%n", a.date, a.time, a.patientId, a.reason);
        }
    }
    
    static void printPrescriptions() {
        System.out.println("PRESCRIPTIONS");
        for (Prescriptions p : prescriptions) {
            System.out.printf("Rx ID: %s, Medication: %s, Dosage: %s, Patient: %s%n", p.id, p.medication, p.dosage, p.patientId);
        }
    }
    
    static void printReferra() {
        System.out.println("REFERRAL");
        for (Referral r : referral) {
            System.out.printf("Ref Id: %s, Priority: %s, To Facility: %s, Reason: %s%n", r.id, r.urgency, r.facilitiesId, r.reason);
        }
    }
    static void findPatient(String id) {
        for (Patient p : patients) {
            if (p.id.equalsIgnoreCase(id)) {
                System.out.println("\n--- PATIENT FOUND ---");
                System.out.println("ID: " + p.id);
                System.out.println("Name: " + p.firstName + " " + p.lastName);
                System.out.println("Address: " + p.address);
                System.out.println("NHS Number: " + p.nhsNumber);
                return;
            }
        }
        System.out.println("Patient not found");
}

static void loadAllData(){
    try {
        for (String[] row : readCsv("patients.csv")) {
            if (row.length >= 14) patients.add(new Patient(row[0], row[1], row[2], row[3], row[4], row[8]));
        }
    }
}


