/*
 * Proyecto upnaTRay
 * Computacion Grafica - Master Universitario en Ingenieria Informatica
 * Curso 2015-2016 - Universidad Publica de Navarra
 * Iñigo Aguas Ardaiz
 */
package primitives;

import javax.vecmath.Tuple4f;

/**
 * Clase para la representación de un punto en coordenadas homogéneas.
 * @author inigo.aguas
 */
public class Point3D extends Tuple4f{
    
    /** 
     * Método constructor de un punto dadas sus coordenadas NO homogéneas.
     * @param x Coordenada x.
     * @param y Coordenada y.
     * @param z Coordenada z.
     */
    public Point3D (final float x, final float y, final float z){
        super(x, y, z, 1);       
    }
    
    /** 
     * Método constructor de un punto dadas sus coordenadas homogéneas.
     * @param x Coordenada x.
     * @param y Coordenada y.
     * @param z Coordenada z.
     * @param w Coordenada homogénea.
     */
    public Point3D (final float x, final float y, final float z, final float w){
        super(x, y, z, 1);       
    }
    
    /** 
     * Método constructor de un punto a partir de otro.
     * @param P Punto P en coordenadas homogéneas.
     */
    public Point3D(Point3D P) {
        super(P);
    }

    /**
     * Método para la generación del vector resta entre un punto y otro dado.
     * @param R Punto R para rectar.
     * @return Vector resta de los dos puntos implicados.
     */
    public Vector3D substract(Point3D R) {
        return new Vector3D(this.x-R.x, this.y-R.y, this.z-R.z);
    }
}
