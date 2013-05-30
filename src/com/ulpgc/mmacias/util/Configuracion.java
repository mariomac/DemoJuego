package com.ulpgc.mmacias.util;

/* ***************************************
   **  Juego de Demostracion *************
   *************************************************************************
   *   Juego creado por Mario Macias Lloret para el proyecto de fin        *
   * de carrera en ingenieria informatica: "Creacion de juegos para J2ME"  *
   * Universidad de Las Palmas de Gran Canaria                             *
   * Curso 2004-2005                                                       *
   *************************************************************************
*/


public class Configuracion {
    private static Configuracion config=null;
    private boolean music;
    private int vidas;

    private Configuracion() {
        //Configuracion por defecto
        music=false;
        vidas=3;
    }

    static public Configuracion singleton() {
        if(config==null) {
            config=new Configuracion();
        }
        return config;
    }

    public boolean isMusic() {
        return music;
    }

    public void setMusic(boolean music) {
        this.music = music;
    }

    public int getVidasIniciales() {
        return vidas;
    }

    public void setVidasIniciales(int vidas) {
        this.vidas = vidas;
    }


}
