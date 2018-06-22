
package tpboris;

import excepciones.EfectivoInsuficienteException;
import excepciones.ErrorDatabaseException;
import excepciones.NoMultiploVeinteException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author BOR
 */
public abstract class Cajero {
    
    public static double obtenerEfectivoDisponible() throws ErrorDatabaseException{
        
        double stock=-1;

        Connection conexion = null;
        Statement stmtConsulta = null;
                
        try {
            
            conexion = AdministradorDeConexiones.obtenerConexion();
            String consulta = "SELECT * FROM cajero WHERE id=1";
            stmtConsulta = conexion.createStatement();
            ResultSet rs = stmtConsulta.executeQuery(consulta);
            
            if (rs.next()){
                stock = rs.getDouble("efectivo");
            }else{
                throw new ErrorDatabaseException();
            }
            
        } catch (ReflectiveOperationException | SQLException ex) {
            
            throw new ErrorDatabaseException();
            
        }finally{
            
            try {
                
                if(conexion != null){
                    conexion.close();
                }
                if(stmtConsulta != null){
                    stmtConsulta.close();
                }

            } catch (SQLException ex) {
                throw new ErrorDatabaseException();
            }         
        }
        
        return stock;
    }
    
    
    //ANTERIOR METODO PARA opcion 3 del menu
    /*static void extraer(double efectivo) {

        Connection conexion = null;
        Statement stmtUpdate = null;
        double efectivoUpdate=obtenerEfectivoDisponible()-efectivo;
                
        try {
            
            conexion = AdministradorDeConexiones.obtenerConexion();
            String update = "UPDATE cajero SET efectivo="+efectivoUpdate+" WHERE id =1";
            stmtUpdate = conexion.createStatement();
            stmtUpdate.execute(update);
            
            System.out.println( "Se extrajo efectivo del CAJERO! ");//sacar
            
        } catch (ReflectiveOperationException | SQLException ex) {
            
            Logger.getLogger(Usuario.class.getName()).log(Level.SEVERE, null, ex);
            
        }finally{
            
            try {
                
                if(conexion != null){
                    conexion.close();
                }
                if(stmtUpdate != null){
                    stmtUpdate.close();
                }

            } catch (SQLException ex) {
                Logger.getLogger(Usuario.class.getName()).log(Level.SEVERE, null, ex);
            }         
        }
    }*/

    public static void setEfectivo(double efectivoUpdate) throws excepciones.ErrorDatabaseException {
        
        Connection conexion = null;
        Statement stmtUpdate = null;
                
        try {
            
            conexion = AdministradorDeConexiones.obtenerConexion();
            String update = "UPDATE cajero SET efectivo="+efectivoUpdate+" WHERE id =1";
            stmtUpdate = conexion.createStatement();
            stmtUpdate.execute(update);
            
            System.out.println( "Cambio el efectivo del CAJERO! stock final "+efectivoUpdate);//sacar
            
        } catch (ReflectiveOperationException | SQLException ex) {
            
            throw new ErrorDatabaseException();
            
        }finally{
            
            try {
                
                if(conexion != null){
                    conexion.close();
                }
                if(stmtUpdate != null){
                    stmtUpdate.close();
                }

            } catch (SQLException ex) {
                throw new ErrorDatabaseException();
            }         
        }
    }
    
    public static boolean validarEfectivoCajero(double montoSolicitado) throws EfectivoInsuficienteException, NoMultiploVeinteException{
        //las validaciones propias del cajero en cuestion:
        //que haya suficiente plata en el 
        //que el monto sea multiplo de 20
        return haySuficiente(montoSolicitado) && esMultiploVeinte(montoSolicitado);
    }
    
    public static boolean haySuficiente(double montoSolicitado) throws EfectivoInsuficienteException {
        //que haya suficiente plata en el cajero
        if (montoSolicitado>obtenerEfectivoDisponible()){
            throw new EfectivoInsuficienteException();
        }
        return montoSolicitado<=obtenerEfectivoDisponible();
    }
    
    public static boolean esMultiploVeinte(double efectivo) throws NoMultiploVeinteException {
        //ve si el efectivo es multiplo de 20
        if (efectivo%20!=0){
            throw new NoMultiploVeinteException();
        }
        return efectivo%20==0;
    }

    /*
    private static class EfectivoInsuficienteException extends RuntimeException {
        
        private final String mensaje;

        public EfectivoInsuficienteException() {
            this.mensaje = "El cajero no tiene suficiente dinero";  
        }

        @Override
        public String getMessage() {
            return this.mensaje;
        } 
    }
*/

    /*
    private static class NoMultiploVeinteException extends RuntimeException {
        
        private final String mensaje;

        public NoMultiploVeinteException() {
            this.mensaje = "El cajero solo maneja billetes de 20, elija un monto multiplo de 20";
        }
        
        @Override
        public String getMessage() {
            return this.mensaje;
        }
    }
*/
   
}