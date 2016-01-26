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

/**
 * Clase abstracta para representar a todas las fuentes luminosas. 
 * @author inigo.aguas
 */
public abstract class Light {
    
    /**
     * Punto de situación de las fuente luminosa.
     */
    protected final Point3D position;
    /**
     * Intensidad de emisión de la fuente luminosa.
     */
    protected final float intensity; // Sería más correcto asignar una intesidad a cada canal.

    /** 
     * Método constructor de las fuentes luminosas
     * @param position Punto de situación de las fuente luminosa.
     * @param intensity Intensidad de emisión de la fuente luminosa.
     */
    public Light(Point3D position, float intensity) {
        this.position = position;
        this.intensity = intensity;
    }

    /** 
     * Método de acceso a la posición de las fuentes luminosas.
     * @return El punto en el que se situa la fuente luminosa.
     */
    public Point3D getPosition() {
        return position;
    }
    
    /**
     * Calcular el valor de irradiancia de una fuente sobre un punto concreto.
     * @param G Objeto group donde se encuentra representada la escena.
     * @param P Punto P sobre el que se calcula la irradiancia.
     * @param normal Vector normal de la superficie a la que pertenece el punto P.
     * @return Devolverá la irradiancia de la fuente luminosa sobre P o 0 si:
     *      - El punto P está fuera del alcance de la fuente luminosa.
     *      - Existe un objeto que impide que los rayos lleguen al punto P (sombra).
     */
    public abstract float irradiance(Group G, Point3D P, Vector3D normal);
}
