package com.ulpgc.mmacias.items.personajes;

import com.ulpgc.mmacias.items.ObjetoMovil;
import com.ulpgc.mmacias.items.armas.DisparoSimple;
import com.ulpgc.mmacias.items.otros.Explosion;
import com.ulpgc.mmacias.util.GestorElementos;
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

public class Protagonista extends ObjetoMovil {
    static Image image=null;

    private boolean muriendo,saliendo;
    private int veloc_caida=0;  //para cuando esta muriendo
    private int tiempo=0;

    private static final int ANCHO=24;
    private static final int ALTO=16;

    public static final int STOP = 0;
    public static final int UP = 1;
    public static final int DOWN = 2;
    public static final int LEFT = 4;
    public static final int RIGHT = 8 ;

    private int velocidad = 45;
    private int errorveloc = 0; // como iremos dividiendo la velocidad entre 10 para dar mas precision,
                                // acumulamos el error para no perderla

    private int direccion;
    private boolean disparando;
    static {
       image=cargaImagen("/protagonista.png");
    }

    public Protagonista() {
        super(image,ANCHO,ALTO);
        tipo=TIPO_PROTA;
        defineReferencePixel(ANCHO/2,ALTO/2);
        setSaliendo();
        disparando=false;
    }

    private void setSaliendo() {
        muriendo=false;
        saliendo=true;
        setPosition(20,50);
        tiempo=0;
        setVisible(false);
    }

    public void mover() {
        if(disparando) {
            disparando=false;
            GestorElementos.singleton().anyade(new DisparoSimple(getX(),getY()+ALTO/2));
        }
        if(muriendo) {
            move(0,++veloc_caida);
            if(veloc_caida<8) {
                setFrame(0);
            } else {
                setFrame(1);
            }
            if(veloc_caida%3==0) {
                GestorElementos.singleton().anyade(new Explosion(getX(),getY()));
            }
            if(getY()>Pantalla.getAltoTablero()) {
                setSaliendo();
            }
        } else {
            if(saliendo) {
                setVisible((tiempo%2)==0?true:false);
                if(tiempo++>24) {
                    setVisible(true);
                    saliendo=false;
                }
            }
            errorveloc=(errorveloc+velocidad)%10;
            int v=(errorveloc+velocidad)/10;

            if((direccion & UP)!=0) {
                setFrame(2);
                if(getY()>0) {
                    move(0,-v);
                }
            } else if((direccion & DOWN)!=0) {
                setFrame(1);
                if(getY()<(Pantalla.getAltoTablero()-ALTO)) {
                    move(0,v);
                }
            } else {
                setFrame(0);
            }

            if((direccion & LEFT)!=0) {
                if(getX()>0) {
                    move(-v,0);
                }

            } else if((direccion & RIGHT)!=0) {
                if(getX()<Pantalla.getAnchoTablero()-30) {
                    move(v,0);
                }
            }
        }
    }

    public void matar() {
        if(puedeMorir()) {
            muriendo=true;
            veloc_caida=0;
        }
    }

    public boolean isMuriendo() {
        return muriendo;
    }

    public boolean puedeMorir() {
        return !muriendo && !saliendo;
    }

    public void addDireccion(int direccion) {
        this.direccion=this.direccion | direccion;
    }

    public void deleteDireccion(int direccion) {
        this.direccion=this.direccion & ~direccion;
    }

    public void dispara() {
        disparando=true;
    }

}
