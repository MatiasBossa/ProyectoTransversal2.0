/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package universidad.control;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import universidad.modelo.*;

/**
 *
 * @author matia
 */
public class CursadaData {
    
    private Connection userConn;
    
    private final String SQL_INSERT = "INSERT INTO cursada(id_alumno, id_materia, nota) VALUES (?, ?, ?)"; //Query para insertar una Cursada

    private final String SQL_DELETE = "DELETE FROM cursada WHERE id_materia = ?";
    
    private final String SQL_SELECT_ALL = "SELECT * FROM cursada";
    
    private final String SQL_SELECT = "SELECT * FROM cursada WHERE id_alumno = ?";
    
    private final String SQL_SELECT_MAT = "SELECT materia.id, materia.nombre  FROM materia, cursada WHERE cursada.idMateria = materia.id AND cursada.idAlumno = ? ";
    
    private final String SQL_SELECT_NOT_MAT = "SELECT materia.id, materia.nombre  FROM materia, cursada WHERE cursada.idMateria = materia.id AND NOT cursada.idAlumno = ? ";
    
    private final String SQL_DELETE_MAT = "DELETE FROM cursada WHERE idAlumno = ? AND idMateria = ? ";
    
    private final String SQL_UPDATE_NOTA = "UPDATE cursada SET nota = ? WHERE idAlumno = ? AND idMateria = ? ";
    
    
       
    public void guardarCursada(Cursada cursada){
        Connection conn = null;
        PreparedStatement ps = null;
        try{
            conn = (this.userConn != null) ? this.userConn : Conexion.getConnection();
            ps = conn.prepareStatement(SQL_INSERT);
            ps.setInt(1, cursada.getAlumno().getId());
            ps.setInt(2, cursada.getMateria().getId());
            ps.setInt(3, cursada.getNota());
            
            ps.executeUpdate();
            
        }catch(SQLException e){
            System.out.println("ERROR al guardar la cursada");
            e.printStackTrace();
        }finally{
            Conexion.close(ps);
            if(this.userConn == null){
                Conexion.close(conn);
            }
        }
    }
    
    public List<Cursada> obtenerCursadas(){
        List<Cursada> listaCursada = new ArrayList<>();
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        Cursada c = null;
        Alumno a = null;
        Materia m = null;
        try{
            conn = (this.userConn != null) ? this.userConn : Conexion.getConnection();
            ps = conn.prepareStatement(SQL_SELECT_ALL);
            rs = ps.executeQuery();
            
            while(rs.next()){
                c = new Cursada();
                c.setId(rs.getInt(1));
                
                a = new Alumno();
                a = buscarAlumno(rs.getInt(2));
                c.setAlumno(a);
                
                m = new Materia();
                m = buscarMateria(rs.getInt(3));
                c.setMateria(m);
                
                c.setNota(rs.getInt(4));
                
                listaCursada.add(c);
            }
            
        }catch(SQLException e){
            System.out.println("ERROR al obtener las cursadas");
            e.printStackTrace();
        }finally{
            Conexion.close(rs);
            Conexion.close(ps);
            if(this.userConn == null){
                Conexion.close(conn);
            }
        }
        
        return listaCursada;
    }
    
    public List<Cursada> obtenerCursadasXAlumno(int id){
        List<Cursada> listaCursada = new ArrayList<>();
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        Cursada c = null;
        Alumno a = null;
        Materia m = null;
        try{
            conn = (this.userConn != null) ? this.userConn : Conexion.getConnection();
            ps = conn.prepareStatement(SQL_SELECT);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            
            while(rs.next()){
                c = new Cursada();
                c.setId(rs.getInt(1));
                
                a = new Alumno();
                a = buscarAlumno(rs.getInt(2));
                c.setAlumno(a);
                
                m = new Materia();
                m = buscarMateria(rs.getInt(3));
                c.setMateria(m);
                
                c.setNota(rs.getInt(4));
                
                listaCursada.add(c);
            }
            
        }catch(SQLException e){
            System.out.println("ERROR al obtener las cursadas");
            e.printStackTrace();
        }finally{
            Conexion.close(rs);
            Conexion.close(ps);
            if(this.userConn == null){
                Conexion.close(conn);
            }
        }
        
        return listaCursada;
    }
    
    public Materia buscarMateria(int id){
        MateriaData m = new MateriaData();
        return m.buscarMateria(id);
    }
    
    public Alumno buscarAlumno(int id){
        AlumnoData a = new AlumnoData();
        return a.buscarAlumno(id);
    }
    
    public List<Materia> obtenerMateriasCursadas(int id){
        List<Materia> listaMaterias = new ArrayList<>();
        Materia m = null;
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try{
            conn = (this.userConn != null) ? this.userConn : Conexion.getConnection();
            ps = conn.prepareStatement(SQL_SELECT_MAT);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            while(rs.next()){
                m = new Materia();
                
                m.setId(rs.getInt(1));
                m.setNombre(rs.getString(2));
                
                listaMaterias.add(m);
            }
        }catch(SQLException e){
            System.out.println("ERROR al obtener Materias cursadas");
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
    
    public List<Materia> obtenerMateriasNOCursadas(int id){
        List<Materia> listaMaterias = new ArrayList<>();
        Materia m = null;
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try{
            conn = (this.userConn != null) ? this.userConn : Conexion.getConnection();
            ps = conn.prepareStatement(SQL_SELECT_NOT_MAT);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            while(rs.next()){
                m = new Materia();
                
                m.setId(rs.getInt(1));
                m.setNombre(rs.getString(2));
                
                listaMaterias.add(m);
            }
        }catch(SQLException e){
            System.out.println("ERROR al obtener Materias cursadas");
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
    
    public void borrarCursadaDeUnaMateriaDeUnAlumno(int idAlumno, int idMateria){
        Connection conn = null;
        PreparedStatement ps = null;
        try{
            conn = (this.userConn != null) ? this.userConn : Conexion.getConnection();
            ps = conn.prepareStatement(SQL_DELETE_MAT);
            ps.setInt(1, idMateria);
            ps.setInt(2, idAlumno);
            ps.executeUpdate();
            
        }catch(SQLException e){
            System.out.println("ERROR al borrar la cursada del Alumno");
            e.printStackTrace();
        }finally{
            Conexion.close(ps);
            if(this.userConn == null){
                Conexion.close(conn);
            }
        }
    }
    
    public void actualizarNotaCursada(int idAlumno, int idMateria, int nota){
        Connection conn = null;
        PreparedStatement ps = null;
        try{
            conn = (this.userConn != null) ? this.userConn : Conexion.getConnection();
            ps = conn.prepareStatement(SQL_UPDATE_NOTA);
            ps.setInt(1, nota);
            ps.setInt(2, idAlumno);
            ps.setInt(3, idMateria);
            ps.executeUpdate();
        }catch(SQLException e){
            System.out.println("ERROR al actualizar nota");
            e.printStackTrace();
        }
        
    }
    
    
    
}
