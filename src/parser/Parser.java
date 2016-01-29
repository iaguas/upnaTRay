/*
 * Proyecto upnaTRay
 * Computacion Grafica - Master Universitario en Ingenieria Informatica
 * Curso 2015-2016 - Universidad Publica de Navarra
 * Iñigo Aguas Ardaiz
 */
package parser;

import java.awt.Color;
import java.io.BufferedReader;
import java.util.StringTokenizer;

import ilumination.CircleDirectional;
import ilumination.Light;
import ilumination.Lights;
import ilumination.Omnidirectional;
import ilumination.Spot;
import java.io.IOException;
import material.LaFortuneWilliemsMaterial;
import material.PhongMaterial;
import primitives.Group;
import primitives.Object3D;
import primitives.Plane;
import primitives.Point3D;
import primitives.Sphere;
import primitives.Triangle;
import primitives.Vector3D;
import projection.AngularFisheye;
import projection.Camera;
import projection.Ortographic;
import projection.Perspective;
import projection.Projection;
import projection.SemisphericalFisheye;

/**
 * Clase para incluir los objectos de las escenas desde un fichero.
 * @author mikel.aldaz and inigo.aguas
 * @version 2.0
 */
public class Parser {

    // ATRIBUTOS
    private final BufferedReader in;

    // METODOS
    /**
     * Método constructor del parser a partir del archivo de entrada.
     * @param in Fichero in de descripción la escena según la gramática dada.
     */
    public Parser (final BufferedReader in) {
        this.in = in;
    }

    /**
     * Método para parsear el color de fondo de la imagen.
     * @return El color en el espacio de color sRGB según la clase Color.
     * @throws java.lang.Exception Excepción lanzada.
     */
    public Color parseBackgroundColor () throws Exception {
        // Iniciar lectura
        in.readLine();

        String line;

        // Leer color
        line = in.readLine();
        final StringTokenizer s = new StringTokenizer(line);    
        s.nextToken();
        final int r = Integer.parseInt(s.nextToken());
        final int g = Integer.parseInt(s.nextToken());
        final int b = Integer.parseInt(s.nextToken());

        final Color color = new Color(r, g, b);

        line = in.readLine();

        return color;
    }

    /**
     * Lee la posicion de la camara, su vector de direccion y vertical auxiliar y
     * devuelve un objeto de camara.
     * @return El objeto camera inicializado adecuadamente.
     * @throws java.lang.Exception Excepción lanzada.
     */
    public Camera parseCamera() throws Exception {
        // Iniciar lectura
        in.readLine();

        StringTokenizer s;
        String line;
        float x, y, z;    

        // Leer posici�n
        line = in.readLine();
        s = new StringTokenizer(line);
        s.nextToken();
        x = Float.parseFloat(s.nextToken());
        y = Float.parseFloat(s.nextToken());
        z = Float.parseFloat(s.nextToken());
        Point3D cameraPos = new Point3D(x, y, z);

        // Leer direcci�n
        line = in.readLine();
        s = new StringTokenizer(line);
        s.nextToken();
        x = Float.parseFloat(s.nextToken());
        y = Float.parseFloat(s.nextToken());
        z = Float.parseFloat(s.nextToken());
        Point3D look = new Point3D(x, y, z);

        // Leer vector vertical auxiliar
        line = in.readLine();
        s = new StringTokenizer(line);
        s.nextToken();
        x = Float.parseFloat(s.nextToken());
        y = Float.parseFloat(s.nextToken());
        z = Float.parseFloat(s.nextToken());
        Vector3D up = new Vector3D(x, y, z);

        // Finalizar lectura
        line = in.readLine();

        return new Camera(cameraPos, look, up);
    }

  /**
   * Lee el tipo de proyección, y en función del tipo, las características de la
   * misma, devolviendo un objeto de tipo Projection
   * @return El objeto projection inicializado adecuadamente.
   * @throws java.lang.Exception Excepción lanzada.
   */
    public Projection parseProjection() throws Exception {

        String line = in.readLine();
        StringTokenizer s = new StringTokenizer(line);

        final String tipo = s.nextToken();
        switch (tipo) {
        case "Perspective":
        {
            // Leer distancia
            line = in.readLine();
            s = new StringTokenizer(line);
            s.nextToken();
            float distance = Float.parseFloat(s.nextToken());

            // Leer fov
            line = in.readLine();
            s = new StringTokenizer(line);
            s.nextToken();
            float fov = Float.parseFloat(s.nextToken());

            // Leer aspecto
            line = in.readLine();
            s = new StringTokenizer(line);
            s.nextToken();
            float aspect = Float.parseFloat(s.nextToken());

            // Finalizar lectura
            line = in.readLine();
            return new Perspective(distance, fov, aspect);
        }
        case "Ortographic":
        {       
            // Leer anchura
            line = in.readLine();
            s = new StringTokenizer(line);
            s.nextToken();
            float width = Float.parseFloat(s.nextToken());

            // Leer altura
            line = in.readLine();
            s = new StringTokenizer(line);
            s.nextToken();
            float height = Float.parseFloat(s.nextToken());

            // Finalizar lectura
            line = in.readLine();
            return new Ortographic(width, height);
        }
        case "SemisphericalFisheye":
        {
            // Leer distancia
            line = in.readLine();
            s = new StringTokenizer(line);
            s.nextToken();
            float distance = Float.parseFloat(s.nextToken());

            // Finalizar lectura
            line = in.readLine();

            return new SemisphericalFisheye(distance);

        }
        case "AngularFisheye":
        {
            // Leer distancia
            line = in.readLine();
            s = new StringTokenizer(line);
            s.nextToken();
            float distance = Float.parseFloat(s.nextToken());

            // Leer angulo
            line = in.readLine();
            s = new StringTokenizer(line);
            s.nextToken();
            float angle = Float.parseFloat(s.nextToken());

            // Finalizar lectura
            line = in.readLine();

            return new AngularFisheye(distance, angle);
        }
        }

        return null;

    }

    /**
     * Lee un grupo de objetos cuyo número está indicado en la primera línea.
     * TODO Comprobar si hay tantos objetos como se indica.
     * @return El objeto group inicializado adecuadamente.
     * @throws java.lang.Exception Excepción lanzada.
     */
    public Group parseGroup() throws Exception {
        String line = in.readLine();

        // Leer n�mero de elementos
        line = in.readLine();
        StringTokenizer s = new StringTokenizer(line);
        s.nextToken();
        int numElements = Integer.parseInt(s.nextToken());
        Group g = new Group(numElements);

        // Leer los elementos tantas veces como se haya le�do anteriormente
        for (int j = 0; j < numElements; ++j) {
            g.addObject(j, parseObject3DElement());
        }

        return g;
    }

    /**
     * Devuelve un objeto (Sphere, Triangle o Group) con sus parámetros inicializados.
     * @return El objecto Sphere inicializado adecuadamente.
     * @throws java.lang.Exception Excepción lanzada.
     */
    public Object3D parseObject3DElement() throws Exception {
        String line = in.readLine();
        StringTokenizer s = new StringTokenizer(line);

        final String tipo = s.nextToken();
        switch (tipo) {
        case "Sphere":
        {
            // Leer posición
            line = in.readLine();
            s = new StringTokenizer(line);
            s.nextToken();
            float x = Float.parseFloat(s.nextToken());
            float y = Float.parseFloat(s.nextToken());
            float z = Float.parseFloat(s.nextToken());

            Point3D center = new Point3D(x, y, z);

            // Leer radio
            line = in.readLine();
            s = new StringTokenizer(line);
            s.nextToken();
            final float radius = Float.parseFloat(s.nextToken());

            // Leer color
            line = in.readLine();
            s = new StringTokenizer(line);
            s.nextToken();
            final int cx = Integer.parseInt(s.nextToken());
            final int cy = Integer.parseInt(s.nextToken());
            final int cz = Integer.parseInt(s.nextToken());

            Color color = new Color(cx, cy, cz);

            // Finalizar lectura
            line = in.readLine();

            return new Sphere(new LaFortuneWilliemsMaterial(color), center, radius);
        }
        case "Triangle":
        {
            // Leer primer vertice
            line = in.readLine();
            s = new StringTokenizer(line);
            s.nextToken();
            float x = Float.parseFloat(s.nextToken());
            float y = Float.parseFloat(s.nextToken());
            float z = Float.parseFloat(s.nextToken());

            Point3D A = new Point3D(x, y, z);

            // Leer segundo vertice
            line = in.readLine();
            s = new StringTokenizer(line);
            s.nextToken();
            x = Float.parseFloat(s.nextToken());
            y = Float.parseFloat(s.nextToken());
            z = Float.parseFloat(s.nextToken());

            Point3D B = new Point3D(x, y, z);

            // Leer tercer vertice
            line = in.readLine();
            s = new StringTokenizer(line);
            s.nextToken();
            x = Float.parseFloat(s.nextToken());
            y = Float.parseFloat(s.nextToken());
            z = Float.parseFloat(s.nextToken());

            Point3D C = new Point3D(x, y, z);

            // Leer color
            line = in.readLine();
            s = new StringTokenizer(line);
            s.nextToken();
            final int cx = Integer.parseInt(s.nextToken());
            final int cy = Integer.parseInt(s.nextToken());
            final int cz = Integer.parseInt(s.nextToken());

            Color color = new Color(cx, cy, cz);

            // Finalizar lectura
            line = in.readLine();

            return new Triangle(new PhongMaterial(color), A, B, C);
        }
        case "Plane":
        {
            // Leer punto
            line = in.readLine();
            s = new StringTokenizer(line);        
            s.nextToken();
            float x = Float.parseFloat(s.nextToken());
            float y = Float.parseFloat(s.nextToken());
            float z = Float.parseFloat(s.nextToken());
            Point3D point = new Point3D(x, y, z);

            // Leer vector normal
            line = in.readLine();
            s = new StringTokenizer(line);        
            s.nextToken();
            x = Float.parseFloat(s.nextToken());
            y = Float.parseFloat(s.nextToken());
            z = Float.parseFloat(s.nextToken());
            Vector3D normal = new Vector3D(x, y, z);

            // Leer color
            line = in.readLine();
            s = new StringTokenizer(line);
            s.nextToken();
            final int cx = Integer.parseInt(s.nextToken());
            final int cy = Integer.parseInt(s.nextToken());
            final int cz = Integer.parseInt(s.nextToken());
            Color color = new Color(cx, cy, cz);

            // Finalizar lectura
            line = in.readLine();

            return new Plane(new PhongMaterial(color), point, normal);
        }
        case "Group":
        {
            // Leer número de elementos
            line = in.readLine();
            s = new StringTokenizer(line);        
            s.nextToken();
            int numElements = Integer.parseInt(s.nextToken());
            Group g = new Group(numElements);

            // Leer los elementos tantas veces como se haya le�do anteriormente
            for (int j = 0; j < numElements; ++j) {
              g.addObject(j, parseObject3DElement());
            }

            // Finalizar lectura
            line = in.readLine();

            return g;
        }      
      }

      return null;
    }

    public Lights parseLights() throws IOException {
        Lights lights = new Lights();
        in.readLine();
        
        // Leer número de elementos
        String line = in.readLine();
        StringTokenizer s = new StringTokenizer(line);
        s.nextToken();
        int numElements = Integer.parseInt(s.nextToken());
        Lights g = new Lights(numElements);
        
        // Leer la irradiancia ambiente.
        line = in.readLine();
        s = new StringTokenizer(line);        
        s.nextToken();
        lights.setAmbientIrradiance(Float.parseFloat(s.nextToken()));
        
        // Leer los elementos tantas veces como se haya leído anteriormente
        for (int j = 0; j < numElements; ++j) {
            lights.addObject(j, parseLightElement());
        }
        
        line = in.readLine();
        // Para la escena 0
        /*Omnidirectional l = new Omnidirectional(new Point3D(30,30,30), 20000.0f);
        lights.addObject(0, l);
        Spot s = new Spot(new Point3D(12,12,20), 1000.0f, (float) ((float) 2*Math.PI), new Point3D(0,0,0));
        lights.addObject(1, s);
        final Point3D a = new Point3D(0,0,0);
        final Point3D b = new Point3D(14,14,14);
        CircleDirectional d = new CircleDirectional(new Point3D(12,12,12), 3000.0f, new Vector3D(b,a), 14.0f);
        //lights.addObject(2, d);*/
        return lights;
    }

    private Light parseLightElement() throws IOException {
        String line = in.readLine();
        StringTokenizer s = new StringTokenizer(line);

        final String tipo = s.nextToken();
        switch (tipo) {
        case "Omnidirectional":
        {
            // Leer la posición
            line = in.readLine();
            s = new StringTokenizer(line);        
            s.nextToken();
            final float x = Float.parseFloat(s.nextToken());
            final float y = Float.parseFloat(s.nextToken());
            final float z = Float.parseFloat(s.nextToken());
            final Point3D position = new Point3D(x, y, z);
            
            // Leer la intensidad
            line = in.readLine();
            s = new StringTokenizer(line);        
            s.nextToken();
            final float intensity = Float.parseFloat(s.nextToken());
            
            in.readLine();
            return new Omnidirectional(position, intensity);
        }
        case "Spot":
        {
            // Leer la posición
            line = in.readLine();
            s = new StringTokenizer(line);        
            s.nextToken();
            float x = Float.parseFloat(s.nextToken());
            float y = Float.parseFloat(s.nextToken());
            float z = Float.parseFloat(s.nextToken());
            final Point3D position = new Point3D(x, y, z);
            
            // Leer la intensidad
            line = in.readLine();
            s = new StringTokenizer(line);        
            s.nextToken();
            final float intensity = Float.parseFloat(s.nextToken());
            
            // Leer el ángulo
            line = in.readLine();
            s = new StringTokenizer(line);        
            s.nextToken();
            final float angle = Float.parseFloat(s.nextToken());
            
            // Leer punto del eje
            line = in.readLine();
            s = new StringTokenizer(line);        
            s.nextToken();
            x = Float.parseFloat(s.nextToken());
            y = Float.parseFloat(s.nextToken());
            z = Float.parseFloat(s.nextToken());
            final Point3D axisPoint = new Point3D(x, y, z);
            
            // Leer la atenuación si existe (es diferente de fin de tupla, } )
            line = in.readLine();
            s = new StringTokenizer(line);        
            if(s.nextToken()!="}"){
                final float atenuation = Float.parseFloat(s.nextToken());
                return new Spot(position, intensity, angle, axisPoint, atenuation);
            }
            else {
                return new Spot(position, intensity, angle, axisPoint);
            }
        }
        case "CircleDirectional":
        {
            // Leer la posición
            line = in.readLine();
            s = new StringTokenizer(line);        
            s.nextToken();
            float x = Float.parseFloat(s.nextToken());
            float y = Float.parseFloat(s.nextToken());
            float z = Float.parseFloat(s.nextToken());
            final Point3D position = new Point3D(x, y, z);
            
            // Leer la intensidad
            line = in.readLine();
            s = new StringTokenizer(line);        
            s.nextToken();
            final float intensity = Float.parseFloat(s.nextToken());
            
            // Leer el vector de dirección
            line = in.readLine();
            s = new StringTokenizer(line);        
            s.nextToken();
            x = Float.parseFloat(s.nextToken());
            y = Float.parseFloat(s.nextToken());
            z = Float.parseFloat(s.nextToken());
            final Vector3D direction = new Vector3D(x, y, z);
           
            // Leer el radio
            line = in.readLine();
            s = new StringTokenizer(line);        
            s.nextToken();
            final float angle = Float.parseFloat(s.nextToken());
             
            in.readLine();
            return new CircleDirectional(position, intensity, direction, angle);
        }
    }
        return null;
}
}