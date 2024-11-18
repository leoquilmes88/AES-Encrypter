package UI;

import javax.swing.JButton;
import javax.swing.JPanel;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AcceptButton extends JPanel implements ActionListener{

    private final UI parent;

    public AcceptButton(UI uiParent) {
        this.parent = uiParent;
        setLayout(new FlowLayout(FlowLayout.CENTER));
        JButton acceptButton = new JButton("Aceptar");
        acceptButton.addActionListener(this);
        add(acceptButton);
    }

    @Override
    public void actionPerformed(ActionEvent click) {
        try {
            parent.buildProcess();
        } catch (InterruptedException e) {
            parent.notification("Fallo la creacion del proceso");
        }
    }

}