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
public abstract class Object3D {
    
    // Argumento de la clase Color.
    Color c;
    
    /**
     * Constructor
     * @param c
     */
    public Object3D(final Color c){
        this.c = c;
    }
    
    /**
     * 
     * @param r
     * @param tmin  restringir la distancia de la zona de intersección cuando la 
     *              imagen a generar incluye algún tipo de efecto de perspectiva
     * @return   * Si el rayo no intersecta con el objeto, devuelve el objeto 
     *             estático VoidHit.
     *           * En caso contrario devuelve un objeto de la clase Hit con la 
     *             información del punto de intersección
     */
    abstract Hit intersect (final Ray r, final float tmin);
    
}
