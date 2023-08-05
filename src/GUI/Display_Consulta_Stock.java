package GUI;

import modelo.Grafo;
import modelo.Stock;
import modelo.Centro_Logistico;
import modelo.Gestor_Stock;

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

import exceptions.StockNoEncontradoException;

public class Display_Consulta_Stock extends JPanel{
    JPanel listado;
    JPanel botones;
    GridBagConstraints con;
    Gestor_Stock gestor;
    Centro_Logistico sucursal;
    Grafo grafo;
    Ventana ventana;

    public Display_Consulta_Stock(Ventana ventana, Centro_Logistico sucursal){
        this.ventana=ventana;
        this.setBackground(Color.BLACK);
        this.setLayout(new GridBagLayout());
        this.con = new GridBagConstraints();
        this.sucursal = sucursal;
        //Font txbotones = new Font("Texto de Botones", ALLBITS, 30);
        this.gestor = new Gestor_Stock();
        this.grafo = new Grafo();
        String arreglo[] = null;
        try{
            arreglo = this.obtenerArreglo(gestor.buscarStock(sucursal));
        } catch(StockNoEncontradoException e){
            JOptionPane.showMessageDialog(null, e.getMessage(), "No se encontraron Stocks en la Sucursal", JOptionPane.ERROR_MESSAGE);
            ventana.nuevoPanel(new Display_Consulta_Sucursales(ventana));
        }
        this.listado = cargarLista(arreglo);
        restricciones(1, 0, 1, 1, 0.65, 1.0, GridBagConstraints.BOTH);
        this.add(listado, this.con);
        
        this.botones = new JPanel();
        botones.setBackground(Color.BLACK);
        botones.setLayout(new GridLayout(5,1));

        JPanel pan1 = new JPanel();
        pan1.setBackground(Color.BLACK);
        pan1.setLayout(new GridLayout(2,1));
        JLabel texto1 = new JLabel("Producto");
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
        pan2.setLayout(new GridLayout(3,1));
        JLabel texto2 = new JLabel("Cantidad");
        texto2.setBackground(Color.BLACK);
        texto2.setForeground(Color.GRAY);
        JTextField campo2 = new JTextField();
        campo2.setBackground(Color.BLACK);
        campo2.setForeground(Color.GRAY);
        campo2.setEnabled(true);

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
        pan2.add(texto2);
        pan2.add(campo2);
        pan2.add(checkbox1);

        JPanel pan5 = new JPanel();
        pan5.setBackground(Color.BLACK);
        pan5.setLayout(new GridLayout(3,1));
        JLabel texto5 = new JLabel("Unidad");
        texto5.setBackground(Color.BLACK);
        texto5.setForeground(Color.GRAY);
        JCheckBox check5 = new JCheckBox("Kilogramos");
        check5.setBackground(Color.BLACK);
        check5.setForeground(Color.GRAY);
        JCheckBox check6 = new JCheckBox("Unidades");
        check6.setBackground(Color.BLACK);
        check6.setForeground(Color.GRAY);
        check5.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent arg0) {
                
                if(check5.isSelected()){
                    check6.setSelected(false);
                }
                //throw new UnsupportedOperationException("Unimplemented method 'actionPerformed'");
            }
            
        });
        check6.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent arg0) {
                
                if(check6.isSelected()){
                    check5.setSelected(false);
                }
                //throw new UnsupportedOperationException("Unimplemented method 'actionPerformed'");
            }
            
        });
        pan5.add(texto5);
        pan5.add(check5);
        pan5.add(check6);

        JButton Boton1 = new JButton("Filtrar");
        Boton1.setBackground(Color.GRAY);
        Boton1.setEnabled(true);
        Boton1.addActionListener(new ActionListener() {
            
            public void actionPerformed(ActionEvent arg0) {
                
                List<Stock> a = null;
                try{
                    a = gestor.buscarStock(sucursal);
                } catch(StockNoEncontradoException e){
                    JOptionPane.showMessageDialog(null, e.getMessage(), "No se encontraron Stocks en la Sucursal", JOptionPane.ERROR_MESSAGE);
                    ventana.nuevoPanel(new Display_Consulta_Sucursales(ventana));
                }
                
                if(!(campo1.getText()).equals("")){
                    String cam = campo1.getText();
                    a = gestor.filtrarStock_Producto(a, cam, sucursal);
                }
                if(!(campo2.getText()).equals("")){
                    if(check1.isSelected()){
                        String cam = campo2.getText();
                        a = gestor.filtrarStock_CantidadMayorIgual(a, (Double)Double.parseDouble(cam), sucursal);
                    }
                    if(check2.isSelected()){
                        String cam = campo2.getText();
                        a = gestor.filtrarStock_CantidadMenor(a, (Double)Double.parseDouble(cam), sucursal);
                    }
                    if(check3.isSelected()){
                        String cam = campo2.getText();
                        a = gestor.filtrarStock_CantidadExacta(a, (Double)Double.parseDouble(cam), sucursal);
                    }
                }
                if(check5.isSelected()){
                    a = gestor.filtrarStock_UnidadKilogramos(a, sucursal);
                }
                if(check6.isSelected()){
                    a = gestor.filtrarStock_UnidadUnidad(a, sucursal);
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

                ventana.nuevoPanel(new Display_Consulta_Sucursales(ventana));

                //throw new UnsupportedOperationException("Unimplemented method 'actionPerformed'");
            }
            
        });

        botones.add(pan1);
        botones.add(pan2);
        botones.add(pan5);
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
        restricciones(0, 1, 3, 1, 1.0, 0.95, GridBagConstraints.BOTH);
        lis.add(scroll, this.con);
        
        JLabel id = new JLabel(" Producto ");
        id.setBackground(Color.BLACK);
        id.setForeground(Color.GRAY);
        restricciones(0, 0, 1, 1, 1.0, 0.05, GridBagConstraints.BOTH);
        lis.add(id, this.con);

        JLabel nombre = new JLabel(" Cantidad ");
        nombre.setBackground(Color.BLACK);
        nombre.setForeground(Color.GRAY);
        restricciones(1, 0, 1, 1, 1.0, 0.05, GridBagConstraints.BOTH);
        lis.add(nombre, this.con);

        JLabel hapertura = new JLabel(" Unidad ");
        hapertura.setBackground(Color.BLACK);
        hapertura.setForeground(Color.GRAY);
        restricciones(2, 0, 1, 1, 1.0, 0.05, GridBagConstraints.BOTH);
        lis.add(hapertura, this.con);
    
        JPanel control = new JPanel();
        control.setLayout(new GridLayout(3,1));
        JButton Boton3 = new JButton("Dar de Baja");
        Boton3.setBackground(Color.GRAY);
        Boton3.setEnabled(true);
        Boton3.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent arg0) {
                
                if(lista.getSelectedValue() != null){
                    String valor[] = lista.getSelectedValue().split("      ");
                    try{
                        for(Stock p : gestor.buscarStock(sucursal)){

                            if(valor[1].equals(p.getId_stock().toString())){
                                int n = 5;
                                n = JOptionPane.showConfirmDialog(null, "¿Esta seguro de eliminar el Stock de "+p.getProducto().getNombre()+" de la sucursal "+p.getSucursal().getNombre()+"?","Eliminar Sucursal", JOptionPane.YES_NO_OPTION);
                                // System.out.println("ESTOOO "+n);
                                if(n == 0) gestor.eliminarStock(p);
                                lis.repaint();
                                lis.validate();
                                break;
                            }
                            
                        }
                    } catch(StockNoEncontradoException e){
                        JOptionPane.showMessageDialog(null, e.getMessage(), "No se encontraron Stocks en la sucursal", JOptionPane.ERROR_MESSAGE);
                        ventana.nuevoPanel(new Display_Consulta_Sucursales(ventana));

                    }
                }
                else{
                    JOptionPane.showMessageDialog(null, "por favor seleccione un Stock", "Stock no seleccionado", JOptionPane.WARNING_MESSAGE);
                }
                
                //throw new UnsupportedOperationException("Unimplemented method 'actionPerformed'");
            }
            
        });
        JButton Boton4 = new JButton("Editar Stock");
        Boton4.setBackground(Color.GRAY);
        Boton4.addActionListener(new ActionListener() {

          @Override
          public void actionPerformed(ActionEvent arg0) {
            try {
              if(lista.getSelectedValue() != null){
                String valor[] = lista.getSelectedValue().split("      ");

                for(Stock p : gestor.buscarStock(sucursal)){

                    if(valor[1].equals(p.getId_stock().toString())){
                      ventana.nuevoPanel(new Display_Editar_Stock(ventana, p));
                    }
                  }
                //throw new UnsupportedOperationException("Unimplemented method 'actionPerformed'");
              }  
              else{
                JOptionPane.showMessageDialog(null, "Por favor seleccione un Stock", "Stock no seleccionado", JOptionPane.WARNING_MESSAGE);
              }
            } catch (Exception e) {            }
            
          }
          
        });
        //Implementar edicion de Stock
        JButton Boton5 = new JButton("Alta Stock");
        Boton5.setBackground(Color.GRAY);
        Boton5.addActionListener(new ActionListener() {

          @Override
          public void actionPerformed(ActionEvent arg0) {
              ventana.nuevoPanel(new Display_Alta_Stock(ventana, sucursal));
              
              //throw new UnsupportedOperationException("Unimplemented method 'actionPerformed'");
          }
          
        });
        //Implementar edicion de Stock
        control.add(Boton3);
        control.add(Boton4);
        control.add(Boton5);
        restricciones(3, 0, 1, 1, 0.10, 1.0, GridBagConstraints.BOTH);
        this.add(control,con);

        return lis;
    }

    private String[] obtenerArreglo(ArrayList<Stock> a){
        ArrayList<String> arr = new ArrayList<String>();
        int cont=0;
        for(Stock b : a){
            arr.add(cont, "      "+b.getId_stock()+
                      "      "+b.getProducto().getNombre()+
                      "      "+b.getCantidad().toString()+
                      "      "+b.getUnidad().toString()+
                      "      "+b.getSucursal().getNombre()+
                      "      ");
            //System.out.println(arr.get(cont));
            cont++;
        }
 
        String[] str = new String[arr.size()];
        return arr.toArray(str);
    }

    private String[] obtenerArreglo(List<Stock> a){
        ArrayList<String> arr = new ArrayList<String>();
        int cont=0;
        for(Stock b : a){
            arr.add(cont, "      "+b.getId_stock()+
                      "      "+b.getProducto().getNombre()+
                      "      "+b.getCantidad().toString()+
                      "      "+b.getUnidad().toString()+
                      "      "+b.getSucursal().getNombre()+
                      "      ");
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