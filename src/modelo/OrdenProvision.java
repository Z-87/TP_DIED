package modelo;

import java.util.ArrayList;
import java.util.Date;

public class OrdenProvision {
  private Date fechaDeOrden;
  private CentroLogistico sucursalDestino;
  private Double tiempoEsperadoHoras;
  private EstadoOrden estado;
  private ArrayList<Cantidad> productos = new ArrayList<>(); 
}
