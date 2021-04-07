package com.logic.array;

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

    public Tipo get(int index) throws IndexOutOfBoundsException {
        Tipo dato = null;
        if (index >= 0 && index < indexActual)
            dato = this.datos[index];
        else
            throw new IndexOutOfBoundsException();
        return dato;
    }

    public void set(Tipo dato, int index) {
        if (index >= 0 && index < indexActual)
            this.datos[index] = dato;

        else if (index == indexActual)
            this.add(dato);
            
        else
            throw new IndexOutOfBoundsException();
    }

    /**
     * 
     * @return La cantidad actual de objetos dentro del array.
     */
    public int length() {
        return this.indexActual;
    }
}
