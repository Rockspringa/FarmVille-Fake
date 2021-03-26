package com.exec;

import com.gui.frames.*;
import java.awt.Color;
import com.logic.objetos.Suelo;
import com.logic.objetos.suelos.*;
import com.logic.objetos.Granjero;

public class Granja {
    private static Suelo[][] parcelas;
    public static Granjero bob;

    public static void main(String[] args) {
        new Login().seeIt();
        inciarParcela();
    }

    public static Color getJParcela(int m, int n) {
        return (parcelas[m][n] instanceof Grama)
                ? Color.GREEN
                : (parcelas[m][n] instanceof Agua)
                    ? Color.BLUE
                    : Color.YELLOW;
    }

    public static void inciarParcela() {
        if (parcelas == null) {
            parcelas = new Suelo[5][5];
            for (int m = 0; m < 5; m++) {
                for (int n = 0; n < 5; n++) {
                    parcelas[m][n] = new Grama();
                }
            }
        }
    }

    public static void parcelaAleatoria() {
        int[] coord = lugarVacio();
        parcelas[coord[0]][coord[1]] = new Grama();
    }

    private static int[] lugarVacio() {
        int maxRow = parcelas.length;
        int maxCol = parcelas[0].length;
        int[] coordenadas = new int[2];

        for (int m = 0; m < maxRow; m++) {
            for (int n = 0; n < maxCol; n++) {
                if (parcelas[m][n] == null) {
                    coordenadas[0] = m;
                    coordenadas[1] = n;
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
        coordenadas[1] = maxCol + 1;

        return coordenadas;
    }

    public static void murioBob() {
        if (!bob.isAlive())
            parcelas = null;
    }
}
