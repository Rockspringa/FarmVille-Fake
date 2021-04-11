package com.logic.objetos;

import javax.swing.ImageIcon;

public interface SerVivo {
    int ANIMAL = 0;
    int PLANTA = 1;

    /**
     * Obtener la vida del ser vivo que heredo el metodo.
     * @return un entero de la cantidad de vida que le queda.
     */
    public abstract int getVida();

    /**
     * Todos los seres vivos tienen un precio, excepto el granjero,
     * para poder adquirilos se tendra que pagar el mismo.
     * @return un entero de la cantidad de dinero a pagar.
     */
    public abstract int getPrecio();

    /**
     * El metodo sirve para que el ser vivo muera, con cada uno funciona
     * diferente, pero en general al morir ocacionara algo diferente dadas
     * las cirscunstanceas.
     */
    public abstract void morir();

    /**
     * El metodo disminuye la vida y el tiempo que va a tardar en el campo
     * el ser vivo.
     */
    public abstract void bajarVida();

    /**
     * El metodo servira para que la vida y el tiempo del ser vivo aumente,
     * o alternativamente se aumenta la cantidad de producto que va a dar el
     * ser vivo.
     * @param alimento es lo que se le dara al ser vivo, cualquier derivado de
     * producto se le puede dar, pero no cualquera va a hacer efecto.
     */
    public abstract void alimentarse(Producto alimento);

    /**
     * Comprueba si el tiempo del ser vivo ya se acabo, y por lo tanto si esta
     * muerto.
     * @return true si el ser vivo esta vivo y false si esta muerto.
     */
    public abstract boolean isAlive();

    /**
     * Todos los seres vivos deben tener un nombre, el metodo le pregunta al ser
     * vivo cual es su nombre.
     * @return un string con el nombre del ser vivo.
     */
    public abstract String getNombre();

    /**
     * Los seres vivos estan representados por una imagen.
     * @return un ImageIcon con que represente al ser vivo y su estado.
     */
    public abstract ImageIcon getImage();
}
