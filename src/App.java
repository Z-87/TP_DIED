import javax.swing.JFrame;

import modelo.ESTADO_RUTA;
import modelo.Grafo;
import modelo.Ruta;



public class App {
    public static void main(String[] args) throws Exception {
        Grafo grafo = new Grafo();
        grafo.cargarSucursales();
        System.out.println(grafo.getSucursales());
        grafo.cargarRutas();
        System.out.println(grafo.getRutas());
        // Ruta ruta = new Ruta("8", grafo.getSucursales().get(1), grafo.getSucursales().get(3), 50.0, 145, ESTADO_RUTA.OPERATIVA);
        // grafo.cargarRuta(ruta);
        grafo.eliminarRuta(grafo.getRutas().get(5));
        System.out.println(grafo.getRutas());
    }
}
