/*
 * Proyecto upnaTRay
 * Computacion Grafica - Master Universitario en Ingenieria Informatica
 * Curso 2015-2016 - Universidad Publica de Navarra
 * Iñigo Aguas Ardaiz
 */
package raytrace;

import java.awt.Color;
import primitives.Point3D;
import primitives.Vector3D;

/**
 *
 * @author inigo.aguas
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
    
    /**
     * Método para conocer si existe intersección entre el objeto y el rayo.
     * @return Devuelve un booleano acorde a lo anterior.
     */
    public boolean hits(){
        return this.t == Float.POSITIVE_INFINITY;
    }

    /**
     * Método para conocer el color del punto de intersección.
     * @return Una variable de tipo Color con el color concreto.
     */
    public Color getColor(){
        return color;
    }
}
