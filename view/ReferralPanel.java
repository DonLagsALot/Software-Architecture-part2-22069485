package view;

import controller.ReferralController;
import model.Referral;
import util.Result;

public final class ReferralPanel extends AbstractCrudPanel {
    private final ReferralController controller;
    private final ReferralTableModel model = new ReferralTableModel();

    public ReferralPanel(ReferralController controller) {
        this.controller = controller;
        table.setModel(model);
        reload();
    }

    @Override protected void reload() { model.setData(controller.all()); }

    @Override protected void onAdd() {
        ReferralDialog d = new ReferralDialog(UiWindow.windowOf(this), "Add Referral");
        d.status.setText("Pending");
        d.setVisible(true);
        if (!d.isOk()) return;

        Result<Referral> r = controller.create(
                d.patientId.getText(), d.clinicianId.getText(), d.referredTo.getText(), d.reason.getText(), d.status.getText()
        );
        if (!r.isOk()) Ui.error(this, r.error());
        reload();
    }

    @Override protected void onEdit() {
        String id = selectedId(0);
        if (id == null) { Ui.error(this, "Select a referral first"); return; }
        Referral r0 = model.getAt(table.getSelectedRow());

        ReferralDialog d = new ReferralDialog(UiWindow.windowOf(this), "Edit Referral");
        d.patientId.setText(r0.patientId());
        d.clinicianId.setText(r0.referringClinicianId());
        d.referredTo.setText(r0.referredTo());
        d.reason.setText(r0.reason());
        d.status.setText(r0.status());
        d.setVisible(true);
        if (!d.isOk()) return;

        Result<Referral> r = controller.update(
                id, d.patientId.getText(), d.clinicianId.getText(), d.referredTo.getText(), d.reason.getText(), d.status.getText()
        );
        if (!r.isOk()) Ui.error(this, r.error());
        reload();
    }

    @Override protected void onDelete() {
        String id = selectedId(0);
        if (id == null) { Ui.error(this, "Select a referral first"); return; }
        if (!Ui.confirm(this, "Delete referral " + id + "?")) return;
        controller.delete(id);
        reload();
    }
}
