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
 * Clase para la representación de una esfera en la escena.
 * @author inigo.aguas
 */
public class Sphere extends Object3D {
    
    private final Point3D center; // Centro de la esfera.
    private final float radius; // Radio de la esfera.
    
    /**
     * Método constructor de la esfera.
     * @param color Color especificado en sRGB del objeto.
     * @param center Centro de la esfera de tipo Point3D.
     * @param radius Radio R de la esfera.
     */
    public Sphere (final Color color, final Point3D center, final float radius){
        super(color);
        this.center = center;
        this.radius = radius;        
    }

    @Override
    public Hit intersect(Ray r, float tmin) {

        float t = 0;
        
        Vector3D v = new Vector3D(r.getOrigin(), center);
        final float c = v.dotProd(v) - radius*radius; 
        if (c > 0) { // Punto origen del rayo fuera de la esfera.
            final float b = v.dotProd(r.getDirection());
            
            if (b >= 0) { // El centro de la esfera en el semiespacio posterior.
                if (c == b*b) {
                    t = b;
                }
                else if (c < b*b){
                    final float d = (float) Math.sqrt(b * b - c);
                    final float tp = b + d;
                    final float tm = c / tp;
                    t = Math.min(tm, tp);
                }
            }
        }
               
        if(t > tmin){
            final Point3D intersection = r.pointAtParameter(t);
            final Vector3D normal = new Vector3D(center, intersection).normalize();
            return new Hit(t, intersection, normal, mat);
        }
        
        return Hit.VoidHit;
        
    }
}