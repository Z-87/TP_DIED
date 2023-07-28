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
    
    ArrayList<Ruta> rutas = new ArrayList<>();
    ArrayList<Centro_Logistico> sucursales = new ArrayList<>();

    public void cargarSucursales(){
        Connection conn = null;
        PreparedStatement tabla = null;
        ResultSet rs = null;
        try {
            Class.forName("org.postgresql.Driver");
            conn = DriverManager.getConnection("jdbc:postgresql://localhost/", "tpadmin", "tpadmindied");
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
            tabla.close();
            rs.close();
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
            tabla.close();
            rs.close();
            tabla = conn.prepareStatement("SELECT * FROM tp.Puerto AS P JOIN tp.Centro_Logistico AS CL ON (CL.id_logistico = P.id_puerto)");
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
        finally { //Libera los recursos
            if(rs!=null) try { rs.close(); }
            catch (SQLException e) { e.printStackTrace(); }
            if(tabla!=null) try { tabla.close(); }
            catch (SQLException e) {e.printStackTrace(); }
            if(conn!=null) try { conn.close(); }
            catch (SQLException e) { e.printStackTrace(); }
        }

    }

    public void cargarRutas() {
        Connection conn = null;
        PreparedStatement tabla = null;
        ResultSet rs = null;
        try {
            Class.forName("org.postgresql.Driver");
            conn = DriverManager.getConnection("jdbc:postgresql://localhost/", "tpadmin", "tpadmindied");
            tabla = conn.prepareStatement("SELECT * FROM tp.Ruta");
            rs = tabla.executeQuery();

            while(rs.next()){
                Ruta aux = new Ruta();
                aux.setId_ruta(rs.getString("id_ruta"));
                String aux1=rs.getString("sucursal_origen");
                String aux2=rs.getString("sucursal_destino");
                aux.setSucursal_Origen((this.sucursales.stream()
                                                        .filter(a -> (aux1.equals(a.getId_logistico())))
                                                        .collect(Collectors.toList())).get(0));
                aux.setSucursal_Destino((this.sucursales.stream()
                                                        .filter(a -> (aux2.equals(a.getId_logistico())))
                                                        .collect(Collectors.toList())).get(0));
                aux.setEstado(ESTADO_RUTA.valueOf(rs.getString("estado")));
                aux.setCapacidad(rs.getDouble("capacidad"));
                aux.setDuracion(rs.getInt("duracion"));
                rutas.add(aux);
            } //Captura las excepciones
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (EnumConstantNotPresentException e){
            e.printStackTrace();
        } 
        finally { //Libera los recursos
            if(rs!=null) try { rs.close(); }
            catch (SQLException e) { e.printStackTrace(); }
            if(tabla!=null) try { tabla.close(); }
            catch (SQLException e) {e.printStackTrace(); }
            if(conn!=null) try { conn.close(); }
            catch (SQLException e) { e.printStackTrace(); }
        }

    }

    public void cargarRuta(Ruta r){
        Connection conn = null;
        PreparedStatement tabla = null;
        ResultSet rs = null;
        if(!rutas.contains(r)){
            try {
            Class.forName("org.postgresql.Driver");
            conn = DriverManager.getConnection("jdbc:postgresql://localhost/", "tpadmin", "tpadmindied");
            tabla = conn.prepareStatement("INSERT INTO tp.Ruta(id_ruta, sucursal_origen, sucursal_destino, estado, capacidad, duracion) VALUES ('"+r.getId_ruta()+"','"+r.getSucursal_Origen().getId_logistico()+"','"+r.getSucursal_Destino().getId_logistico()+"','"+((r.getEstado()).toString())+"',"+(r.getCapacidad().toString())+","+r.getDuracion().toString()+")");
            tabla.executeUpdate();

            rutas.add(r);
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            finally {
                if(rs!=null) try { rs.close(); }
                catch (SQLException e) { e.printStackTrace(); }
                if(tabla!=null) try { tabla.close(); }
                catch (SQLException e) {e.printStackTrace(); }
                if(conn!=null) try { conn.close(); }
                catch (SQLException e) { e.printStackTrace(); }
            }
        }
    }

    public void eliminarRuta(Ruta r){
        Connection conn = null;
        PreparedStatement tabla = null;
        ResultSet rs = null;
        try{
            Class.forName("org.postgresql.Driver");
            conn = DriverManager.getConnection("jdbc:postgresql://localhost/", "tpadmin", "tpadmindied");
            tabla = conn.prepareStatement("DELETE FROM tp.Ruta WHERE id_ruta ='"+ r.getId_ruta()+"'");
            tabla.executeUpdate();
            this.rutas.remove(r);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
            if(rs!=null) try { rs.close(); }
            catch (SQLException e) { e.printStackTrace(); }
            if(tabla!=null) try { tabla.close(); }
            catch (SQLException e) {e.printStackTrace(); }
            if(conn!=null) try { conn.close(); }
            catch (SQLException e) { e.printStackTrace(); }
        }
    }

    public void cargarSucursal(Sucursal a){
        Connection conn = null;
        PreparedStatement tabla = null;
        ResultSet rs = null;
        if(!sucursales.contains(a)){
            try {
            Class.forName("org.postgresql.Driver");
            conn = DriverManager.getConnection("jdbc:postgresql://localhost/", "tpadmin", "tpadmindied");
            tabla = conn.prepareStatement("INSERT INTO tp.Centro_Logistico(id_logistico, nombre, estado, horario_apertura, horario_cierre) VALUES ("+a.getId_logistico()+","+a.getNombre()+","+((a.getEstado()).toString())+","+a.getHorario_apertura()+","+a.getHorario_cierre()+")");
            rs = tabla.executeQuery();
            tabla.close();
            rs.close();
            tabla = conn.prepareStatement("INSERT INTO tp.Sucursal(id_centro) VALUES ("+a.getId_logistico()+")");
            rs = tabla.executeQuery();

            sucursales.add(a);
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            finally {
                if(rs!=null) try { rs.close(); }
                catch (SQLException e) { e.printStackTrace(); }
                if(tabla!=null) try { tabla.close(); }
                catch (SQLException e) {e.printStackTrace(); }
                if(conn!=null) try { conn.close(); }
                catch (SQLException e) { e.printStackTrace(); }
            }
        }
    }

    public void eliminarSucursal(Sucursal b){
        Connection conn = null;
        PreparedStatement tabla = null;
        ResultSet rs = null;
        try{
            Class.forName("org.postgresql.Driver");
            conn = DriverManager.getConnection("jdbc:postgresql://localhost/", "tpadmin", "tpadmindied");
            tabla = conn.prepareStatement("DELETE INTO tp.Centro_Logistico WHERE id_logistico ="+b.getId_logistico());
            rs = tabla.executeQuery();
            tabla.close();
            rs.close();
            tabla = conn.prepareStatement("DELETE INTO tp.Sucursal WHERE id_sucursal="+b.getId_logistico());
            rs = tabla.executeQuery();
            this.sucursales.remove(b);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
            if(rs!=null) try { rs.close(); }
            catch (SQLException e) { e.printStackTrace(); }
            if(tabla!=null) try { tabla.close(); }
            catch (SQLException e) {e.printStackTrace(); }
            if(conn!=null) try { conn.close(); }
            catch (SQLException e) { e.printStackTrace(); }
        }
    }

    public void cargarCentro(Centro a){
        Connection conn = null;
        PreparedStatement tabla = null;
        ResultSet rs = null;
        if(!sucursales.contains(a)){
            try {
            Class.forName("org.postgresql.Driver");
            conn = DriverManager.getConnection("jdbc:postgresql://localhost/", "tpadmin", "tpadmindied");
            tabla = conn.prepareStatement("INSERT INTO tp.Centro_Logistico(id_logistico, nombre, estado, horario_apertura, horario_cierre) VALUES ("+a.getId_logistico()+","+a.getNombre()+","+((a.getEstado()).toString())+","+a.getHorario_apertura()+","+a.getHorario_cierre()+")");
            rs = tabla.executeQuery();
            tabla.close();
            rs.close();
            tabla = conn.prepareStatement("INSERT INTO tp.Centro(id_centro) VALUES ("+a.getId_logistico()+")");
            rs = tabla.executeQuery();

            sucursales.add(a);
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            finally {
                if(rs!=null) try { rs.close(); }
                catch (SQLException e) { e.printStackTrace(); }
                if(tabla!=null) try { tabla.close(); }
                catch (SQLException e) {e.printStackTrace(); }
                if(conn!=null) try { conn.close(); }
                catch (SQLException e) { e.printStackTrace(); }
            }
        }
        
    }

    public void eliminarCentro(Centro b){
        Connection conn = null;
        PreparedStatement tabla = null;
        ResultSet rs = null;
        try{
            Class.forName("org.postgresql.Driver");
            conn = DriverManager.getConnection("jdbc:postgresql://localhost/", "tpadmin", "tpadmindied");
            tabla = conn.prepareStatement("DELETE INTO tp.Centro_Logistico WHERE id_logistico ="+b.getId_logistico());
            rs = tabla.executeQuery();
            tabla.close();
            rs.close();
            tabla = conn.prepareStatement("DELETE INTO tp.Centro WHERE id_centro="+b.getId_logistico());
            rs = tabla.executeQuery();
            this.sucursales.remove(b);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
            if(rs!=null) try { rs.close(); }
            catch (SQLException e) { e.printStackTrace(); }
            if(tabla!=null) try { tabla.close(); }
            catch (SQLException e) {e.printStackTrace(); }
            if(conn!=null) try { conn.close(); }
            catch (SQLException e) { e.printStackTrace(); }
        }
    }

    public void cargarPuerto(Puerto a){
        Connection conn = null;
        PreparedStatement tabla = null;
        ResultSet rs = null;
        if(!sucursales.contains(a)){
            try {
            Class.forName("org.postgresql.Driver");
            conn = DriverManager.getConnection("jdbc:postgresql://localhost/", "tpadmin", "tpadmindied");
            tabla = conn.prepareStatement("INSERT INTO tp.Centro_Logistico(id_logistico, nombre, estado, horario_apertura, horario_cierre) VALUES ("+a.getId_logistico()+","+a.getNombre()+","+((a.getEstado()).toString())+","+a.getHorario_apertura()+","+a.getHorario_cierre()+")");
            rs = tabla.executeQuery();
            tabla.close();
            rs.close();
            tabla = conn.prepareStatement("INSERT INTO tp.Puerto(id_puerto) VALUES ("+a.getId_logistico()+")");
            rs = tabla.executeQuery();

            sucursales.add(a);
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            finally {
                if(rs!=null) try { rs.close(); }
                catch (SQLException e) { e.printStackTrace(); }
                if(tabla!=null) try { tabla.close(); }
                catch (SQLException e) {e.printStackTrace(); }
                if(conn!=null) try { conn.close(); }
                catch (SQLException e) { e.printStackTrace(); }
            }
        }
    }

    public void eliminarPuerto(Puerto b){
        Connection conn = null;
        PreparedStatement tabla = null;
        ResultSet rs = null;
        try{
            Class.forName("org.postgresql.Driver");
            conn = DriverManager.getConnection("jdbc:postgresql://localhost/", "tpadmin", "tpadmindied");
            tabla = conn.prepareStatement("DELETE INTO tp.Centro_Logistico WHERE id_logistico ="+b.getId_logistico());
            rs = tabla.executeQuery();
            tabla.close();
            rs.close();
            tabla = conn.prepareStatement("DELETE INTO tp.Puerto WHERE id_puerto="+b.getId_logistico());
            rs = tabla.executeQuery();
            this.sucursales.remove(b);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
            if(rs!=null) try { rs.close(); }
            catch (SQLException e) { e.printStackTrace(); }
            if(tabla!=null) try { tabla.close(); }
            catch (SQLException e) {e.printStackTrace(); }
            if(conn!=null) try { conn.close(); }
            catch (SQLException e) { e.printStackTrace(); }
        }
    }

    public ArrayList<Ruta> getRutas() {
      return rutas;
    }

    public ArrayList<Centro_Logistico> getSucursales() {
      return sucursales;
    }

    
}
