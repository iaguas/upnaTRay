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
 * Clase para implementar la fuente luminosa omnidireccional.
 * @author inigo.aguas
 */
public class Omnidirectional extends Light{

    /**
     * Método constructor de la fuente luminosa omnidireccional 
     * @param position Posición de la fuente luminosa.
     * @param intensity Intensidad de emisión de la fuente luminosa.
     */
    public Omnidirectional(Point3D position, float intensity) {
        super(position, intensity);
    }

    @Override
    public float irradiance(Group G, Point3D P, Vector3D normal) {
        // 1. Determinación de inclusión del punto P en el campo iluminado.
        // Al ser una fuente omnidireccional descartamos este paso ya que necesariamente
        // estará incluida en su campo de iluminación.
        
        // 2. Determinación de obstaculos por medio de rayo de sombra.
        final Ray r = new Ray(position, P);
        final boolean hasIntersection = G.intersect(r, P);
        if(hasIntersection){
            //System.out.println("por aquí");
            return 0.0f;
        }
        
        // 3. Aplicación de la fórmula de irradiancia asociada a la clase de fuente.
        final Vector3D PS = new Vector3D(P, position);
        final Vector3D I = PS.normalize(); //Retiramos la irradiancia negativa que se produce con el coseno menos 1
        final float normalDotI = normal.dotProd(I);
        final float distanceSquare = PS.dotProd(PS);

        return intensity / (4 * (float) Math.PI * distanceSquare) * normalDotI;
    }
    
}