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
 * Implementación de la lente de la cámara para crear una projección fisheye semiesférica.
 * @author inigo.aguas
 */
public class SemisphericalFisheye extends Projection{
    
    /**
     * Método constructor de la proyección ojo de pez semiesférica.
     * @param radius Radio de la circunferencia en la que se inscribe la proyección.
     */
    public SemisphericalFisheye(float radius) {
        super(radius, 2*radius, 2*radius);
    }

    @Override
    RayGenerator getRayGenerator(Camera cam, int W, int H) {
        return new SemisphericalFisheyeRayGenerator(cam, W, H);
    }
    
    /**
     * Clase interna privada para el generador de rayos de la proy. fisheye semiesférica.
     */
    private static class SemisphericalFisheyeRayGenerator extends RayGenerator {

        // Método constructor
        public SemisphericalFisheyeRayGenerator(final Camera cam, final int W, final int H) {
            super(cam, W, H);
        }
        
        @Override
        public Ray getRay(int m, int n) {
            // Paso 1. Crear un punto proyectado ortográficamente en el plano Z=0 (en -k).
            final float k = cam.getProjection().getDistance();
            final float x = (m + 0.5f) * cam.getProjection().getW()/W - cam.getProjection().getW() * 0.5f;
            final float y = (n + 0.5f) * cam.getProjection().getH()/H - cam.getProjection().getH() * 0.5f;
            final float z = -k;
            
            // Paso 2. Disponer el punto en el circulo en el que se incribe.
            final float hsquare = x*x + y*y;
            final float ksquare = k*k;
            Point3D Pstar = new Point3D(x, y, z);
            
            final float sub =  ksquare - hsquare;
            if (sub > 0){
                float s = 2 * ksquare / sub;
                
                final Point3D P = cam.toSceneCoord(new Point3D(x*s, y*s, z));
                return new Ray(P, cam.getLook());   
            }
            else{
                return new Ray(Pstar, Pstar); // Al pasar el mismo punto consigo que la dirección sea nula.
            }
        }
        
    }
    
}
