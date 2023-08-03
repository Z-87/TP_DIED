package GUI;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Display_Productos extends JPanel{

    public Display_Productos(Ventana ventana){
        
        this.setBackground(Color.BLACK);
        this.setLayout(new GridBagLayout());
        GridBagConstraints con = new GridBagConstraints();

        Font txbotones = new Font("Texto de Botones", ALLBITS, 30);

        JPanel pan4 = new JPanel();
        pan4.setBackground(Color.BLACK);
        JLabel texto = new JLabel("Sistema de Gestion de Distribucion");
        texto.setBackground(Color.BLACK);
        texto.setForeground(Color.GRAY);
        texto.setFont(txbotones);
        con.fill = GridBagConstraints.NONE;
        con.weightx = 0.75;
        con.weighty = 1.0;
        con.gridx = 1;
        con.gridy = 0;
        con.gridwidth = 2;
        con.gridheight = 3;
        this.add(texto, con);

        con.fill = GridBagConstraints.BOTH;

        JPanel pan1 = new JPanel();
        pan1.setBackground(Color.BLACK);
        pan1.setLayout(new GridLayout(0,1));
        JButton Boton1 = new JButton("Consultar Productos");
        Boton1.setBackground(Color.GRAY);
        JButton Boton2 = new JButton("Alta Productos");
        Boton2.setBackground(Color.GRAY);
        JButton Boton3 = new JButton("Volver");
        Boton3.setBackground(Color.GRAY);
        Boton3.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent arg0) {

                ventana.nuevoPanel(new Display_Principal(ventana));

                throw new UnsupportedOperationException("Unimplemented method 'actionPerformed'");
            }
            
        });
        con.weightx = 0.25;
        con.weighty = 1.0;
        con.gridx = 0;
        con.gridy = 0;
        con.gridwidth = 1;
        con.gridheight = 1;
        pan1.add(Boton1);
        pan1.add(Boton2);
        pan1.add(Boton3);
        this.add(pan1, con);
    }
}
