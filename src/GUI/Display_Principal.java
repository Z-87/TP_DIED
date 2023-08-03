package GUI;

import java.lang.reflect.GenericDeclaration;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridLayout;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JComponent;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.WindowConstants;
import javax.swing.border.Border;

public class Display_Principal extends JPanel{

    public Display_Principal(Ventana ventana){
        
        this.setBackground(Color.BLACK);
        this.setLayout(new GridBagLayout());
        GridBagConstraints con = new GridBagConstraints();

        Font txbotones = new Font("Texto de Botones", ALLBITS, 30);
        this.setBackground(Color.BLACK);
        this.setLayout(new GridBagLayout());

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

        JPanel pan1 = new JPanel();
        pan1.setBackground(Color.BLACK);
        pan1.setLayout(new GridLayout(0,1));
        JButton Boton1 = new JButton("SUCURSALES");
        Boton1.setBackground(Color.GRAY);

        Boton1.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent arg0) {

                Display_Sucursales suc = new Display_Sucursales(ventana);
                ventana.nuevoPanel(suc);

                throw new UnsupportedOperationException("Unimplemented method 'actionPerformed'");
            }
            
        });
        con.fill = GridBagConstraints.BOTH;
        con.weightx = 0.25;
        con.weighty = 1.0;
        con.gridx = 0;
        con.gridy = 0;
        con.gridwidth = 1;
        con.gridheight = 1;
        pan1.add(Boton1);
        this.add(pan1, con);

        JPanel pan2 = new JPanel();
        pan2.setBackground(Color.BLACK);
        pan2.setLayout(new GridLayout(0,1));
        JButton Boton3 = new JButton("Rutas");
        Boton3.setBackground(Color.GRAY);
        Boton3.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent arg0) {

                Display_Rutas suc = new Display_Rutas(ventana);
                ventana.nuevoPanel(suc);

                throw new UnsupportedOperationException("Unimplemented method 'actionPerformed'");
            }
            
        });
        con.weightx = 0.25;
        con.weighty = 1.0;
        con.gridx = 0;
        con.gridy = 1;
        con.gridwidth = 1;
        con.gridheight = 1;
        pan2.add(Boton3);
        this.add(pan2, con);

        JPanel pan3 = new JPanel();
        pan3.setBackground(Color.BLACK);
        pan3.setLayout(new GridLayout(0,1));
        JButton Boton5 = new JButton("Productos");
        Boton5.setBackground(Color.GRAY);
        Boton5.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent arg0) {

                Display_Productos suc = new Display_Productos(ventana);
                ventana.nuevoPanel(suc);

                throw new UnsupportedOperationException("Unimplemented method 'actionPerformed'");
            }
            
        });
        con.weightx = 0.25;
        con.weighty = 1.0;
        con.gridx = 0;
        con.gridy = 2;
        con.gridwidth = 1;
        con.gridheight = 1;
        pan3.add(Boton5);
        this.add(pan3, con);  
    }

}
