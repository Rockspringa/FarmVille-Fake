package com.logic.objetos.posee_materia.productos.alimentos;

import javax.swing.ImageIcon;

import com.gui.images.Images;
import com.logic.objetos.posee_materia.productos.Alimento;

/**
 * Solo sirve para identificar y separar los que se podra cosechar de las distintas plantas,
 * los granos solo lo producen los cultivos en el juego.
 */
public class Grano extends Alimento {

    /**
     * Crea un Alimento que solo puede ser producido por cultivos.
     * @param precio el coste del grano.
     * @param vidaRecuperable la vida que recuperara quien lo coma.
     * @param paraOmnivoro true limita la comida para solo omnivoros y false solo para herbivoros.
     * @param nombre el nombre que se mostrara para identificarlo.
     * @param image la imagen que lo representara.
     */
    public Grano(int precio, int vidaRecuperable, boolean paraOmnivoro, String nombre, ImageIcon image) {
        super(precio, vidaRecuperable, paraOmnivoro, nombre, image);
    }
    
    /**
     * Crea un Alimento con una imagen predefinida.
     * @param precio el coste del grano.
     * @param vidaRecuperable la vida que recuperara quien lo coma.
     * @param paraOmnivoro true limita la comida para solo omnivoros y false solo para herbivoros.
     * @param nombre el nombre que se mostrara para identificarlo.
     */
    public Grano(int precio, int vidaRecuperable, boolean paraOmnivoro, String nombre) {
        super(precio, vidaRecuperable, paraOmnivoro, nombre, Images.MAIZ_IMAGE);
    }
    
    /**
     * Crea una nueva instancia que tenga exactamente lo mismo que el grano ingresado.
     * @param grano al cual se le copiaran todas sus caracteristicas.
     */
    public Grano(Grano grano) {
        super(grano);
    }
}
