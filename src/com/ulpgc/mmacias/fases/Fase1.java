package com.ulpgc.mmacias.fases;

import com.ulpgc.mmacias.items.personajes.*;
import com.ulpgc.mmacias.util.GestorElementos;
import com.ulpgc.mmacias.util.Pantalla;
import com.ulpgc.mmacias.util.sound.GestorSonidos;

/* ***************************************
   **  Juego de Demostracion *************
   *************************************************************************
   *   Juego creado por Mario Macias Lloret para el proyecto de fin        *
   * de carrera en ingenieria informatica: "Creacion de juegos para J2ME"  *
   * Universidad de Las Palmas de Gran Canaria                             *
   * Curso 2004-2005                                                       *
   *************************************************************************
*/


public class Fase1 extends Fase {
    //siguiente pixel donde aparecera algun elemento del array "objetos[]"
    private int siguiente;

    private int musicaFase1,musicaElvis;

    public Fase1() {
        super("/fase1.png","/fase1fondo.png");

        siguiente=objetos[OC++]*Pantalla.TILE_SIZE-Pantalla.getAnchoTablero();

        //pre-cachear los sonidos (cuidado! puede agotar toda la memoria)

        musicaFase1=GestorSonidos.singleton().cargaSonido("/fase1.mid","audio/midi",true);
        musicaElvis=GestorSonidos.singleton().cargaSonido("/elvis.mid","audio/midi",true);
        GestorSonidos.singleton().tocaSonido(musicaFase1);
    }

    public void finalizar() {
        GestorSonidos.singleton().paraSonido();
        GestorSonidos.singleton().eliminaSonidos();
    }

    private int OC=0; //contador de objetos
    public void mover() {
        super.mover();
        while(posx>=siguiente) {
            GestorElementos ge=GestorElementos.singleton();
            switch(objetos[OC++]) {
                case 0:
                    ge.anyade(new Caca(objetos[OC++]));
                    break;
                case 1:
                    ge.anyade(new Comecocos(objetos[OC++]));
                    break;
                case 2:
                    ge.anyade(new Cerdo());
                    break;
                case 3:
                    ge.anyade(new NotaMusical());
                    break;
                case 4:
                    ge.anyade(new Elvis(this));
                    GestorSonidos.singleton().tocaSonido(musicaElvis);
                    break;
            }
            siguiente=objetos[OC++]*Pantalla.TILE_SIZE-Pantalla.getAnchoTablero();
        }
    }

    protected byte[][] getMapaFrente() {
        return mapa;
    }

    protected byte[][] getMapaFondo() {
        return mapafondo;
    }

    //el formato es posicion (tile horiz), num. objeto, parametro 1, ... , parametro n
    private static final int objetos[] = {
        13,1,70,
        16,0,16,    20,0,32,    24,0,64,    28,0,96,    32,0,128,
        40,1,25,    43,1,50,    46,1,55,    49,0,85,    52,1,115,
        55,0,135,   58,1,80,    65,2,       70,2,       75,2,
        80,1,50,    80,1,80,    80,1,120,
        90,1,25,    90,1,55,    90,1,95,
        100,0,16,   102,0,32,   104,0,48,   106,0,64,   108,0,80,   110,0,96,   112,0,112,  114,0,128,  116,0,144,
        125,1,20,   125,1,40,   125,1,60,   125,1,80,   125,1,100,  125,1,120,  125,1,140,
        137,2,      140,2,      143,2,      146,2,      149,2,      152,2,
        156,3,      160,3,      164,3,      168,3,
        171,3,      174,3,      177,3,      180,3,
        182,3,      184,3,      185,3,      186,3,
        187,3,      188,3,      189,3,      190,3,
        195,1,70,   199,2,      203,1,70,   206,2,      209,1,70,   212,2,
        214,1,30,   216,2,      218,1,100,  220,2,      222,1,30,   224,2,      226,1,100,
        232,0,20,   233,0,120,  234,0,40,   235,0,100,  236,0,60,   237,0,80,   238,0,80,
        242,0,20,   242,1,40,   242,0,60,   242,1,80,   242,0,100,  242,1,120,

        255,4,
        1000
    };

    private static final byte mapa[][] =  //256x5
            {{ 1, 2, 1, 2, 1, 2, 1, 0, 0, 0, 0, 3, 4, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
              0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2, 1, 0, 0, 0, 0, 0, 0, 2, 1,
              0, 0, 0, 0, 2, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
              0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2, 1, 0, 0, 0, 0,
              0, 0, 2, 1, 0, 0, 0, 0, 0, 0, 2, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 3, 4, 0,
              0, 2, 1, 0, 0, 0, 0, 0, 0, 2, 1, 0, 0, 0, 0, 2, 1, 0, 0, 0, 0, 2, 1, 0, 0,
              0, 0, 0, 0, 2, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
              0, 0, 0, 0, 0, 0, 0, 0, 0, 3, 4, 0, 0, 0, 0, 0, 0, 3, 4, 0, 0, 0, 0, 0, 0,
              3, 4, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2, 1, 0, 0, 0,
              0, 2, 1, 0, 0, 0, 0, 2, 1, 0, 0, 2, 1, 0, 0, 0, 0, 0, 0, 0, 0, 2, 1, 0, 0,
              0, 0, 0, 0, 0, 0, 2},
            { 5, 6, 5, 6, 5, 6, 5, 2, 1, 0, 0, 7, 8, 0, 0, 2, 1, 0, 0, 0, 0, 0, 0, 0, 0,
              0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 6, 5, 2, 1, 0, 0, 2, 1, 6, 5, 3,
              4, 2, 1, 6, 5, 2, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
              0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 6, 5, 2, 1, 0, 0, 2,
              1, 6, 5, 2, 1, 0, 0, 2, 1, 6, 5, 0, 0, 3, 4, 0, 0, 0, 0, 0, 0, 7, 8, 2, 1,
              6, 5, 2, 1, 0, 0, 2, 1, 6, 5, 3, 4, 2, 1, 6, 5, 2, 1, 0, 0, 6, 5, 2, 1, 0,
              0, 2, 1, 6, 5, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
              0, 0, 0, 0, 0, 0, 0, 0, 7, 8, 3, 4, 0, 0, 3, 4, 7, 8, 3, 4, 0, 0, 3, 4, 7,
              8, 3, 4, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 6, 5, 3, 4, 2, 1,
              6, 5, 2, 1, 0, 0, 6, 5, 3, 4, 6, 5, 0, 0, 2, 1, 0, 0, 0, 0, 6, 5, 0, 0, 2,
              1, 0, 0, 0, 0, 6},
            { 9,10, 9,10, 9,10, 9, 6, 5, 2, 1,10, 9, 2, 1, 6, 5, 2, 1,11,12,11,12,11,12,
              0, 0,11,12,11,12,11,12, 0, 0,11,12,11,12,10, 9, 6, 5, 2, 1, 6, 5,10, 9, 7,
              8, 6, 5,10, 9, 6, 5, 2, 1,11,12,11,12,11,12, 0, 0,11,12,11,12,11,12, 0, 0,
             11,12,11,12,11,12,11,12,11,12, 0, 0,11,12,11,12,11,12,10, 9, 6, 5, 2, 1, 6,
              5,10, 9, 6, 5, 2, 1, 6, 5,10, 9, 0, 0, 7, 8, 0, 0, 3, 4, 0, 0,10, 9, 6, 5,
             10, 9, 6, 5, 2, 1, 6, 5,10, 9, 7, 8, 6, 5,10, 9, 6, 5, 2, 1,10, 9, 6, 5, 2,
              1, 6, 5,10, 9,11,12, 0, 0,11,12,11,12,11,12, 0, 0,11,12,11,12,11,12, 0, 0,
             11,12,11,12,11,12, 0, 0,10, 9, 7, 8, 3, 4, 7, 8,10, 9, 7, 8, 3, 4, 7, 8,10,
              9, 7, 8, 3, 4, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,10, 9, 7, 8, 6, 5,
             10, 9, 6, 5, 2, 1,10, 9, 7, 8,10, 9, 0, 0, 6, 5, 0, 0, 3, 4,10, 9, 0, 0, 6,
              5, 0, 0, 3, 4,10},
            {13,14,13,14,13,14,13,10, 9, 6, 5,14,13, 6, 5,10, 9, 6, 5,15,16,15,16,15,16,
             17,18,15,16,15,16,15,16,19,20,15,16,15,16,14,13,10, 9, 6, 5,10, 9,14,13,10,
              9,10, 9,14,13,10, 9, 6, 5,15,16,15,16,15,16,17,18,15,16,15,16,15,16,19,20,
             15,16,15,16,15,16,15,16,15,16,17,18,15,16,15,16,15,16,14,13,10, 9, 6, 5,10,
              9,14,13,10, 9, 6, 5,10, 9,14,13,17,18,10, 9,19,20, 7, 8,17,18,14,13,10, 9,
             14,13,10, 9, 6, 5,10, 9,14,13,10, 9,10, 9,14,13,10, 9, 6, 5,14,13,10, 9, 6,
              5,10, 9,14,13,15,16,17,18,15,16,15,16,15,16,19,20,15,16,15,16,15,16,17,18,
             15,16,15,16,15,16,19,20,14,13,10, 9, 7, 8,10, 9,14,13,10, 9, 7, 8,10, 9,14,
             13,10, 9, 7, 8,17,18,17,18,17,18,17,18,17,18,17,18,17,18,14,13,10, 9,10, 9,
             14,13,10, 9, 6, 5,14,13,10, 9,14,13,17,18,10, 9,19,20, 7, 8,14,13,17,18,10,
              9,19,20, 7, 8,14},
            {21,22,21,22,21,22,21,22,21,22,21,22,21,22,21,22,21,22,21,23,24,23,24,23,24,
             25,26,23,24,23,24,23,24,27,28,23,24,23,24,22,21,22,21,22,21,22,21,22,21,22,
             21,22,21,22,21,22,21,22,21,23,24,23,24,23,24,25,26,23,24,23,24,23,24,27,28,
             23,24,23,24,23,24,23,24,23,24,25,26,23,24,23,24,23,24,22,21,22,21,22,21,22,
             21,22,21,22,21,22,21,22,21,22,21,25,26,22,21,27,28,22,21,25,26,22,21,22,21,
             22,21,22,21,22,21,22,21,22,21,22,21,22,21,22,21,22,21,22,21,22,21,22,21,22,
             21,22,21,22,21,23,24,25,26,23,24,23,24,23,24,27,28,23,24,23,24,23,24,25,26,
             23,24,23,24,23,24,27,28,22,21,22,21,22,21,22,21,22,21,22,21,22,21,22,21,22,
             21,22,21,22,21,25,26,25,26,25,26,25,26,25,26,25,26,25,26,22,21,22,21,22,21,
             22,21,22,21,22,21,22,21,22,21,22,21,25,26,22,21,27,28,22,21,22,21,25,26,22,
             21,27,28,22,21,22}};

    private static final byte mapafondo[][]=   //75x10
      {{ 1, 1, 1, 1, 2, 3, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1,
        1, 2, 3, 1, 1, 1, 1, 1, 1, 1, 4, 5, 1, 1, 1, 1, 2, 3, 1, 1, 1, 1, 1, 1, 1,
        1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 2, 3, 1, 1,
        1},
      { 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 4, 5, 1, 1, 1, 1, 1, 1, 1, 1, 1,
        1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1,
        1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
      { 1, 6, 7, 8, 1, 9,10,11,12, 1, 1, 6, 7, 8, 1, 1, 6, 7, 8, 1, 1, 6, 7, 8, 1,
        1, 4, 5, 9,10,11,12, 1, 1, 6, 7, 8, 1, 1, 1, 1, 1, 6, 7, 8, 1, 1, 6, 7, 8,
        1, 4, 5,10,11,12, 1, 1, 6, 7, 8, 4, 5, 6, 7, 8, 1, 1, 6, 7, 8, 1, 9,10,11},
      { 9,13,14,15,16, 9,13,14,15,16, 9,13,14,15,16, 9,13,14,15,16, 9,13,14,15,16,
        1, 1, 1, 9,13,14,15,16, 9,13,14,15,16, 1, 1, 1,17,18,14,15,16, 9,13,14,15,
       16, 1, 9,13,14,15,16,17,18,14,15,16, 9,13,14,15,16,17,18,14,15,16, 9,13,14},
      {19,20,21,22,23,19,24,25,26,23,19,20,21,22,23,19,20,21,22,23,19,20,21,22,23,
        1, 1, 1,19,24,25,26,23,19,20,21,22,23,27,28, 1,29,30,21,22,23,19,20,21,22,
       23, 1,19,24,25,26,23,29,30,21,22,23,19,20,21,22,23,29,30,21,22,23,19,24,25},
      {31,32,33,34,35,31,32,33,34,35,31,32,33,34,36,37,32,33,34,36,37,32,33,34,35,
        1, 1, 1,31,32,33,34,36,37,32,33,34,35,38,39, 1,31,32,33,34,40,37,32,33,34,
       35, 1,31,32,33,34,40,37,32,33,34,36,37,32,33,34,36,37,32,33,34,35,31,32,33},
      {41,42,43,44,45,41,42,43,44,45,41,42,43,46,47,48,49,43,46,47,48,49,43,44,45,
        1, 1, 1,41,42,43,46,47,48,49,43,44,45, 1, 1, 1,41,42,43,46,50,48,49,43,44,
       45, 1,41,42,43,46,50,48,49,43,46,47,48,49,43,46,47,48,49,43,44,45,41,42,43},
      {51,52,53,54,55,51,52,53,54,55,51,52,53,56,57,58,59,53,56,57,58,59,53,54,55,
        1, 1, 1,51,52,53,56,57,58,59,53,54,55, 1, 4, 5,51,52,53,56,57,58,59,53,54,
       55, 1,51,52,53,56,57,58,59,53,56,57,58,59,53,56,57,58,59,53,54,55,51,52,53},
      {60,61,62,63,64,60,61,62,63,64,60,61,62,65,66,67,68,62,65,66,67,68,62,63,64,
        1, 1, 1,60,61,62,65,66,67,68,62,63,64, 1, 1, 1,60,61,62,65,66,67,68,62,63,
       64, 1,60,61,62,65,66,67,68,62,65,66,67,68,62,65,66,67,68,62,63,64,60,61,62},
      { 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1,
        1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1,
        1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1}};


}
