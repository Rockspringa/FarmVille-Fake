package com.logic.objetos.seres_vivos;

import com.exec.Granja;
import com.logic.objetos.*;
import com.logic.objetos.productos.*;

public class Animal implements SerVivo {
    private final boolean destazable;
    private final boolean productor;
    private final boolean omnivoro;
    private final Producto produce;
    private final int numParcelas;
    private boolean vivo;
    private int vida;

    public Animal(int numParcelas, boolean esOmnivoro, boolean esProductor,
                        boolean esDestazable, Producto produce) {
        this.numParcelas = numParcelas;
        this.omnivoro = esOmnivoro;
        this.productor = esProductor;
        this.destazable = esDestazable;
        this.produce = produce;
        this.vivo = true;
        this.vida = 100;
    }

    public int getVida() {
        return this.vida;
    }

    public boolean isAlive() {
        return this.vivo;
    }

    public void alimentarse(Alimento comida) {
        if (omnivoro && this.isAlive()) {
            this.vida += (comida.isParaOmnivoro())
                            ? comida.getVidaRecuperable()
                            : 0;
        } else if (!omnivoro && this.isAlive()) {
            this.vida += (!comida.isParaOmnivoro())
                            ? comida.getVidaRecuperable()
                            : 0;
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
