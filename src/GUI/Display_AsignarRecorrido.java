package GUI;

import modelo.Grafo;
import modelo.Orden_Provision;
import modelo.Puerto;
import modelo.Centro_Logistico;
import modelo.Gestor_Orden_Provision;

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

public class Display_AsignarRecorrido extends JPanel{
    JPanel listado;
    JPanel botones;
    GridBagConstraints con;
    Gestor_Orden_Provision gestor;
    Ventana ventana;

    public Display_AsignarRecorrido(Ventana ventana, Orden_Provision orden){
        this.ventana=ventana;
        this.setBackground(Color.BLACK);
        this.setLayout(new GridBagLayout());
        this.con = new GridBagConstraints();
        //Font txbotones = new Font("Texto de Botones", ALLBITS, 30);
        this.gestor = new Gestor_Orden_Provision();
        String arreglo[] = this.obtenerArreglo(gestor.listarPosiblesOrigenes(orden));
        this.listado = cargarLista(arreglo);
        restricciones(1, 0, 1, 1, 0.65, 1.0, GridBagConstraints.BOTH);
        this.add(listado, this.con);
        
        
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
        restricciones(0, 1, 1, 1, 1.0, 0.95, GridBagConstraints.BOTH);
        lis.add(scroll, this.con);
        
        JLabel id = new JLabel(" Recorridos que cumplen con las cantidades de prodcutos solicitados ");
        id.setBackground(Color.BLACK);
        id.setForeground(Color.GRAY);
        restricciones(0, 0, 1, 1, 1.0, 0.05, GridBagConstraints.BOTH);
        lis.add(id, this.con);

        JPanel control = new JPanel();
        control.setLayout(new GridLayout(2,1));
        
        JButton Boton3 = new JButton("Asignar Recorrido");
        Boton3.setBackground(Color.RED);
        
        JButton Boton4 = new JButton("Cancelar");
        Boton4.setBackground(Color.GRAY);
        Boton4.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent arg0) {

                ventana.nuevoPanel(new Display_Consulta_OrdenDeProvision(ventana));

                //throw new UnsupportedOperationException("Unimplemented method 'actionPerformed'");
            }
            
        });
        control.add(Boton3);
        control.add(Boton4);
        restricciones(3, 0, 1, 1, 0.10, 1.0, GridBagConstraints.BOTH);
        this.add(control,con);

        return lis;
    }

    private String[] obtenerArreglo(ArrayList<ArrayList<Centro_Logistico>> a){
        ArrayList<String> arr = new ArrayList<String>();
        int cont=0;
        for(ArrayList<Ruta> b : a){
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
