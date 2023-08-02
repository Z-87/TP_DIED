package modelo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public abstract class Centro_Logistico {
    protected Integer id_logistico;
    protected String nombre;
    protected ESTADO_SUCURSAL estado;
    protected String horario_apertura;
    protected String horario_cierre;
    protected double pageRank;

    
    public String getNombre() {
        return this.nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public ESTADO_SUCURSAL getEstado() {
        return this.estado;
    }
    public void setEstado(ESTADO_SUCURSAL estado) {
        this.estado = estado;
    }
    public String getHorario_apertura() {
        return this.horario_apertura;
    }
    public void setHorario_apertura(String horario_apertura) {
        this.horario_apertura = horario_apertura;
    }
    public String getHorario_cierre() {
        return this.horario_cierre;
    }
    public void setHorario_cierre(String horario_cierre) {
        this.horario_cierre = horario_cierre;
    }
    public double getPageRank() {
        return this.pageRank;
    }
    public void setPageRank(double pr) {
        this.pageRank = pr;
    }
    
    public void setNoOperativa(){
        this.estado = ESTADO_SUCURSAL.NO_OPERATIVA;
        this.cargarDatosSucursal("estado", "'NO_OPERATIVA'");
    }

    public void setOperativa(){
        this.estado = ESTADO_SUCURSAL.OPERATIVA;
        this.cargarDatosSucursal("estado", "'OPERATIVA'");
    }

    public void cargarNombre(String newNombre){
        this.setNombre(newNombre);
        this.cargarDatosSucursal("nombre", "'"+newNombre+"'");
    }

    public void cargarHorario_Apertura(String newHorario){
        this.setHorario_apertura(newHorario);
        this.cargarDatosSucursal("horario_apertura", "'"+newHorario+"'");
    }

    public void cargarHorario_Cierre(String newHorario){
        this.setHorario_cierre(newHorario);
        this.cargarDatosSucursal("horario_cierre", "'"+newHorario+"'");
    }

    public void cargarPageRank(Double pr){
        this.setPageRank(pr);
        this.cargarDatosSucursal("pageRank", pr.toString());
    }

    protected void cargarDatosSucursal(String campo, String newValor){
        Connection conn = null;
        try {
            Class.forName("org.postgresql.Driver");
            conn = DriverManager.getConnection("jdbc:postgresql://localhost/", "tpadmin", "tpadmindied");
            PreparedStatement tabla;
            System.out.println(newValor);
            tabla = conn.prepareStatement("UPDATE tp.Centro_Logistico SET "+campo+"="+newValor+" WHERE id_logistico='"+this.id_logistico+"'");
            tabla.executeUpdate();
        } catch(ClassNotFoundException e){
            e.printStackTrace();
        } catch(SQLException e){
            e.printStackTrace();
        }
    }
    public Integer getId_logistico() {
      return id_logistico;
    }
    public void setId_logistico(Integer id_logistico) {
      this.id_logistico = id_logistico;
    }
    
}
