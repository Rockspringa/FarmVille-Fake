package com.gui.frames;

import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.event.MouseInputListener;

import java.awt.*;

import com.exec.Granja;
import com.gui.Frame;
import com.gui.images.Images;

public class Juego extends Frame implements MouseInputListener, ItemListener {
    private JToggleButton btnSelect;
    private JScrollPane scrollable;
    private JLabel[][] parcelas;
    private Container panelParcelas;
    private JMenuBar bar;
    private JButton btnAgregar;
    private boolean isSelecting = false;
    private JLabel selected;
    private Border normalBord;
    private Border bordSelect;
    private Border bordMouseOver;
    private int widthPanel = 500;
    private int heightPanel = 500;
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
        this.widthPanel += 100;
        this.heightPanel += 100;
        this.rowPanel++;
        this.colPanel++;

        this.drawParcelas();
    }

    private void drawParcelas() {
        panelParcelas.removeAll();
        panelParcelas.revalidate();
        panelParcelas.repaint();
        panelParcelas.setSize(widthPanel, heightPanel);
        panelParcelas.setLayout(new GridLayout(rowPanel, colPanel, 0, 0));

        for (int m = 0; m < this.rowPanel; m++) {
            for (int n = 0; n < this.colPanel; n++) {
                ImageIcon icGranja = Granja.getJParcela(m, n);
                ImageIcon icBloq = Images.BLOQUEADO_IMAGE;

                if (parcelas[m][n] == null || (icGranja != icBloq
                                    && parcelas[m][n].getIcon() == icBloq)) {
                    parcelas[m][n] = new JLabel(icGranja);
                    parcelas[m][n].setOpaque(true);
                    parcelas[m][n].setBorder(normalBord);
                    parcelas[m][n].addMouseListener(this);
                    parcelas[m][n].addMouseMotionListener(this);
                    parcelas[m][n].setVerticalAlignment(JLabel.CENTER);
                    parcelas[m][n].setHorizontalAlignment(JLabel.CENTER);
                    parcelas[m][n].setVerticalTextPosition(JLabel.CENTER);
                    parcelas[m][n].setHorizontalTextPosition(JLabel.CENTER);
                } this.panelParcelas.add(parcelas[m][n]);
            }
        }
    }
    
    public Juego() {
        super("FarmFake");
        this.setLayout(new FlowLayout());
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        this.bar = new JMenuBar();
        this.setJMenuBar(bar);

        this.btnAgregar = new JButton("Agregar parcela");
        this.btnAgregar.addActionListener(this);
        this.bar.add(btnAgregar);

        this.btnSelect = new JToggleButton("Seleccionar parcelas");
        this.btnSelect.addItemListener(this);
        this.bar.add(btnSelect);

        panelParcelas = new JPanel();
        normalBord = BorderFactory.createLineBorder(Color.GRAY, 3, false);
        bordSelect = BorderFactory.createLineBorder(Color.RED, 3, false);
        bordMouseOver = BorderFactory.createLineBorder(Color.BLUE, 3, false);

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

    @Override
    public void dispose() {
        super.dispose();
        Granja.ventanaPrincipal.setVisible(true);
    }

    @Override
    public void itemStateChanged(ItemEvent e) {
        if (e.getSource() == btnSelect) {
            if(e.getStateChange() == ItemEvent.SELECTED){
            } else {
                JLabel[] jLAux = new JLabel[rowPanel * colPanel];
                int count = 0;
                for (JLabel[] jLRow : parcelas) {
                    for (JLabel jLP : jLRow) {
                        if (jLP.getBorder() == bordSelect) {
                            jLAux[count++] = jLP;
                            jLP.setBorder(normalBord);
                        }
                    }
                }
            }
            this.isSelecting = !this.isSelecting;
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        JLabel lblEvent = (JLabel) (e.getSource());
        if (lblEvent.getBorder() == bordSelect) {
            lblEvent.setBorder(bordMouseOver);
            this.selected = null;
        } else if (this.isSelecting) {
            lblEvent.setBorder(bordSelect);
        } else {
            if (this.selected != null) {
                this.selected.setBorder(normalBord);
            }   
            lblEvent.setBorder(bordSelect);
            this.selected = lblEvent;
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        JLabel lblEvent = (JLabel) (e.getSource());
        if (lblEvent.getBorder() != bordSelect)
            lblEvent.setBorder(bordMouseOver);
    }

    @Override
    public void mouseExited(MouseEvent e) {
        JLabel lblEvent = (JLabel) (e.getSource());
        if (lblEvent.getBorder() != bordSelect)
            lblEvent.setBorder(normalBord);
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void mouseMoved(MouseEvent e) {
    }
}
