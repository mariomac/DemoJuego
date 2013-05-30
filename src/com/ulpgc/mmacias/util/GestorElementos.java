package com.ulpgc.mmacias.util;

import com.ulpgc.mmacias.fases.Fase;
import com.ulpgc.mmacias.fases.Fase1;
import com.ulpgc.mmacias.items.ObjetoMovil;
import com.ulpgc.mmacias.items.personajes.Protagonista;

/* ***************************************
   **  Juego de Demostracion *************
   *************************************************************************
   *   Juego creado por Mario Macias Lloret para el proyecto de fin        *
   * de carrera en ingenieria informatica: "Creacion de juegos para J2ME"  *
   * Universidad de Las Palmas de Gran Canaria                             *
   * Curso 2004-2005                                                       *
   *************************************************************************
*/


public class GestorElementos {
    private class Nodo {
        public ObjetoMovil objeto=null;
        public Nodo siguiente=null;
    }

    private Nodo actual[];
    private Nodo primero=null;

    private static GestorElementos ge=null;

    private Fase fase=null;
    private Protagonista prota=null;

    private int puntuacion_acumulada=0;

    public int getSize() {
        return fase.getSize();
    }

    public void iniciaDatos() {
        prota=new Protagonista();
        puntuacion_acumulada=0;

        actual=new Nodo[2]; actual[0]=null; actual[1]=null;
    }

    // estas dos funciones retornan la puntuaci�n a sumar, como
    // resultado de la muerte de los enemigos
    public void acumulaPuntuacion(int puntos) {
        puntuacion_acumulada+=puntos;
    }

    public int desacumulaPuntuacion() {
        int tmp=puntuacion_acumulada;
        puntuacion_acumulada=0;
        return tmp;
    }


    public Protagonista getProtagonista() {
        return prota;
    }

    public static GestorElementos singleton() {
        if(ge==null) {
            ge=new GestorElementos();
        }
        return ge;
    }

    public void destroy() {
        ge=null;
        if(fase!=null) {
            fase.finalizar();
        }
        fase=null;
        actual=null;
        primero=null;
        prota=null;
    }

    //Se anyaden al principio de la cola para que las acciones "mover"
    //de los objetos no se realicen hasta la siguiente iteraci�n, cuando
    //se llame al metodo "reinicia"
    public void anyade(ObjetoMovil objetoMovil) {
        fase.insert(objetoMovil,0);
        if(primero==null) {
            primero=new Nodo();
            primero.objeto=objetoMovil;
        } else {
            Nodo tmp=new Nodo();
            tmp.siguiente=primero;
            tmp.objeto=objetoMovil;
            primero=tmp;
        }
    }

    public boolean reinicia(int n) {
        if(primero==null) {
            return false;
        }
        actual[n]=primero;
        return true;
    }

    // en las siguientes 2 funciones, "n" indica el n�mero de
    // referencia al nodo guardado en el array "actual"    
    public boolean siguiente(int n) {
        if(actual[n]==null || actual[n].siguiente==null) {
            return false;
        } else {
            actual[n]=actual[n].siguiente;
            return true;
        }
    }

    public ObjetoMovil getObjetoMovil(int n) {
        if(actual[n]==null) return null;
        return actual[n].objeto;
    }

    public void elimina(ObjetoMovil objetoMovil) {
        fase.remove(objetoMovil);

        Nodo recorr=primero;
        Nodo anterior=null;
        boolean encontrado=false;

        if(primero.objeto==objetoMovil) {
            primero=primero.siguiente;
        } else {
            while(!encontrado && recorr!=null) {
                if(recorr.objeto==objetoMovil) {
                    if(anterior!=null) {
                        anterior.siguiente=recorr.siguiente;
                    }
                    encontrado=true;
                } else {
                    anterior=recorr;
                    recorr=recorr.siguiente;
                }
            }
        }
    }

    public void cargaFase(int num) {
        switch(num) {
            case 1:
                fase=new Fase1();
                break;
        }
    }

    public Fase getFase() {
        return fase;
    }

    public void liberaFase() {
        fase=null;
    }


}
