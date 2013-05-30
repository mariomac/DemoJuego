package com.ulpgc.mmacias.util;

import com.ulpgc.mmacias.fases.Fase;
import com.ulpgc.mmacias.items.personajes.Protagonista;

import javax.microedition.lcdui.Canvas;
import javax.microedition.lcdui.Graphics;
import javax.microedition.lcdui.Font;
import javax.microedition.lcdui.Image;
import javax.microedition.lcdui.game.GameCanvas;

/* ***************************************
   **  Juego de Demostracion *************
   *************************************************************************
   *   Juego creado por Mario Macias Lloret para el proyecto de fin        *
   * de carrera en ingenieria informatica: "Creacion de juegos para J2ME"  *
   * Universidad de Las Palmas de Gran Canaria                             *
   * Curso 2004-2005                                                       *
   *************************************************************************
*/


public class GestorGrafico extends GameCanvas {
    private GestorElementos ge=GestorElementos.singleton();
    private GestorJuego elGestorJuego=null;
    private Font fuente=null;
    Image marcador=null;
    private int puntosanteriores=0,vidasanteriores=0;

    private GestorGrafico() {
        super(false);
        fuente=Font.getFont(Font.FACE_PROPORTIONAL,Font.STYLE_BOLD,Font.SIZE_MEDIUM);
        marcador=Image.createImage(Pantalla.getAnchoPantalla(),Pantalla.getAltoPantalla()-Pantalla.getAltoTablero());
    }

    public GestorGrafico(GestorJuego gestorJuego) {
        this();
        elGestorJuego=gestorJuego;
        this.setCommandListener(gestorJuego);
    }
    
    public void paint(Graphics graphics) {
        Fase fase=ge.getFase();
        if(fase!=null) {
            fase.paint(graphics);
        } else {
            System.out.println("Fase es null");
        }

        if(elGestorJuego!=null) {

            //Hacemos esto para redibujar el �rea del marcador solo cuando �ste haya cambiado
            //As� ahorraremos recursos de procesador
            if(puntosanteriores!=elGestorJuego.getPuntos() || vidasanteriores!=elGestorJuego.getVidas()) {
                puntosanteriores=elGestorJuego.getPuntos();
                vidasanteriores=elGestorJuego.getVidas();
                int ant=Pantalla.getAnchoTablero();
                int anp=Pantalla.getAnchoPantalla();
                int alt=Pantalla.getAltoTablero();
                int alp=Pantalla.getAltoPantalla();

                graphics.setColor(30,30,200);
                graphics.fillRect(0,alt,anp,alp-alt);
                graphics.setColor(90,90,255);
                graphics.drawRect(0,alt,anp,alp-alt);

                graphics.setFont(fuente);
                graphics.setColor(255,255,255);
                graphics.drawString("Puntos: "+puntosanteriores,4,alt+2,Graphics.TOP|Graphics.LEFT);
                graphics.drawString("Vidas: "+vidasanteriores,anp-4,alt+2,Graphics.TOP|Graphics.RIGHT);
            }
        } else {
            System.out.println("elGestorJuego es null");
        }

    }

    protected void keyPressed(int i) {
        super.keyPressed(i);
        Protagonista prota=GestorElementos.singleton().getProtagonista();
        switch(getGameAction(i)) {
            case Canvas.UP:
                prota.addDireccion(Protagonista.UP);
                break;
            case Canvas.DOWN:
                prota.addDireccion(Protagonista.DOWN);
                break;
            case Canvas.LEFT:
                prota.addDireccion(Protagonista.LEFT);
                break;
            case Canvas.RIGHT:
                prota.addDireccion(Protagonista.RIGHT);
                break;
            case Canvas.FIRE:
                prota.dispara();
                break;
        }
    }

    protected void keyReleased(int i) {
        super.keyReleased(i);
        Protagonista prota=GestorElementos.singleton().getProtagonista();
        switch(getGameAction(i)) {
            case Canvas.UP:
                prota.deleteDireccion(Protagonista.UP);
                break;
            case Canvas.DOWN:
                prota.deleteDireccion(Protagonista.DOWN);
                break;
            case Canvas.LEFT:
                prota.deleteDireccion(Protagonista.LEFT);
                break;
            case Canvas.RIGHT:
                prota.deleteDireccion(Protagonista.RIGHT);
                break;
        }
    }
}
