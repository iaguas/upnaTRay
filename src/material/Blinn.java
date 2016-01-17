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
 * Clase que implementa el método de relflectancia de Blinn.
 * @author Inigo.aguas
 */
public class Blinn extends GlossyReflectance {

    /**
     * Método constructor del funcional de reflectancia glossy de Blinn.
     * @param q 
     */
    public Blinn(final float q) {
        super(q);
    }

    @Override
    public float reflectance(final Point3D P, final Vector3D normal, final Point3D V, final Point3D L) {
        // Esto se calcula como el coseno de alpha elevado a q.
        final Vector3D v = new Vector3D(P, V);
        final Vector3D I = new Vector3D(P, L); 
        final Vector3D h = (v.plus(I)).normalize();
        final float cosineHalfAlpha = normal.escprod(h);
        return (float) Math.pow(cosineHalfAlpha, q);
    }
    
}