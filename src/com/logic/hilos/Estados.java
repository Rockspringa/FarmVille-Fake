package com.logic.hilos;

import com.exec.Granja;
import com.logic.objetos.*;
import com.logic.objetos.actividades.*;
import com.logic.objetos.posee_materia.seres_vivos.*;

public class Estados extends Thread {
    
    @Override
    public void run() {
        while (Granja.bob.isAlive()) {
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.getMessage();
            }
            int lado = Granja.getDimesions();
            Actividad act;
            for (int m = 0; m < lado; m++) {
                for (int n = 0; n < lado; n++) {
                    act = Granja.getParcelas()[m][n].getActividad();
                    if (act != null && act instanceof Crianza) {
                        if (((Animal) (act.getSer())).isReadyToRecolect()) {
                            Verdugo.getJuego().isReadyBorder(m, n);
                            Verdugo.getJuego().refreshButtons(true);
                        } else if (((Animal) (act.getSer())).isReadyToDead()) {
                            Verdugo.getJuego().isReadyBorder(m, n);
                            Verdugo.getJuego().refreshButtons(true);
                        } else {
                            Verdugo.getJuego().isNotReadyBorder(m, n);
                        }
                    } else if (act != null && act instanceof Sembrar) {
                        if (((Planta) (act.getSer())).isCosechable()) {
                            Verdugo.getJuego().isReadyBorder(m, n);
                            Verdugo.getJuego().refreshButtons(true);
                        } else {
                            Verdugo.getJuego().isNotReadyBorder(m, n);
                        }
                    }
                }
            }
        }
    }
}
