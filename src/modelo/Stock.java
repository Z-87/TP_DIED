package modelo;

public class Stock {
  private Integer id_stock;
  private Double cantidad;
  private UNIDAD unidad;
  private Producto producto;
  private Centro_Logistico sucursal;
  
  public Stock(Integer id_stock, Double cantidad, UNIDAD unidad, Producto producto, Centro_Logistico sucursal) {
    this.id_stock = id_stock;
    this.cantidad = cantidad;
    this.unidad = unidad;
    this.producto = producto;
    this.sucursal = sucursal;
  }
  
  public void setId_stock(Integer id_stock) {
    this.id_stock = id_stock;
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

  public void setProducto(Producto producto) {
    this.producto = producto;
  }

  public void setSucursal(Centro_Logistico sucursal) {
    this.sucursal = sucursal;
  }

  public Centro_Logistico getSucursal() {
    return sucursal;
  }
  public Integer getId_stock() {
    return id_stock;
  }
  public Producto getProducto() {
    return producto;
  }
}
