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

/**
 * Clase para implementar la fórmula de Phong de coloreado en los materiales.
 * @author inigo.aguas
 */
public class LaFortuneWilliemsMaterial extends Material {

    private final float ka;
    private final float ks;
    private final float kd;
    private final float invPI = (float) (1.0 / Math.PI);
    
    /**
     * Método constructor incluyendo el color y con parámetros fijos.
     * @param color Color del material.
     */
    public LaFortuneWilliemsMaterial(Color color) {
        super(color);
        ka = 0.9f;
        ks = 0.5f; // Comprobar que la suma de ks y kd son correctas en el constructor
        kd = 0.4f;
    }

    @Override
    public Color getColor(Group G, Lights L, Point3D P, Vector3D normal, Point3D V) {
        
        Color c = this.ambientColor(L.getAmbientIrradiance());
        
        // Iluminación directa (local).
        int i = 0;
        while(i < L.getNumberElements()) {
            Color col = this.directShader(G, P, normal, V, L.getObject(i++));
            final int r = (int) (col.getRed() + c.getRed());
            final int g = (int) (col.getGreen() + c.getGreen());
            final int b = (int) (col.getBlue() + c.getBlue());
            c = new Color(r>255 ? 255 : r, g>255 ? 255 : g, b>255 ? 255 : b);
	}
        
        // Ahora podría calcular la iluminación indirecta (global). 
        
       return c; 
    }

    @Override
    public Color directShader(Group G, Point3D P, Vector3D normal, Point3D V, Light L) {
        final float irradiance = L.irradiance(G, P, normal);
        final float reflectance = Fs.reflectance(P, normal, V, L.getPosition());

        final float l = irradiance * (kd * invPI + ks * Fs.getQ() + 2) / (float) (2*Math.PI) * reflectance;
        
        final int r = (int) (color.getRed() * l/3);
        final int g = (int) (color.getGreen() * l/3);
        final int b = (int) (color.getBlue() * l/3);
        return new Color(r>255 ? 255 : r, g>255 ? 255 : g, b>255 ? 255 : b);
    }
    
    @Override
    public Color ambientColor(float irradAmb) {
        final int r = (int) (color.getRed() * irradAmb/3 * ka);
        final int g = (int) (color.getGreen() * irradAmb/3 * ka);
        final int b = (int) (color.getBlue() * irradAmb/3 * ka);
        
        return new Color(r>255 ? 255 : r, g>255 ? 255 : g, b>255 ? 255 : b);
    }
    
}