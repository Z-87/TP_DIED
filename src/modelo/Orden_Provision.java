package modelo;

import java.util.ArrayList;
import java.util.Date;

public class Orden_Provision {
  private Date fechaDeOrden;
  private Centro_Logistico sucursalDestino;
  private Double tiempoEsperadoHoras;
  private ESTADO_ORDEN estado;
  private ArrayList<Cantidad> productos = new ArrayList<>(); 
}
