package modelo;

public class Stock {
  private Integer id_stock;
  private Integer cantidadUnidades;
  private Double cantidadKg;
  private Producto producto;
  private Centro_Logistico sucursal;
  
  public Stock(Integer id_stock, Integer cantidadUnidades, Double cantidadKg, Producto producto, Centro_Logistico sucursal) {
    this.id_stock = id_stock;
    this.cantidadUnidades = cantidadUnidades;
    this.cantidadKg = cantidadKg;
    this.producto = producto;
    this.sucursal = sucursal;
  }
  public Centro_Logistico getSucursal() {
    return sucursal;
  }
  public Integer getId_stock() {
    return id_stock;
  }
  public Integer getCantidadUnidades() {
    return cantidadUnidades;
  }
  public Double getCantidadKg() {
    return cantidadKg;
  }
  public Producto getProducto() {
    return producto;
  }
}
