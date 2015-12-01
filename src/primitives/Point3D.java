/*
 * Proyecto upnaTRay
 * Computacion Grafica - Master Universitario en Ingenieria Informatica
 * Curso 2015-2016 - Universidad Publica de Navarra
 * Iñigo Aguas Ardaiz
 */
package primitives;

import javax.vecmath.Tuple4f;

/**
 *
 * @author inigo.aguas
 */
public class Point3D extends Tuple4f{
    
    public Point3D (final float x, final float y, final float z){
        super(x, y, z, 1);       
    }
}
