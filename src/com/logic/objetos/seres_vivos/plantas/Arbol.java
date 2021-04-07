package com.logic.objetos.seres_vivos.plantas;

import com.logic.objetos.seres_vivos.Granjero;
import com.logic.objetos.seres_vivos.Planta;
import com.gui.images.Images;
import com.logic.objetos.Producto;


public class Arbol extends Planta {
    private final Producto productoCosecha;
    private final int cantProdCosecha;
    private final int cantCosechas;
    private int cosechaActual = 0;

    public Arbol(String name, int cantCosechas, int cantSemillas,
                    int precioSemilla, int deadline, int cosechaTime,
                    int cantProdCosecha, Producto producto) {
        super(name, cantSemillas, precioSemilla, deadline, cosechaTime);
        this.addImage(Images.ARBOL_IMAGE);
        this.cantProdCosecha = cantProdCosecha;
        this.productoCosecha = producto;
        this.cantCosechas = cantCosechas;
    }

    @Override
    public void bajarVida() {
        if (this.isAlive()) {
            super.bajarVida();
            if (isCosechable()) {
                this.addImage(Images.ARBOL_COSECHA_IMAGE);
            } if (!this.isAlive()) {
                this.addImage(Images.ARBOL_PODRIDO_IMAGE);
            }
        }
    }

    @Override
    public void cosechar(Granjero bob) {
        for (int x = 0; x < cantProdCosecha; x++) {
            bob.addProducto(this.productoCosecha);
        }
        if (++this.cosechaActual >= this.cantCosechas) {
            this.morir();
        } else {
            this.addImage(Images.ARBOL_IMAGE);
        }
    }
}
