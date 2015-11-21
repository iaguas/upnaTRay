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
public class Camera {

    private Object lente;
    
    // Parámetros:
    // * Punto de emplazamiento de la camara
    // * Tres vectores que denan una base vectorial ortonormal dextrogira
    // * Una matriz de la transformacion que permite pasar de coordenadas de 
    //   camara a coordenadas de escena (inversa de la matrix de vista)
    // * Objeto de la clase Projection asociado a la camara (define la optica
    //   de la camara)
    
    
    public RayGenerator getRayGenerator (final int W, final int H) {
        return lente.getRayGenerator(this, W, H);
    }
    //donde lente es el nombre del atributo empleado para referenciar a la
    // proyeccion asociada a la camara

    
}
