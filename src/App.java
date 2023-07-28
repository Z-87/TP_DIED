import javax.swing.JFrame;

import modelo.Grafo;
import modelo.Ruta;



public class App {
    public static void main(String[] args) throws Exception {
        Grafo grafo = new Grafo();
        grafo.cargarSucursales();
        System.out.println(grafo.getSucursales());
        grafo.cargarRutas();
        System.out.println(grafo.getRutas());
    }
}
