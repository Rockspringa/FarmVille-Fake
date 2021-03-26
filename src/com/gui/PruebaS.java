package com.gui;

import javax.swing.*;
import java.awt.Image;
import java.awt.Color;
import java.awt.event.*;

public class PruebaS {
    public static void main(String args[]) {
        new Form("It's a proof");
    }
}

class Form extends JFrame implements ActionListener {
    private final ImageIcon PASTO_ICON = new ImageIcon("Pastito.png");
    private JTextField txtField = new JTextField("Numero de cuadricula");
    private JButton btn = new JButton("Resize It");
    private Image PASTO;
    private final int widthWin = 600;
    private final int heightWin = 600;
    private final int CUADRICULA = 10;
    private JLabel[][] labels = new JLabel[CUADRICULA][CUADRICULA];

    public Form(String text) {
        super(text);

        int width = (int) ((widthWin - 36) / CUADRICULA);
        int height = (int) ((heightWin - 88) / CUADRICULA);
        int x = 10;
        int y = 10;

        this.setLayout(null);
        this.setResizable(false);
        this.setBounds(0, 0, widthWin, heightWin);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.getContentPane().setBackground(Color.BLUE);

        PASTO = PASTO_ICON.getImage().getScaledInstance(width - 2, height - 2, Image.SCALE_SMOOTH);
        ImageIcon pastoLb = new ImageIcon(PASTO);
        
        for (int m = 0; m < CUADRICULA; m++) {
            for (int n = 0; n < CUADRICULA; n++) {
                labels[m][n] = new JLabel(pastoLb);
                labels[m][n].setBackground(Color.BLACK);
                labels[m][n].setVerticalAlignment(SwingConstants.CENTER);
                labels[m][n].setHorizontalAlignment(SwingConstants.CENTER);
                labels[m][n].setOpaque(true);
                labels[m][n].setBounds(x, y, width, height);
                this.add(labels[m][n]);
                x += width;
            }
            x = 10;
            y += height;
        }

        txtField.setBounds(10, 382, 165, 15);
        txtField.addFocusListener(new FocusListener(){
            @Override
            public void focusGained(FocusEvent e){
                txtField.setText("");
            }

            @Override
            public void focusLost(FocusEvent e){}
        });
        this.add(txtField);

        btn.setBounds(200, 382, 165, 15);
        btn.addActionListener(this);
        this.add(btn);

        this.setVisible(true);
        this.setLocationRelativeTo(null);
    }

    public void actionPerformed(ActionEvent event) {
        if (event.getSource() == btn) {
            int txt;
            try {
                txt = Integer.parseInt(txtField.getText());
                if (txt > CUADRICULA)
                    throw new NumberFormatException();
            } catch (NumberFormatException e) {
                txt = CUADRICULA;
            }
            int width = (int) ((widthWin - 36) / txt);
            int height = (int) ((heightWin - 88) / txt);
            txtField.setText("Numero de cuadricula");
            
            int x = 10;
            int y = 10;
            for (int m = 0; m < txt; m++) {
                for (int n = 0; n < txt; n++) {
                    PASTO = PASTO_ICON.getImage().getScaledInstance(width - 2, height - 2, Image.SCALE_SMOOTH);
                    ImageIcon pastoLb = new ImageIcon(PASTO);
                    labels[m][n].setBounds(x, y, width, height);
                    labels[m][n].setIcon(pastoLb);
                    labels[m][n].setVisible(true);;
                    x += width;
                }
                x = 10;
                y += height;
            }

            for (int m = txt; m < CUADRICULA; m++) {
                for (int n = 0; n < CUADRICULA; n++) {
                    labels[m][n].setVisible(false);
                }
            }

            for (int m = 0; m < CUADRICULA; m++) {
                for (int n = txt; n < CUADRICULA; n++) {
                    labels[m][n].setVisible(false);
                }
            }
        }
        if (event.getSource() == txtField) {
            txtField.setText(null);
        }
    }
}
