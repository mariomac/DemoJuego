package com.ulpgc.mmacias.items.personajes;

import com.ulpgc.mmacias.items.armas.Disparo;
import com.ulpgc.mmacias.items.otros.Explosion;
import com.ulpgc.mmacias.util.GestorElementos;
import com.ulpgc.mmacias.util.Pantalla;
import com.ulpgc.mmacias.util.Utils;

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


public class Cerdo extends Enemigo {
    private int energia=3;
    private static final int ANCHO=32;
    private static final int ALTO=24;
    private static Image image=null;
    private static final int VELOCIDAD=-4;
    private int REBOTE=-13-Utils.random(6);


    private int accel=0;

    static {
        image=cargaImagen("/cerdo.png");
    }

    public Cerdo() {
        super(image,ANCHO,ALTO);
        defineReferencePixel(ANCHO/2,ALTO/2);
        setPosition(Pantalla.getAnchoTablero()+ANCHO,Utils.random(Pantalla.getAltoTablero()-ANCHO));
    }

    public void matar() {
        super.matar();
        GestorElementos ge=GestorElementos.singleton();
        int x=getX(), y=getY();
        ge.anyade(new Explosion(x-ANCHO/4,y));
        ge.anyade(new Explosion(x-3*ANCHO/4,y));
        terminar();
    }

    public void tocar(Disparo disparo) {
        energia-=disparo.getFuerza();
        if(energia<=0) {
            matar();
        }
    }

    public int getPuntuacion() //devuelve el numero de puntos a sumar cuando muere el enemigo
    {
        return 12;
    }

    public void mover() {
        accel+=2;
        move(VELOCIDAD,accel);
        if(accel==0) {
            dispara();
        }
        if(getY()>Pantalla.getAltoTablero()-ANCHO) {
            accel=REBOTE;
        }
        if(getX()<-ANCHO) {
            terminar();
        }
        if(accel>0) {
            setFrame(0);
        } else {
            setFrame(1);
        }
    }
}
