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
 *
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
     * Constructor de la cámara
     * @param viewPoint Punto de emplazamiento de la cámara.
     * @param look Vector look (vector que señala la dirección de vista).
     * @param up Vector vertical auxiliar up.
     */
    public Camera(final Point3D viewPoint, final Vector3D look, final Vector3D up){
        // Punto de vista
        this.viewPoint = viewPoint;
        
        // Base ortonormal dextrógira 
        this.zbase = new Vector3D(look);
        this.xbase = (up.vecprod(zbase)).normalize();
        this.ybase = zbase.vecprod(xbase);
        
        // Parámetros para la matriz de transformación
        float s = up.escprod(look);
        double t = Math.pow(1-s*s, -0.5f);
        Vector3D oplook = look.oposite();
        
        // Creación de la matriz de transformación
        transformMatrix = new Matrix4f();
        transformMatrix.setColumn(0,
            (float) t * (up.y*oplook.z - up.z*oplook.y),
            (float) t * (up.z*oplook.x - up.x*oplook.z),
            (float) t * (up.x*oplook.y - up.y*oplook.x),
            0);
        transformMatrix.setColumn(1,
            (float) t * (up.x-s*oplook.x),
            (float) t * (up.y-s*oplook.y),
            (float) t * (up.z-s*oplook.z),
            0);
        transformMatrix.setColumn(2, new Vector4f(oplook));
        transformMatrix.setColumn(3, new Vector4f(viewPoint));
    }
    
    public Point3D toSceneCoord(Point3D R) {
        //transformMatrix.transform(R);
        
        return new Point3D(
            transformMatrix.m00*R.x + transformMatrix.m01*R.y + transformMatrix.m02*R.z + transformMatrix.m03*R.w,
            transformMatrix.m10*R.x + transformMatrix.m11*R.y + transformMatrix.m12*R.z + transformMatrix.m13*R.w,
            transformMatrix.m20*R.x + transformMatrix.m21*R.y + transformMatrix.m22*R.z + transformMatrix.m23*R.w,
            transformMatrix.m30*R.x + transformMatrix.m31*R.y + transformMatrix.m32*R.z + transformMatrix.m33*R.w
        );
    }
    
    public RayGenerator getRayGenerator (final int W, final int H) {
        return lent.getRayGenerator(this, W, H);
    }
    
    /**
     * Devuelve la projección que está siendo utilizada.
     * @return En un objeto de tipo Projection es devuelta la projección.
     */
    public Projection getProjection(){
        return lent;
    }

    Vector3D getLook() {
        return new Vector3D(zbase);
    }

    Point3D getPoint() {
        return new Point3D(viewPoint);
    }
    
    /**
     * Se elije la proyección con la que se debe llevar a cabo la renderización.
     * @param proj Un objecto de tipo Projection para mostar cómo debe hacerse esta.
     */
    public void setProjection(final Projection proj){
        lent = proj;
    }
}
