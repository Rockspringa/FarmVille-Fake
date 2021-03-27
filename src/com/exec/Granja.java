package com.exec;

import javax.swing.ImageIcon;
import com.gui.frames.*;
import com.logic.objetos.*;
import com.logic.objetos.suelos.*;

public class Granja {
    private static Suelo[][] parcelas;
    public static Granjero bob;

    public static void main(String[] args) {
        new Login().seeIt();
    }

    public static ImageIcon getJParcela(int m, int n) {
        return parcelas[m][n].getImage();
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

    public static void newParcela() {
        int[] posXY = lugarVacio();
        parcelas[posXY[0]][posXY[1]] = parcelaAleatoria();
    }

    private static Suelo parcelaAleatoria() {
        Suelo newParcela;
        int rnd;
        boolean DesieDist = Desierto.lessThanNecessary();
        boolean GramaDist = Grama.lessThanNecessary();
        boolean AguaDist = Agua.lessThanNecessary();

        if (GramaDist && AguaDist && DesieDist)
            rnd = (int) (Math.random() * 3);

        else if (GramaDist && AguaDist)
            rnd = (int) (Math.random() * 2);
            
        else if (GramaDist && DesieDist) {
            rnd = (int) (Math.random() * 2);
            if (rnd == 1)
                rnd = 2;
        } else if (AguaDist && DesieDist)
            rnd = (int) (Math.random() * 2 + 1);

        else if (GramaDist)
            rnd = 0;

        else if (AguaDist)
            rnd = 1;

        else
            rnd = 2;

        newParcela = (rnd == 0) ? new Grama()
                        : (rnd == 1) ? new Agua()
                            : new Desierto();
        
        return newParcela;
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
