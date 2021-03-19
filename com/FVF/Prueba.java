package com.FVF;

public class Prueba extends Thread {
    private int i;

    @Override
    public void run() {
        try {
            Thread.sleep(i);
            System.out.println("Ya se murio");
        } catch (InterruptedException e) {
            System.out.println("No se murio");
        }
    }

    public void start(int i) {
        this.i = i;
        start();
    }
}
