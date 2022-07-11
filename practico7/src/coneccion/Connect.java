/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package coneccion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Connect {
    private static Connection conn;
    private static final String DRIVER = "org.mariadb.jdbc.Driver";
    private static final String USER = "root";
    private static final String PASS = "";
    private static final String db = "pro3abm";
    private static final String URL = "jdbc:mariadb://localhost:3306/"+db;
    
    public Connect(){
        conn = null;
        try{
            Class.forName(DRIVER);
            conn = DriverManager.getConnection(URL, USER, PASS);
            
            // Connect?
            if(conn != null)
                System.out.println("Conexión establecida exitosamente");
        }catch (ClassNotFoundException | SQLException ex){
            System.out.println("Conexión Fallida:\n\n"+ex);
        }
    }
    public static Connection getConnection() {
        try {
            Class.forName(DRIVER);
            
            conn = DriverManager.getConnection(URL, USER, PASS);
            if(conn != null)
                System.out.println("Conexión establecida exitosamente");
            return conn;
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
