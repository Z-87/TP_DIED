package modelo;

public class Producto {
  private Integer id_producto;
  private String nombre;
  private String descripcion;
  private Double precio;

  public Producto() {
  }

  public Producto(Integer id_producto, String nombre, String descripcion, Double precio) {
    this.id_producto = id_producto;
    this.nombre = nombre;
    this.descripcion = descripcion;
    this.precio = precio;
  }
  
  public String getNombre() {
    return nombre;
  }
  public String getDescripcion() {
    return descripcion;
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

  public Double getPrecio() {
    return precio;
  }

  public void setPrecio(Double precio) {
    this.precio = precio;
  }

  public String toString() {
    return this.nombre;
  }
}