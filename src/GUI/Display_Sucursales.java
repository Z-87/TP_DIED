package GUI;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.WindowConstants;

public class Display_Sucursales extends JPanel{

    public Display_Sucursales(Ventana ventana){

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
        JButton Boton1 = new JButton("Consultar Sucursal");
        Boton1.setBackground(Color.GRAY);
        Boton1.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent arg0) {
                
                ventana.nuevoPanel(new Display_Consulta_Sucursales(ventana));

                //throw new UnsupportedOperationException("Unimplemented method 'actionPerformed'");
            }
            
        });
        JButton Boton2 = new JButton("Alta Sucursal");
        Boton2.setBackground(Color.GRAY);
        Boton2.addActionListener(new ActionListener() {

          @Override
          public void actionPerformed(ActionEvent arg0) {

              Display_Alta_Sucursal suc = new Display_Alta_Sucursal(ventana);
              ventana.nuevoPanel(suc);

              // throw new UnsupportedOperationException("Unimplemented method 'actionPerformed'");
          }
          
      });
        JButton Boton3 = new JButton("Consultar Ordenes de Provision");
        Boton3.setBackground(Color.GRAY);
        Boton3.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent arg0) {

                ventana.nuevoPanel(new Display_Consulta_OrdenDeProvision(ventana));
                
                //throw new UnsupportedOperationException("Unimplemented method 'actionPerformed'");
            }
            
        });
        JButton Boton4 = new JButton("Volver");
        Boton4.setBackground(Color.GRAY);
        Boton4.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent arg0) {

                ventana.nuevoPanel(new Display_Principal(ventana));
                
                //throw new UnsupportedOperationException("Unimplemented method 'actionPerformed'");
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
        pan1.add(Boton4);
        this.add(pan1, con);

    }
}
