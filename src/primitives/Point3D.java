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
        super(x, y, z, w);       
    }
    
    /** 
     * Método constructor de un punto a partir de otro.
     * @param P Punto P en coordenadas homogéneas.
     */
    public Point3D(final Point3D P) {
        super(P);
    }

    /**
     * Método para la generación del vector resta entre un punto y otro dado.
     * @param R Punto R para rectar.
     * @return Vector resta de los dos puntos implicados.
     */
    public Vector3D substract(final Point3D R) {
        return new Vector3D(this.getX()-R.getX(), this.getY()-R.getY(), this.getZ()-R.getZ());
    }
    
    /**
     * Cálculo de la distancia entre dos puntos
     * @param P Punto sobre el que calcular la distancia.
     * @return La distancia entre el punto P y el punto que llama.
     */
    public float distance(final Point3D P){
        final double xsquare = (this.getX() - P.getX()) * (this.getX() - P.getX());
        final double ysquare = (this.getY() - P.getY()) * (this.getY() - P.getY());
        final double zsquare = (this.getZ() - P.getZ()) * (this.getZ() - P.getZ());
        return (float) Math.sqrt(xsquare + ysquare + zsquare);
    }
    
    /**
     * Reescritura del método equals para los puntos
     * @param P Punto a comparar.
     * @return Si los puntos son iguales o no.
     */
    public boolean equals(final Point3D P){
        return this.getW() == P.getW() && this.getX() == P.getX() && 
                this.getY() == P.getY() && this.getZ() == P.getZ();
    }
}
