/*
 * Proyecto upnaTRay
 * Computacion Grafica - Master Universitario en Ingenieria Informatica
 * Curso 2015-2016 - Universidad Publica de Navarra
 * Iñigo Aguas Ardaiz
 */
package main;

import ilumination.Lights;
import java.awt.Color;
import java.awt.image.BufferedImage;
import primitives.Group;
import material.Material;
import primitives.Point3D;
import primitives.Vector3D;
import projection.Camera;
import raytrace.Hit;
import raytrace.Ray;
import raytrace.RayGenerator;

/**
 * Clase imágen que incluye la formación de las imágenes 2D a través de la
 * representación 3D de los objetos de la escena.
 * @author inigo.aguas
 */
public class Image {
    
    private final int W, H; // Tamaño de la ventana
    private final Color backgroundColor;
    private final BufferedImage bimg;
    
    /**
     * Método constructor a partir del tamaño de la imagen que se desea y el 
     * color del fondo para los objetos representados en la escena.
     * @param W Anchura de la imagen en píxeles.
     * @param H Altura de la imagen en píxeles.
     * @param backColor Color del fondo de la imagen representado por la clase Color.
     */
    public Image(int W, int H, Color backColor){
        this.W = W;
        this.H = H;
        this.backgroundColor = backColor;
        this.bimg = new BufferedImage(W, H, BufferedImage.TYPE_INT_ARGB);
    }
    
    /**
     * Este método realiza una iteración sobre los píexeles de la imagen para 
     * obtener su color. Se basa en BufferedImage y en el espacio de color sRGB.
     * @param scene Grupo de objectos que forman la escena.
     * @param cam Cámara que mira la escena.
     */
    public void synthesis (final Group scene, final Camera cam){
        // Genera un rayo usando la cámara y la proyección proporcionadas.
        final float tmin = cam.getProjection().getDistance();
	final RayGenerator rg = cam.getRayGenerator(W,H);
        
        // Calcula la intersercción con el grupo de objectos de la escena.
	for (int m=0; m<W; ++m)
            for(int n=0; n<H; ++n){
                    final Ray ray = rg.getRay(m,n);
            // Calcula el valor de color del píxel en base al resultado de la 
            // intersección y a la iluminación aplicada sobre él.
                    final Hit hit = scene.intersect(ray, tmin);
                    if(hit.hits())
                        putPixel(m, n, hit.getColor());
                    else
                        // Si el rayo no choca con ningún pixel, se aplica el color de fondo.
                        putPixel(m, n, backgroundColor);
            }
    }
    
    public void synthesis (final Group scene, final Camera cam, final Lights L) {
        final float tmin = cam.getProjection().getDistance();
        final RayGenerator rg = cam.getRayGenerator(W, H);
        
        for (int m=0; m<W; ++m)
            for(int n=0; n<H; ++n){
                final Ray ray = rg.getRay(m, n);
                final Hit h = scene.intersect(ray, tmin);
                if(h.hits()){
                    final Material mat = h.getMaterial();
                    final Point3D P = h.getIntersectionPoint();
                    final Vector3D normal = h.getNormal();
                    final Point3D V = ray.getOrigin();
                    final Color color = mat.getColor(scene, L, P, normal, V);               
                    putPixel(m, n, color);
                }
                else // Si el rayo no choca con ningún pixel, se aplica el color de fondo.
                    putPixel(m, n, backgroundColor);

            }
    }
        
    /**
     * Método para disponer el color de un píxel en su localización adecuada de la imagen.
     * @param m Posición del píxel en el eje X.
     * @param n Posición del píxel en el eje Y.
     * @param color Color del píxel en el espacio sRGB según la clase Color.
     */
    public void putPixel(int m, int n, Color color){
        bimg.setRGB(m, H-1-n, color.getRGB());
    }   
    
    /**
     * Método de acceso a la anchura de la imagen.
     * @return La anchura W de la imagen en píxeles.
     */
    public int getWidth(){
        return W;
    }
    
    /** 
     * Método de acceso a la altura de la imagen.
     * @return La altura H de la imagen en píxeles.
     */
    public int getHeight(){
        return H;
    } 
    
    /**
     * Método de acceso a la forma de almacenamiento de la imagen con la clase BufferedImage.
     * @return El objeto BufferedImage que contiene los píxeles de la misma.
     */
    public BufferedImage getBufferedImage(){
        return bimg;
    }
}
