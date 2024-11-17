package UI;

import encriptado.Buffer;
import encriptado.DatosProceso;

import javax.swing.*;
import java.awt.*;

public class UI extends JFrame {

    private final Toolkit toolkit = Toolkit.getDefaultToolkit();
    private final SelectorOrigen selectorOrigen = new SelectorOrigen();
    private final SelectorDirectorioDestino selectorDestino = new SelectorDirectorioDestino();
    private final JTextField inputNombre = new JTextField(10);
    private final JTextField inputPassword = new JTextField(10);
    private final Buffer buffer;

    public UI(Buffer buffer) {
        this.buffer = buffer;
        configurar();
        iniciar();
    }

    private void iniciar() {
        setTitle("Encriptador de archivos");
        Image icono = toolkit.getImage("icon.png");
        setIconImage(icono);

        Dimension screenSize = toolkit.getScreenSize();
        setBounds(screenSize.width / 4, screenSize.height / 4, 500 , 245);
        setResizable(false);

        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    private void configurar() {
        setLayout(new FlowLayout(FlowLayout.CENTER));

        Box columna = Box.createVerticalBox();

        Box origen = Box.createHorizontalBox();
        origen.add(selectorOrigen );

        Box directorioSalida = Box.createHorizontalBox();
        directorioSalida.add(selectorDestino );

        Box nombreSalida = Box.createHorizontalBox();
        nombreSalida.add(new JLabel("Nombre archivo nuevo: "));
        nombreSalida.add(Box.createHorizontalStrut(10));
        nombreSalida.add(inputNombre );

        Box password = Box.createHorizontalBox();
        password.add(new JLabel("Password: "));
        password.add(Box.createHorizontalStrut(10));
        password.add(inputPassword );

        Box botonAceptar = Box.createHorizontalBox();
        botonAceptar.add(new BotonAceptar(this));

        columna.add(origen);
        columna.add(directorioSalida);
        columna.add(password);
        columna.add(nombreSalida);
        columna.add(botonAceptar);
        add(columna);
    }

    public void notificar(String mensaje) {
        JOptionPane.showMessageDialog(this, mensaje);
    }

    public DatosProceso getDatos() {
        return (DatosProceso) this.buffer.read();
    }

    public void buidProceso() throws InterruptedException {
        buffer.write(new DatosProceso(selectorOrigen.pathOrigen(), selectorDestino.pathDestino(),
                inputPassword.getText(), inputNombre.getText()));
    }
}