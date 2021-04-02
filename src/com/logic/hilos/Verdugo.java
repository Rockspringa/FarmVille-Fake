package com.logic.hilos;

import com.exec.Granja;
import com.gui.Frame;
import com.gui.frames.Juego;

public class Verdugo extends Thread {
    @Override
    public void run() {
        Frame juego = new Juego();
        Granja.bob.newGame();
        juego.seeIt();
        while (Granja.bob.isAlive()) {
            Granja.bob.bajarVida();
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.getMessage();
            }
        }
        juego.dispose();
        Granja.ventanaPrincipal.setVisible(true);
    }
}
