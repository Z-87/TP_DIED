package modelo;

public class Sucursal extends Centro_Logistico{
    
    public Sucursal(){
        this.id_logistico = null;
        this.nombre = null;
        this.estado = null;
        this.horario_apertura = null;
        this.horario_cierre = null;
    }

    public Sucursal(Integer id_logistico, String nombre, ESTADO_SUCURSAL estado, String horario_apertura, String horario_cierre){
        this.id_logistico = id_logistico;
        this.nombre = nombre;
        this.estado = estado;
        this.horario_apertura = horario_apertura;
        this.horario_cierre = horario_cierre;
    };

    
}
