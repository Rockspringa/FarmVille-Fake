package com.exec;

import javax.swing.ImageIcon;

import com.gui.Frame;
import com.gui.frames.*;
import com.gui.images.Images;
import com.logic.array.Array;
import com.logic.objetos.posee_materia.*;
import com.logic.objetos.posee_materia.suelos.*;
import com.logic.objetos.posee_materia.productos.*;
import com.logic.objetos.posee_materia.seres_vivos.*;
import com.logic.objetos.posee_materia.seres_vivos.plantas.*;

public class Granja {
    public static Frame ventanaPrincipal;
    public static Granjero bob;
    private static Suelo[][] parcelas;
    private static Array<Planta> plantas;
    private static Array<Animal> animales;
    private static Array<Producto> productos;

    public static void main(String[] args) {
        Granja.plantas = new Array<Planta>();
        Granja.animales = new Array<Animal>();
        Granja.productos = new Array<Producto>();
        productos.add(new Barco());
        addCultivo("Maiz", 3, 3, 15, 10, 3, new Producto(5, "Maiz", Images.MAIZ_IMAGE));
        addArbol("Manzano", 3, 5, 7, 25, 18, 4, new Producto(3, "Manzana", Images.MANZANA_IMAGE));
        //animales.add((new Animal("Vaca")));
        //animales.add((new Animal("Gallina")));
        new Login().seeIt();
    }

    public static void addCultivo(String name, int cantSemillas,int precioSemilla,
                int deadline, int cosechaTime, int cantProdCosecha, Producto producto) {
    
        plantas.add(new Cultivo(name, cantSemillas, precioSemilla, deadline,
                        cosechaTime, cantProdCosecha, producto));
    }

    public static void addArbol(String name, int cantCosechas, int cantSemillas,
                            int precioSemilla, int deadline, int cosechaTime,
                            int cantProdCosecha, Producto producto) {
    
        plantas.add(new Arbol(name, cantCosechas, cantSemillas, precioSemilla, deadline,
                    cosechaTime, cantProdCosecha, producto));
    }

    public static void addAnimal(String name, double numParcelas, int precio,
                        boolean esOmnivoro, boolean esProductor, boolean esDestazable,
                        Producto produce) {
        
        animales.add(new Animal(name, numParcelas, precio, esOmnivoro,
                            esProductor, esDestazable, produce));
    }

    public static ImageIcon getJParcela(int m, int n) {
        return (parcelas[m][n] != null)
                    ? parcelas[m][n].getImage()
                    : Images.BLOQUEADO_IMAGE;
    }

    public static void iniciarParcela() {
        if (parcelas == null) {
            parcelas = new Suelo[5][5];
            for (int m = 0; m < 5; m++) {
                for (int n = 0; n < 5; n++) {
                    parcelas[m][n] = parcelaAleatoria();
                }
            }
        } else {
            parcelas = null;
            iniciarParcela();
        }
    }

    public static boolean newParcela() {
        int[] posXY = lugarVacio();
        parcelas[posXY[0]][posXY[1]] = parcelaAleatoria();
        return (posXY[2] == 1) ? true : false;
    }

    private static Suelo parcelaAleatoria() {
        Suelo newParcela = null;
        int rnd = (int) (Math.random() * 3);
        int[] numSuelo = {0, 1, 2};
        boolean DesieDist = Desierto.lessThanNecessary();
        boolean GramaDist = Grama.lessThanNecessary();
        boolean AguaDist = Agua.lessThanNecessary();

        if (!GramaDist) {
            numSuelo[0] = -1;
        } if (!AguaDist) {
            numSuelo[1] = -1;
        } if (!DesieDist) {
            numSuelo[2] = -1;
        }
        do {
            if (numSuelo[rnd] == -1) {
                rnd = (int) (Math.random() * 3);
            } else if (numSuelo[rnd] == 0) {
                newParcela = new Grama();
            } else if (numSuelo[rnd] == 1) {
                newParcela = new Agua();
            } else {
                newParcela = new Desierto();
            }
        } while (newParcela == null);
        return newParcela;
    }

    private static int[] lugarVacio() {
        int maxRow = parcelas.length;
        int maxCol = parcelas[0].length;
        int[] coordenadas = new int[3];

        for (int m = 0; m < maxRow; m++) {
            for (int n = 0; n < maxCol; n++) {
                if (parcelas[m][n] == null) {
                    coordenadas[0] = m;
                    coordenadas[1] = n;
                    coordenadas[2] = 0;
                    return coordenadas;
                }
            }
        }

        Suelo[][] arrAux = new Suelo[maxRow + 1][maxCol + 1];
        for (int m = 0; m < maxRow; m++) {
            for (int n = 0; n < maxCol; n++) {
                arrAux[m][n] = parcelas[m][n];
            }
        }

        parcelas = arrAux;
        coordenadas[0] = 0;
        coordenadas[1] = maxCol;
        coordenadas[2] = 1;

        return coordenadas;
    }

    public static void murioBob() {
        if (!bob.isAlive())
            parcelas = null;
    }

    public static int getDimesions() {
        return Granja.parcelas.length;
    }
    
    public static Suelo[][] getParcelas() {
        return Granja.parcelas;
    }

    public static void terminarActividad(int m, int n) {
        if (parcelas[m][n] != null && parcelas[m][n].getActividad() != null) {
            parcelas[m][n].getActividad().terminarActividad(bob);
        }
    }

    public static void terminarActividad(int[] coord) {
        Granja.terminarActividad(coord[0], coord[1]);
    }

    public static void terminarActividades(int[][] coordenadas) {
        for (int[] coord : coordenadas) {
            Granja.terminarActividad(coord);
        }
    }

    public static Array<Animal> getAnimales() {
        return Granja.animales;
    }

    public static Array<Planta> getPlantas() {
        return Granja.plantas;
    }
    
    public static Array<Producto> getProductos() {
        return Granja.productos;
    }
}
