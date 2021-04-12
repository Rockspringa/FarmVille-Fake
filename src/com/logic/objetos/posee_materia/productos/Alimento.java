package com.logic.objetos.posee_materia.productos;

import javax.swing.ImageIcon;

import com.logic.objetos.posee_materia.*;


public class Alimento extends Producto {
    private final boolean paraOmnivoro;
    private final int vidaRecuperable;

    public Alimento(int precio, int vidaRecuperable, boolean paraOmnivoro,
                    String nombre, ImageIcon image) {
        super(precio, nombre, image);
        this.vidaRecuperable = vidaRecuperable;
        this.paraOmnivoro = paraOmnivoro;
    }

    public Alimento(Alimento oldAlimento) {
        super(oldAlimento);
        this.vidaRecuperable = oldAlimento.vidaRecuperable;
        this.paraOmnivoro = oldAlimento.paraOmnivoro;
    }

    /**
     * Los alimentos funcionan de tal manera que recuperan vida a los animales o al granjero.
     * @return un entero con la vida que recuperara.
     */
    public int getVidaRecuperable() {
        return this.vidaRecuperable;
    }

    /**
     * Los herbivoros no pueden comer carne, o comida para omnivoros mas especificamente, por eso
     * el metodo verifica que los herbivoros puedan comer este alimento.
     * @return un booleano, true para decir que si es para herbivoros o false para lo contrario.
     */
    public boolean isParaHerbivoro() {
        return !this.paraOmnivoro;
    }

    /**
     * Los omnivoros comen de todo, pero la comida para herbivoros les es prohibida por el granjero,
     * por eso el metodo verifica que los omnivoros puedan comer este alimento.
     * @return un booleano, true para decir que si es para omnivoros o false para lo contrario.
     */
    public boolean isParaOmnivoro() {
        return this.paraOmnivoro;
    }
}
