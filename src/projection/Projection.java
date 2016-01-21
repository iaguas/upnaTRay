/*
 * Proyecto upnaTRay
 * Computacion Grafica - Master Universitario en Ingenieria Informatica
 * Curso 2015-2016 - Universidad Publica de Navarra
 * Iñigo Aguas Ardaiz
 */
package projection;

import raytrace.RayGenerator;

/**
 * Clase abstracta para manejar las projecciones de las que heredan las demás.
 * @author inigo.aguas
 */
public abstract class Projection {
    // Atributos: 
    // * Distancia d del plano de proyeccion a la camara
    // * Anchura w de la ventana de proyeccion
    // * Altura h de la ventana de proyeccion
    
    /**
     * Distancia d del plano de proyeccion a la camara
     */
    private float distance;
    /**
     * Anchura w de la ventana de proyeccion
     */
    private float w;
    /**
     * Altura h de la ventana de proyeccion
     */
    private float h;
    
    /**
     * Constructor sin argumentos de la clase Projection.
     */
    public Projection () {}
    
    /**
     * Constructor de la proyección
     * @param distance Distancia entre entre el plano de proyección y la cámara.
     * @param w Anchura de la ventana de proyección.
     * @param h Altura de la ventana de proyección.
     */
    public Projection (float distance, float w, float h){
        this.distance = distance;
        this.w = w;
        this.h = h;
    }
    
    /**
     * Método con el se crea el generador de rayos del trazador.
     * @param cam Cámara asociada al trazador.
     * @param w Anchura de la ventana de proyección.
     * @param h Altura de la ventana de proyección.
     * @return Un generador de rayos adecuado a la lente de la cámara y los parámetros del trazador.
     */
    abstract RayGenerator getRayGenerator (final Camera cam, final int w, final int h);
    
    /**
     * Método de acceso a la distancia a la que se produce la intersección del rayo.
     * @return La distancia a la que se produce la intersección.
     */
    public float getDistance() {
        return distance;
    }
    
    /**
     * Método de acceso a la anchura de la ventana de proyección.
     * @return Anchura de la ventana de proyección.
     */
    public float getW() {
        return w;
    }
    
    /**
     * Método de acceso a la altura de la ventana de proyección.
     * @return Altura de la ventana de proyección.
     */
    public float getH() {
        return h;
    }

    /**
     * Método de definición de la distancia a la que se produce la intersección del rayo.
     * @param distance La distancia a la que se produce la intersección.
     */
    public void setDistance(float distance) {
        this.distance = distance;
    }

    /**
     * Método de definición de la anchura de la ventana de proyección.
     * @param w Anchura de la ventana de proyección.
     */
    public void setW(float w) {
        this.w = w;
    }

    /** 
     * Método de definición de la altura de la ventana de proyección.
     * @param h Altura de la ventana de proyección.
     */
    public void setH(float h) {
        this.h = h;
    }
    
}