package com.logic.objetos.seres_vivos.plantas;

import com.logic.objetos.seres_vivos.*;
import com.logic.objetos.*;


public class Cultivo extends Planta {
    private static final int numeroCosechas = 1;

    public Cultivo(int cantSemillas, int precioSemilla, Producto producto) {
        super(cantSemillas, precioSemilla, numeroCosechas, producto);
    }
}
