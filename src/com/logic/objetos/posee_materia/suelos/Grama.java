package com.logic.objetos.posee_materia.suelos;

import com.logic.objetos.*;
import com.gui.images.Images;
import com.logic.objetos.actividades.*;
import com.logic.objetos.posee_materia.*;


public class Grama extends Suelo {
    private static final double DISTRIBUCION = 0.4;
    private static double distrActual = 0;
    private static int cantGrama = 0;

    public Grama() {
        super(Images.GRAMA_IMAGE);
        cantGrama++;
    }

    @Override
    public boolean sePuedeHacer(Actividad act) {
        return (act instanceof Sembrar || act instanceof Crianza)
                    ? true : false;
    }

    public static int reinciarCantSuelos() {
        return cantGrama = 0;
    }

    public static boolean lessThanNecessary() {
        if (Suelo.getCantSuelos() != 0)
            distrActual = cantGrama / Suelo.getCantSuelos();
        return (distrActual < DISTRIBUCION)
                ? true : false;
    }
}
