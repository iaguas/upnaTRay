/*
 * Proyecto upnaTRay
 * Computacion Grafica - Master Universitario en Ingenieria Informatica
 * Curso 2015-2016 - Universidad Publica de Navarra
 * Iñigo Aguas Ardaiz
 */
package material;

import ilumination.Light;
import ilumination.Lights;
import java.awt.Color;
import primitives.Group;
import primitives.Point3D;
import primitives.Vector3D;
import raytrace.Hit;
import raytrace.Ray;

/**
 * Clase para implementar la fórmula de Phong de coloreado en los materiales.
 * @author inigo.aguas
 */
public class PhongMaterial extends Material {

    private final float ka;
    private final float ks;
    private final float kd;
    
    /**
     * Método constructor incluyendo el color y con parámetros fijos.
     * @param color Color del material.
     */
    public PhongMaterial(Color color) {
        super(color);
        ka = 0.9f;
        ks = 0.4f;
        kd = 1.0f;
    }

    /**
     * Método constructor incluyendo el color y con parámetros de la ecuación.
     * @param color Color del material.
     * @param ka Parámetro de la luz ambiental.
     * @param ks Parámetro de la luz especular.
     * @param kd Parámetro de la luz directa.
     */
    public PhongMaterial(Color color, float ka, float ks, float kd) {
        super(color);
        this.ka = ka;
        this.ks = ks;
        this.kd = kd;
    }
    
    @Override
    public Color getColor(Group G, Lights L, Point3D P, Vector3D normal, Point3D V, int it) {
        
        Color c = this.ambientColor(L.getAmbientIrradiance());
        
        // Iluminación directa (local).
        int i = 0;
        while(i < L.getNumberElements()) {
            final Color col = this.directShader(G, P, normal, V, L.getObject(i++));
            final int r = (int) (col.getRed() + c.getRed());
            final int g = (int) (col.getGreen() + c.getGreen());
            final int b = (int) (col.getBlue() + c.getBlue());
            c = new Color(r>255 ? 255 : (r<0 ? 0 : r), g>255 ? 255 : (g<0 ? 0 : g), b>255 ? 255 : (b<0 ? 0 : b));
	}
        
        // Ahora podría calcular la iluminación indirecta (global). 
        if(it>0) {
            if (this.isSpecular()) {
                // Generar el rayo secundario rmirror especular ideal.
                Vector3D v = (new Vector3D(P,V)).normalize();
                Vector3D rayDirector = (v.minus(normal.multiply(2 * normal.dotProd(v)))).oposite();
                final Ray rmirror = new Ray(P, rayDirector);
                //System.out.println("Rayos. Sombra: " + v.dotProd(normal) + " Reflejado: " + rayDirector.dotProd(normal));
                final Hit h = G.intersect(rmirror, 0);
                if(h.hits()){                
                    final Material mat = h.getMaterial();
                    final Point3D Ps = h.getIntersectionPoint();
                    final Vector3D ns = h.getNormal();
                    final Vector3D nprime = normal.multiply(0.00001f);
                    final Point3D Pprime = new Point3D(nprime.sumPoint(P));
                    final Color cs = getColor(G, L, Ps, ns, Pprime, it-1);
                    
                    final int r = (int) (ks*cs.getRed() + (1-ks)*c.getRed());
                    final int g = (int) (ks*cs.getGreen() + (1-ks)*c.getGreen());
                    final int b = (int) (ks*cs.getBlue() + (1-ks)*c.getBlue());
                    c = new Color(r>255 ? 255 : (r<0 ? 0 : r), g>255 ? 255 : (g<0 ? 0 : g), b>255 ? 255 : (b<0 ? 0 : b));
                }
            }
        }
        
       return c; 
    }

    @Override
    public Color directShader(Group G, Point3D P, Vector3D normal, Point3D V, Light L) {
        final float irradiance = L.irradiance(G, P, normal);
        final float nDotI = normal.dotProd(new Vector3D(P, L.getPosition()).normalize()); // Se pueden ahorrar operacines con el modulo
        final float reflectance = Fs.reflectance(P, normal, V, L.getPosition());
        
        final float l = Math.abs(irradiance * (kd + ks * reflectance / nDotI));
        
        final int r = (int) (color.getRed() * l/3);
        final int g = (int) (color.getGreen() * l/3);
        final int b = (int) (color.getBlue() * l/3);
        return new Color(r>255 ? 255 : (r<0 ? 0 : r), g>255 ? 255 : (g<0 ? 0 : g), b>255 ? 255 : (b<0 ? 0 : b));
    }
    
    @Override
    public Color ambientColor(float irradAmb) {
        final int r = (int) (color.getRed() * irradAmb/3 * ka);
        final int g = (int) (color.getGreen() * irradAmb/3 * ka);
        final int b = (int) (color.getBlue() * irradAmb/3 * ka);
        
        return new Color(r>255 ? 255 : (r<0 ? 0 : r), g>255 ? 255 : (g<0 ? 0 : g), b>255 ? 255 : (b<0 ? 0 : b));
    }
    
}
