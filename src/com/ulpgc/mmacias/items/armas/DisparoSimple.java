package com.ulpgc.mmacias.items.armas;

import com.ulpgc.mmacias.util.Pantalla;

import javax.microedition.lcdui.Image;

/* ***************************************
   **  Juego de Demostracion *************
   *************************************************************************
   *   Juego creado por Mario Macias Lloret para el proyecto de fin        *
   * de carrera en ingenieria informatica: "Creacion de juegos para J2ME"  *
   * Universidad de Las Palmas de Gran Canaria                             *
   * Curso 2004-2005                                                       *
   *************************************************************************
*/


public class DisparoSimple extends Disparo {
    private static Image image=null;
    private static final int ANCHO=8;
    private static final int ALTO=8;

    static {
       image=cargaImagen("/disparo.png");
    }
    public DisparoSimple(int x, int y) {
        super(image);
        this.setPosition(x,y);
        defineReferencePixel(ANCHO/2,ALTO/2);
    }
    public void mover() {
        if(getX()>Pantalla.getAnchoTablero()) {
            terminar();
        }
        move(15,0);
    }

    public int getFuerza() {
        return 1;
    }
}
