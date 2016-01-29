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
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import ilumination.Lights;
import parser.Parser;
import primitives.Group;
import projection.Camera;
import projection.Projection;

/**
 * Clase principal del proyecto que genera en una ventana la imagen propuesta.
 * Se le pasa la misma en un archivo y se recoge en un cuadro de texto. // TODO
 * @author inigo.aguas
 */
public class UpnaTRay extends JFrame{

    private final static int W=600, H=600; // Tamaño de la ventana
    private static Image img;
    private final String pwd = System.getProperty("user.dir");
    private static Container pane;
    private static UpnaTRay canvas;
    
    public UpnaTRay() throws Exception{
        JMenuItem openOption, saveOption, closeOption; 
        
        JMenuBar menubar = new JMenuBar();
        JMenu menu = new JMenu("Opciones");
        menu.add(openOption = new JMenuItem("Abrir escena"));
        menu.add(saveOption = new JMenuItem("Guardar escena"));
        menu.addSeparator();
        menu.add(closeOption = new JMenuItem( "Salir"));
        menubar.add(menu);
        setJMenuBar(menubar);
        
        openOption.addActionListener(new MenuListener());
        saveOption.addActionListener(new MenuListener());
        closeOption.addActionListener(new MenuListener());
    }

    /**
     * Método principal del trazador que inicia la ejecución del mismo.
     * @param args the command line arguments
     * @throws java.lang.Exception Excepción lanzada.
     */
    public static void main(String[] args) throws Exception{
        img = generateImage("scenes/scene1");
        canvas = new UpnaTRay();
        canvas.setSize(W+16, H+59);
        canvas.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        canvas.setTitle("upnaTRay - imagen renderizada");
        pane = canvas.getContentPane();
        pane.add(new ImagePanel(img));
        canvas.setVisible(true);
    }
    
    private File openFile() {
        File openFile = null;
        try {
            JFileChooser fileChooser = new JFileChooser(pwd + File.separator + "scenes");
            fileChooser.showOpenDialog(this);
            openFile = fileChooser.getSelectedFile();
        }
        catch(HeadlessException ex) {
            JOptionPane.showMessageDialog(null,ex+"" + "\nNo se ha encontrado el archivo",
                 "Advertencia",JOptionPane.WARNING_MESSAGE);
        }
        return openFile;
    } 

    private File saveFile() {
        File saveFile = null;
        try {
            JFileChooser fileChooser = new JFileChooser();
            fileChooser.showSaveDialog(this);
            saveFile = fileChooser.getSelectedFile();
            String filePath = saveFile.getPath();
            if(!filePath.toLowerCase().endsWith(".png"))
            {
                saveFile = new File(filePath + ".png");
            }
        }
        catch(HeadlessException ex) {
            JOptionPane.showMessageDialog(null,"Su archivo no se ha podido guardar",
                 "Advertencia",JOptionPane.WARNING_MESSAGE);
        }
        return saveFile;
    } 
    
    /**
     * Generador de una imagen para mostrar a partir de un archivo dado.
     * @param filename Ruta del archivo que contiene la descripción de la escena.
     * @return Un objeto imagen img que contiene la imagen generada.
     * @throws Exception (probablemente tendrá que ver con archivos).
     */
    public static Image generateImage(String filename) throws Exception{
        Parser parser = new Parser(new BufferedReader(new FileReader(filename)));
        Camera cam = parser.parseCamera();
        Projection proj = parser.parseProjection();
        cam.setProjection(proj);
        Color bcolor = parser.parseBackgroundColor();
        Lights lights = parser.parseLights();
        Group scene = parser.parseGroup();
        Image image = new Image(W, H, bcolor);
        image.synthesis(scene, cam, lights);
        return image;
    }
    
    /**
     * Generador de una imagen para mostrar a partir de un archivo dado.
     * @param file Fichero que contiene la descripción de la escena.
     * @return Un objeto imagen img que contiene la imagen generada.
     * @throws Exception (probablemente tendrá que ver con archivos).
     */
    public static Image generateImage(File file) throws Exception{
        Parser parser = new Parser(new BufferedReader(new FileReader(file)));
        Camera cam = parser.parseCamera();
        Projection proj = parser.parseProjection();
        cam.setProjection(proj);
        Color bcolor = parser.parseBackgroundColor();
        Lights lights = parser.parseLights();
        Group scene = parser.parseGroup();
        Image image = new Image(W, H, bcolor);
        image.synthesis(scene, cam, lights);
        return image;
    }
    
    private class MenuListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String actionCommand = e.getActionCommand();
            if(e.getSource() instanceof JMenuItem){
                switch (actionCommand) {
                    case "Abrir escena":
                        File ofile = openFile();
                        try {
                            img = generateImage(ofile);
                        } 
                        catch (Exception ex) {
                            Logger.getLogger(UpnaTRay.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        pane.removeAll();
                        pane.add(new ImagePanel(img));
                        canvas.setVisible(true);
                        break;
                    case "Guardar escena":
                        if(img!=null){
                            File sf = saveFile();
                            try {
                                ImageIO.write(img.getBufferedImage(), "png", sf);
                            } 
                            catch (IOException ex) {
                                JOptionPane.showMessageDialog(null,"Su archivo no se ha podido guardar",
                                    "Advertencia",JOptionPane.WARNING_MESSAGE);                        
                            }
                        }
                        else
                            JOptionPane.showMessageDialog(null,"Se debe cargar una escena para poder guardarla",
                                    "Advertencia",JOptionPane.WARNING_MESSAGE);
                        break;
                    case "Salir":
                        System.exit(0);
                        break;
                }
            }
        }
    }
}

/**
 * Clase para implementar la ventana en la que mostrar la imagen.
 * @author inigo.aguas
 */
class ImagePanel extends JPanel{
	
    private final BufferedImage img; // Argumento en el que se incluye la definición de la imagen.
        
    /**
     * Método constructor de la clase dada una imagen.
     * @param img Imagen img dada para mostrar en la ventana.
     */
    public ImagePanel(Image img){
        this.img = img.getBufferedImage();
    }

    @Override
    public void paintComponent(Graphics g){
            super.paintComponent(g);
            Graphics2D g2d = (Graphics2D) g;
            g2d.drawImage(img, null, 0, 0);
    }
}