package modelo;

public class Cantidad {
  private Integer id_cantidad;
  private Orden_Provision orden_provision;
  private Double cantidad;
  private UNIDAD unidad;
  private Producto producto;
  
  
  public Cantidad(Integer id_cantidad, Orden_Provision orden_provision, Double cantidad, UNIDAD unidad,
      Producto producto) {
    this.id_cantidad = id_cantidad;
    this.orden_provision = orden_provision;
    this.cantidad = cantidad;
    this.unidad = unidad;
    this.producto = producto;
  }
  
  public Double getCantidad() {
    return cantidad;
  }
  public void setCantidad(Double cantidad) {
    this.cantidad = cantidad;
  }

  public UNIDAD getUnidad() {
    return unidad;
  }
  public void setUnidad(UNIDAD unidad) {
    this.unidad = unidad;
  }

  public Orden_Provision getOrden_provision() {
    return orden_provision;
  }
  public void setOrden_provision(Orden_Provision orden_provision) {
    this.orden_provision = orden_provision;
  }

  public Producto getProducto() {
    return producto;
  }
  public void setProducto(Producto producto) {
    this.producto = producto;
  }

  public Integer getId_cantidad() {
    return id_cantidad;
  }

  public void setId_cantidad(Integer id_cantidad) {
    this.id_cantidad = id_cantidad;
  }
  
}
