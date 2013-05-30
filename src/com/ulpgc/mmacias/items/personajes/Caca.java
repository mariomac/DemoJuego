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


public class Caca extends Enemigo {
    private static final int WIDTH=16;
    private static final int HEIGHT=16;

    private static Image image=null;

    private int estado=0;
    private int time=0;

    private int disparo=Utils.random(Pantalla.getAnchoTablero());
    private boolean disparado=false;

    static {
        image=cargaImagen("/caca.png");
    }

    private Caca() {
        super(image,WIDTH,HEIGHT);
        defineReferencePixel(WIDTH/2,HEIGHT/2);
    }

    public Caca(int altura) {
        this();
        setPosition(Pantalla.getAnchoTablero()+WIDTH,altura);
    }

    public void mover() {
        if(time++%2==0) {
            this.nextFrame();
        }

        switch(estado) {
            case 0:
                move(-6,0);
                int x=getX();
                if(x<disparo && !disparado) {
                    dispara();
                    disparado=true;
                }
                if(getX()<10) {
                    if(getY()<Pantalla.getAltoTablero()/2) {
                        estado=1;
                    } else {
                        estado=2;
                    }
                }
                break;
            case 1:
                move(4,2);
                if(getX()>Pantalla.getAnchoTablero() || getY()<-WIDTH) {
                    terminar();
                }
                break;
            case 2:
                move(4,-2);
                if(getX()>Pantalla.getAnchoTablero() || getY()>Pantalla.getAltoTablero()) {
                    terminar();
                }
                break;
        }

    }

    public void matar() {
        super.matar();
        GestorElementos.singleton().anyade(new Explosion(getX(),getY()));
    }

    public void tocar(Disparo disparo) {
        matar();
    }

    public int getPuntuacion() //devuelve el numero de puntos a sumar cuando muere el enemigo
    {
        return 5;
    }
}
