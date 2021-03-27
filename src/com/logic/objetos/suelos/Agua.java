package com.logic.objetos.suelos;

import com.logic.objetos.*;
import com.gui.images.Images;
import javax.swing.ImageIcon;
import com.logic.objetos.actividades.*;


public class Agua extends Suelo {
    public static final double DISTRIBUCION = 0.35;
    private static double distrActual = 0;
    private static int cantAgua = 0;
    private boolean botePescando = true;
    private ImageIcon image;

    public Agua() {
        super();
        cantAgua++;
        this.image = Images.AGUA_IMAGE;
    }

    @Override
    public boolean sePuedeHacer(Actividad act) {
        return (act instanceof Pescar)
                    ? true : false;
    }

    public static boolean lessThanNecessary() {
        if (Suelo.getCantSuelos() != 0)
            distrActual = cantAgua / Suelo.getCantSuelos();
        return (distrActual < DISTRIBUCION)
                ? true : false;
    }

    @Override
    public ImageIcon getImage() {
        return this.image;
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
