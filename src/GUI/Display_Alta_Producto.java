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

public class Display_Alta_Producto extends JPanel{
  GridBagConstraints con = new GridBagConstraints();
  Grafo grafo = new Grafo();

    public Display_Alta_Producto(Ventana ventana){

        this.setBackground(new Color(0x22272e));
        this.setLayout(new GridBagLayout());
        ArrayList<Centro_Logistico> listaCentroLogistico = grafo.getSucursales();
        Centro_Logistico[] listaStucursales = new Centro_Logistico[listaCentroLogistico.size()];
        listaCentroLogistico.toArray(listaStucursales);
        // GridBagConstraints con = new GridBagConstraints();

        Font txbotones = new Font("Texto de Botones", ALLBITS, 30);

        JPanel pan4 = new JPanel(new GridLayout(10, 1, 10, 5));
        pan4.setBackground(new Color(0x22272e));
        JLabel labelNombre = new JLabel("Nombre");
        labelNombre.setBackground(Color.BLACK);
        labelNombre.setForeground(Color.GRAY);
        JTextField inputNombre = new JTextField();
        inputNombre.setBackground(Color.BLACK);
        inputNombre.setForeground(Color.GRAY);
        inputNombre.setEnabled(true);
        pan4.add(labelNombre);
        pan4.add(inputNombre);
        JLabel labelDescripcion = new JLabel("Descripcion");
        labelDescripcion.setBackground(Color.BLACK);
        labelDescripcion.setForeground(Color.GRAY);
        JTextField inputDescripcion = new JTextField();
        inputDescripcion.setBackground(Color.BLACK);
        inputDescripcion.setForeground(Color.GRAY);
        inputDescripcion.setEnabled(true);
        pan4.add(labelDescripcion);
        pan4.add(inputDescripcion);
        JLabel labelPrecio = new JLabel("Precio");
        labelPrecio.setBackground(Color.BLACK);
        labelPrecio.setForeground(Color.GRAY);
        JTextField inputPrecio = new JTextField();
        inputPrecio.setBackground(Color.BLACK);
        inputPrecio.setForeground(Color.GRAY);
        inputPrecio.setEnabled(true);
        pan4.add(labelPrecio);
        pan4.add(inputPrecio);

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
              Gestor_Producto gestor = new Gestor_Producto();
              try {
                Producto producto = new Producto(
                  null, 
                  inputNombre.getText(),
                  inputDescripcion.getText(),
                  Double.parseDouble(inputPrecio.getText())
                );
                gestor.crearProducto(producto);
                JOptionPane.showMessageDialog(null, "Se creó el producto " + producto.getNombre() + ".", "Producto creado correctamente", JOptionPane.INFORMATION_MESSAGE);
              
                ventana.nuevoPanel(new Display_Principal(ventana));
              } catch (Exception e) {
                JOptionPane.showMessageDialog(null, e.getMessage(), "Error al crear el producto", JOptionPane.ERROR_MESSAGE);
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
