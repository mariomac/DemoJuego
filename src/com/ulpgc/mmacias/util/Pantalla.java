package com.ulpgc.mmacias.util;

import javax.microedition.lcdui.Displayable;
import javax.microedition.lcdui.Canvas;
import javax.microedition.lcdui.Graphics;
import javax.microedition.midlet.MIDlet;

/* ***************************************
   **  Juego de Demostracion *************
   *************************************************************************
   *   Juego creado por Mario Macias Lloret para el proyecto de fin        *
   * de carrera en ingenieria informatica: "Creacion de juegos para J2ME"  *
   * Universidad de Las Palmas de Gran Canaria                             *
   * Curso 2004-2005                                                       *
   *************************************************************************
*/


// La funci�n de esta clase es "pre-cachear" algunos calculos referentes a la pantalla y algunas utilidades m�s
public class Pantalla {
    public static final int TILE_SIZE=16;
    private static int anchoPantalla;
    private static int altoPantalla;

    public static void cachearDatos() {
        //Truco chapuceril para pillar el ancho y alto de la pantalla al completo
        Displayable display=new Canvas() { protected void paint(Graphics graphics) {}};
        anchoPantalla=display.getWidth();
        altoPantalla=display.getHeight();
    }


    public static int getAnchoPantalla() {
        return anchoPantalla;
    }

    public static int getAltoPantalla() {
        return altoPantalla;
    }

    public static int getAnchoTablero() {
        return anchoPantalla;
    }

    public static int getAltoTablero() {
        return 10*TILE_SIZE;
    }




}

