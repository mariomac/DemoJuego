package com.ulpgc.mmacias.items.armas;

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


public abstract class Disparo extends ObjetoMovil {

    public Disparo(Image image) {
        super(image);
        this.tipo=TIPO_DISPARO;
    }
    abstract public int getFuerza();

}
