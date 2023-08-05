import java.util.ArrayList;
import java.util.Date;

import javax.swing.JFrame;

import GUI.Ventana;
import modelo.Cantidad;
import modelo.Centro;
import modelo.Centro_Logistico;
import modelo.ESTADO_RUTA;
import modelo.ESTADO_SUCURSAL;
import modelo.Gestor_Orden_Provision;
import modelo.Gestor_Producto;
import modelo.Gestor_Stock;
import modelo.Grafo;
import modelo.Orden_Provision;
import modelo.Producto;
import modelo.Puerto;
import modelo.Recorrido;
import modelo.Ruta;
import modelo.Stock;
import modelo.Sucursal;
import modelo.UNIDAD;



public class App {
  public static void main(String[] args) throws Exception {
    // Grafo grafo = new Grafo();
    // grafo.cargarSucursales();
    // System.out.println(grafo.getSucursales());
    // Centro_Logistico s = grafo.getSucursales().get(4);
    // grafo.cargarRutas();
    // System.out.println(grafo.getSucursales());
    // Ruta ruta = new Ruta("8", grafo.getSucursales().get(1), grafo.getSucursales().get(3), 50.0, 145, ESTADO_RUTA.OPERATIVA);
    // grafo.cargarRuta(ruta);
    // Centro_Logistico sucursal = new Centro("8", "Centro 2", ESTADO_SUCURSAL.OPERATIVA, "8:00", "15:00");
    // grafo.cargarCentro((Centro)sucursal);
    // grafo.eliminarSucursal((Sucursal)grafo.getSucursales().get(1));
    // System.out.println(grafo.getSucursales());
    // grafo.eliminarRuta(grafo.getRutas().get(5));
    // System.out.println(grafo.getRutas());

    // Gestor_Producto gestorP = new Gestor_Producto();
    // gestorP.crearProducto(new Producto(null, "salamin", "un salamin", 1200.0));
    // for(Producto i : gestorP.getProductos()){
    //   System.out.println("- " + i.getId_producto());
    // }
    // Cantidad p1 = new Cantidad(null, null, 100.0, UNIDAD.UNIDADES, gestorP.getProductos().get(0));
    // Cantidad p2 = new Cantidad(null, null, 50.0, UNIDAD.UNIDADES, gestorP.getProductos().get(1));
    // ArrayList<Cantidad> productos = new ArrayList<>();
    // productos.add(p1);
    // productos.add(p2);
    // Orden_Provision orden = new Orden_Provision(null, new Date(), s, null, 10.0, null, productos, null);

    // Gestor_Orden_Provision GOP = new Gestor_Orden_Provision();
    // GOP.crearOrden_Provision(orden);

    // ArrayList<Orden_Provision> listado = GOP.listarOrdenes();
    // for(Orden_Provision i : listado){
    //   System.out.println("- " + i.getId());
    // }

    // ArrayList<Centro_Logistico> posiblesOrigenes = GOP.listarPosiblesOrigenes(listado.get(listado.size()-1));
    // for(Centro_Logistico i : posiblesOrigenes){
    //   System.out.println("- " + i.getNombre());
    //   for(ArrayList<Centro_Logistico> r : grafo.obtenerCaminos(i, s)){
    //       System.out.println("=====================================");
    //       for(Centro_Logistico ruta : r){
    //         System.out.print(ruta.getNombre()+ " > ");           
    //       }  
    //       System.out.println("");
          
    //   }
    //   System.out.println("=====================================\n");
    // }

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
    // Gestor_Stock gestor = new Gestor_Stock();
    // Stock stock1 = gestor.crearStock(10, 1.0, p1, grafo.getSucursales().get(0));
    // Stock stock2 = gestor.crearStock(10, 1.0, p2, grafo.getSucursales().get(0));
    // gestor.eliminarStock(stock);
    // ry {
      // ArrayList<Producto> productos = new ArrayList<>();
      // productos.add(gestorP.getProductos().get(0));
      // productos.add(gestorP.getProductos().get(1));

      // System.out.println((gestor.buscarStock(productos)));
    // } catch (Exception e) {
    //   System.out.println(e.getMessage());
    // }

    Ventana ventana = new Ventana();
    ventana.inicio();
  }
}
