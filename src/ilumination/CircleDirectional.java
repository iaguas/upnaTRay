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
public class CircleDirectional extends Light{
    
    private final Vector3D direction;
    private final float radius;

    public CircleDirectional(Point3D position, float intensity, Vector3D direction, float radius) {
        super(position, intensity);
        this.direction = direction.normalize();
        this.radius = radius;
    }

    @Override
    public float irradiance(Group G, Point3D P, Vector3D normal) {
        Point3D Q = null; // Punto necesario para conocer el inicio del rayo.
        // 1. Determinación de inclusión del punto P en el campo iluminado.
        // Calculo si la distancia entre el punto y el más cercano del eje es mayor
        // o menor del eje.
        final Vector3D PL = new Vector3D(P, position);
        // 1a. El rayo es paralelo al cilindro.
        if(direction.dotProd(normal) == 0) { 
            final Vector3D vCrossU = direction.crossProd(normal);
            final float squareDistance = PL.dotProd(vCrossU);
            if(squareDistance > radius*radius) {
                return 0;
            }
            
            // 2a. Determinación de obstaculos por medio de rayo de sombra (paralelos).
            // Se debe utilizar como punto de referencia del rayo uno que esté situado
            // en el plano de la luz y vertical a P.
            final float NormalDotPL = PL.dotProd(normal);
            Q = (normal.multiply(NormalDotPL)).sumPoint(P);    
        }
        // 1b. El rayo no es paralelo.
        else {
            final float SquareModule = PL.dotProd(PL);
            final float NormalDotPL = PL.dotProd(normal);
            final float squareDistance = SquareModule - NormalDotPL * NormalDotPL;        
            if(squareDistance > radius*radius) {
                return 0;
            }
            
            // 2b. Determinación de obstaculos por medio de rayo de sombra (NO paralelos).
            // Se debe utilizar como punto de referencia del rayo uno que esté situado
            // en el plano de la luz y vertical a P.
            Q = (normal.multiply(NormalDotPL)).sumPoint(P);        
        }
        
        // Acabar la segunda parte con el punto obtenido.
        final Ray r = new Ray(Q, P);
        final boolean hasIntersection = G.intersect(r, P);
        if(hasIntersection){
            return 0.0f;
        }
        
        // 3. Aplicación de la fórmula de irradiancia asociada a la clase de fuente.
        final Vector3D PS = new Vector3D(P, position);
        final Vector3D I = PS.normalize();
        final float normalDotI = normal.dotProd(I);
        final float area = (float) Math.PI * radius * radius;
        return intensity / area * normalDotI;
    }
    
}