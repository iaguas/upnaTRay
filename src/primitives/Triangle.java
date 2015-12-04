/*
 * Proyecto upnaTRay
 * Computacion Grafica - Master Universitario en Ingenieria Informatica
 * Curso 2015-2016 - Universidad Publica de Navarra
 * Iñigo Aguas Ardaiz
 */
package primitives;

import java.awt.Color;
import javax.vecmath.Matrix3f;
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
     */
    public Triangle(final Color color, final Point3D a, final Point3D b, final Point3D c) {
        super(color);
        this.a = a;
        this.b = b;
        this.c = c;
        Vector3D AB = new Vector3D(a, b);
        Vector3D AC = new Vector3D(c, a);
        this.normal = (AB.vecprod(AC)).normalize();
    }

    @Override
    public Hit intersect(Ray r, float tmin){
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        
        Vector3D norm = (new Vector3D(this.a, this.b)).vecprod(new Vector3D(this.a, this.c));
        float cc = norm.escprod(r.getDirection());
        
        if (cc < 0){ // Intersección por la cara exterior.
            float bb = norm.escprod(new Vector3D(a, r.getOrigin()));
            if (bb >= 0) { // Intersección en el semiespacio posterior.
                final float alpha = -bb / cc;
                //float[] result = new float[2];
                float[] result = solve(new Vector3D(this.a, this.b), 
                                       new Vector3D(this.a, this.c), 
                                       new Vector3D(this.a, r.getOrigin()), 
                                       r.getDirection());
                float beta = result[0];
                float gamma = result[1];
                if ((beta >= 0) && (beta <= 1)){
                    if ((gamma >= 0) && (gamma <= 1)){ 
                        if ((gamma >= 0) && (gamma <= 1)){ 
                            return new Hit(alpha, r.pointAtParameter(alpha), norm, color);
                        }
                    }
                }
            }
        }
        
        return Hit.VoidHit;
    }
    
    private float[] solve(Vector3D AB, Vector3D AC, Vector3D AR, Vector3D v){
        // Implementar Cramer
        
        Matrix3f denominator = new Matrix3f();
        denominator.setColumn(0, AB.getVector3f());
        denominator.setColumn(1, AC.getVector3f());
        denominator.setColumn(2, v.getVector3f());
        float demDet = denominator.determinant();
        
        Matrix3f numeratorX = new Matrix3f();
        numeratorX.setColumn(0, AR.getVector3f());
        numeratorX.setColumn(1, AC.getVector3f());
        numeratorX.setColumn(2, v.getVector3f());
        float numXDet = numeratorX.determinant();
 
        Matrix3f numeratorY = new Matrix3f();
        numeratorY.setColumn(0, AR.getVector3f());
        numeratorY.setColumn(1, AC.getVector3f());
        numeratorY.setColumn(2, v.getVector3f());
        float numYDet = numeratorY.determinant();
 
        float[] result = new float[2];
        result[0] = numXDet/demDet;
        result[1] = numYDet/demDet;
        
        return result;
    }
    
}
