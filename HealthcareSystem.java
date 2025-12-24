import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;


public class HealthcareSystem {
    private List<Staff> staff = new ArrayList<>();
    private List<Clinicians> clinicians = new ArrayList<>();
    private List<Perscriptions> perscriptions = new ArrayList<>();
    private List<appointments> appointments = new ArrayList<>();
    private List<Referrals> referrals = new ArrayList<>();
    private List<Patients> patients = new ArrayList<>();
    private List<Facilites> facilites = new ArrayList<>();

    static DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd/mm/yyyy");
    static DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("hh:mm");

    public static void main(String[] args) {
        try {

        }
    }
}
