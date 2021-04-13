package com.logic.objetos.posee_materia.productos;

import javax.swing.ImageIcon;

import com.gui.images.Images;
import com.logic.objetos.posee_materia.*;

/**
 * Los alimentos tienen distintas caracteristicas, unos pueden ser comidos por unos animales y otros no,
 * recuperaran una cantidad de vida distinta cada uno, a menos que se coloque la misma.
 */
public class Alimento extends Producto {
    private final boolean paraOmnivoro;
    private final int vidaRecuperable;

    /**
     * Crea un Alimento desde cero.
     * @param precio el coste que tendra.
     * @param vidaRecuperable la vida que recuperara a quien ose comerlo.
     * @param paraOmnivoro limita a quien puede comerlo, true solo omnivoros y false solo herbivoros.
     * @param nombre el nombre que portara y horara.
     * @param image la imagen que lo representara.
     */
    public Alimento(int precio, int vidaRecuperable, boolean paraOmnivoro,
                    String nombre, ImageIcon image) {
        super(precio, nombre, image);
        this.vidaRecuperable = vidaRecuperable;
        this.paraOmnivoro = paraOmnivoro;
    }

    /**
     * Alimento con dos posibles opciones predefinidas de imagen, para omnivoro y herbivoro.
     * @param precio el coste que tendra el alimento.
     * @param vidaRecuperable la vida que recuperara.
     * @param paraOmnivoro para cual de los dos sera, omnivoro (true) o herbivoro (false).
     * @param nombre el identificador en forma de letras y numero, caracteres en general.
     */
    public Alimento(int precio, int vidaRecuperable, boolean paraOmnivoro, String nombre) {
        super(precio, nombre, (paraOmnivoro) ? Images.CARNE_IMAGE : Images.HONGO_IMAGE);
        this.vidaRecuperable = vidaRecuperable;
        this.paraOmnivoro = paraOmnivoro;
    }

    /**
     * Imita todos los atributos del Alimento ingresado, siendo una nueva instancia.
     * @param oldAlimento
     */
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
