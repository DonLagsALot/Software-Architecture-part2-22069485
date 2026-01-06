package view;

import controller.PatientController;
import model.Patient;
import util.Result;

public final class PatientPanel extends AbstractCrudPanel {
    private final PatientController controller;
    private final PatientTableModel model = new PatientTableModel();

    public PatientPanel(PatientController controller) {
        this.controller = controller;
        table.setModel(model);
        reload();
    }

    @Override protected void reload() {
        model.setData(controller.all());
    }

    @Override protected void onAdd() {
        PatientDialog d = new PatientDialog(UiWindow.windowOf(this), "Add Patient");
        d.setVisible(true);
        if (!d.isOk()) return;

        Result<Patient> r = controller.create(d.name.getText(), d.dob.getText(), d.phone.getText());
        if (!r.isOk()) Ui.error(this, r.error());
        reload();
    }

    @Override protected void onEdit() {
        String id = selectedId(0);
        if (id == null) { Ui.error(this, "Select a patient first"); return; }
        int row = table.getSelectedRow();
        Patient p = model.getAt(row);

        PatientDialog d = new PatientDialog(UiWindow.windowOf(this), "Edit Patient");
        d.name.setText(p.name());
        d.dob.setText(p.dob());
        d.phone.setText(p.phone());
        d.setVisible(true);
        if (!d.isOk()) return;

        Result<Patient> r = controller.update(id, d.name.getText(), d.dob.getText(), d.phone.getText());
        if (!r.isOk()) Ui.error(this, r.error());
        reload();
    }

    @Override protected void onDelete() {
        String id = selectedId(0);
        if (id == null) { Ui.error(this, "Select a patient first"); return; }
        if (!Ui.confirm(this, "Delete patient " + id + "?")) return;

        controller.delete(id);
        reload();
    }
}
