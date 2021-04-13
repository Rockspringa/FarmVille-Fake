package com.gui.frames;

import java.io.*;
import java.awt.*;
import java.awt.image.*;
import java.awt.event.*;

import javax.imageio.ImageIO;
import javax.management.InvalidAttributeValueException;
import javax.swing.*;
import javax.swing.border.*;
import javax.swing.event.MouseInputListener;

import com.gui.Frame;
import com.exec.Granja;
import com.logic.objetos.*;
import com.gui.images.Images;
import com.logic.array.Array;
import com.logic.objetos.posee_materia.*;
import com.logic.objetos.posee_materia.productos.*;
import com.logic.objetos.posee_materia.seres_vivos.*;
import com.logic.objetos.posee_materia.seres_vivos.plantas.*;

/**
 * Es la clase que se encarga de crear tanto el mercado y la bodega del juego, con esta clase se pueden mostrar
 * los items existentes que hay tanto en posesion del granjero, como disponibles para comprar.
 */
public class Tiega extends Frame implements MouseInputListener {
    /** Al utilizar esta variable en el constructor de <code>Tiega</code> se creara la interfaz para una tienda */
    public static final int TIENDA = 0;
    /** Al utilizar esta variable en el constructor de <code>Tiega</code> se creara la interfaz para una bodega */
    public static final int BODEGA = 1;
    private static final int WIDTH = 700;
    private static final int HEIGHT = 320;
    private static final int COL_PANEL = 5;
    private static final int ROW_PANEL = 2;
    private final Color menuItemOriginalColor;
    private Array<TitledBorder> topTittles;
    private Array<TitledBorder> botTittles;
    private Array<Border> bords;
    private PoseeMateria lblClicked;
    private JImagePanel panelTienda;
    private JPopupMenu menu;
    private JMenuItem comprarBtn;
    private JMenuItem venderBtn;
    private JMenuItem comerBtn;
    private JLabel[] objetos;
    private JButton btnIzq;
    private JButton btnDer;
    private JLabel pocoOro;
    private JLabel cantOro;
    private Border emptyBorder;
    private Border tituloGenerico;
    private Border linePressedBorder;
    private Border lineClickedBorder;
    private Border lblBordClicked;
    private Border lblBordEntered;
    private Font jetFont;
    private int iter;

    /**
     * El constructor crea una interfaz de muestrario de imagenes, de los objetos, pero estos solo seran llenados
     * al utilizar una de las dos variables estaticas publicas que existen dentro de esta misma clase.
     * @param disposicion
     */
    public Tiega(int disposicion) {
        super("", WIDTH, HEIGHT);
        if (disposicion == BODEGA) {
            this.setTitle("Bodega");
        } else if (disposicion == TIENDA) {
            this.setTitle("Mercado");
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

        this.objetos = new JLabel[10];
        if (disposicion == TIENDA) {
            this.llenarPanel(iter, Granja.getAnimales(),
                            Granja.getPlantas(), Granja.getProductos());

            this.comprarBtn = new JMenuItem("Comprar...");
            this.comprarBtn.addActionListener(this);
            this.menu.add(this.comprarBtn);

            this.pocoOro = new JLabel();
            this.pocoOro.setFont(jetFont);
            this.pocoOro.setText("<html><p style=\"width:230px\">Ocurrio "
                            + "un error durante la compra, aparentemente "
                            + "no posee suficiente oro.</p></html>");
            this.pocoOro.setHorizontalTextPosition(JLabel.LEFT);
        } else if (disposicion == BODEGA) {
            this.llenarPanel(iter, Granja.bob.getCrias(),
                            Granja.bob.getSemillas(), Granja.bob.getBodega());

            this.venderBtn = new JMenuItem();
            this.venderBtn.addActionListener(this);
            this.menu.add(this.venderBtn);

            this.comerBtn = new JMenuItem();
            this.comerBtn.addActionListener(this);
            this.comerBtn.setVisible(false);
            this.menu.add(this.comerBtn);

            this.cantOro = new JLabel();
            this.cantOro.setFont(jetFont);
            this.cantOro.setHorizontalTextPosition(JLabel.LEFT);
        }
    }

    /**
     * Funcion que pondra todos los labels en el panel.
     * @param numIter es el numero de veces que se ha llamado a la funcion, empezando desde 0.
     */
    private void llenarPanel(int numIter, Array<Animal> arAni,
                            Array<Planta> arPla, Array<Producto> arPro) {
        int maxAn = 0;
        int maxPl = 0;
        int maxPr = 0;
        int maxVa = 0;
        int arrIndex = 0;
        int faltanAn = arAni.length() - (numIter * 10);
        int faltanPl = arPla.length() + faltanAn;
        int faltanPr = arPro.length() + faltanPl;

        if (faltanAn > 0) {
            faltanPl = arPla.length();
            faltanPr = arPro.length();
        } else if (faltanPl > 0) {
            faltanPr = arPro.length();
        }

        if (faltanAn >= 10) {
            maxAn = 10;
        } else if (faltanAn > 0){
            maxAn = faltanAn;
            if (arPla.length() >= 10 - maxAn) {
                maxPl = 10 - maxAn;
            } else {
                maxPl = arPla.length();
                if (arPro.length() >= 10 - maxPl - maxAn ) {
                    maxPr = 10 - maxAn - maxPl;
                } else {
                    maxPr = arPro.length();
                }
            }
        } else {
            if (faltanPl >= 10) {
                maxPl = 10;
            } else if (faltanPl > 0) {
                maxPl = faltanPl;
                if (arPro.length() >= 10 - maxPl) {
                    maxPr = 10 - maxPl;
                } else {
                    maxPr = arPro.length();
                }
            } else {
                if (faltanPr >= 10) {
                    maxPr = 10;
                } else if (faltanPr > 0) {
                    maxPr = faltanPl;
                }
            }
        }
        maxVa = 10 - maxAn - maxPl - maxPr;

        int y = arAni.length() - faltanAn;
        for (int x = y; x < y + maxAn; x++) {
            this.llenarArray(arAni.get(x), arrIndex);
            arrIndex++;
        } y = arPla.length() - faltanPl;
        for (int x = y; x < y + maxPl; x++) {
            this.llenarArray(arPla.get(x), arrIndex);
            arrIndex++;
        } y = arPro.length() - faltanPr;
        for (int x = y; x < y + maxPr; x++) {
            this.llenarArray(arPro.get(x), arrIndex);
            arrIndex++;
        } for (int x = 0; x < maxVa; x++) {
            this.objetos[arrIndex] = new JLabel(Images.CAJA_VACIA_IMAGE);
            this.objetos[arrIndex].setBorder(tituloGenerico);
            this.objetos[arrIndex].addMouseListener(this);
            this.panelTienda.add(this.objetos[arrIndex]);
            arrIndex++;
        }
    }

    /**
     * Se encarga de tomar los datos de lo que posea materia para poder crear un borde con los mismos
     * y hacer un JLable con su nombre, llenando el array designado para ello.
     * @param mater es el objeto del cual se tomaran los datos e imagen.
     * @param arrIndex es la posicion en la cual se guardara el JLabel y el borde.
     */
    private void llenarArray(PoseeMateria mater, int arrIndex) {
        this.objetos[arrIndex] = new JLabel(mater.getImage());

        this.topTittles.set(BorderFactory.createTitledBorder(this.emptyBorder,
                                mater.getNombre(), TitledBorder.CENTER,
                                TitledBorder.TOP, this.jetFont, Color.WHITE), arrIndex);
        this.botTittles.set(BorderFactory.createTitledBorder(this.emptyBorder,
                                mater.getPrecio() + " de oro",
                                TitledBorder.CENTER, TitledBorder.BOTTOM,
                                this.jetFont, Color.BLACK), arrIndex);

        this.bords.set(BorderFactory.createCompoundBorder(this.botTittles.get(arrIndex),
                                this.topTittles.get(arrIndex)), arrIndex);

        this.objetos[arrIndex].setBorder(this.bords.get(arrIndex));
        this.objetos[arrIndex].addMouseListener(this);
        this.panelTienda.add(this.objetos[arrIndex]);
    }

    /**
     * Modificacion de un JPanel para que pueda mostrar una imagen de fondo.
     */
    private class JImagePanel extends JPanel {
        private final BufferedImage img;

        /**
         * Arma el JPanel con su imagen de fondo.
         * @param lm es el layout a utilizar dentro del JPanel.
         * @param img es la imagen de fondo a utilizar, no se puede cambiar.
         */
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

    /**
     * Al pasarle un archivo, siendo una imagen, leera el contenido de imagen del archivo.
     * @param arch es el archivo a pasar a BufferedImage.
     * @return un BufferedImage de lograrse leer el archivo o null de lo contrario.
     */
    private BufferedImage getImage(File arch) {
        try {
            return ImageIO.read(arch);
        } catch (IOException e) {
            return null;
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // Boton para comprar en el mercado
        if (e.getSource() == comprarBtn) {
            if (Granja.bob.isEnough(this.lblClicked.getPrecio())) {
                if (this.lblClicked instanceof Barco) {
                    Granja.bob.addProducto(new Barco());
                } else if (this.lblClicked instanceof Animal) {
                    Granja.bob.addCria(new Animal((Animal) (this.lblClicked)));
                } else if (this.lblClicked instanceof Arbol) {
                    Granja.bob.addSemilla(new Arbol((Arbol) (this.lblClicked)));
                } else if (this.lblClicked instanceof Cultivo) {
                    Granja.bob.addSemilla(new Cultivo((Cultivo) (this.lblClicked)));
                } else if (this.lblClicked instanceof Fertilizante) {
                    Granja.bob.addProducto(new Fertilizante((Fertilizante) (this.lblClicked)));
                } else if (this.lblClicked instanceof Alimento) {
                    Granja.bob.addProducto(new Alimento((Alimento) (this.lblClicked)));
                } else if (this.lblClicked instanceof Producto) {
                    Granja.bob.addProducto(new Producto((Producto) (this.lblClicked)));
                } Granja.bob.setOro(this.lblClicked.getPrecio());
            } else {
                JOptionPane.showMessageDialog(this, this.pocoOro, "Oro insuficiente", JOptionPane.ERROR_MESSAGE);
            } this.lblClicked = null;
        }
        // Venta de objetos en la bodega
        if (e.getSource() == venderBtn) {
            int dineroAnterior = Granja.bob.getOro();
            try {
                if (this.lblClicked instanceof Animal) {
                    Granja.bob.getCrias().pop((Animal) (this.lblClicked));
                } else if (this.lblClicked instanceof Planta) {
                    Granja.bob.getSemillas().pop((Planta) (this.lblClicked));
                } else if (this.lblClicked instanceof Producto) {
                    Granja.bob.getBodega().pop((Producto) (this.lblClicked));
                }
                Granja.bob.ganaOro(this.lblClicked.getPrecio());
                this.cantOro.setText("La cantidad de " + dineroAnterior
                            + " de oro, ahora es de " + Granja.bob.getOro()
                            + " de oro.");
                JOptionPane.showMessageDialog(this, this.cantOro, "Cantidad de oro",
                            JOptionPane.INFORMATION_MESSAGE);
            } catch (InvalidAttributeValueException error) {
                JOptionPane.showMessageDialog(this, "Ocurrio un error encontrando "
                            + "el objeto seleccionado, pruebe mas tarde.", "No encontrado",
                            JOptionPane.ERROR_MESSAGE);
            }
            this.panelTienda.removeAll();
            this.llenarPanel(iter, Granja.bob.getCrias(), Granja.bob.getSemillas(), Granja.bob.getBodega());
            this.panelTienda.revalidate();
            this.panelTienda.repaint();
        }
        // Boton para comprar en la bodega
        if (e.getSource() == comerBtn) {
            int vidaAnterior = Granja.bob.getVida();
            try {
                Granja.bob.getBodega().pop((Producto) (this.lblClicked));
                Granja.bob.alimentarse((Producto) (this.lblClicked));
                this.cantOro.setText("Te has curado, antes tenias " + vidaAnterior
                            + " de vida, ahora tenes " + Granja.bob.getVida()
                            + " puntos de vida.");
                JOptionPane.showMessageDialog(this, this.cantOro, "Cantidad de vida",
                            JOptionPane.INFORMATION_MESSAGE);
            } catch (InvalidAttributeValueException error) {
                JOptionPane.showMessageDialog(this, "Ocurrio un error encontrando "
                            + "el objeto seleccionado, pruebe mas tarde.", "Error 404, Not Found",
                            JOptionPane.ERROR_MESSAGE);
            }
            this.panelTienda.removeAll();
            this.llenarPanel(iter, Granja.bob.getCrias(), Granja.bob.getSemillas(), Granja.bob.getBodega());
            this.panelTienda.revalidate();
            this.panelTienda.repaint();
        }
        // Boton para pasar a la siguiente pagina
        if (e.getSource() == btnDer) {
            Array<Animal> arAni = null;
            Array<Planta> arPla = null;
            Array<Producto> arPro = null;
            if (this.getTitle().equals("Mercado")) {
                arAni = Granja.getAnimales();
                arPla = Granja.getPlantas();
                arPro = Granja.getProductos();
            } else if (this.getTitle().equals("Bodega")) {
                arAni = Granja.bob.getCrias();
                arPla = Granja.bob.getSemillas();
                arPro = Granja.bob.getBodega();
            }

            int iterAnterior = iter;
            int index = (iter + 1) * 10;
            index -= arAni.length();
            index -= arPla.length();
            index -= arPro.length();

            if (iter != 0 || index < 0) {
                this.panelTienda.removeAll();
            }
            if (index < 0) {
                this.llenarPanel(++iter, arAni, arPla, arPro);
            } else if (iter != 0) {
                iter = 0;
                this.llenarPanel(iter, arAni, arPla, arPro);
            }
            if (iterAnterior != 0 || index < 0) {
                this.panelTienda.revalidate();
                this.panelTienda.repaint();
            }
        } if (e.getSource() == btnIzq) {
            Array<Animal> arAni = null;
            Array<Planta> arPla = null;
            Array<Producto> arPro = null;
            if (this.getTitle().equals("Mercado")) {
                arAni = Granja.getAnimales();
                arPla = Granja.getPlantas();
                arPro = Granja.getProductos();
            } else if (this.getTitle().equals("Bodega")) {
                arAni = Granja.bob.getCrias();
                arPla = Granja.bob.getSemillas();
                arPro = Granja.bob.getBodega();
            }

            int iterAnterior = iter;
            int index = (iter + 1) * 10;
            index -= arAni.length();
            index -= arPla.length();
            index -= arPro.length();

            if (iter > 0 || index < 0) {
                this.panelTienda.removeAll();
            }
            if (iter > 0) {
                this.llenarPanel(--iter, arAni, arPla, arPro);
            } else if (index < 0) {
                while (true) {
                    int numSumado = 2;
                    index = (iter + numSumado) * 10;
                    index -= arAni.length();
                    index -= arPla.length();
                    index -= arPro.length();
        
                    if (index > 0) {
                        --numSumado;
                        index -= 10;
                        iter += numSumado;
                        this.llenarPanel(iter, arAni, arPla, arPro);
                        break;
                    } numSumado++;
                }
            }
            if (iterAnterior > 0 || index < 0) {
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
            Array<Animal> arAni = null;
            Array<Planta> arPla = null;
            Array<Producto> arPro = null;
            if (this.getTitle().equals("Mercado")) {
                arAni = Granja.getAnimales();
                arPla = Granja.getPlantas();
                arPro = Granja.getProductos();
                this.comprarBtn.setBackground(this.menuItemOriginalColor);
                this.comprarBtn.setForeground(Color.BLACK);
                this.comprarBtn.setIcon(null);
            } else if (this.getTitle().equals("Bodega")) {
                arAni = Granja.bob.getCrias();
                arPla = Granja.bob.getSemillas();
                arPro = Granja.bob.getBodega();
            }


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
            } else if (arPro.length() + arPla.length() + arPro.length() > indexEv) {
                this.lblClicked = arPro.get(indexEv - arAni.length() - arPla.length());
            }

            if (this.getTitle().equals("Mercado")
                            && !Granja.bob.isEnough(this.lblClicked.getPrecio())) {
                this.comprarBtn.setBackground(new Color(163, 184, 204));
                this.comprarBtn.setForeground(Color.RED);
                this.comprarBtn.setIcon(Images.ERROR_IMAGE);
            } else if (this.getTitle().equals("Bodega")) {
                this.venderBtn.setText("Vender por " + this.lblClicked.getPrecio() + " de oro.");
                if (this.lblClicked instanceof Alimento) {
                    Alimento alim = (Alimento) (this.lblClicked);
                    this.comerBtn.setText("Recuperar " + alim.getVidaRecuperable() + " de vida.");
                    this.comerBtn.setVisible(true);
                }
            }
            menu.show(e.getComponent(), e.getX(), e.getY());
        }
    }

    @Override
    public void mouseDragged(MouseEvent e) {}

    @Override
    public void mouseMoved(MouseEvent e) {}
}
