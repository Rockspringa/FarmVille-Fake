package com.logic.objetos.posee_materia.seres_vivos;

import javax.swing.ImageIcon;

import com.logic.objetos.posee_materia.SerVivo;

public abstract class Planta implements SerVivo {
    private int tiempoVivo = 0;
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

    public Planta(Planta oldPlant) {
        this.name = oldPlant.name;
        this.alive = true;
        this.deadline = oldPlant.deadline;
        this.cosechaTime = oldPlant.cosechaTime;
        this.cantSemillas = oldPlant.cantSemillas;
        this.precioSemilla = oldPlant.precioSemilla;
    }

    @Override
    public String getNombre() {
        return this.name;
    }

    @Override
    public int getVida() {
        return this.deadline - this.tiempoVivo;
    }

    @Override
    public void bajarVida() {
        this.tiempoVivo++;
        if (tiempoVivo > deadline) {
            this.morir();
        }
    }

    @Override
    public boolean isAlive() {
        return this.alive;
    }

    @Override
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
    
    @Override
    public int getPrecio() {
        return precioSemilla;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        } else if (obj instanceof Planta
                    && obj.getClass().getSimpleName().equals(this.getClass().getSimpleName())) {
            Planta objP = (Planta) (obj);
            if (objP.getNombre().equals(this.getNombre())
                        && objP.getPrecio() == this.getPrecio()
                        && objP.getCantSemillas() == this.getCantSemillas()) {
                return true;
            }
        } return false;
    }
}
