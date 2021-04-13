package com.logic.objetos.posee_materia.seres_vivos;

import javax.swing.ImageIcon;

import com.exec.Granja;
import com.gui.images.Images;
import com.logic.objetos.posee_materia.*;
import com.logic.objetos.posee_materia.productos.*;

public class Animal implements SerVivo {
    private final Producto[] produceDest;
    private final Producto[] produceProd;
    private final double[] porcentajeDest;
    private final double[] porcentajeProd;
    private final boolean destazable;
    private final boolean productor;
    private final boolean omnivoro;
    private final double numParcelas;
    private final String nombre;
    private final int precio;
    private ImageIcon imageNormal;
    private ImageIcon imageMuerto;
    private boolean vivo;
    private int cantProdDest;
    private int cantProdProd;
    private int tiempo;
    private int tiempoDestace;
    private int vida;
    private int cantAlim;

    public Animal(String nombre, double numParcelas, int precio, boolean esOmnivoro,
                        boolean esProductor, int cantProdProd, Producto[] produceProd,
                        double[] porcProd, boolean esDestazable, int cantProdDest,
                        Producto[] produceDest, double[] porcDest) {
        this.cantProdDest = cantProdDest;
        this.produceDest = produceDest;
        this.porcentajeDest = porcDest;
        this.porcentajeProd = porcProd;
        this.cantProdProd = cantProdProd;
        this.numParcelas = numParcelas;
        this.produceProd = produceProd;
        this.destazable = esDestazable;
        this.productor = esProductor;
        this.omnivoro = esOmnivoro;
        this.nombre = nombre;
        this.precio = precio;
        this.tiempoDestace = 0;
        this.tiempo = 0;
        this.vivo = true;
        this.vida = 30;

        if (this.destazable && this.productor && this.omnivoro) {
            this.imageNormal = Images.DES_PROD_OMNI_IMAGE;
            this.imageMuerto = Images.DES_PROD_OMNI_MUERTO_IMAGE;
        } else if (this.destazable && this.productor) {
            this.imageNormal = Images.DES_PROD_HER_IMAGE;
            this.imageMuerto = Images.DES_PROD_HER_MUERTO_IMAGE;
        } else if (this.destazable && this.omnivoro) {
            this.imageNormal = Images.DES_OMNI_IMAGE;
            this.imageMuerto = Images.DES_OMNI_MUERTO_IMAGE;
        } else if (this.productor && this.omnivoro) {
            this.imageNormal = Images.PROD_OMNI_IMAGE;
            this.imageMuerto = Images.PROD_OMNI_MUERTO_IMAGE;
        } else if (this.destazable) {
            this.imageNormal = Images.DES_HER_IMAGE;
            this.imageMuerto = Images.DES_HER_MUERTO_IMAGE;
        } else if (this.productor) {
            this.imageNormal = Images.PROD_HER_IMAGE;
            this.imageMuerto = Images.PROD_HER_MUERTO_IMAGE;
        }
    }

    public Animal(String nombre, double numParcelas, int precio, boolean esOmnivoro,
                    boolean esProductor, int cantProdProd, Producto produceProd,
                    double porcProd, boolean esDestazable, int cantProdDest,
                    Producto produceDest, double porcDest) {
        this(nombre, numParcelas, precio, esOmnivoro, esProductor, cantProdProd,
                    new Producto[] {produceProd}, new double[] {porcProd}, esDestazable,
                    cantProdDest, new Producto[] {produceDest}, new double[] {porcDest});
        }

    public Animal(String vaca, int uno) {
        this.produceDest =  new Producto[] {new Producto(Producto.CUERO), new Alimento(Producto.CARNE)};
        this.cantProdDest = 5;
        this.porcentajeDest = new double[] {0.25, 0.75};
        this.porcentajeProd = new double[] {1};
        this.tiempoDestace = 0;
        this.cantProdProd = 2;
        this.produceProd = new Producto[] {new Alimento(Producto.LECHE)};
        this.imageNormal = Images.VACA_IMAGE;
        this.imageMuerto = Images.VACA_MUERTO_IMAGE;
        this.numParcelas = 2;
        this.destazable = true;
        this.productor = true;
        this.omnivoro = false;
        this.nombre = vaca;
        this.precio = 10;
        this.tiempo = 0;
        this.vivo = true;
        this.vida = 30;
    }

    public Animal(String gallina) {
        this.cantProdDest = 3;
        this.produceDest = new Producto[] {new Alimento(Producto.CARNE)};
        this.porcentajeDest = new double[] {1};
        this.porcentajeProd = new double[] {1};
        this.cantProdProd = 4;
        this.produceProd = new Producto[] {new Alimento(Producto.HUEVO)};
        this.imageNormal = Images.GALLINA_IMAGE;
        this.imageMuerto = Images.GALLINA_MUERTO_IMAGE;
        this.numParcelas = 0.5;
        this.destazable = true;
        this.productor = true;
        this.omnivoro = true;
        this.nombre = gallina;
        this.precio = 4;
        this.tiempo = 0;
        this.tiempoDestace = 0;
        this.vivo = true;
        this.vida = 30;
    }    

    public Animal(Animal oldAnimal) {
        this.cantProdDest = oldAnimal.cantProdDest;
        this.produceDest =  oldAnimal.produceDest;
        this.porcentajeDest = oldAnimal.porcentajeDest;
        this.porcentajeProd = oldAnimal.porcentajeProd;
        this.cantProdProd = oldAnimal.cantProdProd;
        this.produceProd = oldAnimal.produceProd;
        this.imageNormal = oldAnimal.imageNormal;
        this.imageMuerto = oldAnimal.imageMuerto;
        this.numParcelas = oldAnimal.numParcelas;
        this.destazable = oldAnimal.destazable;
        this.productor = oldAnimal.productor;
        this.omnivoro = oldAnimal.omnivoro;
        this.nombre = oldAnimal.nombre;
        this.precio = oldAnimal.precio;
        this.tiempoDestace = 0;
        this.tiempo = 0;
        this.vivo = true;
        this.vida = 30;
    }

    @Override
    public String getNombre() {
        return this.nombre;
    }

    public boolean isOmnivoro() {
        return this.omnivoro;
    }

    public boolean isHerbivoro() {
        return !this.omnivoro;
    }

    @Override
    public int getPrecio() {
        return this.precio;
    }

    @Override
    public ImageIcon getImage() {
        return (this.isAlive()) ? this.imageNormal
                                : this.imageMuerto;
    }

    public double getNumParcelas() {
        return numParcelas;
    }

    @Override
    public int getVida() {
        return this.vida;
    }

    @Override
    public void bajarVida() {
        this.tiempo++;
        this.tiempoDestace++;
        if (tiempo >= vida || tiempoDestace > 80) {
            this.vida = 0;
            this.vivo = false;
        }
    }

    @Override
    public boolean isAlive() {
        return this.vivo;
    }

    @Override
    public void alimentarse(Producto comida) {
        Alimento alm;
        if (comida instanceof Alimento) {
            alm = (Alimento) comida;
            if (omnivoro && this.isAlive()) {
                if (this.cantAlim++ < 3) {
                    this.cantProdDest++;
                    this.cantProdProd++;
                }
                this.vida += (alm.isParaOmnivoro())
                                ? alm.getVidaRecuperable()
                                : 0;
            } else if (!omnivoro && this.isAlive()) {
                if (this.cantAlim++ < 3) {
                    this.cantProdDest++;
                    this.cantProdProd++;
                }
                this.vida += (!alm.isParaOmnivoro())
                                ? alm.getVidaRecuperable()
                                : 0;
            }
        }
    }

    @Override
    public void morir() {
        if (destazable && isReadyToDead()) {
            for (int m = 0; m < this.produceDest.length; m++) {
                int max = 0;
                try {
                    max = (int) (this.cantProdDest * this.porcentajeDest[m]);
                } catch (IndexOutOfBoundsException e) {
                    max = 0;
                }
                for (int n = 0; n < max; n++) {
                    if (this.produceDest[m] instanceof Alimento)
                        Granja.bob.addProducto(new Alimento((Alimento) (this.produceDest[m])));
                    else
                        Granja.bob.addProducto(new Producto(this.produceDest[m]));
                }
            }
            this.vida = 0;
            this.vivo = false;
        }
    }

    public boolean isDestazable() {
        return this.destazable;
    }

    public boolean isReadyToDead() {
        return (isDestazable() && !isReadyToRecolect())
                && (this.tiempoDestace > 25 && isAlive());
    }

    public void getProductos() {
        if (productor && isReadyToRecolect()) {
            for (int m = 0; m < this.produceProd.length; m++) {
                int max = 0;
                try {
                    max = (int) (this.cantProdProd * this.porcentajeProd[m]);
                } catch (IndexOutOfBoundsException e) {
                    max = 0;
                }
                for (int n = 0; n < max; n++) {
                    if (this.produceProd[m] instanceof Alimento)
                        Granja.bob.addProducto(new Alimento((Alimento) (this.produceProd[m])));
                    else
                        Granja.bob.addProducto(new Producto(this.produceDest[m]));
                }
            }
            this.vida-= this.tiempo;
            this.tiempo -= this.tiempo;
        }
    }

    public boolean isProductor() {
        return this.productor;
    }

    public boolean isReadyToRecolect() {
        return (isProductor() && this.tiempo > 15)
                        ? (isAlive()) ? true : false : false;
    }
    
    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        } else if (obj instanceof Animal
                    && obj.getClass().getSimpleName().equals(this.getClass().getSimpleName())) {
            Animal objA = (Animal) (obj);
            if (objA.getNombre().equals(this.getNombre())
                        && objA.getPrecio() == this.getPrecio()
                        && objA.isOmnivoro() == this.isOmnivoro()
                        && objA.isDestazable() == this.isDestazable()
                        && objA.isProductor() == this.isProductor()
                        && objA.imageNormal == objA.imageNormal) {
                return true;
            }
        } return false;
    }
}
