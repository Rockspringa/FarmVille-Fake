package com.logic.objetos.posee_materia.productos;

import com.gui.images.Images;
import com.logic.objetos.posee_materia.Producto;

/**
 * Solo sirve para separar a los barcos de los productos sin que sea una animal y sin necesidad de poner
 * metodos invariables, es basicamente una clase estatica.
 */
public class Barco extends Producto {

    /**
     * Crea un barco que costara 50 de oro, que se llamara barco, y la imagen de un barco navegando.
     */
    public Barco() {
        super(50, "Barco", Images.BARCO_IMAGE);
    }

    /**
     * Crea un barco usando las caracteristicas de un barco anterior. No sirve de nada pero por si acaso.
     * @param barco es el anterior barco que pasara sus atributos con honor al siguiente barco.
     */
    public Barco(Barco barco) {
        super(barco);
    }
}
