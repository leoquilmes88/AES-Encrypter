package UI;

import javax.swing.JButton;
import javax.swing.JPanel;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BotonAceptar extends JPanel implements ActionListener{

    private final UI parent;

    public BotonAceptar(UI uiParent) {
        this.parent = uiParent;
        setLayout(new FlowLayout(FlowLayout.CENTER));
        JButton botonAceptar = new JButton("Aceptar");
        botonAceptar.addActionListener(this);
        add(botonAceptar);
    }

    @Override
    public void actionPerformed(ActionEvent click) {
        try {
            parent.buidProceso();
        } catch (InterruptedException e) {
            parent.notificar("Fallo la creacion del proceso");
        }
    }

}