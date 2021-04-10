package com.gui;

import javax.swing.*;
import java.awt.event.*;


public abstract class Frame extends JFrame implements ActionListener {
    private int width = 622;
    private int height = 707;

    private void begin() {
        this.setLayout(null);
        this.setResizable(false);
        this.setBounds(0, 0, this.width, this.height);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public Frame(String header) {
        super(header);
        this.begin();
    }

    public Frame(String header, int width, int height) {
        super(header);
        this.width = width;
        this.height = height;
        this.begin();
    }

    public void seeIt() {
        this.setVisible(true);
        this.setLocationRelativeTo(null);
    }
}
