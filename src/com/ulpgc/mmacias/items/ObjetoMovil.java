package com.ulpgc.mmacias.items;

import com.ulpgc.mmacias.util.GestorElementos;

import javax.microedition.lcdui.Image;
import javax.microedition.lcdui.game.Sprite;
import java.io.IOException;

/* ***************************************
   **  Juego de Demostracion *************
   *************************************************************************
   *   Juego creado por Mario Macias Lloret para el proyecto de fin        *
   * de carrera en ingenieria informatica: "Creacion de juegos para J2ME"  *
   * Universidad de Las Palmas de Gran Canaria                             *
   * Curso 2004-2005                                                       *
   *************************************************************************
*/


public abstract class ObjetoMovil extends Sprite {
    public static final int TIPO_DISPARO    = 0;
    public static final int TIPO_ENEMIGO    = 1;
    public static final int TIPO_OPTION     = 2;
    public static final int TIPO_BALAMALA   = 3;
    public static final int TIPO_OTRO       = 4;
    public static final int TIPO_PROTA      = 5;

    protected int tipo;


    public ObjetoMovil(Image image) {
        super(image);
    }

    public ObjetoMovil(Image image, int framewidth, int frameheight) {
        super(image,framewidth,frameheight);
    }

    public int getTipo() {
        return tipo;
    }

    public void setTipo(int tipo) {
        this.tipo = tipo;
    }

    public void terminar() {
        GestorElementos.singleton().elimina(this);
    }

    public void matar() {
        terminar();
    }

    abstract public void mover();

    protected static Image cargaImagen(String imagename) {
        Image image=null;
        try {
            image=Image.createImage(imagename);
        } catch(IOException e) {
            System.err.println("Error cargando imagen "+imagename+": "+e.getMessage());
        }
        return image;
    }
}
