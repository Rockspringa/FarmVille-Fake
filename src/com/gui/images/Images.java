package com.gui.images;

import javax.swing.ImageIcon;
import java.awt.Image;

public class Images {
    private static final int width = 105;
    private static final int height = 98;

    private static final ImageIcon GRAMA_ICON = new ImageIcon("src\\com\\gui\\images\\Grama.png");
    private static final Image GRAMA_IM_IC = GRAMA_ICON.getImage().getScaledInstance(width, height, Image.SCALE_SMOOTH);
    public static final ImageIcon GRAMA_IMAGE = new ImageIcon(GRAMA_IM_IC);

    private static final ImageIcon AGUA_ICON = new ImageIcon("src\\com\\gui\\images\\Agua.png");
    private static final Image AGUA_IM_IC = AGUA_ICON.getImage().getScaledInstance(width, height, Image.SCALE_SMOOTH);
    public static final ImageIcon AGUA_IMAGE = new ImageIcon(AGUA_IM_IC);

    private static final ImageIcon DESIERTO_ICON = new ImageIcon("src\\com\\gui\\images\\Desierto.png");
    private static final Image DESIERTO_IM_IC = DESIERTO_ICON.getImage().getScaledInstance(width, height, Image.SCALE_SMOOTH);
    public static final ImageIcon DESIERTO_IMAGE = new ImageIcon(DESIERTO_IM_IC);

    
}
