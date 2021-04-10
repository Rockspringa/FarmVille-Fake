package com.logic.objetos.seres_vivos;

import com.logic.array.Array;
import com.logic.objetos.*;

public class Granjero {
    private final String nombre;
    private final String apodo;
    private Array<Producto> bodega;
    private boolean vivo;
    private int vida;
    private int oro;

    public Granjero(String nombre, String apodo) {
        this.nombre = nombre;
        this.apodo = apodo;
        this.bodega = new Array<Producto>();
    }

    public void addProducto(Producto producto) {
        this.bodega.add(producto);
    }

    public Array<Producto> getBodega() {
        return this.bodega;
    }

    public void newGame() {
        if (this.vivo == false) {
            this.vivo = true;
            this.vida = 20;
            this.setOro(100);
        }
    }

    public int getOro() {
        return this.oro;
    }

    public void setOro(int precio) {
        this.oro = (isEnough(precio)) ? this.oro - precio
                                    : this.oro;
    }

    public boolean isEnough(int precio) {
        return (precio <= this.oro) ? true : false;
    }

    public int getVida() {
        return this.vida;
    }

    public void bajarVida() {
        if (vida <= 1) {
            this.vida = 0;
            this.vivo = false;
            this.setOro(this.getOro());
        } else {
            this.vida--;
        }
    }

    public boolean isAlive() {
        return this.vivo;
    }

    public String getApodo() {
        return this.apodo;
    }

    public String getNombre() {
        return this.nombre;
    }

    public void morir() {
        this.vida = 0;
        this.vivo = false;
        this.setOro(this.getOro());
    }
}
