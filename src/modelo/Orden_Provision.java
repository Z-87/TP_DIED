package modelo;

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
  private Integer id_recorrido;
  
  public Orden_Provision(Integer id, Date fechaDeOrden, Centro_Logistico sucursalDestino, Centro_Logistico sucursalOrigen, Double tiempoEsperadoHoras,
      ESTADO_ORDEN estado, ArrayList<Cantidad> productos, Integer id_recorrido) {
    this.id_orden = id;
    this.fechaDeOrden = fechaDeOrden;
    this.sucursalDestino = sucursalDestino;
    this.sucursalOrigen = sucursalOrigen;
    this.tiempoEsperadoHoras = tiempoEsperadoHoras;
    this.estado = estado;
    this.productos = productos;
    this.id_recorrido = id_recorrido;
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

  public void setId_recorrido(Integer id_recorrido) {
    this.id_recorrido = id_recorrido;
  }

  public Integer getId_recorrido() {
    return id_recorrido;
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
