package view;

import controller.*;

import javax.swing.*;
import java.awt.*;

public final class MainFrame extends JFrame {

    public MainFrame(
            PatientController patients,
            ClinicianController clinicians,
            AppointmentController appointments,
            PrescriptionController prescriptions,
            ReferralController referrals
    ) {
        super("Medical Referral System (MVC)");

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setSize(1050, 650);
        setLocationRelativeTo(null);

        JTabbedPane tabs = new JTabbedPane();

        tabs.addTab("Patients", new PatientPanel(patients));
        tabs.addTab("Clinicians", new ClinicianPanel(clinicians));
        tabs.addTab("Appointments", new AppointmentPanel(appointments));
        tabs.addTab("Prescriptions", new PrescriptionPanel(prescriptions));
        tabs.addTab("Referrals", new ReferralPanel(referrals));

        setLayout(new BorderLayout());
        add(tabs, BorderLayout.CENTER);
    }
}
