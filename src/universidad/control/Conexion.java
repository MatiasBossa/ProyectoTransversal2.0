/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package universidad.control;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author HP
 */
public class Conexion {
    
    private static final String JDBC_DRIVER = "org.mariadb.jdbc.Driver"; 
    private static final String JDBC_HOST = "localhost:3306";
    private static final String JDBC_DB = "universidad";
    private static final String JDBC_URL = "jdbc:mysql://" + JDBC_HOST + "/" + JDBC_DB;
    private static final String JDBC_USER = "root";
    private static final String JDBC_PASS = "";
    private static Driver driver = null;
    
    public static synchronized Connection getConnection()throws SQLException{
        if(driver == null){
            try{
                Class jdbcDriverClass = Class.forName(JDBC_DRIVER);
                driver = (Driver) jdbcDriverClass.newInstance();
                DriverManager.registerDriver(driver);
            }catch(Exception e){
                System.out.println("Error al conectar");
                e.printStackTrace();
            }
        }
     return DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASS);
    }
    
    public static void close(ResultSet rs){
        try{
            if(rs != null){
                rs.close();
            }
        }catch (SQLException e){
            e.printStackTrace();
        }       
    }
    
    public static void close(PreparedStatement ps){
        try{
            if(ps != null){
                ps.close();
            }
        }catch (SQLException e){
            e.printStackTrace();
        }       
    }
    
    public static void close(Connection c){
        try{
            if(c != null){
                c.close();
            }
        }catch (SQLException e){
            e.printStackTrace();
        }       
    }
    
    
    
}
