package com.gui.frames;

import javax.management.InvalidAttributeValueException;
import javax.naming.InsufficientResourcesException;
import javax.swing.*;
import java.awt.event.*;
import javax.swing.border.Border;
import javax.swing.event.MouseInputListener;

import java.awt.*;

import com.exec.Granja;
import com.gui.Frame;
import com.gui.images.Images;
import com.logic.Opcion;
import com.logic.array.Array;
import com.logic.objetos.Actividad;
import com.logic.objetos.PoseeMateria;
import com.logic.objetos.actividades.*;
import com.logic.objetos.posee_materia.*;
import com.logic.objetos.posee_materia.productos.*;
import com.logic.objetos.posee_materia.seres_vivos.*;

/**
 * La clase <code>Juego</code> se encarga de mostrar toda la interfaz en donde se ejecutara el juego,
 * mostrando las distintas acciones disponibles, las tierras que se posean, entre otras cosas.
 */
public class Juego extends Frame implements MouseInputListener, ItemListener {
    private Array<JMenuItemBag> subMenuFertilizantes;
    private Array<JMenuItemBag> subMenuAlimentos;
    private Array<JMenuItemBag> subMenuAnimales;
    private Array<JMenuItemBag> subMenuPlantas;
    private JRadioButtonMenuItem btnSelect;
    private JScrollPane scrollable;
    private JLabel[][] tierras;
    private JMenuItem btnBarco;
    private JMenuItem btnAgregar;
    private JMenuItem btnTienda;
    private JMenuItem btnBodega;
    private JMenuItem btnCosechar;
    private JMenuItem btnDestazar;
    private JMenuItem btnRecolectar;
    private JMenuItem btnLimpiar;
    private JMenuItem btnQuitar;
    private Container panelTierras;
    private JLabel[] vida;
    private JLabel[] cantOro;
    private JMenuBar bar;
    private boolean isSelecting = false;
    private JMenu[] submenuGrama;
    private Border normalBord;
    private Border bordReady;
    private Border bordSelect;
    private Border bordMouseOver;
    private JLabel selected;
    private JPanel panelOro;
    private JPanel panelVida;
    private JMenu actividades;
    private JMenu fertilizantes;
    private JMenu alimentos;
    private JMenu comercio;
    private JMenu optTierras;
    private Tiega tiendaBodega;
    private Color menuItemOriginalColor;
    private int widthPanel = 550;
    private int heightPanel = 550;
    private int rowPanel = 5;
    private int colPanel = 5;
    private int numTierrasCompradas = 0;

    /**
     * Modifica el tamaño del array de JLabels', pasa los JLabels' al nuevo array,
     * y modifica las medidas para que se mantengan las medidas de los JLabels'.
     */
    private void resizeTierras() {
        JLabel[][] arrAux = new JLabel[this.rowPanel + 1][this.colPanel + 1];
        for (int m = 0; m < this.rowPanel; m++) {
            for (int n = 0; n < this.colPanel; n++) {
                arrAux[m][n] = tierras[m][n];
            }
        }

        this.tierras = arrAux;
        this.widthPanel += 110;
        this.heightPanel += 110;
        this.rowPanel++;
        this.colPanel++;

        this.drawTierras();
    }

    /**
     * Vacia el panel que en donde se muestran los suelos lo revalida y repinta para
     * que se muestre instantaneamente el cambio, cambia el tamaño del panel para
     * que se mantenga el tamaño designado para las celdas, se iguala la cantidad de
     * suelos que hay guardados en la granja y toma sus imagenes y la añade al panel.
     */
    private void drawTierras() {
        panelTierras.removeAll();
        panelTierras.setSize(widthPanel, heightPanel);
        panelTierras.setLayout(new GridLayout(rowPanel, colPanel, 0, 0));

        for (int m = 0; m < this.rowPanel; m++) {
            for (int n = 0; n < this.colPanel; n++) {
                ImageIcon icGranja = Granja.getJParcela(m, n);
                ImageIcon icBloq = Images.BLOQUEADO_IMAGE;

                if (tierras[m][n] == null || (icGranja != icBloq
                                    && tierras[m][n].getIcon() == icBloq)) {
                    tierras[m][n] = new JLabel(icGranja);
                    tierras[m][n].setOpaque(true);
                    tierras[m][n].setFont(new Font("JetBrainsMono Nerd Font Mono", 1, 15));
                    tierras[m][n].setForeground(Color.WHITE);
                    tierras[m][n].setBorder(normalBord);
                    tierras[m][n].addMouseListener(this);
                    tierras[m][n].addMouseMotionListener(this);
                    tierras[m][n].setVerticalAlignment(JLabel.CENTER);
                    tierras[m][n].setHorizontalAlignment(JLabel.CENTER);
                } this.panelTierras.add(tierras[m][n]);
            }
        }
        
        panelTierras.revalidate();
        panelTierras.repaint();
    }
    
    /**
     * Se inicializa todos los objetos que se han declarado, dejandolos listos para su uso posterior
     * con la preparacion necesaria.
     */
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

        this.comercio = new JMenu("Compra/Vender");
        this.bar.add(this.comercio);

        this.bar.add(new JSeparator(JSeparator.VERTICAL));

        this.btnAgregar = new JMenuItem("Comprar tierra");
        this.btnAgregar.addActionListener(this);
        this.comercio.add(this.btnAgregar);

        this.btnTienda = new JMenuItem("Abrir Mercado");
        this.btnTienda.addActionListener(this);
        this.comercio.add(this.btnTienda);

        this.btnBodega = new JMenuItem("Abrir Bodega");
        this.btnBodega.addActionListener(this);
        this.comercio.add(this.btnBodega);

        this.actividades = new JMenu("Realizar");
        this.actividades.setEnabled(false);
        this.actividades.setOpaque(true);
        this.bar.add(this.actividades);

        this.submenuGrama = new JMenu[2];
        this.submenuGrama[0] = new JMenu("Siembra");
        this.submenuGrama[1] = new JMenu("Crianza");
        this.submenuGrama[0].setEnabled(false);
        this.submenuGrama[1].setEnabled(false);
        this.actividades.add(this.submenuGrama[0]);
        this.actividades.add(this.submenuGrama[1]);

        this.btnBarco = new JMenuItem("Pescar");
        this.btnBarco.setEnabled(false);
        this.btnBarco.addActionListener(this);
        this.menuItemOriginalColor = this.btnBarco.getBackground();
        this.actividades.add(this.btnBarco);

        /* Creacion del menu que maneje alimentacion, fertilizacion, matanza y demas */
        this.optTierras = new JMenu("Cuidados");
        this.optTierras.setEnabled(false);
        this.bar.add(this.optTierras);
        
        this.bar.add(new JSeparator(JSeparator.VERTICAL));

        this.btnSelect = new JRadioButtonMenuItem("Seleccionar tierras", false);
        this.btnSelect.addItemListener(this);
        this.bar.add(this.btnSelect);

        this.fertilizantes = new JMenu("Fertilizar");
        this.fertilizantes.setEnabled(false);
        this.optTierras.add(this.fertilizantes);

        this.alimentos = new JMenu("Alimentar");
        this.alimentos.setEnabled(false);
        this.optTierras.add(this.alimentos);

        this.optTierras.add(new JSeparator());

        this.btnQuitar = new JMenuItem("Quitar");
        this.btnQuitar.setEnabled(false);
        this.btnQuitar.addActionListener(this);
        this.optTierras.add(this.btnQuitar);

        this.optTierras.add(new JSeparator());

        this.btnCosechar = new JMenuItem("Cosechar");
        this.btnCosechar.setEnabled(false);
        this.btnCosechar.addActionListener(this);
        this.optTierras.add(this.btnCosechar);

        this.btnLimpiar = new JMenuItem("Limpiar");
        this.btnLimpiar.setEnabled(false);
        this.btnLimpiar.addActionListener(this);
        this.optTierras.add(this.btnLimpiar);

        this.btnDestazar = new JMenuItem("Destazar");
        this.btnDestazar.setEnabled(false);
        this.btnDestazar.addActionListener(this);
        this.optTierras.add(this.btnDestazar);

        this.btnRecolectar = new JMenuItem("Recolectar");
        this.btnRecolectar.setEnabled(false);
        this.btnRecolectar.addActionListener(this);
        this.optTierras.add(this.btnRecolectar);

        /* Creacion de una especie de barra para mostrar la vida del granjero y el oro del mismo. */
        this.panelVida = new JPanel(new FlowLayout(FlowLayout.LEFT, 5, 0));
        this.panelVida.setAlignmentX(JPanel.LEFT_ALIGNMENT);
        this.panelVida.setAlignmentY(JPanel.BOTTOM_ALIGNMENT);
        this.panelVida.setPreferredSize(new Dimension(620, 35));
        window.add(this.panelVida);

        /* Creacion de labels para la cantidad de vida. */
        this.vida = new JLabel[17];
        Dimension oroDim = new Dimension(35, 35);
        Dimension heartDim = new Dimension(20, 20);
        
        this.vida[0] = new JLabel(Images.CABEZA_IMAGE);
        this.vida[0].setOpaque(true);
        this.vida[0].setPreferredSize(oroDim);
        this.panelVida.add(this.vida[0]);

        for (int x = 1; x < 16; x++) {
            this.vida[x] = new JLabel(Images.CORAZON_IMAGE);
            this.vida[x].setOpaque(true);
            this.vida[x].setPreferredSize(heartDim);
            this.panelVida.add(this.vida[x]);
        }
        this.vida[16] = new JLabel("+ 5");
        this.vida[16].setVerticalTextPosition(JLabel.CENTER);
        this.vida[16].setFont(new Font("JetBrainsMono Nerd Font Mono", 1, 14));
        this.vida[16].setPreferredSize(oroDim);
        this.panelVida.add(this.vida[16]);

        this.panelOro = new JPanel(new FlowLayout(FlowLayout.RIGHT, 0, 0));
        this.panelOro.setPreferredSize(new Dimension(135, 35));
        this.panelOro.setAlignmentX(JPanel.RIGHT_ALIGNMENT);
        this.panelVida.add(this.panelOro);

        this.cantOro = new JLabel[2];
        this.cantOro[0] = new JLabel("20");
        this.cantOro[0].setVerticalTextPosition(JLabel.CENTER);
        this.cantOro[0].setHorizontalTextPosition(JLabel.RIGHT);
        this.cantOro[0].setPreferredSize(oroDim);
        this.cantOro[0].setFont(new Font("JetBrainsMono Nerd Font Mono", 1, 14));
        this.panelOro.add(this.cantOro[0]);

        this.cantOro[1] = new JLabel(Images.ORO_IMAGE);
        this.cantOro[1].setOpaque(true);
        this.cantOro[1].setPreferredSize(oroDim);
        this.panelOro.add(this.cantOro[1]);

        /* Creacion de panel de Tierras, llenado de Tierras y los bordes que se utilizaran
            en las Tierras */
        this.panelTierras = new JPanel();
        normalBord = BorderFactory.createLineBorder(Color.WHITE, 3, false);
        bordReady = BorderFactory.createLineBorder(Color.BLUE, 3, false);
        bordSelect = BorderFactory.createLineBorder(Color.ORANGE, 3, false);
        bordMouseOver = BorderFactory.createLineBorder(Color.LIGHT_GRAY, 3, false);

        Granja.iniciarParcela();
        this.tierras = new JLabel[5][5];
        this.drawTierras();

        /* JScrollPane que contendra el panel de Tierras para que no se redimensionen las Tierras */
        this.scrollable = new JScrollPane(panelTierras);
        this.scrollable.setSize(widthPanel, heightPanel);
        window.add(scrollable);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // Comprar parcelas
        if (e.getSource() == btnAgregar) {
            this.numTierrasCompradas++;
            if (Granja.bob.isEnough(Granja.getParcelas()[0][0].getPrecio())) {
                if (Granja.newParcela()) {
                    if (rowPanel == 5)
                        this.scrollable.setSize(widthPanel + 48, heightPanel + 48);
                        this.scrollable.setPreferredSize(new Dimension(widthPanel + 48, heightPanel + 48));
                    this.resizeTierras();
                } else {
                    this.drawTierras();
                }
            } else {
                JOptionPane.showMessageDialog(this, "No se pudo completar la compra, por favor, regrese, "
                            + "cuando tenga suficiente oro, para comprar mas tierras.", "Oro Insuficiente",
                            JOptionPane.ERROR_MESSAGE);
            }
        }
        // Abrir Tienda
        if (e.getSource() == btnTienda) {
            if (tiendaBodega != null) {
                tiendaBodega.dispose();
                tiendaBodega = null;
            }
            tiendaBodega = new Tiega(Tiega.TIENDA);
            tiendaBodega.seeIt();
        }
        // Abrir bodega
        if (e.getSource() == btnBodega) {
            if (tiendaBodega != null) {
                tiendaBodega.dispose();
                tiendaBodega = null;
            }
            tiendaBodega = new Tiega(Tiega.BODEGA);
            tiendaBodega.seeIt();
        }
        // Empezar a pescar
        if (e.getSource() == btnBarco) {
            for (int m = 0; m < tierras.length; m++) {
                for (int n = 0; n < tierras[m].length; n++) {
                    if (tierras[m][n].getBorder() == bordSelect) {
                        Suelo suelo = Granja.getParcelas()[m][n];
                        try {
                            suelo.setActividad(new Pescar((Barco) (Granja.bob.getBodega().pop(new Barco())), suelo));
                        } catch (InvalidAttributeValueException exec) {
                            JOptionPane.showMessageDialog(this, "No posees mas barcos, no podras pescar hasta "
                                        + "que compres mas.", "Error 404", JOptionPane.ERROR_MESSAGE);
                            break;
                        }
                        tierras[m][n].setBorder(normalBord);
                    }
                }
            } this.btnSelect.setSelected(false);
            this.refreshImages();
        }
        // Quita los barcos que hayan
        if (e.getSource() == btnQuitar) {
            for (int m = 0; m < tierras.length; m++) {
                for (int n = 0; n < tierras[m].length; n++) {
                    if (tierras[m][n].getBorder() == bordSelect) {
                        Suelo suelo = Granja.getParcelas()[m][n];
                        try {
                            suelo.getActividad().terminarActividad(Granja.bob);
                        } catch (InsufficientResourcesException exec) {
                            JOptionPane.showMessageDialog(this, "Este mensaje no aparecera"
                                        + ", espero", "404", JOptionPane.ERROR_MESSAGE);
                            break;
                        }
                        tierras[m][n].setBorder(normalBord);
                    }
                }
            } this.btnSelect.setSelected(false);
            this.refreshImages();
        }
        // Cosecha las plantas, obtiene sus productos y se los da al granjero
        if (e.getSource() == btnCosechar) {
            for (int m = 0; m < tierras.length; m++) {
                for (int n = 0; n < tierras[m].length; n++) {
                    if (tierras[m][n].getBorder() == bordSelect) {
                        Suelo suelo = Granja.getParcelas()[m][n];
                        try {
                            suelo.getActividad().terminarActividad(Granja.bob);
                        } catch (InsufficientResourcesException exec) {
                            JOptionPane.showMessageDialog(this, "No posees suficiente oro."
                                        + " Se pudrio antes de que la cosecharas.", "404",
                                        JOptionPane.ERROR_MESSAGE);
                            break;
                        }
                        tierras[m][n].setBorder(normalBord);
                    }
                }
            } this.btnSelect.setSelected(false);
            this.refreshImages();
            this.refreshTexts();
        }
        // Limpia los terrenos que esten con cuerpos muertos o plantas podridads
        if (e.getSource() == btnLimpiar) {
            for (int m = 0; m < tierras.length; m++) {
                for (int n = 0; n < tierras[m].length; n++) {
                    if (tierras[m][n].getBorder() == bordSelect) {
                        Suelo suelo = Granja.getParcelas()[m][n];
                        try {
                            suelo.getActividad().terminarActividad(Granja.bob);
                        } catch (InsufficientResourcesException exec) {
                            JOptionPane.showMessageDialog(this, "No posees suficiente oro."
                                        + "\nNo podras usar la tierra hasta que la limpies.",
                                        "Error 404, oro not Found", JOptionPane.ERROR_MESSAGE);
                            break;
                        }
                        tierras[m][n].setBorder(normalBord);
                    }
                }
            } this.btnSelect.setSelected(false);
            this.refreshImages();
            this.refreshTexts();
        }
        // Destaza a los animales destazables y obtiene sus productos.
        if (e.getSource() == btnDestazar) {
            for (int m = 0; m < tierras.length; m++) {
                for (int n = 0; n < tierras[m].length; n++) {
                    if (tierras[m][n].getBorder() == bordSelect) {
                        Suelo suelo = Granja.getParcelas()[m][n];
                        try {
                            suelo.getActividad().terminarActividad(Granja.bob);
                        } catch (InsufficientResourcesException exec) {
                            JOptionPane.showMessageDialog(this, "No posees suficiente oro."
                                        + "\nAlgun animal murio antes de que lo mataras.",
                                        "Error 404, oro not Found", JOptionPane.ERROR_MESSAGE);
                            break;
                        }
                        tierras[m][n].setBorder(normalBord);
                    }
                }
            } this.btnSelect.setSelected(false);
            this.refreshImages();
            this.refreshTexts();
        }
        // Recolecta los productos que genero el animal.
        if (e.getSource() == btnRecolectar) {
            for (int m = 0; m < tierras.length; m++) {
                for (int n = 0; n < tierras[m].length; n++) {
                    if (tierras[m][n].getBorder() == bordSelect) {
                        Suelo suelo = Granja.getParcelas()[m][n];
                        try {
                            suelo.getActividad().terminarActividad(Granja.bob);
                        } catch (InsufficientResourcesException exec) {
                            JOptionPane.showMessageDialog(this, "No posees suficiente oro."
                                        + "\nAlgun animal murio antes de que recolectaras sus productos.",
                                        "Error 404, oro not Found", JOptionPane.ERROR_MESSAGE);
                            break;
                        }
                        tierras[m][n].setBorder(normalBord);
                    }
                }
            } this.btnSelect.setSelected(false);
            this.refreshImages();
        }
        // Todas las acciones que requieren de escoger un producto, animal o planta.
        if (e.getSource() instanceof JMenuItemBag) {
            JMenuItemBag bag = (JMenuItemBag) (e.getSource());
            Array<JLabel> arrLbl = new Array<JLabel>();
            Array<Suelo> arrAux = new Array<Suelo>();
            for (int m = 0; m < tierras.length; m++) {
                for (int n = 0; n < tierras[m].length; n++) {
                    if (tierras[m][n].getBorder() == bordSelect) {
                        arrLbl.add(this.tierras[m][n]);
                        arrAux.add(Granja.getParcelas()[m][n]);
                        tierras[m][n].setBorder(normalBord);
                    }
                }
            } this.btnSelect.setSelected(false);

            // Empezar a plantar
            if (subMenuPlantas.contains(bag)) {
                Planta pl = (Planta) (bag.getItem());
                for (int m = 0; m < arrAux.length(); m++) {
                    try {
                        arrAux.get(m).setActividad(new Sembrar(Granja.bob.getSemillas().pop(pl),
                                    arrAux.get(m)));
                        arrLbl.get(m).setText(pl.getNombre());
                        arrLbl.get(m).setVerticalTextPosition(JLabel.CENTER);
                        arrLbl.get(m).setHorizontalTextPosition(JLabel.CENTER);
                    } catch (InvalidAttributeValueException exc) {
                        JOptionPane.showMessageDialog(this, "Ya no tienes mas semillas del mismo "
                                    + "tipo.", "Error 404", JOptionPane.ERROR_MESSAGE);
                        break;
                    }
                }
                this.refreshImages();
            }
            // Empezar a criar animales
            else if (subMenuAnimales.contains(bag)) {
                Animal an = (Animal) (bag.getItem());
                for (int m = 0; m < arrAux.length(); m++) {
                    try {
                        arrAux.get(m).setActividad(new Crianza(Granja.bob.getCrias().pop(an),
                                    arrAux.get(m)));
                        arrLbl.get(m).setText(an.getNombre());
                        arrLbl.get(m).setVerticalTextPosition(JLabel.CENTER);
                        arrLbl.get(m).setHorizontalTextPosition(JLabel.CENTER);
                    } catch (InvalidAttributeValueException exc) {
                        JOptionPane.showMessageDialog(this, "Ya no tienes mas crias del mismo "
                                    + "animal.", "Error 404", JOptionPane.ERROR_MESSAGE);
                        break;
                    }
                }
                this.refreshImages();
            }
            // Alimentar animales
            else if (subMenuAlimentos.contains(bag)) {
                Alimento al = (Alimento) (bag.getItem());
                for (int m = 0; m < arrAux.length(); m++) {
                    Animal an = (Animal) (arrAux.get(m).getActividad().getSer());
                    try {
                        if (al.isParaOmnivoro() && an.isHerbivoro())
                            JOptionPane.showMessageDialog(this, "Cometes un crimen contra la naturaleza. "
                                        + "El animal es herbivoro, no omnivoro.", "Error", JOptionPane.ERROR_MESSAGE);
                        else if (al.isParaHerbivoro() && an.isOmnivoro())
                            JOptionPane.showMessageDialog(this, "En el mundo real puedes hacerlo pero "
                                        + "aqui tenemos reglas.", "Error", JOptionPane.ERROR_MESSAGE);
                        else
                            an.alimentarse(Granja.bob.getBodega().pop(al));
                    } catch (InvalidAttributeValueException exc) {
                        JOptionPane.showMessageDialog(this, "Ya no tienes mas alimento del mismo tipo "
                                    + "tendras que comprar mas.", "Error 404", JOptionPane.ERROR_MESSAGE);
                        break;
                    }
                }
            }
            // Fertilizar plantas
            else if (subMenuFertilizantes.contains(bag)) {
                Fertilizante fert = (Fertilizante) (bag.getItem());
                for (int m = 0; m < arrAux.length(); m++) {
                    Planta pl = (Planta) (arrAux.get(m).getActividad().getSer());
                    try {
                        pl.alimentarse(Granja.bob.getBodega().pop(fert));
                    } catch (InvalidAttributeValueException exc) {
                        JOptionPane.showMessageDialog(this, "El fertilizante se ha acabado, corre al "
                                    + "mercado, la cosecha se acerca", "Error 404", JOptionPane.ERROR_MESSAGE);
                        break;
                    }
                }
            }
        }
    }

    /**
     * El metodo buscara que imagenes ya cambiaron, si se detecta que una imagen es diferente a la
     * que esta actualmente esta se cambiara y solicitara volver a pintar el jlabel.
     */
    public void refreshImages() {
        boolean repaintar = false;
        for (int m = 0; m < rowPanel; m++) {
            for (int n = 0; n < colPanel; n++) {
                if (tierras[m][n].getIcon() != Granja.getJParcela(m, n)) {
                    tierras[m][n].setIcon(Granja.getJParcela(m, n));
                    repaintar = true;
                }
            }
        } if (repaintar) {
            this.revalidate();
            this.repaint();
        }
    }

    /**
     * el metodo se encargara de modificar los JMenuItems que representan los objetos que se pueden
     * plantar, criar, usar en las tierras, asi se mostrara solo lo que se tenga en posesion.
     */
    public void refreshActions() {
        int index = 0;
        String name = "";
        PoseeMateria item = null;
        boolean agregar = true;
        boolean error = true;
        this.btnBarco.removeAll();
        this.submenuGrama[0].removeAll();
        this.submenuGrama[1].removeAll();
        this.fertilizantes.removeAll();
        this.alimentos.removeAll();
        this.subMenuFertilizantes = new Array<JMenuItemBag>();
        this.subMenuAlimentos = new Array<JMenuItemBag>();
        this.subMenuAnimales = new Array<JMenuItemBag>();
        this.subMenuPlantas = new Array<JMenuItemBag>();

        for (int x = 0; x < Granja.bob.getBodega().length(); x++) {
            item = Granja.bob.getBodega().get(x);
            name = (item != null) ? item.getNombre() : null;

            if (Granja.bob.getBodega().get(x) instanceof Barco) {
                this.btnBarco.setBackground(this.menuItemOriginalColor);
                this.btnBarco.setForeground(Color.BLACK);
                this.btnBarco.setIcon(null);
                agregar = false;
                error = false;
            } else if (!(Granja.bob.getBodega().get(x) instanceof Fertilizante))
                agregar = false;
            else {
                if (name != null) {
                    for (int y = 0; y < this.subMenuFertilizantes.length(); y++) {
                        if (this.subMenuFertilizantes.get(y).getText().equals(name)) {
                            agregar = false;
                            break;
                        } else
                            agregar = true;
                    }
                }
            } if (agregar) {
                this.subMenuFertilizantes.add(new JMenuItemBag(name, item));
                this.subMenuFertilizantes.get(index).addActionListener(this);
                this.fertilizantes.add(this.subMenuFertilizantes.get(index));
                index++;
            }
            if (error) {
                this.btnBarco.setBackground(new Color(163, 184, 204));
                this.btnBarco.setForeground(Color.RED);
                this.btnBarco.setIcon(Images.ERROR_IMAGE);
            }
        }
        
        index = 0;
        for (int x = 0; x < Granja.bob.getBodega().length(); x++) {
            item = Granja.bob.getBodega().get(x);
            name = (item != null) ? item.getNombre() : null;
            if (!(Granja.bob.getBodega().get(x) instanceof Alimento))
                agregar = false;
            else {
                if (name != null) {
                    for (int y = 0; y < this.subMenuAlimentos.length(); y++) {
                        if (this.subMenuAlimentos.get(y).getText().equals(name)) {
                            agregar = false;
                            break;
                        } else
                            agregar = true;
                    }
                }
            } if (agregar) {
                this.subMenuAlimentos.add(new JMenuItemBag(name, item));
                this.subMenuAlimentos.get(index).addActionListener(this);
                this.alimentos.add(this.subMenuAlimentos.get(index));
                index++;
            }
        }
        
        index = 0;
        for (int x = 0; x < Granja.bob.getSemillas().length(); x++) {
            item = Granja.bob.getSemillas().get(x);
            if (item == null) continue;
            name = item.getNombre();
            for (int y = 0; y < this.subMenuPlantas.length(); y++) {
                if (this.subMenuPlantas.get(y).getText().equals(name)) {
                    agregar = false;
                    break;
                } else
                    agregar = true;
            } if (agregar) {
                this.subMenuPlantas.add(new JMenuItemBag(name, item));
                this.subMenuPlantas.get(index).addActionListener(this);
                this.submenuGrama[0].add(this.subMenuPlantas.get(index));
                index++;
            }
        }
        
        index = 0;
        for (int x = 0; x < Granja.bob.getCrias().length(); x++) {
            item = Granja.bob.getCrias().get(x);
            if (item == null) continue;
            name = item.getNombre();
            for (int y = 0; y < this.subMenuAnimales.length(); y++) {
                if (this.subMenuAnimales.get(y).getText().equals(name)){
                    agregar = false;
                    break;
                } else
                    agregar = true;                
            } if (agregar) {
                this.subMenuAnimales.add(new JMenuItemBag(name, item));
                this.subMenuAnimales.get(index).addActionListener(this);
                this.submenuGrama[1].add(this.subMenuAnimales.get(index));
                index++;
            }
        }
        
        this.btnBarco.revalidate();
        this.btnBarco.repaint();
        this.submenuGrama[0].revalidate();
        this.submenuGrama[0].repaint();
        this.submenuGrama[1].revalidate();
        this.submenuGrama[1].repaint();
        this.fertilizantes.revalidate();
        this.fertilizantes.repaint();
        this.alimentos.revalidate();
        this.alimentos.repaint();

        if (error) {
            this.btnBarco.setEnabled(false);
        } if (this.subMenuFertilizantes.length() != 0) {
            this.fertilizantes.setEnabled(true);
        } else {
            this.fertilizantes.setEnabled(false);
        } if (this.subMenuAlimentos.length() != 0) {
            this.alimentos.setEnabled(true);
        } else {
            this.alimentos.setEnabled(false);
        } if (this.subMenuPlantas.length() != 0) {
            this.submenuGrama[0].setEnabled(true);
        } else {
            this.submenuGrama[0].setEnabled(false);
        } if (this.subMenuAnimales.length() != 0) {
            this.submenuGrama[1].setEnabled(true);
        } else {
            this.submenuGrama[1].setEnabled(false);
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
        new Registro().seeIt();
    }

    /**
     * Disminuye la vida del granjero, solo visualmente.
     */
    public void bajoVida() {
        int width = 135;
        if (Granja.bob.getVida() > 15) {
            for (int x = 1; x < 16; x++) {
                this.vida[x].setVisible(true);
            }
            this.vida[16].setText("+ " + (Granja.bob.getVida() - 15));
        } else {
            for (int x = 1; x < Granja.bob.getVida(); x++)
                this.vida[x].setVisible(true);
            for (int x = Granja.bob.getVida(); x < 17; x++) {
                this.vida[x].setVisible(false);
                width += 25;
            } width += 15;
        }
        this.panelOro.setPreferredSize(new Dimension(width, 40));
        this.cantOro[0].setText(String.valueOf(Granja.bob.getOro()));
        if (!Granja.bob.isEnough(Granja.getParcelas()[0][0].getPrecio())) {
            this.btnAgregar.setBackground(new Color(163, 184, 204));
            this.btnAgregar.setForeground(Color.RED);
            this.btnAgregar.setIcon(Images.ERROR_IMAGE);
        } else {
            this.btnAgregar.setBackground(this.menuItemOriginalColor);
            this.btnAgregar.setForeground(Color.BLACK);
            this.btnAgregar.setIcon(null);
        } this.refreshActions();
    }

    @Override
    public void itemStateChanged(ItemEvent e) {
        if (e.getSource() == btnSelect) {
            if(e.getStateChange() == ItemEvent.SELECTED){
                this.btnSelect.setText("Cancelar seleccion ");
            } else {
                this.btnSelect.setText("Seleccionar tierras");
                Array<JLabel> jLAux = new Array<JLabel>();
                for (int m = 0; m < tierras.length; m++) {
                    for (int n = 0; n < tierras[m].length; n++) {
                        if (tierras[m][n].getBorder() == bordSelect) {
                            jLAux.add(tierras[m][n]);
                            tierras[m][n].setBorder(normalBord);
                        }
                    }
                }
        
            }
            this.isSelecting = !this.isSelecting;
        }
    }

    /**
     * Cada vez que un JLabel de Tierras sea clickeado hara que se cambie el borde,
     * se cambiara a un borde que de a entender que esta seleccionado o que se deselecciono,
     * si esta presionado el boton seleccionar se realizara la misma accion pero no limitado
     * a un solo JLabel.
     */
    @Override
    public void mouseClicked(MouseEvent e) {
        JLabel lblEvent = (JLabel) (e.getSource());
        this.actividades.setEnabled(true);
        this.optTierras.setEnabled(true);
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
        } refreshButtons(sonIguales);
    }

    public void refreshButtons(boolean sonIguales) {
        JLabel anterior = null;
        Suelo sueloAnt = null;
        Suelo[][] suelosR = Granja.getParcelas();
        Opcion optReali = null;
        Actividad act = null;
        boolean salir = false;
        boolean actExist = true;
        for (int m = 0; m < colPanel; m++) {
            for (int n = 0; n < rowPanel; n++) {
                if (tierras[m][n].getBorder() == this.bordSelect) {
                    Actividad act2 = suelosR[m][n].getActividad();
                    if (act != null && act2 != null && actExist) {
                        if (optReali != act2.getOpcion(act)) {
                            optReali = null;
                            actExist = false;
                        }
                    } else if ((anterior != null && sueloAnt != null) &&
                            (anterior.getIcon() != tierras[m][n].getIcon())) {
                        sonIguales = false;
                        anterior = null;
                        sueloAnt = null;
                        actExist = false;
                        salir = true;
                        break;
                    } else {
                        if (anterior == null) {
                            if (suelosR[m][n].getActividad() != null)
                                optReali = suelosR[m][n].getActividad().getOpcion();
                        }
                        anterior = tierras[m][n];
                        sueloAnt = suelosR[m][n];
                        sonIguales = true;
                    }
                    act = sueloAnt.getActividad();
                }
            }
            if (salir)
                break;
        }

        if ((!this.isSelecting && this.selected == null) || (anterior == null && actExist))
            sonIguales = false;
        if (sonIguales) {
            if (anterior.getIcon() == Images.GRAMA_IMAGE) {
                this.submenuGrama[0].setEnabled(true);
                this.submenuGrama[1].setEnabled(true);
                this.btnBarco.setEnabled(false);
                this.optTierras.setEnabled(false);
            } else if (anterior.getIcon() == Images.AGUA_IMAGE) {
                this.submenuGrama[0].setEnabled(false);
                this.submenuGrama[1].setEnabled(false);
                this.btnBarco.setEnabled(true);
                this.optTierras.setEnabled(false);
            } else if (actExist) {
                this.actividades.setEnabled(false);
                if (optReali == Opcion.FERTILIZAR) {
                    this.fertilizantes.setEnabled(true);
                    this.btnQuitar.setEnabled(false);
                    this.alimentos.setEnabled(false);
                    this.btnCosechar.setEnabled(false);
                    this.btnLimpiar.setEnabled(false);
                    this.btnDestazar.setEnabled(false);
                    this.btnRecolectar.setEnabled(false);
                } else if (optReali == Opcion.ALIMENTAR) {
                    this.fertilizantes.setEnabled(false);
                    this.btnQuitar.setEnabled(false);
                    this.alimentos.setEnabled(true);
                    this.btnCosechar.setEnabled(false);
                    this.btnLimpiar.setEnabled(false);
                    this.btnDestazar.setEnabled(false);
                    this.btnRecolectar.setEnabled(false);
                } else if (optReali == Opcion.QUITAR) {
                    this.fertilizantes.setEnabled(false);
                    this.alimentos.setEnabled(false);
                    this.btnQuitar.setEnabled(true);
                    this.btnCosechar.setEnabled(false);
                    this.btnLimpiar.setEnabled(false);
                    this.btnDestazar.setEnabled(false);
                    this.btnRecolectar.setEnabled(false);
                } else if (optReali == Opcion.COSECHAR) {
                    this.fertilizantes.setEnabled(false);
                    this.alimentos.setEnabled(false);
                    this.btnQuitar.setEnabled(false);
                    this.btnCosechar.setEnabled(true);
                    this.btnLimpiar.setEnabled(false);
                    this.btnDestazar.setEnabled(false);
                    this.btnRecolectar.setEnabled(false);
                } else if (optReali == Opcion.LIMPIAR) {
                    this.fertilizantes.setEnabled(false);
                    this.alimentos.setEnabled(false);
                    this.btnQuitar.setEnabled(false);
                    this.btnCosechar.setEnabled(false);
                    this.btnLimpiar.setEnabled(true);
                    this.btnDestazar.setEnabled(false);
                    this.btnRecolectar.setEnabled(false);
                } else if (optReali == Opcion.DESTAZAR) {
                    this.fertilizantes.setEnabled(false);
                    this.alimentos.setEnabled(false);
                    this.btnQuitar.setEnabled(false);
                    this.btnCosechar.setEnabled(false);
                    this.btnLimpiar.setEnabled(false);
                    this.btnDestazar.setEnabled(true);
                    this.btnRecolectar.setEnabled(false);
                } else if (optReali == Opcion.RECOLECTAR) {
                    this.fertilizantes.setEnabled(false);
                    this.alimentos.setEnabled(false);
                    this.btnQuitar.setEnabled(false);
                    this.btnCosechar.setEnabled(false);
                    this.btnLimpiar.setEnabled(false);
                    this.btnDestazar.setEnabled(false);
                    this.btnRecolectar.setEnabled(true);
                } else {
                    this.optTierras.setEnabled(false);
                }
            }
        } else {
            this.actividades.setEnabled(false);
            this.optTierras.setEnabled(false);
        }
    } 
    
    /**
     * Cuando el mouse entre en el area del JLabel de una tierra este cambiara de color,
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
     * Detecta cuando el mouse acabe de salir del area del JLabel tierra para devolver el
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

    private class JMenuItemBag extends JMenuItem {
        private PoseeMateria item;

        public JMenuItemBag(String arg0, PoseeMateria item) {
            super(arg0);
            this.item = item;
        }

        public PoseeMateria getItem() {
            return this.item;
        }
    }

    public void isReadyBorder(int m, int n) {
        if (this.tierras[m][n].getBorder() != this.bordReady
                    && this.tierras[m][n].getBorder() != this.bordSelect) {
            this.tierras[m][n].setBorder(this.bordReady);
        }
    }

    public void isNotReadyBorder(int m, int n) {
        if (this.tierras[m][n].getBorder() == this.bordReady) {
            this.tierras[m][n].setBorder(this.normalBord);
        }
    }

    public int getTierrasCompradas() {
        return this.numTierrasCompradas;
    }

    public void refreshTexts() {
        for (JLabel[] lbls : tierras) {
            for (JLabel lbl : lbls) {
                if (lbl.getText() != null && !lbl.getText().isBlank()) {
                    if (lbl.getIcon() == Images.GRAMA_IMAGE
                            || lbl.getIcon() == Images.AGUA_IMAGE) {
                        lbl.setText("");
                    }
                }
            }
        }
    }
}
