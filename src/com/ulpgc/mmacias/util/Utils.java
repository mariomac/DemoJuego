package com.ulpgc.mmacias.util;

import java.util.Random;

/* ***************************************
   **  Juego de Demostracion *************
   *************************************************************************
   *   Juego creado por Mario Macias Lloret para el proyecto de fin        *
   * de carrera en ingenieria informatica: "Creacion de juegos para J2ME"  *
   * Universidad de Las Palmas de Gran Canaria                             *
   * Curso 2004-2005                                                       *
   *************************************************************************
*/


public class Utils {
    private static Random rnd=new Random(System.currentTimeMillis());
    public static int random(int max) {
        int r=rnd.nextInt();
        if(r<0) {
            r=(-r)%max;
        } else {
            r=r%max;
        }

        return r;
    }
    
}
