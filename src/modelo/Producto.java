package modelo;

public class Producto implements Gestor {
  private String id_producto;
  private String nombre;
  private String descripcion;
  private Double precioUnitario;
  private Double precioKg;

  public Producto() {
  }

  public Producto(String id_producto, String nombre, String descripcion, Double precioUnitario, Double precioKg) {
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
  public String getId_producto() {
    return id_producto;
  }

}
