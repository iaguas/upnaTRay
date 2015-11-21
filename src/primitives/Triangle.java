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
public class Triangle extends Object3D {
    
    // Puntos del triángulo
    Point3D a;
    Point3D b;
    Point3D c;
    
    /**
     * Constructor
     * @param a 
     * @param b 
     * @param c
     * @param color
     */
    public Triangle(final Color color, final Point3D a, final Point3D b, final Point3D c) {
        super(color);
        this.a = a;
        this.b = b;
        this.c = c;
    }

    @Override
    Hit intersect(Ray r, float tmin) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
