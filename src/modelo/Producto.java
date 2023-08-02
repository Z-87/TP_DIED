package modelo;

public class Producto {
  private Integer id_producto;
  private String nombre;
  private String descripcion;
  private Double precioUnitario;
  private Double precioKg;

  public Producto() {
  }

  public Producto(Integer id_producto, String nombre, String descripcion, Double precioUnitario, Double precioKg) {
    this.id_producto = id_producto;
    this.nombre = nombre;
    this.descripcion = descripcion;
    this.precioUnitario = precioUnitario;
    this.precioKg = precioKg;
  }
  
  public String getNombre() {
    return nombre;
  }
  public String getDescripcion() {
    return descripcion;
  }
  public Double getPrecioUnitario() {
    return precioUnitario;
  }
  public Double getPrecioKg() {
    return precioKg;
  }
  public Integer getId_producto() {
    return id_producto;
  }

  public void setId_producto(Integer id_producto) {
    this.id_producto = id_producto;
  }

  public void setNombre(String nombre) {
    this.nombre = nombre;
  }

  public void setDescripcion(String descripcion) {
    this.descripcion = descripcion;
  }

  public void setPrecioUnitario(Double precioUnitario) {
    this.precioUnitario = precioUnitario;
  }

  public void setPrecioKg(Double precioKg) {
    this.precioKg = precioKg;
  }

}
