package UI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

class SelectorOrigen extends JPanel implements ActionListener{

    private final JTextField pathArchivo = new JTextField(30);

    public SelectorOrigen() {
        add(pathArchivo);
        JButton elegirArchivo = new JButton("Archivo origen");
        add(elegirArchivo);

        elegirArchivo.addActionListener(this);
        setLayout(new FlowLayout(FlowLayout.CENTER));
    }

    @Override
    public void actionPerformed(ActionEvent event) {

        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);

        int result = fileChooser.showOpenDialog(this);

        if (result != JFileChooser.CANCEL_OPTION) {

            File fileName = fileChooser.getSelectedFile();

            if ((fileName == null) || (fileName.getName().isEmpty())) {
                pathArchivo.setText("...");
            } else {
                pathArchivo.setText(fileName.getAbsolutePath());
            }
        }
    }

    public String pathOrigen() {
        return this.pathArchivo.getText();
    }
}