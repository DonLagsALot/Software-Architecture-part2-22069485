package view;

import javax.swing.*;
import java.awt.*;

public final class PatientDialog extends JDialog {
    public final JTextField name = new JTextField(22);
    public final JTextField dob = new JTextField(22);
    public final JTextField phone = new JTextField(22);

    private boolean ok = false;

    public PatientDialog(Window owner, String title) {
        super(owner, title, ModalityType.APPLICATION_MODAL);
        setLayout(new BorderLayout(10,10));

        JPanel form = new JPanel(new GridLayout(0,2,8,8));
        form.add(new JLabel("Name")); form.add(name);
        form.add(new JLabel("DOB")); form.add(dob);
        form.add(new JLabel("Phone")); form.add(phone);

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
