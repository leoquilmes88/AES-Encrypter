package UI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

public class SelectorDirectorioDestino extends JPanel implements ActionListener{

    private final JTextField destino = new JTextField(30);

    public SelectorDirectorioDestino() {
        add(destino);
        JButton elegirDestino = new JButton("Carpeta destino");
        add(elegirDestino);

        elegirDestino.addActionListener(this);
        setLayout(new FlowLayout(FlowLayout.CENTER));
    }

    @Override
    public void actionPerformed(ActionEvent event) {

        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);

        int result = fileChooser.showOpenDialog(this);

        if (result != JFileChooser.CANCEL_OPTION) {

            File fileName = fileChooser.getSelectedFile();

            if ((fileName == null) || (fileName.getName().isEmpty())) {
                destino.setText("...");
            } else {
                destino.setText(fileName.getAbsolutePath());
            }
        }
    }

    public String pathDestino() {
        return this.destino.getText();
    }
}