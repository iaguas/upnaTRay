/*
 * Proyecto upnaTRay
 * Computacion Grafica - Master Universitario en Ingenieria Informatica
 * Curso 2015-2016 - Universidad Publica de Navarra
 * Iñigo Aguas Ardaiz
 */
package primitives;

import java.awt.Color;
import material.Material;
import raytrace.Hit;
import raytrace.Ray;

/**
 * Clase para la representación de un plano en la escena.
 * @author inigo.aguas
 */
public class Plane extends Object3D {
    
    private final Point3D point; // Punto del plano.
    private final Vector3D normal; // Vector normal al plano.
    
    /**
     * Método constructor de un plano.
     * @param color Color espeficado en sRGB del objeto.
     * @param point Punto del plano.
     * @param normal Vector normal al plano.
     */
    public Plane(final Color color, final Point3D point, final Vector3D normal){
        super(color);
        this.point = point;
        this.normal = normal;
    }

    /**
     * Método constructor de un plano.
     * @param mat Material del objeto.
     * @param point Punto del plano.
     * @param normal Vector normal al plano.
     */
    public Plane(final Material mat, final Point3D point, final Vector3D normal){
        super(mat);
        this.point = point;
        this.normal = normal;
    }
    
    @Override
    public Hit intersect(Ray r, float tmin) {
        final float denominator = normal.dotProd(r.getDirection().oposite());
        if (denominator > 0){ // Tengo cuidado de no divir por 0
            final Vector3D AR = new Vector3D(point, r.getOrigin());
            final float a = AR.dotProd(normal) / denominator;
            return new Hit(a, r.pointAtParameter(a), normal, mat);
        }
        return Hit.VoidHit;
    }

    @Override
    public boolean intersect(Ray r, Point3D P) {
        final float denominator = normal.dotProd(r.getDirection().oposite());
        if (denominator > 0){ // Tengo cuidado de no divir por 0
            final Vector3D AR = new Vector3D(point, r.getOrigin());
            final float a = AR.dotProd(normal) / denominator;
            final Point3D intersection = r.pointAtParameter(a);
            final Vector3D separation = new Vector3D(P, intersection);
            final float moduleSquare = separation.dotProd(separation);
            return moduleSquare > 1.0e-7;
            }
        return false;
    }
}
