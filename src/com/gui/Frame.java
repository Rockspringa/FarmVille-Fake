package com.gui;

import javax.swing.*;
import java.awt.event.*;

/**
 * Esta clase se encarga de crear todo lo basico de los JFrame utilizados para todo el juego.
 */
public abstract class Frame extends JFrame implements ActionListener {
    private int width = 622;
    private int height = 707;

    /**
     * Se encarga de modificar las cosas basicas del JFrame.
     */
    private void begin() {
        this.setLayout(null);
        this.setResizable(false);
        this.setBounds(0, 0, this.width, this.height);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    /**
     * Crea un JFrame, le coloca el titulo, usa las dimensiones predefinidas, y llama el metodo begin().
     * @param header es el titulo que tendra el JFrame.
     */
    public Frame(String header) {
        super(header);
        this.begin();
    }

    /**
     * Crea un JFrame con las medidas indicadas y el titulo indicado.
     * @param header es el titulo a utilizar.
     * @param width es el ancho de la ventana.
     * @param height es el alto de la ventana.
     */
    public Frame(String header, int width, int height) {
        super(header);
        this.width = width;
        this.height = height;
        this.begin();
    }

    /**
     * Centra la ventana y la vuelve visible.
     */
    public void seeIt() {
        this.setVisible(true);
        this.setLocationRelativeTo(null);
    }
}
