package com.logic.hilos;

import com.exec.Granja;
import com.gui.frames.Juego;

public class Verdugo extends Thread {
    private static Juego juego = null;
    private static int tiempoTotal = 0;
    
    @Override
    public void run() {
        juego = new Juego();
        Granja.bob.newGame();
        juego.seeIt();
        new MatarTiempo().start();
        new Estados().start();
        tiempoTotal = 0;

        while (Granja.bob.isAlive()) {
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.getMessage();
            }
            tiempoTotal++;
            tiempoTotal++;
            Granja.bob.bajarVida();
            juego.bajoVida();
        }
        juego.dispose();
    }

    public static Juego getJuego() {
        return juego;
    }

    public static int getTiempo() {
        return tiempoTotal;
    }
}
