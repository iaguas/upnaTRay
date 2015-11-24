/*
 * Proyecto upnaTRay
 * Computacion Grafica - Master Universitario en Ingenieria Informatica
 * Curso 2015-2016 - Universidad Publica de Navarra
 * Iñigo Aguas Ardaiz
 */
package projection;

import raytrace.Ray;
import raytrace.RayGenerator;

/**
 *
 * @author inigo.aguas
 */
public class Perspective extends Projection {

    private float angle;
    private float aspect;
    
    /**
     * Constructor de la proyección en perspectiva
     * @param distance Distancia entre entre el plano de proyección y la cámara.
     * @param w Anchura de la ventana de proyección.
     * @param h Altura de la ventana de proyección.
     * @param angle
     * @param aspect 
     */
    public Perspective(final float distance, final float w, final float h, 
            final float angle, final float aspect) {
        super(distance, w, h);
        this.angle = angle;
        this.aspect = aspect;
    }

    // Distancia dentre el plano de proyeccion y la camara
    // Angulo fov de apertura vertical del campo visual (en grados)
    // Relacion de aspecto a (ratio entre anchura y altura de la ventana)
    
    @Override
    RayGenerator getRayGenerator (final Camera cam, final int W, final int H) {
        return new PerspectiveRayGenerator(cam, W, H);
    }
    
    private static class PerspectiveRayGenerator extends RayGenerator {

        public PerspectiveRayGenerator(final Camera cam, final int W, final int H) {
            super(cam, W, H);
        }
        
        // En el pdf está como hacerlo cuando habla de la clase RayGenerator
        @Override
        public Ray getRay(int m, int n) {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }
        
    }
    
}
