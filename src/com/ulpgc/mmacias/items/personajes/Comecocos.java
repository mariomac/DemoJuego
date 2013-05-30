package com.ulpgc.mmacias.items.personajes;

import com.ulpgc.mmacias.items.armas.Disparo;
import com.ulpgc.mmacias.items.otros.Explosion;
import com.ulpgc.mmacias.util.Pantalla;
import com.ulpgc.mmacias.util.Utils;
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

public class Comecocos extends Enemigo {
    private static final int ANCHO=16;
    private static final int ALTO=16;

    private static Image image=null;

    private int dirx=-7,diry=0;
    boolean sube=true;
    private int time=0;
    private int siguienteCambio=10+Utils.random(20);

    static {
        image=cargaImagen("/comecocos.png");
    }

    private Comecocos() {
        super(image,ANCHO,ALTO);
        defineReferencePixel(ANCHO/2,ALTO/2);
    }

    public Comecocos(int altura) {
        this();
        setPosition(Pantalla.getAnchoTablero()+ANCHO,altura);
    }

    public void mover() {
        int x=getX(), y=getY();
        if(time++%2==0) {
            this.nextFrame();
        }
        if(time>siguienteCambio) {
            cambiaDireccion();
            siguienteCambio+=5+Utils.random(5);
        }

        if(y<=0) {
            setPosition(x,y=0);
            cambiaDireccion();
        } else if(y>Pantalla.getAltoTablero()-ALTO) {
            setPosition(x,Pantalla.getAltoTablero()-ANCHO);
            cambiaDireccion();
        }

        if(getX()<-ANCHO) {
            terminar();
        }
        move(dirx,diry);
    }

    private void cambiaDireccion() {
        if(dirx==0) {
            dirx=-5;
            diry=0;
        } else {
            dirx=0;
            diry=Utils.random(2)==0?-5:5;
        }

        if(dirx!=0) {
            setTransform(TRANS_NONE);
        } else {
            if(diry<0) {
                setTransform(TRANS_ROT90);
            } else {
                setTransform(TRANS_ROT270);
            }
        }
    }

    public void tocar(Disparo disparo) {
        matar();
    }

    public void matar() {
        super.matar();
        GestorElementos.singleton().anyade(new Explosion(getX(),getY()));
    }

    public int getPuntuacion() //devuelve el numero de puntos a sumar cuando muere el enemigo
    {
        return 7;
    }


}
