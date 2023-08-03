package GUI;

import modelo.Grafo;
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
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

public class Display_Consulta_Sucursales extends JPanel{
    JPanel listado;
    JPanel botones;

    public Display_Consulta_Sucursales(Ventana ventana){
        this.setBackground(Color.BLACK);
        this.setLayout(new GridBagLayout());
        GridBagConstraints con = new GridBagConstraints();
        //Font txbotones = new Font("Texto de Botones", ALLBITS, 30);

        Grafo g = new Grafo();
        g.cargarSucursales();
        String arreglo[] = this.obtenerArreglo(g.getSucursales());
        this.listado = cargarLista(arreglo);
        con.fill = GridBagConstraints.BOTH;
        con.weightx = 0.75;
        con.weighty = 1.0;
        con.gridx = 1;
        con.gridy = 0;
        con.gridwidth = 1;
        con.gridheight = 1;
        this.add(listado, con);

        con.fill = GridBagConstraints.BOTH;
        con.weightx = 1.0;
        con.weighty = 1.0;
        con.gridx = 0;
        con.gridy = 0;
        con.gridwidth = 1;
        con.gridheight = 1;

        this.botones = new JPanel();
        botones.setBackground(Color.BLACK);
        botones.setLayout(new GridLayout(3,1));
        JPanel pan1 = new JPanel();
        pan1.setBackground(Color.BLACK);
        pan1.setLayout(new GridLayout(1,3));
        JTextField campo1 = new JTextField("Ingrese nombre a buscar");
        campo1.setBackground(Color.BLACK);
        campo1.setForeground(Color.GRAY);
        campo1.setEnabled(false);
        JButton Boton1 = new JButton("Filtrar");
        Boton1.setBackground(Color.GRAY);
        Boton1.setEnabled(false);
        Boton1.addActionListener(new ActionListener() {
            
            public void actionPerformed(ActionEvent arg0) {
                String cam = campo1.getText();
                List<Centro_Logistico> a = g.consultarScursales_Nombre(cam);

                reCargarLista(obtenerArreglo(a));
                // TODO Auto-generated method stub
                throw new UnsupportedOperationException("Unimplemented method 'actionPerformed'");
            }
            
        });
        JRadioButton radBoton1 = new JRadioButton();
        radBoton1.setBackground(Color.BLACK);
        radBoton1.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent arg0) {
                if(radBoton1.isSelected()){
                    campo1.setEnabled(true);
                    Boton1.setEnabled(true);
                }
                else{
                    campo1.setEnabled(false);
                    Boton1.setEnabled(false);
                }
                // TODO Auto-generated method stub
                throw new UnsupportedOperationException("Unimplemented method 'actionPerformed'");
            }
            
        });
        pan1.add(radBoton1);
        pan1.add(campo1);
        pan1.add(Boton1);
        JButton Boton2 = new JButton("Alta Ruta");
        Boton2.setBackground(Color.GRAY);
        JButton Boton3 = new JButton("Volver");
        Boton3.setBackground(Color.GRAY);
        Boton3.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent arg0) {

                ventana.nuevoPanel(new Display_Principal(ventana));

                throw new UnsupportedOperationException("Unimplemented method 'actionPerformed'");
            }
            
        });
        con.fill = GridBagConstraints.BOTH;
        con.weightx = 0.25;
        con.weighty = 1.0;
        con.gridx = 0;
        con.gridy = 0;
        con.gridwidth = 1;
        con.gridheight = 1;
        botones.add(pan1, con);
        con.fill = GridBagConstraints.BOTH;
        con.weightx = 0.25;
        con.weighty = 1.0;
        con.gridx = 0;
        con.gridy = 1;
        con.gridwidth = 1;
        con.gridheight = 1;
        botones.add(Boton2);
        con.fill = GridBagConstraints.BOTH;
        con.weightx = 0.25;
        con.weighty = 1.0;
        con.gridx = 0;
        con.gridy = 2;
        con.gridwidth = 1;
        con.gridheight = 1;
        botones.add(Boton3);
        con.fill = GridBagConstraints.BOTH;
        con.weightx = 0.25;
        con.weighty = 1.0;
        con.gridx = 0;
        con.gridy = 0;
        con.gridwidth = 1;
        con.gridheight = 1;
        this.add(botones, con);
    }

    private void reCargarLista(String arreglo[]){
        GridBagConstraints con = new GridBagConstraints();
        con.fill = GridBagConstraints.BOTH;
        con.weightx = 1;
        con.weighty = 1;
        con.gridx = 0;
        con.gridy = 0;
        con.gridwidth = 1;
        con.gridheight = 1;

        this.listado.removeAll();
        this.listado.add(cargarLista(arreglo), con);
        this.listado.repaint();
        this.listado.validate();
    }

    private JPanel cargarLista(String arreglo[]){
        GridBagConstraints con = new GridBagConstraints();
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
        con.fill = GridBagConstraints.BOTH;
        con.weightx = 1.0;
        con.weighty = 0.95;
        con.gridx = 0;
        con.gridy = 1;
        con.gridwidth = 7;
        con.gridheight = 1;
        lis.add(scroll, con);
        
        JLabel id = new JLabel(" Id ");
        id.setBackground(Color.BLACK);
        id.setForeground(Color.GRAY);
        con.fill = GridBagConstraints.BOTH;
        con.weightx = 1.0;
        con.weighty = 0.05;
        con.gridx = 0;
        con.gridy = 0;
        con.gridwidth = 1;
        con.gridheight = 1;
        lis.add(id, con);

        JLabel nombre = new JLabel(" Nombre ");
        nombre.setBackground(Color.BLACK);
        nombre.setForeground(Color.GRAY);
        con.fill = GridBagConstraints.BOTH;
        con.weightx = 1.0;
        con.weighty = 0.05;
        con.gridx = 1;
        con.gridy = 0;
        con.gridwidth = 1;
        con.gridheight = 1;
        lis.add(nombre, con);

        JLabel hapertura = new JLabel(" Horario Apertura ");
        hapertura.setBackground(Color.BLACK);
        hapertura.setForeground(Color.GRAY);
        con.fill = GridBagConstraints.BOTH;
        con.weightx = 1.0;
        con.weighty = 0.05;
        con.gridx = 2;
        con.gridy = 0;
        con.gridwidth = 1;
        con.gridheight = 1;
        lis.add(hapertura, con);

        JLabel hcierre = new JLabel(" Horario Cierre ");
        hcierre.setBackground(Color.BLACK);
        hcierre.setForeground(Color.GRAY);
        con.fill = GridBagConstraints.BOTH;
        con.weightx = 1.0;
        con.weighty = 0.05;
        con.gridx = 3;
        con.gridy = 0;
        con.gridwidth = 1;
        con.gridheight = 1;
        lis.add(hcierre, con);

        JLabel estado = new JLabel(" Estado ");
        estado.setBackground(Color.BLACK);
        estado.setForeground(Color.GRAY);
        con.fill = GridBagConstraints.BOTH;
        con.weightx = 1.0;
        con.weighty = 0.05;
        con.gridx = 4;
        con.gridy = 0;
        con.gridwidth = 1;
        con.gridheight = 1;
        lis.add(estado, con);

        JLabel pageRank = new JLabel(" Page Rank");
        pageRank.setBackground(Color.BLACK);
        pageRank.setForeground(Color.GRAY);
        con.fill = GridBagConstraints.BOTH;
        con.weightx = 1.0;
        con.weighty = 0.05;
        con.gridx = 5;
        con.gridy = 0;
        con.gridwidth = 1;
        con.gridheight = 1;
        lis.add(pageRank, con);

        JLabel flujoMaximo = new JLabel(" Flujo Maximo");
        flujoMaximo.setBackground(Color.BLACK);
        flujoMaximo.setForeground(Color.GRAY);
        con.fill = GridBagConstraints.BOTH;
        con.weightx = 1.0;
        con.weighty = 0.05;
        con.gridx = 6;
        con.gridy = 0;
        con.gridwidth = 1;
        con.gridheight = 1;
        lis.add(flujoMaximo, con);

        con.fill = GridBagConstraints.BOTH;
        con.weightx = 0.75;
        con.weighty = 1.0;
        con.gridx = 1;
        con.gridy = 0;
        con.gridwidth = 1;
        con.gridheight = 1;

        //this.add(listado, con);
        return lis;
    }

    private String[] obtenerArreglo(ArrayList<Centro_Logistico> a){
        ArrayList<String> arr = new ArrayList<String>();
        int cont=0;
        for(Centro_Logistico b : a){
            
            arr.add(cont, "  "+b.getId_logistico()+
                          "  "+b.getNombre()+
                          "  "+b.getHorario_apertura()+
                          "  "+b.getHorario_cierre()+
                          "  "+b.getEstado().toString()+
                          "  "+b.getPageRank()+"  ");
            System.out.println(arr.get(cont));
            cont++;
        }
 
        String[] str = new String[arr.size()];
        return arr.toArray(str);

    }

    private String[] obtenerArreglo(List<Centro_Logistico> a){
        ArrayList<String> arr = new ArrayList<String>();
        int cont=0;
        for(Centro_Logistico b : a){
            
            arr.add(cont, "  "+b.getId_logistico()+
                          "  "+b.getNombre()+
                          "  "+b.getHorario_apertura()+
                          "  "+b.getHorario_cierre()+
                          "  "+b.getEstado().toString()+
                          "  "+b.getPageRank()+"  ");
            System.out.println(arr.get(cont));
            cont++;
        }
 
        String[] str = new String[arr.size()];
        return arr.toArray(str);

    }

}
