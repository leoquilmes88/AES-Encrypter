package UI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

public class CustomSelector extends JPanel implements ActionListener{
    private final JTextField path = new JTextField(30);
    private final int selectionModeIndex;

    public CustomSelector(String buttonMessage, int selectionModeIndex) {
        this.selectionModeIndex = selectionModeIndex;
        add(path);
        JButton button = new JButton(buttonMessage);
        add(button);

        button.addActionListener(this);
        setLayout(new FlowLayout(FlowLayout.CENTER));
    }

    @Override
    public void actionPerformed(ActionEvent event) {

        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setFileSelectionMode(selectionModeIndex);

        int result = fileChooser.showOpenDialog(this);

        if (result != JFileChooser.CANCEL_OPTION) {

            File fileName = fileChooser.getSelectedFile();

            if ((fileName == null) || (fileName.getName().isEmpty())) {
                path.setText("...");
            } else {
                path.setText(fileName.getAbsolutePath());
            }
        }
    }

    public String path() {
        return this.path.getText();
    }
}
