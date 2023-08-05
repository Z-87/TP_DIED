package GUI;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.LayoutManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.WindowConstants;

import modelo.*;

public class Display_Alta_Orden_Provision extends JPanel{
  GridBagConstraints con = new GridBagConstraints();

  public Display_Alta_Orden_Provision(Ventana ventana, Centro_Logistico sucursalDestino){

        this.setBackground(new Color(0x22272e));
        this.setLayout(new GridBagLayout());
        Gestor_Producto gestorProducto = new Gestor_Producto();
        Gestor_Orden_Provision gestorOrden = new Gestor_Orden_Provision();
        // GridBagConstraints con = new GridBagConstraints();

        Font txbotones = new Font("Texto de Botones", ALLBITS, 30);

        JPanel pan4 = new JPanel(new GridLayout(10, 1, 10, 5));
        pan4.setBackground(new Color(0x22272e));
        JPanel titlePanel = new JPanel();
        titlePanel.setBackground(new Color(0x22272e));
        JLabel title = new JLabel("NUEVA ORDEN DE PROVISIÓN");
        title.setFont(new Font("title", ALLBITS, 22));
        title.setBackground(new Color(0x22272e));
        title.setForeground(Color.GRAY);
        titlePanel.add(title);
        // JLabel labelTiempoEsperado = new JLabel("Tiempo estimado del pedido");
        // labelTiempoEsperado.setBackground(new Color(0x22272e));
        // labelTiempoEsperado.setForeground(Color.GRAY);
        // JTextField inputTiempoEsperado = new JTextField();
        // inputTiempoEsperado.setBackground(new Color(0x22272e));
        // inputTiempoEsperado.setForeground(Color.GRAY);
        // inputTiempoEsperado.setEnabled(true);
        // GridBagConstraints constraint = new GridBagConstraints();
        // constraint.anchor = GridBagConstraints.NORTH;
        pan4.add(titlePanel);
        // pan4.add(labelTiempoEsperado);
        // pan4.add(inputTiempoEsperado);

        ArrayList<JPanel> productos = new ArrayList<>();
        JButton agregarProducto = new JButton("Agregar Producto");
        agregarProducto.addActionListener(new ActionListener() {
          public void actionPerformed(ActionEvent e) {
            JPanel producto = new JPanel(new GridLayout(1, 3));
            JComboBox<Producto> selectProducto = new JComboBox<>(gestorProducto.getProductos().toArray(new Producto[0]));
            selectProducto.setBackground(new Color(0x22272e));
            selectProducto.setForeground(Color.GRAY);
            selectProducto.setEnabled(true);
            JTextField cantidad = new JTextField("Cantidad");
            cantidad.setBackground(new Color(0x22272e));
            cantidad.setForeground(Color.GRAY);
            cantidad.setEnabled(true);
            JComboBox<UNIDAD> unidad = new JComboBox<>(UNIDAD.values());
            unidad.setBackground(new Color(0x22272e));
            unidad.setForeground(Color.GRAY);
            unidad.setEnabled(true);

            producto.add(selectProducto);
            producto.add(cantidad);
            producto.add(unidad);

            productos.add(producto);
            pan4.add(producto);
            pan4.repaint();
            pan4.validate();
          }
        });
        pan4.add(agregarProducto);
        this.con.insets = new Insets(10, 10, 10, 10);
        restricciones(1, 0, 1, 1, 0.8, 1.0, GridBagConstraints.BOTH);
        this.add(pan4, this.con);

        JPanel pan1 = new JPanel();
        pan1.setBackground(new Color(0x22272e));
        pan1.setLayout(new GridLayout(0,1));
        JButton Boton1 = new JButton("Guardar");
        Boton1.setBackground(Color.GRAY);
        Boton1.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent arg0) {
              try {
                  Orden_Provision orden = new Orden_Provision(
                  null, 
                  new Date(), 
                  sucursalDestino, 
                  null, 
                  null, 
                  ESTADO_ORDEN.PENDIENTE, 
                  null, null
                );

                ArrayList<Cantidad> cantidades = new ArrayList<>();
                for(JPanel p : productos){
                  cantidades.add(new Cantidad(
                    null,
                    orden,
                    Double.parseDouble(((JTextField)p.getComponent(1)).getText()),
                    (UNIDAD)((JComboBox<UNIDAD>)p.getComponent(2)).getSelectedItem(),
                    (Producto)((JComboBox<Producto>)p.getComponent(0)).getSelectedItem()
                  ));
                }

                orden.setProductos(cantidades);
                gestorOrden.crearOrden_Provision(orden);

                JOptionPane.showMessageDialog(null, "Se creó la orden de provisión.", "Orden creada correctamente", JOptionPane.INFORMATION_MESSAGE);
                ventana.nuevoPanel(new Display_Consulta_Sucursales(ventana));
              } catch (Exception e) {
                JOptionPane.showMessageDialog(null, e.getMessage(), "Error al crear la orden de pedido", JOptionPane.ERROR_MESSAGE);
              }
              
              // try {
              //   Grafo grafo = new Grafo();
              //   if(inputTipoSucursal.getSelectedItem().equals("Sucursal")){
              //     Sucursal nuevaSucursal = new Sucursal(
              //       null,
              //       inputNombreSucursal.getText(),
              //       ESTADO_SUCURSAL.valueOf((String)inputEstadoSucursal.getSelectedItem()),
              //       inputHorarioApertura.getText(),
              //       inputHorarioCierre.getText()
              //     );
              //     grafo.cargarSucursal(nuevaSucursal);
              //   }else if(inputTipoSucursal.getSelectedItem().equals("Puerto")){
              //     Puerto nuevaSucursal = new Puerto(
              //       null,
              //       inputNombreSucursal.getText(),
              //       ESTADO_SUCURSAL.valueOf((String)inputEstadoSucursal.getSelectedItem()),
              //       inputHorarioApertura.getText(),
              //       inputHorarioCierre.getText()
              //     );
              //     grafo.cargarPuerto(nuevaSucursal);
              //   }else {
              //     Centro nuevaSucursal = new Centro(
              //       null,
              //       inputNombreSucursal.getText(),
              //       ESTADO_SUCURSAL.valueOf((String)inputEstadoSucursal.getSelectedItem()),
              //       inputHorarioApertura.getText(),
              //       inputHorarioCierre.getText()
              //     );
              //     grafo.cargarCentro(nuevaSucursal);
              //   }
                
              //   JOptionPane.showMessageDialog(null, "Se creó la sucursal " + inputNombreSucursal.getText() + ".", "Sucursal creada correctamente", JOptionPane.INFORMATION_MESSAGE);
              //   ventana.nuevoPanel(new Display_Sucursales(ventana));
              // } catch (Exception e) {
              //   JOptionPane.showMessageDialog(null, e.getMessage(), "Error al crear la sucursal", JOptionPane.ERROR_MESSAGE);
              // }
                

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
