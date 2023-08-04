package GUI;

import modelo.Grafo;
import modelo.Puerto;
import modelo.Centro_Logistico;
import java.awt.Color;
//import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
//import java.awt.List;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

public class Display_Consulta_Stock extends JPanel{
    JPanel listado;
    JPanel botones;
    GridBagConstraints con;
    Grafo grafo;
    Ventana ventana;

    public Display_Consulta_Stock(Ventana ventana){
        this.ventana=ventana;
        this.setBackground(Color.BLACK);
        this.setLayout(new GridBagLayout());
        this.con = new GridBagConstraints();
        //Font txbotones = new Font("Texto de Botones", ALLBITS, 30);

        this.grafo = new Grafo();
        //g.cargarSucursales();
        String arreglo[] = this.obtenerArreglo(grafo.getSucursales());
        //this.listado = cargarLista(arreglo);
        this.listado = cargarLista();
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
        pan2.setLayout(new GridLayout(2,1));
        JLabel texto2 = new JLabel("Cantidad");
        texto2.setBackground(Color.BLACK);
        texto2.setForeground(Color.GRAY);
        JTextField campo2 = new JTextField();
        campo2.setBackground(Color.BLACK);
        campo2.setForeground(Color.GRAY);
        campo2.setEnabled(true);
        pan2.add(texto2);
        pan2.add(campo2);
        
        JPanel pan5 = new JPanel();
        pan5.setBackground(Color.BLACK);
        pan5.setLayout(new GridLayout(3,1));
        JLabel texto5 = new JLabel("Unidad");
        texto5.setBackground(Color.BLACK);
        texto5.setForeground(Color.GRAY);
        JCheckBox check1 = new JCheckBox("Kilogramos");
        check1.setBackground(Color.BLACK);
        check1.setForeground(Color.GRAY);
        JCheckBox check2 = new JCheckBox("Unidades");
        check2.setBackground(Color.BLACK);
        check2.setForeground(Color.GRAY);
        check1.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent arg0) {
                
                if(check1.isSelected()) check2.setEnabled(false);
                else check2.setEnabled(true);

                //throw new UnsupportedOperationException("Unimplemented method 'actionPerformed'");
            }
            
        });
        check2.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent arg0) {
                
                if(check2.isSelected()) check1.setEnabled(false);
                else check1.setEnabled(true);

                //throw new UnsupportedOperationException("Unimplemented method 'actionPerformed'");
            }
            
        });
        pan5.add(texto5);
        pan5.add(check1);
        pan5.add(check2);

        JButton Boton1 = new JButton("Filtrar");
        Boton1.setBackground(Color.GRAY);
        Boton1.setEnabled(true);
        Boton1.addActionListener(new ActionListener() {
            
            public void actionPerformed(ActionEvent arg0) {
                /*
                List<Centro_Logistico> a = grafo.getSucursales();
                if(campo1.getText() != null){
                    String cam = campo1.getText();
                    a = grafo.filtrarScursales_Nombre(a, cam);
                }
                if(check1.isSelected() && !check2.isEnabled()){
                    a = grafo.filtrarScursales_Operativas(a);
                }
                if(check2.isSelected() && !check1.isEnabled()){
                    a = grafo.filtrarScursales_NoOperativas(a);
                }
                reCargarLista(obtenerArreglo(a));
                */
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

    //private void reCargarLista(String arreglo[]){
    private void reCargarLista(){
        this.listado.removeAll();
        restricciones(0, 0, 1, 1, 1.0, 1.0, GridBagConstraints.BOTH);
        //this.listado.add(cargarLista(arreglo), this.con);
        this.listado.add(cargarLista(), this.con);
        this.listado.repaint();
        this.listado.validate();
    }

    //private JPanel cargarLista(String arreglo[]){
    private JPanel cargarLista(){
        restricciones(0, 0, 1, 1, 1.0, 0.05, GridBagConstraints.BOTH);
        JPanel lis = new JPanel();
        lis.setLayout(new GridBagLayout());
        lis.setBackground(Color.BLACK);

        JList<String> lista = new JList<>();
        //lista.setListData(arreglo);
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
        /*
        JLabel hcierre = new JLabel(" Horario Cierre ");
        hcierre.setBackground(Color.BLACK);
        hcierre.setForeground(Color.GRAY);
        restricciones(3, 0, 1, 1, 1.0, 0.05, GridBagConstraints.BOTH);
        lis.add(hcierre, this.con);

        JLabel estado = new JLabel(" Estado ");
        estado.setBackground(Color.BLACK);
        estado.setForeground(Color.GRAY);
        restricciones(4, 0, 1, 1, 1.0, 0.05, GridBagConstraints.BOTH);
        lis.add(estado, this.con);

        JLabel pageRank = new JLabel(" Page Rank");
        pageRank.setBackground(Color.BLACK);
        pageRank.setForeground(Color.GRAY);
        restricciones(5, 0, 1, 1, 1.0, 0.05, GridBagConstraints.BOTH);
        lis.add(pageRank, this.con);

        JLabel flujoMaximo = new JLabel(" Flujo Maximo");
        flujoMaximo.setBackground(Color.BLACK);
        flujoMaximo.setForeground(Color.GRAY);
        restricciones(6, 0, 1, 1, 1.0, 0.05, GridBagConstraints.BOTH);
        lis.add(flujoMaximo, this.con);
        */
        JPanel control = new JPanel();
        control.setLayout(new GridLayout(3,1));
        JButton Boton3 = new JButton("Dar de Baja");
        Boton3.setBackground(Color.GRAY);
        Boton3.setEnabled(true);
        Boton3.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent arg0) {
                /*
                if(lista.getSelectedValue() != null){
                    String valor[] = lista.getSelectedValue().split("      ");

                    for(Centro_Logistico p : grafo.getSucursales()){

                        if(valor[1].equals(p.getId_logistico().toString())){
                            int n = 5;
                            n = JOptionPane.showConfirmDialog(null, "¿Esta seguro de eliminar la sucursal "+p.getNombre()+"?","Eliminar Sucursal", JOptionPane.YES_NO_OPTION);
                            System.out.println("ESTOOO "+n);
                            if(n == 0) grafo.eliminarCentroLogistico(p);
                            break;
                        }
                        
                    }
                }
                else{
                    JOptionPane.showMessageDialog(null, "por favor seleccione un Stock", "Stock no seleccionado", JOptionPane.WARNING_MESSAGE);
                }
                */
                //throw new UnsupportedOperationException("Unimplemented method 'actionPerformed'");
            }
            
        });
        JButton Boton4 = new JButton("Edtar Stock");
        Boton4.setBackground(Color.GRAY);
        //Implementar edicion de Stock
        JButton Boton5 = new JButton("Alta Stock");
        Boton5.setBackground(Color.GRAY);
        //Implementar edicion de Stock
        control.add(Boton3);
        control.add(Boton4);
        control.add(Boton5);
        restricciones(3, 0, 1, 1, 0.10, 1.0, GridBagConstraints.BOTH);
        this.add(control,con);

        return lis;
    }

    private String[] obtenerArreglo(ArrayList<Centro_Logistico> a){
        ArrayList<String> arr = new ArrayList<String>();
        int cont=0;
        for(Centro_Logistico b : a){
            if(b instanceof Puerto){
                arr.add(cont, "      "+b.getId_logistico()+
                          "      "+b.getNombre()+
                          "      "+b.getHorario_apertura()+
                          "      "+b.getHorario_cierre()+
                          "      "+b.getEstado().toString()+
                          "      "+b.getPageRank()+
                          "      Punto de Referencia");
            }
            else{
                arr.add(cont, "      "+b.getId_logistico()+
                          "      "+b.getNombre()+
                          "      "+b.getHorario_apertura()+
                          "      "+b.getHorario_cierre()+
                          "      "+b.getEstado().toString()+
                          "      "+b.getPageRank()+
                          "      "+grafo.flujoMaximo(b)+"  ");
            }
            
            //System.out.println(arr.get(cont));
            cont++;
        }
 
        String[] str = new String[arr.size()];
        return arr.toArray(str);

    }

    private String[] obtenerArreglo(List<Centro_Logistico> a){
        ArrayList<String> arr = new ArrayList<String>();
        int cont=0;
        for(Centro_Logistico b : a){
            if(b instanceof Puerto){
                arr.add(cont, "      "+b.getId_logistico()+
                          "      "+b.getNombre()+
                          "      "+b.getHorario_apertura()+
                          "      "+b.getHorario_cierre()+
                          "      "+b.getEstado().toString()+
                          "      "+b.getPageRank()+
                          "      Punto de Referencia");
            }
            else{
                arr.add(cont, "      "+b.getId_logistico()+
                          "      "+b.getNombre()+
                          "      "+b.getHorario_apertura()+
                          "      "+b.getHorario_cierre()+
                          "      "+b.getEstado().toString()+
                          "      "+b.getPageRank()+
                          "      "+grafo.flujoMaximo(b)+"  ");
            }
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