package com.gui.frames;

import java.awt.event.*;
import javax.swing.*;
import java.awt.*;

import com.exec.Granja;
import com.gui.Frame;
import com.gui.images.Images;

public class Juego extends Frame {
    private JLabel[][] parcelas;
    private JScrollPane scrollable;
    private Container panelParcelas;
    private JMenuBar bar;
    private JButton btnAgregar;
    private int widthPanel = 520;
    private int heightPanel = 520;
    private int rowPanel = 5;
    private int colPanel = 5;

    private void resizeParcelas() {
        JLabel[][] arrAux = new JLabel[this.rowPanel + 1][this.colPanel + 1];
        for (int m = 0; m < this.rowPanel; m++) {
            for (int n = 0; n < this.colPanel; n++) {
                arrAux[m][n] = parcelas[m][n];
            }
        }

        this.parcelas = arrAux;
        this.widthPanel += 105;
        this.heightPanel += 105;
        this.rowPanel++;
        this.colPanel++;

        this.drawParcelas();
    }

    private void drawParcelas() {
        panelParcelas.removeAll();
        panelParcelas.revalidate();
        panelParcelas.repaint();
        panelParcelas.setSize(widthPanel, heightPanel);
        panelParcelas.setLayout(new GridLayout(rowPanel, colPanel, 5, 5));

        for (int m = 0; m < this.rowPanel; m++) {
            for (int n = 0; n < this.colPanel; n++) {
                ImageIcon icGranja = Granja.getJParcela(m, n);
                ImageIcon icBloq = Images.BLOQUEADO_IMAGE;

                if (parcelas[m][n] == null || (icGranja != icBloq
                                    && parcelas[m][n].getIcon() == icBloq)) {
                    parcelas[m][n] = new JLabel(icGranja);
                    parcelas[m][n].setVerticalAlignment(SwingConstants.CENTER);
                    parcelas[m][n].setHorizontalAlignment(SwingConstants.CENTER);
                    parcelas[m][n].setOpaque(true);
                } this.panelParcelas.add(parcelas[m][n]);
            }
        }
    }
    
    public Juego() {
        super("FarmFake");
        this.setLayout(new FlowLayout());

        this.bar = new JMenuBar();
        this.setJMenuBar(bar);

        this.btnAgregar = new JButton("Agregar Parcela");
        this.btnAgregar.addActionListener(this);
        this.bar.add(btnAgregar);

        panelParcelas = new JPanel();

        Granja.iniciarParcela();
        parcelas = new JLabel[5][5];
        this.drawParcelas();

        this.scrollable = new JScrollPane(panelParcelas);
        this.scrollable.setSize(widthPanel, heightPanel);
        this.getContentPane().add(scrollable, BorderLayout.CENTER);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnAgregar) {
            if (Granja.newParcela()) {
                if (rowPanel == 5)
                    this.scrollable.setSize(widthPanel + 20, heightPanel + 20);
                this.resizeParcelas();
            } else {
                this.drawParcelas();
            }
        }
    }
}
