package com.logic.objetos.actividades;

import javax.swing.ImageIcon;

import com.exec.Granja;
import com.logic.Opcion;
import com.logic.objetos.*;
import com.gui.images.Images;
import com.logic.objetos.posee_materia.*;
import com.logic.objetos.posee_materia.productos.*;
import com.logic.objetos.posee_materia.seres_vivos.*;


public class Pescar extends Actividad {
    private int tiempoPescando;
    private int cantPeces;
    private int cantPecesCapturados;
    private Alimento pescado;
    private final Barco barco;
    
    public Pescar(Barco barco, Suelo[] area) {
        super(area);
        this.barco = barco;
        this.tiempoPescando = 0;
        this.cantPecesCapturados = 5;
        this.cantPeces = (int) (Math.random() * 10 + 20);
        this.pescado = new Alimento(3, 2, true, "Pescado", Images.PESCADO_IMAGE);
    }

    public Pescar(Barco barco, Suelo area) {
        super(new Suelo[] {area});
        this.barco = barco;
        this.tiempoPescando = 0;
        this.cantPecesCapturados = 5;
        this.cantPeces = (int) (Math.random() * 10 + 20);
    }

    @Override
    public void realizarActividad() {
        for (Suelo parcela : this.getArea()) {
            parcela.setActividad(this);
        }
    }

    @Override
    public void pasoTiempo() {
        this.tiempoPescando++;
        if (tiempoPescando % 10 == 0) {
            int cant = 0;
            if (cantPecesCapturados < cantPeces) {
                cant = cantPecesCapturados;
                cantPeces -= cantPecesCapturados;
            } else if (cantPeces > 0) {
                cant = cantPeces;
                cantPeces -= cantPeces;
            } for (int x = 0; x < cant; x++) {
                Granja.bob.addProducto(pescado);
            }
        }
    }

    @Override
    public void terminarActividad(Granjero bob) {
        for (Suelo suelo : this.getArea()) {
            suelo.setActividad(null);
        }
    }

    @Override
    public ImageIcon getImage() {
        return (tiempoPescando % 10 == 0)
                ? Images.BARCO_MAS_IMAGE
                : barco.getImage();
    }

    @Override
    public Opcion getOpcion(Actividad act) {
        if (act instanceof Pescar) {
            if (act.getArea() != null && this.getArea() != null) {
                return Opcion.QUITAR;
            }
        } return Opcion.NADA;
    }

    @Override
    public Opcion getOpcion() {
        return Opcion.QUITAR;
    }

    @Override
    public SerVivo getSer() {
        return null;
    }
}
