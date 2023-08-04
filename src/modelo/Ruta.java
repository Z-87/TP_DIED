package modelo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Ruta {
    Integer id_ruta;
    Centro_Logistico sucursal_origen;
    Centro_Logistico sucursal_destino;
    Double capacidad;
    Integer duracion;
    ESTADO_RUTA estado;

    

    public Ruta() {
    }

    public Ruta(Integer id_ruta, Centro_Logistico sucursal_origen, Centro_Logistico sucursal_destino, double capacidad,
            int duracion, ESTADO_RUTA estado) {
        this.id_ruta = id_ruta;
        this.sucursal_origen = sucursal_origen;
        this.sucursal_destino = sucursal_destino;
        this.capacidad = capacidad;
        this.duracion = duracion;
        this.estado = estado;
    }

    public Integer getId_ruta() {
        return id_ruta;
    }

    public void setId_ruta(Integer id_ruta) {
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

    public void cargarSucursalOrigen(Centro_Logistico s){
        this.setSucursal_Origen(s);
        cargarDatosRuta("sucursal_origen", "'"+s.getId_logistico()+"'");
    }

    public void cargarSucursalDestino(Centro_Logistico s){
        this.setSucursal_Destino(s);
        cargarDatosRuta("sucursal_destino", "'"+s.getId_logistico()+"'");
    }

    public void cargarCapacidad(Double cap){
        this.setCapacidad(cap);
        cargarDatosRuta("capacidad", cap.toString());
    }

    public void cargarDuracion(Integer dur){
        this.setDuracion(dur);
        cargarDatosRuta("duracion", dur.toString());
    }

    public void setOperativa(){
        this.setEstado(ESTADO_RUTA.OPERATIVA);
        cargarDatosRuta("estado", "'OPERATIVA'");
    }

    public void setNoOperativa(){
        this.setEstado(ESTADO_RUTA.NO_OPERATIVA);
        cargarDatosRuta("estado", "'NO_OPERATIVA'");
    }

    protected void cargarDatosRuta(String campo, String newValor){
        Connection conn = null;
        try {
            Class.forName("org.postgresql.Driver");
            conn = DriverManager.getConnection("jdbc:postgresql://localhost/", "tpadmin", "tpadmindied");
            PreparedStatement tabla;
            System.out.println(newValor);
            tabla = conn.prepareStatement("UPDATE tp.Ruta SET "+campo+"="+newValor+" WHERE id_ruta='"+this.getId_ruta()+"'");
            tabla.executeUpdate();
        } catch(ClassNotFoundException e){
            e.printStackTrace();
        } catch(SQLException e){
            e.printStackTrace();
        }
    }
}
