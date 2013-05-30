package com.ulpgc.mmacias.items.personajes;

import com.ulpgc.mmacias.items.ObjetoMovil;
import com.ulpgc.mmacias.items.armas.Disparo;
import com.ulpgc.mmacias.items.otros.BalaMala;
import com.ulpgc.mmacias.util.GestorElementos;
import com.ulpgc.mmacias.util.Pantalla;
import com.ulpgc.mmacias.fases.Fase;
import javax.microedition.lcdui.Image;
import javax.microedition.lcdui.game.Sprite;

/* ***************************************
   **  Juego de Demostracion *************
   *************************************************************************
   *   Juego creado por Mario Macias Lloret para el proyecto de fin        *
   * de carrera en ingenieria informatica: "Creacion de juegos para J2ME"  *
   * Universidad de Las Palmas de Gran Canaria                             *
   * Curso 2004-2005                                                       *
   *************************************************************************
*/


public class Elvis extends Enemigo {
    public static final int ANCHO = 40;
    public static final int ALTO  = 56;
    public static final Image image=ObjetoMovil.cargaImagen("/elvis.png");

    private int estado=0;
    private int energia=30;
    private int tiempo=0;
    private int direccion=15;

    private Fase fase;


    public Elvis(Fase laFase) {
        super(image,ANCHO,ALTO);
        this.fase=laFase;
        tipo=TIPO_ENEMIGO;
        defineReferencePixel(ANCHO/2,ALTO/2);
        setPosition(Pantalla.getAnchoTablero(),Pantalla.getAltoTablero()-Pantalla.TILE_SIZE/2-ALTO);
    }

    public void mover() {
        tiempo++;
        switch(estado) {
            case 0:
                move(-3,0);
                if(tiempo%4==0) {
                    if(getFrame()==0) {
                        setFrame(1);
                    } else {
                        setFrame(0);
                    }
                }
                if(tiempo%20==0) {
                    GestorElementos ge=GestorElementos.singleton();
                    Protagonista prota=ge.getProtagonista();
                    ge.anyade(new BalaMala(getX()+ANCHO/2,getY()+15,prota.getX(),prota.getY()));
                }
                if(getX()<Pantalla.getAnchoTablero()/2) {
                    estado=1;
                    tiempo=0;
                }
                break;
            case 1:
                if(tiempo%2==0) {
                    if(getFrame()==2) {
                        setFrame(3);
                    } else {
                        setFrame(2);
                    }
                }
                if(tiempo%15==0) {
                    GestorElementos ge=GestorElementos.singleton();
                    ge.anyade(new NotaMusical());
                }
                if(tiempo>90) {
                    estado=2;
                    tiempo=0;
                }
                break;
            case 2:
                if(tiempo%2==0) {
                    if(getFrame()==0) {
                        setFrame(1);
                    } else {
                        setFrame(0);
                    }
                }
                move(direccion,0);
                if(getX()<0 || getX()>Pantalla.getAnchoTablero()-ANCHO) {
                    direccion=-direccion;
                    if(direccion>0) {
                        setTransform(Sprite.TRANS_MIRROR);
                    } else {
                        setTransform(Sprite.TRANS_NONE);
                    }
                }
                if(tiempo%15==0) {
                    GestorElementos ge=GestorElementos.singleton();
                    Protagonista prota=ge.getProtagonista();
                    ge.anyade(new BalaMala(getX(),getY()-ALTO/4,prota.getX(),prota.getY()));
                }
                if(tiempo>85) {
                    estado=1;
                    tiempo=0;
                }
                break;
            case 3:
                move(5,direccion++);
                switch((tiempo++)%4) {
                    case 0:
                        setTransform(TRANS_NONE);
                        break;
                    case 1:
                        setTransform(TRANS_ROT270);
                        break;
                    case 3:
                        setTransform(TRANS_ROT180);
                        break;
                    case 4:
                        setTransform(TRANS_ROT90);
                        break;
                }
                if(getX()>Pantalla.getAnchoTablero()) {
                    super.matar();
                    fase.setAcabada(true);
                }
                break;
        }

    }

    public void tocar(Disparo disparo) {
        energia-=disparo.getFuerza();
        if(energia<0) {
            matar();
        }
    }

    public void matar() {
        tipo=TIPO_OTRO;
        estado=3;
        direccion=-12;
        tiempo=0;
    }

    public int getPuntuacion() //devuelve el numero de puntos a sumar cuando muere el enemigo
    {
        return 60;
    }
}
