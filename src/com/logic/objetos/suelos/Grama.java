package com.logic.objetos.suelos;

import javax.swing.ImageIcon;
import com.logic.objetos.*;
import com.gui.images.Images;
import com.logic.objetos.actividades.*;


public class Grama extends Suelo {
    private static final double DISTRIBUCION = 0.4;
    private boolean plantaPlantada = true;
    private static double distrActual = 0;
    private static int cantGrama = 0;
    private ImageIcon image;

    public Grama() {
        super();
        cantGrama++;
        this.image = Images.GRAMA_IMAGE;
    }

    @Override
    public boolean sePuedeHacer(Actividad act) {
        return (act instanceof Sembrar || act instanceof Crianza)
                    ? true : false;
    }

    @Override
    public ImageIcon getImage() {
        return this.image;
    }

    public static boolean lessThanNecessary() {
        if (Suelo.getCantSuelos() != 0)
            distrActual = cantGrama / Suelo.getCantSuelos();
        return (distrActual < DISTRIBUCION)
                ? true : false;
    }

    public void cosecharPlanta() {
        if (plantaPlantada) {
            System.out.println("Estoy cosechando");
        }
    }
}
