package com.logic.objetos.posee_materia.seres_vivos.plantas;

import com.logic.objetos.posee_materia.productos.*;
import com.logic.objetos.posee_materia.seres_vivos.*;
import com.logic.objetos.posee_materia.*;
import com.gui.images.Images;


public class Cultivo extends Planta {
    private final Producto productoCosecha;
    private int cantProdCosecha;
    private int cantFertilizadas = 0;

    public Cultivo(String name, int cantSemillas,int precioSemilla,
                    int deadline, int cosechaTime,
                    int cantProdCosecha, Producto producto) {
        super(name, cantSemillas, precioSemilla, deadline, cosechaTime);
        this.addImage(Images.SEMILLA_IMAGE);
        this.cantProdCosecha = cantProdCosecha;
        this.productoCosecha = producto;
    }

    public Cultivo(Cultivo oldMata) {
        super(oldMata);
        this.addImage(Images.SEMILLA_IMAGE);
        this.cantProdCosecha = oldMata.cantProdCosecha;
        this.productoCosecha = oldMata.productoCosecha;
    }

    @Override
    public void bajarVida() {
        if (this.isAlive()) {
            super.bajarVida();
            if (!isCosechable()) {
                this.addImage(Images.CULTIVO_IMAGE);
            } else {
                this.addImage(Images.CULTIVO_COSECHA_IMAGE);
            } if (!this.isAlive()) {
                this.addImage(Images.CULTIVO_PODRIDO_IMAGE);
            }
        }
    }

    @Override
    public void alimentarse(Producto alimento) {
        Fertilizante fert = null;
        if (alimento instanceof Fertilizante) {
            if (cantFertilizadas <= 3) {
                fert = (Fertilizante) (alimento);
                cantProdCosecha += fert.getFertilizacion();
            } else {
                cantProdCosecha--;
            }
            cantFertilizadas++;
        }
    }

    @Override
    public void cosechar(Granjero bob) {
        for (int x = 0; x < cantProdCosecha; x++) {
            if (this.productoCosecha instanceof Alimento)
                bob.addProducto(new Alimento((Alimento) (this.productoCosecha)));
            else
                bob.addProducto(new Producto(this.productoCosecha));
        } this.morir();
    }
}
