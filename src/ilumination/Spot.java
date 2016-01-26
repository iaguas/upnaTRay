/*
 * Proyecto upnaTRay
 * Computacion Grafica - Master Universitario en Ingenieria Informatica
 * Curso 2015-2016 - Universidad Publica de Navarra
 * Iñigo Aguas Ardaiz
 */

package ilumination;

import primitives.Group;
import primitives.Point3D;
import primitives.Vector3D;
import raytrace.Ray;

/**
 * Clase para implementar la fuente luminosa spot.
 * @author inigo.aguas
 */
public class Spot extends Light{
    
    private final float angleCosine;
    private final Point3D axisPoint;

    /**
     * Método constructor de la fuente luminosa spot. 
     * @param position Posición de la fuente luminosa.
     * @param intensity Intensidad de emisión de la fuente luminosa.
     * @param angle Ángulo de apertura de la fuente luminosa spot en grados.
     * @param axisPoint Punto del eje para definir el mismo.
     */
    public Spot(Point3D position, float intensity, float angle, Point3D axisPoint) {
        super(position, intensity);
        this.angleCosine = (float) Math.cos(Math.toRadians(angle));
        this.axisPoint = axisPoint;
    }

    @Override
    public float irradiance(Group G, Point3D P, Vector3D normal) {
        // 1. Determinación de inclusión del punto P en el campo iluminado.
        // Si el coseno del ángulo entre el eje y el punto es mayor que el de la 
        // fuente, no está en el campo iluminado.
        final Vector3D LP = new Vector3D(position, P).normalize();
        final Vector3D axisVector = new Vector3D(position, axisPoint).normalize();
        final float pointCosine = LP.dotProd(axisVector);
        if(pointCosine > angleCosine) {
            return 0.0f;
        }
        
        // 2. Determinación de obstaculos por medio de rayo de sombra.
        final Ray r = new Ray(position, P);
        final boolean hasIntersection = G.intersect(r, P);
        if(hasIntersection){
            return 0.0f;
        }
        
        // 3. Aplicación de la fórmula de irradiancia asociada a la clase de fuente.
        final Vector3D PS = new Vector3D(P, position);
        final Vector3D I = PS.normalize();
        final float normalDotI = normal.dotProd(I);
        final float distanceSquare = PS.dotProd(PS);
        
        return intensity / (4 * (float) Math.PI * distanceSquare) * normalDotI;
    }
    
}