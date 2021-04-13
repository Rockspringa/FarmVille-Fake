package com.logic.objetos.posee_materia.seres_vivos;

import javax.naming.InsufficientResourcesException;
import javax.swing.ImageIcon;

import com.exec.Granja;
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
    private int oroGanado = 0;
    private int alimGener = 0;
    private int alimConsu = 0;
    private int vida;
    private int oro;

    public Granjero(String nombre, String apodo) {
        this.nombre = nombre;
        this.apodo = apodo;
    }

    public void addProducto(Producto producto) {
        this.bodega.add(producto);
        if (producto instanceof Alimento) {
            Array<Producto> prod = Granja.getProductos();
            for (int x = 0; x < prod.length(); x++) {
                if (producto.equals(prod.get(x))) {
                    this.alimGener++;
                }
            }
        }
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
            this.vida = 200;
            this.oro = 200;
            this.oroGanado = 0;
            this.alimGener = 0;
            this.alimConsu = 0;
            this.crias = new Array<Animal>();
            this.bodega = new Array<Producto>();
            this.semillas = new Array<Planta>();
        }
    }

    public int getOro() {
        return this.oro;
    }

    public int getOroGanado() {
        return this.oroGanado;
    }

    public void setOro(int precio) throws InsufficientResourcesException {
        if (isEnough(precio))
            this.oro -= precio;
        else
            throw new InsufficientResourcesException();
    }

    public void ganaOro(int oro) {
        this.oro += oro;
        this.oroGanado += oro;
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
            this.morir();
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
        this.oro = 0;
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
                this.alimConsu++;
            }
        }
    }

    @Override
    public ImageIcon getImage() {
        return null;
    }

    public int getAlimGener() {
        return this.alimGener;
    }

    public int getAlimConsu() {
        return this.alimConsu;
    }
}
