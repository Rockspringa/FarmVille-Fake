package com.FVF.objetos;

public abstract class Suelo {
    private static int cantSuelos = 0;
    private static final int precio = 10;

    public Suelo() {
        cantSuelos++;
    }

    public int getPrecio() {
        return precio;
    }

    public int getCantSuelos() {
        return cantSuelos;
    }

    public abstract boolean sePuedeHacer(Actividad act);
    
    @Override
    public String toString() {
        return this.getClass().getSimpleName();
    }
}