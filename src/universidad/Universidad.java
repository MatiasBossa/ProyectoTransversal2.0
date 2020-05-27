/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package universidad;

import java.time.LocalDate;
import universidad.control.AlumnoData;
import universidad.control.CursadaData;
import universidad.modelo.Alumno;


/**
 *
 * @author HP
 */
public class Universidad {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
         /* 
        Alumno a = new Alumno();
        a.setNombre("Matias");
        a.setActivo(true);
        a.setFecNac(LocalDate.now());
        
        AlumnoData ad = new AlumnoData();
        ad.guardarAlumno(a);
        */
        CursadaData cd = new CursadaData();
        //cd.borrarCursadaDeUnaMateriaDeUnAlumno(1, 1);
        cd.actualizarNotaCursada(1, 1, 9);
        
       
           
    }
}
