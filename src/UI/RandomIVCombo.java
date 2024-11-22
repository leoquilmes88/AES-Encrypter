package UI;

import javax.swing.*;
import java.awt.*;

public class RandomIVCombo extends JPanel {

    private final JComboBox<Boolean> actionCombo = new JComboBox<>();

    public RandomIVCombo() {
        setLayout(new FlowLayout(FlowLayout.CENTER));

        actionCombo.addItem(true);
        actionCombo.addItem(false);

        add(actionCombo);
    }

    public Boolean getElection() {
        return (Boolean) actionCombo.getSelectedItem();
    }
}
