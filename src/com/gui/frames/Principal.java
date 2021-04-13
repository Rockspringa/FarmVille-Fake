package com.gui.frames;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import com.exec.Granja;
import com.gui.Frame;
import com.gui.images.Images;
import com.logic.hilos.*;

/**
 * Es la ventana que se encarga de manejar el direccionamiento hacia los distintos JFrames, al frame para
 * jugar, al frame de la creacion de nuevos animales, plantas y objetos, y al JFrame de las estadisticas.
 */
public class Principal extends Frame {
    private JLabel granjero;
    private JLabel texto;
    private JPanel panelLbls;
    private JPanel panelBtn;
    private JButton juegoBtn;
    private JButton diosBtn;

    /**
     * Diseña toda la interfaz ayudandose de layouts y de distintos componentes.
     */
    public Principal() {
        super("FarmFake");
        this.getContentPane().setLayout(new BorderLayout());

        panelLbls = new JPanel();
        panelLbls.setLayout(new BoxLayout(panelLbls, BoxLayout.X_AXIS));
        this.getContentPane().add(panelLbls, BorderLayout.CENTER);

        granjero = new JLabel(Images.GRANJERO_IMAGE);
        panelLbls.add(granjero);

        texto = new JLabel();
        texto.setFont(new Font("JetBrainsMono NF", 1, 14));
        texto.setText("<html><p style=\"width:200px\">Hola, soy el granjero "
                    + Granja.bob.getNombre() + ", pero llamame "
                    + Granja.bob.getApodo() + ". Puedes presionar el"
                    + " boton 'Jugar' para iniciar tus labores en la "
                    + " granja, o puedes presionar el boton "
                    + " 'Crear...' para crear nuevos animales,"
                    + " plantas o productos.</p></html>");
        texto.setVerticalTextPosition(JLabel.TOP);
        texto.setHorizontalTextPosition(JLabel.CENTER);
        panelLbls.add(texto);

        panelBtn = new JPanel();
        panelBtn.setLayout(new FlowLayout(FlowLayout.CENTER, 100, 15));

        juegoBtn = new JButton("Jugar");
        juegoBtn.setPreferredSize(new Dimension(125, 25));
        juegoBtn.addActionListener(this);
        panelBtn.add(juegoBtn);

        diosBtn = new JButton("Crear...");
        diosBtn.setPreferredSize(new Dimension(125, 25));
        diosBtn.addActionListener(this);
        panelBtn.add(diosBtn);
        this.getContentPane().add(panelBtn, BorderLayout.PAGE_END);
        this.pack();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == juegoBtn) {
            this.setVisible(false);
            new Verdugo().start();
        }
    }
    
}
