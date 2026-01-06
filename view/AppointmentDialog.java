package view;

import javax.swing.*;
import java.awt.*;

public final class AppointmentDialog extends JDialog {
    public final JTextField patientId = new JTextField(22);
    public final JTextField clinicianId = new JTextField(22);
    public final JTextField dateTime = new JTextField(22);
    public final JTextField reason = new JTextField(22);

    private boolean ok = false;

    public AppointmentDialog(Window owner, String title) {
        super(owner, title, ModalityType.APPLICATION_MODAL);
        setLayout(new BorderLayout(10,10));

        JPanel form = new JPanel(new GridLayout(0,2,8,8));
        form.add(new JLabel("Patient ID")); form.add(patientId);
        form.add(new JLabel("Clinician ID")); form.add(clinicianId);
        form.add(new JLabel("Date/Time")); form.add(dateTime);
        form.add(new JLabel("Reason")); form.add(reason);

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
