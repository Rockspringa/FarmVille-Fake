package com.logic.hilos;

import com.exec.Granja;
import com.logic.objetos.Suelo;

public class MatarTiempo extends Thread {

    @Override
    public void run() {
        Suelo[][] arrSuelos;
        while (Granja.bob.isAlive()) {
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.getMessage();
            }
            arrSuelos = Granja.getParcelas();
            for (Suelo[] parcelas : arrSuelos) {
                for (Suelo parc : parcelas) {
                    if (parc != null && parc.getActividad() != null)
                        parc.getActividad().pasoTiempo();
                }
            }
        }
    }
}
