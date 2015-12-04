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
        
        float c = normal.escprod(r.getDirection());
        
        if (c < 0){ // Intersección por la cara exterior.
            float b = normal.escprod(new Vector3D(point, r.getOrigin()));
            if (b >= 0) { // Intersección en el semiespacio posterior.
                final float t = -b / c;
                return new Hit(t, r.pointAtParameter(t), normal, color);
            }
        }
        return Hit.VoidHit;
    }
}
