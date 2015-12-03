/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import java.awt.Color;
import java.awt.Container;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;
import primitives.Group;
import primitives.Point3D;
import primitives.Sphere;
import primitives.Vector3D;
import projection.Camera;
import projection.Ortographic;
import projection.Perspective;

/**
 *
 * @author aguas
 */
public class UpnaTRay {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args){
        // TODO code application logic here
        
        Image img = new Image(200, 200, Color.WHITE);
        Camera cam = new Camera(new Point3D(200,0,0), new Vector3D(-1,0,0), new Vector3D(0,1,0));
        //Ortographic ort = new Ortographic(150, 150);
        Perspective ort = new Perspective(50, 45, 1f);
        cam.setProjection(ort);
        Group scene = new Group();
        scene.addObject(0, new Sphere(Color.BLUE, new Point3D(0,0,0), 20));
        scene.addObject(0,new Sphere(Color.ORANGE, new Point3D(0,30,0), 30));
        img.synthesis(scene, cam);
        //img.dibujar();
        
        //BufferedImage img = ImageIO.read( new File("prueba.jpg"));
        JFrame canvas = new JFrame();
        canvas.setSize(img.getWidth()+16,img.getHeight()+38);
        canvas.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        canvas.setTitle("upnaTRay - imagen renderizada");
        Container pane = canvas.getContentPane();
        pane.add(new ColorPanel(img));
        canvas.setVisible(true);   
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