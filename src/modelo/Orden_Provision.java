package modelo;

import java.util.ArrayList;
import java.util.Date;

public class Orden_Provision {
  private String idOrden;
  private Date fechaDeOrden;
  private Centro_Logistico sucursalDestino;
  private Centro_Logistico sucursalOrigen;
  private Double tiempoEsperadoHoras;
  private ESTADO_ORDEN estado;
  private ArrayList<Cantidad> productos = new ArrayList<>();
  private String id_recorrido;
  
  public Orden_Provision(String id, Date fechaDeOrden, Centro_Logistico sucursalDestino, Centro_Logistico sucursalOrigen, Double tiempoEsperadoHoras,
      ESTADO_ORDEN estado, ArrayList<Cantidad> productos, String id_recorrido) {
    this.idOrden = id;
    this.fechaDeOrden = fechaDeOrden;
    this.sucursalDestino = sucursalDestino;
    this.sucursalOrigen = sucursalOrigen;
    this.tiempoEsperadoHoras = tiempoEsperadoHoras;
    this.estado = estado;
    this.productos = productos;
    this.id_recorrido = id_recorrido;
  }
  
  public String getId_recorrido() {
    return id_recorrido;
  }
  public Centro_Logistico getSucursalOrigen() {
    return sucursalOrigen;
  }
  public String getId() {
    return idOrden;
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
