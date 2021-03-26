package com.logic.objetos.suelos;

import com.logic.objetos.*;
import com.logic.objetos.actividades.*;


public class Agua extends Suelo {
    public static final double DISTRIBUCION = 0.4;
    private static double distrActual = 0;
    private boolean botePescando = true;
    private static int cantAgua = 0;

    public Agua() {
        super();
        cantAgua++;
        int cantSuelos = getCantSuelos();
        distrActual = cantAgua / cantSuelos;
    }

    public boolean sePuedeHacer(Actividad act) {
        return (act instanceof Pescar)
                    ? true : false;
    }

    public static double getDistribucion() {
        return distrActual;
    }

    public void pescar() {
        if (botePescando) {
            System.out.println("Estoy pescando");
        }
    }
}
