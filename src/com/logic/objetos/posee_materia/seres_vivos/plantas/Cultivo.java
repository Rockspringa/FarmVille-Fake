package com.logic.objetos.posee_materia.seres_vivos.plantas;

import com.logic.objetos.posee_materia.productos.alimentos.*;
import com.logic.objetos.posee_materia.seres_vivos.*;
import com.logic.objetos.posee_materia.productos.*;
import com.logic.objetos.posee_materia.*;
import com.gui.images.Images;
import com.logic.array.Array;

/**
 * Es la clase que genera granos, aunque no todos los cultivos generan granos, pero en este juego si,
 * asi que se debe usar para todo lo que produzca granos.
 */
public class Cultivo extends Planta {
    public static final Array<Grano> arrGranos = new Array<Grano>();
    public static final Grano MAIZ = new Grano(5, 5, false, "Maiz", Images.MAIZ_IMAGE);
    private final Grano productoCosecha;
    private int cantProdCosecha;
    private int cantFertilizadas = 0;

    /**
     * Crea un cultivo con todas sus caracteristicas, procura que no tenga las mismas que otro, para eso
     * mejor usa el otro constructor.
     * @param name es el nombre del noble guerro <code>Cultivo</code>.
     * @param cantSemillas es la cantidad de semillas de habichuelas magicas que necesitas.
     * @param precioSemilla es lo que costara la semilla que da paso a la vida del guerrero.
     * @param deadline es el tiempo que tiene para cumplir su mision..., morir cosechado.
     * @param cosechaTime es el tiempo en el que ya puede cumplir su mision.
     * @param cantProdCosecha es la cantidad de producto, no fija, que puedes cosechar.
     * @param producto es el producto que va a dar, debe de ser Grano, se explica en <code>Grano</code>.
     */
    public Cultivo(String name, int cantSemillas,int precioSemilla,
                    int deadline, int cosechaTime,
                    int cantProdCosecha, Grano producto) {
        super(name, cantSemillas, precioSemilla, deadline, cosechaTime);
        this.addImage(Images.SEMILLA_IMAGE);
        this.cantProdCosecha = cantProdCosecha;
        this.productoCosecha = producto;
    }

    /**
     * Crea un nuevo cultivo como el viejo pero vivo.
     * @param oldMata
     */
    public Cultivo(Cultivo oldMata) {
        super(oldMata);
        this.addImage(Images.SEMILLA_IMAGE);
        this.cantProdCosecha = oldMata.cantProdCosecha;
        this.productoCosecha = new Grano(oldMata.productoCosecha);
    }

    /**
     * Le ingresa las frutas ya existentes.
     */
    public static void llenarArray() {
        if (arrGranos.length() == 0) {
            arrGranos.add(MAIZ);
        }
    }

    @Override
    public void bajarVida() {
        if (this.isAlive()) {
            super.bajarVida();
            if (!isCosechable() && this.isAlive()) {
                this.addImage(Images.CULTIVO_IMAGE);
            } else if (isCosechable()) {
                this.addImage(Images.CULTIVO_COSECHA_IMAGE);
            } else {
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
        if (this.isAlive()) {
            for (int x = 0; x < cantProdCosecha; x++) {
                if (this.productoCosecha instanceof Grano)
                    bob.addProducto(new Grano((Grano) (this.productoCosecha)));
                else
                    bob.addProducto(new Producto(this.productoCosecha));
            } this.morir();
        }
    }
}
