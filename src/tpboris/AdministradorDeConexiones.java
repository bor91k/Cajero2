package tpboris;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public abstract class AdministradorDeConexiones {
    
    public static Connection obtenerConexion() throws ReflectiveOperationException, SQLException {
        
        String dbDriver = "com.mysql.jdbc.Driver";
        String dbConnString = "jdbc:mysql://localhost:3306/bdbanco";
        String dbUser = "root";
        String dbPass = "";
        Class.forName(dbDriver).newInstance();
            
        return DriverManager.getConnection(dbConnString, dbUser, dbPass);
    }
}