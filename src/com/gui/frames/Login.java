package com.gui.frames;

import javax.swing.JButton;

import java.awt.*;
import javax.swing.*;
import com.gui.Frame;
import com.exec.Granja;
import java.awt.event.*;
import com.logic.objetos.Granjero;


public class Login extends Frame implements FocusListener {
    private JLabel apodoLbl;
    private JLabel nombreLbl;
    private JTextField apodoTxtF;
    private JTextField nombreTxtF;
    private JButton btnCrear;

    public Login() {
        super("Creando granjero...", 350, 205);

        nombreLbl = new JLabel("Nombre:");
        nombreLbl.setBounds(25, 20, 50, 30);
        this.add(nombreLbl);

        nombreTxtF = new JTextField();
        nombreTxtF.setBounds(90, 20, 220, 30);
        nombreTxtF.addFocusListener(this);
        this.add(nombreTxtF);
        
        apodoLbl = new JLabel("Apodo:");
        apodoLbl.setBounds(25, 70, 50, 30);
        this.add(apodoLbl);
        
        apodoTxtF = new JTextField();
        apodoTxtF.setBounds(90, 70, 220, 30);
        apodoTxtF.addFocusListener(this);
        this.add(apodoTxtF);

        btnCrear = new JButton("Crear granjero");
        btnCrear.setBounds(25, 120, 280, 30);
        btnCrear.addActionListener(this);
        this.add(btnCrear);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnCrear) {
            if (!nombreTxtF.getText().equals("") && !apodoTxtF.getText().equals("")) {
                Granja.bob = new Granjero(nombreTxtF.getText(), apodoTxtF.getText());
                new Juego().seeIt();
                this.dispose();
            }
        }
    }

    @Override
    public void focusGained(FocusEvent e) {
        if (e.getSource() == nombreTxtF) {
            nombreTxtF.setText("");
            nombreLbl.setForeground(Color.BLACK);
        }

        if (e.getSource() == apodoTxtF) {
            apodoTxtF.setText("");
            apodoLbl.setForeground(Color.BLACK);
        }
    }

    @Override
    public void focusLost(FocusEvent e) {
        if (e.getSource() == nombreTxtF) {
            if (nombreTxtF.getText().equals(""))
                nombreLbl.setForeground(Color.RED);
            else
                nombreLbl.setForeground(Color.BLACK);
                
        }

        if (e.getSource() == apodoTxtF) {
            if (apodoTxtF.getText().equals(""))
                apodoLbl.setForeground(Color.RED);
            else
                apodoLbl.setForeground(Color.BLACK);
        }
    }
}
