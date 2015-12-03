/*
 * Proyecto upnaTRay
 * Computacion Grafica - Master Universitario en Ingenieria Informatica
 * Curso 2015-2016 - Universidad Publica de Navarra
 * Iñigo Aguas Ardaiz
 */
package main;

import java.awt.Color;
import java.awt.image.BufferedImage;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import primitives.Group;
import projection.Camera;
import projection.Projection;
import raytrace.Hit;
import raytrace.Ray;
import raytrace.RayGenerator;

/**
 *
 * @author inigo.aguas
 */
public class Image {
    
    int W, H; // Tamaño de la ventana
    Color backgroundColor;
    BufferedImage bimg;
    
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
     * @param cam Objeto cámara que mira la escena.
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
    
    public void putPixel(int m, int n, Color color){
        bimg.setRGB(m, H-1-n, color.getRGB());
    }   
    
    public int getWidth(){
        return W;
    }
    
    public int getHeight(){
        return H;
    } 
    
    public BufferedImage getBufferedImage(){
        return bimg;
    }
    
    
    public void dibujar(){
        JFrame frame = new JFrame();
        frame.getContentPane().add(new JLabel(new ImageIcon(this.bimg)));
        frame.pack();
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
