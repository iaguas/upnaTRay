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
 * Clase para la representación de un vector en coordenadas homogéneas. 
 * @author inigo.aguas
 */
public class Vector3D extends Tuple4f {
   
    /** 
     * Método constructor de un vector dadas sus coordenadas NO homogéneas.
     * @param x Coordenada x.
     * @param y Coordenada y.
     * @param z Coordenada z.
     */
    public Vector3D(float x, float y, float z){
        super(x, y, z, 0);
    }
    
    /** 
     * Método constructor de un vector dadas sus coordenadas NO homogéneas.
     * @param x Coordenada x.
     * @param y Coordenada y.
     * @param z Coordenada z.
     * @param w Coordenada homogénea.
     */
    public Vector3D(float x, float y, float z, float w){
        super(x, y, z, w);
    }
    
    /**
     * Método constructor de un vector dados dos puntos entre los que se haya.
     * @param a Punto a.
     * @param b Punto b.
     */
    public Vector3D(final Point3D a, final Point3D b){
        super(b.x-a.x, b.y-a.y, b.z-a.z, 0);
    }

    /**
     * Método constructor de un vector a partir de otro.
     * @param vector 
     */
    public Vector3D(Vector3D vector) {
        super(vector);
    }

    /**
     * Normalización de un vector dado.
     * @return Vector normalizado.
     */
    public Vector3D normalize() {
        float module = (float) Math.sqrt(this.x*this.x + this.y*this.y + this.z*this.z);
        return new Vector3D(this.x/module, this.y/module, this.z/module);
    }   

    /**
     * Implementación del producto vectorial entre dos vectores (producto cruz).
     * @param vector Segundo vector sobre el que multiplicar.
     * @return Vector resultado del producto.
     */
    public Vector3D vecprod(Vector3D vector) {
        return new Vector3D(
            this.y*vector.z - this.z*vector.y,  // Coordenada x
            this.z*vector.x - this.x*vector.z,  // Coordenada y
            this.x*vector.y - this.y*vector.x); // Coordinada z
    }
    
    /**
     * Implementación del producto escalar (producto punto).
     * @param vector Segundo vector sobre el que multiplicar.
     * @return Escalar resultado del producto.
     */
    public float escprod(Vector3D vector) {
        return this.x*vector.x + this.y*vector.y + this.z*vector.z; // Se evita w por ser 0 siempre.
    }

    /**
     * Método para hacer que todas las coordenadas del vector sean multiplicadas por -1.
     * @return Vector con todas sus coordenadas multiplicadas por -1.
     */
    public Vector3D oposite(){
        return new Vector3D(-this.x, -this.y, -this.z);
    }
    
    /**
     * Implemetnación de la suma de un vector y un punto.
     * @param P Punto P que sumar.
     * @return Punto resultado de la suma de un punto y un vector.
     */
    public Point3D sumPoint(Point3D P){
        return new Point3D(this.x+P.x, this.y+P.y, this.z+P.z);
    }

    /**
     * Método para devolver el vector según la clase forma Vector3f.
     * @return Objeto de la clase Vector3f resultado.
     */
    Vector3f getVector3f() {
        return new Vector3f(this.x, this.y, this.z);
    }
}
