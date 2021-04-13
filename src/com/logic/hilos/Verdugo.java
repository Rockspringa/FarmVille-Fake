package com.logic.hilos;

import com.exec.Granja;
import com.gui.frames.Juego;

public class Verdugo extends Thread {
    private static Juego juego = null;
    
    @Override
    public void run() {
        juego = new Juego();
        Granja.bob.newGame();
        juego.seeIt();
        new MatarTiempo().start();

        while (Granja.bob.isAlive()) {
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.getMessage();
            }
            Granja.bob.bajarVida();
            juego.bajoVida();
        }
        juego.dispose();
        Granja.ventanaPrincipal.setVisible(true);
    }

    public static Juego getJuego() {
        return juego;
    }
}
