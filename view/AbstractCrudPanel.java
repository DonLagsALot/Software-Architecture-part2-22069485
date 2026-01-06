package view;

import javax.swing.*;
import java.awt.*;

public abstract class AbstractCrudPanel extends JPanel {

    protected final JTable table = new JTable();

    protected AbstractCrudPanel() {
        setLayout(new BorderLayout(8, 8));
        add(new JScrollPane(table), BorderLayout.CENTER);
        add(buildButtons(), BorderLayout.SOUTH);
    }

    private JComponent buildButtons() {
        JPanel p = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JButton add = new JButton("Add");
        JButton edit = new JButton("Edit");
        JButton del = new JButton("Delete");
        JButton refresh = new JButton("Refresh");

        add.addActionListener(e -> onAdd());
        edit.addActionListener(e -> onEdit());
        del.addActionListener(e -> onDelete());
        refresh.addActionListener(e -> reload());

        p.add(refresh);
        p.add(add);
        p.add(edit);
        p.add(del);
        return p;
    }

    protected String selectedId(int idColIndex) {
        int row = table.getSelectedRow();
        if (row < 0) return null;
        Object v = table.getValueAt(row, idColIndex);
        return v == null ? null : v.toString();
    }

    protected abstract void reload();
    protected abstract void onAdd();
    protected abstract void onEdit();
    protected abstract void onDelete();
}
