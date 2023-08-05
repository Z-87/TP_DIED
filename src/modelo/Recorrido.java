package modelo;

import java.util.ArrayList;

public class Recorrido {
  private Integer id_recorrido;
  private Double peso;
  private Integer duracion;
  private ArrayList<Ruta> rutas;
  
  public Recorrido(Integer id_recorrido, Double peso, Integer duracion, ArrayList<Ruta> rutas) {
    this.id_recorrido = id_recorrido;
    this.peso = peso;
    this.duracion = duracion;
    this.rutas = rutas;
  }

  public Recorrido() {
    this.id_recorrido = null;
    this.peso = null;
    this.duracion = null;
    this.rutas = null;
  }

  public Integer getId_recorrido() {
    return id_recorrido;
  }

  public Double getPeso() {
    return peso;
  }

  public Integer getDuracion() {
    return duracion;
  }

  public ArrayList<Ruta> getRutas() {
    return rutas;
  }

  public void setId_recorrido(Integer id_recorrido) {
    this.id_recorrido = id_recorrido;
  }

  public void setPeso(Double peso) {
    this.peso = peso;
  }

  public void setDuracion(Integer duracion) {
    this.duracion = duracion;
  }

  public void setRutas(ArrayList<Ruta> rutas) {
    this.rutas = rutas;
  }
  
}
