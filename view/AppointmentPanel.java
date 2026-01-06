package view;

import controller.AppointmentController;
import model.Appointment;
import util.Result;

public final class AppointmentPanel extends AbstractCrudPanel {
    private final AppointmentController controller;
    private final AppointmentTableModel model = new AppointmentTableModel();

    public AppointmentPanel(AppointmentController controller) {
        this.controller = controller;
        table.setModel(model);
        reload();
    }

    @Override protected void reload() { model.setData(controller.all()); }

    @Override protected void onAdd() {
        AppointmentDialog d = new AppointmentDialog(UiWindow.windowOf(this), "Add Appointment");
        d.setVisible(true);
        if (!d.isOk()) return;

        Result<Appointment> r = controller.create(d.patientId.getText(), d.clinicianId.getText(), d.dateTime.getText(), d.reason.getText());
        if (!r.isOk()) Ui.error(this, r.error());
        reload();
    }

    @Override protected void onEdit() {
        String id = selectedId(0);
        if (id == null) { Ui.error(this, "Select an appointment first"); return; }
        Appointment a = model.getAt(table.getSelectedRow());

        AppointmentDialog d = new AppointmentDialog(UiWindow.windowOf(this), "Edit Appointment");
        d.patientId.setText(a.patientId());
        d.clinicianId.setText(a.clinicianId());
        d.dateTime.setText(a.dateTime());
        d.reason.setText(a.reason());
        d.setVisible(true);
        if (!d.isOk()) return;

        Result<Appointment> r = controller.update(id, d.patientId.getText(), d.clinicianId.getText(), d.dateTime.getText(), d.reason.getText());
        if (!r.isOk()) Ui.error(this, r.error());
        reload();
    }

    @Override protected void onDelete() {
        String id = selectedId(0);
        if (id == null) { Ui.error(this, "Select an appointment first"); return; }
        if (!Ui.confirm(this, "Delete appointment " + id + "?")) return;
        controller.delete(id);
        reload();
    }
}
