package com.FVF.objetos;

public abstract class Actividad {
    private static final int precio = 10;
    
    public int getPrecio() {
        return precio;
    }

    public boolean sePuede(Suelo suelo) {
        return suelo.sePuedeHacer(this);
    }

    public abstract void realizarActividad(Suelo suelo);
}
