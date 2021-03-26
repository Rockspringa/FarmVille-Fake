package com.logic.objetos.plantas;

import com.logic.objetos.*;


public class Cultivo extends Planta {
    private static final int numeroCosechas = 1;

    public Cultivo(int cantSemillas, int precioSemilla, Producto producto) {
        super(cantSemillas, precioSemilla, numeroCosechas, producto);
    }
}
