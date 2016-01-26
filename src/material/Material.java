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
 * Clase que implementa el objeto material, un propiedad de los objetos.
 * @author inigo.aguas
 */
public abstract class Material {

    /**
     * Color del que es el material.
     */
    protected Color color;
    /**
     * Función de reflectancia que utiliza el material.
     */
    protected GlossyReflectance Fs;
    final private boolean specular;
    
    /** 
     * Método constructor del material con su color, dejando constante la función de reflectancia y las propiedades del material.
     * @param color 
     */
    public Material(Color color){
        this.color = color;
        this.Fs = new Blinn(5);
        this.specular = false;
    }
    
    /** 
     * Método de acceso para conocer si un material es especular o no.
     * @return Retorna verdadero si el material es especular.
     */
    public boolean isSpecular() {
        return specular;
    }

    /**
     * Método de acceso al color del material.
     * @return El color del material.
     */
    public Color getColor() {
        return this.color;
    }
    
    /** 
     * Método de definición de la función de reflectancia.
     * @param Fs objeto Blinn o Horn que es la función de reflectancia.
     */
    public void setGlossyReflectance(GlossyReflectance Fs){
        this.Fs = Fs;
    }
    
    /** 
     * Método de acceso para el cálculo del color en cada punto de la imagen 
     * teniendo en cuenta todos los objetos así como la función de coloreado.
     * @param G Grupo de objetos de la escena.
     * @param L Grupo de luces de la escena.
     * @param P Punto P sobre el que calcular el color.
     * @param normal Vector normal a la superficie en el punto P.
     * @param V Punto V donde se situa la cámara.
     * @return Devuelve el color en el punto P teniendo en cuenta todos los objetos
     *         así como los objetos de la función de coloreado.
     */
    public abstract Color getColor(final Group G, final Lights L, final Point3D P,
            final Vector3D normal, final Point3D V);
    
    /**
     * Genera el color de un punto P de la imagen teniendo en cuenta la iluminación directa.
     * @param G Grupo de objetos de la escena.
     * @param P Punto P sobre el que calcular el color.
     * @param normal Vector normal a la superficie en el punto P.
     * @param V Punto V donde se situa la cámara.
     * @param L Punto L donde se situa las luces.
     * @return Devuelve el color en el punto P teniendo en cuenta la iluminación directa.
     */
    public abstract Color directShader(Group G, Point3D P, Vector3D normal, Point3D V, Light L);
    
    /**
     * Devuelve el color de un píxel de la imagen teniendo en cuenta la luz ambiente.
     * @param irradAmb Irradiancia ambiente de la escena
     * @return Color de un píxel de la imagen según la luz ambiente.
     */
    public abstract Color ambientColor(float irradAmb);
}
