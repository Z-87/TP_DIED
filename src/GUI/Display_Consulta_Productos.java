package GUI;

import modelo.Producto;
import modelo.Gestor_Producto;

import java.awt.Color;
//import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

public class Display_Consulta_Productos extends JPanel{
    JPanel listado;
    JPanel botones;
    GridBagConstraints con;
    Gestor_Producto gestor;
    Ventana ventana;

    public Display_Consulta_Productos(Ventana ventana){
        this.ventana=ventana;
        this.setBackground(Color.BLACK);
        this.setLayout(new GridBagLayout());
        this.con = new GridBagConstraints();
        //Font txbotones = new Font("Texto de Botones", ALLBITS, 30);

        this.gestor = new Gestor_Producto();
        String arreglo[] = this.obtenerArreglo(gestor.getProductos());
        this.listado = cargarLista(arreglo);
        restricciones(1, 0, 1, 1, 0.65, 1.0, GridBagConstraints.BOTH);
        this.add(listado, this.con);
        
        this.botones = new JPanel();
        botones.setBackground(Color.BLACK);
        botones.setLayout(new GridLayout(5,1));

        JPanel pan1 = new JPanel();
        pan1.setBackground(Color.BLACK);
        pan1.setLayout(new GridLayout(2,1));
        JLabel texto1 = new JLabel(" Id de Producto ");
        texto1.setBackground(Color.BLACK);
        texto1.setForeground(Color.GRAY);
        JTextField campo1 = new JTextField();
        campo1.setBackground(Color.BLACK);
        campo1.setForeground(Color.GRAY);
        campo1.setEnabled(true);
        pan1.add(texto1);
        pan1.add(campo1);

        JPanel pan2 = new JPanel();
        pan2.setBackground(Color.BLACK);
        pan2.setLayout(new GridLayout(2,1));
        JLabel texto2 = new JLabel(" Nombre Producto ");
        texto2.setBackground(Color.BLACK);
        texto2.setForeground(Color.GRAY);
        JTextField campo2 = new JTextField();
        campo2.setBackground(Color.BLACK);
        campo2.setForeground(Color.GRAY);
        campo2.setEnabled(true);
        pan2.add(texto2);
        pan2.add(campo2);

        JPanel pan4 = new JPanel();
        pan4.setBackground(Color.BLACK);
        pan4.setLayout(new GridLayout(3,1));
        JLabel texto5 = new JLabel("Precio");
        texto5.setBackground(Color.BLACK);
        texto5.setForeground(Color.GRAY);
        JTextField campo5 = new JTextField();
        campo5.setBackground(Color.BLACK);
        campo5.setForeground(Color.GRAY);
        campo5.setEnabled(true);
        JPanel checkbox1 = new JPanel();
        checkbox1.setBackground(Color.BLACK);
        checkbox1.setLayout(new GridLayout(1,3));
        JCheckBox check1 = new JCheckBox("Mayor Igual a");
        check1.setBackground(Color.BLACK);
        check1.setForeground(Color.GRAY);
        JCheckBox check2 = new JCheckBox("Menor");
        check2.setBackground(Color.BLACK);
        check2.setForeground(Color.GRAY);
        JCheckBox check3 = new JCheckBox("Igual");
        check3.setBackground(Color.BLACK);
        check3.setForeground(Color.GRAY);
        check1.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent arg0) {
                
                if(check1.isSelected()){
                    check2.setSelected(false);
                    check3.setSelected(false);
                }

                //throw new UnsupportedOperationException("Unimplemented method 'actionPerformed'");
            }
            
        });
        check2.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent arg0) {
                
                if(check2.isSelected()){
                    check1.setSelected(false);
                    check3.setSelected(false);
                }

                //throw new UnsupportedOperationException("Unimplemented method 'actionPerformed'");
            }
            
        });
        check3.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent arg0) {
                
               if(check3.isSelected()){
                    check1.setSelected(false);
                    check2.setSelected(false);
                }

                //throw new UnsupportedOperationException("Unimplemented method 'actionPerformed'");
            }
            
        });
        checkbox1.add(check1);
        checkbox1.add(check2);
        checkbox1.add(check3);
        pan4.add(texto5);
        pan4.add(campo5);
        pan4.add(checkbox1);

        JButton Boton1 = new JButton("Filtrar");
        Boton1.setBackground(Color.GRAY);
        Boton1.setEnabled(true);
        Boton1.addActionListener(new ActionListener() {
            
            public void actionPerformed(ActionEvent arg0) {
            
                List<Producto> a = gestor.getProductos();
                if(!(campo1.getText()).equals("")){
                    String cam = campo1.getText();
                    a = gestor.filtrarProducto_Id(a, (Integer)Integer.parseInt(cam));
                }
                if(!(campo2.getText()).equals("")){
                    String cam = campo2.getText();
                    a = gestor.filtraProducto_Nombre(a, cam);
                }
                if(!(campo5.getText()).equals("")){
                    if(check1.isSelected()){
                        String cam = campo5.getText();
                        a = gestor.filtrarProducto_PrecioMayorIgual(a, (Double)Double.parseDouble(cam));
                    }
                    else if(check2.isSelected()){
                        String cam = campo5.getText();
                        a = gestor.filtrarProducto_PrecioMenor(a, (Double)Double.parseDouble(cam));
                    }
                    else if(check3.isSelected()){
                        String cam = campo5.getText();
                        a = gestor.filtrarProducto_PrecioExacto(a, (Double)Double.parseDouble(cam));
                    }
                }
            
                reCargarLista(obtenerArreglo(a));
                //throw new UnsupportedOperationException("Unimplemented method 'actionPerformed'");
            
            }
            
            
        });

        JButton Boton2 = new JButton("Volver");
        Boton2.setBackground(Color.GRAY);
        Boton2.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent arg0) {

                ventana.nuevoPanel(new Display_Productos(ventana));

                //throw new UnsupportedOperationException("Unimplemented method 'actionPerformed'");
            }
            
        });

        botones.add(pan1);
        botones.add(pan2);
        botones.add(pan4);
        botones.add(Boton1);
        botones.add(Boton2);
        restricciones(0, 0, 1, 1, 0.25, 1.0, GridBagConstraints.BOTH);
        this.add(botones, this.con);
    }

    private void reCargarLista(String arreglo[]){
        this.listado.removeAll();
        restricciones(0, 0, 1, 1, 1.0, 1.0, GridBagConstraints.BOTH);
        this.listado.add(cargarLista(arreglo), this.con);
        this.listado.repaint();
        this.listado.validate();
    }

    private JPanel cargarLista(String arreglo[]){
        restricciones(0, 0, 1, 1, 1.0, 0.05, GridBagConstraints.BOTH);
        JPanel lis = new JPanel();
        lis.setLayout(new GridBagLayout());
        lis.setBackground(Color.BLACK);

        JList<String> lista = new JList<>();
        lista.setListData(arreglo);
        lista.setBackground(Color.BLACK);
        lista.setForeground(Color.CYAN);
        lista.setVisibleRowCount(10);
        lista.setFixedCellHeight(50);
        JScrollPane scroll = new JScrollPane(lista);
        scroll.setBackground(Color.BLACK);
        restricciones(0, 1, 4, 1, 1.0, 0.95, GridBagConstraints.BOTH);
        lis.add(scroll, this.con);
        
        JLabel id = new JLabel(" Id Prodcuto ");
        id.setBackground(Color.BLACK);
        id.setForeground(Color.GRAY);
        restricciones(0, 0, 1, 1, 1.0, 0.05, GridBagConstraints.BOTH);
        lis.add(id, this.con);

        JLabel nombre = new JLabel(" Nombre Producto ");
        nombre.setBackground(Color.BLACK);
        nombre.setForeground(Color.GRAY);
        restricciones(1, 0, 1, 1, 1.0, 0.05, GridBagConstraints.BOTH);
        lis.add(nombre, this.con);

        JLabel hapertura = new JLabel(" Descripcion ");
        hapertura.setBackground(Color.BLACK);
        hapertura.setForeground(Color.GRAY);
        restricciones(2, 0, 1, 1, 1.0, 0.05, GridBagConstraints.BOTH);
        lis.add(hapertura, this.con);
        
        JLabel hcierre = new JLabel(" Precio ");
        hcierre.setBackground(Color.BLACK);
        hcierre.setForeground(Color.GRAY);
        restricciones(3, 0, 1, 1, 1.0, 0.05, GridBagConstraints.BOTH);
        lis.add(hcierre, this.con);

        JPanel control = new JPanel();
        control.setLayout(new GridLayout(2,1));
        JButton Boton3 = new JButton("Dar de Baja");
        Boton3.setBackground(Color.GRAY);
        Boton3.setEnabled(true);
        Boton3.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent arg0) {
                if(lista.getSelectedValue() != null){
                    String valor[] = lista.getSelectedValue().split("      ");

                    for(Producto p : gestor.getProductos()){

                        if(valor[1].equals(p.getId_producto().toString())){
                            int n = 5;
                            n = JOptionPane.showConfirmDialog(null, "¿Esta seguro de eliminar el Prodcuto "+p.getId_producto()+"?","Eliminar Producto", JOptionPane.YES_NO_OPTION);
                            if(n == 0) gestor.eliminarProducto(p);
                            reCargarLista(obtenerArreglo(gestor.getProductos()));
                        }
                        
                    }
                }
                else{
                    JOptionPane.showMessageDialog(null, "por favor seleccione una Ruta", "Ruta no seleccionada", JOptionPane.WARNING_MESSAGE);
                }
                
                //throw new UnsupportedOperationException("Unimplemented method 'actionPerformed'");
            }
            
        });
        JButton Boton4 = new JButton("Editar Producto");
        Boton4.setBackground(Color.RED);
        //Implementar Edicion de producto
        control.add(Boton3);
        control.add(Boton4);
        restricciones(3, 0, 1, 1, 0.10, 1.0, GridBagConstraints.BOTH);
        this.add(control,con);

        return lis;
    }

    private String[] obtenerArreglo(ArrayList<Producto> a){
        ArrayList<String> arr = new ArrayList<String>();
        int cont=0;
        for(Producto b : a){
            arr.add(cont, "      "+b.getId_producto()+
                      "      "+b.getNombre()+
                      "      "+b.getDescripcion()+
                      "      "+b.getPrecio()+"      ");
            
            //System.out.println(arr.get(cont));
            cont++;
        }
 
        String[] str = new String[arr.size()];
        return arr.toArray(str);

    }

    private String[] obtenerArreglo(List<Producto> a){
        ArrayList<String> arr = new ArrayList<String>();
        int cont=0;
        for(Producto b : a){
            arr.add(cont, "      "+b.getId_producto()+
                      "      "+b.getNombre()+
                      "      "+b.getDescripcion()+
                      "      "+b.getPrecio()+"      ");
            //System.out.println(arr.get(cont));
            cont++;
        }
 
        String[] str = new String[arr.size()];
        return arr.toArray(str);

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