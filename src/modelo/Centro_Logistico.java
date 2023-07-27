package modelo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public abstract class Centro_Logistico {
    protected String id_logistico;
    protected String nombre;
    protected ESTADO_SUCURSAL estado;
    protected String horario_apertura;
    protected String horario_cierre;

    protected String getId_logistico() {
        return id_logistico;
    }
    protected void setId_logistico(String id_logistico) {
        this.id_logistico = id_logistico;
    }
    protected String getNombre() {
        return nombre;
    }
    protected void setNombre(String nombre) {
        this.nombre = nombre;
    }
    protected ESTADO_SUCURSAL getEstado() {
        return estado;
    }
    protected void setEstado(ESTADO_SUCURSAL estado) {
        this.estado = estado;
    }
    protected String getHorario_apertura() {
        return horario_apertura;
    }
    protected void setHorario_apertura(String horario_apertura) {
        this.horario_apertura = horario_apertura;
    }
    protected String getHorario_cierre() {
        return horario_cierre;
    }
    protected void setHorario_cierre(String horario_cierre) {
        this.horario_cierre = horario_cierre;
    }
    
    public void setNoOperativa(){
        this.estado = ESTADO_SUCURSAL.NO_OPERATIVA;
        Connection conn = null;
        try {
            Class.forName("org.postgresql.Driver");
            conn = DriverManager.getConnection("localhost", "tpadmin", "tpadmindied");
            PreparedStatement tabla;
            ResultSet rs;
            tabla = conn.prepareStatement("UPDATE tp.Centro_Logistico SET estado='NO_OPERATIVA' WHERE id_logistico="+this.id_logistico);
            rs = tabla.executeQuery();
        } catch(ClassNotFoundException e){
            e.printStackTrace();
        } catch(SQLException e){
            e.printStackTrace();
        }
    }

    public void setOperativa(){
        this.estado = ESTADO_SUCURSAL.OPERATIVA;
        Connection conn = null;
        try {
            Class.forName("org.postgresql.Driver");
            conn = DriverManager.getConnection("localhost", "tpadmin", "tpadmindied");
            PreparedStatement tabla;
            ResultSet rs;
            tabla = conn.prepareStatement("UPDATE tp.Centro_Logistico SET estado='OPERATIVA' WHERE id_logistico="+this.id_logistico);
            rs = tabla.executeQuery();
        } catch(ClassNotFoundException e){
            e.printStackTrace();
        } catch(SQLException e){
            e.printStackTrace();
        }
    }

    
}
