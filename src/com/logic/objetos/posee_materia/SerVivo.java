package com.logic.objetos.posee_materia;

import com.logic.objetos.PoseeMateria;

/**
 * La interfaz hereda la funcionalidad de <code>PoseeMateri</code> para que mantengan la funcionalidad
 * dentro del juego, los seres vivos tendran que poseer sus caracteristicas intrinsecas.
 */
public interface SerVivo extends PoseeMateria {
    int ANIMAL = 0;
    int PLANTA = 1;

    /**
     * Obtener la vida del ser vivo que heredo el metodo.
     * @return un entero de la cantidad de vida que le queda.
     */
    public abstract int getVida();

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
}
