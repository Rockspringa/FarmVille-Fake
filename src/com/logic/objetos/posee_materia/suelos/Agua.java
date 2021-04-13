package com.logic.objetos.posee_materia.suelos;

import com.logic.objetos.*;
import com.gui.images.Images;
import com.logic.objetos.actividades.*;
import com.logic.objetos.posee_materia.*;


public class Agua extends Suelo {
    public static final double DISTRIBUCION = 0.35;
    private static double distrActual = 0;
    private static int cantAgua = 0;

    public Agua() {
        super(Images.AGUA_IMAGE);
        cantAgua++;
    }

    @Override
    public boolean sePuedeHacer(Actividad act) {
        return (act instanceof Pescar)
                    ? true : false;
    }

    public static int reinciarCantSuelos() {
        return cantAgua = 0;
    }

    public static boolean lessThanNecessary() {
        if (Suelo.getCantSuelos() != 0)
            distrActual = cantAgua / Suelo.getCantSuelos();
        return (distrActual < DISTRIBUCION)
                ? true : false;
    }
}
