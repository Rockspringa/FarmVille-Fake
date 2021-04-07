package com.logic.objetos.seres_vivos.plantas;

import com.logic.objetos.seres_vivos.*;
import com.gui.images.Images;
import com.logic.objetos.*;


public class Cultivo extends Planta {
    private final Producto productoCosecha;
    private final int cantProdCosecha;

    public Cultivo(String name, int cantSemillas,int precioSemilla,
                    int deadline, int cosechaTime,
                    int cantProdCosecha, Producto producto) {
        super(name, cantSemillas, precioSemilla, deadline, cosechaTime);
        this.addImage(Images.CULTIVO_IMAGE);
        this.cantProdCosecha = cantProdCosecha;
        this.productoCosecha = producto;
    }

    @Override
    public void bajarVida() {
        if (this.isAlive()) {
            super.bajarVida();
            if (isCosechable()) {
                this.addImage(Images.CULTIVO_COSECHA_IMAGE);
            } if (!this.isAlive()) {
                this.addImage(Images.CULTIVO_PODRIDO_IMAGE);
            }
        }
    }

    @Override
    public void cosechar(Granjero bob) {
        for (int x = 0; x < cantProdCosecha; x++) {
            bob.addProducto(this.productoCosecha);
        } this.morir();
    }
}
