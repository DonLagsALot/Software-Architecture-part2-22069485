package view;

import controller.PrescriptionController;
import model.Prescription;
import util.Result;

public final class PrescriptionPanel extends AbstractCrudPanel {
    private final PrescriptionController controller;
    private final PrescriptionTableModel model = new PrescriptionTableModel();

    public PrescriptionPanel(PrescriptionController controller) {
        this.controller = controller;
        table.setModel(model);
        reload();
    }

    @Override protected void reload() { model.setData(controller.all()); }

    @Override protected void onAdd() {
        PrescriptionDialog d = new PrescriptionDialog(UiWindow.windowOf(this), "Add Prescription");
        d.setVisible(true);
        if (!d.isOk()) return;

        Result<Prescription> r = controller.create(
                d.patientId.getText(), d.clinicianId.getText(), d.medication.getText(), d.dosage.getText(), d.instructions.getText()
        );
        if (!r.isOk()) Ui.error(this, r.error());
        reload();
    }

    @Override protected void onEdit() {
        String id = selectedId(0);
        if (id == null) { Ui.error(this, "Select a prescription first"); return; }
        Prescription p = model.getAt(table.getSelectedRow());

        PrescriptionDialog d = new PrescriptionDialog(UiWindow.windowOf(this), "Edit Prescription");
        d.patientId.setText(p.patientId());
        d.clinicianId.setText(p.clinicianId());
        d.medication.setText(p.medication());
        d.dosage.setText(p.dosage());
        d.instructions.setText(p.instructions());
        d.setVisible(true);
        if (!d.isOk()) return;

        Result<Prescription> r = controller.update(
                id, d.patientId.getText(), d.clinicianId.getText(), d.medication.getText(), d.dosage.getText(), d.instructions.getText()
        );
        if (!r.isOk()) Ui.error(this, r.error());
        reload();
    }

    @Override protected void onDelete() {
        String id = selectedId(0);
        if (id == null) { Ui.error(this, "Select a prescription first"); return; }
        if (!Ui.confirm(this, "Delete prescription " + id + "?")) return;
        controller.delete(id);
        reload();
    }
}
