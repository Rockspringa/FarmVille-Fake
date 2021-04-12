package com.logic.objetos;

import javax.swing.ImageIcon;

import com.logic.objetos.posee_materia.*;
import com.logic.objetos.posee_materia.seres_vivos.*;

public abstract class Actividad {
    private final Suelo[] area;

    public Actividad(Suelo[] area) {
        this.area = area;
    }

    public Suelo[] getArea() {
        return this.area;
    }

    public boolean sePuede(Suelo suelo) {
        return suelo.sePuedeHacer(this);
    }

    public void limpiarCuerpo(Granjero bob) {
        if (bob.isEnough(10)) {
            bob.setOro(10);
            for (Suelo parcela : area) {
                parcela.setActividad(null);
            }
        }
    }

    public abstract void pasoTiempo();

    public abstract void realizarActividad();

    public abstract void terminarActividad(Granjero bob);

    public abstract ImageIcon getImage();
}
