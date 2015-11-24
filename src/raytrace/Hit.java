/*
 * Proyecto upnaTRay
 * Computaci�n Gr�fica - M�ster Universitario en Ingenier�a Inform�tica
 * Curso 2015-2016 - Universidad P�blica de Navarra
 * I�igo Aguas Ardaiz
 *  * Each line should be prefixed with  * 
 */
package raytrace;

import java.awt.Color;
import primitives.Point3D;
import primitives.Vector3D;

/**
 *
 * @author aguas
 */
public class Hit {
    
    // Almacena la informacion del punto de interseccion mas cercano. Conserva 
    // el valor del parametro t, el punto de interseccion, la normal y el material 
    // en ese punto (por ahora color)
    private float t;
    private Point3D intersectionPoint;
    private Vector3D normal;
    private Color color;
    
    public static final Hit VoidHit = new Hit(Float.POSITIVE_INFINITY);
    // El color será el de fondo.
    
    private Hit(final float t){
        this.t = t;
    }
    
    public Hit(final float t, final Point3D intersectionPoint, final Vector3D normal, final Color color){
        this.t = t;
        this.intersectionPoint = intersectionPoint;
        this.normal = normal;
        this.color = color;
    }
}
