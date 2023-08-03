package GUI;

import java.awt.GridBagConstraints;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.WindowConstants;

public class Ventana extends JFrame{
    JPanel panel;

    public void inicio(){
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.panel = new Display_Principal(this);

        this.add(this.panel);
        this.pack();
        this.setTitle("Sistema de Gestion");
        this.setSize(1920, 1080);
        this.setVisible(true);
    }

    public void nuevoPanel(JPanel nuevo){
        GridBagConstraints con = new GridBagConstraints();
        con.fill = GridBagConstraints.BOTH;
        con.weightx = 1;
        con.weighty = 1;
        con.gridx = 0;
        con.gridy = 0;
        con.gridwidth = 1;
        con.gridheight = 1;

        this.panel.removeAll();
        this.panel.add(nuevo, con);
        this.panel.repaint();
        this.panel.validate();
    }
}
