/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package universidad.control;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import universidad.modelo.Alumno;



/**
 *
 * @author HP
 */
public class AlumnoData {
    
    private Connection userConn; 
    
    private final String SQL_INSERT = "INSERT INTO alumno(nombre, fecNac, activo) VALUES (?, ?, ?)";
    
    private final String SQL_UPDATE = "UPDATE alumno SET nombre = ?, fecNac = ?, activo = ? WHERE id_alumno = ?";
    
    private final String SQL_DELETE = "DELETE FROM alumno WHERE id_persona = ?";
    
    private final String SQL_SELECT_ALL = "SELECT * FROM alumno";
    
    private final String SQL_SELECT = "SELECT * FROM alumno WHERE id_alumno = ?";


    public int guardarAlumno(Alumno alumno) {
        Connection conn = null;
        PreparedStatement ps = null;
        int rows = 0;
        try {
            conn = (this.userConn != null) ? this.userConn : Conexion.getConnection();
            ps = conn.prepareStatement(SQL_INSERT);
            ps.setString(1, alumno.getNombre());
            ps.setDate(2, Date.valueOf( alumno.getFecNac()) );
            ps.setBoolean(3, alumno.getActivo());
            
            rows = ps.executeUpdate();
            
            System.out.println("Registros afectados: "+ rows);
            
        }catch (SQLException e) {
            System.out.println("Error al insertar Alumno");
        }finally{
            Conexion.close(ps);
            if(this.userConn == null){
                Conexion.close(conn);
            }
        }
        
        return rows;
    }
    
    public List<Alumno> obtenerAlumnos() {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        Alumno alumno = null;
        List<Alumno> listaAlumnos = new ArrayList<>();
        try {
            conn = (this.userConn != null) ? this.userConn : Conexion.getConnection();
            ps = conn.prepareStatement(SQL_SELECT_ALL);
            rs = ps.executeQuery();
            
            while (rs.next()) {
                alumno = new Alumno();
                alumno.setId(rs.getInt("id"));
                alumno.setNombre(rs.getString("nombre"));
                alumno.setFecNac(rs.getDate("fecNac").toLocalDate());
                alumno.setActivo(rs.getBoolean("activo"));
                
                listaAlumnos.add(alumno);
            }
        } catch (SQLException e) {
            System.out.println("ERROR al obtener los alumnos.");
            e.printStackTrace();
        }finally{
            Conexion.close(rs);
            Conexion.close(ps);
            if(this.userConn == null){
                Conexion.close(conn);
            }
        }
        
        return listaAlumnos;
    }
    
    public int borrarAlumno(int id) {
        Connection conn = null;
        PreparedStatement ps = null;
        int rows = 0;
        try {
            conn = (this.userConn != null) ? this.userConn : Conexion.getConnection();
            ps = conn.prepareStatement(SQL_DELETE);
            ps.setInt(1, id);
            
            rows = ps.executeUpdate();
            
            System.out.println("Registros afectados: "+ rows);
            
        }catch (SQLException e) {
            System.out.println("ERROR al eliminar el alumno");
            e.printStackTrace();
        }finally{
            Conexion.close(ps);
            if(this.userConn == null){
                Conexion.close(conn);
            }
        }
        
        return rows;
    }
    
    public int actualizarAlumno(Alumno alumno) {
        Connection conn = null;
        PreparedStatement ps = null;
        int rows = 0;
        try {
            conn = (this.userConn != null) ? this.userConn : Conexion.getConnection();
            ps = conn.prepareStatement(SQL_UPDATE);
            ps.setString(1, alumno.getNombre());
            ps.setDate(2, Date.valueOf(alumno.getFecNac()));
            ps.setBoolean(3, alumno.getActivo());
            ps.setInt(4, alumno.getId());
            
            rows = ps.executeUpdate();
            
            System.out.println("Registros afectados: "+ rows);
            
        } catch (SQLException e) {
            System.out.println("Error al modificar el Alumno");
            e.printStackTrace();
        }finally{
            Conexion.close(ps);
            if(this.userConn == null){
                Conexion.close(conn);
            }
        }
        return rows;
    }
    
    public Alumno buscarAlumno(int id) {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        Alumno alumno = null;
        try {
            conn = (this.userConn != null) ? this.userConn : Conexion.getConnection();
            ps = conn.prepareStatement(SQL_SELECT);
            ps.setInt(1, id);
            
            rs = ps.executeQuery();
            
            if (rs.next()) {
                alumno = new Alumno();
                alumno.setId(rs.getInt("id"));
                alumno.setNombre(rs.getString("nombre"));
                alumno.setFecNac(rs.getDate("fecNac").toLocalDate());
                alumno.setActivo(rs.getBoolean("activo"));
            }
              
        
        } catch (SQLException e) {
            System.out.println("ERROR al encontrar el alumno");
            e.printStackTrace();
        }finally{
            Conexion.close(rs);
            Conexion.close(ps);
            if(this.userConn == null){
                Conexion.close(conn);
            }
        }
         return alumno;
    }
    
    
}
