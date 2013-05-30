package com.ulpgc.mmacias.util.sound;

import com.ulpgc.mmacias.util.Configuracion;

import javax.microedition.media.MediaException;
import java.util.Vector;

/* ***************************************
   **  Juego de Demostración *************
   *************************************************************************
   *   Juego creado por Mario Macías Lloret para el proyecto de fín        *
   * de carrera en ingeniería informática: "Creación de juegos para J2ME"  *
   * Universidad de Las Palmas de Gran Canaria                             *
   * Curso 2004-2005                                                       *
   *************************************************************************
*/

public class GestorSonidos {
    private static GestorSonidos gm=null;
    private Vector sonidos=new Vector(5);

    private Integer index_tocando=null;

    private GestorSonidos() {
    }

    public static GestorSonidos singleton() {
        if(gm==null) {
            gm=new GestorSonidos();
        }
        return gm;
    }

    public void eliminaSonidos() {
        if(sonidos!=null) {
            for(int i=0;i<sonidos.size();i++) {
                MediaPlayer sonido=((MediaPlayer)sonidos.elementAt(i));
                sonido.stop();
                sonido.close();
            }
        }
        index_tocando=null;
        sonidos=new Vector(5);
    }

    //Retorna la posición del vector donde está el sonido
    public int cargaSonido(String filename, String type, boolean repeat) {
        MediaPlayer sonido=new MediaPlayer(filename,type,repeat);
        try {
            sonido.realize();
        } catch(MediaException e) {
            System.out.println("Error cargando el sonido: "+e.getMessage());
        }
        sonidos.addElement(sonido);
        return sonidos.indexOf(sonido);
    }

    public void tocaSonido(int index) {
        paraSonido();
        if(Configuracion.singleton().isMusic()) {
            ((MediaPlayer)sonidos.elementAt(index)).start();
            index_tocando=new Integer(index);
        }
    }

    public Integer getIndexSonidoActual() {
        return index_tocando;
    }

    public void paraSonido() {
        if(index_tocando!=null) {
            ((MediaPlayer)sonidos.elementAt(index_tocando.intValue())).stop();
            index_tocando=null;
        }
    }
}
