package com.logic.objetos.seres_vivos;

import com.logic.objetos.*;

public abstract class Planta {
    private final int cantSemillas;
    private final int precioSemilla;
    private final int numeroCosechas; /* Antes de morir */
    private final Producto productoCosecha;

    public Planta(int cantSemillas, int precioSemilla,
                    int numeroCosechas, Producto producto) {
        this.cantSemillas = cantSemillas;
        this.precioSemilla = precioSemilla;
        this.numeroCosechas = numeroCosechas;
        this.productoCosecha = producto;
    }

    public Producto cosechar() {
        return productoCosecha;
    }

    public int getCantSemillas() {
        return cantSemillas;
    }
    
    public int getPrecioSemilla() {
        return precioSemilla;
    }

    public int getNumeroCosechas() {
        return numeroCosechas;
    }
}
