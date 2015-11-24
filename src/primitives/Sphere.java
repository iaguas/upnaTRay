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
    
    private final Point3D center;
    private final float radius;
    
    /**
     * Constructor de la esfera.
     * @param color Color especificado en sRGB del objeto.
     * @param center Centro de la esfera.
     * @param radius Radio de la esfera.
     */
    public Sphere (final Color color, final Point3D center, final float radius){
        super(color);
        this.center = center;
        this.radius = radius;        
    }

    @Override
    public Hit intersect(Ray r, float tmin) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}