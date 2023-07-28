package modelo;

public class Producto implements Gestor {
  private String nombre;
  private String descripcion;
  private Double precioUnitario;
  private Double precioKg;

  public Producto() {
  }

  public Producto(String nombre, String descripcion, Double precioUnitario, Double precioKg) {
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

}
