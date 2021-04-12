package com.logic.objetos.posee_materia;

import javax.swing.ImageIcon;

import com.logic.objetos.PoseeMateria;


public class Producto implements PoseeMateria {
    private final int precio;
    private final String nombre;
    private final ImageIcon image;

    public Producto(int precio, String nombre, ImageIcon image) {
        this.precio = precio;
        this.nombre = nombre;
        this.image = image;
    }

    public Producto(Producto oldProduct) {
        this.precio = oldProduct.precio;
        this.nombre = oldProduct.nombre;
        this.image = oldProduct.image;
    }

    @Override
    public int getPrecio() {
        return this.precio;
    }

    @Override
    public String getNombre() {
        return this.nombre;
    }

    @Override
    public ImageIcon getImage() {
        return this.image;
    }
}
