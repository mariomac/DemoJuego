package com.ulpgc.mmacias.fases;

import com.ulpgc.mmacias.util.GestorElementos;
import com.ulpgc.mmacias.util.Pantalla;
import javax.microedition.lcdui.Graphics;
import javax.microedition.lcdui.Image;
import javax.microedition.lcdui.game.LayerManager;
import javax.microedition.lcdui.game.TiledLayer;
import java.io.IOException;

/* ***************************************
   **  Juego de Demostracion *************
   *************************************************************************
   *   Juego creado por Mario Macias Lloret para el proyecto de fin        *
   * de carrera en ingenieria informatica: "Creacion de juegos para J2ME"  *
   * Universidad de Las Palmas de Gran Canaria                             *
   * Curso 2004-2005                                                       *
   *************************************************************************
*/


public abstract class Fase extends LayerManager {

    private boolean acabada;
    protected int posx=0;

    protected TiledLayer frente=null;
    protected TiledLayer fondo=null;

    public Fase(String imgMapaFrente,String imgMapaFondo) {
        super();        
        GestorElementos ge=GestorElementos.singleton();
        ge.iniciaDatos();
        this.append(ge.getProtagonista());
        acabada=false;
        setViewWindow(0,0,Pantalla.getAnchoPantalla(),Pantalla.getAltoTablero());

        byte mapaFrente[][]=getMapaFrente();
        try {
            frente=new TiledLayer(mapaFrente[0].length,mapaFrente.length,Image.createImage(imgMapaFrente),Pantalla.TILE_SIZE,Pantalla.TILE_SIZE);
        } catch(IOException e) {
            System.out.println("Error cargando imagen "+imgMapaFrente+": "+e.getMessage());
        }
        rellenarTiles(frente,mapaFrente);
        frente.setPosition(0,16*5);
        frente.setVisible(true);
        this.append(frente);

        byte mapaFondo[][]=getMapaFondo();
        try {
            fondo=new TiledLayer(mapaFondo[0].length,mapaFondo.length,Image.createImage(imgMapaFondo),Pantalla.TILE_SIZE,Pantalla.TILE_SIZE);
        } catch(IOException e) {
            System.out.println("Error cargando imagen "+imgMapaFondo+": "+e.getMessage());
        }
        rellenarTiles(fondo,mapaFondo);
        fondo.setPosition(0,0);
        fondo.setVisible(true);
        this.append(fondo);

        setViewWindow(0,0,Pantalla.getAnchoTablero(),Pantalla.getAltoTablero());
    }

    protected void rellenarTiles(TiledLayer tl,byte[][] tilemap) {
        for(int rows=0;rows<tilemap.length;rows++) {
            for(int cols=0;cols<tilemap[rows].length;cols++) {
                tl.setCell(cols,rows,tilemap[rows][cols]);
            }
        }
    }

    public void paint(Graphics g) {
        try {
            if(g!=null) {
                paint(g,0,0);
            }
        } catch(NullPointerException e) {
            System.out.println(e.getMessage());
        }
    }

    public void setAcabada(boolean acabada) {
        this.acabada=acabada;
    }
    public boolean isAcabada() {
        return acabada;
    }

    public void mover() {
         //Si no se ha llegado al final de la pantalla)
        if((posx+Pantalla.getAnchoTablero())<frente.getColumns()*Pantalla.TILE_SIZE-5) {
            posx+=3;
            //Semaforo para no cambiar la posicion de la pantalla mientras se esta dibujando
            //asi se evitan efectos indeseables
//            synchronized(this) {
                frente.setPosition(-posx,Pantalla.TILE_SIZE*5);
                fondo.setPosition(-posx/4,0);
//            }
        }
    };
    abstract public void finalizar();

    abstract protected byte[][] getMapaFrente();
    abstract protected byte[][] getMapaFondo();

}
