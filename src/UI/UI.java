package UI;

import crypt.Buffer;
import crypt.ProcessData;

import javax.swing.*;
import java.awt.*;

public class UI extends JFrame {

    private final Toolkit toolkit = Toolkit.getDefaultToolkit();
    private final CustomSelector originSelector = new CustomSelector("Archivo origen", JFileChooser.FILES_ONLY);
    private final CustomSelector destinationSelector = new CustomSelector("Carpeta destino", JFileChooser.DIRECTORIES_ONLY);
    private final JTextField outputFileName = new JTextField(10);
    private final JTextField inputPassword = new JTextField(10);
    private final JTextField inputSalt = new JTextField(10);
    private final ActionCombo actionCombo = new ActionCombo();
    private final Buffer buffer;

    public UI(Buffer buffer) {
        this.buffer = buffer;
        init();
        start();
    }

    private void start() {
        setTitle("Encriptador de archivos");
        Image icon = toolkit.getImage("icon.png");
        setIconImage(icon);

        Dimension screenSize = toolkit.getScreenSize();
        setBounds(screenSize.width / 4, screenSize.height / 4, 500 , 225);
        setResizable(false);

        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    private void init() {
        setLayout(new FlowLayout(FlowLayout.CENTER));

        Box column = Box.createVerticalBox();

        Box origin = Box.createHorizontalBox();
        origin.add(originSelector);

        Box outputDirectory = Box.createHorizontalBox();
        outputDirectory.add(destinationSelector);

        Box outputFileNameBox = Box.createHorizontalBox();
        outputFileNameBox.add(new JLabel("Nombre archivo nuevo: "));
        outputFileNameBox.add(Box.createHorizontalStrut(10));
        outputFileNameBox.add(this.outputFileName);

        Box password = Box.createHorizontalBox();
        password.add(new JLabel("Password: "));
        password.add(Box.createHorizontalStrut(10));
        password.add(inputPassword);

        Box salt = Box.createHorizontalBox();
        salt.add(new JLabel("Salt: "));
        salt.add(Box.createHorizontalStrut(10));
        salt.add(inputSalt);

        Box passSalt = Box.createHorizontalBox();
        passSalt.add(password);
        passSalt.add(Box.createHorizontalStrut(10));
        passSalt.add(salt);

        Box actionComboBox = Box.createHorizontalBox();
        actionComboBox.add(new JLabel("Accion: "));
        actionComboBox.add(actionCombo);
        actionComboBox.add(new AcceptButton(this));

        /*Box acceptButton = Box.createHorizontalBox();
        acceptButton.add(new AcceptButton(this));*/

        column.add(origin);
        column.add(outputDirectory);
        column.add(Box.createVerticalStrut(5));
        column.add(passSalt);
        column.add(Box.createVerticalStrut(10));
        column.add(outputFileNameBox);
        column.add(Box.createVerticalStrut(10));
        column.add(actionComboBox);
        //column.add(acceptButton);
        add(column);
    }

    public void notification(String message) {
        JOptionPane.showMessageDialog(this, message);
    }

    public ProcessData getData() {
        return (ProcessData) this.buffer.read();
    }

    public void buildProcess() throws InterruptedException {
        buffer.write(new ProcessData(originSelector.path(), destinationSelector.path(), inputPassword.getText(),
                inputSalt.getText(), actionCombo.getAction(), outputFileName.getText()));
    }
}