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
            tabla = conn.prepareStatement("INSERT INTO tp.Centro_Logistico(id_logistico, nombre, estado, horario_apertura, horario_cierre) VALUES ('"+a.getId_logistico()+"','"+a.getNombre()+"','"+((a.getEstado()).toString())+"','"+a.getHorario_apertura()+"','"+a.getHorario_cierre()+"')");
            tabla.executeUpdate();
            tabla.close();
            tabla = conn.prepareStatement("INSERT INTO tp.Sucursal(id_centro) VALUES ('"+a.getId_logistico()+"')");
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
            tabla = conn.prepareStatement("DELETE FROM tp.Sucursal WHERE id_sucursal='"+b.getId_logistico()+"'");
            tabla.executeUpdate();
            tabla.close();
            tabla = conn.prepareStatement("DELETE FROM tp.Centro_Logistico WHERE id_logistico ='"+b.getId_logistico()+"'");
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
            tabla = conn.prepareStatement("INSERT INTO tp.Centro_Logistico(id_logistico, nombre, estado, horario_apertura, horario_cierre) VALUES ('"+a.getId_logistico()+"','"+a.getNombre()+"','"+((a.getEstado()).toString())+"','"+a.getHorario_apertura()+"','"+a.getHorario_cierre()+"')");
            tabla.executeUpdate();
            tabla.close();
            tabla = conn.prepareStatement("INSERT INTO tp.Centro(id_centro) VALUES ('"+a.getId_logistico()+"')");
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
            tabla = conn.prepareStatement("DELETE FROM tp.Centro WHERE id_centro='"+b.getId_logistico()+"'");
            tabla.executeUpdate();
            tabla.close();
            tabla = conn.prepareStatement("DELETE FROM tp.Centro_Logistico WHERE id_logistico ='"+b.getId_logistico()+"'");
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
            tabla = conn.prepareStatement("INSERT INTO tp.Centro_Logistico(id_logistico, nombre, estado, horario_apertura, horario_cierre) VALUES ('"+a.getId_logistico()+"','"+a.getNombre()+"','"+((a.getEstado()).toString())+"','"+a.getHorario_apertura()+"','"+a.getHorario_cierre()+"')");
            tabla.executeUpdate();
            tabla.close();
            tabla = conn.prepareStatement("INSERT INTO tp.Puerto(id_puerto) VALUES ('"+a.getId_logistico()+"')");
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
            tabla = conn.prepareStatement("DELETE FROM tp.Centro_Logistico WHERE id_logistico ='"+b.getId_logistico()+"'");
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

    public ArrayList<Ruta> getRutas() {
      return rutas;
    }

    public ArrayList<Centro_Logistico> getSucursales() {
      return sucursales;
    }

    private List<Centro_Logistico> getAdyacentes(Centro_Logistico a){
        return this.getRutas().stream()
                              .filter(c -> a.equals(c.getSucursal_Origen()))
                              .map(g -> g.getSucursal_Destino())
                              .collect(Collectors.toList());
    }

    public Centro_Logistico consultarScursal_Id(String id){
        return this.sucursales.stream().filter(a -> id.equals(a.getId_logistico())).collect(Collectors.toList()).get(0);
    }

    public Centro_Logistico consultarScursal_Nombre(String nombre){
        return this.sucursales.stream().filter(a -> nombre.equals(a.getNombre())).collect(Collectors.toList()).get(0);
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

        while(!pendientes.isEmpty()){
            if(volver){
                Centro_Logistico actual = pendientes.pop();
                adyacentes = this.getAdyacentes(actual);
                paso.push(actual);
                arr.add(nivel, 0);
                cont=0;
                if(actual.equals(destino)){
                    ArrayList<Centro_Logistico> camino = new ArrayList<>();
                    for(Centro_Logistico f : paso){
                        //System.out.print(f.getNombre()+" -> ");
                        camino.add(f);
                    }
                    caminos.add(camin, camino);
                    //System.out.println("");
                    paso.pop();
                    //arr.set(nivel-1, 0);
                    //nivel--;
                    arr.set(nivel-1, arr.get(nivel-1)-1);
                    volver = false;
                    camin++;
                }
                else if(!adyacentes.isEmpty() && volver){
                    for(Centro_Logistico v : adyacentes){
                        pendientes.push(v);
                        cont++;
                        arr.set(nivel, cont);
                    }
                    nivel++;
                }

            }
            else{
                if(arr.get(nivel-1) > 0){
                    //System.out.print(" arrI: "+arr.get(nivel-1));
                    //arr.set(nivel-1, (arr.get(nivel-1)-1));
                    volver = true;
                    //System.out.print(" arrF: "+arr.get(nivel-1));
                }
                else{
                    paso.pop();
                    //System.out.print(" arrI: "+arr.get(nivel-1));
                    //arr.set(nivel-1, 0);
                    //System.out.print(" arrF: "+arr.get(nivel-1));
                    nivel--;
                    arr.set(nivel-1, arr.get(nivel-1)-1);
                    //if(arr.get(nivel-1) > 0){
                    //    volver = true;
                    //}
                }
            }
        }
        return caminos;
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
        //ArrayList<ArrayList<Centro_Logistico>> caminos = new ArrayList<ArrayList<Centro_Logistico>>();
        Stack<Centro_Logistico> paso = new Stack<>();

        List<Ruta> rutasAdyacentes;
        ArrayList<ArrayList<Ruta>> rutas = new ArrayList<ArrayList<Ruta>>();
        Stack<Ruta> pasoR = new Stack<>();

        Stack<Ruta> rutasPendientes = new Stack<Ruta>();

        Stack<Centro_Logistico> pendientes = new Stack<Centro_Logistico>();
        int nivel = 0, cont=0, camin=0;
        ArrayList<Integer> arr = new ArrayList<Integer>();
        pendientes.push(origen);
        rutasPendientes.push(null);

        while(!pendientes.isEmpty()){
            if(volver){
                Centro_Logistico actual = pendientes.pop();
                Ruta rutaActual = rutasPendientes.pop();
                adyacentes = this.getAdyacentes(actual);
                rutasAdyacentes = this.getRutasAdyacentes(actual);
                paso.push(actual);
                pasoR.push(rutaActual);
                arr.add(nivel, 0);
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
                    //System.out.println("");
                    paso.pop();
                    pasoR.pop();
                    //arr.set(nivel-1, 0);
                    //nivel--;
                    arr.set(nivel-1, arr.get(nivel-1)-1);
                    volver = false;
                    camin++;
                }
                else if(!adyacentes.isEmpty() && volver){
                    for(Centro_Logistico v : adyacentes){
                        pendientes.push(v);
                        cont++;
                        arr.set(nivel, cont);
                    }
                    for(Ruta b : rutasAdyacentes){
                        rutasPendientes.push(b);
                    }
                    nivel++;
                }

            }
            else{
                if(arr.get(nivel-1) > 0){
                    //System.out.print(" arrI: "+arr.get(nivel-1));
                    //arr.set(nivel-1, (arr.get(nivel-1)-1));
                    volver = true;
                    //System.out.print(" arrF: "+arr.get(nivel-1));
                }
                else{
                    paso.pop();
                    pasoR.pop();
                    //System.out.print(" arrI: "+arr.get(nivel-1));
                    //arr.set(nivel-1, 0);
                    //System.out.print(" arrF: "+arr.get(nivel-1));
                    nivel--;
                    arr.set(nivel-1, arr.get(nivel-1)-1);
                    //if(arr.get(nivel-1) > 0){
                    //    volver = true;
                    //}
                }
            }
        }
        return rutas;
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
                tabla = conn.prepareStatement("UPDATE tp.Centro_Logistico SET pageRank = "+pr[cont]+" WHERE id_logistico = '"+c.getId_logistico()+"'");
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

}

