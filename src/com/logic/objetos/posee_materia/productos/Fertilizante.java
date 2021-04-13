package com.logic.objetos.posee_materia.productos;

import javax.swing.ImageIcon;

import com.gui.images.Images;
import com.logic.objetos.posee_materia.*;

/**
 * Es la clase que identifican las plantas para saber que se pueden nutrir, y aumentar, de forma antinatural,
 * la cantidad de producto que generan, este deja de hacer efecto y puede empeorar la cosecha si este se usa muchas,
 * veces.
 */
public class Fertilizante extends Producto {
    private final int fertilizacion;
    
    /**
     * Metodo que crea un Fertilizante con una imagen a eleccion, aunque no hay otra que la predeterminada.
     * @param precio es el valor comercial del fertilizante.
     * @param fertilizacion es la cantidad de productos que aumentara al fertilizar.
     * @param nombre es como se llamara.
     * @param image es la imagen que lo definira.
     */
    public Fertilizante(int precio, int fertilizacion, String nombre, ImageIcon image) {
        super(precio, nombre, image);
        this.fertilizacion = fertilizacion;
    }

    /**
     * Crea un fertilizante con la unica imagen de fertilizante que hay.
     * @param precio es el coste que tiene segun la demanda al instanciar la clase
     * @param fertilizacion es la cantidad de productos que aumentara al fertilizar.
     * @param nombre es como se llamara.
     */
    public Fertilizante(int precio, int fertilizacion, String nombre) {
        super(precio, nombre, Images.FERTILIZANTE_IMAGE);
        this.fertilizacion = fertilizacion;
    }

    /**
     * Crea un fertilizante usando un anterior fertilizante.
     * @param fert es el anterior fertilizante.
     */
    public Fertilizante(Fertilizante fert) {
        super(fert);
        this.fertilizacion = fert.fertilizacion;
    }

    /**
     * El metodo para conocer cuantos productos extra se obtendran si se fertiliza una planta
     * con este producto.
     * @return un entero que indica lo anterior.
     */
    public int getFertilizacion() {
        return this.fertilizacion;
    }
}
