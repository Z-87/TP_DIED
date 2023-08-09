package GUI;

import modelo.Grafo;
import modelo.Producto;
import modelo.Ruta;
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

public class Display_Consulta_Rutas extends JPanel{
    JPanel listado;
    JPanel botones;
    GridBagConstraints con;
    Grafo grafo;
    Ventana ventana;

    public Display_Consulta_Rutas(Ventana ventana){
        this.ventana=ventana;
        this.setBackground(Color.BLACK);
        this.setLayout(new GridBagLayout());
        this.con = new GridBagConstraints();
        //Font txbotones = new Font("Texto de Botones", ALLBITS, 30);

        this.grafo = new Grafo();
        String arreglo[] = this.obtenerArreglo(grafo.getRutas());
        this.listado = cargarLista(arreglo);
        restricciones(1, 0, 1, 1, 0.65, 1.0, GridBagConstraints.BOTH);
        this.add(listado, this.con);
        
        this.botones = new JPanel();
        botones.setBackground(Color.BLACK);
        botones.setLayout(new GridLayout(8,1));

        JPanel pan1 = new JPanel();
        pan1.setBackground(Color.BLACK);
        pan1.setLayout(new GridLayout(2,1));
        JLabel texto1 = new JLabel("Id de Ruta");
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
        JLabel texto2 = new JLabel("Sucursal Orgien");
        texto2.setBackground(Color.BLACK);
        texto2.setForeground(Color.GRAY);
        JTextField campo2 = new JTextField();
        campo2.setBackground(Color.BLACK);
        campo2.setForeground(Color.GRAY);
        campo2.setEnabled(true);
        pan2.add(texto2);
        pan2.add(campo2);

        JPanel pan3 = new JPanel();
        pan3.setBackground(Color.BLACK);
        pan3.setLayout(new GridLayout(2,1));
        JLabel texto3 = new JLabel("Sucursal Destino");
        texto3.setBackground(Color.BLACK);
        texto3.setForeground(Color.GRAY);
        JTextField campo3 = new JTextField();
        campo3.setBackground(Color.BLACK);
        campo3.setForeground(Color.GRAY);
        campo3.setEnabled(true);
        pan3.add(texto3);
        pan3.add(campo3);

        JPanel pan4 = new JPanel();
        pan4.setBackground(Color.BLACK);
        pan4.setLayout(new GridLayout(3,1));
        JLabel texto4 = new JLabel("Capacidad");
        texto4.setBackground(Color.BLACK);
        texto4.setForeground(Color.GRAY);
        JTextField campo4 = new JTextField();
        campo4.setBackground(Color.BLACK);
        campo4.setForeground(Color.GRAY);
        campo4.setEnabled(true);
        JPanel checkbox1 = new JPanel();
        checkbox1.setBackground(Color.BLACK);
        checkbox1.setLayout(new GridLayout(1,3));
        JCheckBox check4 = new JCheckBox("Mayor Igual a");
        check4.setBackground(Color.BLACK);
        check4.setForeground(Color.GRAY);
        JCheckBox check5 = new JCheckBox("Menor");
        check5.setBackground(Color.BLACK);
        check5.setForeground(Color.GRAY);
        JCheckBox check6 = new JCheckBox("Igual");
        check6.setBackground(Color.BLACK);
        check6.setForeground(Color.GRAY);
        check4.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent arg0) {
                
                if(check4.isSelected()){
                    check5.setSelected(false);
                    check6.setSelected(false);
                }

                //throw new UnsupportedOperationException("Unimplemented method 'actionPerformed'");
            }
            
        });
        check5.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent arg0) {
                
                if(check5.isSelected()){
                    check4.setSelected(false);
                    check6.setSelected(false);
                }

                //throw new UnsupportedOperationException("Unimplemented method 'actionPerformed'");
            }
            
        });
        check6.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent arg0) {
                
               if(check6.isSelected()){
                    check4.setSelected(false);
                    check5.setSelected(false);
                }

                //throw new UnsupportedOperationException("Unimplemented method 'actionPerformed'");
            }
            
        });
        checkbox1.add(check4);
        checkbox1.add(check5);
        checkbox1.add(check6);
        pan4.add(texto4);
        pan4.add(campo4);
        pan4.add(checkbox1);

        JPanel pan5 = new JPanel();
        pan5.setBackground(Color.BLACK);
        pan5.setLayout(new GridLayout(3,1));
        JLabel texto5 = new JLabel("Duracion");
        texto5.setBackground(Color.BLACK);
        texto5.setForeground(Color.GRAY);
        JTextField campo5 = new JTextField();
        campo5.setBackground(Color.BLACK);
        campo5.setForeground(Color.GRAY);
        campo5.setEnabled(true);
        JPanel checkbox2 = new JPanel();
        checkbox2.setBackground(Color.BLACK);
        checkbox2.setLayout(new GridLayout(1,3));
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
        checkbox2.add(check1);
        checkbox2.add(check2);
        checkbox2.add(check3);
        pan5.add(texto5);
        pan5.add(campo5);
        pan5.add(checkbox2);

        
        JPanel pan6 = new JPanel();
        pan6.setBackground(Color.BLACK);
        pan6.setLayout(new GridLayout(3,1));
        JLabel texto6 = new JLabel("Estado de Orden");
        texto6.setBackground(Color.BLACK);
        texto6.setForeground(Color.GRAY);
        JCheckBox check7 = new JCheckBox("Operativa");
        check7.setBackground(Color.BLACK);
        check7.setForeground(Color.GRAY);
        JCheckBox check8 = new JCheckBox("No Operativa");
        check8.setBackground(Color.BLACK);
        check8.setForeground(Color.GRAY);
        check7.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent arg0) {
                
                if(check7.isSelected()) check8.setSelected(false);

                //throw new UnsupportedOperationException("Unimplemented method 'actionPerformed'");
            }
            
        });
        check8.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent arg0) {
                
                if(check8.isSelected()) check7.setSelected(false);

                //throw new UnsupportedOperationException("Unimplemented method 'actionPerformed'");
            }
            
        });
        pan6.add(texto6);
        pan6.add(check7);
        pan6.add(check8);

        JButton Boton1 = new JButton("Filtrar");
        Boton1.setBackground(Color.GRAY);
        Boton1.setEnabled(true);
        Boton1.addActionListener(new ActionListener() {
            
            public void actionPerformed(ActionEvent arg0) {
                
                List<Ruta> a = grafo.getRutas();
                if(!(campo1.getText()).equals("")){
                    String cam = campo1.getText();
                    a = grafo.filtrarRuta_Id(a, (Integer)Integer.parseInt(cam));
                }
                if(!(campo2.getText()).equals("")){
                    String cam = campo2.getText();
                    a = grafo.filtrarRutas_sucursalOrigen(a, grafo.consultarScursal_Id((Integer)Integer.parseInt(cam)));
                }
                if(!(campo3.getText()).equals("")){
                    String cam = campo3.getText();
                    a = grafo.filtrarRutas_sucursalDestino(a, grafo.consultarScursal_Id((Integer)Integer.parseInt(cam)));
                }
                if(!(campo4.getText()).equals("")){
                    if(check4.isSelected()){
                        String cam = campo4.getText();
                        a = grafo.filtrarRutas_capacidadMayorIgual(a, (Double)Double.parseDouble(cam));
                    }
                    else if(check5.isSelected()){
                        String cam = campo4.getText();
                        a = grafo.filtrarRutas_capacidadMenor(a, (Double)Double.parseDouble(cam));
                    }
                    else if(check6.isSelected()){
                        String cam = campo4.getText();
                        a = grafo.filtrarRutas_capacidadExacta(a, (Double)Double.parseDouble(cam));
                    }
                }
                if(!(campo5.getText()).equals("")){
                    if(check1.isSelected()){
                        String cam = campo5.getText();
                        a = grafo.filtrarRutas_duracionMayorIgual(a, (Double)Double.parseDouble(cam));
                    }
                    else if(check2.isSelected()){
                        String cam = campo5.getText();
                        a = grafo.filtrarRutas_duracionMenor(a, (Double)Double.parseDouble(cam));
                    }
                    else if(check3.isSelected()){
                        String cam = campo5.getText();
                        a = grafo.filtrarRutas_duracionExacta(a, (Double)Double.parseDouble(cam));
                    }
                }
                
                if(check7.isSelected()){
                    a = grafo.filtrarRutas_Operativas(a);
                }
                if(check8.isSelected()){
                    a = grafo.filtrarRutas_NoOperativas(a);
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

                ventana.nuevoPanel(new Display_Principal(ventana));

                //throw new UnsupportedOperationException("Unimplemented method 'actionPerformed'");
            }
            
        });

        botones.add(pan1);
        botones.add(pan2);
        botones.add(pan3);
        botones.add(pan4);
        botones.add(pan5);
        botones.add(pan6);
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
        restricciones(0, 1, 6, 1, 1.0, 0.95, GridBagConstraints.BOTH);
        lis.add(scroll, this.con);
        
        JLabel id = new JLabel(" Id Ruta ");
        id.setBackground(Color.BLACK);
        id.setForeground(Color.GRAY);
        restricciones(0, 0, 1, 1, 1.0, 0.05, GridBagConstraints.BOTH);
        lis.add(id, this.con);

        JLabel nombre = new JLabel(" Sucursal Origen ");
        nombre.setBackground(Color.BLACK);
        nombre.setForeground(Color.GRAY);
        restricciones(1, 0, 1, 1, 1.0, 0.05, GridBagConstraints.BOTH);
        lis.add(nombre, this.con);

        JLabel hapertura = new JLabel(" Sucursal Destino ");
        hapertura.setBackground(Color.BLACK);
        hapertura.setForeground(Color.GRAY);
        restricciones(2, 0, 1, 1, 1.0, 0.05, GridBagConstraints.BOTH);
        lis.add(hapertura, this.con);
        
        JLabel hcierre = new JLabel(" Capacidad ");
        hcierre.setBackground(Color.BLACK);
        hcierre.setForeground(Color.GRAY);
        restricciones(3, 0, 1, 1, 1.0, 0.05, GridBagConstraints.BOTH);
        lis.add(hcierre, this.con);

        JLabel estado = new JLabel(" Duracion ");
        estado.setBackground(Color.BLACK);
        estado.setForeground(Color.GRAY);
        restricciones(4, 0, 1, 1, 1.0, 0.05, GridBagConstraints.BOTH);
        lis.add(estado, this.con);
        
        JLabel pageRank = new JLabel(" Estado ");
        pageRank.setBackground(Color.BLACK);
        pageRank.setForeground(Color.GRAY);
        restricciones(5, 0, 1, 1, 1.0, 0.05, GridBagConstraints.BOTH);
        lis.add(pageRank, this.con);

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

                    for(Ruta p : grafo.getRutas()){

                        if(valor[1].equals(p.getId_ruta().toString())){
                            int n = 5;
                            n = JOptionPane.showConfirmDialog(null, "¿Esta seguro de eliminar la Ruta "+p.getId_ruta()+"?","Eliminar Ruta", JOptionPane.YES_NO_OPTION);
                            //System.out.println("ESTOOO "+n);
                            if(n == 0) grafo.eliminarRuta(p);
                            reCargarLista(obtenerArreglo(grafo.getRutas()));
                        }
                        
                    }
                }
                else{
                    JOptionPane.showMessageDialog(null, "por favor seleccione una Ruta", "Ruta no seleccionada", JOptionPane.WARNING_MESSAGE);
                }
                
                //throw new UnsupportedOperationException("Unimplemented method 'actionPerformed'");
            }
            
        });
        JButton Boton4 = new JButton("Editar Ruta");
        Boton4.setBackground(Color.GRAY);
        Boton4.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent arg0) {
                if(lista.getSelectedValue() != null){
                    String valor[] = lista.getSelectedValue().split("      ");

                    for(Ruta p : grafo.getRutas()){

                        if(valor[1].equals(p.getId_ruta().toString())){
                            ventana.nuevoPanel(new Display_Editar_Ruta(ventana, p));
                        }
                        
                    }
                }
                else{
                    JOptionPane.showMessageDialog(null, "Por favor seleccione una ruta", "Producto no seleccionado", JOptionPane.WARNING_MESSAGE);
                }
                
                //throw new UnsupportedOperationException("Unimplemented method 'actionPerformed'");
            }
            
        });
        //Implementar Asignacion de recorrido
        control.add(Boton3);
        control.add(Boton4);
        restricciones(3, 0, 1, 1, 0.10, 1.0, GridBagConstraints.BOTH);
        this.add(control,con);

        return lis;
    }

    private String[] obtenerArreglo(ArrayList<Ruta> a){
        ArrayList<String> arr = new ArrayList<String>();
        int cont=0;
        for(Ruta b : a){
            arr.add(cont, "      "+b.getId_ruta()+
                      "      "+b.getSucursal_Origen().getId_logistico().toString()+
                      "      "+b.getSucursal_Destino().getId_logistico().toString()+
                      "      "+b.getCapacidad()+
                      "      "+b.getDuracion()+
                      "      "+b.getEstado().toString()+"      ");
            
            //System.out.println(arr.get(cont));
            cont++;
        }
 
        String[] str = new String[arr.size()];
        return arr.toArray(str);

    }

    private String[] obtenerArreglo(List<Ruta> a){
        ArrayList<String> arr = new ArrayList<String>();
        int cont=0;
        for(Ruta b : a){
            arr.add(cont, "      "+b.getId_ruta()+
                      "      "+b.getSucursal_Origen().getId_logistico().toString()+
                      "      "+b.getSucursal_Destino().getId_logistico().toString()+
                      "      "+b.getCapacidad()+
                      "      "+b.getDuracion()+
                      "      "+b.getEstado().toString()+"      ");
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