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
    
    public SemisphericalFisheye(float radius) {
        this.distance = radius; // La distancia es el radio de la proyección. 
        // TODO: Debería ser siempre int para que la w y h tengan sentido.
        this.w = 2 * radius;
        this.h = 2 * radius;
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
            final float x = (m + 0.5f) * cam.getProjection().w/W - cam.getProjection().w*0.5f;
            final float y = (n + 0.5f) * cam.getProjection().h/H - cam.getProjection().h*0.5f;
            final float z = -k;
            
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
