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
import primitives.propieties.Material;

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
     * @param color Color del objeto en el impacto.
     */
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

    public Material getMaterial() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
