/*
 * Proyecto upnaTRay
 * Computacion Grafica - Master Universitario en Ingenieria Informatica
 * Curso 2015-2016 - Universidad Publica de Navarra
 * Iñigo Aguas Ardaiz
 */
package raytrace;

import projection.Camera;

/**
 * Clase abstracta para la representación del generador de rayos según la proyección.
 * @author inigo.aguas
 */
public abstract class RayGenerator {
    
    // Parámetros de cámara y dimensiones de la imagen.
    protected Camera cam;
    protected int W, H;
    
    /** 
     * Método constructor del generador de rayos abstracto
     * @param cam Cámara utilizada en el trazador.
     * @param W Anchura de la imagen.
     * @param H Altura de la imagen.
     */
    protected RayGenerator(final Camera cam, final int W, final int H){
        this.cam = cam;
        this.W = W;
        this.H = H;
    }
    
    /**
     * Método de acceso al rayo generado para un píxel en una coordenada.
     * @param m Coordenada x.
     * @param n Coordenada y.
     * @return El rayo que genera la intersección (hit) para el píxel (m, n).
     */
    public abstract Ray getRay (final int m, final int n);    
   
}
