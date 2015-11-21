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
public class Plane extends Object3D {
    
    Point3D p;
    // Vector normal al plano
    
    public Plane(final Color c, final Point3D p){//, Vector)
        super(c);
        this.p = p;
    }

    @Override
    Hit intersect(Ray r, float tmin) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
