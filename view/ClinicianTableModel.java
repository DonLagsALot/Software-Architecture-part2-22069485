package view;

import model.Clinician;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;
import java.util.List;

public final class ClinicianTableModel extends AbstractTableModel {
    private final List<Clinician> data = new ArrayList<>();
    private final String[] cols = {"ID", "Name", "Specialty", "Phone"};

    public void setData(List<Clinician> clinicians) {
        data.clear();
        if (clinicians != null) data.addAll(clinicians);
        fireTableDataChanged();
    }

    public Clinician getAt(int row) { return data.get(row); }

    @Override public int getRowCount() { return data.size(); }
    @Override public int getColumnCount() { return cols.length; }
    @Override public String getColumnName(int c) { return cols[c]; }

    @Override public Object getValueAt(int rowIndex, int columnIndex) {
        Clinician c = data.get(rowIndex);
        switch (columnIndex) {
            case 0: return c.id();
            case 1: return c.name();
            case 2: return c.specialty();
            case 3: return c.phone();
            default: return "";
        }
    }
}
