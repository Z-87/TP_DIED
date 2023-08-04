package GUI;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.LayoutManager;
// import java.awt.List;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.WindowConstants;

import modelo.*;

public class Display_Editar_Sucursal extends JPanel{
  GridBagConstraints con = new GridBagConstraints();
  Grafo grafo = new Grafo();

  public Display_Editar_Sucursal(Ventana ventana, Centro_Logistico centro_logistico){

        this.setBackground(new Color(0x22272e));
        this.setLayout(new GridBagLayout());
        // GridBagConstraints con = new GridBagConstraints();

        Font txbotones = new Font("Texto de Botones", ALLBITS, 30);

        JPanel pan4 = new JPanel(new GridLayout(8, 1, 10, 5));
        pan4.setBackground(new Color(0x22272e));
        JLabel labelNombreSucursal = new JLabel("Nombre Sucursal");
        labelNombreSucursal.setBackground(Color.BLACK);
        labelNombreSucursal.setForeground(Color.GRAY);
        JTextField inputNombreSucursal = new JTextField(centro_logistico.getNombre());
        inputNombreSucursal.setBackground(Color.BLACK);
        inputNombreSucursal.setForeground(Color.GRAY);
        inputNombreSucursal.setEnabled(true);
        pan4.add(labelNombreSucursal);
        pan4.add(inputNombreSucursal);
        JLabel labelHorarioApertura = new JLabel("Horario Apertura");
        labelHorarioApertura.setBackground(Color.BLACK);
        labelHorarioApertura.setForeground(Color.GRAY);
        JTextField inputHorarioApertura = new JTextField(centro_logistico.getHorario_apertura());
        inputHorarioApertura.setBackground(Color.BLACK);
        inputHorarioApertura.setForeground(Color.GRAY);
        inputHorarioApertura.setEnabled(true);
        pan4.add(labelHorarioApertura);
        pan4.add(inputHorarioApertura);
        JLabel labelHorarioCierre = new JLabel("Horario Cierre");
        labelHorarioCierre.setBackground(Color.BLACK);
        labelHorarioCierre.setForeground(Color.GRAY);
        JTextField inputHorarioCierre = new JTextField(centro_logistico.getHorario_cierre());
        inputHorarioCierre.setBackground(Color.BLACK);
        inputHorarioCierre.setForeground(Color.GRAY);
        inputHorarioCierre.setEnabled(true);
        pan4.add(labelHorarioCierre);
        pan4.add(inputHorarioCierre);
        JLabel labelEstadoSucursal = new JLabel("Estado Sucursal");
        labelEstadoSucursal.setBackground(Color.BLACK);
        labelEstadoSucursal.setForeground(Color.GRAY);
        JComboBox<String> inputEstadoSucursal = new JComboBox<>(Arrays.toString(ESTADO_SUCURSAL.values()).replaceAll("^.|.$", "").split(", "));
        inputEstadoSucursal.setSelectedItem(centro_logistico.getEstado().toString());
        inputEstadoSucursal.setBackground(Color.BLACK);
        inputEstadoSucursal.setForeground(Color.GRAY);
        inputEstadoSucursal.setEnabled(true);
        pan4.add(labelEstadoSucursal);
        pan4.add(inputEstadoSucursal);

        this.con.insets = new Insets(10, 10, 10, 10);
        restricciones(1, 0, 1, 1, 0.8, 1.0, GridBagConstraints.BOTH);
        this.add(pan4, this.con);

        JPanel pan1 = new JPanel();
        pan1.setBackground(Color.BLACK);
        pan1.setLayout(new GridLayout(0,1));
        JButton Boton1 = new JButton("Guardar");
        Boton1.setBackground(Color.GRAY);
        Boton1.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent arg0) {
              try {
                centro_logistico.cargarHorario_Apertura(inputHorarioApertura.getText());
                centro_logistico.cargarHorario_Cierre(inputHorarioCierre.getText());
                centro_logistico.cargarNombre(inputNombreSucursal.getText());
                centro_logistico.cargarEstado((String)inputEstadoSucursal.getSelectedItem());
                
                JOptionPane.showMessageDialog(null, "Se editó la sucursal " + inputNombreSucursal.getText() + ".", "Sucursal editada correctamente", JOptionPane.INFORMATION_MESSAGE);
                ventana.nuevoPanel(new Display_Consulta_Sucursales(ventana));
              } catch (Exception e) {
                JOptionPane.showMessageDialog(null, e.getMessage(), "Error al editar la sucursal", JOptionPane.ERROR_MESSAGE);
              }
                

              throw new UnsupportedOperationException("Unimplemented method 'actionPerformed'");
            }
            
        });
        JButton Boton4 = new JButton("Volver");
        Boton4.setBackground(Color.GRAY);
        Boton4.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent arg0) {

                ventana.nuevoPanel(new Display_Sucursales(ventana));

                throw new UnsupportedOperationException("Unimplemented method 'actionPerformed'");
            }
            
        });
        this.con.insets = new Insets(0, 0, 0, 0);
        restricciones(0, 0, 1, 1, 0.2, 1.0, GridBagConstraints.BOTH);

        pan1.add(Boton1);
        pan1.add(Boton4);
        this.add(pan1, this.con);


    }

    public void restricciones(int gridx, int gridy, int gridwidth, int gridheight ,double weightx, double weighty, int fill){
      this.con.fill = fill;
      this.con.weightx = weightx;
      this.con.weighty = weighty;
      this.con.gridx = gridx;
      this.con.gridy = gridy;
      this.con.gridwidth = gridwidth;
      this.con.gridheight = gridheight;
  }
}
