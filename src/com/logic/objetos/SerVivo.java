package com.logic.objetos;

import javax.swing.ImageIcon;

public interface SerVivo {
    int ANIMAL = 0;
    int PLANTA = 1;
    public abstract int getVida();
    public abstract int getPrecio();
    public abstract void morir();
    public abstract void bajarVida();
    public abstract void alimentarse(Producto alimento);
    public abstract boolean isAlive();
    public abstract String getNombre();
    public abstract ImageIcon getImage();
}
