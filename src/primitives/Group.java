/*
 * Proyecto upnaTRay
 * Computacion Grafica - Master Universitario en Ingenieria Informatica
 * Curso 2015-2016 - Universidad Publica de Navarra
 * Iñigo Aguas Ardaiz
 */
package primitives;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Iterator;
import raytrace.Hit;
import raytrace.Ray;

/**
 * Clase que representa un grupo de objetos en 3D en el espacio.
 * @author inigo.aguas
 */
public class Group extends Object3D{
    
    private ArrayList<Object3D> list;
    
    /** 
     * Método constructor sin ningún parámetro (inicializa el color de fondo a negro).
     */
    public Group(){
        super(Color.BLACK);
        list = new ArrayList<>();
    }
    
    /**
     * Método constructor sabiendo el número de objetos del grupo (inicializa el color de fondo a negro).
     * @param numElements Número de elementos del grupo.
     */
    public Group(int numElements){
        super(Color.BLACK);
        list = new ArrayList<>(numElements);
    }
    
    /** 
     * Método para añadir un objeto al grupo.
     * @param index Número de objeto que se añade.
     * @param obj Objeto de tipo Object3D que se añade al grupo.
     */
    public void addObject (final int index, final Object3D obj){
        list.add(index, obj);
    }
    
    /** 
     * Método para tomar un objeto del grupo dado un número de índice.
     * @param index Índice del objeto a recobrar.
     * @return Objeto de tipo Object3D rescatado del grupo del índice dado.
     */
    public Object3D getObject (final int index){
        return list.get(index);
    }
    
    // El metodo intersect()de esta subclase itera por los objetos almacenados 
    // en la estructura llamando a sus respectivos metodos de interseccion
    @Override
    public Hit intersect(Ray r, float tmin){
        Hit hmin = Hit.VoidHit;
        Iterator<Object3D> it = list.iterator();
	while (it.hasNext()) {
            Hit h = it.next().intersect(r, tmin);
            if(hmin.getT() > h.getT()){
                hmin = h;
            }
	}
        return hmin;
    }

    @Override
    public boolean intersect(final Ray r, final Point3D P) {
        boolean hasIntersection = false;
        Iterator<Object3D> it = list.iterator();
	while (! hasIntersection && it.hasNext()) {
            hasIntersection = it.next().intersect(r, P);
	}
        return hasIntersection;
    }
    
}
