/*
 * Proyecto upnaTRay
 * Computacion Grafica - Master Universitario en Ingenieria Informatica
 * Curso 2015-2016 - Universidad Publica de Navarra
 * Iñigo Aguas Ardaiz
 */
package projection;

import raytrace.RayGenerator;

/**
 *
 * @author inigo.aguas
 */
public abstract class Projection {
    // Atributos: 
    // * Distancia d del plano de proyeccion a la camara
    // * Anchura w de la ventana de proyeccion
    // * Altura h de la ventana de proyeccion
    
    abstract RayGenerator getRayGenerator (final Camera cam, final int w, final int h);
    
}