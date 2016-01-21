/*
 * Proyecto upnaTRay
 * Computacion Grafica - Master Universitario en Ingenieria Informatica
 * Curso 2015-2016 - Universidad Publica de Navarra
 * Iñigo Aguas Ardaiz
 */
package projection;

import javax.vecmath.Matrix4f;
import javax.vecmath.Vector4f;
import primitives.Point3D;
import primitives.Vector3D;
import raytrace.RayGenerator;

/**
 * Clase que implementa la cámara del trazador de rayos a la que se le pueden 
 * "cambiar las lentes" (esto es, las proyecciones).
 * @author inigo.aguas
 */
public class Camera {

    private final Point3D viewPoint;
    private final Vector3D xbase;
    private final Vector3D ybase;
    private final Vector3D zbase;
    private Projection lent;
    private final Matrix4f transformMatrix; // De camara a escena
    
    /**
     * Método constructor de la cámara con el vector de look.
     * @param viewPoint Punto de emplazamiento de la cámara.
     * @param look Vector look (vector que señala la dirección de vista).
     * @param up Vector vertical auxiliar up.
     */
    public Camera(final Point3D viewPoint, final Vector3D look, final Vector3D up){
        // Punto de vista
        this.viewPoint = viewPoint;
        
        // Base ortonormal dextrógira 
        this.zbase = new Vector3D(look.oposite().normalize());
        this.xbase = (up.vecprod(zbase)).normalize();
        this.ybase = zbase.vecprod(xbase); // normalizar es innecesario si los otros lo están
        
        // Parámetros para la matriz de transformación
        final Vector3D oplook = look.oposite().normalize();
        final float s = up.escprod(oplook);
        final double t = Math.pow(1-s*s, -0.5f);
        
        // Creación de la matriz de transformación
        transformMatrix = new Matrix4f();
        transformMatrix.setColumn(0,
            (float) t * (up.y*oplook.z - up.z*oplook.y),
            (float) t * (up.z*oplook.x - up.x*oplook.z),
            (float) t * (up
                    .x*oplook.y - up.y*oplook.x),
            0.0f);
        transformMatrix.setColumn(1,
            (float) t * (up.x-s*oplook.x),
            (float) t * (up.y-s*oplook.y),
            (float) t * (up.z-s*oplook.z),
            0.0f);
        transformMatrix.setColumn(2, new Vector4f(oplook));
        transformMatrix.setColumn(3, new Vector4f(viewPoint));
    }
    
    /**
     * Método constructor de la cámara con el punto look y el punto de vista.
     * @param viewPoint Punto de emplazamiento de la cámara.
     * @param look Punto look (punto que señala la dirección de vista).
     * @param up Vector vertical auxiliar up.
     */
    public Camera(final Point3D viewPoint, final Point3D look, final Vector3D up){
        this(viewPoint, new Vector3D(viewPoint, look), up);
    }
    
    /**
     * Método para transformar las coordenadas de cámara a escena.
     * @param P Punto P a transformar según el sistema de coordenadas de la cámara.
     * @return Un punto cuyas coordenadas responden al sistema de coordenadas de la escena.
     */
    public Point3D toSceneCoord(Point3D P) {
        //transformMatrix.transform(R);
        
        return new Point3D(
            transformMatrix.getElement(0,0)*P.getX() + transformMatrix.getElement(0,1)*P.getY() + transformMatrix.getElement(0,2)*P.getZ() + transformMatrix.getElement(0,3)*P.getW(),
            transformMatrix.getElement(1,0)*P.getX() + transformMatrix.getElement(1,1)*P.getY() + transformMatrix.getElement(1,2)*P.getZ() + transformMatrix.getElement(1,3)*P.getW(),
            transformMatrix.getElement(2,0)*P.getX() + transformMatrix.getElement(2,1)*P.getY() + transformMatrix.getElement(2,2)*P.getZ() + transformMatrix.getElement(2,3)*P.getW(),
            transformMatrix.getElement(3,0)*P.getX() + transformMatrix.getElement(3,1)*P.getY() + transformMatrix.getElement(3,2)*P.getZ() + transformMatrix.getElement(3,3)*P.getW()
        );
    }
    
    /**
     * Método para recuperar el objeto generador de rayos (diferente según la proyección).
     * @param W Anchura de la imagen de proyección en píxeles.
     * @param H Altura de la imagen de proyección en píxeles.
     * @return Devuelve un objeto generador de rayos para el trazador con la lente dispuesta.
     */
    public RayGenerator getRayGenerator (final int W, final int H) {
        return lent.getRayGenerator(this, W, H);
    }
    
    /**
     * Método de acceso a la projección que está siendo utilizada.
     * @return Devuelve la projección utilizada.
     */
    public Projection getProjection(){
        return lent;
    }

    /**
     * Método de acceso al vector look.
     * @return Devuelve el vector look.
     */
    Vector3D getLook() {
        return new Vector3D(zbase.oposite());
    }

    /** 
     * Método de acceso al punto de emplazamiento de la cámara.
     * @return Devuelve el punto de emplazamiento de la cámara.
     */
    Point3D getPoint() {
        return new Point3D(viewPoint);
    }
    
    /**
     * Método de definición de la proyección con la que se debe llevar a cabo la renderización.
     * @param proj Un objecto de tipo Projection con la definición de la projección a utilizar en la renderización.
     */
    public void setProjection(final Projection proj){
        lent = proj;
    }
}
