
package Conexion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

public class Conectar {

    private static final String DRIVER = "com.mysql.cj.jdbc.Driver";
    private static final String USER = "root";
    private static final String PASS = "";
    private static final String db = "pro3abm";
    private static final String URL = "jdbc:mysql://localhost/"+db+"";
    
    
    public static Connection conexion = null;
    
    //Este metodo realiza la conexión con la base de datos H2
    public Conectar() {
        
        try {
           conexion = null;
           //cargar nuestro driver
           Class.forName(DRIVER);
           conexion =DriverManager.getConnection(URL,USER,PASS);
           System.out.println("conexion establecida");
           conexion.close();
       } catch (ClassNotFoundException | SQLException e) {
           System.out.println("error de conexion");
           JOptionPane.showMessageDialog(null, "error de conexion "+e);
       }
         
     }
    public static Connection getConnection() {
        try {
            Class.forName(DRIVER);
            
            conexion = DriverManager.getConnection(URL, USER, PASS);
            if(conexion != null)
                System.out.println("Conexión establecida exitosamente");
            return conexion;
        } catch (ClassNotFoundException | SQLException ex) {
            throw new RuntimeException("Erro na conexão: ", ex);
        }
    }

    public static void closeConnection(Connection con) {
        try {
            if (con != null) {
                con.close();
            }
        } catch (SQLException ex) {
            Logger.getLogger(Connect.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void closeConnection(Connection con, PreparedStatement stmt) {

        closeConnection(con);

        try {

            if (stmt != null) {
                stmt.close();
            }

        } catch (SQLException ex) {
            Logger.getLogger(Connect.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void closeConnection(Connection con, PreparedStatement stmt, ResultSet rs) {

        closeConnection(con, stmt);

        try {

            if (rs != null) {
                rs.close();
            }

        } catch (SQLException ex) {
            Logger.getLogger(Connect.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}

