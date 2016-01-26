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
 * Clase que implementa el método de relflectancia de Horn.
 * @author Inigo.aguas
 */
public class Horn extends GlossyReflectance {

    /**
     * Método constructor del funcional de reflectancia glossy de Horn.
     * @param q 
     */
    public Horn(final float q) {
        super(q);
    }

    @Override
    public float reflectance(final Point3D P, final Vector3D normal, final Point3D V, final Point3D L) {
        // Esto se calcula como el coseno de alpha elevado a q.
        final Vector3D v = new Vector3D(P, V).normalize();
        final Vector3D I = new Vector3D(P, L).normalize(); 
        final float cosineAlpha = 2 * I.dotProd(normal) * v.dotProd(normal) - v.dotProd(I);
        return (float) Math.pow(cosineAlpha, q);
    }
    
}
