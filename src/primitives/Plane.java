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
    
    private final Point3D point;
    private final Vector3D normal;
    // Vector normal al plano
    
    /**
     * Constructor de un plano
     * @param color Color espeficado en sRGB del objeto.
     * @param point Punto del plano.
     * @param normal Vector normal al plano.
     */
    public Plane(final Color color, final Point3D point, final Vector3D normal){
        super(color);
        this.point = point;
        this.normal = normal;
    }

    @Override
    public Hit intersect(Ray r, float tmin) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
