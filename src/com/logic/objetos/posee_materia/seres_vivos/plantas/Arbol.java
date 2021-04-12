package com.logic.objetos.posee_materia.seres_vivos.plantas;

import com.logic.objetos.posee_materia.productos.*;
import com.logic.objetos.posee_materia.seres_vivos.*;
import com.logic.objetos.posee_materia.*;
import com.gui.images.Images;


public class Arbol extends Planta {
    private final Producto productoCosecha;
    private final int cantCosechas;
    private int cantFertilizadas = 0;
    private int cosechaActual = 0;
    private int cantProdCosecha;

    public Arbol(String name, int cantCosechas, int cantSemillas,
                    int precioSemilla, int deadline, int cosechaTime,
                    int cantProdCosecha, Producto producto) {
        super(name, cantSemillas, precioSemilla, deadline, cosechaTime);
        this.addImage(Images.SEMILLA_IMAGE);
        this.cantProdCosecha = cantProdCosecha;
        this.productoCosecha = producto;
        this.cantCosechas = cantCosechas;
    }

    public Arbol(Arbol oldTree) {
        super(oldTree);
        this.addImage(Images.SEMILLA_IMAGE);
        this.cantProdCosecha = oldTree.cantProdCosecha;
        this.productoCosecha = oldTree.productoCosecha;
        this.cantCosechas = oldTree.cantCosechas;
    }

    @Override
    public void bajarVida() {
        if (this.isAlive()) {
            super.bajarVida();
            if (!isCosechable()) {
                this.addImage(Images.ARBOL_IMAGE);
            } else {
                this.addImage(Images.ARBOL_COSECHA_IMAGE);
            } if (!this.isAlive()) {
                this.addImage(Images.ARBOL_PODRIDO_IMAGE);
            }
        }
    }

    @Override
    public void alimentarse(Producto alimento) {
        Fertilizante fert = null;
        if (alimento instanceof Fertilizante) {
            if (cantFertilizadas <= 3 * (cosechaActual + 1)) {
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
        if (this.isAlive()) {
            for (int x = 0; x < cantProdCosecha; x++) {
                if (this.productoCosecha instanceof Alimento)
                    bob.addProducto(new Alimento((Alimento) (this.productoCosecha)));
                else
                    bob.addProducto(new Producto(this.productoCosecha));
            }
        }
        if (++this.cosechaActual >= this.cantCosechas) {
            this.morir();
        } else {
            this.addImage(Images.ARBOL_IMAGE);
        }
    }
}
