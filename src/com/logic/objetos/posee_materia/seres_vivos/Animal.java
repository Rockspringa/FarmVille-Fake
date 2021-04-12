package com.logic.objetos.posee_materia.seres_vivos;

import javax.swing.ImageIcon;

import com.exec.Granja;
import com.gui.images.Images;
import com.logic.objetos.posee_materia.*;
import com.logic.objetos.posee_materia.productos.*;

public class Animal implements SerVivo {
    private final Producto produceDestace;
    private final Producto produceProd;
    private final boolean destazable;
    private final boolean productor;
    private final boolean omnivoro;
    private final double numParcelas;
    private final String nombre;
    private final int cantProdDestace;
    private final int cantProdProd;
    private final int precio;
    private ImageIcon imageNormal;
    private ImageIcon imageMuerto;
    private boolean vivo;
    private int tiempo;
    private int tiempoDestace;
    private int vida;

    public Animal(String nombre, double numParcelas, int precio, boolean esOmnivoro,
                        boolean esProductor, boolean esDestazable, Producto produce) {
        this.numParcelas = numParcelas;
        this.destazable = esDestazable;
        this.productor = esProductor;
        this.omnivoro = esOmnivoro;
        this.produce = produce;
        this.nombre = nombre;
        this.precio = precio;
        this.tiempoDestace = 0;
        this.tiempo = 0;
        this.vivo = true;
        this.vida = 30;

        if (this.destazable && this.productor && this.omnivoro) {
            this.imageNormal = Images.DES_PROD_OMNI_IMAGE;
            this.imageMuerto = Images.DES_PROD_OMNI_MUERTO_IMAGE;
        } else if (this.destazable && this.productor) {
            this.imageNormal = Images.DES_PROD_HER_IMAGE;
            this.imageMuerto = Images.DES_PROD_HER_MUERTO_IMAGE;
        } else if (this.destazable && this.omnivoro) {
            this.imageNormal = Images.DES_OMNI_IMAGE;
            this.imageMuerto = Images.DES_OMNI_MUERTO_IMAGE;
        } else if (this.productor && this.omnivoro) {
            this.imageNormal = Images.PROD_OMNI_IMAGE;
            this.imageMuerto = Images.PROD_OMNI_MUERTO_IMAGE;
        } else if (this.destazable) {
            this.imageNormal = Images.DES_HER_IMAGE;
            this.imageMuerto = Images.DES_HER_MUERTO_IMAGE;
        } else if (this.productor) {
            this.imageNormal = Images.PROD_HER_IMAGE;
            this.imageMuerto = Images.PROD_HER_MUERTO_IMAGE;
        }
    }

    public Animal(String vaca, int uno) {
        this.imageNormal = Images.VACA_IMAGE;
        this.imageMuerto = Images.VACA_MUERTO_IMAGE;
        this.numParcelas = 2;
        this.destazable = true;
        this.productor = true;
        this.omnivoro = false;
        this.produce = null;
        this.nombre = vaca;
        this.precio = 10;
        this.tiempoDestace = 0;
        this.tiempo = 0;
        this.vivo = true;
        this.vida = 30;
    }

    public Animal(String gallina) {
        this.imageNormal = Images.GALLINA_IMAGE;
        this.imageMuerto = Images.GALLINA_MUERTO_IMAGE;
        this.numParcelas = 0.5;
        this.destazable = true;
        this.productor = true;
        this.omnivoro = true;
        this.produce = null;
        this.nombre = gallina;
        this.precio = 4;
        this.tiempo = 0;
        this.tiempoDestace = 0;
        this.vivo = true;
        this.vida = 30;
    }    

    public Animal(Animal oldAnimal) {
        this.imageNormal = oldAnimal.imageNormal;
        this.imageMuerto = oldAnimal.imageMuerto;
        this.numParcelas = oldAnimal.numParcelas;
        this.destazable = oldAnimal.destazable;
        this.productor = oldAnimal.productor;
        this.omnivoro = oldAnimal.omnivoro;
        this.produce = oldAnimal.produce;
        this.nombre = oldAnimal.nombre;
        this.precio = oldAnimal.precio;
        this.tiempoDestace = 0;
        this.tiempo = 0;
        this.vivo = true;
        this.vida = 30;
    }

    @Override
    public String getNombre() {
        return this.nombre;
    }

    @Override
    public int getPrecio() {
        return this.precio;
    }

    @Override
    public ImageIcon getImage() {
        return (this.isAlive()) ? this.imageNormal
                                : this.imageMuerto;
    }

    public double getNumParcelas() {
        return numParcelas;
    }

    @Override
    public int getVida() {
        return this.vida;
    }

    @Override
    public void bajarVida() {
        this.tiempo++;
        this.tiempoDestace++;
        if (tiempo >= vida || tiempoDestace > 80) {
            this.vida = 0;
            this.vivo = false;
        }
    }

    @Override
    public boolean isAlive() {
        return this.vivo;
    }

    @Override
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
        if (destazable && isReadyToDead()) {
                if (this.productoCosecha instanceof Alimento)
                    bob.addProducto(new Alimento((Alimento) (this.productoCosecha)));
                else
                    bob.addProducto(new Producto(this.productoCosecha));
            this.vida = 0;
            this.vivo = false;
        }
    }

    public boolean isDestazable() {
        return this.destazable;
    }

    public boolean isReadyToDead() {
        return this.tiempoDestace > 25 && isAlive();
    }

    public void getProductos() {
        if (productor && isReadyToRecolect()) {
            Granja.bob.addProducto(this.produce);
            this.vida-= this.tiempo;
            this.tiempo -= this.tiempo;
        }
    }

    public boolean isProductor() {
        return this.productor;
    }

    public boolean isReadyToRecolect() {
        return (isProductor() && this.tiempo > 15)
                        ? (isAlive()) ? true : false : false;
    }
}
