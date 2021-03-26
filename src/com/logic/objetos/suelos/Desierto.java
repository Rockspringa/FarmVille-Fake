package com.logic.objetos.suelos;

import com.logic.objetos.*;

public class Desierto extends Suelo{
    public static final double DISTRIBUCION = 0.4;
    private static double distrActual = 0;
    private static int cantDesierto = 0;

    public Desierto() {
        super();
        cantDesierto++;
        int cantSuelos = getCantSuelos();
        distrActual = cantDesierto / cantSuelos;
    }

    public boolean sePuedeHacer(Actividad act) {
        return false;
    }

    public static double getDistribucion() {
        return distrActual;
    }
}
