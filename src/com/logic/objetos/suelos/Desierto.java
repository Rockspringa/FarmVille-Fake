package com.logic.objetos.suelos;

import com.gui.images.Images;
import com.logic.objetos.*;
import javax.swing.ImageIcon;

public class Desierto extends Suelo{
    public static final double DISTRIBUCION = 0.25;
    private static double distrActual = 0;
    private static int cantDesierto = 0;
    private ImageIcon image;

    public Desierto() {
        super();
        cantDesierto++;
        int rndImg = (int) (Math.random() * 10);
        this.image = (rndImg == 0) ? Images.DESIERTO_CACTUS_IMAGE
                    : (rndImg == 1) ? Images.DESIERTO_CRANEO_IMAGE
                                    : Images.DESIERTO_IMAGE;
    }

    @Override
    public boolean sePuedeHacer(Actividad act) {
        return false;
    }

    @Override
    public ImageIcon getImage() {
        return this.image;
    }

    public static boolean lessThanNecessary() {
        if (Suelo.getCantSuelos() != 0)
            distrActual = cantDesierto / Suelo.getCantSuelos();
        return (distrActual < DISTRIBUCION)
                ? true : false;
    }

    public static double getDistribucion() {
        return distrActual;
    }
}
