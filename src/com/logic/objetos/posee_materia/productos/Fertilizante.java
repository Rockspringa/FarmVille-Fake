package com.logic.objetos.posee_materia.productos;

import javax.swing.ImageIcon;

import com.logic.objetos.posee_materia.*;


public class Fertilizante extends Producto {
    private final int fertilizacion;
    
    public Fertilizante(int precio, int fertilizacion, String nombre, ImageIcon image) {
        super(precio, nombre, image);
        this.fertilizacion = fertilizacion;
    }

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
