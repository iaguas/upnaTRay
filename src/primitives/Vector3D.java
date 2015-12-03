/*
 * Proyecto upnaTRay
 * Computacion Grafica - Master Universitario en Ingenieria Informatica
 * Curso 2015-2016 - Universidad Publica de Navarra
 * Iñigo Aguas Ardaiz
 */
package primitives;

import javax.vecmath.Tuple4f;
import javax.vecmath.Vector3f;

/**
 *
 * @author inigo.aguas
 */
public class Vector3D extends Tuple4f {
   
    public Vector3D(float x, float y, float z){
        super(x, y, z, 0);
    }
    
    public Vector3D(float x, float y, float z, float w){
        super(x, y, z, w);
    }
    
    public Vector3D(final Point3D a, final Point3D b){
        super(b.x-a.x, b.y-a.y, b.z-a.z, 0);
    }

    public Vector3D(Vector3D vector) {
        super(vector);
    }

    public Vector3D normalize() {
        float module = (float) Math.sqrt(this.x*this.x + this.y*this.y + this.z*this.z);
        return new Vector3D(this.x/module, this.y/module, this.z/module);
    }   

    public Vector3D vecprod(Vector3D vector) {
        return new Vector3D(
            this.y*vector.z - this.z*vector.y,  // Coordenada x
            this.z*vector.x - this.x*vector.z,  // Coordenada y
            this.x*vector.y - this.y*vector.x); // Coordinada z
    }
    
    public float escprod(Vector3D vector) {
        return this.x*vector.x + this.y*vector.y + this.z*vector.z; // Se evita w por ser 0 siempre.
    }

    public Vector3D oposite(){
        return new Vector3D(-this.x, -this.y, -this.z);
    }
    
    public Point3D sumPoint(Point3D P){
        return new Point3D(this.x+P.x, this.y+P.y, this.z+P.z);
    }

    Vector3f getVector3f() {
        return new Vector3f(this.x, this.y, this.z);
    }
}
