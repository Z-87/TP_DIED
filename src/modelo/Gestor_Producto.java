package modelo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Gestor_Producto {

  public Producto crearProducto(String nombre, String descripcion, Double precio_unitario, Double precio_kg) {
    Producto producto = new Producto(nombre, descripcion, precio_unitario, precio_kg);

    Connection conn = null;
    PreparedStatement tabla = null;
    ResultSet rs = null;
    try {
      Class.forName("org.postgresql.Driver");
      conn = DriverManager.getConnection("jdbc:postgresql://localhost/", "tpadmin", "tpadmindied");
      tabla = conn.prepareStatement("INSERT INTO tp.Centro_Logistico(id_producto, nombre, descripcion, precio_unitario, precio_kg) VALUES ('"+a.getId_logistico()+"','"+a.getNombre()+"','"+((a.getEstado()).toString())+"','"+a.getHorario_apertura()+"','"+a.getHorario_cierre()+"')");
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
