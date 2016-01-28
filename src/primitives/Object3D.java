/*
 * Proyecto upnaTRay
 * Computacion Grafica - Master Universitario en Ingenieria Informatica
 * Curso 2015-2016 - Universidad Publica de Navarra
 * Iñigo Aguas Ardaiz
 */
package primitives;

import java.awt.Color;
import material.Material;
import material.PhongMaterial;
import raytrace.Hit;
import raytrace.Ray;

/**
 * Clase abstracta de la que derivan todos los objetos 3D a implementar en el trazador.
 * @author inigo.aguas
 */
public abstract class Object3D {
    
    /** 
     * Argumento para disponer del color del objeto (en todas las herencias).
     */
    protected final Color color;
    protected final Material mat;
    
    /**
     * Método constructor de un objecto dado el color del mismo.
     * @param color Color del objeto dado según el sistema sRGB implementado en la clase Color.
     */
    public Object3D(final Color color){
        this.color = color;
        this.mat = new PhongMaterial(color);
    }
    
    /**
     * Método constructor de un objecto dado el color del mismo.
     * @param material Material del objeto.
     */
    public Object3D(final Material material){
        this.mat = material;
        this.color = mat.getColor();
    }
    
    /**
     * Método para representar la intersección entre los rayos emitidos por el
     * trazador y los objetos que se encuentran en la escena.
     * @param r  Rayo emitido por el trazador.
     * @param tmin  Se restringe con una distancia tmin la zona de intersección 
     *              cuando la imagen a generar incluye algún tipo de efecto de 
     *              perspectiva.
     * @return   * Si el rayo intersecta con un objeto, se devuelve un objeto de
     *             la clase Hit con la información del punto de intersección.
     *           * En caso contrario devuelve el objeto estático VoidHit.
     */
    public abstract Hit intersect (final Ray r, final float tmin);
    
    /**
     * Método para representar la intersección entre los rayos de sombra y los 
     * objetos que se encuentran en la escena.
     * @param r Rayo de sombra emitido por la fuente de luz.
     * @param P Punto de intersección con el objeto conocido.
     * @param tmax Distancia al cuadrado entre el punto y el origen del rayo.
     * @return Si el rayo intersecta con algún objeto de la escena o no.
     */    
    public abstract boolean intersect(final Ray r, final Point3D P, final float tmax);
}
