package com.logic.objetos.actividades;

import javax.swing.*;

import com.logic.objetos.*;
import com.logic.objetos.posee_materia.*;
import com.logic.objetos.posee_materia.seres_vivos.*;


public class Crianza extends Actividad {
    private final Animal animal;

    public Crianza(Animal animal, Suelo[] area) {
        super(area);
        this.animal = animal;
    }

    public Crianza(Animal animal, Suelo area) {
        super(new Suelo[] {area});
        this.animal = animal;
    }

    public static boolean enoughtSpace(Animal animal, Suelo[] area) {
        return (animal.getNumParcelas() >= area.length)
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
        if (animal.isProductor() && animal.isDestazable()) {
            if (animal.isReadyToRecolect()) {
                animal.getProductos();
            } else if (animal.isReadyToDead()) {
                animal.morir();
            } else if (!animal.isAlive()) {
                this.limpiarCuerpo(bob);
            }
        } else if (animal.isDestazable() && animal.isReadyToDead()) {
            animal.morir();
        } else if (animal.isProductor() && animal.isReadyToDead()) {
            animal.getProductos();
        } else if (!animal.isAlive()) {
            this.limpiarCuerpo(bob);
        }
    }
}
