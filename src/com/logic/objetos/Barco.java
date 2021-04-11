package com.logic.objetos;

import javax.swing.ImageIcon;

import com.gui.images.Images;

public class Barco implements SerVivo {

    @Override
    public int getVida() {
        return 100;
    }

    @Override
    public int getPrecio() {
        return 50;
    }

    @Override
    public void morir() {}

    @Override
    public void bajarVida() {}

    @Override
    public void alimentarse(Producto alimento) {}

    @Override
    public boolean isAlive() {
        return true;
    }

    @Override
    public String getNombre() {
        return "Barco";
    }

    @Override
    public ImageIcon getImage() {
        return Images.BARCO_IMAGE;
    }
}
