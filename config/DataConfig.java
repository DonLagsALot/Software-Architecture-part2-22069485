package config;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public final class DataConfig {
    private DataConfig() {}

    public static final String PATIENTS_CSV = "patients.csv";
    public static final String CLINICIANS_CSV = "clinicians.csv";
    public static final String APPOINTMENTS_CSV = "appointments.csv";
    public static final String PRESCRIPTIONS_CSV = "prescriptions.csv";
    public static final String REFERRALS_CSV = "referrals.csv";

    public static Path resolveDataDir() {
        String override = System.getProperty("data.dir");
        Path dir = (override == null || override.trim().isEmpty())
                ? Paths.get("data")
                : Paths.get(override.trim());
        try { Files.createDirectories(dir); } catch (Exception ignored) {}
        return dir.toAbsolutePath().normalize();
    }
}
