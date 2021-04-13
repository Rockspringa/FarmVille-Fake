package com.logic.objetos.posee_materia;

import javax.swing.ImageIcon;

import com.logic.array.*;
import com.logic.objetos.*;
import com.gui.images.Images;
import com.logic.objetos.posee_materia.productos.*;


public class Producto implements PoseeMateria {
    public static final Alimento HUEVO = new Alimento(1, 2, false, "Huevo", Images.HUEVO_IMAGE);
    public static final Alimento LECHE = new Alimento(2, 4, false, "Leche", Images.LECHE_IMAGE);
    public static final Alimento CARNE = new Alimento(4, 4, true, "Carne", Images.CARNE_IMAGE);
    public static final Producto CUERO = new Producto(8, "Cuero", Images.CUERO_IMAGE);
    public static final Array<Producto> arrProd = new Array<Producto>();
    private final int precio;
    private final String nombre;
    private final ImageIcon image;

    public Producto(int precio, String nombre, ImageIcon image) {
        this.precio = precio;
        this.nombre = nombre;
        this.image = image;
    }

    public Producto(int precio, String nombre) {
        this.precio = precio;
        this.nombre = nombre;
        this.image = Images.CUERO_IMAGE;
    }

    public Producto(Producto oldProduct) {
        this.precio = oldProduct.precio;
        this.nombre = oldProduct.nombre;
        this.image = oldProduct.image;
    }

    public static void llenarArray() {
        if (arrProd.length() == 0) {
            arrProd.add(HUEVO);
            arrProd.add(LECHE);
            arrProd.add(CARNE);
            arrProd.add(CUERO);
        }
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

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        } else if (obj instanceof Producto
                    && obj.getClass().getSimpleName().equals(this.getClass().getSimpleName())) {
            Producto objP = (Producto) (obj);
            if (objP.getNombre().equals(this.getNombre())
                        && objP.getPrecio() == this.getPrecio()
                        && objP.getImage() == this.getImage()) {
                return true;
            }
        } return false;
    }
}
