/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package universidad.control;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import universidad.modelo.Materia;



/**
 *
 * @author Matias Bossa Vavlerde
 */

public class MateriaData {
    
    private Connection userConn;
    
    private final String SQL_INSERT = "INSERT INTO materia(nombre) VALUES (?)";
    
    private final String SQL_UPDATE = "UPDATE materia SET nombre = ? WHERE id_materia = ?";
    
    private final String SQL_DELETE = "DELETE FROM materia WHERE id_materia = ?";
    
    private final String SQL_SELECT_ALL = "SELECT * FROM materia";
    
    private final String SQL_SELECT = "SELECT * FROM materia WHERE id_alumno = ?";

   
    public int guardarMateria(Materia materia) {
        Connection conn = null;
        PreparedStatement ps = null;
        int rows = 0;
        try {
            conn = (this.userConn != null) ? this.userConn : Conexion.getConnection();
            ps = conn.prepareStatement(SQL_INSERT);
            ps.setString(1, materia.getNombre());
            
            rows = ps.executeUpdate();
            
            System.out.println("Registros afectados: "+ rows);
            
        }catch (SQLException e) {
            System.out.println("ERROR al agregar materia");
            e.printStackTrace();
        }finally{
            Conexion.close(ps);
            if(this.userConn == null){
                Conexion.close(conn);
            }
        }
        
        return rows;
    }
    
    public List<Materia> obtenerMaterias() {
        List<Materia> listaMaterias = new ArrayList<Materia>();
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            conn = (this.userConn != null) ? this.userConn : Conexion.getConnection();
            ps = conn.prepareStatement(SQL_SELECT_ALL);
            rs = ps.executeQuery();
            Materia materia = null;
            while (rs.next()) {
                materia = new Materia();
                materia.setId(rs.getInt("id"));
                materia.setNombre(rs.getString("nombre"));
                
                listaMaterias.add(materia);
            }
            
        }catch (SQLException e) {
            System.out.println("ERROR al obtener las materias");
            e.printStackTrace();
        }finally{
            Conexion.close(rs);
            Conexion.close(ps);
            if(this.userConn == null){
                Conexion.close(conn);
            }
            
        }
        return listaMaterias;
    }
    
    public Materia buscarMateria(int id){
        Materia materia = null;
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try{
            conn = (this.userConn != null) ? this.userConn : Conexion.getConnection();
            ps = conn.prepareStatement(SQL_SELECT);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            
            if(rs.next()){
                materia.setId(rs.getInt("id_materia"));
                materia.setNombre(rs.getString("nombre"));
            }
            
        }catch(SQLException e){
            System.out.println("ERROR al buscar la materia");
            e.printStackTrace();
        }finally{
            Conexion.close(rs);
            Conexion.close(ps);
            if(this.userConn == null){
                Conexion.close(conn);
            }
        }
        
        return materia;
    }
    
    public int borrarMateria(int id){
        Connection conn = null;
        PreparedStatement ps = null;
        int rows = 0;
        try{
            conn = (this.userConn != null) ? this.userConn : Conexion.getConnection();
            ps = conn.prepareStatement(SQL_DELETE);
            rows = ps.executeUpdate();
            ps.setInt(1, id);
            System.out.println("Registros afectados: "+ rows);
            
        }catch(SQLException e){
            System.out.println("ERROR al eliminar la materia");
            e.printStackTrace();
        }finally{
            Conexion.close(ps);
            if(this.userConn == null){
                Conexion.close(conn);           
            }
        }
        return rows;
    }
    
    public int actualizarMateria(Materia materia){
        Connection conn = null;
        PreparedStatement ps = null;
        int rows = 0;
        try{
            conn = (this.userConn != null) ? this.userConn : Conexion.getConnection();
            ps = conn.prepareStatement(SQL_UPDATE);
            rows = ps.executeUpdate();
            ps.setString(1, materia.getNombre());
            ps.setInt(2, materia.getId());
            
            System.out.println("Registros afectados: "+ rows);
            
        }catch(SQLException e){
            System.out.println("ERROR al actualizar materia");
            e.printStackTrace();
        }finally{
            Conexion.close(ps);
            if(this.userConn == null){
                Conexion.close(conn);
            }
        }
        return rows;
    }

    
}
