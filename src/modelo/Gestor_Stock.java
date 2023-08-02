package modelo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import exceptions.StockNoEncontradoException;

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
          "cantidad_unit = " + stockEditado.getCantidadUnidades() + ". " +
          "cantidad_kg = " + stockEditado.getCantidadKg() + " " +
        "WHERE id_stock = '" + stock.getId_stock() + "'"
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
        "WHERE id_logistico = '" + sucursal.getId_logistico() + "' AND id_producto = '" + producto.getId_producto() + "'"
      );
      rs = tabla.executeQuery();

      if(rs.next()) {
        resultado = new Stock(
          rs.getString("id_stock"), 
          (Integer)Integer.parseInt(rs.getString("cantidad_unit")), 
          (Double)Double.parseDouble(rs.getString("cantidad_unit")), 
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
        "WHERE id_logistico = '" + sucursal.getId_logistico() + "'"
      );
      rs = tabla.executeQuery();

      while(rs.next()) {
        resultado.add(new Stock(
          rs.getString("id_stock"), 
          rs.getInt("cantidad_unit"), 
          rs.getDouble("cantidad_unit"), 
          new Producto(rs.getString("id_producto"), rs.getString("nombre"), rs.getString("descripcion"), rs.getDouble("precio_unit"), rs.getDouble("precio_kg")), 
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
        "INNER JOIN tp.centro ON id_centro = id_logistico" +
        "INNER JOIN tp.puerto ON id_puerto = id_logistico" +
        "INNER JOIN tp.sucursal ON id_sucursal = id_logistico" +
        "WHERE id_producto = '" + producto.getId_producto() + "'"
      );
      rs = tabla.executeQuery();

      while(rs.next()) {
        Centro_Logistico sucursal;
        if(rs.getString("id_centro") != null) {
          sucursal = new Centro(
            rs.getString("id_centro"), 
            rs.getString("nombre"), 
            ESTADO_SUCURSAL.valueOf(rs.getString("estado")), 
            rs.getString("horario_apertura"), 
            rs.getString("horario_apertura")
          );
        } else if(rs.getString("id_puerto") != null) {
          sucursal = new Puerto(
            rs.getString("id_puerto"), 
            rs.getString("nombre"), 
            ESTADO_SUCURSAL.valueOf(rs.getString("estado")), 
            rs.getString("horario_apertura"), 
            rs.getString("horario_apertura")
          );
        } else {
          sucursal = new Sucursal(
            rs.getString("id_sucursal"), 
            rs.getString("nombre"), 
            ESTADO_SUCURSAL.valueOf(rs.getString("estado")), 
            rs.getString("horario_apertura"), 
            rs.getString("horario_apertura")
          );
        }
        
        resultado.add(new Stock(
          rs.getString("id_stock"), 
          rs.getInt("cantidad_unit"), 
          rs.getDouble("cantidad_unit"), 
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
    Connection conn = null;
    PreparedStatement tabla = null;
    ResultSet rs = null;
    try {
      Class.forName("org.postgresql.Driver");
      conn = DriverManager.getConnection("jdbc:postgresql://localhost/", "tpadmin", "tpadmindied");
      
      String id_productos = String.join("','", productos.stream().map(p -> (p.getId_producto())).toList());
      tabla = conn.prepareStatement(
        "SELECT " +
          "id_logistico, id_centro, id_puerto, id_sucursal, C.nombre cnombre, C.estado cestado, C.horario_apertura chorario_apertura, C.horario_cierre chorario_cierre," +
          "id_producto, P.nombre pnombre, P.descripcion pdescripcion, P.precio_unit pprecio_unit, P.precio_kg pprecio_kg, id_stock, cantidad_unit, cantidad_kg " + 
        "FROM tp.stock " + 
          "INNER JOIN tp.centro_logistico C USING(id_logistico) " +
          "LEFT JOIN tp.centro ON id_centro = id_logistico " +
          "LEFT JOIN tp.puerto ON id_puerto = id_logistico " +
          "LEFT JOIN tp.sucursal ON id_sucursal = id_logistico " +
          "INNER JOIN tp.producto P USING(id_producto) " +
        "WHERE id_producto IN ('" + id_productos + "');"
      );
      rs = tabla.executeQuery();

      while(rs.next()) {
        Centro_Logistico sucursal;
        if(rs.getString("id_centro") != null) {
          sucursal = new Centro(
            rs.getString("id_centro"), 
            rs.getString("Cnombre"), 
            ESTADO_SUCURSAL.valueOf(rs.getString("Cestado")), 
            rs.getString("Chorario_apertura"), 
            rs.getString("Chorario_apertura")
          );
        } else if(rs.getString("id_puerto") != null) {
          sucursal = new Puerto(
            rs.getString("id_puerto"), 
            rs.getString("Cnombre"), 
            ESTADO_SUCURSAL.valueOf(rs.getString("Cestado")), 
            rs.getString("Chorario_apertura"), 
            rs.getString("Chorario_apertura")
          );
        } else {
          sucursal = new Sucursal(
            rs.getString("id_sucursal"), 
            rs.getString("Cnombre"), 
            ESTADO_SUCURSAL.valueOf(rs.getString("Cestado")), 
            rs.getString("Chorario_apertura"), 
            rs.getString("Chorario_apertura")
          );
        }

        Producto producto = new Producto(
          rs.getString("id_producto"), 
          rs.getString("Pnombre"), 
          rs.getString("Pdescripcion"), 
          rs.getDouble("Pprecio_unit"), 
          rs.getDouble("Pprecio_kg")
        );

        resultado.add(new Stock(
          rs.getString("id_stock"), 
          rs.getInt("cantidad_unit"), 
          rs.getDouble("cantidad_unit"), 
          producto,
          sucursal
        ));
      } 
      if(resultado.size() < 1){
        throw new StockNoEncontradoException("Ninguna sucursal cuenta con los productos requeridos en stock.");
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
}
