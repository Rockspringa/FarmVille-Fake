package com.logic.objetos.posee_materia;

import javax.swing.ImageIcon;

import com.logic.objetos.*;

public abstract class Suelo implements PoseeMateria {
    private ImageIcon image;
    private Actividad actividad;
    private static int cantSuelos = 0;
    private static final int precio = 20;

    public Suelo(ImageIcon image) {
        this.addImage(image);
        cantSuelos++;
    }

    @Override
    public String getNombre() {
        return this.getClass().getSimpleName();
    }

    public Actividad getActividad() {
        return actividad;
    }

    public void setActividad(Actividad actividad) {
        if (this.sePuedeHacer(actividad))
            this.actividad = actividad;
    }

    public abstract boolean sePuedeHacer(Actividad act);

    public Suelo() {
        cantSuelos++;
    }

    public void addImage(ImageIcon image) {
        if (this.image == null)
            this.image = image;
    }

    @Override
    public int getPrecio() {
        return precio;
    }

    public static int getCantSuelos() {
        return Suelo.cantSuelos;
    }

    @Override
    public ImageIcon getImage() {
        return (this.actividad == null)
                    ? this.image
                    : this.getActividad().getImage();
    }
    
    @Override
    public String toString() {
        return this.getClass().getSimpleName();
    }
}