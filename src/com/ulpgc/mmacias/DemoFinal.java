package com.ulpgc.mmacias;

import com.ulpgc.mmacias.util.Utils;
import com.ulpgc.mmacias.util.sound.GestorSonidos;
import javax.microedition.lcdui.*;
import javax.microedition.lcdui.Canvas;
import javax.microedition.lcdui.Graphics;
import javax.microedition.lcdui.Font;
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


public class DemoFinal implements CommandListener {

    private DemoJuego midlet;

    private Pantalla pantalla;

    private Image imagen;

    public DemoFinal(DemoJuego midlet) {
        this.midlet=midlet;
        try {
            imagen=Image.createImage("/final.png");
        } catch(IOException e) {
            System.out.println("Error cargando imagen final.png: "+e.getMessage());
        }

        Letreros letreros=new Letreros();
        pantalla=letreros;
        Display.getDisplay(midlet).setCurrent(letreros);
        letreros.setCommandListener(this);
        letreros.addCommand(new Command("Finalizar",Command.SCREEN,1));
        int index=GestorSonidos.singleton().cargaSonido("/final.mid","audio/midi",false);
        GestorSonidos.singleton().tocaSonido(index);
        Thread thread=new Thread(letreros);
        thread.run();
    }

    private abstract class Pantalla extends Canvas {
        protected boolean salir;

        public Pantalla() {
            salir=false;
        }

        public void salir() {
            salir=true;
        }

    }

    private class Letreros extends Pantalla implements Runnable {
        private final int numero=30;
        private int color[],x[],y[],incx[],incy[];

        private int colfondo,inccolfondo;
        public Letreros() {
            super();
            color=new int[numero];
            x=new int[numero];
            y=new int[numero];
            incx=new int[numero];
            incy=new int[numero];

            //inicializar datos de los graficos
            colfondo=0;inccolfondo=5;
            for(int i=0;i<numero;i++) {
                color[i]=Utils.random(255*255*255);
                x[i]=Utils.random(getWidth());
                y[i]=Utils.random(getHeight());
                incx[i]=Utils.random(7)-4;
                incy[i]=Utils.random(7)-4;
            }
        }

        protected void paint(Graphics graphics) {
            graphics.setColor(128,255-colfondo,colfondo);
            graphics.fillRect(0,0,getWidth(),getHeight());
            graphics.setFont(Font.getFont(Font.FACE_PROPORTIONAL,Font.STYLE_BOLD,Font.SIZE_MEDIUM));
            for(int i=0;i<numero;i++) {
                graphics.setColor(color[i]);
                graphics.drawString("Fin",x[i],y[i],Graphics.HCENTER|Graphics.TOP);
            }

            graphics.setColor(196-colfondo/2,64+colfondo/2,128);
            graphics.setFont(Font.getFont(Font.FACE_SYSTEM,Font.STYLE_BOLD,Font.SIZE_LARGE));
            graphics.drawString("�Felicidades!",getWidth()/2,10,Graphics.HCENTER|Graphics.TOP);

            graphics.drawImage(imagen,getWidth()/2,getHeight()/2,Graphics.HCENTER|Graphics.VCENTER);
        }


        public void run() {
            while(!salir) {
                colfondo+=inccolfondo;
                if(colfondo>=255) {
                    inccolfondo=-inccolfondo;
                    colfondo=255;
                } else if(colfondo<=0) {
                    inccolfondo=-inccolfondo;
                    colfondo=0;
                }

                for(int i=0;i<numero;i++) {
                    x[i]+=incx[i];
                    if(x[i]<0||x[i]>getWidth()) {
                        incx[i]=-incx[i];
                    }
                    y[i]+=incy[i];
                    if(y[i]<0||y[i]>getHeight()) {
                        incy[i]=-incy[i];
                    }
                }
                repaint();
                try {
                    Thread.sleep(30);
                } catch(Exception e) {
                    System.out.println(e);
                }
            }
            midlet.setEstado(DemoJuego.ESTADO_PRESENTACION);
        }
    }

    public void commandAction(Command command, Displayable displayable) {
        if(command.getCommandType()==Command.SCREEN) {
            GestorSonidos.singleton().eliminaSonidos();
            pantalla.salir();
        }
    }
}
