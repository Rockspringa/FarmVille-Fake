package com.gui.frames;

import java.awt.event.*;
import javax.swing.*;

import com.exec.Granja;
import com.gui.Frame;

public class Juego extends Frame {
    private JLabel[][] parcelas;
    
    public Juego() {
        super("FarmFake");

        Granja.iniciarParcela();
        parcelas = new JLabel[5][5];
        int width = 105;
        int height = 98;
        int x = 20;
        int y = 20;
        
        for (int m = 0; m < 5; m++) {
            for (int n = 0; n < 5; n++) {
                parcelas[m][n] = new JLabel(Granja.getJParcela(m, n));
                parcelas[m][n].setVerticalAlignment(SwingConstants.CENTER);
                parcelas[m][n].setHorizontalAlignment(SwingConstants.CENTER);
                parcelas[m][n].setOpaque(true);
                parcelas[m][n].setBounds(x, y, width, height);
                this.add(parcelas[m][n]);
                x += width + 5;
            }
            x = 20;
            y += height + 5;
        }
    }

    public void actionPerformed(ActionEvent e) {
        
    }
}
