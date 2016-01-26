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
 * Clase para la representación de un triángulo en la escena.
 * @author inigo.aguas
 */
public class Triangle extends Object3D {
    
    // Puntos del triángulo
    private final Point3D a;
    private final Point3D b;
    private final Point3D c;
    private final Vector3D normal; // Vector normal al triángulo.
    
    /**
     * Método constructor de un triángulo.
     * @param a Vértice a del triángulo.
     * @param b Vértice b del triángulo.
     * @param c Vértice c del triángulo.
     * @param color Color especificado en sRGB del objeto.
     */
    public Triangle(final Color color, final Point3D a, final Point3D b, final Point3D c) {
        super(color);
        this.a = a;
        this.b = b;
        this.c = c;
        final Vector3D AB = new Vector3D(a, b);
        final Vector3D AC = new Vector3D(c, a);
        this.normal = (AB.crossProd(AC)).normalize();
    }
        
    /**
     * Método constructor de un triángulo.
     * @param a Vértice a del triángulo.
     * @param b Vértice b del triángulo.
     * @param c Vértice c del triángulo.
     * @param mat Material del objeto.
     */
    public Triangle(final Material mat, final Point3D a, final Point3D b, final Point3D c) {
        super(mat);
        this.a = a;
        this.b = b;
        this.c = c;
        final Vector3D AB = new Vector3D(a, b);
        final Vector3D AC = new Vector3D(c, a);
        this.normal = (AB.crossProd(AC)).normalize();
    }

    @Override
    public Hit intersect(Ray r, float tmin){
        final Vector3D norm = (new Vector3D(this.a, this.b)).crossProd(new Vector3D(this.a, this.c));
        final float cc = norm.dotProd(r.getDirection());
        
        if (cc < 0){ // Intersección por la cara exterior.
            final float bb = norm.dotProd(new Vector3D(a, r.getOrigin()));
            if (bb >= 0) { // Intersección en el semiespacio posterior.
                final float alpha = -bb / cc;
                
                // Se evita la implementación de cramer directamente.
                final Vector3D AB = new Vector3D(this.a, this.b);
                final Vector3D AC = new Vector3D(this.a, this.c);
                final Vector3D AR = new Vector3D(this.a, r.getOrigin());
                final float denom = 1 / r.getDirection().dotProd(AC.crossProd(AB));
                final float beta = -denom * AC.dotProd(r.getDirection().crossProd(AR));
                final float gamma = denom * AB.dotProd(r.getDirection().crossProd(AR)); 
                
                if ((beta >= 0) && (beta <= 1)){
                    if ((gamma >= 0) && (gamma <= 1)){ 
                        if ((beta + gamma) <= 1){ 
                            return new Hit(alpha, r.pointAtParameter(alpha), norm, mat);
                        }
                    }
                }
            }
        }
        
        return Hit.VoidHit;
    }
    
    @Override
    public boolean intersect(Ray r, Point3D P) {    
        Vector3D norm = (new Vector3D(this.a, this.b)).crossProd(new Vector3D(this.a, this.c));
        float cc = norm.dotProd(r.getDirection());
        
        if (cc < 0){ // Intersección por la cara exterior.
            float bb = norm.dotProd(new Vector3D(a, r.getOrigin()));
            if (bb >= 0) { // Intersección en el semiespacio posterior.
                final float alpha = -bb / cc;
                
                // Se evita la implementación de cramer directamente.
                final Vector3D AB = new Vector3D(this.a, this.b);
                final Vector3D AC = new Vector3D(this.a, this.c);
                final Vector3D AR = new Vector3D(this.a, r.getOrigin());
                final float denom = 1 / r.getDirection().dotProd(AC.crossProd(AB));
                final float beta = -denom * AC.dotProd(r.getDirection().crossProd(AR));
                final float gamma = denom * AB.dotProd(r.getDirection().crossProd(AR)); 
                
                if ((beta >= 0) && (beta <= 1)){
                    if ((gamma >= 0) && (gamma <= 1)){ 
                        if ((beta + gamma) <= 1){ 
                            final Point3D intersection = r.pointAtParameter(alpha);
                            final Vector3D separation = new Vector3D(P, intersection);
                            final float moduleSquare = separation.dotProd(separation);
                            return moduleSquare > 1.0e-8;
                        }
                    }
                }
            }
        }
        
        return false;
    }
    
}
