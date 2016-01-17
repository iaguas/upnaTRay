/*
 * Proyecto upnaTRay
 * Computacion Grafica - Master Universitario en Ingenieria Informatica
 * Curso 2015-2016 - Universidad Publica de Navarra
 * Iñigo Aguas Ardaiz
 */
package material;

import primitives.Point3D;
import primitives.Vector3D;

/**
 *
 * @author Inigo.aguas
 */
public abstract class GlossyReflectance {
    
    /**
     * Exponente de lustre especular.
     */
    protected final float q;
    
    /**
     * Método constructor del funcional de reflectancia glossy abstracto.
     * @param q Exponente de lustre especular.
     */
    public GlossyReflectance (final float q){
        this.q = q;
    }
    
    /**
     * Obtención de la reflectancia según el método adecuado.
     * @param P Punto P sobre el que calcular la reflectancia.
     * @param normal Vector normal a la superficie.
     * @param V Posición de la cámara.
     * @param L Posición de la luz.
     * @return La reflectancia obtenida en el punto P.
     */
    public abstract float reflectance (final Point3D P, final Vector3D normal,
            final Point3D V, final Point3D L);
    
}
