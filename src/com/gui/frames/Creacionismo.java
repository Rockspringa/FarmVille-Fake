package com.gui.frames;

import java.awt.*;
import java.awt.event.ActionEvent;

import javax.swing.*;

import com.gui.Frame;

public class Creacionismo extends Frame {
    private JTabbedPane tabePane;
    private JPanel plantasPane;
    private JPanel animalesPane;
    private JPanel granosPane;
    private JPanel frutasPane;
    private JPanel productosPane;

    public Creacionismo() {
        super("Crear...");
        tabePane = new JTabbedPane();
        this.getContentPane().setLayout(new BorderLayout());
        this.getContentPane().add(tabePane, BorderLayout.CENTER);

        plantasPane = new JPanel();
        tabePane.addTab("Plantas", null, plantasPane, "Aqui puedes crear plantas");

        animalesPane = new JPanel();
        tabePane.addTab("Animales", null, animalesPane, "Crea aqui a los animales");

        granosPane = new JPanel();
        tabePane.addTab("Granos", null, granosPane, "Crea el producto para los cultivos");

        frutasPane = new JPanel();
        tabePane.addTab("Frutas", null, frutasPane, "Crea frutas para las plantas que dan frutas");

        productosPane = new JPanel();
        tabePane.addTab("Productos", null, productosPane, "Crea todo lo que pueden dar los animales");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
    }
    
}
