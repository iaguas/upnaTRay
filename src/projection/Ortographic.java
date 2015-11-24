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
public class Ortographic extends Projection {

    /**
     * 
     * @param distance
     * @param w
     * @param h 
     */
    public Ortographic(final float distance, final float w, final float h) {
        super(distance, w, h);
    }
    
    @Override
    RayGenerator getRayGenerator(Camera cam, int W, int H) {
        return new OrtographicRayGenerator(cam, W, H);
    }
    
    
    private static class OrtographicRayGenerator extends RayGenerator {

        public OrtographicRayGenerator(final Camera cam, final int W, final int H) {
            super(cam, W, H);
        }

        // En el pdf está como hacerlo cuando habla de la clase Ray Generator
        
        @Override
        public Ray getRay(int m, int n) {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }
        
    }
}
