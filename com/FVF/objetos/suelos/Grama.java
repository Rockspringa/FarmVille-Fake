package com.FVF.objetos.suelos;

import com.FVF.objetos.*;
import com.FVF.objetos.actividades.*;


public class Grama extends Suelo {
    public static final double DISTRIBUCION = 0.4;
    private static double distrActual = 0;
    private static int cantGrama = 0;
    private boolean plantaPlantada = true;

    public Grama() {
        super();
        cantGrama++;
        int cantSuelos = getCantSuelos();
        distrActual = cantGrama / cantSuelos;
    }

    public boolean sePuedeHacer(Actividad act) {
        return (act instanceof Sembrar || act instanceof Crianza)
                    ? true : false;
    }

    public static double getDistribucion() {
        return distrActual;
    }

    public void cosecharPlanta() {
        if (plantaPlantada) {
            System.out.println("Estoy cosechando");
        }
    }
}
