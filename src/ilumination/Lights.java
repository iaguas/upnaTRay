/*
 * Proyecto upnaTRay
 * Computacion Grafica - Master Universitario en Ingenieria Informatica
 * Curso 2015-2016 - Universidad Publica de Navarra
 * Iñigo Aguas Ardaiz
 */
package ilumination;

import java.util.ArrayList;

/**
 * Clase que representa un grupo de luces en el espacio.
 * @author inigo.aguas
 */
public class Lights {
    
    final private ArrayList<Light> list;
    private float ambientIrradiance;
    
    /**
     * Método constructor de un grupo vacío.
     */
    public Lights(){
        list = new ArrayList<>();
    }
    
    /**
     * Método constructor sabiendo el número de objetos del grupo.
     * @param numElements Número de elementos del grupo.
     */
    public Lights(int numElements){
        list = new ArrayList<>(numElements);
    }
    
    /** 
     * Método para añadir un objeto al grupo.
     * @param index Número de objeto que se añade.
     * @param obj Objeto de tipo Light que se añade al grupo.
     */
    public void addObject (final int index, final Light obj){
        list.add(index, obj);
    }
    
    /** 
     * Método para tomar un objeto del grupo dado un número de índice.
     * @param index Índice del objeto a recobrar.
     * @return Objeto de tipo Object3D rescatado del grupo del índice dado.
     */
    public Light getObject (final int index){
        return list.get(index);
    }

    /** 
     * Método de acceso a la irradiancia ambiente.
     * @return Devuelve el valor de la irradiancia ambiente.
     */
    public float getAmbientIrradiance() {
        return 0.7f;
    }

    /**
     * Método de acceso para conocer el número de elementos del grupo.
     * @return El número de elementos del grupo.
     */
    public int getNumberElements() {
        return list.size();
    }
    
    /** 
     * Método de definición de la irradiancia ambiente.
     * @param ambIrrad Irradiancia ambiente.
     */
    public void setAmbientIrradiance(float ambIrrad) {
        ambientIrradiance = ambIrrad;
    }
}
