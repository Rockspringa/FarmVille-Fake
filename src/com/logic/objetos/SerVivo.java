package com.logic.objetos;

import com.logic.objetos.productos.Alimento;

public interface SerVivo {
    public abstract int getVida();
    public abstract void morir();
    public abstract void alimentarse(Alimento comida);
    public abstract boolean isAlive();
}
