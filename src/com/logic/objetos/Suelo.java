package com.logic.objetos;

import javax.swing.ImageIcon;

public abstract class Suelo {
    private ImageIcon image;
    private Actividad actividad;
    private static int cantSuelos = 0;
    private static final int precio = 10;

    public Suelo(ImageIcon image) {
        this.addImage(image);
        cantSuelos++;
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

    public int getPrecio() {
        return precio;
    }

    public static int getCantSuelos() {
        return Suelo.cantSuelos;
    }

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