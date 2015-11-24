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
    private final Point3D a;
    private final Point3D b;
    private final Point3D c;
    private final Vector3D normal;
    
    /**
     * Constructor de un triángulo.
     * @param a Punto del triángulo.
     * @param b Punto del triángulo.
     * @param c Punto del triángulo.
     * @param color Color especificado en sRGB del objeto.
     * @param normal Vector normal al triángulo.
     */
    public Triangle(final Color color, final Point3D a, final Point3D b, final Point3D c, final Vector3D normal) {
        super(color);
        this.a = a;
        this.b = b;
        this.c = c;
        this.normal = normal;
    }

    @Override
    public Hit intersect(Ray r, float tmin) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
