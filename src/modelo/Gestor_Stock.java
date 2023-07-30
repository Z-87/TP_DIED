package modelo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Gestor_Stock {
  public Stock crearStock(Integer cantidad_unit, Double cantidad_kg, Producto producto, Centro_Logistico sucursal) {
    Stock stock = null;
    Connection conn = null;
    PreparedStatement tabla = null;
    ResultSet rs = null;
    try {
      Class.forName("org.postgresql.Driver");
      conn = DriverManager.getConnection("jdbc:postgresql://localhost/", "tpadmin", "tpadmindied");
      rs = conn.prepareStatement("SELECT id_stock FROM tp.stock ORDER BY id_stock DESC LIMIT 1").executeQuery();
      rs.next();
      String id_siguiente = Integer.toString(Integer.parseInt(rs.getString("id_stock")) + 1);
      tabla = conn.prepareStatement(
        "INSERT INTO tp.stock(id_stock, id_logistico, id_producto, cantidad_unit, cantidad_kg)" + 
        "VALUES ('" + 
          id_siguiente + "','" + 
          sucursal.getId_logistico() + "','" + 
          producto.getId_producto() + "','" + 
          cantidad_unit + "','" + 
          cantidad_kg + 
        "')"
      );
      tabla.executeUpdate();
      stock = new Stock(id_siguiente, cantidad_unit, cantidad_kg, producto, sucursal);
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
    return stock;
  }

  public void eliminarStock(Stock stock) {
    Connection conn = null;
    PreparedStatement tabla = null;
    ResultSet rs = null;
    try {
      Class.forName("org.postgresql.Driver");
      conn = DriverManager.getConnection("jdbc:postgresql://localhost/", "tpadmin", "tpadmindied");
      tabla = conn.prepareStatement(
        "DELETE FROM tp.stock WHERE id_stock = '" + stock.getId_stock() + "'"
      );
      tabla.executeUpdate();
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
}
