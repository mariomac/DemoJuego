package com.ulpgc.mmacias.items.otros;

import com.ulpgc.mmacias.items.ObjetoMovil;
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


public class BalaMala extends ObjetoMovil {
    private static final int VELOCIDAD=5;
    private static final int ANCHO=8;
    private static final int ALTO=8;

    private static Image image=null;
    static {
        if(image==null) {
            image=cargaImagen("/balamala.png");
        }
    }

    //haremos que la bala se dirija al punto mediante el algoritmo de bresenham.
    double x,y,incx,incy;

    public BalaMala(int fromx, int fromy, int tox, int toy) {
        super(image);
        this.tipo=TIPO_BALAMALA;
        this.setPosition(fromx,fromy);
        defineReferencePixel(ANCHO/2,ALTO/2);

        x=fromx; y=fromy;

        //Aproximacion rapida y rastrera de sqrt(lado_largo+lado_corto) --> ladro_largo+5/16*lado_corto
        //Normalizaremos los vectores y los multiplicamos por la velocidad para conocer el incremento que deben
        //tener las balas en todo momento
        //Se hace as� por que java no tiene ni SQRT, SIN, COS, etc... para hacerlo mediante otros m�todos m�s precisos
        int L1=Math.abs(tox-fromx);
        int L2=Math.abs(toy-fromy);

        int max,min;
        if(Math.abs(L1)>Math.abs(L2)) {
            max=L1; min=L2;
        } else {
            max=L2; min=L1;
        }
        double modulo=Math.abs(16*max+5*min)/16;
        incx=L1/modulo*VELOCIDAD;
        incy=L2/modulo*VELOCIDAD;
        if(fromx>tox) incx=-incx;
        if(fromy>toy) incy=-incy;
        //System.out.println("incremento: ("+Integer.toString((int)(incx*100))+","+Integer.toString((int)(incy*100))+")");

    }
    public void mover() {
        if(getX()>Pantalla.getAnchoTablero() || getX()<-ANCHO || getY()<-ALTO || getY()>Pantalla.getAltoTablero()) {
            terminar();
        }
        x+=incx; y+=incy;
        setPosition((int)x,(int)y);
    }

}
