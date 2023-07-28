package modelo;



public class Ruta {
    String id_ruta;
    Centro_Logistico sucursal_origen;
    Centro_Logistico sucursal_destino;
    Double capacidad;
    Integer duracion;
    ESTADO_RUTA estado;

    

    public Ruta() {
    }

    public Ruta(String id_ruta, Centro_Logistico sucursal_origen, Centro_Logistico sucursal_destino, double capacidad,
            int duracion, ESTADO_RUTA estado) {
        this.id_ruta = id_ruta;
        this.sucursal_origen = sucursal_origen;
        this.sucursal_destino = sucursal_destino;
        this.capacidad = capacidad;
        this.duracion = duracion;
        this.estado = estado;
    }

    public String getId_ruta() {
        return id_ruta;
    }

    public void setId_ruta(String id_ruta) {
        this.id_ruta = id_ruta;
    }

    public Centro_Logistico getSucursal_Origen() {
        return sucursal_origen;
    }

    public void setSucursal_Origen(Centro_Logistico sucursal_origen) {
        this.sucursal_origen = sucursal_origen;
    }

    public Centro_Logistico getSucursal_Destino() {
        return sucursal_destino;
    }

    public void setSucursal_Destino(Centro_Logistico sucursal_destino) {
        this.sucursal_destino = sucursal_destino;
    }

    public Double getCapacidad() {
        return capacidad;
    }

    public void setCapacidad(double capacidad) {
        this.capacidad = capacidad;
    }

    public Integer getDuracion() {
        return duracion;
    }

    public void setDuracion(int duracion) {
        this.duracion = duracion;
    }

    public ESTADO_RUTA getEstado() {
        return estado;
    }

    public void setEstado(ESTADO_RUTA estado) {
        this.estado = estado;
    }
}
