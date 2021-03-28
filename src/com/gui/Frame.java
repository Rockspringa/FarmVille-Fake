package com.gui;

import javax.swing.*;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.event.*;


public abstract class Frame extends JFrame implements ActionListener {
    private int width = 600;
    private int height = 620;

    private void begin() {
        this.setLayout(null);
        this.setBounds(0, 0, this.width, this.height);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
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
