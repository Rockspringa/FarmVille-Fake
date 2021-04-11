package com.gui.frames;

import java.io.*;
import java.awt.*;
import java.awt.image.*;
import java.awt.event.*;

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
import com.logic.objetos.seres_vivos.plantas.*;

public class Tiega extends Frame implements MouseInputListener {
    public static final int TIENDA = 0;
    public static final int BODEGA = 1;
    private static final int WIDTH = 700;
    private static final int HEIGHT = 320;
    private static final int COL_PANEL = 5;
    private static final int ROW_PANEL = 2;
    private final Color menuItemOriginalColor;
    private Array<TitledBorder> topTittles;
    private Array<TitledBorder> botTittles;
    private Array<Border> bords;
    private JImagePanel panelTienda;
    private JPopupMenu menu;
    private JMenuItem comprarBtn;
    private JLabel[] objetos;
    private SerVivo lblClicked;
    private JButton btnIzq;
    private JButton btnDer;
    private JLabel pocoOro;
    private Border emptyBorder;
    private Border tituloGenerico;
    private Border linePressedBorder;
    private Border lineClickedBorder;
    private Border lblBordClicked;
    private Border lblBordEntered;
    private Font jetFont;
    private int iter;

    public Tiega(int disposicion) {
        super("Mercado", WIDTH, HEIGHT);
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
        this.jetFont = new Font("JetBrainsMono Nerd Font Mono", 1, 12);

        titleBord1.setTitleJustification(TitledBorder.CENTER);
        titleBord1.setTitleColor(Color.WHITE);
        titleBord1.setTitleFont(this.jetFont);

        titleBord2.setTitleColor(Color.YELLOW);
        titleBord2.setTitlePosition(TitledBorder.BOTTOM);
        titleBord2.setTitleJustification(TitledBorder.CENTER);
        titleBord2.setTitleFont(this.jetFont);

        this.tituloGenerico = BorderFactory.createCompoundBorder(titleBord2, titleBord1);

        this.menu = new JPopupMenu();
        this.menuItemOriginalColor = this.menu.getBackground();

        if (disposicion == TIENDA) {
            this.objetos = new JLabel[10];
            this.llenarPanel(iter);

            this.comprarBtn = new JMenuItem("Comprar...");
            this.comprarBtn.addActionListener(this);
            this.menu.add(comprarBtn);

            this.pocoOro = new JLabel();
            this.pocoOro.setFont(jetFont);
            this.pocoOro.setText("<html><p style=\"width:230px\">Ocurrio "
                            + "un error durante la compra, aparentemente "
                            + "no posee suficiente oro.</p></html>");
            this.pocoOro.setHorizontalTextPosition(JLabel.LEFT);
        } else if (disposicion == BODEGA) {

        }
    }

    /**
     * Funcion que pondra todos los labels en el panel.
     * @param numIter es el numero de veces que se ha llamado a la funcion, empezando desde 0.
     */
    private void llenarPanel(int numIter) {
        Array<Animal> arAni = Granja.getAnimales();
        Array<Planta> arPla = Granja.getPlantas();
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
            this.llenarArray(arAni.get(x), arrIndex);
            arrIndex++;
        } for (int x = numMostP; x < maxPl + numMostP; x++) {
            this.llenarArray(arPla.get(x), arrIndex);
            arrIndex++;
        } for (int x = 0; x < maxVa; x++) {
            this.objetos[arrIndex] = new JLabel(Images.CAJA_VACIA_IMAGE);
            this.objetos[arrIndex].setBorder(tituloGenerico);
            this.objetos[arrIndex].addMouseListener(this);
            this.panelTienda.add(this.objetos[arrIndex]);
            arrIndex++;
        }
    }

    private void llenarArray(SerVivo ser, int arrIndex) {
        this.objetos[arrIndex] = new JLabel(ser.getImage());

        this.topTittles.set(BorderFactory.createTitledBorder(this.emptyBorder,
                                ser.getNombre(), TitledBorder.CENTER,
                                TitledBorder.TOP, this.jetFont, Color.WHITE), arrIndex);
        this.botTittles.set(BorderFactory.createTitledBorder(this.emptyBorder,
                                ser.getPrecio() + " de oro",
                                TitledBorder.CENTER, TitledBorder.BOTTOM,
                                this.jetFont, Color.BLACK), arrIndex);

        this.bords.set(BorderFactory.createCompoundBorder(this.botTittles.get(arrIndex),
                                this.topTittles.get(arrIndex)), arrIndex);

        this.objetos[arrIndex].setBorder(this.bords.get(arrIndex));
        this.objetos[arrIndex].addMouseListener(this);
        this.panelTienda.add(this.objetos[arrIndex]);
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
        if (e.getSource() == comprarBtn) {
            if (Granja.bob.isEnough(this.lblClicked.getPrecio())) {
                if (this.lblClicked instanceof Animal) {
                    Granja.bob.addCria(new Animal((Animal) (this.lblClicked)));
                } else if (this.lblClicked instanceof Arbol) {
                    Granja.bob.addSemilla(new Arbol((Arbol) (this.lblClicked)));
                } else if (this.lblClicked instanceof Cultivo) {
                    Granja.bob.addSemilla(new Cultivo((Cultivo) (this.lblClicked)));
                } Granja.bob.setOro(this.lblClicked.getPrecio());
            } else {
                this.comprarBtn.setBackground(this.menuItemOriginalColor);
                this.comprarBtn.setForeground(Color.BLACK);
                this.comprarBtn.setIcon(null);
                JOptionPane.showMessageDialog(this, this.pocoOro, "Oro insuficiente", JOptionPane.ERROR_MESSAGE);
            }
        } if (e.getSource() == btnDer) {
            int index = (iter + 1) * 10;
            if (this.getTitle() == "Mercado") {
                index -= Granja.getAnimales().length();
                index -= Granja.getPlantas().length();
            } else if (this.getTitle() == "Bodega") {
                index -= Granja.bob.getBodega().length();
                index -= Granja.bob.getSemillas().length();
                index -= Granja.bob.getCrias().length();
            }

            if (iter != 0 || index < 0) {
                this.panelTienda.removeAll();
            }
            if (index < 0) {
                this.llenarPanel(++iter);
            } else if (iter != 0) {
                iter = 0;
                this.llenarPanel(iter);
            }
            if (iter != 0 || index < 0) {
                this.panelTienda.revalidate();
                this.panelTienda.repaint();
            }
        } if (e.getSource() == btnIzq) {
            int index = (iter + 1) * 10;
            if (this.getTitle() == "Mercado") {
                index -= Granja.getAnimales().length();
                index -= Granja.getPlantas().length();
            } else if (this.getTitle() == "Bodega") {
                index -= Granja.bob.getBodega().length();
                index -= Granja.bob.getSemillas().length();
                index -= Granja.bob.getCrias().length();
            }

            if (iter > 0 || index < 0) {
                this.panelTienda.removeAll();
            }
            if (iter > 0) {
                this.llenarPanel(--iter);
            } else if (index < 0) {
                while (true) {
                    int numSumado = 2;
                    index = (iter + numSumado) * 10;
                    if (this.getTitle() == "Mercado") {
                        index -= Granja.getAnimales().length();
                        index -= Granja.getPlantas().length();
                    } else if (this.getTitle() == "Bodega") {
                        index -= Granja.bob.getBodega().length();
                        index -= Granja.bob.getSemillas().length();
                        index -= Granja.bob.getCrias().length();
                    }
                    if (index > 0) {
                        --numSumado;
                        index -= 10;
                        iter += numSumado;
                        this.llenarPanel(iter);
                        break;
                    } numSumado++;
                }
            }
            if (iter > 0 || index < 0) {
                this.panelTienda.revalidate();
                this.panelTienda.repaint();
            }
        }
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
        JLabel ev = (JLabel) (e.getSource());
        if (ev.getIcon() != Images.CAJA_VACIA_IMAGE) {
            Array<Animal> arAni = Granja.getAnimales();
            Array<Planta> arPla = Granja.getPlantas();
            int indexEv = -1;
            for (int x = 0; x < 10; x++) {
                if (this.objetos[x] == ev) {
                    indexEv = x;
                    break;
                }
            } indexEv += 10 * this.iter;
            if (arAni.length() > indexEv) {
                this.lblClicked = arAni.get(indexEv);
            } else if (arPla.length() + arAni.length() > indexEv) {
                this.lblClicked = arPla.get(indexEv - arAni.length());
            }

            if (!Granja.bob.isEnough(this.lblClicked.getPrecio())) {
                this.comprarBtn.setBackground(new Color(163, 184, 204));
                this.comprarBtn.setForeground(Color.RED);
                this.comprarBtn.setIcon(Images.ERROR_IMAGE);
            }
            menu.show(e.getComponent(), e.getX(), e.getY());
        }
    }

    @Override
    public void mouseDragged(MouseEvent e) {}

    @Override
    public void mouseMoved(MouseEvent e) {}
}
