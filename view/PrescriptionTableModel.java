package view;

import model.Prescription;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;
import java.util.List;

public final class PrescriptionTableModel extends AbstractTableModel {
    private final List<Prescription> data = new ArrayList<>();
    private final String[] cols = {"ID", "Patient ID", "Clinician ID", "Medication", "Dosage", "Instructions"};

    public void setData(List<Prescription> items) {
        data.clear();
        if (items != null) data.addAll(items);
        fireTableDataChanged();
    }

    public Prescription getAt(int row) { return data.get(row); }

    @Override public int getRowCount() { return data.size(); }
    @Override public int getColumnCount() { return cols.length; }
    @Override public String getColumnName(int c) { return cols[c]; }

    @Override public Object getValueAt(int rowIndex, int columnIndex) {
        Prescription p = data.get(rowIndex);
        switch (columnIndex) {
            case 0: return p.id();
            case 1: return p.patientId();
            case 2: return p.clinicianId();
            case 3: return p.medication();
            case 4: return p.dosage();
            case 5: return p.instructions();
            default: return "";
        }
    }
}
