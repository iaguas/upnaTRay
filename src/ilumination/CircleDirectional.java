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
        this.direction = direction;
        this.radius = radius;
    }

    @Override
    public float irradiance(Group G, Point3D P, Vector3D normal) {
        // 1. Determinación de inclusión del punto P en el campo iluminado.
        // Calculo si la distancia entre el punto y el más cercano del eje es mayor
        // o menor del eje.
        final Vector3D RB = new Vector3D(P, position);
        if(direction.dotProd(normal) == 0) { // El rayo es paralelo al cilindro.
            final Vector3D vCrossU = direction.crossProd(normal);
            final float squareDistance = RB.dotProd(vCrossU);
            if(squareDistance > radius*radius) {
                return 0;
            }
        }
        else { // El rayo no es paralelo.
            final float SquareModule = RB.dotProd(RB);
            final float NormalDotRB = RB.dotProd(normal);
            final float squareDistance = SquareModule - NormalDotRB * NormalDotRB;        
            if(squareDistance > radius*radius) {
                return 0;
            }
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
        final float area = (float) Math.PI * radius * radius;
        return intensity / area * normalDotI;
    }
    
}