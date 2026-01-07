package app;

import config.DataConfig;
import controller.*;
import repository.*;
import service.*;
import view.MainFrame;

import javax.swing.*;
import java.nio.file.Path;

public final class ReferralSystemApplication {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            try { start(); }
            catch (Exception e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(null, "Startup failed:\n" + e.getMessage(),
                        "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
}

    private static void start() {
        Path dataDir = DataConfig.resolveDataDir();

        PatientRepository patientRepo = new CsvPatientRepository(dataDir.resolve(DataConfig.PATIENTS_CSV));
        ClinicianRepository clinicianRepo = new CsvClinicianRepository(dataDir.resolve(DataConfig.CLINICIANS_CSV));
        AppointmentRepository appointmentRepo = new CsvAppointmentRepository(dataDir.resolve(DataConfig.APPOINTMENTS_CSV));
        PrescriptionRepository prescriptionRepo = new CsvPrescriptionRepository(dataDir.resolve(DataConfig.PRESCRIPTIONS_CSV));
        ReferralRepository referralRepo = new CsvReferralRepository(dataDir.resolve(DataConfig.REFERRALS_CSV));

        patientRepo.load();
        clinicianRepo.load();
        appointmentRepo.load();
        prescriptionRepo.load();
        referralRepo.load();

        PatientService patientService = new PatientService(patientRepo);
        ClinicianService clinicianService = new ClinicianService(clinicianRepo);
        AppointmentService appointmentService = new AppointmentService(appointmentRepo, patientRepo, clinicianRepo);
        PrescriptionService prescriptionService = new PrescriptionService(prescriptionRepo, patientRepo, clinicianRepo);

        ReferralManager referralManager = ReferralManager.getInstance(referralRepo);

        ReferralService referralService = new ReferralService(referralManager, patientRepo, clinicianRepo, appointmentRepo);

        MainFrame frame = new MainFrame(
                new PatientController(patientService),
                new ClinicianController(clinicianService),
                new AppointmentController(appointmentService),
                new PrescriptionController(prescriptionService),
                new ReferralController(referralService)
        );
        frame.setVisible(true);
    }
}
