package com.logic.objetos;

import javax.swing.ImageIcon;

public abstract class Suelo {
    private static int cantSuelos = 0;
    private static final int precio = 10;

    public Suelo() {
        cantSuelos++;
    }

    public int getPrecio() {
        return precio;
    }

    public static int getCantSuelos() {
        return Suelo.cantSuelos;
    }

    public abstract ImageIcon getImage();

    public abstract boolean sePuedeHacer(Actividad act);
    
    @Override
    public String toString() {
        return this.getClass().getSimpleName();
    }
}