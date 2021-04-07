package com.logic.objetos.seres_vivos;

import javax.swing.ImageIcon;

public abstract class Planta {
    private int tiempoVivo;
    private boolean alive;
    private ImageIcon image;
    private final int deadline; /* Tiempo que tarda en morir despues de dar produtos. */
    private final int cosechaTime; /* Tiempo que tarda dar producto. */
    private final int cantSemillas;
    private final int precioSemilla;
    private final String name;

    public Planta(String name, int cantSemillas, int precioSemilla,
                        int deadline, int cosechaTime) {
                            this.name = name;
        this.alive = true;
        this.deadline = deadline;
        this.cosechaTime = cosechaTime;
        this.cantSemillas = cantSemillas;
        this.precioSemilla = precioSemilla;
    }

    public Planta(String name, int cantSemillas, int precioSemilla,
                        int deadline, int cosechaTime, ImageIcon image) {
        this.name = name;
        this.addImage(image);
        this.deadline = deadline;
        this.cosechaTime = cosechaTime;
        this.cantSemillas = cantSemillas;
        this.precioSemilla = precioSemilla;
    }

    public String getName() {
        return this.name;
    }

    public void bajarVida() {
        this.tiempoVivo++;
        if (tiempoVivo > deadline) {
            this.morir();
        }
    }

    public boolean isAlive() {
        return this.alive;
    }

    public ImageIcon getImage() {
        return this.image;
    }

    public void addImage(ImageIcon image) {
        this.image = image;
    }

    public void resetTiempoVivo() {
        this.tiempoVivo = 0;
    }

    public void morir() {
        this.alive = false;
    }

    public boolean isCosechable() {
        return (this.isAlive())
                    ? (this.cosechaTime < this.tiempoVivo &&
                            this.tiempoVivo < this.deadline)
                                   ? true : false
                    : false;
    }

    public abstract void cosechar(Granjero bob);

    public int getCantSemillas() {
        return cantSemillas;
    }
    
    public int getPrecioSemilla() {
        return precioSemilla;
    }
}
