package com.ulpgc.mmacias.items.options;

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

public abstract class Option extends ObjetoMovil {
    public Option(Image image) {
        super(image);
    }

    public Option(Image image, int framewidth, int frameheight) {
        super(image,framewidth,frameheight);
    }

    //hace lo que quiera cuando el protagonista lo obtiene
    public abstract void actua();
}
