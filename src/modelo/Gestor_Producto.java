package modelo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import exceptions.ProductoNoEliminable;

public class Gestor_Producto {
  private ArrayList<Producto> productos = new ArrayList<>();

  public void crearProducto(Producto producto) {
    Connection conn = null;
    PreparedStatement tabla = null;
    ResultSet rs = null;
    try {
      Class.forName("org.postgresql.Driver");
      conn = DriverManager.getConnection("jdbc:postgresql://localhost/", "tpadmin", "tpadmindied");
      tabla = conn.prepareStatement(
        "INSERT INTO tp.producto(nombre, descripcion, precio)" + 
        "VALUES ('" + producto.getNombre() + "','" + producto.getDescripcion() + "','" + producto.getPrecio() + "')" +
        "RETURNING id_producto"
      );
      rs = tabla.executeQuery();
      rs.next();

      producto.setId_producto(rs.getInt("id_producto"));
      productos.add(producto);
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

  public void eliminarProducto(Producto producto) throws ProductoNoEliminable {
    Connection conn = null;
    PreparedStatement tabla = null;
    ResultSet rs = null;
    //[!] cuando se elimina un producto hay que eliminar todos los 'Stock' y las 'Orden_Provision' asociados.
    try {
      Class.forName("org.postgresql.Driver");
      conn = DriverManager.getConnection("jdbc:postgresql://localhost/", "tpadmin", "tpadmindied");
      tabla = conn.prepareStatement(
        "DELETE FROM tp.producto WHERE id_producto = '" + producto.getId_producto() + "'"
      );
      tabla.executeUpdate();
      productos.remove(producto);
    } catch (ClassNotFoundException e) {
      e.printStackTrace();
    } catch (SQLException e) {
      e.printStackTrace();
      throw new ProductoNoEliminable("El producto está asociado a stocks vigentes que debe eliminar previamente.");
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
          "precio = " + productoEditado.getPrecio() +
        "WHERE id_producto = '" + producto.getId_producto() + "'"
      );
      tabla.executeUpdate();
      productos.set(productos.indexOf(producto), productoEditado);
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

  public Gestor_Producto() {
    Connection conn = null;
    PreparedStatement tabla = null;
    ResultSet rs = null;
    try {
      Class.forName("org.postgresql.Driver");
      conn = DriverManager.getConnection("jdbc:postgresql://localhost/", "tpadmin", "tpadmindied");
      tabla = conn.prepareStatement(
        "SELECT * FROM tp.producto"
      );
      rs = tabla.executeQuery();

      while(rs.next()) {
        productos.add(new Producto(
          rs.getInt("id_producto"), 
          rs.getString("nombre"), 
          rs.getString("descripcion"), 
          rs.getDouble("precio")
        ));
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
  }
  public ArrayList<Producto> getProductos() {    
    return productos;
  }

  public Producto consultarProducto_Id(Integer id){
        return this.productos.stream().filter(a -> id.equals(a.getId_producto())).collect(Collectors.toList()).get(0);
  }

  public List<Producto> filtrarProducto_Id(List<Producto> lista,Integer id){
        return lista.stream().filter(a -> id.equals(a.getId_producto())).collect(Collectors.toList());
  }
  public List<Producto> filtraProducto_Nombre(List<Producto> lista,String nombre){
        return lista.stream().filter(a -> nombre.equals(a.getNombre())).collect(Collectors.toList());
  }
  public List<Producto> filtrarProducto_PrecioExacto(List<Producto> lista,Double precio){
        return lista.stream().filter(a -> precio.equals(a.getPrecio())).collect(Collectors.toList());
  }
  public List<Producto> filtrarProducto_PrecioMayorIgual(List<Producto> lista,Double precio){
        return lista.stream().filter(a -> a.getPrecio() >= precio).collect(Collectors.toList());
  }
  public List<Producto> filtrarProducto_PrecioMenor(List<Producto> lista,Double precio){
        return lista.stream().filter(a -> a.getPrecio() < precio).collect(Collectors.toList());
  }
}