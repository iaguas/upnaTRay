/*
 * Proyecto upnaTRay
 * Computacion Grafica - Master Universitario en Ingenieria Informatica
 * Curso 2015-2016 - Universidad Publica de Navarra
 * Iñigo Aguas Ardaiz
 */
package main;

import java.awt.Color;
import java.awt.Container;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import javax.swing.JFrame;
import javax.swing.JPanel;

import parser.Parser;
import primitives.Group;
import primitives.Plane;
import primitives.Point3D;
import primitives.Sphere;
import primitives.Triangle;
import primitives.Vector3D;
import projection.Camera;
import projection.Ortographic;
import projection.Projection;
import projection.Perspective;

/**
 * Clase principal del proyecto que genera en una ventana la imagen propuesta.
 * Se le pasa la misma en un archivo y se recoge en un cuadro de texto. // TODO
 * @author inigo.aguas
 */
public class UpnaTRay {

    /**
     * Método principal del trazador que inicia la ejecución del mismo.
     * @param args the command line arguments
     * @throws java.lang.Exception
     */
    public static void main(String[] args) throws Exception{
        //Image img = basicOrtographicSphereImage();
        //Image img = basicPerspectiveSphereImage();
        //Image img = basicPerspectivePlaneImage();
        //Image img = basicOrtographicTriangleImage();
        Image img = generateImage("scenes" + File.separator + "scene0");
        JFrame canvas = new JFrame();
        canvas.setSize(img.getWidth()+16,img.getHeight()+38);
        canvas.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        canvas.setTitle("upnaTRay - imagen renderizada");
        Container pane = canvas.getContentPane();
        pane.add(new ColorPanel(img));
        canvas.setVisible(true);  
    }
    
    /**
     * Generador de una imagen para mostrar a partir de un archivo dado.
     * @param filename Ruta del archivo que contiene la descripción de la escena.
     * @return Un objeto imagen img que contiene la imagen generada.
     * @throws Exception (probablemente tendrá que ver con archivos).
     */
    private static Image generateImage(String filename) throws Exception{
        Parser parser = new Parser(new BufferedReader(new FileReader(filename)));
        Camera cam = parser.parseCamera();
        Projection proj = parser.parseProjection();
        cam.setProjection(proj);
        Color bcolor = parser.parseBackgroundColor();
        Group scene = parser.parseGroup();
        Image img = new Image(800, 800, bcolor);
        img.synthesis(scene, cam);
        return img;
    }
    
    /**
     * Generador de una imagen para probar la projección ortográfica con esferas.
     * @return Un objeto imagen img que contiene la imagen generada.
     */
    private static Image basicOrtographicSphereImage(){
        Image img = new Image(200, 200, Color.WHITE);
        Camera cam = new Camera(new Point3D(200,0,0), new Vector3D(-1,0,0), new Vector3D(0,1,0));
        Ortographic ort = new Ortographic(150, 150);
        //Perspective ort = new Perspective(50, 45, 1f);
        cam.setProjection(ort);
        Group scene = new Group();
        scene.addObject(0, new Sphere(Color.BLUE, new Point3D(0,0,0), 20));
        scene.addObject(0,new Sphere(Color.ORANGE, new Point3D(0,30,0), 30));
        img.synthesis(scene, cam);
        return img;
    }
    
    /**
     * Generador de una imagen para probar la projección en perspectiva con esferas.
     * @return Un objeto imagen img que contiene la imagen generada.
     */
    private static Image basicPerspectiveSphereImage(){
        Image img = new Image(200, 200, Color.WHITE);
        Camera cam = new Camera(new Point3D(200,0,0), new Vector3D(-1,0,0), new Vector3D(0,1,0));
        Perspective ort = new Perspective(50, 45, 1f);
        cam.setProjection(ort);
        Group scene = new Group();
        scene.addObject(0, new Sphere(Color.BLUE, new Point3D(0,0,0), 20));
        scene.addObject(0,new Sphere(Color.ORANGE, new Point3D(0,30,0), 30));
        img.synthesis(scene, cam);
        return img;
    }
    
    /**
     * Generador de una imagen para probar la projección ortográfica con triángulos.
     * @return Un objeto imagen img que contiene la imagen generada.
     */
    private static Image basicOrtographicTriangleImage(){
        Image img = new Image(200, 200, Color.WHITE);
        Camera cam = new Camera(new Point3D(200,0,0), new Vector3D(-1,0,0), new Vector3D(0.0f,1.0f,0.0f));
        Ortographic ort = new Ortographic(150, 150);
        //Perspective ort = new Perspective(50, 45, 1f);
        cam.setProjection(ort);
        Group scene = new Group();
        scene.addObject(0, new Triangle(Color.BLUE, new Point3D(50,50,0),  new Point3D(0,0,0),new Point3D(100,0,0)));
        img.synthesis(scene, cam);
        return img;
    }
    
    /**
     * Generador de una imagen para probar la projección en perspectiva con planos.
     * @return Un objeto imagen img que contiene la imagen generada.
     */
    private static Image basicPerspectivePlaneImage(){
        Image img = new Image(500, 500, Color.WHITE);
        Camera cam = new Camera(new Point3D(100,20,100), new Vector3D(-1,0,-1), new Vector3D(0.0f,1.0f,0.0f));
        //Ortographic ort = new Ortographic(50, 50);
        Perspective ort = new Perspective(10, 90, 1f);
        cam.setProjection(ort);
        Group scene = new Group();
        scene.addObject(0, new Plane(Color.GREEN, new Point3D(0,0,0), new Vector3D(1,0,0)));
        scene.addObject(1, new Plane(Color.RED, new Point3D(0,0,0), new Vector3D(0,1,0)));
        scene.addObject(2, new Plane(Color.BLUE, new Point3D(0,0,0), new Vector3D(0,0,1)));
        img.synthesis(scene, cam);
        return img;
    }    
}

/**
 * Clase para implementar la ventana en la que mostrar la imagen.
 * @author inigo.aguas
 */
class ColorPanel extends JPanel{
	
    private BufferedImage img; // Argumento en el que se incluye la definición de la imagen.
        
    /**
     * Método constructor de la clase dada una imagen.
     * @param img Imagen img dada para mostrar en la ventana.
     */
    public ColorPanel(Image img){
        this.img = img.getBufferedImage();
    }

    @Override
    public void paintComponent(Graphics g){
            super.paintComponent(g);
            Graphics2D g2d = (Graphics2D) g;
            g2d.drawImage(img, null, 0, 0);
    }
}
