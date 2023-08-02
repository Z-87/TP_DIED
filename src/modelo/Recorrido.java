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
  
}
