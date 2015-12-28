/*
 * Proyecto upnaTRay
 * Computacion Grafica - Master Universitario en Ingenieria Informatica
 * Curso 2015-2016 - Universidad Publica de Navarra
 * Iñigo Aguas Ardaiz
 */
package projection;

import primitives.Point3D;
import raytrace.Ray;
import raytrace.RayGenerator;

/**
 * Implementación de la lente de la cámara para crear una projección ortográfica.
 * @author inigo.aguas
 */
public class Ortographic extends Projection {

    /**
     * Método constructor de la projección ortográfica.
     * @param w Anchura de la ventana de projección en píxeles.
     * @param h Altura de la ventana de projección en píxeles.
     */
    public Ortographic(final float w, final float h) {
        super(0.0f, w, h);
    }
    
    @Override
    RayGenerator getRayGenerator(Camera cam, int W, int H) {
        return new OrtographicRayGenerator(cam, W, H);
    }
    
    
    /**
     * Clase interna privada para el generador de rayos de la proy. ortográfica.
     */
    private static class OrtographicRayGenerator extends RayGenerator {

        // Método constructor
        public OrtographicRayGenerator(final Camera cam, final int W, final int H) {
            super(cam, W, H);
        }
        
        @Override
        public Ray getRay(int m, int n) {
            final float x = (m + 0.5f) * cam.getProjection().w/W - cam.getProjection().w*0.5f;
            final float y = (n + 0.5f) * cam.getProjection().h/H - cam.getProjection().h*0.5f;
            final float z = 0.0f;
            Point3D R = cam.toSceneCoord(new Point3D(x, y, z));
            return new Ray(R, cam.getLook());
        }
        
    }
}
