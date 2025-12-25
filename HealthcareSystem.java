import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class HealthcareSystem {

    static List<Patient> patients = new Arraylist<>();
    static List<Clinician> clinicians = new Arraylist<>();
    static List<Facilities> facilities = new ArrayList<>();
    static List<Appointment> appointments = new ArrayList<>();
    static List<Percsription> perscriptions = new ArrayList<>();
    static List<Referrals> referrals = new ArrayList<>();
    static List<Staff> staff = new ArrayList<>();
    
    public static void main (String[] args) {
        loadAllData();
    }
}
