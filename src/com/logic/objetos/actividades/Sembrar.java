package com.logic.objetos.actividades;

import javax.swing.ImageIcon;

import com.logic.Opcion;
import com.logic.objetos.*;
import com.logic.objetos.posee_materia.*;
import com.logic.objetos.posee_materia.seres_vivos.*;


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
    public SerVivo getSer() {
        return this.planta;
    }

    @Override
    public Opcion getOpcion(Actividad act) {
        if (act instanceof Sembrar) {
            Planta an = (Planta) (act.getSer());
            Planta an2 = (Planta) (this.getSer());
            if (an.isAlive() && an2.isAlive()) {
                if (an.isCosechable() && an2.isCosechable()) {
                    return Opcion.COSECHAR;
                } return Opcion.FERTILIZAR;
            } else if (an.isAlive() == an2.isAlive()) {
                return Opcion.LIMPIAR;
            }
        } return Opcion.NADA;
    }

    @Override
    public Opcion getOpcion() {
        Planta an = (Planta) (this.getSer());
        if (an.isAlive()) {
            if (an.isCosechable()) {
                return Opcion.COSECHAR;
            } return Opcion.FERTILIZAR;
        } else {
            return Opcion.LIMPIAR;
        }
    }

    @Override
    public void terminarActividad(Granjero bob) {
        if (planta.isCosechable()) {
            planta.cosechar(bob);
            if (!planta.isAlive()) {
                for (Suelo suelo : this.getArea())
                    suelo.setActividad(null);
            }
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
