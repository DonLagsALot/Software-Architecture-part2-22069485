package view;

import model.Referral;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;
import java.util.List;

public final class ReferralTableModel extends AbstractTableModel {
    private final List<Referral> data = new ArrayList<>();
    private final String[] cols = {"ID", "Patient ID", "Clinician ID", "Referred To", "Reason", "Status"};

    public void setData(List<Referral> items) {
        data.clear();
        if (items != null) data.addAll(items);
        fireTableDataChanged();
    }

    public Referral getAt(int row) { return data.get(row); }

    @Override public int getRowCount() { return data.size(); }
    @Override public int getColumnCount() { return cols.length; }
    @Override public String getColumnName(int c) { return cols[c]; }

    @Override public Object getValueAt(int rowIndex, int columnIndex) {
        Referral r = data.get(rowIndex);
        switch (columnIndex) {
            case 0: return r.id();
            case 1: return r.patientId();
            case 2: return r.referringClinicianId();
            case 3: return r.referredTo();
            case 4: return r.reason();
            case 5: return r.status();
            default: return "";
        }
    }
}
