package modelo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;
import java.util.stream.Collectors;

public class Grafo {
    
    ArrayList<Ruta> rutas = new ArrayList<>();
    ArrayList<Centro_Logistico> sucursales = new ArrayList<>();


//GESTION_SUCURSALES//GESTION_SUCURSALES//GESTION_SUCURSALES//GESTION_SUCURSALES//GESTION_SUCURSALES//GESTION_SUCURSALES//GESTION_SUCURSALES
//GESTION_SUCURSALES//GESTION_SUCURSALES//GESTION_SUCURSALES//GESTION_SUCURSALES//GESTION_SUCURSALES//GESTION_SUCURSALES//GESTION_SUCURSALES
//GESTION_SUCURSALES//GESTION_SUCURSALES//GESTION_SUCURSALES//GESTION_SUCURSALES//GESTION_SUCURSALES//GESTION_SUCURSALES//GESTION_SUCURSALES
//GESTION_SUCURSALES//GESTION_SUCURSALES//GESTION_SUCURSALES//GESTION_SUCURSALES//GESTION_SUCURSALES//GESTION_SUCURSALES//GESTION_SUCURSALES
//GESTION_SUCURSALES//GESTION_SUCURSALES//GESTION_SUCURSALES//GESTION_SUCURSALES//GESTION_SUCURSALES//GESTION_SUCURSALES//GESTION_SUCURSALES
//GESTION_SUCURSALES//GESTION_SUCURSALES//GESTION_SUCURSALES//GESTION_SUCURSALES//GESTION_SUCURSALES//GESTION_SUCURSALES//GESTION_SUCURSALES
//GESTION_SUCURSALES//GESTION_SUCURSALES//GESTION_SUCURSALES//GESTION_SUCURSALES//GESTION_SUCURSALES//GESTION_SUCURSALES//GESTION_SUCURSALES
//GESTION_SUCURSALES//GESTION_SUCURSALES//GESTION_SUCURSALES//GESTION_SUCURSALES//GESTION_SUCURSALES//GESTION_SUCURSALES//GESTION_SUCURSALES
//GESTION_SUCURSALES//GESTION_SUCURSALES//GESTION_SUCURSALES//GESTION_SUCURSALES//GESTION_SUCURSALES//GESTION_SUCURSALES//GESTION_SUCURSALES
//GESTION_SUCURSALES//GESTION_SUCURSALES//GESTION_SUCURSALES//GESTION_SUCURSALES//GESTION_SUCURSALES//GESTION_SUCURSALES//GESTION_SUCURSALES

    public ArrayList<Centro_Logistico> getSucursales() {
      return sucursales;
    }

    public void eliminarCentroLogistico(Centro_Logistico elim){
        if(elim instanceof Sucursal){
            this.eliminarSucursal((Sucursal)elim);
        }
        else if(elim instanceof Puerto){
            this.eliminarPuerto((Puerto)elim);
        }
        else if(elim instanceof Centro){
            this.eliminarCentro((Centro)elim);
        }
        
    }

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
                aux.setId_logistico(rs.getInt("id_logistico"));
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
                aux2.setId_logistico(rs.getInt("id_logistico"));
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
                aux3.setId_logistico(rs.getInt("id_logistico"));
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

    public void cargarSucursal(Sucursal a){
        Connection conn = null;
        PreparedStatement tabla = null;
        ResultSet rs = null;
        if(!sucursales.contains(a)){
            try {
            Class.forName("org.postgresql.Driver");
            conn = DriverManager.getConnection("jdbc:postgresql://localhost/", "tpadmin", "tpadmindied");
            tabla = conn.prepareStatement("INSERT INTO tp.Centro_Logistico(nombre, estado, horario_apertura, horario_cierre) VALUES ('"+a.getNombre()+"','"+((a.getEstado()).toString())+"','"+a.getHorario_apertura()+"','"+a.getHorario_cierre()+"') RETURNING id_logistico");
            rs = tabla.executeQuery();
            rs.next();
            String id = rs.getString("id_logistico");
            tabla.close();
            tabla = conn.prepareStatement("INSERT INTO tp.Sucursal(id_sucursal) VALUES ("+id+")");
            tabla.executeUpdate();

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
            List<Ruta> rutas = this.rutas.stream()
              .filter(r -> (r.getSucursal_Origen().getId_logistico().equals(b.getId_logistico()) || r.getSucursal_Destino().getId_logistico().equals(b.getId_logistico())))
              .collect(Collectors.toList());
            rutas.forEach(r -> eliminarRuta(r));
            Class.forName("org.postgresql.Driver");
            conn = DriverManager.getConnection("jdbc:postgresql://localhost/", "tpadmin", "tpadmindied");
            tabla = conn.prepareStatement("DELETE FROM tp.Sucursal WHERE id_sucursal="+b.getId_logistico()+"");
            tabla.executeUpdate();
            tabla.close();
            tabla = conn.prepareStatement("DELETE FROM tp.Centro_Logistico WHERE id_logistico ="+b.getId_logistico()+"");
            tabla.executeUpdate();
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
            tabla = conn.prepareStatement("INSERT INTO tp.Centro_Logistico(nombre, estado, horario_apertura, horario_cierre) VALUES ('"+a.getNombre()+"','"+((a.getEstado()).toString())+"','"+a.getHorario_apertura()+"','"+a.getHorario_cierre()+"') RETURNING id_logistico");
            rs = tabla.executeQuery();
            rs.next();
            String id = rs.getString("id_logistico");
            tabla.close();
            tabla = conn.prepareStatement("INSERT INTO tp.Centro(id_sucursal) VALUES ("+id+")");
            tabla.executeUpdate();

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
            tabla = conn.prepareStatement("DELETE FROM tp.Centro WHERE id_centro="+b.getId_logistico());
            tabla.executeUpdate();
            tabla.close();
            tabla = conn.prepareStatement("DELETE FROM tp.Centro_Logistico WHERE id_logistico ="+b.getId_logistico());
            tabla.executeUpdate();
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
            tabla = conn.prepareStatement("INSERT INTO tp.Centro_Logistico(nombre, estado, horario_apertura, horario_cierre) VALUES ('"+a.getNombre()+"','"+((a.getEstado()).toString())+"','"+a.getHorario_apertura()+"','"+a.getHorario_cierre()+"') RETURNING id_logistico");
            rs = tabla.executeQuery();
            rs.next();
            String id = rs.getString("id_logistico");
            tabla.close();
            tabla = conn.prepareStatement("INSERT INTO tp.Puerto(id_puerto) VALUES ("+id+")");
            tabla.executeUpdate();

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
            tabla = conn.prepareStatement("DELETE FROM tp.Puerto WHERE id_puerto='"+b.getId_logistico()+"'");
            tabla.executeUpdate();
            tabla.close();
            tabla = conn.prepareStatement("DELETE FROM tp.Centro_Logistico WHERE id_logistico ="+b.getId_logistico());
            tabla.executeUpdate();
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

    private List<Centro_Logistico> getAdyacentes(Centro_Logistico a){
        return this.getRutas().stream()
                              .filter(c -> a.equals(c.getSucursal_Origen()))
                              .map(g -> g.getSucursal_Destino())
                              .collect(Collectors.toList());
    }

    public ArrayList<ArrayList<Centro_Logistico>> obtenerCaminos(Centro_Logistico origen, Centro_Logistico destino){
        //Usamos recorrido en profundidad
        boolean volver = true;
        List<Centro_Logistico> adyacentes;
        ArrayList<ArrayList<Centro_Logistico>> caminos = new ArrayList<ArrayList<Centro_Logistico>>();
        Stack<Centro_Logistico> paso = new Stack<>();
        Stack<Centro_Logistico> pendientes = new Stack<Centro_Logistico>();
        int nivel = 0, cont=0, camin=0;
        ArrayList<Integer> arr = new ArrayList<Integer>();
        pendientes.push(origen);
        arr.add(nivel, 0);

        while(!pendientes.isEmpty()){
            if(volver){
                Centro_Logistico actual = pendientes.pop();
                adyacentes = this.getAdyacentes(actual);
                paso.push(actual);
                arr.add(nivel+1,0);
                cont=0;
                if(actual.equals(destino)){
                    ArrayList<Centro_Logistico> camino = new ArrayList<>();
                    for(Centro_Logistico f : paso){
                        //System.out.print(f.getNombre()+" -> ");
                        camino.add(f);
                    }
                    caminos.add(camin, camino);
                    volver = false;
                    camin++;
                }
                else{
                    if(!adyacentes.isEmpty()){
                    for(Centro_Logistico v : adyacentes){
                        pendientes.push(v);
                        cont++;
                    }
                    nivel++;
                    arr.set(nivel, cont);
                    }
                    else{
                        volver=false;
                    }
                }
            }
            else{

                if(arr.get(nivel+1) > 0){
                    volver = true;
                    nivel++;
                }
                else{
                    paso.pop();
                    arr.set(nivel, arr.get(nivel)-1);
                    nivel--;
                }
            }
        }
        return caminos;
    }

    //Revisar si se queda
    private List<Centro_Logistico> getIncidentes(Centro_Logistico a){
        return this.getRutas().stream()
                              .filter(c -> a.equals(c.getSucursal_Destino()))
                              .map(d -> d.getSucursal_Destino())
                              .collect(Collectors.toList());
    }

    public double flujoMaximo(Centro_Logistico c){
        double maximoFlujo = 0;
        for(Centro_Logistico a : (sucursales.stream().filter(d -> d instanceof Puerto).collect(Collectors.toList())))
            for(ArrayList<Ruta> f : this.obtenerRutas(a, c)){
                maximoFlujo = f.get(0).getCapacidad();
                for(int i=0; i < f.size()-1; i++){
                    double cap = f.get(i).getCapacidad();
                    if(cap < maximoFlujo){
                        maximoFlujo = cap;
                    }
                }
        }
        return maximoFlujo;
    }

    public double flujoMaximo(Centro_Logistico c){
        double maximoFlujo = 0;
        for(Centro_Logistico a : (sucursales.stream().filter(d -> d instanceof Puerto).collect(Collectors.toList())))
            for(ArrayList<Ruta> f : this.obtenerRutas(a, c)){
                maximoFlujo = f.get(0).getCapacidad();
                for(int i=0; i < f.size()-1; i++){
                    double cap = f.get(i).getCapacidad();
                    if(cap < maximoFlujo){
                        maximoFlujo = cap;
                    }
                }
        }
        return maximoFlujo;
    }

    public double flujoMaximo(Centro_Logistico r, Centro_Logistico c){
        double maximoFlujo = 0;
        for(ArrayList<Ruta> f : this.obtenerRutas(r, c)){
            maximoFlujo = f.get(0).getCapacidad();
            for(Ruta k : f){
                double cap = k.getCapacidad();
                if(cap < maximoFlujo){
                    maximoFlujo = cap;
                }
            }
        }
        return maximoFlujo;
    }

    public Integer demoraTotal(Centro_Logistico r, Centro_Logistico c){
        Integer demoraTotal = 0;
        for(ArrayList<Ruta> f : this.obtenerRutas(r, c)){
            for(Ruta k : f){
                demoraTotal = demoraTotal+k.getDuracion();
            }
        }
        return demoraTotal;
    }

///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
///                                                                                                                             ///
///     Codigo obtenido con ayuda de chatGPT y adaptado por nosotros para el calculo del Page Rank de todas las sucursales      ///
///                                                                                                                             ///
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    private double[] pageRank(int[][] Mrelaciones, double dampingFactor, double tolerancia, int maxIterations, int tamL) {
        //int num7Pages = Mrelaciones.length;
        double[] pageRank = new double[tamL];
        Arrays.fill(pageRank, 1.0 / tamL);

        for (int iteration = 0; iteration < maxIterations; iteration++) {
            double[] newPageRank = new double[tamL];
            double teleportation = (1 - dampingFactor) / tamL;

            for (int i = 0; i < tamL; i++) {
                for (int j = 0; j < tamL; j++) {
                    if (Mrelaciones[j][i] == 1) {
                        newPageRank[i] += pageRank[j] / countOutboundLinks(Mrelaciones, j);
                    }
                }
                newPageRank[i] = dampingFactor * newPageRank[i] + teleportation;
            }

            if (isConverged(pageRank, newPageRank, tolerancia)) {
                break;
            }

            pageRank = newPageRank;
        }

        return pageRank;
    }

    private static int countOutboundLinks(int[][] Mrelaciones, int page) {
        int count = 0;
        for (int i = 0; i < Mrelaciones[page].length; i++) {
            count += Mrelaciones[page][i];
        }
        return count;
    }

    private static boolean isConverged(double[] pageRank, double[] newPageRank, double tolerancia) {
        for (int i = 0; i < pageRank.length; i++) {
            if (Math.abs(pageRank[i] - newPageRank[i]) > tolerancia) {
                return false;
            }
        }
        return true;
    }

///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
///     Fin del codigo obtenido con ayuda de chatGPT                                                                            ///
///                                                                                                                             ///
///     Inicio de la adaptacion                                                                                                 ///
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    private int dondeEsta(Centro_Logistico e){
        int cont = 0;
        for(Centro_Logistico g : this.getSucursales()){
            if(g.equals(e)){
                break;
            }
            cont++;
        }
        return cont;
    }

    public void calcularPageRank(){
        Connection conn = null;
        PreparedStatement tabla = null;
        ResultSet rs = null;
        int arr[][] = new int[100][100];
        for(Ruta r : this.getRutas()){
            int o = this.dondeEsta(r.getSucursal_Origen());
            int d = this.dondeEsta(r.getSucursal_Destino());
            arr[o][d] = 1;
        }
        int tamL = this.getSucursales().size(), cont=0;
        System.out.println(" "+tamL+" ");
        double pr[] = this.pageRank(arr, 0.15, 0.0001, 100, tamL);
        try{
            Class.forName("org.postgresql.Driver");
            conn = DriverManager.getConnection("jdbc:postgresql://localhost/", "tpadmin", "tpadmindied");
            for(Centro_Logistico c : this.getSucursales()){
                //System.out.print(" "+c.getNombre()+" -> ");
                //System.out.print(" "+pr[cont]+" -> ");
                c.setPageRank(pr[cont]);
                tabla = conn.prepareStatement("UPDATE tp.Centro_Logistico SET pageRank = "+pr[cont]+" WHERE id_logistico = "+c.getId_logistico());
                tabla.executeUpdate();
                tabla.close();
                cont++;
            }
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

///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
///     Fin de la adaptacion                                                                                                    ///
///                                                                                                                             ///
///                                                                                                                             ///
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    public Centro_Logistico consultarScursal_Id(Integer id){
        return this.sucursales.stream().filter(a -> id.equals(a.getId_logistico())).collect(Collectors.toList()).get(0);
    }

    public List<Centro_Logistico> filtrarScursal_Id(List<Centro_Logistico> lista, String id){
        return lista.stream().filter(a -> id.equals(a.getId_logistico().toString())).collect(Collectors.toList());
    }

    public List<Centro_Logistico> consultarScursales_Nombre(String nombre){
        return this.sucursales.stream().filter(a -> nombre.equals(a.getNombre())).collect(Collectors.toList());
    }

    public List<Centro_Logistico> filtrarScursales_Nombre(List<Centro_Logistico> lista, String nombre){
        return lista.stream().filter(a -> nombre.equals(a.getNombre())).collect(Collectors.toList());
    }

    public List<Centro_Logistico> consultarScursales_HorarioApertura(String h){
        return this.sucursales.stream().filter(a -> h.equals(a.getHorario_apertura())).collect(Collectors.toList());
    }

    public List<Centro_Logistico> filtrarScursales_HorarioApertura(List<Centro_Logistico> lista, String h){
        return lista.stream().filter(a -> h.equals(a.getHorario_apertura())).collect(Collectors.toList());
    }

    public List<Centro_Logistico> consultarScursales_HorarioCierre(String h){
        return this.sucursales.stream().filter(a -> h.equals(a.getHorario_cierre())).collect(Collectors.toList());
    }

    public List<Centro_Logistico> filtrarScursales_HorarioCierre(List<Centro_Logistico> lista, String h){
        return lista.stream().filter(a -> h.equals(a.getHorario_cierre())).collect(Collectors.toList());
    }

    public List<Centro_Logistico> consultarScursales_pageRank(double pr){
        return this.sucursales.stream().filter(a -> a.getPageRank() == pr).collect(Collectors.toList());
    }

    public List<Centro_Logistico> consultarScursales_Operativas(){
        return this.sucursales.stream().filter(a -> ESTADO_SUCURSAL.OPERATIVA.equals(a.getEstado())).collect(Collectors.toList());
    }

    public List<Centro_Logistico> filtrarScursales_Operativas(List<Centro_Logistico> lista){
        return lista.stream().filter(a -> ESTADO_SUCURSAL.OPERATIVA.equals(a.getEstado())).collect(Collectors.toList());
    }

    public List<Centro_Logistico> consultarScursales_NoOperativas(){
        return this.sucursales.stream().filter(a -> ESTADO_SUCURSAL.NO_OPERATIVA.equals(a.getEstado())).collect(Collectors.toList());
    }

    public List<Centro_Logistico> filtrarScursales_NoOperativas(List<Centro_Logistico> lista){
        return this.sucursales.stream().filter(a -> ESTADO_SUCURSAL.NO_OPERATIVA.equals(a.getEstado())).collect(Collectors.toList());
    }

    

//GESTION_RUTAS//GESTION_RUTAS//GESTION_RUTAS//GESTION_RUTAS//GESTION_RUTAS//GESTION_RUTAS//GESTION_RUTAS//GESTION_RUTAS//GESTION_RUTAS
//GESTION_RUTAS//GESTION_RUTAS//GESTION_RUTAS//GESTION_RUTAS//GESTION_RUTAS//GESTION_RUTAS//GESTION_RUTAS//GESTION_RUTAS//GESTION_RUTAS
//GESTION_RUTAS//GESTION_RUTAS//GESTION_RUTAS//GESTION_RUTAS//GESTION_RUTAS//GESTION_RUTAS//GESTION_RUTAS//GESTION_RUTAS//GESTION_RUTAS
//GESTION_RUTAS//GESTION_RUTAS//GESTION_RUTAS//GESTION_RUTAS//GESTION_RUTAS//GESTION_RUTAS//GESTION_RUTAS//GESTION_RUTAS//GESTION_RUTAS
//GESTION_RUTAS//GESTION_RUTAS//GESTION_RUTAS//GESTION_RUTAS//GESTION_RUTAS//GESTION_RUTAS//GESTION_RUTAS//GESTION_RUTAS//GESTION_RUTAS
//GESTION_RUTAS//GESTION_RUTAS//GESTION_RUTAS//GESTION_RUTAS//GESTION_RUTAS//GESTION_RUTAS//GESTION_RUTAS//GESTION_RUTAS//GESTION_RUTAS
//GESTION_RUTAS//GESTION_RUTAS//GESTION_RUTAS//GESTION_RUTAS//GESTION_RUTAS//GESTION_RUTAS//GESTION_RUTAS//GESTION_RUTAS//GESTION_RUTAS
//GESTION_RUTAS//GESTION_RUTAS//GESTION_RUTAS//GESTION_RUTAS//GESTION_RUTAS//GESTION_RUTAS//GESTION_RUTAS//GESTION_RUTAS//GESTION_RUTAS
//GESTION_RUTAS//GESTION_RUTAS//GESTION_RUTAS//GESTION_RUTAS//GESTION_RUTAS//GESTION_RUTAS//GESTION_RUTAS//GESTION_RUTAS//GESTION_RUTAS
//GESTION_RUTAS//GESTION_RUTAS//GESTION_RUTAS//GESTION_RUTAS//GESTION_RUTAS//GESTION_RUTAS//GESTION_RUTAS//GESTION_RUTAS//GESTION_RUTAS

    public ArrayList<Ruta> getRutas() {
      return rutas;
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
                aux.setId_ruta(rs.getInt("id_ruta"));
                Integer aux1=rs.getInt("sucursal_origen");
                Integer aux2=rs.getInt("sucursal_destino");
                aux.setSucursal_Origen((this.sucursales.stream()
                                                        .filter(a -> (aux1 == a.getId_logistico()))
                                                        .collect(Collectors.toList())).get(0));
                aux.setSucursal_Destino((this.sucursales.stream()
                                                        .filter(p -> (aux2 == p.getId_logistico()))
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
            tabla = conn.prepareStatement("INSERT INTO tp.Ruta(sucursal_origen, sucursal_destino, estado, capacidad, duracion) VALUES ("+r.getSucursal_Origen().getId_logistico()+","+r.getSucursal_Destino().getId_logistico()+",'"+((r.getEstado()).toString())+"',"+(r.getCapacidad().toString())+","+r.getDuracion().toString()+") RETURNING id_ruta");
            rs = tabla.executeQuery();
            
            r.setId_ruta(rs.getInt("id_ruta"));
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
            tabla = conn.prepareStatement("DELETE FROM tp.Ruta WHERE id_ruta ="+ r.getId_ruta());
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

    public Ruta consultarRuta_Id(Integer id){
        return this.rutas.stream().filter(a -> id.equals(a.getId_ruta())).collect(Collectors.toList()).get(0);
    }

    public List<Ruta> filtrarRuta_Id(List<Ruta> lista, Integer id){
        return lista.stream().filter(a -> id.equals(a.getId_ruta())).collect(Collectors.toList());
    }

    public List<Ruta> consultarRutas_sucursalOrigen(Centro_Logistico s){
        return this.rutas.stream().filter(a -> s.equals(a.getSucursal_Origen())).collect(Collectors.toList());
    }

    public List<Ruta> filtrarRutas_sucursalOrigen(List<Ruta> lista, Centro_Logistico s){
        return lista.stream().filter(a -> s.equals(a.getSucursal_Origen())).collect(Collectors.toList());
    }

    public List<Ruta> consultarRutas_sucursalDestino(Centro_Logistico s){
        return this.rutas.stream().filter(a -> s.equals(a.getSucursal_Destino())).collect(Collectors.toList());
    }

    public List<Ruta> filtrarRutas_sucursalDestino(List<Ruta> lista, Centro_Logistico s){
        return lista.stream().filter(a -> s.equals(a.getSucursal_Destino())).collect(Collectors.toList());
    }

    public List<Ruta> consultarRutas_capacidadExacta(double cap){
        return this.rutas.stream().filter(a -> a.getCapacidad() == cap).collect(Collectors.toList());
    }

    public List<Ruta> filtrarRutas_capacidadExacta(List<Ruta> lista, double cap){
        return lista.stream().filter(a -> a.getCapacidad() == cap).collect(Collectors.toList());
    }

    public List<Ruta> consultarRutas_capacidadMayorIgual(double cap){
        return this.rutas.stream().filter(a -> a.getCapacidad() >= cap).collect(Collectors.toList());
    }

    public List<Ruta> filtrarRutas_capacidadMayorIgual(List<Ruta> lista, double cap){
        return lista.stream().filter(a -> a.getCapacidad() >= cap).collect(Collectors.toList());
    }

    public List<Ruta> consultarRutas_capacidadMenor(double cap){
        return this.rutas.stream().filter(a -> a.getCapacidad() < cap).collect(Collectors.toList());
    }

    public List<Ruta> filtrarRutas_capacidadMenor(List<Ruta> lista, double cap){
        return lista.stream().filter(a -> a.getCapacidad() < cap).collect(Collectors.toList());
    }

    public List<Ruta> consultarRutas_duracionExacta(double dur){
        return this.rutas.stream().filter(a -> a.getDuracion() == dur).collect(Collectors.toList());
    }

    public List<Ruta> filtrarRutas_duracionExacta(List<Ruta> lista, double dur){
        return lista.stream().filter(a -> a.getDuracion() == dur).collect(Collectors.toList());
    }

    public List<Ruta> consultarRutas_duracionMayorIgual(double dur){
        return this.rutas.stream().filter(a -> a.getDuracion() >= dur).collect(Collectors.toList());
    }

    public List<Ruta> filtrarRutas_duracionMayorIgual(List<Ruta> lista, double dur){
        return lista.stream().filter(a -> a.getDuracion() >= dur).collect(Collectors.toList());
    }

    public List<Ruta> consultarRutas_duracionMenor(double dur){
        return this.rutas.stream().filter(a -> a.getDuracion() < dur).collect(Collectors.toList());
    }

    public List<Ruta> filtrarRutas_duracionMenor(List<Ruta> lista, double dur){
        return lista.stream().filter(a -> a.getDuracion() < dur).collect(Collectors.toList());
    }

    public List<Ruta> consultarRutas_Operativas(){
        return this.rutas.stream().filter(a -> ESTADO_RUTA.OPERATIVA.equals(a.getEstado())).collect(Collectors.toList());
    }

    public List<Ruta> filtrarRutas_Operativas(List<Ruta> lista){
        return lista.stream().filter(a -> ESTADO_RUTA.OPERATIVA.equals(a.getEstado())).collect(Collectors.toList());
    }

    public List<Ruta> consultarRutas_NoOperativas(){
        return this.rutas.stream().filter(a -> ESTADO_RUTA.NO_OPERATIVA.equals(a.getEstado())).collect(Collectors.toList());
    }

    public List<Ruta> filtrarRutas_NoOperativas(List<Ruta> lista){
        return lista.stream().filter(a -> ESTADO_RUTA.NO_OPERATIVA.equals(a.getEstado())).collect(Collectors.toList());
    }

    //REVISAR SI DEBE QUEDARSE
    public Ruta encontrarRuta(Centro_Logistico origen, Centro_Logistico destino){
        return this.getRutas().stream()
                              .filter(a -> origen.equals(a.getSucursal_Origen()) && destino.equals(a.getSucursal_Destino()))
                              .collect(Collectors.toList())
                              .get(0);
    }

    private List<Ruta> getRutasAdyacentes(Centro_Logistico a){
        return this.getRutas().stream()
                              .filter(c -> a.equals(c.getSucursal_Origen()))
                              .collect(Collectors.toList());
    }

    public ArrayList<ArrayList<Ruta>> obtenerRutas(Centro_Logistico origen, Centro_Logistico destino){
        //Usamos recorrido en profundidad
        boolean volver = true;
        List<Centro_Logistico> adyacentes;
        ArrayList<ArrayList<Centro_Logistico>> caminos = new ArrayList<ArrayList<Centro_Logistico>>();
        Stack<Centro_Logistico> paso = new Stack<>();
        Stack<Centro_Logistico> pendientes = new Stack<Centro_Logistico>();

        List<Ruta> rutasAdyacentes;
        ArrayList<ArrayList<Ruta>> rutas = new ArrayList<ArrayList<Ruta>>();
        Stack<Ruta> pasoR = new Stack<>();
        Stack<Ruta> rutasPendientes = new Stack<Ruta>();

        int nivel = 0, cont=0, camin=0;
        ArrayList<Integer> arr = new ArrayList<Integer>();
        pendientes.push(origen);
        rutasPendientes.push(null);

        arr.add(nivel, 0);
        while(!pendientes.isEmpty()){
            if(volver){
                Centro_Logistico actual = pendientes.pop();
                adyacentes = this.getAdyacentes(actual);
                Ruta rutaActual = rutasPendientes.pop();
                rutasAdyacentes = this.getRutasAdyacentes(actual);
                pasoR.push(rutaActual);
                paso.push(actual);
                arr.add(nivel+1,0);
                cont=0;
                if(actual.equals(destino)){
                    ArrayList<Ruta> ruta = new ArrayList<>();
                    int r=0;
                    for(Ruta f : pasoR){
                        //System.out.print(f.getNombre()+" -> ");
                        if(r>0)ruta.add(f);
                        r++;
                    }
                    rutas.add(camin, ruta);
                    volver = false;
                    camin++;
                }
                else{
                    if(!adyacentes.isEmpty()){
                    for(Centro_Logistico v : adyacentes){
                        pendientes.push(v);
                        cont++;
                    }
                    for(Ruta b : rutasAdyacentes){
                        rutasPendientes.push(b);
                    }
                    nivel++;
                    arr.set(nivel, cont);
                    }
                    else{
                        volver=false;
                    }
                }
            }
            else{

                if(arr.get(nivel+1) > 0){
                    volver = true;
                    nivel++;
                }
                else{
                    paso.pop();
                    pasoR.pop();
                    arr.set(nivel, arr.get(nivel)-1);
                    nivel--;
                }
            }
        }
        return rutas;
    }
    
    public Grafo() {
      this.cargarSucursales();
      this.cargarRutas();
    }
}

