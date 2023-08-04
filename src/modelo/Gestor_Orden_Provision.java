package modelo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.swing.JOptionPane;

import exceptions.OrdenesNoEncontradasException;
import exceptions.StockNoEncontradoException;

public class Gestor_Orden_Provision {
  private ArrayList<Orden_Provision> ordenes = new ArrayList<>();

  public Orden_Provision crearOrden_Provision(Orden_Provision orden) {
    Connection conn = null;
    PreparedStatement tabla = null;
    ResultSet rs = null;
    try {
      Class.forName("org.postgresql.Driver");
      conn = DriverManager.getConnection("jdbc:postgresql://localhost/", "tpadmin", "tpadmindied");
      tabla = conn.prepareStatement(
        "INSERT INTO tp.orden_provision(fecha_orden, tiempo_esperado, estado, sucursal_origen, sucursal_destino, id_recorrido)" + 
        "VALUES ('" +
          orden.getFechaDeOrden() + "','" + 
          orden.getTiempoEsperadoHoras() + "','" +
          "EN_PROCESO'," +
          "null,'" + 
          orden.getSucursalDestino().getId_logistico() + "', " +
          "null " +
        ") " +
        "RETURNING id_orden"
      );
      rs = tabla.executeQuery();
      rs.next();

      orden.setId_orden(rs.getInt("id_orden"));
      tabla.close();

      //INSERTs de Cantidad.
      String sql = "INSERT INTO tp.cantidad(id_producto, id_orden, cantidad, unidad) VALUES";

      for(Cantidad c : orden.getProductos()) {
        sql += "(" + 
          c.getProducto().getId_producto() + ", " + 
          orden.getId() + ", " +
          c.getCantidad() + ", '" +
          c.getUnidad() +
        "'), ";
      }
      tabla = conn.prepareStatement(sql.substring(0, sql.length() - 2) + ";");
      tabla.executeUpdate();

      ordenes.add(orden);
    } catch (ClassNotFoundException e) {
      e.printStackTrace();
    } catch (SQLException e) {
      e.printStackTrace();
    } catch (EnumConstantNotPresentException e){
      e.printStackTrace();
    } 
    finally { //Libera los recursos
      if(rs!=null) try { rs.close(); }
      catch (SQLException e) { e.printStackTrace(); }
      if(tabla!=null) try { tabla.close(); }
      catch (SQLException e) {e.printStackTrace(); }
      if(conn!=null) try { conn.close(); }
      catch (SQLException e) { e.printStackTrace(); }
    }
    return orden;
  }

  public ArrayList<Orden_Provision> listarOrdenes() throws OrdenesNoEncontradasException {
    ArrayList<Orden_Provision> ordenes = new ArrayList<>();
    Connection conn = null;
    PreparedStatement tabla = null;
    ResultSet rs = null;
    try {
      Class.forName("org.postgresql.Driver");
      conn = DriverManager.getConnection("jdbc:postgresql://localhost/", "tpadmin", "tpadmindied");
      tabla = conn.prepareStatement(
        "SELECT " + 
          "O.id_orden O_id_orden, O.fecha_orden O_fecha_orden, O.tiempo_esperado O_tiempo_esperado, O.estado O_estado, O.id_recorrido O_id_recorrido, " +
          "DestinoC.id_centro DestinoC_id_centro, DestinoP.id_puerto DestinoP_id_puerto, DestinoS.id_sucursal DestinoS_id_sucursal, " +
          "Destino.id_logistico Destino_id_logistico, Destino.nombre Destino_nombre, Destino.estado Destino_estado, Destino.horario_apertura Destino_horario_apertura, Destino.horario_cierre Destino_horario_cierre, " +
          "OrigenC.id_centro OrigenC_id_centro, OrigenP.id_puerto OrigenP_id_puerto, OrigenS.id_sucursal OrigenS_id_sucursal, " +
          "Origen.id_logistico Origen_id_logistico, Origen.nombre Origen_nombre, Origen.estado Origen_estado, Origen.horario_apertura Origen_horario_apertura, Origen.horario_cierre Origen_horario_cierre " +
        "FROM tp.orden_provision O " + 
          "INNER JOIN tp.centro_logistico Destino ON Destino.id_logistico = O.sucursal_destino " +
          "LEFT JOIN tp.centro DestinoC ON DestinoC.id_centro = Destino.id_logistico " +
          "LEFT JOIN tp.puerto DestinoP ON DestinoP.id_puerto = Destino.id_logistico " +
          "LEFT JOIN tp.sucursal DestinoS ON DestinoS.id_sucursal = Destino.id_logistico " +
          "LEFT JOIN tp.centro_logistico Origen ON Origen.id_logistico = sucursal_origen " +
          "LEFT JOIN tp.centro OrigenC ON OrigenC.id_centro = Origen.id_logistico " +
          "LEFT JOIN tp.puerto OrigenP ON OrigenP.id_puerto = Origen.id_logistico " +
          "LEFT JOIN tp.sucursal OrigenS ON OrigenS.id_sucursal = Origen.id_logistico " +
        "ORDER BY id_orden"
      );
      rs = tabla.executeQuery();

      while(rs.next()) {
        Centro_Logistico sucursalDestino;
        if(rs.getString("DestinoC_id_centro") != null) {
          sucursalDestino = new Centro(
            rs.getInt("DestinoC_id_centro"), 
            rs.getString("Destino_nombre"), 
            ESTADO_SUCURSAL.valueOf(rs.getString("Destino_estado")), 
            rs.getString("Destino_horario_apertura"), 
            rs.getString("Destino_horario_cierre")
          );
        } else if(rs.getString("DestinoP_id_puerto") != null) {
          sucursalDestino = new Puerto(
            rs.getInt("DestinoP_id_puerto"), 
            rs.getString("Destino_nombre"), 
            ESTADO_SUCURSAL.valueOf(rs.getString("Destino_estado")), 
            rs.getString("Destino_horario_apertura"), 
            rs.getString("Destino_horario_cierre")
          );
        } else {
          sucursalDestino = new Sucursal(
            rs.getInt("DestinoS_id_sucursal"), 
            rs.getString("Destino_nombre"), 
            ESTADO_SUCURSAL.valueOf(rs.getString("Destino_estado")), 
            rs.getString("Destino_horario_apertura"), 
            rs.getString("Destino_horario_cierre")
          );
        }

        Centro_Logistico sucursalOrigen = null;
        if(rs.getString("OrigenC_id_centro") != null) {
          sucursalOrigen = new Centro(
            rs.getInt("OrigenC_id_centro"), 
            rs.getString("Origen_nombre"), 
            ESTADO_SUCURSAL.valueOf(rs.getString("Origen_estado")), 
            rs.getString("Origen_horario_apertura"), 
            rs.getString("Origen_horario_cierre")
          );
        } else if(rs.getString("OrigenP_id_puerto") != null) {
          sucursalOrigen = new Puerto(
            rs.getInt("OrigenP_id_puerto"), 
            rs.getString("Origen_nombre"), 
            ESTADO_SUCURSAL.valueOf(rs.getString("Origen_estado")), 
            rs.getString("Origen_horario_apertura"), 
            rs.getString("Origen_horario_cierre")
          );
        } else if(rs.getString("OrigenS_id_sucursal") != null){
          sucursalOrigen = new Sucursal(
            rs.getInt("OrigenS_id_sucursal"), 
            rs.getString("Origen_nombre"), 
            ESTADO_SUCURSAL.valueOf(rs.getString("Origen_estado")), 
            rs.getString("Origen_horario_apertura"), 
            rs.getString("Origen_horario_cierre")
          );
        }

        Orden_Provision o = new Orden_Provision(
          rs.getInt("O_id_orden"), 
          rs.getDate("O_fecha_orden"),
          sucursalDestino, 
          sucursalOrigen,
          rs.getDouble("O_tiempo_esperado"),
          ESTADO_ORDEN.valueOf(rs.getString("O_estado")), 
          new ArrayList<Cantidad>(),
          null //[!] en listarSucursales no vamos a poder ver si tienen recorrido o no, ver si se implementa.
        );
        o.loadProductos();
        ordenes.add(o);
      } 
      if(ordenes.size() < 1){
        throw new OrdenesNoEncontradasException("No se encontraron Ordenes de Provisión.");
      }
    } catch (ClassNotFoundException e) {
      e.printStackTrace();
    } catch (SQLException e) {
      e.printStackTrace();
    } catch (EnumConstantNotPresentException e){
      e.printStackTrace();
    } 
    finally { //Libera los recursos
      if(rs!=null) try { rs.close(); }
      catch (SQLException e) { e.printStackTrace(); }
      if(tabla!=null) try { tabla.close(); }
      catch (SQLException e) {e.printStackTrace(); }
      if(conn!=null) try { conn.close(); }
      catch (SQLException e) { e.printStackTrace(); }
    }
    return ordenes;
  }  

  public ArrayList<Centro_Logistico> listarPosiblesOrigenes(Orden_Provision orden) {
    ArrayList<Centro_Logistico> resultado = new ArrayList<>();
    Gestor_Stock gestor_stock = new Gestor_Stock();
    // ArrayList<Producto> listaProductos = (ArrayList<Producto>)orden.getProductos().stream().map(c -> c.getProducto()).toList();
    // try {
      Grafo grafo = new Grafo();
      // grafo.cargarSucursales();

      for(Centro_Logistico c : grafo.getSucursales()) {
        Boolean stockSuficiente = true;

        for(Cantidad k : orden.getProductos()) {
          try {
            Stock stock = gestor_stock.buscarStock(c, k.getProducto());
            if(stock.getCantidad() < k.getCantidad()){
              stockSuficiente = false;
            }
          } catch (StockNoEncontradoException e) {stockSuficiente = false;}
        }
        if(stockSuficiente){
          resultado.add(c);
        }
      }
    return resultado;
  }
  
  public void seleccionarRecorrido(Orden_Provision orden, Recorrido recorrido) {
    Connection conn = null;
    PreparedStatement tabla = null;
    ResultSet rs = null;
    try {
      Class.forName("org.postgresql.Driver");
      conn = DriverManager.getConnection("jdbc:postgresql://localhost/", "tpadmin", "tpadmindied");
      tabla = conn.prepareStatement(
        "INSERT INTO tp.Recorrido(peso, duracion) VALUES (" + recorrido.getPeso() + ", " + recorrido.getDuracion() + ")" +
        "RETURNING id_recorrido"
      );
      rs = tabla.executeQuery();
      rs.next();
      int ordenRuta = 0;
      for(Ruta r : recorrido.getRutas()){
        tabla.close();
        tabla = conn.prepareStatement(
          "INSERT INTO tp.rutas_recorrido(id_recorrido, id_ruta, orden) VALUES" +
          "(" + rs.getInt("id_recorrido") +  "," + r.getId_ruta() + ", " + ordenRuta + ")"
        );
        tabla.executeUpdate();
        ordenRuta++;
      }
      orden.setRecorrido(recorrido);
    } catch (ClassNotFoundException e) {
      e.printStackTrace();
    } catch (SQLException e) {
      e.printStackTrace();
    } catch (EnumConstantNotPresentException e){
      e.printStackTrace();
    } 
    finally { //Libera los recursos
      if(rs!=null) try { rs.close(); }
      catch (SQLException e) { e.printStackTrace(); }
      if(tabla!=null) try { tabla.close(); }
      catch (SQLException e) {e.printStackTrace(); }
      if(conn!=null) try { conn.close(); }
      catch (SQLException e) { e.printStackTrace(); }
    }
  }

  public List<Orden_Provision> consultarOrden_Provision_EstadoEnProceso(ArrayList<Orden_Provision> ordenes){
    return ordenes.stream().filter(a -> ESTADO_ORDEN.EN_PROCESO == a.getEstado()).collect(Collectors.toList());
  }

  public List<Orden_Provision> consultarOrden_Provision_EstadoPendiente(ArrayList<Orden_Provision> ordenes){
    return ordenes.stream().filter(a -> ESTADO_ORDEN.PENDIENTE == a.getEstado()).collect(Collectors.toList());
  }

  public ArrayList<ArrayList<Ruta>> posiblesCaminos(Orden_Provision orden){
    ArrayList<ArrayList<Ruta>> aux = new ArrayList<ArrayList<Ruta>>();
      Grafo grafo = new Grafo();
      for(Centro_Logistico posible : this.listarPosiblesOrigenes(orden)){
          for(ArrayList<Ruta> aux2 : grafo.obtenerRutas(posible, orden.getSucursalDestino())){
              aux.add(aux2);
          }
      }
    return aux;
  }
}
