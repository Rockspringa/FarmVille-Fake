package com.gui.frames;

import java.awt.image.*;
import java.io.File;
import java.io.IOException;
import java.awt.event.*;
import java.awt.*;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.*;
import javax.swing.event.MouseInputListener;

import com.exec.Granja;
import com.gui.Frame;
import com.gui.images.Images;
import com.logic.array.Array;
import com.logic.objetos.SerVivo;
import com.logic.objetos.seres_vivos.*;

public class Tiega extends Frame implements MouseInputListener {
    public static final int TIENDA = 0;
    public static final int BODEGA = 1;
    private static final int WIDTH = 700;
    private static final int HEIGHT = 320;
    private static final int COL_PANEL = 5;
    private static final int ROW_PANEL = 2;
    private Array<TitledBorder> topTittles;
    private Array<TitledBorder> botTittles;
    private Array<Border> bords;
    private JImagePanel panelTienda;
    private JLabel[] objetos;
    private JButton btnIzq;
    private JButton btnDer;
    private Border emptyBorder;
    private Border tituloGenerico;
    private Border linePressedBorder;
    private Border lineClickedBorder;
    private Border lblBordClicked;
    private Border lblBordEntered;
    private JLabel lblClicked;
    private Font jetFont;
    private int iter;

    public Tiega(int disposicion) {
        super("Tienda", WIDTH, HEIGHT);
        if (disposicion == BODEGA) {
            this.setTitle("Bodega");
        } this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        Dimension dim = new Dimension(30, 250);
        JPanel win = (JPanel) (this.getContentPane());

        win.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 15));
        win.setAlignmentX(JPanel.CENTER_ALIGNMENT);

        this.btnIzq = new JButton();
        this.btnIzq.addActionListener(this);
        this.btnIzq.setIcon(Images.FLECHA_IZQ_IMAGE);
        this.btnIzq.setPreferredSize(dim);
        win.add(this.btnIzq);

        BufferedImage img = null;
        img = getImage(Images.MADERAS_FILE);
        this.panelTienda = new JImagePanel(new GridLayout(ROW_PANEL, COL_PANEL, 5, 5), img);
        this.panelTienda.setPreferredSize(new Dimension(580, 250));
        this.panelTienda.setBorder(new EmptyBorder(13, 5, 12, 5));
        win.add(this.panelTienda);

        this.btnDer = new JButton();
        this.btnDer.addActionListener(this);
        this.btnDer.setIcon(Images.FLECHA_DER_IMAGE);
        this.btnDer.setPreferredSize(dim);
        win.add(this.btnDer);

        this.emptyBorder = BorderFactory.createEmptyBorder();
        this.linePressedBorder = BorderFactory.createLineBorder(Color.CYAN, 4);
        this.lineClickedBorder = BorderFactory.createLineBorder(Color.BLUE, 8);
        this.topTittles = new Array<TitledBorder>();
        this.botTittles = new Array<TitledBorder>();
        this.bords = new Array<Border>();

        TitledBorder titleBord1 = BorderFactory.createTitledBorder(this.emptyBorder, "Vacio");
        TitledBorder titleBord2 = BorderFactory.createTitledBorder(this.emptyBorder, "Precio");
        jetFont = new Font("JetBrainsMono Nerd Font Mono", 1, 12);

        titleBord1.setTitleJustification(TitledBorder.CENTER);
        titleBord1.setTitleColor(Color.WHITE);
        titleBord1.setTitleFont(this.jetFont);

        titleBord2.setTitleColor(Color.YELLOW);
        titleBord2.setTitlePosition(TitledBorder.BOTTOM);
        titleBord2.setTitleJustification(TitledBorder.CENTER);
        titleBord2.setTitleFont(this.jetFont);

        this.tituloGenerico = BorderFactory.createCompoundBorder(titleBord2, titleBord1);

        this.objetos = new JLabel[10];
        this.llenarPanel(iter++);
    }

    /**
     * Funcion que pondra todos los labels en el panel.
     * @param numIter es el numero de veces que se ha llamado a la funcion, empezando desde 0.
     */
    private void llenarPanel(int numIter) {
        Array<Animal> arAni= Granja.getAnimales();
        Array<Planta> arPla= Granja.getPlantas();
        int maxAn = 0;
        int maxPl = 0;
        int maxVa = 0;
        int arrIndex = 0;
        int numMostA = arAni.length() - (numIter * 10);
        int numMostP = -numMostA;

        if (numMostA >= 10) {
            maxAn = 10;
            maxPl = 0;
            maxVa = 0;
        } else if (numMostA > 0){
            maxAn = numMostA;
            if (arPla.length() >= 10 - maxAn) {
                maxPl = 10 - maxAn;
            } else {
                maxPl = arPla.length();
            }
        } else {
            maxAn = 0;
            if (arPla.length() - (numMostP) >= 10) {
                maxPl = 10;
            } else {
                maxPl = arPla.length() - (numMostP);
            }
        }
        maxVa = 10 - maxAn - maxPl;
        if (numMostP < 0) {
            numMostP = 0;
        }
        
        for (int x = numIter * 10; x < maxAn + (numIter * 10); x++) {
            this.llenarArray(arAni.get(x), this.objetos[arrIndex], arrIndex);
            arrIndex++;
        } for (int x = numMostP; x < maxPl + numMostP; x++) {
            this.llenarArray(arPla.get(x), this.objetos[arrIndex], arrIndex);
            arrIndex++;
        } for (int x = 0; x < maxVa; x++) {
            this.objetos[arrIndex] = new JLabel(Images.CAJA_VACIA_IMAGE);
            this.objetos[arrIndex].setBorder(tituloGenerico);
            this.objetos[arrIndex].addMouseListener(this);
            this.panelTienda.add(this.objetos[arrIndex]);
            arrIndex++;
        }
    }

    private void llenarArray(SerVivo ser, JLabel obj, int arrIndex) {
        obj = new JLabel(ser.getImage());

        this.topTittles.set(BorderFactory.createTitledBorder(this.emptyBorder,
                                ser.getNombre(), TitledBorder.CENTER,
                                TitledBorder.TOP, this.jetFont, Color.WHITE), arrIndex);
        this.botTittles.set(BorderFactory.createTitledBorder(this.emptyBorder,
                                ser.getPrecio() + " de oro",
                                TitledBorder.CENTER, TitledBorder.BOTTOM,
                                this.jetFont, Color.ORANGE), arrIndex);

        this.bords.set(BorderFactory.createCompoundBorder(this.botTittles.get(arrIndex),
                                this.topTittles.get(arrIndex)), arrIndex);

        obj.setBorder(this.bords.get(arrIndex));
        obj.addMouseListener(this);
        this.panelTienda.add(obj);
        
    }

    private class JImagePanel extends JPanel {
        BufferedImage img;
        public JImagePanel(LayoutManager lm, BufferedImage img) {
            super(lm);
            this.img = img;
        }

        @Override
        public void paintComponent(Graphics g) {
            super.paintComponent(g);
            g.drawImage(img, 0, 0, this);
        }
    }

    private BufferedImage getImage(File arch) {
        try {
            return ImageIO.read(arch);
        } catch (IOException e) {
            return null;
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        JLabel ev = (JLabel) (e.getSource());
        lblBordEntered = ev.getBorder();
        ev.setBorder(BorderFactory.createCompoundBorder(linePressedBorder, lblBordEntered));
    }

    @Override
    public void mouseExited(MouseEvent e) {
        JLabel ev = (JLabel) (e.getSource());
        ev.setBorder(lblBordEntered);
        lblBordEntered = null;
    }

    @Override
    public void mousePressed(MouseEvent e) {
        JLabel ev = (JLabel) (e.getSource());
        lblBordClicked = ev.getBorder();
        ev.setBorder(BorderFactory.createCompoundBorder(lineClickedBorder, lblBordEntered));
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        JLabel ev = (JLabel) (e.getSource());
        CompoundBorder evBorder = (CompoundBorder) (lblBordClicked);
        if (lblBordEntered != evBorder.getInsideBorder()) {
            lblBordClicked = evBorder.getInsideBorder();
        }
        ev.setBorder(lblBordClicked);
        lblBordClicked = null;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        // TODO Auto-generated method stub
        
    }

}
