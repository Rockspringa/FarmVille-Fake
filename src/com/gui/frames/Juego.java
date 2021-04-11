package com.gui.frames;

import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.event.MouseInputListener;

import java.awt.*;

import com.exec.Granja;
import com.gui.Frame;
import com.gui.images.Images;
import com.logic.array.Array;

public class Juego extends Frame implements MouseInputListener, ItemListener {
    private Array<JMenuItem> subMenuAnimales;
    private Array<JMenuItem> subMenuPlantas;
    private JToggleButton btnSelect;
    private JScrollPane scrollable;
    private JLabel[][] parcelas;
    private JMenuItem submenuAgua;
    private Container panelParcelas;
    private JMenuBar bar;
    private JLabel[] vida;
    private JButton btnAgregar;
    private JButton btnTienda;
    private boolean isSelecting = false;
    private JMenu[] submenuGrama;
    private Border normalBord;
    private Border bordSelect;
    private Border bordMouseOver;
    private JLabel selected;
    private JPanel panelVida;
    private JMenu actividades;
    private Tiega tiendaBodega;
    private int widthPanel = 550;
    private int heightPanel = 550;
    private int rowPanel = 5;
    private int colPanel = 5;

    /**
     * Modifica el tamaño del array de JLabels', pasa los JLabels' al nuevo array,
     * y modifica las medidas para que se mantengan las medidas de los JLabels'.
     */
    private void resizeParcelas() {
        JLabel[][] arrAux = new JLabel[this.rowPanel + 1][this.colPanel + 1];
        for (int m = 0; m < this.rowPanel; m++) {
            for (int n = 0; n < this.colPanel; n++) {
                arrAux[m][n] = parcelas[m][n];
            }
        }

        this.parcelas = arrAux;
        this.widthPanel += 110;
        this.heightPanel += 110;
        this.rowPanel++;
        this.colPanel++;

        this.drawParcelas();
    }

    /**
     * Vacia el panel que en donde se muestran los suelos lo revalida y repinta para
     * que se muestre instantaneamente el cambio, cambia el tamaño del panel para
     * que se mantenga el tamaño designado para las celdas, se iguala la cantidad de
     * suelos que hay guardados en la granja y toma sus imagenes y la añade al panel.
     */
    private void drawParcelas() {
        panelParcelas.removeAll();
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
        
        panelParcelas.revalidate();
        panelParcelas.repaint();
    }
    
    public Juego() {
        /* Modifica el JPanel predeterminado del Frame. */
        super("FarmFake");
        JPanel window = (JPanel) (this.getContentPane());
        window.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 1));
        window.setAlignmentX(JPanel.LEFT_ALIGNMENT);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        /* Creacion de la barra de menus, con botones y demas */
        this.bar = new JMenuBar();
        this.setJMenuBar(bar);

        this.btnAgregar = new JButton("Agregar parcela");
        this.btnAgregar.addActionListener(this);
        this.bar.add(this.btnAgregar);

        this.btnTienda = new JButton("Abrir Mercado");
        this.btnTienda.addActionListener(this);
        this.bar.add(this.btnTienda);

        this.btnSelect = new JToggleButton("Seleccionar parcelas");
        this.btnSelect.addItemListener(this);
        this.bar.add(this.btnSelect);

        this.actividades = new JMenu("Actividades");
        this.actividades.setVisible(false);
        this.actividades.setOpaque(true);
        this.bar.add(this.actividades);

        this.submenuGrama = new JMenu[2];
        this.submenuGrama[0] = new JMenu("Siembra");
        this.submenuGrama[1] = new JMenu("Crianza");
        this.submenuGrama[0].setVisible(false);
        this.submenuGrama[1].setVisible(false);
        this.actividades.add(this.submenuGrama[0]);
        this.actividades.add(this.submenuGrama[1]);

        this.submenuAgua = new JMenuItem("Pescar");
        this.submenuAgua.setVisible(false);
        this.actividades.add(this.submenuAgua);

        /* Creacion de una especie de barra para mostrar la vida del granjero y el oro del mismo. */
        this.panelVida = new JPanel();
        this.panelVida.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 0));
        this.panelVida.setAlignmentX(JPanel.LEFT_ALIGNMENT);
        this.panelVida.setPreferredSize(new Dimension(440, 40));
        window.add(this.panelVida);

        /* Creacion de labels para la cantidad de vida. */
        this.vida = new JLabel[21];
        
        this.vida[0] = new JLabel(Images.CABEZA_IMAGE);
        this.vida[0].setOpaque(true);
        this.vida[0].setPreferredSize(new Dimension(40, 40));
        this.panelVida.add(this.vida[0]);

        for (int x = 1; x < 21; x++) {
            this.vida[x] = new JLabel(Images.CORAZON_IMAGE);
            this.vida[x].setOpaque(true);
            this.vida[x].setPreferredSize(new Dimension(20, 20));
            this.panelVida.add(this.vida[x]);
        }

        /* Creacion de panel de parcelas, llenado de parcelas y los bordes que se utilizaran
            en las parcelas */
        this.panelParcelas = new JPanel();
        normalBord = BorderFactory.createLineBorder(Color.GRAY, 3, false);
        bordSelect = BorderFactory.createLineBorder(Color.RED, 3, false);
        bordMouseOver = BorderFactory.createLineBorder(Color.BLUE, 3, false);

        Granja.iniciarParcela();
        this.parcelas = new JLabel[5][5];
        this.drawParcelas();

        /* JScrollPane que contendra el panel de parcelas para que no se redimensionen las parcelas */
        this.scrollable = new JScrollPane(panelParcelas);
        this.scrollable.setSize(widthPanel, heightPanel);
        window.add(scrollable);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnAgregar) {
            if (Granja.newParcela()) {
                if (rowPanel == 5)
                    this.scrollable.setSize(widthPanel + 48, heightPanel + 48);
                    this.scrollable.setPreferredSize(new Dimension(widthPanel + 48, heightPanel + 48));
                this.resizeParcelas();
            } else {
                this.drawParcelas();
            }
        } if (e.getSource() == btnTienda) {
            if (tiendaBodega != null) {
                tiendaBodega.dispose();
                tiendaBodega = null;
            }
            tiendaBodega = new Tiega(Tiega.TIENDA);
            tiendaBodega.seeIt();
        }
    }

    @Override
    public void dispose() {
        super.dispose();
        if (tiendaBodega != null) {
            tiendaBodega.dispose();
            tiendaBodega = null;
        }
        Granja.bob.morir();
        Granja.ventanaPrincipal.setVisible(true);
    }

    /**
     * Disminuye la vida del granjero, solo visualmente.
     */
    public void bajoVida() {
        for (int x = 20; x > 0; x--) {
            if (this.vida[x].isVisible()) {
                this.vida[x].setVisible(false);
                break;
            }
        }
    }

    @Override
    public void itemStateChanged(ItemEvent e) {
        if (e.getSource() == btnSelect) {
            if(e.getStateChange() == ItemEvent.SELECTED){
                this.btnSelect.setText("Realizar accion");
            } else {
                this.btnSelect.setText("Seleccionar parcelas");
                Array<JLabel> jLAux = new Array<JLabel>();
                for (int m = 0; m < parcelas.length; m++) {
                    for (int n = 0; n < parcelas[m].length; n++) {
                        if (parcelas[m][n].getBorder() == bordSelect) {
                            jLAux.add(parcelas[m][n]);
                            parcelas[m][n].setBorder(normalBord);
                        }
                    }
                }
            }
            this.isSelecting = !this.isSelecting;
        }
    }

    /**
     * Cada vez que un JLabel de parcelas sea clickeado hara que se cambie el borde,
     * se cambiara a un borde que de a entender que esta seleccionado o que se deselecciono,
     * si esta presionado el boton seleccionar se realizara la misma accion pero no limitado
     * a un solo JLabel.
     */
    @Override
    public void mouseClicked(MouseEvent e) {
        JLabel lblEvent = (JLabel) (e.getSource());
        this.actividades.setVisible(true);
        boolean sonIguales = true;

        if (lblEvent.getIcon() == Images.BLOQUEADO_IMAGE) {}
        else if (lblEvent.getBorder() == bordSelect) {
            lblEvent.setBorder(bordMouseOver);
            if (!isSelecting) {
                this.selected = null;
                sonIguales = false;
            }
        } else if (this.isSelecting) {
            lblEvent.setBorder(bordSelect);

            if (this.selected == null) {
                this.selected = lblEvent;
            }           
        } else {
            if (this.selected != null) {
                this.selected.setBorder(normalBord);
            }
            lblEvent.setBorder(bordSelect);
            this.selected = lblEvent;
            sonIguales = true;
        }        

        JLabel anterior = null;
        boolean salir = false;
        for (int m = 0; m < colPanel; m++) {
            for (int n = 0; n < rowPanel; n++) {
                if (parcelas[m][n].getBorder() == this.bordSelect) {
                    if (anterior != null &&
                            anterior.getIcon() != parcelas[m][n].getIcon()) {
                        sonIguales = false;
                        anterior = null;
                        salir = true;
                        break;
                    } else {
                        anterior = parcelas[m][n];
                        sonIguales = true;
                    }
                }
            }
            if (salir)
                break;
        }

        if (!this.isSelecting && this.selected == null || anterior == null)
            sonIguales = false;
        
        if (sonIguales && anterior.getIcon() == Images.GRAMA_IMAGE) {
            this.submenuGrama[0].setVisible(true);
            this.submenuGrama[1].setVisible(true);
            this.submenuAgua.setVisible(false);
        } else if (sonIguales && anterior.getIcon() == Images.AGUA_IMAGE) {
            this.submenuGrama[0].setVisible(false);
            this.submenuGrama[1].setVisible(false);
            this.submenuAgua.setVisible(true);
        } else {
            this.actividades.setVisible(false);
        }
    }
    
    /**
     * Cuando el mouse entre en el area del JLabel de una parcela este cambiara de color,
     * siendo el unico que posea este color distintivo, excepto si esta seleccionado.
     */
    @Override
    public void mouseEntered(MouseEvent e) {
        JLabel lblEvent = (JLabel) (e.getSource());
        if (lblEvent.getIcon() == Images.BLOQUEADO_IMAGE) {}
        else if (lblEvent.getBorder() != bordSelect)
            lblEvent.setBorder(bordMouseOver);
    }

    /**
     * Detecta cuando el mouse acabe de salir del area del JLabel parcela para devolver el
     * borde normal, o predefinido, a menos que este este seleccionado.
     */
    @Override
    public void mouseExited(MouseEvent e) {
        JLabel lblEvent = (JLabel) (e.getSource());
        if (lblEvent.getIcon() == Images.BLOQUEADO_IMAGE) {}
        else if (lblEvent.getBorder() != bordSelect)
            lblEvent.setBorder(normalBord);
    }

    /* Eventos que vienen incluidos en la interfaz MouseInputListener pero que aun no poseen
        utilidad en este proyecto. */
    @Override
    public void mousePressed(MouseEvent e) {}

    @Override
    public void mouseReleased(MouseEvent e) {}

    @Override
    public void mouseDragged(MouseEvent e) {}

    @Override
    public void mouseMoved(MouseEvent e) {}
}
