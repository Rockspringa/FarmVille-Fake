package com.logic.objetos;

import javax.naming.InsufficientResourcesException;
import javax.swing.ImageIcon;

import com.logic.Opcion;
import com.logic.objetos.posee_materia.*;
import com.logic.objetos.posee_materia.seres_vivos.*;

/**
 * Clase que representa la crianza, siembra y pesca.
 */
public abstract class Actividad {
    private final Suelo[] area;

    /**
     * Crea una actividad.
     * @param area son los cuadros en los cuales se dara la actividad
     */
    public Actividad(Suelo[] area) {
        this.area = area;
    }

    public Suelo[] getArea() {
        return this.area;
    }

    public boolean sePuede(Suelo suelo) {
        return suelo.sePuedeHacer(this);
    }

    public void limpiarCuerpo(Granjero bob) throws InsufficientResourcesException {
        if (bob.isEnough(10)) {
            bob.setOro(10);
            for (Suelo parcela : area) {
                parcela.setActividad(null);
            }
        }
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        } else if (obj.getClass().getSimpleName().equals(this.getClass().getSimpleName())) {
            return true;
        } return false;
    }

    public boolean isSameOpcion(Actividad act) {
        return (act.getOpcion(this) != Opcion.NADA) ? true : false;
    }

    public abstract Opcion getOpcion(Actividad act);

    public abstract Opcion getOpcion();

    public abstract SerVivo getSer();

    public abstract void pasoTiempo();

    public abstract void realizarActividad();

    public abstract void terminarActividad(Granjero bob) throws InsufficientResourcesException;

    public abstract ImageIcon getImage();
}
