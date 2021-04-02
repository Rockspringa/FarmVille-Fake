package com.logic.objetos.productos;

import com.logic.objetos.Producto;


public class Alimento extends Producto {
    private final boolean paraOmnivoro;
    private final boolean paraHerbivoro;
    private final int vidaRecuperable;

    public Alimento(int vidaRecuperable, boolean paraOmnivoro) {
        this.vidaRecuperable = vidaRecuperable;
        this.paraOmnivoro = paraOmnivoro;
        this.paraHerbivoro = !paraOmnivoro;
    }

    public int getVidaRecuperable() {
        return vidaRecuperable;
    }

    public boolean isParaHerbivoro() {
        return paraHerbivoro;
    }

    public boolean isParaOmnivoro() {
        return paraOmnivoro;
    }
}
