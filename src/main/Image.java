/*
 * Proyecto upnaTRay
 * Computacion Grafica - Master Universitario en Ingenieria Informatica
 * Curso 2015-2016 - Universidad Publica de Navarra
 * Iñigo Aguas Ardaiz
 */
package main;

import primitives.Group;
import projection.Camera;

/**
 *
 * @author inigo.aguas
 */
public class Image {
    
    /**
     * Este método realiza una iteración sobre los píexeles de la imagen para 
     * obtener su color. Se basa en BufferedImage y en el espacio de color sRGB.
     * @param scene Grupo de objectos que forman la escena.
     * @param cam Objeto cámara que mira la escena.
     */
    public void synthesis (final Group scene, final Camera cam){
        // Genera un rayo usando la cámara y la proyección proporcionadas.
        
        // Calcula la intersercción con el grupo de objectos de la escena.
        
        // Calcula el valor de color del píxel en base al resultado de la 
        // intersección y a la iluminación aplicada sobre él.
        
        // Si el rayo no choca con ningún pixel, se aplica el color de fondo.
    }
    
}
