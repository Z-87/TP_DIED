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

import GUI.Display_Consulta_Sucursales;
import exceptions.StockNoEncontradoException;

public class Gestor_Stock {
  public Stock crearStock(Stock stock) {
    Connection conn = null;
    PreparedStatement tabla = null;
    ResultSet rs = null;
    try {
      Class.forName("org.postgresql.Driver");
      conn = DriverManager.getConnection("jdbc:postgresql://localhost/", "tpadmin", "tpadmindied");
      tabla = conn.prepareStatement(
        "INSERT INTO tp.stock(id_logistico, id_producto, cantidad, unidad)" + 
        "VALUES ('" +
          stock.getSucursal().getId_logistico() + "','" + 
          stock.getProducto().getId_producto() + "'," + 
          stock.getCantidad() + ", '" + 
          stock.getUnidad() +
        "')" + 
        "RETURNING id_stock"
      );
      rs = tabla.executeQuery();
      stock = new Stock(rs.getInt("id_stock"), stock.getCantidad(), stock.getUnidad(), stock.getProducto(), stock.getSucursal());
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
        "DELETE FROM tp.stock WHERE id_stock = " + stock.getId_stock()
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

  public void editarStock(Stock stock, Stock stockEditado) {
    Connection conn = null;
    PreparedStatement tabla = null;
    ResultSet rs = null;
    try {
      Class.forName("org.postgresql.Driver");
      conn = DriverManager.getConnection("jdbc:postgresql://localhost/", "tpadmin", "tpadmindied");
      tabla = conn.prepareStatement(
        "UPDATE tp.stock SET" + 
          "sucursal = '" + stockEditado.getSucursal().getId_logistico() + "', " +
          "producto = '" + stockEditado.getProducto().getId_producto() + "', " +
          "cantidad = " + stockEditado.getCantidad() + ". " +
          "unidad = '" + stockEditado.getUnidad() + "' " +
        "WHERE id_stock = " + stock.getId_stock()
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

  public Stock buscarStock(Centro_Logistico sucursal, Producto producto) throws StockNoEncontradoException {
    Stock resultado = null;
    Connection conn = null;
    PreparedStatement tabla = null;
    ResultSet rs = null;
    try {
      Class.forName("org.postgresql.Driver");
      conn = DriverManager.getConnection("jdbc:postgresql://localhost/", "tpadmin", "tpadmindied");
      tabla = conn.prepareStatement(
        "SELECT * FROM tp.stock " +
        "WHERE id_logistico = " + sucursal.getId_logistico() + " AND id_producto = " + producto.getId_producto()
      );
      rs = tabla.executeQuery();

      if(rs.next()) {
        resultado = new Stock(
          rs.getInt("id_stock"), 
          rs.getDouble("cantidad"), 
          UNIDAD.valueOf(rs.getString("unidad")),
          producto, sucursal
        );
      } else {
        throw new StockNoEncontradoException("No hay stock de " + producto.getNombre() + " en la sucursal " + sucursal.getNombre());
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

    return resultado;
  }
  public ArrayList<Stock> buscarStock(Centro_Logistico sucursal) throws StockNoEncontradoException {
    ArrayList<Stock> resultado = new ArrayList<>();
    Connection conn = null;
    PreparedStatement tabla = null;
    ResultSet rs = null;
    try {
      Class.forName("org.postgresql.Driver");
      conn = DriverManager.getConnection("jdbc:postgresql://localhost/", "tpadmin", "tpadmindied");
      tabla = conn.prepareStatement(
        "SELECT * FROM tp.stock INNER JOIN tp.producto USING(id_producto)" +
        "WHERE id_logistico = " + sucursal.getId_logistico() + ""
      );
      rs = tabla.executeQuery();

      while(rs.next()) {
        resultado.add(new Stock(
          rs.getInt("id_stock"), 
          rs.getDouble("cantidad"), 
          UNIDAD.valueOf(rs.getString("unidad")),
          new Producto(rs.getInt("id_producto"), rs.getString("nombre"), rs.getString("descripcion"), rs.getDouble("precio")), 
          sucursal
        ));
      } 
      if(resultado.size() < 1){
        throw new StockNoEncontradoException("La sucursal " + sucursal.getNombre() + "no cuenta con ningún producto en stock.");
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

    return resultado;
  }

  public ArrayList<Stock> buscarStock(Producto producto) throws StockNoEncontradoException {
    ArrayList<Stock> resultado = new ArrayList<>();
    Connection conn = null;
    PreparedStatement tabla = null;
    ResultSet rs = null;
    try {
      Class.forName("org.postgresql.Driver");
      conn = DriverManager.getConnection("jdbc:postgresql://localhost/", "tpadmin", "tpadmindied");
      tabla = conn.prepareStatement(
        "SELECT * FROM tp.stock" + 
        "INNER JOIN tp.centro_logistico USING(id_logistico)" +
        "LEFT JOIN tp.centro ON id_centro = id_logistico" +
        "LEFT JOIN tp.puerto ON id_puerto = id_logistico" +
        "LEFT JOIN tp.sucursal ON id_sucursal = id_logistico" +
        "WHERE id_producto = '" + producto.getId_producto() + "'"
      );
      rs = tabla.executeQuery();

      while(rs.next()) {
        Centro_Logistico sucursal;
        if(rs.getString("id_centro") != null) {
          sucursal = new Centro(
            rs.getInt("id_centro"), 
            rs.getString("nombre"), 
            ESTADO_SUCURSAL.valueOf(rs.getString("estado")), 
            rs.getString("horario_apertura"), 
            rs.getString("horario_apertura")
          );
        } else if(rs.getString("id_puerto") != null) {
          sucursal = new Puerto(
            rs.getInt("id_puerto"), 
            rs.getString("nombre"), 
            ESTADO_SUCURSAL.valueOf(rs.getString("estado")), 
            rs.getString("horario_apertura"), 
            rs.getString("horario_apertura")
          );
        } else {
          sucursal = new Sucursal(
            rs.getInt("id_sucursal"), 
            rs.getString("nombre"), 
            ESTADO_SUCURSAL.valueOf(rs.getString("estado")), 
            rs.getString("horario_apertura"), 
            rs.getString("horario_apertura")
          );
        }
        
        resultado.add(new Stock(
          rs.getInt("id_stock"),
          rs.getDouble("cantidad"), 
          UNIDAD.valueOf(rs.getString("unidad")),
          producto,
          sucursal
        ));
      } 
      if(resultado.size() < 1){
        throw new StockNoEncontradoException("Ninguna sucursal cuenta con " + producto.getNombre() + " en stock.");
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

    return resultado;
  }

  public ArrayList<Stock> buscarStock(ArrayList<Producto> productos) throws StockNoEncontradoException {
    ArrayList<Stock> resultado = new ArrayList<>();  
    for(Producto p : productos) {
      resultado.addAll(buscarStock(p));
    }

    return resultado;
  }

  public List<Stock> filtrarStock_Producto(List<Stock> sto,String prod, Centro_Logistico cent){  
    List<Stock> arr = null;
    try{
        arr = this.buscarStock(cent).stream().filter(a -> prod.equals(a.getProducto().getNombre())).collect(Collectors.toList());
    }catch(StockNoEncontradoException e){
        JOptionPane.showMessageDialog(null, e.getMessage(), "No se encontraron Stocks en la Sucursal", JOptionPane.ERROR_MESSAGE);
    }
    return arr;
 }

 public List<Stock> filtrarStock_CantidadExacta(List<Stock> sto,Double cant, Centro_Logistico cent){  
    List<Stock> arr = null;
    try{
        arr = this.buscarStock(cent).stream().filter(a -> cant == a.getCantidad()).collect(Collectors.toList());
    }catch(StockNoEncontradoException e){
        JOptionPane.showMessageDialog(null, e.getMessage(), "No se encontraron Stocks en la Sucursal", JOptionPane.ERROR_MESSAGE);
    }
    return arr;
 }

 public List<Stock> filtrarStock_CantidadMayorIgual(List<Stock> sto,Double cant, Centro_Logistico cent){  
    List<Stock> arr = null;
    try{
        arr = this.buscarStock(cent).stream().filter(a -> cant >= a.getCantidad()).collect(Collectors.toList());
    }catch(StockNoEncontradoException e){
        JOptionPane.showMessageDialog(null, e.getMessage(), "No se encontraron Stocks en la Sucursal", JOptionPane.ERROR_MESSAGE);
    }
    return arr;
 }

 public List<Stock> filtrarStock_CantidadMenor(List<Stock> sto,Double cant, Centro_Logistico cent){  
    List<Stock> arr = null;
    try{
        arr = this.buscarStock(cent).stream().filter(a -> cant < a.getCantidad()).collect(Collectors.toList());
    }catch(StockNoEncontradoException e){
        JOptionPane.showMessageDialog(null, e.getMessage(), "No se encontraron Stocks en la Sucursal", JOptionPane.ERROR_MESSAGE);
    }
    return arr;
 }

 public List<Stock> filtrarStock_UnidadKilogramos(List<Stock> sto, Centro_Logistico cent){  
    List<Stock> arr = null;
    try{
        arr = this.buscarStock(cent).stream().filter(a -> UNIDAD.KILOGRAMOS.equals(a.getUnidad())).collect(Collectors.toList());
    }catch(StockNoEncontradoException e){
        JOptionPane.showMessageDialog(null, e.getMessage(), "No se encontraron Stocks en la Sucursal", JOptionPane.ERROR_MESSAGE);
    }
    return arr;
 }

 public List<Stock> filtrarStock_UnidadUnidad(List<Stock> sto, Centro_Logistico cent){  
    List<Stock> arr = null;
    try{
        arr = this.buscarStock(cent).stream().filter(a -> UNIDAD.UNIDADES.equals(a.getUnidad())).collect(Collectors.toList());
    }catch(StockNoEncontradoException e){
        JOptionPane.showMessageDialog(null, e.getMessage(), "No se encontraron Stocks en la Sucursal", JOptionPane.ERROR_MESSAGE);
    }
    return arr;
 }
}
