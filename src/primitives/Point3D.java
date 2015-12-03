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

    public Point3D (final float x, final float y, final float z, final float w){
        super(x, y, z, 1);       
    }
    
    public Point3D(Point3D P) {
        super(P);
    }

    public Vector3D substract(Point3D R) {
        return new Vector3D(this.x-R.x, this.y-R.y, this.z-R.z);
    }
}
