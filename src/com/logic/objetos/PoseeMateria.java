package com.logic.objetos;

import javax.swing.ImageIcon;

/**
 * La interfaz posee metodos que todo animal, planta o cosa deberia de tener para funcionar en el juego.
 * Al implementar esta interfaz abre las puertas al uso de las diferentes gui que hay. Bueno no pero es
 * utilizada en varias de estas para generaliza.
 */
public interface PoseeMateria {
    /**
     * Todo lo que tenga materia posee un precio, excepto el granjero,
     * para poder adquirilo se tendra que pagar el mismo.
     * @return un entero de la cantidad de dinero a pagar.
     */
    public abstract int getPrecio();

    /**
     * Todo lo que posee materia tiene un nombre que los identifica,
     * el metodo le pregunta al este cual es su nombre.
     * @return un string con el nombre de lo que posea materia.
     */
    public abstract String getNombre();
    
    /**
     * Si posee materia va a estar representado por una imagen.
     * @return un ImageIcon con que represente a lo que posea materia.
     */
    public abstract ImageIcon getImage();
}
