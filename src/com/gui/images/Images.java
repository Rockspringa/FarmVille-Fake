package com.gui.images;

import javax.swing.ImageIcon;
import java.awt.Image;

public class Images {
    private static final int width = 94;
    private static final int height = 94;

    private static final ImageIcon GRANJERO_ICON = new ImageIcon("src\\com\\gui\\images\\Granjero.png");
    private static final Image GRANJERO_IM_IC = GRANJERO_ICON.getImage().getScaledInstance(300, 300, Image.SCALE_SMOOTH);
    public static final ImageIcon GRANJERO_IMAGE = new ImageIcon(GRANJERO_IM_IC);

    private static final ImageIcon GRAMA_ICON = new ImageIcon("src\\com\\gui\\images\\Grama.png");
    private static final Image GRAMA_IM_IC = GRAMA_ICON.getImage().getScaledInstance(width, height, Image.SCALE_SMOOTH);
    public static final ImageIcon GRAMA_IMAGE = new ImageIcon(GRAMA_IM_IC);

    private static final ImageIcon GRAMA_VALLA_ICON = new ImageIcon("src\\com\\gui\\images\\Grama_Valla.png");
    private static final Image GRAMA_VALLA_IM_IC = GRAMA_VALLA_ICON.getImage().getScaledInstance(width, height, Image.SCALE_SMOOTH);
    public static final ImageIcon GRAMA_VALLA_IMAGE = new ImageIcon(GRAMA_VALLA_IM_IC);

    private static final ImageIcon AGUA_ICON = new ImageIcon("src\\com\\gui\\images\\Agua.png");
    private static final Image AGUA_IM_IC = AGUA_ICON.getImage().getScaledInstance(width, height, Image.SCALE_SMOOTH);
    public static final ImageIcon AGUA_IMAGE = new ImageIcon(AGUA_IM_IC);

    private static final ImageIcon DESIERTO_ICON = new ImageIcon("src\\com\\gui\\images\\Desierto.png");
    private static final Image DESIERTO_IM_IC = DESIERTO_ICON.getImage().getScaledInstance(width, height, Image.SCALE_SMOOTH);
    public static final ImageIcon DESIERTO_IMAGE = new ImageIcon(DESIERTO_IM_IC);

    private static final ImageIcon DESIERTO_CRANEO_ICON = new ImageIcon("src\\com\\gui\\images\\Desierto_Craneo.png");
    private static final Image DESIERTO_CRANEO_IM_IC = DESIERTO_CRANEO_ICON.getImage().getScaledInstance(width, height, Image.SCALE_SMOOTH);
    public static final ImageIcon DESIERTO_CRANEO_IMAGE = new ImageIcon(DESIERTO_CRANEO_IM_IC);

    private static final ImageIcon DESIERTO_CACTUS_ICON = new ImageIcon("src\\com\\gui\\images\\Desierto_Cactus.png");
    private static final Image DESIERTO_CACTUS_IM_IC = DESIERTO_CACTUS_ICON.getImage().getScaledInstance(width, height, Image.SCALE_SMOOTH);
    public static final ImageIcon DESIERTO_CACTUS_IMAGE = new ImageIcon(DESIERTO_CACTUS_IM_IC);

    private static final ImageIcon BLOQUEADO_ICON = new ImageIcon("src\\com\\gui\\images\\Bloqueado.png");
    private static final Image BLOQUEADO_IM_IC = BLOQUEADO_ICON.getImage().getScaledInstance(width, height, Image.SCALE_SMOOTH);
    public static final ImageIcon BLOQUEADO_IMAGE = new ImageIcon(BLOQUEADO_IM_IC);
}
