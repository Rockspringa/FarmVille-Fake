package com.logic.objetos.plantas;

import com.logic.objetos.Planta;
import com.logic.objetos.Producto;


public class Arbol extends Planta {
    public Arbol(int cantSemillas, int precioSemilla,
                    int numeroCosechas, Producto producto) {
        super(cantSemillas, precioSemilla, numeroCosechas, producto);
    }
}
