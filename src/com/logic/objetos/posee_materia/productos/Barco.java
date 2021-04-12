package com.logic.objetos.posee_materia.productos;

import com.gui.images.Images;
import com.logic.objetos.posee_materia.Producto;


public class Barco extends Producto {

    public Barco() {
        super(50, "Barco", Images.BARCO_IMAGE);
    }

    public Barco(Barco barco) {
        super(barco);
    }
}
