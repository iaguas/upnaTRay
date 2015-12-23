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
import java.io.FileReader;
import javax.swing.JFrame;
import javax.swing.JPanel;
import parser.Parser;
import primitives.Group;
import primitives.Point3D;
import primitives.Sphere;
import primitives.Triangle;
import primitives.Vector3D;
import projection.Camera;
import projection.Ortographic;
import projection.Projection;
import projection.Perspective;

/**
 *
 * @author inigo.aguas
 */
public class UpnaTRay {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws Exception{
        //Image img = basicOrtographicTriangleImage();
        Image img = basicOrtographicSphereImage();
        //Image img = basicPerspectiveSphereImage();
        //Image img = generateImage("scenes/scene0");
        JFrame canvas = new JFrame();
        canvas.setSize(img.getWidth()+16,img.getHeight()+38);
        canvas.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        canvas.setTitle("upnaTRay - imagen renderizada");
        Container pane = canvas.getContentPane();
        pane.add(new ColorPanel(img));
        canvas.setVisible(true);  
    }
    
    private static Image generateImage(String filename) throws Exception{
        BufferedReader br = new BufferedReader(new FileReader(filename));
        Parser parser = new Parser(br);
        Camera cam = parser.parseCamera();
        Projection proj = parser.parseProjection();
        cam.setProjection(proj);
        Color bcolor = parser.parseBackgroundColor();
        Group scene = parser.parseGroup();
        Image img = new Image(1000, 1000, bcolor);
        img.synthesis(scene, cam);
        return img;
    }
    
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
    
}

class ColorPanel extends JPanel{
	BufferedImage img;
        
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
