/*
 * Proyecto upnaTRay
 * Computacion Grafica - Master Universitario en Ingenieria Informatica
 * Curso 2015-2016 - Universidad Publica de Navarra
 * Iñigo Aguas Ardaiz
 */
package raytrace;

import projection.Camera;

/**
 *
 * @author inigo.aguas
 */
public abstract class RayGenerator {
    
    public RayGenerator (final Camera cam, final int W, final int H){
        
    }
    
    public abstract Ray getRay (final int m, final int n);    
   
}
