package GUI;

import modelo.Orden_Provision;
import modelo.Gestor_Orden_Provision;

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

import exceptions.OrdenesNoEncontradasException;

public class Display_Consulta_OrdenDeProvision extends JPanel{
    private JPanel listado;
    private JPanel botones;
    private GridBagConstraints con;
    private Gestor_Orden_Provision gestor;
    private ArrayList<Orden_Provision> ordenes;
    private Ventana ventana;

    public Display_Consulta_OrdenDeProvision(Ventana ventana){
        this.ventana=ventana;
        this.ordenes = new ArrayList<Orden_Provision>();
        this.setBackground(Color.BLACK);
        this.setLayout(new GridBagLayout());
        this.con = new GridBagConstraints();
        //Font txbotones = new Font("Texto de Botones", ALLBITS, 30);

        this.gestor = new Gestor_Orden_Provision();
        //g.cargarSucursales();
        try{
            ordenes = gestor.listarOrdenes();
            String arreglo[] = this.obtenerArreglo(ordenes);
            this.listado = cargarLista(arreglo);
        } catch(OrdenesNoEncontradasException e){
            JOptionPane.showMessageDialog(null, e.getMessage(), "Error al Obtener Ordenes", JOptionPane.ERROR_MESSAGE);
        }
        restricciones(1, 0, 1, 1, 0.65, 1.0, GridBagConstraints.BOTH);
        this.add(listado, this.con);
        
        this.botones = new JPanel();
        botones.setBackground(Color.BLACK);
        botones.setLayout(new GridLayout(3,1));
        
        JPanel pan5 = new JPanel();
        pan5.setBackground(Color.BLACK);
        pan5.setLayout(new GridLayout(3,1));
        JLabel texto5 = new JLabel("Estado de Orden");
        texto5.setBackground(Color.BLACK);
        texto5.setForeground(Color.GRAY);
        JCheckBox check1 = new JCheckBox("Pendientes");
        check1.setBackground(Color.BLACK);
        check1.setForeground(Color.GRAY);
        JCheckBox check2 = new JCheckBox("En Progreso");
        check2.setBackground(Color.BLACK);
        check2.setForeground(Color.GRAY);
        check1.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent arg0) {
                
                if(check1.isSelected()) check2.setSelected(false);

                //throw new UnsupportedOperationException("Unimplemented method 'actionPerformed'");
            }
            
        });
        check2.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent arg0) {
                
                if(check2.isSelected()) check1.setSelected(false);

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
                
                if(check1.isSelected()){
                    reCargarLista(obtenerArreglo(gestor.consultarOrden_Provision_EstadoPendiente(ordenes)));
                }
                else if(check2.isSelected()){
                    reCargarLista(obtenerArreglo(gestor.consultarOrden_Provision_EstadoEnProceso(ordenes)));
                }
                else{
                    reCargarLista(obtenerArreglo(ordenes));
                }
                
                //throw new UnsupportedOperationException("Unimplemented method 'actionPerformed'");
            }
            
        });

        JButton Boton2 = new JButton("Volver");
        Boton2.setBackground(Color.GRAY);
        Boton2.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent arg0) {

                ventana.nuevoPanel(new Display_Sucursales(ventana));

                //throw new UnsupportedOperationException("Unimplemented method 'actionPerformed'");
            }
            
        });

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
        restricciones(0, 1, 6, 1, 1.0, 0.95, GridBagConstraints.BOTH);
        lis.add(scroll, this.con);
        
        JLabel id = new JLabel(" Id ");
        id.setBackground(Color.BLACK);
        id.setForeground(Color.GRAY);
        restricciones(0, 0, 1, 1, 1.0, 0.05, GridBagConstraints.BOTH);
        lis.add(id, this.con);

        JLabel nombre = new JLabel(" Fecha de Emision ");
        nombre.setBackground(Color.BLACK);
        nombre.setForeground(Color.GRAY);
        restricciones(1, 0, 1, 1, 1.0, 0.05, GridBagConstraints.BOTH);
        lis.add(nombre, this.con);

        JLabel hapertura = new JLabel(" Tiempo Estimado ");
        hapertura.setBackground(Color.BLACK);
        hapertura.setForeground(Color.GRAY);
        restricciones(2, 0, 1, 1, 1.0, 0.05, GridBagConstraints.BOTH);
        lis.add(hapertura, this.con);
        
        JLabel hcierre = new JLabel(" Sucursal Origen ");
        hcierre.setBackground(Color.BLACK);
        hcierre.setForeground(Color.GRAY);
        restricciones(3, 0, 1, 1, 1.0, 0.05, GridBagConstraints.BOTH);
        lis.add(hcierre, this.con);

        JLabel estado = new JLabel(" Sucursal Destino ");
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
        control.setLayout(new GridLayout(1,1));
        
        JButton Boton4 = new JButton("Asignar Recorrido");
        Boton4.setBackground(Color.RED);
        Boton4.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent arg0) {

                if(lista.getSelectedValue() != null){
                    String valor[] = lista.getSelectedValue().split("      ");

                    for(Orden_Provision p : ordenes){

                        if(valor[1].equals(p.getId().toString())){
                            ventana.nuevoPanel(new Display_AsignarRecorrido(ventana, p));
                        }
                        
                    }
                }
                else{
                    JOptionPane.showMessageDialog(null, "Por favor seleccione una Orden de Provision", "Orden de Provision no seleccionada", JOptionPane.WARNING_MESSAGE);
                }
                
                //throw new UnsupportedOperationException("Unimplemented method 'actionPerformed'");
            }
            
        });
        control.add(Boton4);
        restricciones(3, 0, 1, 1, 0.10, 1.0, GridBagConstraints.BOTH);
        this.add(control,con);

        return lis;
    }

    private String[] obtenerArreglo(ArrayList<Orden_Provision> a){
        ArrayList<String> arr = new ArrayList<String>();
        int cont=0;
        for(Orden_Provision b : a){
            if(b.getSucursalOrigen() == null /*|| b.getRecorrido() == null*/){
                arr.add(cont, "      "+b.getId()+
                      "      "+b.getFechaDeOrden()+
                      "      "+b.getTiempoEsperadoHoras()+
                      "      null"+
                      "      "+b.getSucursalDestino().getId_logistico()+
                      "      "+b.getEstado().toString()+"  ");
            }
            else{
                arr.add(cont, "      "+b.getId()+
                      "      "+b.getFechaDeOrden()+
                      "      "+b.getTiempoEsperadoHoras()+
                      "      "+b.getSucursalOrigen().getId_logistico()+
                      "      "+b.getSucursalDestino().getId_logistico()+
                      "      "+b.getEstado().toString()+"  ");
            }
            
            
            //System.out.println(arr.get(cont));
            cont++;
        }
 
        String[] str = new String[arr.size()];
        return arr.toArray(str);

    }

    private String[] obtenerArreglo(List<Orden_Provision> a){
        ArrayList<String> arr = new ArrayList<String>();
        int cont=0;
        for(Orden_Provision b : a){
            if(b.getSucursalOrigen() == null || b.getRecorrido() == null){
                arr.add(cont, "      "+b.getId()+
                      "      "+b.getFechaDeOrden()+
                      "      "+b.getTiempoEsperadoHoras()+
                      "      null"+
                      "      "+b.getSucursalDestino().getId_logistico()+
                      "      "+b.getEstado().toString()+"  ");
            }
            else{
                arr.add(cont, "      "+b.getId()+
                      "      "+b.getFechaDeOrden()+
                      "      "+b.getTiempoEsperadoHoras()+
                      "      "+b.getSucursalOrigen().getId_logistico()+
                      "      "+b.getSucursalDestino().getId_logistico()+
                      "      "+b.getEstado().toString()+"  ");
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