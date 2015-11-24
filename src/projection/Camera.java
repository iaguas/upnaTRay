/*
 * Proyecto upnaTRay
 * Computacion Grafica - Master Universitario en Ingenieria Informatica
 * Curso 2015-2016 - Universidad Publica de Navarra
 * Iñigo Aguas Ardaiz
 */
package projection;

import primitives.Point3D;
import primitives.Vector3D;
import raytrace.RayGenerator;

/**
 *
 * @author inigo.aguas
 */
public class Camera {

    private Object lente;// Creo que es el projection de abajo
    private Point3D viewPoint;
    private Vector3D xbase;
    private Vector3D ybase;
    private Vector3D zbase;
    private Projection lent;
    // Parámetros:
    // * Una matriz de la transformacion que permite pasar de coordenadas de 
    //   camara a coordenadas de escena (inversa de la matrix de vista)
    
    /**
     * Constructor de la cámara
     * @param viewPoint Punto de emplazamiento de la cámara.
     * @param look Vector look (vector que señala la dirección de vista).
     * @param up Vector vertical auxiliar up.
     */
    public Camera(final Point3D viewPoint, final Vector3D look, final Vector3D up){
        this.viewPoint = viewPoint;
        // TODO Calcular la base en función de look y up.
        // TODO Luego hay que crear en algún momento la matriz de transformación (transformacion.vista.pdf)
    }
    
    
    public RayGenerator getRayGenerator (final int W, final int H) {
        return lente.getRayGenerator(this, W, H); // Esto se soluciona al acabar la lente
    }
    //donde lente es el nombre del atributo empleado para referenciar a la
    // proyeccion asociada a la camara

    
}
