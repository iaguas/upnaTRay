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
 *
 * @author inigo.aguas
 */
public class Group extends Object3D{
    
    ArrayList<Object3D> list;
    
    public Group(){
        super(Color.BLACK);
        list = new ArrayList<>();
    }
    
    public Group(int numElements){
        super(Color.BLACK);
        list = new ArrayList<>(numElements);
    }
    
    public void addObject (final int index, final Object3D obj){
        list.add(index, obj);
    }
    
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
    
}
