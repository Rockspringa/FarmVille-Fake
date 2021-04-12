package com.logic.objetos.posee_materia.seres_vivos;

import javax.swing.ImageIcon;

import com.logic.array.Array;
import com.logic.objetos.posee_materia.*;
import com.logic.objetos.posee_materia.productos.*;

public class Granjero implements SerVivo {
    private final String nombre;
    private final String apodo;
    private Array<Producto> bodega;
    private Array<Animal> crias;
    private Array<Planta> semillas;
    private boolean vivo;
    private int vida;
    private int oro;

    public Granjero(String nombre, String apodo) {
        this.nombre = nombre;
        this.apodo = apodo;
        this.crias = new Array<Animal>();
        this.bodega = new Array<Producto>();
        this.semillas = new Array<Planta>();
    }

    public void addProducto(Producto producto) {
        this.bodega.add(producto);
    }

    public void addCria(Animal cria) {
        this.crias.add(cria);
    }

    public void addSemilla(Planta semilla) {
        this.semillas.add(semilla);
    }

    public Array<Producto> getBodega() {
        return this.bodega;
    }

    public Array<Animal> getCrias() {
        return this.crias;
    }

    public Array<Planta> getSemillas() {
        return this.semillas;
    }

    public void newGame() {
        if (this.vivo == false) {
            this.vivo = true;
            this.vida = 20;
            this.oro = 20;
        }
    }

    public int getOro() {
        return this.oro;
    }

    public void setOro(int precio) {
        this.oro = (isEnough(precio)) ? this.oro - precio
                                    : this.oro;
    }

    public void ganaOro(int oro) {
        this.oro += oro;
    }

    public boolean isEnough(int precio) {
        return (precio <= this.oro) ? true : false;
    }

    @Override
    public int getVida() {
        return this.vida;
    }

    @Override
    public void bajarVida() {
        if (vida <= 1) {
            this.vida = 0;
            this.vivo = false;
            this.setOro(this.getOro());
        } else {
            this.vida--;
        }
    }

    @Override
    public boolean isAlive() {
        return this.vivo;
    }

    public String getApodo() {
        return this.apodo;
    }

    @Override
    public String getNombre() {
        return this.nombre;
    }

    @Override
    public void morir() {
        this.vida = 0;
        this.vivo = false;
        this.setOro(this.getOro());
    }

    @Override
    public int getPrecio() {
        return this.getOro();
    }

    @Override
    public void alimentarse(Producto alimento) {
        Alimento alim = null;
        if (alimento instanceof Alimento) {
            alim = (Alimento) (alimento);
            if (this.isAlive()) {
                this.vida += alim.getVidaRecuperable();
            }
        }
    }

    @Override
    public ImageIcon getImage() {
        return null;
    }
}
