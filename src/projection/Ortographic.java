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
    
    @Override
    RayGenerator getRayGenerator(Camera cam, int W, int H) {
        return new OrtographicRayGenerator(cam, W, H);
    }
    
    
    private static class OrtographicRayGenerator extends RayGenerator {

        public OrtographicRayGenerator(Camera cam, int W, int H) {
            super(cam, W, H);
        }

        // En el pdf está como hacerlo
        
        @Override
        public Ray getRay(int m, int n) {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }
        
    }
}
