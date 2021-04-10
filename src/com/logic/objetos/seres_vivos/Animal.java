package com.logic.objetos.seres_vivos;

import javax.swing.ImageIcon;

import com.exec.Granja;
import com.logic.objetos.*;
import com.logic.objetos.productos.*;

public class Animal implements SerVivo {
    private final boolean destazable;
    private final boolean productor;
    private final boolean omnivoro;
    private final Producto produce;
    private final double numParcelas;
    private final String nombre;
    private final int precio;
    private ImageIcon image;
    private boolean vivo;
    private int vida;

    public Animal(String nombre, double numParcelas, int precio, boolean esOmnivoro,
                        boolean esProductor, boolean esDestazable, Producto produce,
                        ImageIcon image) {
        this.numParcelas = numParcelas;
        this.destazable = esDestazable;
        this.productor = esProductor;
        this.omnivoro = esOmnivoro;
        this.produce = produce;
        this.nombre = nombre;
        this.precio = precio;
        this.image = image;
        this.vivo = true;
        this.vida = 100;
    }

    public String getNombre() {
        return this.nombre;
    }

    public int getPrecio() {
        return this.precio;
    }

    public ImageIcon getImage() {
        return image;
    }

    public void setImage(ImageIcon image) {
        this.image = image;
    }

    public double getNumParcelas() {
        return numParcelas;
    }

    public int getVida() {
        return this.vida;
    }

    @Override
    public void bajarVida() {
        this.vida--;
        if (vida <= 0) {
            this.morir();
        }
    }

    public boolean isAlive() {
        return this.vivo;
    }

    public void alimentarse(Producto comida) {
        Alimento alm;
        if (comida instanceof Alimento) {
            alm = (Alimento) comida;
            if (omnivoro && this.isAlive()) {
                this.vida += (alm.isParaOmnivoro())
                                ? alm.getVidaRecuperable()
                                : 0;
            } else if (!omnivoro && this.isAlive()) {
                this.vida += (!alm.isParaOmnivoro())
                                ? alm.getVidaRecuperable()
                                : 0;
            }
        }
    }

    @Override
    public void morir() {
        if (destazable) {
            Granja.bob.addProducto(this.produce);
        }
    }

    public void getProductos() {
        if (productor) {
            Granja.bob.addProducto(this.produce);
        }
    }
}
