package modelo;

public class Cantidad {
  private Integer id_cantidad;
  private Orden_Provision orden_provision;
  private Integer cantidadUnidades;
  private Double cantidadKg;
  private Producto producto;
  
  
  public Cantidad(Integer id_cantidad, Orden_Provision orden_provision, Integer cantidadUnidades, Double cantidadKg,
      Producto producto) {
    this.id_cantidad = id_cantidad;
    this.orden_provision = orden_provision;
    this.cantidadUnidades = cantidadUnidades;
    this.cantidadKg = cantidadKg;
    this.producto = producto;
  }
  
  public Orden_Provision getOrden_provision() {
    return orden_provision;
  }
  public void setOrden_provision(Orden_Provision orden_provision) {
    this.orden_provision = orden_provision;
  }
  public Integer getCantidadUnidades() {
    return cantidadUnidades;
  }
  public void setCantidadUnidades(Integer cantidadUnidades) {
    this.cantidadUnidades = cantidadUnidades;
  }
  public Double getCantidadKg() {
    return cantidadKg;
  }
  public void setCantidadKg(Double cantidadKg) {
    this.cantidadKg = cantidadKg;
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
