/*
 * Proyecto upnaTRay
 * Computacion Grafica - Master Universitario en Ingenieria Informatica
 * Curso 2015-2016 - Universidad Publica de Navarra
 * Iñigo Aguas Ardaiz
 */
package primitives;

import java.awt.Color;
import raytrace.Hit;
import raytrace.Ray;

/**
 *
 * @author inigo.aguas
 */
public class Group extends Object3D {
    
    public Group(final Color c) {
        super(c);
    }
    
    void addObject (final int index, final Object3D obj){
        
    }
    
    Object3D getObject (final int index){
        return null;
    }
    
    // El metodo intersect()de esta subclase itera por los objetos almacenados 
    // en la estructura llamando a sus respectivos metodos de interseccion

    @Override
    Hit intersect(Ray r, float tmin) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
