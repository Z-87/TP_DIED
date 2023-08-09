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

public class Display_Alta_Ruta extends JPanel{
  GridBagConstraints con = new GridBagConstraints();
  Grafo grafo = new Grafo();

    public Display_Alta_Ruta(Ventana ventana){

        this.setBackground(new Color(0x22272e));
        this.setLayout(new GridBagLayout());
        ArrayList<Centro_Logistico> listaCentroLogistico = grafo.getSucursales();
        Centro_Logistico[] listaStucursales = new Centro_Logistico[listaCentroLogistico.size()];
        listaCentroLogistico.toArray(listaStucursales);
        // GridBagConstraints con = new GridBagConstraints();

        Font txbotones = new Font("Texto de Botones", ALLBITS, 30);

        JPanel pan4 = new JPanel(new GridLayout(10, 1, 10, 5));
        pan4.setBackground(new Color(0x22272e));
        JLabel labelSucursalOrigen = new JLabel("Sucursal Origen");
        labelSucursalOrigen.setBackground(Color.BLACK);
        labelSucursalOrigen.setForeground(Color.GRAY);
        JComboBox<Centro_Logistico> inputSucursalOrigen = new JComboBox<>(listaStucursales);
        inputSucursalOrigen.setBackground(Color.BLACK);
        inputSucursalOrigen.setForeground(Color.GRAY);
        inputSucursalOrigen.setEnabled(true);
        pan4.add(labelSucursalOrigen);
        pan4.add(inputSucursalOrigen);
        JLabel labelSucursalDestino = new JLabel("Sucursal Destino");
        labelSucursalDestino.setBackground(Color.BLACK);
        labelSucursalDestino.setForeground(Color.GRAY);
        JComboBox<Centro_Logistico> inputSucursalDestino = new JComboBox<>(listaStucursales);
        inputSucursalDestino.setBackground(Color.BLACK);
        inputSucursalDestino.setForeground(Color.GRAY);
        inputSucursalDestino.setEnabled(true);
        pan4.add(labelSucursalDestino);
        pan4.add(inputSucursalDestino);
        JLabel labelCapacidad = new JLabel("Capacidad");
        labelCapacidad.setBackground(Color.BLACK);
        labelCapacidad.setForeground(Color.GRAY);
        JTextField inputCapacidad = new JTextField();
        inputCapacidad.setBackground(Color.BLACK);
        inputCapacidad.setForeground(Color.GRAY);
        inputCapacidad.setEnabled(true);
        pan4.add(labelCapacidad);
        pan4.add(inputCapacidad);
        JLabel labelDuracion = new JLabel("Duracion");
        labelDuracion.setBackground(Color.BLACK);
        labelDuracion.setForeground(Color.GRAY);
        JTextField inputDuracion = new JTextField();
        inputDuracion.setBackground(Color.BLACK);
        inputDuracion.setForeground(Color.GRAY);
        inputDuracion.setEnabled(true);
        pan4.add(labelDuracion);
        pan4.add(inputDuracion);
        JLabel labelEstadoRuta = new JLabel("Estado Ruta");
        labelEstadoRuta.setBackground(Color.BLACK);
        labelEstadoRuta.setForeground(Color.GRAY);
        JComboBox<String> inputEstadoRuta = new JComboBox<>(Arrays.toString(ESTADO_RUTA.values()).replaceAll("^.|.$", "").split(", "));
        inputEstadoRuta.setBackground(Color.BLACK);
        inputEstadoRuta.setForeground(Color.GRAY);
        inputEstadoRuta.setEnabled(true);
        pan4.add(labelEstadoRuta);
        pan4.add(inputEstadoRuta);

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
              // System.out.println((String)inputTipoSucursal.getSelectedItem());
              
              try {
                Ruta ruta = new Ruta(
                  null, 
                  (Centro_Logistico)inputSucursalOrigen.getSelectedItem(), 
                  (Centro_Logistico)inputSucursalDestino.getSelectedItem(),
                  Double.parseDouble(inputCapacidad.getText()), 
                  Integer.parseInt(inputDuracion.getText()),
                  ESTADO_RUTA.valueOf((String)inputEstadoRuta.getSelectedItem())
                );
                grafo.cargarRuta(ruta);
                JOptionPane.showMessageDialog(null, "Se creó una ruta desde " + inputSucursalOrigen.getSelectedItem().toString() + " hasta " + inputSucursalDestino.getSelectedItem().toString() + ".", "Ruta creada correctamente", JOptionPane.INFORMATION_MESSAGE);
              
                ventana.nuevoPanel(new Display_Principal(ventana));
              } catch (Exception e) {
                JOptionPane.showMessageDialog(null, e.getMessage(), "Error al crear la ruta", JOptionPane.ERROR_MESSAGE);
              }

            }
            
        });
        JButton Boton4 = new JButton("Volver");
        Boton4.setBackground(Color.GRAY);
        Boton4.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent arg0) {

                ventana.nuevoPanel(new Display_Principal(ventana));

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
