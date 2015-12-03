/*
 * Proyecto upnaTRay
 * Computacion Grafica - Master Universitario en Ingenieria Informatica
 * Curso 2015-2016 - Universidad Publica de Navarra
 * Iñigo Aguas Ardaiz
 */
package raytrace;

import primitives.Point3D;
import primitives.Vector3D;

/**
 *
 * @author inigo.aguas
 */
public class Ray {
    
    Point3D point;
    Vector3D vector;
    
    public Ray (final Point3D R, final Point3D Q){
        this.point = R;
        this.vector = (Q.substract(R)).normalize();
    }
    
    public Ray (final Point3D R, final Vector3D v){
        this.point = R;
        this.vector = v.normalize();
    }
    
    public Point3D pointAtParameter (final float t){
        return new Point3D(point.x+t*vector.x, point.y+t*vector.y, point.z+t*vector.z);
    }
}
