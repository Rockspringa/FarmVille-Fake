package com.gui.frames;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

import com.exec.Granja;
import com.gui.Frame;
import com.logic.hilos.Verdugo;

public class Registro extends Frame {
    private JLabel nombreTag;
    private JLabel duracionPartidaTag;
    private JLabel oroTag;
    private JLabel alimGenerTag;
    private JLabel alimConsuTag;
    private JLabel tierraCompradaTag;
    private JLabel nombre;
    private JLabel duracionPartida;
    private JLabel oro;
    private JLabel alimGener;
    private JLabel alimConsu;
    private JLabel tierraComprada;
    private Font jtb;

    public Registro() {
        super("Estadisticas", 520, 520);
        this.getContentPane().setLayout(new GridLayout(6, 2, 5, 2));
        ((JPanel) (this.getContentPane())).setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        this.jtb = new Font("JetBrainsMono Nerd Font Mono", 1, 14);
        
        this.nombreTag = new JLabel("Nombre del Granjero");
        this.nombreTag.setFont(jtb);
        this.nombreTag.setVerticalTextPosition(JLabel.CENTER);
        this.nombreTag.setHorizontalTextPosition(JLabel.CENTER);
        this.getContentPane().add(this.nombreTag);

        this.nombre = new JLabel(Granja.bob.getNombre());
        this.nombre.setFont(jtb);
        this.nombre.setVerticalTextPosition(JLabel.CENTER);
        this.nombre.setHorizontalTextPosition(JLabel.RIGHT);
        this.getContentPane().add(this.nombre);

        this.duracionPartidaTag = new JLabel("Duracion de la partida");
        this.duracionPartidaTag.setFont(jtb);
        this.duracionPartidaTag.setVerticalTextPosition(JLabel.CENTER);
        this.duracionPartidaTag.setHorizontalTextPosition(JLabel.CENTER);
        this.getContentPane().add(this.duracionPartidaTag);

        this.duracionPartida = new JLabel("" + Verdugo.getTiempo());
        this.duracionPartida.setFont(jtb);
        this.duracionPartida.setVerticalTextPosition(JLabel.CENTER);
        this.duracionPartida.setHorizontalTextPosition(JLabel.RIGHT);
        this.getContentPane().add(this.duracionPartida);

        this.oroTag = new JLabel("Oro generado");
        this.oroTag.setFont(jtb);
        this.oroTag.setVerticalTextPosition(JLabel.CENTER);
        this.oroTag.setHorizontalTextPosition(JLabel.CENTER);
        this.getContentPane().add(this.oroTag);

        this.oro = new JLabel("" + Granja.bob.getOroGanado());
        this.oro.setFont(jtb);
        this.oro.setVerticalTextPosition(JLabel.CENTER);
        this.oro.setHorizontalTextPosition(JLabel.RIGHT);
        this.getContentPane().add(this.oro);

        this.alimGenerTag = new JLabel("Cantidad de alimento\ngenerado");
        this.alimGenerTag.setFont(jtb);
        this.alimGenerTag.setVerticalTextPosition(JLabel.CENTER);
        this.alimGenerTag.setHorizontalTextPosition(JLabel.CENTER);
        this.getContentPane().add(this.alimGenerTag);

        this.alimGener = new JLabel("" + Granja.bob.getAlimGener());
        this.alimGener.setFont(jtb);
        this.alimGener.setVerticalTextPosition(JLabel.CENTER);
        this.alimGener.setHorizontalTextPosition(JLabel.RIGHT);
        this.getContentPane().add(this.alimGener);

        this.alimConsuTag = new JLabel("Cantidad de alimento\nconsumido");
        this.alimConsuTag.setFont(jtb);
        this.alimConsuTag.setVerticalTextPosition(JLabel.CENTER);
        this.alimConsuTag.setHorizontalTextPosition(JLabel.CENTER);
        this.getContentPane().add(this.alimConsuTag);

        this.alimConsu = new JLabel("" + Granja.bob.getAlimConsu());
        this.alimConsu.setFont(jtb);
        this.alimConsu.setVerticalTextPosition(JLabel.CENTER);
        this.alimConsu.setHorizontalTextPosition(JLabel.RIGHT);
        this.getContentPane().add(this.alimConsu);

        this.tierraCompradaTag = new JLabel("Cantidad de tierras\ncompradas");
        this.tierraCompradaTag.setFont(jtb);
        this.tierraCompradaTag.setVerticalTextPosition(JLabel.CENTER);
        this.tierraCompradaTag.setHorizontalTextPosition(JLabel.CENTER);
        this.getContentPane().add(this.tierraCompradaTag);

        this.tierraComprada = new JLabel("" + Verdugo.getJuego().getTierrasCompradas());
        this.tierraComprada.setFont(jtb);
        this.tierraComprada.setVerticalTextPosition(JLabel.CENTER);
        this.tierraComprada.setHorizontalTextPosition(JLabel.RIGHT);
        this.getContentPane().add(this.tierraComprada);
    }

    @Override
    public void dispose() {
        super.dispose();
        Granja.ventanaPrincipal.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
    }
}
