package view;

import javax.swing.*;
import java.awt.*;

public final class UiWindow {
    private UiWindow() {}
    public static Window windowOf(Component c) {
        return SwingUtilities.getWindowAncestor(c);
    }
}
