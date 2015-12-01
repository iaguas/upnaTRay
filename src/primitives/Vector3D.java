/*
 * Proyecto upnaTRay
 * Computaci�n Gr�fica - M�ster Universitario en Ingenier�a Inform�tica
 * Curso 2015-2016 - Universidad P�blica de Navarra
 * I�igo Aguas Ardaiz
 *  * Each line should be prefixed with  * 
 */
package primitives;

import javax.vecmath.Tuple4f;

/**
 *
 * @author aguas
 */
public class Vector3D extends Tuple4f {
   
   public Vector3D(final Point3D a, final Point3D b){
       super(b.x-a.x, b.y-a.y, b.z-a.z, 0);
   }
    
}
