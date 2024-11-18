package UI;

import crypt.ActionMode;

import javax.swing.*;
import java.awt.*;

public class ActionCombo extends JPanel{
    private final JComboBox<ActionMode> actionCombo = new JComboBox<ActionMode>();

    public ActionCombo() {
        setLayout(new FlowLayout(FlowLayout.CENTER));

        for (ActionMode action:ActionMode.values()){
            actionCombo.addItem(action);
        }

        add(actionCombo);
    }

    public ActionMode getAction() {
        return (ActionMode) actionCombo.getSelectedItem();
    }
}
