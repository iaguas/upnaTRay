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
 * Implementación de la lente de la cámara para crear una projección en prespectiva.
 * @author inigo.aguas
 */
public class Perspective extends Projection {

    // Como solo se utilizan en el constructor, no nos preocupamos de guardarlos para nada.
    //private final float angle;
    //private final float aspect;
    
    /**
     * Constructor de la proyección en perspectiva
     * @param distance Distancia entre entre el plano de proyección y la cámara.
     * @param angle Ángulo de la perspectiva en grados.
     * @param aspect Aspecto de la proyección.
     */
    public Perspective(final float distance, final float angle, final float aspect) {
        // No utilizamos el constructor super para calcular h y w pq tendría que ser lo primero.
        this.setDistance(distance);
        final float a = (float) Math.toRadians(angle);
        // Calculamos también las dimensiones.
        this.setH(2 * distance * (float) Math.tan(a*0.5f));
        this.setW(aspect * this.getH());
    }
    
    @Override
    RayGenerator getRayGenerator (final Camera cam, final int W, final int H) {
        return new PerspectiveRayGenerator(cam, W, H);
    }
    
    /**
     * Clase interna privada para el generador de rayos de la proy. en perspectiva.
     */
    private static class PerspectiveRayGenerator extends RayGenerator {

        // Método constructor
        public PerspectiveRayGenerator(final Camera cam, final int W, final int H) {
            super(cam, W, H);
        }
        
        @Override
        public Ray getRay(int m, int n) {
            final float x = (m + 0.5f) * cam.getProjection().getW()/W - cam.getProjection().getW() * 0.5f;
            final float y = (n + 0.5f) * cam.getProjection().getH()/H - cam.getProjection().getH() * 0.5f;
            final float z = -cam.getProjection().getDistance();
            final Point3D R = cam.toSceneCoord(new Point3D(x, y, z));
            return new Ray(cam.getPoint(), R);
        }
        
    }
    
}
