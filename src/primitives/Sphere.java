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
public class Sphere extends Object3D {
    
    /**
     * Constructor
     * @param c 
     */
    public Sphere (final Color c){
        super(c);
    }

    @Override
    Hit intersect(Ray r, float tmin) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}