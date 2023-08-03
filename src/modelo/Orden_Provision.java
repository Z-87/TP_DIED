package modelo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

public class Orden_Provision {
  private Integer id_orden;
  private Date fechaDeOrden;
  private Centro_Logistico sucursalDestino;
  private Centro_Logistico sucursalOrigen;
  private Double tiempoEsperadoHoras;
  private ESTADO_ORDEN estado;
  private ArrayList<Cantidad> productos = new ArrayList<>();
  private Recorrido recorrido;
  
  public Orden_Provision(Integer id, Date fechaDeOrden, Centro_Logistico sucursalDestino, Centro_Logistico sucursalOrigen, Double tiempoEsperadoHoras,
      ESTADO_ORDEN estado, ArrayList<Cantidad> productos, Recorrido recorrido) {
    this.id_orden = id;
    this.fechaDeOrden = fechaDeOrden;
    this.sucursalDestino = sucursalDestino;
    this.sucursalOrigen = sucursalOrigen;
    this.tiempoEsperadoHoras = tiempoEsperadoHoras;
    this.estado = estado;
    this.productos = productos;
    this.recorrido = recorrido;
  }
  
  public void setId_orden(Integer id_orden) {
    this.id_orden = id_orden;
  }

  public void setFechaDeOrden(Date fechaDeOrden) {
    this.fechaDeOrden = fechaDeOrden;
  }

  public void setSucursalDestino(Centro_Logistico sucursalDestino) {
    this.sucursalDestino = sucursalDestino;
  }

  public void setSucursalOrigen(Centro_Logistico sucursalOrigen) {
    this.sucursalOrigen = sucursalOrigen;
  }

  public void setTiempoEsperadoHoras(Double tiempoEsperadoHoras) {
    this.tiempoEsperadoHoras = tiempoEsperadoHoras;
  }

  public void setEstado(ESTADO_ORDEN estado) {
    this.estado = estado;
  }

  public void setProductos(ArrayList<Cantidad> productos) {
    this.productos = productos;
  }

   public void loadProductos() {
    Connection conn = null;
    PreparedStatement tabla = null;
    ResultSet rs = null;
    try {
      Class.forName("org.postgresql.Driver");
      conn = DriverManager.getConnection("jdbc:postgresql://localhost/", "tpadmin", "tpadmindied");
      tabla = conn.prepareStatement("SELECT * FROM tp.Cantidad INNER JOIN tp.Producto USING(id_producto) WHERE id_orden = " + this.id_orden);
      rs = tabla.executeQuery();
      while(rs.next()){
        Cantidad c = new Cantidad(
          rs.getInt("id_cantidad"),
          this,
          rs.getDouble("cantidad"),
          UNIDAD.valueOf(rs.getString("unidad")),
          new Producto(
            rs.getInt("id_producto"),
            rs.getString("nombre"),
            rs.getString("descripcion"),
            rs.getDouble("precio")
          )
        );
        this.productos.add(c);
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
    this.productos = productos;
  }

  
  public Recorrido getRecorrido() {
    return recorrido;
  }

  public void setRecorrido(Recorrido recorrido) {
    this.recorrido = recorrido;
  }

  public Centro_Logistico getSucursalOrigen() {
    return sucursalOrigen;
  }
  public Integer getId() {
    return id_orden;
  }
  public Date getFechaDeOrden() {
    return fechaDeOrden;
  }
  public Centro_Logistico getSucursalDestino() {
    return sucursalDestino;
  }
  public Double getTiempoEsperadoHoras() {
    return tiempoEsperadoHoras;
  }
  public ESTADO_ORDEN getEstado() {
    return estado;
  }
  public ArrayList<Cantidad> getProductos() {
    return productos;
  } 

  
}
