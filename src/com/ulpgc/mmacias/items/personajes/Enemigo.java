package com.ulpgc.mmacias.items.personajes;

import com.ulpgc.mmacias.items.ObjetoMovil;
import com.ulpgc.mmacias.items.armas.Disparo;
import com.ulpgc.mmacias.items.otros.BalaMala;
import com.ulpgc.mmacias.util.GestorElementos;
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


public abstract class Enemigo extends ObjetoMovil {
    public Enemigo(Image image) {
        super(image);
        this.tipo=TIPO_ENEMIGO;
    }

    public Enemigo(Image image, int ancho, int alto) {
        super(image,ancho,alto);
        this.tipo=TIPO_ENEMIGO;
    }

    public void matar() {
        GestorElementos.singleton().acumulaPuntuacion(getPuntuacion());
        terminar();
    }

    protected void dispara() {
        GestorElementos ge=GestorElementos.singleton();
        Protagonista p=ge.getProtagonista();
        ge.anyade(new BalaMala(getX(),getY(),p.getX(),p.getY()));
    }



    public abstract void tocar(Disparo disparo);
    public abstract int getPuntuacion(); //devuelve el numero de puntos a sumar cuando muere el enemigo

}
