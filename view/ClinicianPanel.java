package view;

import controller.ClinicianController;
import model.Clinician;
import util.Result;

public final class ClinicianPanel extends AbstractCrudPanel {
    private final ClinicianController controller;
    private final ClinicianTableModel model = new ClinicianTableModel();

    public ClinicianPanel(ClinicianController controller) {
        this.controller = controller;
        table.setModel(model);
        reload();
    }

    @Override protected void reload() { model.setData(controller.all()); }

    @Override protected void onAdd() {
        ClinicianDialog d = new ClinicianDialog(UiWindow.windowOf(this), "Add Clinician");
        d.setVisible(true);
        if (!d.isOk()) return;

        Result<Clinician> r = controller.create(d.name.getText(), d.specialty.getText(), d.phone.getText());
        if (!r.isOk()) Ui.error(this, r.error());
        reload();
    }

    @Override protected void onEdit() {
        String id = selectedId(0);
        if (id == null) { Ui.error(this, "Select a clinician first"); return; }
        Clinician c = model.getAt(table.getSelectedRow());

        ClinicianDialog d = new ClinicianDialog(UiWindow.windowOf(this), "Edit Clinician");
        d.name.setText(c.name());
        d.specialty.setText(c.specialty());
        d.phone.setText(c.phone());
        d.setVisible(true);
        if (!d.isOk()) return;

        Result<Clinician> r = controller.update(id, d.name.getText(), d.specialty.getText(), d.phone.getText());
        if (!r.isOk()) Ui.error(this, r.error());
        reload();
    }

    @Override protected void onDelete() {
        String id = selectedId(0);
        if (id == null) { Ui.error(this, "Select a clinician first"); return; }
        if (!Ui.confirm(this, "Delete clinician " + id + "?")) return;
        controller.delete(id);
        reload();
    }
}
