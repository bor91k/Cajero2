
package tpboris;

import excepciones.ErrorDatabaseException;
import excepciones.NoTieneSuficienteException;
import excepciones.UsuarioNoExisteException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Usuario {
    
    private final int id;
    
    private Usuario(int id){
        this.id=id;
    }
   
    public static Usuario validarCredenciales(int nroCuenta, int nroNip) throws UsuarioNoExisteException, ErrorDatabaseException{
        //este metodo crea usuarios en caso de que las credenciales que se pasan son correctas (es la unica manera de instanciar Usuarios)

        Usuario user = null;
        Connection conexion = null;
        Statement stmtConsulta = null;
        
        try {
            
            conexion = AdministradorDeConexiones.obtenerConexion();
            String consulta = "SELECT * FROM usuario WHERE nroCuenta="+nroCuenta+" AND nroNip="+nroNip;
            stmtConsulta = conexion.createStatement();
            ResultSet rs = stmtConsulta.executeQuery(consulta);

            if (rs.next()){
                int id = rs.getInt("id");
                System.out.println("Usuario con esas credenciales. ID: "+id);//sacar
                user = new Usuario(id);
            }else{
                System.out.println("No hay usuario con esas credenciales");//sacar
                throw new UsuarioNoExisteException();
            }
            
        } catch (ReflectiveOperationException | SQLException ex) {

            System.out.println("ErrorDatabaseException 1");//sacar
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

                System.out.println("ErrorDatabaseException 2");//sacar
                throw new ErrorDatabaseException();
            }         
        }
        return user;
    }
    
    public double solicitarSaldo() throws ErrorDatabaseException{
    
        double saldo = -1;
        Connection conexion = null;
        Statement stmtConsulta = null;
                
        try {
            
            conexion = AdministradorDeConexiones.obtenerConexion();
            String consulta = "SELECT * FROM usuario WHERE id="+this.id;
            stmtConsulta = conexion.createStatement();
            ResultSet rs = stmtConsulta.executeQuery(consulta);

            if (rs.next()){
                saldo = rs.getDouble("saldo");
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
        return saldo;
    }
    
    //Anterior metodo para opcion 3 extraer efectivo
    /*public void retirarEfectivo(double saldoUpdate){
        
            //conectarme
            Connection conexion = null;
            Statement stmtUpdate = null;
                
            try {
                conexion = AdministradorDeConexiones.obtenerConexion();
                
                //hago una trasaccion porque hay que cambiar la cuenta del usuario y el contenido del cajero a la vez
                conexion.setAutoCommit(false);

                String update = "UPDATE usuario SET saldo="+saldoUpdate+" WHERE id = "+id;
                stmtUpdate = conexion.createStatement();
                stmtUpdate.execute(update);

            } catch (ReflectiveOperationException | SQLException ex) {

                try {
                    conexion.rollback();
                } catch (SQLException ex1) {
                    Logger.getLogger(Usuario.class.getName()).log(Level.SEVERE, null, ex1);
                    throw new NoSePuedeException();
                }
                Logger.getLogger(Usuario.class.getName()).log(Level.SEVERE, null, ex);
                throw new NoSePuedeException();

            }finally{
                //cerrar
                try {

                    if(conexion != null){
                        conexion.close();
                    }
                    if(stmtUpdate != null){
                        stmtUpdate.close();
                    }

                } catch (SQLException ex) {
                    Logger.getLogger(Usuario.class.getName()).log(Level.SEVERE, null, ex);
                    throw new NoSePuedeException();
                }
            }
    }*/
        
    public void setSaldo(double saldoUpdate) throws ErrorDatabaseException{
        
            //conectarme
            Connection conexion = null;
            Statement stmtUpdate = null;
                
            try {
                conexion = AdministradorDeConexiones.obtenerConexion();
                String update = "UPDATE usuario SET saldo="+saldoUpdate+" WHERE id = "+id;
                stmtUpdate = conexion.createStatement();
                stmtUpdate.execute(update);

            } catch (ReflectiveOperationException | SQLException ex) {
                throw new ErrorDatabaseException();
            }finally{
                //cerrar
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
    
    public boolean tieneSuficiente(double montoSolicitado) throws NoTieneSuficienteException{
        
        if (montoSolicitado>solicitarSaldo()){
            throw new NoTieneSuficienteException();
        }
        return montoSolicitado<=solicitarSaldo();
    }
  /*  
    private static class NoTieneSuficienteException extends RuntimeException {
        
        private final String mensaje;

        public NoTieneSuficienteException() {
            this.mensaje = "No tiene suficiente saldo en su cuenta, pruebe con un monto menor";
        }

        @Override
        public String getMessage() {
            return mensaje;
        }
    }
    */
    
/*
    public static class NoSePuedeException extends RuntimeException {
        
        private final String mensaje;

        public NoSePuedeException() {
            this.mensaje = "No se puede realizar esta accion, intente de nuevo";
        }
        
        @Override
        public String getMessage() {
            return mensaje;
        }
    }
*/
    /*
    public static class UsuarioNoExisteException extends RuntimeException {
        
        private String mensaje;

        public UsuarioNoExisteException() {
            this.mensaje = "Error, intente de nuevo.";
        }

        @Override
        public String getMessage() {
            return mensaje;
        }
        
        
    }
*/
}