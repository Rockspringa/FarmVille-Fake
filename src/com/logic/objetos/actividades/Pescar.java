package com.logic.objetos.actividades;

import javax.swing.ImageIcon;

import com.logic.objetos.*;
import com.logic.objetos.seres_vivos.Granjero;


public class Pescar extends Actividad {
    
    public Pescar(Suelo[] area) {
        super(area);
    }

    public Pescar(Suelo area) {
        super(new Suelo[] {area});
    }

    @Override
    public void realizarActividad() {
        System.out.println("Estoy pescando");
    }

    @Override
    public void pasoTiempo() {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void terminarActividad(Granjero bob) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public ImageIcon getImage() {
        // TODO Auto-generated method stub
        return null;
    }   
}
