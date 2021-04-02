package com.exec;

import javax.swing.ImageIcon;

import com.gui.Frame;
import com.gui.frames.*;
import com.gui.images.Images;
import com.logic.objetos.*;
import com.logic.objetos.suelos.*;
import com.logic.objetos.seres_vivos.*;

public class Granja {
    public static Frame ventanaPrincipal;
    private static Suelo[][] parcelas;
    public static Granjero bob;

    public static void main(String[] args) {
        new Login().seeIt();
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
}
