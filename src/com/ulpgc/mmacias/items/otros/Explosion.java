package com.ulpgc.mmacias.items.otros;

import com.ulpgc.mmacias.items.ObjetoMovil;

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


public class Explosion extends ObjetoMovil{
    private static final int WIDTH=16;
    private static final int HEIGHT=16;

    private static Image image=null;

    private int time=0;

    static {
        image=cargaImagen("/explosion.png");
    }

    public Explosion(int x,int y) {
        super(image,WIDTH,HEIGHT);
        tipo=TIPO_OTRO;
        setPosition(x,y);
    }

    public void mover() {
        if(++time>4) {
            terminar();
        }
        nextFrame();
    }
}
