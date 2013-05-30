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


public class NotaMusical extends Enemigo {
    public static final int ANCHO = 16;
    public static final int ALTO  = 16;

    private static Image image=null;
    static {
        image=cargaImagen("/nota.png");
    }

    private boolean atacando=false;
    private int time=0;
    private int accelx=0;
    private int accely=0;

    public NotaMusical() {
        super(image,ANCHO,ALTO);
        this.tipo=TIPO_OTRO;
        defineReferencePixel(ANCHO/2,ALTO/2);
        setPosition(Utils.random(Pantalla.getAnchoTablero()),Utils.random(Pantalla.getAltoTablero()));
    }

    public void tocar(Disparo disparo) {
        matar();
    }

    public void mover() {
        time++;
        if(atacando) {
            Protagonista prota=GestorElementos.singleton().getProtagonista();
            int x=getX(), y=getY();
            if(time%5==0) {
                accelx+=x>prota.getX()?-1:+1;
                accely+=y>prota.getY()?-1:+1;
            }
            if(x<-ANCHO || y <-ALTO || x>Pantalla.getAnchoTablero()+ANCHO || y>Pantalla.getAltoTablero()+ALTO) {
                terminar();
            } else {
                nextFrame();
                move(accelx,accely);
            }
        } else {
            setVisible(time%2==0?true:false);
            if(time>13) {
                setVisible(true);
                atacando=true;
                tipo=TIPO_ENEMIGO;
            }
        }
    }

    public int getPuntuacion() //devuelve el numero de puntos a sumar cuando muere el enemigo
    {
        return 10;
    }

    public void matar() {
        super.matar();    //To change body of overridden methods use File | Settings | File Templates.
        GestorElementos.singleton().anyade(new Explosion(getX(),getY()));        
    }
}
