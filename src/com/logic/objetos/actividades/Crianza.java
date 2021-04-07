package com.logic.objetos.actividades;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

import com.logic.objetos.*;
import com.logic.objetos.seres_vivos.Animal;
import com.logic.objetos.seres_vivos.Granjero;


public class Crianza extends Actividad {
    private Animal animal;

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
        // TODO Auto-generated method stub
        
    }

    @Override
    public void terminarActividad(Granjero bob) {
        // TODO Auto-generated method stub
        
    }
}
