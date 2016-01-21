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
 * Clase que implementa la representación del rayo.
 * @author inigo.aguas
 */
public class Ray {
    
    private final Point3D point;
    private final Vector3D vector;
    
    /**
     * Método constructor del rayo a partir de dos puntos.
     * @param P Punto P del rayo. 
     * @param Q Punto Q del rayo.
     */
    public Ray (final Point3D P, final Point3D Q){
        this.point = P;
        this.vector = (Q.substract(P)).normalize();
    }
    
    /**
     * Método constructor del rayo a partir de un punto y un vector.
     * @param P Punto P del rayo. 
     * @param v Vector v en la dirección del rayo.
     */
    public Ray (final Point3D P, final Vector3D v){
        this.point = P;
        this.vector = v.normalize();
    }
    
    /**
     * Método para disponer de un punto a una distancia dada en la dirección del rayo.
     * @param t Distancia t.
     * @return Devuelve un punto a distancia t en la dirección del rayo.
     */
    public Point3D pointAtParameter (final float t){
        return new Point3D(point.getX() + t*vector.getX(), 
                           point.getY() + t*vector.getY(), 
                           point.getZ() + t*vector.getZ());
    }
    
    /**
     * Método de acceso al punto de origen del rayo.
     * @return Punto de origen del rayo.
     */
    public Point3D getOrigin (){
        return new Point3D(point);
    }

    /**
     * Método de acceso al vector de dirección del rayo.
     * @return Vector de dirección del rayo.
     */
    public Vector3D getDirection() {
        return new Vector3D(vector);
    }
}
