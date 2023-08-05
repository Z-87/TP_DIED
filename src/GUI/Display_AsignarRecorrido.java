package GUI;

import modelo.Grafo;
import modelo.Orden_Provision;
import modelo.Recorrido;
import modelo.Ruta;
import modelo.Gestor_Orden_Provision;

import java.awt.Color;
//import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

public class Display_AsignarRecorrido extends JPanel{
    private JPanel listado;
    //private JPanel botones;
    private GridBagConstraints con;
    private Gestor_Orden_Provision gestor;
    private Grafo grafo;
    private Ventana ventana;
    private ArrayList<Recorrido> recorridos;
    public Display_AsignarRecorrido(Ventana ventana, Orden_Provision orden){
        this.ventana=ventana;
        this.setBackground(Color.BLACK);
        this.setLayout(new GridBagLayout());
        this.con = new GridBagConstraints();
        //Font txbotones = new Font("Texto de Botones", ALLBITS, 30);
        this.gestor = new Gestor_Orden_Provision();
        this.grafo = new Grafo();
        this.recorridos = gestor.posiblesCaminos(orden);
        String arreglo[] = this.obtenerArreglo(this.recorridos);
        this.listado = cargarLista(arreglo, orden);
        restricciones(1, 0, 1, 1, 0.65, 1.0, GridBagConstraints.BOTH);
        this.add(listado, this.con);
        
        
    }

    private JPanel cargarLista(String arreglo[], Orden_Provision orden){
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
        Boton3.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent arg0) {
                
                if(lista.getSelectedValue() != null){
                    int n = 5;
                    n = JOptionPane.showConfirmDialog(null, "¿Confirma la seleccion?","Asignar Recorrido", JOptionPane.YES_NO_OPTION);
                    if(n == 0){
                        int valor = lista.getSelectedIndex();
                        gestor.seleccionarRecorrido(orden, recorridos.get(valor));
                        JOptionPane.showMessageDialog(null, "La Asignacion fue exitosa!", "Exito en la operacion", JOptionPane.INFORMATION_MESSAGE);
                    }
                    
                    ventana.nuevoPanel(new Display_Consulta_OrdenDeProvision(ventana));

                }
                else{
                    JOptionPane.showMessageDialog(null, "Por favor seleccione una Ruta", "Ruta no seleccionada", JOptionPane.WARNING_MESSAGE);
                }
                
                // TODO Auto-generated method stub
                throw new UnsupportedOperationException("Unimplemented method 'actionPerformed'");
            }
            
        });
        
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

    private String[] obtenerArreglo(ArrayList<Recorrido> a){
        ArrayList<String> arr1 = new ArrayList<String>();
        int cont=0;
        for(Recorrido b : a){
            String arr2 = new String();
            for(Ruta r : b.getRutas()){
                 arr2=arr2+" --> "+r.getSucursal_Origen().getNombre();
            }
            arr2=arr2+" --> "+b.getRutas().get(b.getRutas().size()-1).getSucursal_Destino().getNombre()+
            "      Flujo Maximo del Recorrido: "+b.getPeso()+
            "      Tiempo Estimado: "+b.getDuracion();
            arr1.add(cont, arr2);
            cont++;
        }
 
        String[] str = new String[arr1.size()];
        return arr1.toArray(str);

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
