package com.logic.objetos.actividades;

import javax.naming.InsufficientResourcesException;
import javax.swing.*;

import com.logic.Opcion;
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
    public SerVivo getSer() {
        return this.animal;
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
    public Opcion getOpcion(Actividad act) {
        if (act instanceof Crianza) {
            Animal an = (Animal) (act.getSer());
            Animal an2 = (Animal) (this.getSer());
            if (an.isAlive() && an2.isAlive()) {
                if (an.isReadyToRecolect() && an2.isReadyToRecolect()) {
                    return Opcion.RECOLECTAR;
                } if (an.isReadyToDead() && an2.isReadyToDead()) {
                    return Opcion.DESTAZAR;
                } return Opcion.ALIMENTAR;
            } else if (an.isAlive() == an2.isAlive()) {
                return Opcion.LIMPIAR;
            }
        } return Opcion.NADA;
    }

    @Override
    public Opcion getOpcion() {
        Animal an = (Animal) (this.getSer());
        if (an.isAlive()) {
            if (an.isReadyToRecolect()) {
                return Opcion.RECOLECTAR;
            } if (an.isReadyToDead()) {
                return Opcion.DESTAZAR;
            } return Opcion.ALIMENTAR;
        } else {
            return Opcion.LIMPIAR;
        }
    }

    @Override
    public void terminarActividad(Granjero bob) throws InsufficientResourcesException {
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
        } else if (animal.isProductor() && animal.isReadyToRecolect()) {
            animal.getProductos();
        } else if (!animal.isAlive()) {
            this.limpiarCuerpo(bob);
        }
    }
}
