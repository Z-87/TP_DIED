import java.util.ArrayList;

import javax.swing.JFrame;

import modelo.Centro;
import modelo.Centro_Logistico;
import modelo.ESTADO_RUTA;
import modelo.ESTADO_SUCURSAL;
import modelo.Gestor_Producto;
import modelo.Gestor_Stock;
import modelo.Grafo;
import modelo.Producto;
import modelo.Puerto;
import modelo.Ruta;
import modelo.Stock;
import modelo.Sucursal;



public class App {
  public static void main(String[] args) throws Exception {
    Grafo grafo = new Grafo();
    grafo.cargarSucursales();
    System.out.println(grafo.getSucursales());
    // Sucursal s = (Sucursal)grafo.getSucursales().get(1);
    grafo.cargarRutas();
    // System.out.println(grafo.getRutas());
    // Ruta ruta = new Ruta("8", grafo.getSucursales().get(1), grafo.getSucursales().get(3), 50.0, 145, ESTADO_RUTA.OPERATIVA);
    // grafo.cargarRuta(ruta);
    // Centro_Logistico sucursal = new Centro("8", "Centro 2", ESTADO_SUCURSAL.OPERATIVA, "8:00", "15:00");
    // grafo.cargarCentro((Centro)sucursal);
    // grafo.eliminarSucursal((Sucursal)grafo.getSucursales().get(1));
    // System.out.println(grafo.getSucursales());
    // grafo.eliminarRuta(grafo.getRutas().get(5));
    // System.out.println(grafo.getRutas());

    Gestor_Producto gestorP = new Gestor_Producto();
    Producto p1 = gestorP.crearProducto("salamin", "un salamin", 200.0, 1200.0);
    Producto p2 = gestorP.crearProducto("salamin", "un salamin", 200.0, 1200.0);


    // System.out.println(p.getId_producto());
    // ArrayList<ArrayList<Centro_Logistico>> caminos = grafo.obtenerCaminos(grafo.getSucursales().get(10), grafo.getSucursales().get(0));
    // for(ArrayList<Centro_Logistico> c : caminos) {
    //   System.out.println("=========");
    //   for(Centro_Logistico x : c){
    //     System.out.print(x.getNombre() + " > ");
    //   }
    //   System.out.println("");
    // }
    // System.out.println(caminos.get(0));
    Gestor_Stock gestor = new Gestor_Stock();
    // Stock stock = gestor.crearStock(10, 1.0, p, s);
    // gestor.eliminarStock(stock);
    try {
      ArrayList<Producto> productos = new ArrayList<>();
      productos.add(p1);
      productos.add(p2);

      System.out.println(gestor.buscarStock(productos));
    } catch (Exception e) {
      System.out.println(e.getMessage());
    }
  }
}
