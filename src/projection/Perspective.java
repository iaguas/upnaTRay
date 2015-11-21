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

    // Distancia dentre el plano de proyeccion y la camara
    // Angulo fov de apertura vertical del campo visual (en grados)
    // Relacion de aspecto a (ratio entre anchura y altura de la ventana)
    
    @Override
    RayGenerator getRayGenerator (final Camera cam, final int W, final int H) {
        return new PerspectiveRayGenerator(cam, W, H);
    }
    
    private static class PerspectiveRayGenerator extends RayGenerator {

        public PerspectiveRayGenerator(Camera cam, int W, int H) {
            super(cam, W, H);
        }
        
        // En el pdf está como hacerlo
        @Override
        public Ray getRay(int m, int n) {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }
        
    }
    
}
