package modelo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Gestor_Producto {

  public Producto crearProducto(String nombre, String descripcion, Double precio_unitario, Double precio_kg) {
    Producto producto = null;
    Connection conn = null;
    PreparedStatement tabla = null;
    ResultSet rs = null;
    try {
      Class.forName("org.postgresql.Driver");
      conn = DriverManager.getConnection("jdbc:postgresql://localhost/", "tpadmin", "tpadmindied");
      rs = conn.prepareStatement("SELECT id_producto FROM tp.producto ORDER BY id_producto DESC LIMIT 1").executeQuery();
      rs.next();
      String id_siguiente = Integer.toString(Integer.parseInt(rs.getString("id_producto")) + 1);
      tabla = conn.prepareStatement(
        "INSERT INTO tp.producto(id_producto, nombre, descripcion, precio_unit, precio_kg)" + 
        "VALUES ('" + id_siguiente + "','" + nombre + "','" + descripcion + "','" + precio_unitario + "','" + precio_kg + "')"
      );
      tabla.executeUpdate();
      producto = new Producto(id_siguiente, nombre, descripcion, precio_unitario, precio_kg);
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
    return producto;
  }

  public void eliminarProducto(Producto producto) {
    Connection conn = null;
    PreparedStatement tabla = null;
    ResultSet rs = null;
    //cuando se elimina un producto hay que eliminar todos los 'Stock' y las 'Orden_Provision' asociados.
    try {
      Class.forName("org.postgresql.Driver");
      conn = DriverManager.getConnection("jdbc:postgresql://localhost/", "tpadmin", "tpadmindied");
      tabla = conn.prepareStatement(
        "DELETE FROM tp.producto WHERE id_producto = '" + producto.getId_producto() + "'"
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

  public void editarProducto(Producto producto, Producto productoEditado) {
    Connection conn = null;
    PreparedStatement tabla = null;
    ResultSet rs = null;
    try {
      Class.forName("org.postgresql.Driver");
      conn = DriverManager.getConnection("jdbc:postgresql://localhost/", "tpadmin", "tpadmindied");
      tabla = conn.prepareStatement(
        "UPDATE tp.producto SET" + 
          "nombre = '" + productoEditado.getNombre() + "', " +
          "descripcion = '" + productoEditado.getDescripcion() + "', " +
          "precio_unit = " + productoEditado.getPrecioUnitario() + ". " +
          "precio_kg = " + productoEditado.getPrecioKg() + " " +
        "WHERE id_producto = '" + producto.getId_producto() + "'"
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