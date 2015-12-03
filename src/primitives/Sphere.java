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
               
        float t = 0;
        Point3D intersection = null;
        Vector3D normal = null;
        
        Vector3D v = new Vector3D(r.getOrigin(), center);
        float c = v.escprod(v) - radius*radius; 
        if (c > 0) { // Punto origen del rayo fuera de la esfera.
            float b = v.escprod(v);
            
            if (b >= 0) { // El centro de la esfera en el semiespacio posterior.
                if (c == b*b) {
                    t = b;
                    intersection = r.pointAtParameter(t);
                    normal = new Vector3D(center, intersection).normalize();
                }
                else if (c < b*b){
                    float d = (float) Math.sqrt(b * b - c);
                    float tp = b + d;
                    float tm = c / tp;
                    t = Math.min(tm, tp);
                    intersection = r.pointAtParameter(t);
                    normal = new Vector3D(center, intersection).normalize();
                }
            }
        }
        
        if(t > tmin){
            return new Hit(t, intersection, normal, color);
        }
        
        return Hit.VoidHit;

    }
}