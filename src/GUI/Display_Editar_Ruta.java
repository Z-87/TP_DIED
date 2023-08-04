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

public class Display_Editar_Ruta extends JPanel{
  GridBagConstraints con = new GridBagConstraints();
  Grafo grafo = new Grafo();

    public Display_Editar_Ruta(Ventana ventana, Ruta ruta){

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
        inputSucursalOrigen.setSelectedItem(ruta.getSucursal_Origen());
        inputSucursalOrigen.setBackground(Color.BLACK);
        inputSucursalOrigen.setForeground(Color.GRAY);
        inputSucursalOrigen.setEnabled(true);
        pan4.add(labelSucursalOrigen);
        pan4.add(inputSucursalOrigen);
        JLabel labelSucursalDestino = new JLabel("Sucursal Origen");
        labelSucursalDestino.setBackground(Color.BLACK);
        labelSucursalDestino.setForeground(Color.GRAY);
        JComboBox<Centro_Logistico> inputSucursalDestino = new JComboBox<>(listaStucursales);
        inputSucursalDestino.setSelectedItem(ruta.getSucursal_Destino());
        inputSucursalDestino.setBackground(Color.BLACK);
        inputSucursalDestino.setForeground(Color.GRAY);
        inputSucursalDestino.setEnabled(true);
        pan4.add(labelSucursalDestino);
        pan4.add(inputSucursalDestino);
        JLabel labelCapacidad = new JLabel("Capacidad");
        labelCapacidad.setBackground(Color.BLACK);
        labelCapacidad.setForeground(Color.GRAY);
        JTextField inputCapacidad = new JTextField(ruta.getCapacidad().toString());
        inputCapacidad.setBackground(Color.BLACK);
        inputCapacidad.setForeground(Color.GRAY);
        inputCapacidad.setEnabled(true);
        pan4.add(labelCapacidad);
        pan4.add(inputCapacidad);
        JLabel labelDuracion = new JLabel("Duracion");
        labelDuracion.setBackground(Color.BLACK);
        labelDuracion.setForeground(Color.GRAY);
        JTextField inputDuracion = new JTextField(ruta.getDuracion().toString());
        inputDuracion.setBackground(Color.BLACK);
        inputDuracion.setForeground(Color.GRAY);
        inputDuracion.setEnabled(true);
        pan4.add(labelDuracion);
        pan4.add(inputDuracion);
        JLabel labelEstadoRuta = new JLabel("Estado Ruta");
        labelEstadoRuta.setBackground(Color.BLACK);
        labelEstadoRuta.setForeground(Color.GRAY);
        JComboBox<String> inputEstadoRuta = new JComboBox<>(Arrays.toString(ESTADO_RUTA.values()).replaceAll("^.|.$", "").split(", "));
        inputEstadoRuta.setSelectedItem(ruta.getEstado().toString());
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
                ruta.cargarSucursalOrigen((Centro_Logistico)inputSucursalOrigen.getSelectedItem());
                ruta.cargarSucursalDestino((Centro_Logistico)inputSucursalDestino.getSelectedItem());
                ruta.cargarCapacidad(Double.parseDouble(inputCapacidad.getText()));
                ruta.cargarDuracion((Integer.parseInt(inputDuracion.getText())));
                if(inputEstadoRuta.getSelectedItem().equals("OPERATIVA"))ruta.setOperativa();
                else ruta.setNoOperativa();

                JOptionPane.showMessageDialog(null, "Se editó la ruta correctamente.", "Ruta creada correctamente", JOptionPane.INFORMATION_MESSAGE);
              
                ventana.nuevoPanel(new Display_Consulta_Rutas(ventana));
              } catch (Exception e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(null, e.getMessage(), "Error al editar ruta.", JOptionPane.ERROR_MESSAGE);
              }

            }
            
        });
        JButton Boton4 = new JButton("Volver");
        Boton4.setBackground(Color.GRAY);
        Boton4.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent arg0) {

                ventana.nuevoPanel(new Display_Productos(ventana));

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
