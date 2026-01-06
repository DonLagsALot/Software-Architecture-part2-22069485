package view;

import model.Appointment;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;
import java.util.List;

public final class AppointmentTableModel extends AbstractTableModel {
    private final List<Appointment> data = new ArrayList<>();
    private final String[] cols = {"ID", "Patient ID", "Clinician ID", "Date/Time", "Reason"};

    public void setData(List<Appointment> items) {
        data.clear();
        if (items != null) data.addAll(items);
        fireTableDataChanged();
    }

    public Appointment getAt(int row) { return data.get(row); }

    @Override public int getRowCount() { return data.size(); }
    @Override public int getColumnCount() { return cols.length; }
    @Override public String getColumnName(int c) { return cols[c]; }

    @Override public Object getValueAt(int rowIndex, int columnIndex) {
        Appointment a = data.get(rowIndex);
        switch (columnIndex) {
            case 0: return a.id();
            case 1: return a.patientId();
            case 2: return a.clinicianId();
            case 3: return a.dateTime();
            case 4: return a.reason();
            default: return "";
        }
    }
}
