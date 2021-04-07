package com.logic.objetos.actividades;

import javax.swing.ImageIcon;

import com.logic.objetos.*;
import com.logic.objetos.seres_vivos.Granjero;
import com.logic.objetos.seres_vivos.Planta;


public class Sembrar extends Actividad {
    private final Planta planta;

    public Sembrar(Planta planta, Suelo[] area) {
        super(area);
        this.planta = planta;
    }

    public Sembrar(Planta planta, Suelo area) {
        super(new Suelo[] {area});
        this.planta = planta;
    }

    @Override
    public void realizarActividad() {
        for (Suelo parcela : this.getArea()) {
            parcela.setActividad(this);
        }
    }

    @Override
    public void terminarActividad(Granjero bob) {
        if (planta.isCosechable()) {
            planta.cosechar(bob);
            this.getArea()[0].setActividad(null);
        } else if (!planta.isAlive()) {
            this.limpiarCuerpo(bob);
        }
    }

    @Override
    public ImageIcon getImage() {
        return planta.getImage();
    }

    @Override
    public void pasoTiempo() {
        planta.bajarVida();
    }
}
