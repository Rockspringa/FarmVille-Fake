package com.logic.objetos.actividades;

import javax.swing.*;

import com.logic.objetos.*;
import com.logic.objetos.seres_vivos.*;


public class Crianza extends Actividad {
    private final Animal animal;

    public Crianza(Animal animal, Suelo[] area) {
        super(area);
        this.animal = animal;
    }

    public static boolean enoughtSpace(Animal animal, Suelo[] area) {
        return (animal.getNumParcelas() > area.length)
                ? false : true;
    }

    @Override
    public void realizarActividad() {
        for (Suelo parcela : this.getArea()) {
            parcela.setActividad(this);
        }
    }    

    @Override
    public ImageIcon getImage() {
        return animal.getImage();
    }

    @Override
    public void pasoTiempo() {
        animal.bajarVida();
    }

    @Override
    public void terminarActividad(Granjero bob) {
        /////////
    }
}
