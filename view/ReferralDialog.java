package view;

import javax.swing.*;
import java.awt.*;

public final class ReferralDialog extends JDialog {
    public final JTextField patientId = new JTextField(22);
    public final JTextField clinicianId = new JTextField(22);
    public final JTextField referredTo = new JTextField(22);
    public final JTextField reason = new JTextField(22);
    public final JTextField status = new JTextField(22);

    private boolean ok = false;

    public ReferralDialog(Window owner, String title) {
        super(owner, title, ModalityType.APPLICATION_MODAL);
        setLayout(new BorderLayout(10,10));

        JPanel form = new JPanel(new GridLayout(0,2,8,8));
        form.add(new JLabel("Patient ID")); form.add(patientId);
        form.add(new JLabel("Referring Clinician ID")); form.add(clinicianId);
        form.add(new JLabel("Referred To")); form.add(referredTo);
        form.add(new JLabel("Reason")); form.add(reason);
        form.add(new JLabel("Status")); form.add(status);

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
