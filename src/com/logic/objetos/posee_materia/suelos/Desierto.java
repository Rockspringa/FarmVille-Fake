package com.logic.objetos.posee_materia.suelos;

import com.logic.objetos.posee_materia.*;
import com.gui.images.Images;
import com.logic.objetos.*;

public class Desierto extends Suelo{
    public static final double DISTRIBUCION = 0.25;
    private static double distrActual = 0;
    private static int cantDesierto = 0;

    public Desierto() {
        super();
        cantDesierto++;
        int rndImg = (int) (Math.random() * 10);
        this.addImage((rndImg == 0) ? Images.DESIERTO_CACTUS_IMAGE
                    : (rndImg == 1) ? Images.DESIERTO_CRANEO_IMAGE
                                    : Images.DESIERTO_IMAGE);
    }

    @Override
    public boolean sePuedeHacer(Actividad act) {
        return false;
    }

    public static boolean lessThanNecessary() {
        if (Suelo.getCantSuelos() != 0)
            distrActual = cantDesierto / Suelo.getCantSuelos();
        return (distrActual < DISTRIBUCION)
                ? true : false;
    }
}
