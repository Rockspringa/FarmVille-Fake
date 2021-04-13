package com.logic.objetos.posee_materia.seres_vivos.plantas;

import com.logic.objetos.posee_materia.productos.alimentos.*;
import com.logic.objetos.posee_materia.seres_vivos.*;
import com.logic.objetos.posee_materia.productos.*;
import com.logic.objetos.posee_materia.*;
import com.logic.array.Array;
import com.gui.images.Images;

/**
 * La clase se llama Arbol pero representa a todos aquellos nobles fruteros que dan frutas..., y que luchan por
 * dar todas las cosechas que puedan antes de morir.
 */
public class Arbol extends Planta {
    public static final Array<Fruta> arrFruta = new Array<Fruta>();
    public static final Fruta MANZANA = new Fruta(3, 2, false, "Manzana", Images.MANZANA_IMAGE);
    private final Fruta productoCosecha;
    private final int cantCosechas;
    private int cantFertilizadas = 0;
    private int cosechaActual = 0;
    private int cantProdCosecha;

    /**
     * Crea un arbol con su producto, semilla, y demas.
     * @param name es el nombre.
     * @param cantCosechas es la cantidad de veces maxima que dara fruto.
     * @param cantSemillas es la cantidad de semillas que se usan para plantarlo.
     * @param precioSemilla es lo que costara comprar cada semilla.
     * @param deadline es la cantidad de tiempo que vivira.
     * @param cosechaTime es la cantidad de tiempo que tardara en poder ser cosechado.
     * @param cantProdCosecha es la cantidad de producto que podra dar.
     * @param producto es el unico producto que va a dar, ni que fuera animal para generar madera y papel higienico.
     */
    public Arbol(String name, int cantCosechas, int cantSemillas,
                    int precioSemilla, int deadline, int cosechaTime,
                    int cantProdCosecha, Fruta producto) {
        super(name, cantSemillas, precioSemilla, deadline, cosechaTime);
        this.addImage(Images.SEMILLA_IMAGE);
        this.cantProdCosecha = cantProdCosecha;
        this.productoCosecha = producto;
        this.cantCosechas = cantCosechas;
    }

    /**
     * El metodo se encarga de mandar los ultimos deseos de su predecesor el gran arbol sabio llamado
     * oldTree, este le tranferira todas sus caracteristicas para que algun dia detenga la guerra entre
     * cultivos y arboles.
     * @param oldTree es el gran arbol sabio, predecesor del nuevo.
     */
    public Arbol(Arbol oldTree) {
        super(oldTree);
        this.addImage(Images.SEMILLA_IMAGE);
        this.cantProdCosecha = oldTree.cantProdCosecha;
        this.productoCosecha = oldTree.productoCosecha;
        this.cantCosechas = oldTree.cantCosechas;
    }

    /**
     * Le ingresa las frutas ya existentes.
     */
    public static void llenarArray() {
        if (arrFruta.length() == 0) {
            arrFruta.add(MANZANA);
        }
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
                if (this.productoCosecha instanceof Fruta)
                    bob.addProducto(new Fruta((Fruta) (this.productoCosecha)));
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
