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
     * Método constructor de un vector dados dos puntos entre los que se haya.
     * @param a Punto a.
     * @param b Punto b.
     */
    public Vector3D(final Point3D a, final Point3D b){
        super(b.getX()-a.getX(), b.getY()-a.getY(), b.getZ()-a.getZ(), 0);
    }

    /**
     * Método constructor de un vector a partir de otro.
     * @param vector Vector original.
     */
    public Vector3D(Vector3D vector) {
        super(vector);
    }

    /**
     * Módulo del vector 
     * @return módulo del vector
     */
    public float module() {
        return (float) Math.sqrt(getX()*getX() + getY()*getY() + getZ()*getZ());
    }
    
    /**
     * Normalización de un vector dado.
     * @return Vector normalizado.
     */
    public Vector3D normalize() {
        final float module = this.module();
        final float invmodule = 1.0f / ((module == 0.0f) ? 1 : module); // Para controlar la división por 0;
        return this.multiply(invmodule);
    }    

    /**
     * Implementación del producto vectorial entre dos vectores (producto cruz).
     * @param vector Segundo vector sobre el que multiplicar.
     * @return Vector resultado del producto.
     */
    public Vector3D vecprod(Vector3D vector) {
        return new Vector3D(
            getY() * vector.getZ() - getZ() * vector.getY(),  // Coordenada x
            getZ() * vector.getX() - getX() * vector.getZ(),  // Coordenada y
            getX() * vector.getY() - getY() * vector.getX()); // Coordinada z
    }
    
    /**
     * Implementación del producto escalar (producto punto).
     * @param vector Segundo vector sobre el que multiplicar.
     * @return Escalar resultado del producto.
     */
    public float escprod(Vector3D vector) {
        return getX() * vector.getX() + 
               getY() * vector.getY() + 
               getZ() * vector.getZ(); // Se evita w por ser 0 siempre.
    }

    /**
     * Método para hacer que todas las coordenadas del vector sean multiplicadas por -1.
     * @return Vector con todas sus coordenadas multiplicadas por -1.
     */
    public Vector3D oposite() {
        return new Vector3D(-getX(), -getY(), -getZ());
    }
    
    /**
     * Implemetnación de la suma de un vector y un punto.
     * @param P Punto P que sumar.
     * @return Punto resultado de la suma de un punto y un vector.
     */
    public Point3D sumPoint(Point3D P) {
        return new Point3D(getX()+P.getX(), getY()+P.getY(), getZ()+P.getZ());
    }
    
    /** 
     * Implementación de la suma de vectores
     * @param v Vector a sumar.
     * @return La suma de los vectores argumento.
     */
    public Vector3D plus(Vector3D v) {
        return new Vector3D(this.getX()+v.getX(), this.getY()+v.getY(), this.getZ()+v.getZ());
    }
    
    /**
     * Implementación de un vector por un escalar
     * @param f Escalar
     * @return Vector v multiplicado por el escalar f.
     */
    public Vector3D multiply(float f) {
        return new Vector3D(getX()*f, getY()*f, getZ()*f);
    }
    
    /**
     * Método para devolver el vector según la clase forma Vector3f.
     * @return Objeto de la clase Vector3f resultado.
     */
    Vector3f getVector3f() {
        return new Vector3f(getX(), getY(), getZ());
    }
}
