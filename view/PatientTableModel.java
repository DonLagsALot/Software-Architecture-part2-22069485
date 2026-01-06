package view;

import model.Patient;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;
import java.util.List;

public final class PatientTableModel extends AbstractTableModel {
    private final List<Patient> data = new ArrayList<>();
    private final String[] cols = {"ID", "Name", "DOB", "Phone"};

    public void setData(List<Patient> patients) {
        data.clear();
        if (patients != null) data.addAll(patients);
        fireTableDataChanged();
    }

    public Patient getAt(int row) { return data.get(row); }

    @Override public int getRowCount() { return data.size(); }
    @Override public int getColumnCount() { return cols.length; }
    @Override public String getColumnName(int c) { return cols[c]; }

    @Override public Object getValueAt(int rowIndex, int columnIndex) {
        Patient p = data.get(rowIndex);
        switch (columnIndex) {
            case 0: return p.id();
            case 1: return p.name();
            case 2: return p.dob();
            case 3: return p.phone();
            default: return "";
        }
    }
}
