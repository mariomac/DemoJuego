package com.ulpgc.mmacias;

import com.ulpgc.mmacias.util.*;
import javax.microedition.midlet.MIDlet;
import javax.microedition.midlet.MIDletStateChangeException;

/* ***************************************
   **  Juego de Demostracion *************
   *************************************************************************
   *   Juego creado por Mario Macias Lloret para el proyecto de fin        *
   * de carrera en ingenieria informatica: "Creacion de juegos para J2ME"  *
   * Universidad de Las Palmas de Gran Canaria                             *
   * Curso 2004-2005                                                       *
   *************************************************************************
*/


public class DemoJuego extends MIDlet {
    public final static int ESTADO_PRESENTACION = 0;
    public final static int ESTADO_PAUSA = 1;
    public final static int ESTADO_JUEGO = 2;
    public final static int ESTADO_SALIENDO = 3;
    public final static int ESTADO_GAMEOVER = 4;
    public final static int ESTADO_FIN = 5;

    private int estado=ESTADO_PRESENTACION;
    private GestorJuego gj=null;
    private Presentacion presentacion =null;

    protected void startApp() throws MIDletStateChangeException {
        switch(estado) {
            case ESTADO_JUEGO:
            case ESTADO_PAUSA:
                setEstado(ESTADO_PAUSA);
                break;
            case ESTADO_PRESENTACION:
                Pantalla.cachearDatos();
                setEstado(ESTADO_PRESENTACION);
                break;
        }
    }


    protected void pauseApp() {
        if(estado==ESTADO_JUEGO) {
            setEstado(ESTADO_PAUSA);
        }
    }

    protected void destroyApp(boolean b) throws MIDletStateChangeException {
        setEstado(ESTADO_SALIENDO);
        GestorElementos.singleton().destroy();
    }

    public int getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
        switch(estado) {
            case ESTADO_PRESENTACION:
                presentacion=new Presentacion(this);
                presentacion.setEstado(Presentacion.ESTADO_IMAGEN);
                break;
            case ESTADO_FIN:
                new DemoFinal(this);
                break;
            case ESTADO_JUEGO:
                gj.pausar(false);
                break;
            case ESTADO_PAUSA:
                gj.pausar(true);
                break;
        }
    }

    public void iniciaPartida() {
        presentacion=null;
        GestorElementos.singleton().destroy();        
        gj=new GestorJuego(this);
        gj.iniciaPartida();
    }
}
