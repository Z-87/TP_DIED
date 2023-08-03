package modelo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

import exceptions.OrdenesNoEncontradasException;

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
        "INSERT INTO tp.orden_provision(fecha_orden, tiempo_esperado, estado, sucursal_origen, sucursal_destino)" + 
        "VALUES ('" +
          orden.getFechaDeOrden() + "','" + 
          orden.getTiempoEsperadoHoras() + "','" +
          "EN_PROCESO'," +
          "null,'" + 
          orden.getSucursalDestino().getId_logistico() + 
        "' )" +
        "RETURNING id_orden"
      );
      rs = tabla.executeQuery();
      rs.next();

      orden.setId_orden(rs.getInt("id_orden"));
      tabla.close();

      //INSERTs de Cantidad.
      String sql = "INSERT INTO tp.cantidad(id_cantidad, id_producto, id_orden, cantidad_unit, cantidad_kg) VALUES";

      for(Cantidad c : orden.getProductos()) {
        sql += "('" + 
          c.getProducto().getId_producto() + "','" + 
          orden.getId() + "'," +
          c.getCantidadUnidades() + "," +
          c.getCantidadKg() +
        "' ), ";
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
        "SELECT * FROM tp.orden_provision O" + 
        "INNER JOIN tp.centro_logistico Destino ON Destino.id_logistico = sucursal_destino" +
        "LEFT JOIN tp.centro DestinoC ON DestinoC.id_centro = Destino.id_logistico" +
        "LEFT JOIN tp.puerto DestinoP ON DestinoP.id_puerto = Destino.id_logistico" +
        "LEFT JOIN tp.sucursal DestinoS ON DestinoS.id_sucursal = Destino.id_logistico" +
        "INNER JOIN tp.centro_logistico Origen ON Origen.id_logistico = sucursal_origen" +
        "LEFT JOIN tp.centro OrigenC ON OrigenC.id_centro = Origen.id_logistico" +
        "LEFT JOIN tp.puerto OrigenP ON OrigenP.id_puerto = Origen.id_logistico" +
        "LEFT JOIN tp.sucursal OrigenS ON OrigenS.id_sucursal = Origen.id_logistico"
      );
      rs = tabla.executeQuery();

      while(rs.next()) {

        Centro_Logistico sucursalDestino = new Sucursal(
          rs.getInt("DestinoS.id_sucursal"), 
          rs.getString("Destino.nombre"), 
          ESTADO_SUCURSAL.valueOf(rs.getString("Destino.estado")), 
          rs.getString("Destino.horario_apertura"), 
          rs.getString("Destino.horario_apertura")
        );

        if(rs.getString("DestinoC.id_centro") != null) {
          sucursalDestino = (Centro)sucursalDestino;
        } else if(rs.getString("DestinoP.id_puerto") != null) {
          sucursalDestino = (Puerto)sucursalDestino;
        }

        Centro_Logistico sucursalOrigen = null;
        if(rs.getString("Origen.id_logistico") != null) {
          sucursalOrigen = new Sucursal(
            rs.getInt("OrigenS.id_sucursal"), 
            rs.getString("Origen.nombre"), 
            ESTADO_SUCURSAL.valueOf(rs.getString("Origen.estado")), 
            rs.getString("Origen.horario_apertura"), 
            rs.getString("Origen.horario_apertura")
          );
        }
          
        if(rs.getString("OrigenC.id_centro") != null) {
          sucursalOrigen = (Centro)sucursalOrigen;
        } else if(rs.getString("OrigenP.id_puerto") != null) {
          sucursalOrigen = (Puerto)sucursalOrigen;
        }

        ordenes.add(new Orden_Provision(
          rs.getInt("O.id_orden"), 
          rs.getDate("O.fecha_orden"),
          sucursalDestino, 
          sucursalOrigen,
          rs.getDouble("O.tiempo_esperado"),
          ESTADO_ORDEN.valueOf(rs.getString("O.estado")), 
          new ArrayList<Cantidad>(),
          null
        ));
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
    ArrayList<Producto> listaProductos = (ArrayList<Producto>)orden.getProductos().stream().map(c -> c.getProducto()).toList();
    try {
      ArrayList<Stock> stocks = gestor_stock.buscarStock(listaProductos);

    } catch (Exception e) {
      // TODO: handle exception
    }
    return resultado;
  }

}
