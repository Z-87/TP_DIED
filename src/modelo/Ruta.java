package modelo;

public class Ruta {
    String id_ruta;
    Centro_Logistico origen;
    Centro_Logistico destino;
    int capacidad;
    int duracion;
    ESTADO_RUTA estado;

    public Ruta(String id_ruta, Centro_Logistico origen, Centro_Logistico destino, int capacidad, int duracion,
            ESTADO_RUTA estado) {
        this.id_ruta = id_ruta;
        this.origen = origen;
        this.destino = destino;
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

    public Centro_Logistico getOrigen() {
        return origen;
    }

    public void setOrigen(Centro_Logistico origen) {
        this.origen = origen;
    }

    public Centro_Logistico getDestino() {
        return destino;
    }

    public void setDestino(Centro_Logistico destino) {
        this.destino = destino;
    }

    public int getCapacidad() {
        return capacidad;
    }

    public void setCapacidad(int capacidad) {
        this.capacidad = capacidad;
    }

    public int getDuracion() {
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
