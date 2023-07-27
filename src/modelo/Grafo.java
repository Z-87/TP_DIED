package modelo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Grafo {
    
    ArrayList<Ruta> rutas;
    ArrayList<Centro_Logistico> sucursales;

    public void cargarSucursales(){
        Connection conn = null;
        try {
            Class.forName("org.postgresql.Driver");
            conn = DriverManager.getConnection("localhost", "tpadmin", "tpadmindied");
            PreparedStatement tabla;
            ResultSet rs;
            tabla = conn.prepareStatement("SELECT * FROM tp.Centro AS C JOIN tp.Centro_Logistico AS CL ON (CL.id_logistico = C.id_centro)");
            rs = tabla.executeQuery();

            while(rs.next()){
                Centro aux = new Centro();
                aux.setId_logistico(rs.getString("id_logistico"));
                aux.setNombre(rs.getString("nombre"));
                aux.setHorario_apertura(rs.getString("horario_apertura"));
                aux.setHorario_cierre(rs.getString("horario_cierre"));
                aux.setEstado(ESTADO_SUCURSAL.valueOf(rs.getString("estado")));
                sucursales.add(aux);
            }

            tabla = conn.prepareStatement("SELECT * FROM tp.Sucursal AS S JOIN tp.Centro_Logistico AS CL ON (CL.id_logistico = S.id_sucursal)");
            rs = tabla.executeQuery();

            while(rs.next()){
                Sucursal aux2 = new Sucursal();
                aux2.setId_logistico(rs.getString("id_logistico"));
                aux2.setNombre(rs.getString("nombre"));
                aux2.setHorario_apertura(rs.getString("horario_apertura"));
                aux2.setHorario_cierre(rs.getString("horario_cierre"));
                aux2.setEstado(ESTADO_SUCURSAL.valueOf(rs.getString("estado")));
                sucursales.add(aux2);
            }

            tabla = conn.prepareStatement("SELECT * FROM tp.Puerto AS P JOIN tp.Centro_Logistico AS CL ON (CL.id_logistico = P.id_sucursal)");
            rs = tabla.executeQuery();

            while(rs.next()){
                Puerto aux3 = new Puerto();
                aux3.setId_logistico(rs.getString("id_logistico"));
                aux3.setNombre(rs.getString("nombre"));
                aux3.setHorario_apertura(rs.getString("horario_apertura"));
                aux3.setHorario_cierre(rs.getString("horario_cierre"));
                aux3.setEstado(ESTADO_SUCURSAL.valueOf(rs.getString("estado")));
                sucursales.add(aux3);
            }

        } catch(ClassNotFoundException e){
            e.printStackTrace();
        } catch(SQLException e){
            e.printStackTrace();
        }
    }

    public void cargarRutas() {
        Connection conn = null;
        try {
            Class.forName("org.postgresql.Driver");
            conn = DriverManager.getConnection("localhost", "tpadmin", "tpadmindied");
            PreparedStatement tabla;
            ResultSet rs;
            tabla = conn.prepareStatement("SELECT * FROM st.Ruta");
            rs = tabla.executeQuery();
            
            while(rs.next()){
                Ruta aux = new Ruta();
                aux.setId_ruta(rs.getString("id_ruta"));
                aux.setSucursal_Origen((this.sucursales.stream()
                                                        .filter(a -> (a.getId_logistico() = rs.getString("sucursal_origen")))
                                                        .collect(Collectors.toList())).get(0));
                aux.setSucursal_Destino((this.sucursales.stream()
                                                        .filter(a -> (a.getId_logistico() = rs.getString("sucursal_destino")))
                                                        .collect(Collectors.toList())).get(0));
                aux.setEstado(ESTADO_RUTA.valueOf(rs.getString("estado")));
                aux.setCapacidad(rs.getDouble("capacidad"));
                aux.setDuracion(rs.getInt("duracion"));
                rutas.add(aux);
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (EnumConstantNotPresentException e){
            e.printStackTrace();
        } 


    }

    public void cargarRuta(){

    }

    public void eliminarRuta(){

    }

    public void cargarSucursal(){

    }

    public void eliminarSucursal(){
        
    }

    public void cargarCentro(Centro a){
        Connection conn = null;
        if(!sucursales.contains(a)){
            try {
            Class.forName("org.postgresql.Driver");
            conn = DriverManager.getConnection("localhost", "tpadmin", "tpadmindied");
            PreparedStatement tabla;
            ResultSet rs;
            tabla = conn.prepareStatement("INSERT INTO tp.Centro_Logistico(id_logistico, nombre, estado, horario_apertura, horario_cierre) VALUES ("+a.getId_logistico()+","+a.getNombre()+","a.getEstado()+","+a.getHorario_apertura()+","+a.getHorario_cierre()+")");
            rs = tabla.executeQuery();
            
            sucursales.add(a);
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        
    }

    public void eliminarCentro(){
        
    }

    public void cargarPuerto(){

    }

    public void eliminarPuerto(){
        
    }

}
