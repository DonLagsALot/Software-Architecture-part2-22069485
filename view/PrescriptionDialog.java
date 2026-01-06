package view;

import javax.swing.*;
import java.awt.*;

public final class PrescriptionDialog extends JDialog {
    public final JTextField patientId = new JTextField(22);
    public final JTextField clinicianId = new JTextField(22);
    public final JTextField medication = new JTextField(22);
    public final JTextField dosage = new JTextField(22);
    public final JTextField instructions = new JTextField(22);

    private boolean ok = false;

    public PrescriptionDialog(Window owner, String title) {
        super(owner, title, ModalityType.APPLICATION_MODAL);
        setLayout(new BorderLayout(10,10));

        JPanel form = new JPanel(new GridLayout(0,2,8,8));
        form.add(new JLabel("Patient ID")); form.add(patientId);
        form.add(new JLabel("Clinician ID")); form.add(clinicianId);
        form.add(new JLabel("Medication")); form.add(medication);
        form.add(new JLabel("Dosage")); form.add(dosage);
        form.add(new JLabel("Instructions")); form.add(instructions);

        JPanel buttons = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JButton cancel = new JButton("Cancel");
        JButton save = new JButton("Save");
        cancel.addActionListener(e -> dispose());
        save.addActionListener(e -> { ok = true; dispose(); });

        buttons.add(cancel); buttons.add(save);

        add(form, BorderLayout.CENTER);
        add(buttons, BorderLayout.SOUTH);
        pack();
        setLocationRelativeTo(owner);
    }

    public boolean isOk() { return ok; }
}
