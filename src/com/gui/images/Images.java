package com.gui.images;

import javax.swing.ImageIcon;
import java.awt.Image;
import java.io.File;

public class Images {
    private static final int widthJuego = 110;
    private static final int heightJuego = 110;

    public static final File MADERAS_FILE = new File("src\\com\\gui\\images\\diseño\\Maderas.png");

    private static final ImageIcon ERROR_ICON = new ImageIcon("src\\com\\gui\\images\\diseño\\Error.png");
    private static final Image ERROR_IM_IC = ERROR_ICON.getImage().getScaledInstance(15, 15, Image.SCALE_SMOOTH);
    public static final ImageIcon ERROR_IMAGE = new ImageIcon(ERROR_IM_IC);

    private static final ImageIcon ERROR_INVERSO_ICON = new ImageIcon("src\\com\\gui\\images\\diseño\\Error_Inverso.png");
    private static final Image ERROR_INVERSO_IM_IC = ERROR_INVERSO_ICON.getImage().getScaledInstance(15, 15, Image.SCALE_SMOOTH);
    public static final ImageIcon ERROR_INVERSO_IMAGE = new ImageIcon(ERROR_INVERSO_IM_IC);

    private static final ImageIcon CAJA_VACIA_ICON = new ImageIcon("src\\com\\gui\\images\\diseño\\Caja_Vacia.png");
    private static final Image CAJA_VACIA_IM_IC = CAJA_VACIA_ICON.getImage().getScaledInstance(widthJuego, heightJuego, Image.SCALE_SMOOTH);
    public static final ImageIcon CAJA_VACIA_IMAGE = new ImageIcon(CAJA_VACIA_IM_IC);

    private static final ImageIcon FLECHA_DER_ICON = new ImageIcon("src\\com\\gui\\images\\diseño\\Flecha_Der.png");
    private static final Image FLECHA_DER_IM_IC = FLECHA_DER_ICON.getImage().getScaledInstance(30, 250, Image.SCALE_SMOOTH);
    public static final ImageIcon FLECHA_DER_IMAGE = new ImageIcon(FLECHA_DER_IM_IC);

    private static final ImageIcon FLECHA_IZQ_ICON = new ImageIcon("src\\com\\gui\\images\\diseño\\Flecha_Izq.png");
    private static final Image FLECHA_IZQ_IM_IC = FLECHA_IZQ_ICON.getImage().getScaledInstance(30, 250, Image.SCALE_SMOOTH);
    public static final ImageIcon FLECHA_IZQ_IMAGE = new ImageIcon(FLECHA_IZQ_IM_IC);

    private static final ImageIcon GRANJERO_ICON = new ImageIcon("src\\com\\gui\\images\\diseño\\Granjero.png");
    private static final Image GRANJERO_IM_IC = GRANJERO_ICON.getImage().getScaledInstance(300, 300, Image.SCALE_SMOOTH);
    public static final ImageIcon GRANJERO_IMAGE = new ImageIcon(GRANJERO_IM_IC);

    private static final ImageIcon CABEZA_ICON = new ImageIcon("src\\com\\gui\\images\\diseño\\Cabeza.png");
    private static final Image CABEZA_IM_IC = CABEZA_ICON.getImage().getScaledInstance(40, 40, Image.SCALE_SMOOTH);
    public static final ImageIcon CABEZA_IMAGE = new ImageIcon(CABEZA_IM_IC);

    private static final ImageIcon CORAZON_ICON = new ImageIcon("src\\com\\gui\\images\\diseño\\Corazon.png");
    private static final Image CORAZON_IM_IC = CORAZON_ICON.getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH);
    public static final ImageIcon CORAZON_IMAGE = new ImageIcon(CORAZON_IM_IC);

    private static final ImageIcon GRAMA_ICON = new ImageIcon("src\\com\\gui\\images\\Grama.png");
    private static final Image GRAMA_IM_IC = GRAMA_ICON.getImage().getScaledInstance(widthJuego, heightJuego, Image.SCALE_SMOOTH);
    public static final ImageIcon GRAMA_IMAGE = new ImageIcon(GRAMA_IM_IC);

    private static final ImageIcon GRAMA_VALLA_ICON = new ImageIcon("src\\com\\gui\\images\\Grama_Valla.png");
    private static final Image GRAMA_VALLA_IM_IC = GRAMA_VALLA_ICON.getImage().getScaledInstance(widthJuego, heightJuego, Image.SCALE_SMOOTH);
    public static final ImageIcon GRAMA_VALLA_IMAGE = new ImageIcon(GRAMA_VALLA_IM_IC);

    private static final ImageIcon AGUA_ICON = new ImageIcon("src\\com\\gui\\images\\Agua.png");
    private static final Image AGUA_IM_IC = AGUA_ICON.getImage().getScaledInstance(widthJuego, heightJuego, Image.SCALE_SMOOTH);
    public static final ImageIcon AGUA_IMAGE = new ImageIcon(AGUA_IM_IC);

    private static final ImageIcon DESIERTO_ICON = new ImageIcon("src\\com\\gui\\images\\Desierto.png");
    private static final Image DESIERTO_IM_IC = DESIERTO_ICON.getImage().getScaledInstance(widthJuego, heightJuego, Image.SCALE_SMOOTH);
    public static final ImageIcon DESIERTO_IMAGE = new ImageIcon(DESIERTO_IM_IC);

    private static final ImageIcon DESIERTO_CRANEO_ICON = new ImageIcon("src\\com\\gui\\images\\Desierto_Craneo.png");
    private static final Image DESIERTO_CRANEO_IM_IC = DESIERTO_CRANEO_ICON.getImage().getScaledInstance(widthJuego, heightJuego, Image.SCALE_SMOOTH);
    public static final ImageIcon DESIERTO_CRANEO_IMAGE = new ImageIcon(DESIERTO_CRANEO_IM_IC);

    private static final ImageIcon DESIERTO_CACTUS_ICON = new ImageIcon("src\\com\\gui\\images\\Desierto_Cactus.png");
    private static final Image DESIERTO_CACTUS_IM_IC = DESIERTO_CACTUS_ICON.getImage().getScaledInstance(widthJuego, heightJuego, Image.SCALE_SMOOTH);
    public static final ImageIcon DESIERTO_CACTUS_IMAGE = new ImageIcon(DESIERTO_CACTUS_IM_IC);

    private static final ImageIcon BLOQUEADO_ICON = new ImageIcon("src\\com\\gui\\images\\Bloqueado.png");
    private static final Image BLOQUEADO_IM_IC = BLOQUEADO_ICON.getImage().getScaledInstance(widthJuego, heightJuego, Image.SCALE_SMOOTH);
    public static final ImageIcon BLOQUEADO_IMAGE = new ImageIcon(BLOQUEADO_IM_IC);

    private static final ImageIcon BARCO_ICON = new ImageIcon("src\\com\\gui\\images\\Barco.png");
    private static final Image BARCO_IM_IC = BARCO_ICON.getImage().getScaledInstance(widthJuego, heightJuego, Image.SCALE_SMOOTH);
    public static final ImageIcon BARCO_IMAGE = new ImageIcon(BARCO_IM_IC);

    private static final ImageIcon BARCO_MAS_ICON = new ImageIcon("src\\com\\gui\\images\\Barco_Mas.png");
    private static final Image BARCO_MAS_IM_IC = BARCO_MAS_ICON.getImage().getScaledInstance(widthJuego, heightJuego, Image.SCALE_SMOOTH);
    public static final ImageIcon BARCO_MAS_IMAGE = new ImageIcon(BARCO_MAS_IM_IC);

    /* Todas las imagenes de plantas */

    private static final ImageIcon CULTIVO_ICON = new ImageIcon("src\\com\\gui\\images\\plantas\\Cultivo.png");
    private static final Image CULTIVO_IM_IC = CULTIVO_ICON.getImage().getScaledInstance(widthJuego, heightJuego, Image.SCALE_SMOOTH);
    public static final ImageIcon CULTIVO_IMAGE = new ImageIcon(CULTIVO_IM_IC);

    private static final ImageIcon CULTIVO_COSECHA_ICON = new ImageIcon("src\\com\\gui\\images\\plantas\\Cultivo_Cosecha.png");
    private static final Image CULTIVO_COSECHA_IM_IC = CULTIVO_COSECHA_ICON.getImage().getScaledInstance(widthJuego, heightJuego, Image.SCALE_SMOOTH);
    public static final ImageIcon CULTIVO_COSECHA_IMAGE = new ImageIcon(CULTIVO_COSECHA_IM_IC);

    private static final ImageIcon CULTIVO_PODRIDO_ICON = new ImageIcon("src\\com\\gui\\images\\plantas\\Cultivo_Podrido.png");
    private static final Image CULTIVO_PODRIDO_IM_IC = CULTIVO_PODRIDO_ICON.getImage().getScaledInstance(widthJuego, heightJuego, Image.SCALE_SMOOTH);
    public static final ImageIcon CULTIVO_PODRIDO_IMAGE = new ImageIcon(CULTIVO_PODRIDO_IM_IC);

    private static final ImageIcon ARBOL_ICON = new ImageIcon("src\\com\\gui\\images\\plantas\\Arbol.png");
    private static final Image ARBOL_IM_IC = ARBOL_ICON.getImage().getScaledInstance(widthJuego, heightJuego, Image.SCALE_SMOOTH);
    public static final ImageIcon ARBOL_IMAGE = new ImageIcon(ARBOL_IM_IC);

    private static final ImageIcon ARBOL_COSECHA_ICON = new ImageIcon("src\\com\\gui\\plantas\\images\\Arbol_Cosecha.png");
    private static final Image ARBOL_COSECHA_IM_IC = ARBOL_COSECHA_ICON.getImage().getScaledInstance(widthJuego, heightJuego, Image.SCALE_SMOOTH);
    public static final ImageIcon ARBOL_COSECHA_IMAGE = new ImageIcon(ARBOL_COSECHA_IM_IC);

    private static final ImageIcon ARBOL_PODRIDO_ICON = new ImageIcon("src\\com\\gui\\plantas\\images\\Arbol_Podrido.png");
    private static final Image ARBOL_PODRIDO_IM_IC = ARBOL_PODRIDO_ICON.getImage().getScaledInstance(widthJuego, heightJuego, Image.SCALE_SMOOTH);
    public static final ImageIcon ARBOL_PODRIDO_IMAGE = new ImageIcon(ARBOL_PODRIDO_IM_IC);

    /* Todas las imagenes de animales */

    private static final ImageIcon DES_PROD_HER_MUERTO_ICON = new ImageIcon("src\\com\\gui\\images\\animales\\DestaProd_Hebivoro_Muerto.png");
    private static final Image DES_PROD_HER_MUERTO_IM_IC = DES_PROD_HER_MUERTO_ICON.getImage().getScaledInstance(widthJuego, heightJuego, Image.SCALE_SMOOTH);
    public static final ImageIcon DES_PROD_HER_MUERTO_IMAGE = new ImageIcon(DES_PROD_HER_MUERTO_IM_IC);

    private static final ImageIcon DES_PROD_HER_ICON = new ImageIcon("src\\com\\gui\\images\\animales\\DestaProd_Hebivoro.png");
    private static final Image DES_PROD_HER_IM_IC = DES_PROD_HER_ICON.getImage().getScaledInstance(widthJuego, heightJuego, Image.SCALE_SMOOTH);
    public static final ImageIcon DES_PROD_HER_IMAGE = new ImageIcon(DES_PROD_HER_IM_IC);

    private static final ImageIcon DES_PROD_OMNI_MUERTO_ICON = new ImageIcon("src\\com\\gui\\images\\animales\\DestaProd_Omnivoro_Muerto.png");
    private static final Image DES_PROD_OMNI_MUERTO_IM_IC = DES_PROD_OMNI_MUERTO_ICON.getImage().getScaledInstance(widthJuego, heightJuego, Image.SCALE_SMOOTH);
    public static final ImageIcon DES_PROD_OMNI_MUERTO_IMAGE = new ImageIcon(DES_PROD_OMNI_MUERTO_IM_IC);

    private static final ImageIcon DES_PROD_OMNI_ICON = new ImageIcon("src\\com\\gui\\images\\animales\\DestaProd_Omnivoro.png");
    private static final Image DES_PROD_OMNI_IM_IC = DES_PROD_OMNI_ICON.getImage().getScaledInstance(widthJuego, heightJuego, Image.SCALE_SMOOTH);
    public static final ImageIcon DES_PROD_OMNI_IMAGE = new ImageIcon(DES_PROD_OMNI_IM_IC);

    private static final ImageIcon DES_HER_MUERTO_ICON = new ImageIcon("src\\com\\gui\\images\\animales\\Destazable_Hebivoro_Muerto.png");
    private static final Image DES_HER_MUERTO_IM_IC = DES_HER_MUERTO_ICON.getImage().getScaledInstance(widthJuego, heightJuego, Image.SCALE_SMOOTH);
    public static final ImageIcon DES_HER_MUERTO_IMAGE = new ImageIcon(DES_HER_MUERTO_IM_IC);

    private static final ImageIcon DES_HER_ICON = new ImageIcon("src\\com\\gui\\images\\animales\\Destazable_Hebivoro.png");
    private static final Image DES_HER_IM_IC = DES_HER_ICON.getImage().getScaledInstance(widthJuego, heightJuego, Image.SCALE_SMOOTH);
    public static final ImageIcon DES_HER_IMAGE = new ImageIcon(DES_HER_IM_IC);

    private static final ImageIcon DES_OMNI_MUERTO_ICON = new ImageIcon("src\\com\\gui\\images\\animales\\Destazable_Omnivoro_Muerto.png");
    private static final Image DES_OMNI_MUERTO_IM_IC = DES_OMNI_MUERTO_ICON.getImage().getScaledInstance(widthJuego, heightJuego, Image.SCALE_SMOOTH);
    public static final ImageIcon DES_OMNI_MUERTO_IMAGE = new ImageIcon(DES_OMNI_MUERTO_IM_IC);

    private static final ImageIcon DES_OMNI_ICON = new ImageIcon("src\\com\\gui\\images\\animales\\Destazable_Omnivoro.png");
    private static final Image DES_OMNI_IM_IC = DES_OMNI_ICON.getImage().getScaledInstance(widthJuego, heightJuego, Image.SCALE_SMOOTH);
    public static final ImageIcon DES_OMNI_IMAGE = new ImageIcon(DES_OMNI_IM_IC);

    private static final ImageIcon PROD_HER_MUERTO_ICON = new ImageIcon("src\\com\\gui\\images\\animales\\Productor_Hebivoro_Muerto.png");
    private static final Image PROD_HER_MUERTO_IM_IC = PROD_HER_MUERTO_ICON.getImage().getScaledInstance(widthJuego, heightJuego, Image.SCALE_SMOOTH);
    public static final ImageIcon PROD_HER_MUERTO_IMAGE = new ImageIcon(PROD_HER_MUERTO_IM_IC);

    private static final ImageIcon PROD_HER_ICON = new ImageIcon("src\\com\\gui\\images\\animales\\Productor_Hebivoro.png");
    private static final Image PROD_HER_IM_IC = PROD_HER_ICON.getImage().getScaledInstance(widthJuego, heightJuego, Image.SCALE_SMOOTH);
    public static final ImageIcon PROD_HER_IMAGE = new ImageIcon(PROD_HER_IM_IC);

    private static final ImageIcon PROD_OMNI_MUERTO_ICON = new ImageIcon("src\\com\\gui\\images\\animales\\Productor_Omnivoro_Muerto.png");
    private static final Image PROD_OMNI_MUERTO_IM_IC = PROD_OMNI_MUERTO_ICON.getImage().getScaledInstance(widthJuego, heightJuego, Image.SCALE_SMOOTH);
    public static final ImageIcon PROD_OMNI_MUERTO_IMAGE = new ImageIcon(PROD_OMNI_MUERTO_IM_IC);

    private static final ImageIcon PROD_OMNI_ICON = new ImageIcon("src\\com\\gui\\images\\animales\\Productor_Omnivoro.png");
    private static final Image PROD_OMNI_IM_IC = PROD_OMNI_ICON.getImage().getScaledInstance(widthJuego, heightJuego, Image.SCALE_SMOOTH);
    public static final ImageIcon PROD_OMNI_IMAGE = new ImageIcon(PROD_OMNI_IM_IC);
    
    private static final ImageIcon GALLINA_MUERTO_ICON = new ImageIcon("src\\com\\gui\\images\\animales\\Gallina_Muerto.png");
    private static final Image GALLINA_MUERTO_IM_IC = GALLINA_MUERTO_ICON.getImage().getScaledInstance(widthJuego, heightJuego, Image.SCALE_SMOOTH);
    public static final ImageIcon GALLINA_MUERTO_IMAGE = new ImageIcon(GALLINA_MUERTO_IM_IC);

    private static final ImageIcon GALLINA_ICON = new ImageIcon("src\\com\\gui\\images\\animales\\Gallina.png");
    private static final Image GALLINA_IM_IC = GALLINA_ICON.getImage().getScaledInstance(widthJuego, heightJuego, Image.SCALE_SMOOTH);
    public static final ImageIcon GALLINA_IMAGE = new ImageIcon(GALLINA_IM_IC);

    private static final ImageIcon VACA_MUERTO_ICON = new ImageIcon("src\\com\\gui\\images\\animales\\Vaca_Muerto.png");
    private static final Image VACA_MUERTO_IM_IC = VACA_MUERTO_ICON.getImage().getScaledInstance(widthJuego, heightJuego, Image.SCALE_SMOOTH);
    public static final ImageIcon VACA_MUERTO_IMAGE = new ImageIcon(VACA_MUERTO_IM_IC);

    private static final ImageIcon VACA_ICON = new ImageIcon("src\\com\\gui\\images\\animales\\Vaca.png");
    private static final Image VACA_IM_IC = VACA_ICON.getImage().getScaledInstance(widthJuego, heightJuego, Image.SCALE_SMOOTH);
    public static final ImageIcon VACA_IMAGE = new ImageIcon(VACA_IM_IC);
}
