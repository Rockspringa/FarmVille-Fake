package com.logic.array;

import javax.management.InvalidAttributeValueException;

/**
 * La clase <code>Array</code> es una estructura de datos que cumple la funcion de un
 * array dinamico, al poder autoincrementar su tamaño, la funcion de una pila y una cola,
 * al sacar del array el ultimo o el primer objeto que se ingreso, o al menos la primera
 * o ultima posicion que haya en el mismo, y la funcion de lista, de la cual se puede sacar,
 * un elemento sin importar su lugar. Se encarga de reposicionar sus elementos.
 */
public class Array<Tipo> {
    private Tipo[] datos;
    private int maxTamaño;
    private int indexActual;

    public Array() {
        this.datos = (Tipo[]) (new Object[10]); /* Downcasting de object a Tipo */ 
        this.maxTamaño = 10;
        this.indexActual = 0;
    }

    /**
     * Metodo para añadir un elemento al array.
     * @param dato es el nuevo dato, instancia, null, etc. que se añadira al array.
     */
    public void add(Tipo dato) {
        if (indexActual < maxTamaño) {
            this.datos[indexActual++] = dato;
        } else {
            maxTamaño += 10;
            Tipo[] arrAux = (Tipo[]) (new Object[this.maxTamaño]);
            for (int x = 0; x < this.indexActual; x++) {
                arrAux[x] = this.datos[x];
            } arrAux[this.indexActual] = dato;
            this.datos = arrAux;
        }
    }

    /**
     * El metodo se encarga de buscar un objeto que este dentro de la lista segun su posicion
     * en el mismo.
     * @param index es la direccion o lugar que ocupar el objeto, empezando desde 0.
     * @return un objeto del tipo que se definio al crear la lista.
     * @exception IndexOutOfBoundsException indicando que la direccion esta fuera de los limites.
     */
    public Tipo get(int index) {
        Tipo dato = null;
        if (index >= 0 && index < indexActual)
            dato = this.datos[index];
        else
            throw new IndexOutOfBoundsException();
        return dato;
    }

    /**
     * Elimina un dato de la lista, reorganizando los elementos guardados para ocupar ese espacio,
     * si este no es el ultimo.
     * @param index es la direccion o lugar que ocupar el objeto, empezando desde 0.
     * @return el objeto eliminado, del tipo que se definio al crear la lista.
     * @exception IndexOutOfBoundsException indicando que la direccion esta fuera de los limites.
     */
    public Tipo pop(int index) {
        Tipo dato = null;
        if (index >= 0 && index < indexActual) {
            dato = this.datos[index];
            for (int x = index; x < indexActual - 1; x++) {
                this.datos[x] = this.datos[x + 1];
            } this.datos[this.indexActual--] = null;
            if (this.indexActual < this.maxTamaño - 10) {
                this.maxTamaño -= 10;
            }
        } else {
            throw new IndexOutOfBoundsException();
        }
        return dato;
    }

    /**
     * Elimina un dato de la lista usando buscando la posicion del elemento, reorganizando los
     * elementos guardados para ocupar ese espacio, si este no es el ultimo. Si no se encuentra
     * el elemento dentro de la lista se lanzara una excepcion.
     * @param index es la direccion o lugar que ocupar el objeto, empezando desde 0.
     * @return el objeto eliminado, del tipo que se definio al crear la lista.
     * @exception InvalidAttibuteValueException indicando que el valor del atributo no existe.
     */
    public Tipo pop(Tipo dato) throws InvalidAttributeValueException {
        for (int x = 0; x < this.indexActual; x++) {
            if (datos[x] == dato) {
                return this.pop(x);
            }
        }
        throw new InvalidAttributeValueException();
    }

    /**
     * Busca el primer elemento que exista en la lista, este sera eliminado y su lugar
     * sera ocupado por el siguiente elemento, y asi sucesivamente, de existir un siguiente.
     * @return el objeto eliminado, del tipo que se definio al crear la lista.
     * @exception IndexOutOfBoundsException indicando que la lista esta vacia.
     */
    public Tipo getFirstInput() {
        Tipo dato = null;
        if (indexActual > 0) {
            dato = datos[0];
            for (int x = 0; x < this.indexActual - 1; x++) {
                this.datos[x] = this.datos[x + 1];
            } this.datos[this.indexActual--] = null;
            if (this.indexActual < this.maxTamaño - 10) {
                this.maxTamaño -= 10;
            }
        } else {
            throw new IndexOutOfBoundsException();
        }
        return dato;
    }

    /**
     * Busca el ultimo elemento que exista en la lista.
     * @return el objeto eliminado, del tipo que se definio al crear la lista.
     * @exception IndexOutOfBoundsException indicando que la lista esta vacia.
     */
    public Tipo getLastInput() {
        Tipo dato = null;
        if (indexActual > 0) {
            dato = this.datos[indexActual - 1];
            this.datos[indexActual--] = null;
            if (this.indexActual < this.maxTamaño - 10) {
                this.maxTamaño -= 10;
            }
        } else {
            throw new IndexOutOfBoundsException();
        }
        return dato;
    }

    /**
     * Cambia el elemento que ocupe la direccion ocupada indicada.
     * @param dato es el elemento que se guardara en la lista.
     * @param index es la direccion en donde se cambiara el elemento.
     * @exception IndexOutOfBoundsException indicando que la direccion esta fuera de los limites.
     */
    public void set(Tipo dato, int index) {
        if (index >= 0 && index < indexActual)
            this.datos[index] = dato;

        else if (index == indexActual)
            this.add(dato);
            
        else
            throw new IndexOutOfBoundsException();
    }

    /**
     * Devuelve la cantidad de elementos, actual, dentro de la lista
     * @return La cantidad actual de objetos dentro de la lista.
     */
    public int length() {
        return this.indexActual;
    }
}
