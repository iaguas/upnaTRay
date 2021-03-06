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
import material.Material;

/**
 * Clase con la que se representa el impacto de un rayo en un objeto.
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
    private Material mat;
    
    /**
     * Objeto estático para reconocer cuando no se golpea con ningún objeto.
     */
    public static final Hit VoidHit = new Hit(Float.POSITIVE_INFINITY);
    // El color será el de fondo.
    
    // Método constructor para la creación de VoidHit.
    private Hit(final float t){
        this.t = t;
    }

    /**
     * Método constructor de la implementación del impacto.
     * @param t Distancia de intersección t.
     * @param intersectionPoint Punto de intersección.
     * @param normal Vector normal.
     * @param mat Material del objeto golpeado.
     */
    public Hit(final float t, final Point3D intersectionPoint, final Vector3D normal, final Material mat){
        this.t = t;
        this.intersectionPoint = intersectionPoint;
        this.normal = normal;
        this.mat = mat;
        this.color = mat.getColor();
    }

    /**
     * Método para conocer si existe intersección entre el objeto y el rayo.
     * @return Devuelve un booleano acorde a lo anterior.
     */
    public boolean hits(){
        return this.getT() != Float.POSITIVE_INFINITY;
    }

    /**
     * Método de acceso al color del punto de intersección.
     * @return Una variable de tipo Color con el color concreto.
     */
    public Color getColor(){
        return this.color;
    }

    /**
     * Método de acceso al valor de t (distancia de intersección).
     * @return Distancia de intersección t.
     */
    public float getT() {
        return t;
    }

    /**
     * Método de acceso al Material del objeto.
     * @return Material mat.
     */
    public Material getMaterial() {
        return mat;
    }

    /**
     * Método de acceso al punto de intersección del impacto.
     * @return Punto de intersección del impacto.
     */
    public Point3D getIntersectionPoint() {
        return new Point3D(intersectionPoint);
    }

    /** 
     * Método de acceso a la normal de la superficie del punto de intersección del impacto.
     * @return Vector normal de la superficie del punto de intersección del impacto.
     */
    public Vector3D getNormal() {
        return new Vector3D(normal);
    }
}