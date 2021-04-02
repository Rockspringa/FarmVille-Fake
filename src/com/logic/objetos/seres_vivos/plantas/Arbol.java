package com.logic.objetos.seres_vivos.plantas;

import com.logic.objetos.seres_vivos.Planta;
import com.logic.objetos.Producto;


public class Arbol extends Planta {
    public Arbol(int cantSemillas, int precioSemilla,
                    int numeroCosechas, Producto producto) {
        super(cantSemillas, precioSemilla, numeroCosechas, producto);
    }
}
