package com.ulpgc.mmacias.util;

import com.ulpgc.mmacias.fases.Fase;
import com.ulpgc.mmacias.items.ObjetoMovil;
import com.ulpgc.mmacias.items.options.Option;
import com.ulpgc.mmacias.items.armas.Disparo;
import com.ulpgc.mmacias.items.personajes.Enemigo;
import com.ulpgc.mmacias.items.personajes.Protagonista;
import com.ulpgc.mmacias.DemoJuego;
import com.ulpgc.mmacias.util.sound.GestorSonidos;
import javax.microedition.lcdui.*;

/* ***************************************
   **  Juego de Demostracion *************
   *************************************************************************
   *   Juego creado por Mario Macias Lloret para el proyecto de fin        *
   * de carrera en ingenieria informatica: "Creacion de juegos para J2ME"  *
   * Universidad de Las Palmas de Gran Canaria                             *
   * Curso 2004-2005                                                       *
   *************************************************************************
*/


public class GestorJuego implements Runnable, CommandListener {
    private GestorElementos ge=GestorElementos.singleton();
    private GestorGrafico gg=new GestorGrafico(this);
    private DemoJuego midlet=null;

    private boolean letrero_puesto;
    private final static int FRAMES_SEGUNDO=10;

    private long tiempoactual,tiempoanterior;
    private long iteraciones;

    private int vidas;
    private int puntos;

    private int tiempo;
    private int musicaGameOver;

    private Integer sonido_tocando;

    private Command cmdContinuar=new Command("Continuar",Command.SCREEN,1);
    private Command cmdPausar=new Command("Pausar",Command.SCREEN,1);

    public GestorJuego(DemoJuego midlet) {
        this.midlet=midlet;
        Display.getDisplay(midlet).setCurrent(gg);
        tiempoanterior=System.currentTimeMillis();
        iteraciones=0;
        vidas=Configuracion.singleton().getVidasIniciales();
        letrero_puesto=false;
        puntos=0;
        //Pantalla.cachearDatos(midlet);
        //GestorSonidos.singleton().
    }

    public void run() {
        tiempoanterior=System.currentTimeMillis();
        iteraciones=0;
        //long frames=0;
        int estado=midlet.getEstado();
        while(estado==DemoJuego.ESTADO_JUEGO || estado==DemoJuego.ESTADO_GAMEOVER || estado==DemoJuego.ESTADO_PAUSA) {
            if(ge.getFase().isAcabada()) {
                ge.getFase().finalizar();
                midlet.setEstado(DemoJuego.ESTADO_FIN);
            } else {
                if(estado!=DemoJuego.ESTADO_PAUSA) {
                    mover();
                }
            }
            iteraciones++;

            //implementaci�n del frame skipping
            //queremos 15 frames por segundo, por lo tanto, se deber� llamar a la
            //funci�n mover() cada 1000/15 ms. Si no se puede alcanzar dicha cuota,
            //se omitir� la funci�n repaint (la mas lenta) hasta que pueda ser
            //alcanzada. Si se alcanza de sobras, haremos "dormir" al thread los segundos
            //sobrantes
            tiempoactual=System.currentTimeMillis();
            long idealiter=FRAMES_SEGUNDO*(tiempoactual-tiempoanterior)/1000;
            if(iteraciones>=idealiter) {
                gg.repaint();
                //frames++;
                try {
                    long tiemposiguiente=(iteraciones+1)*1000/FRAMES_SEGUNDO+tiempoanterior;
                    tiempoactual=System.currentTimeMillis();
                    //System.out.println("jar: "+(tiemposiguiente-tiempoactual));
                    long diferencia=tiemposiguiente-tiempoactual;
                    if(diferencia>0) {
                        Thread.sleep(diferencia);
                    }
                } catch(InterruptedException e) {
                    System.out.println(e.getMessage());
                }
            }
//            if(iteraciones%100==0) {
//                System.out.println("Tama�o cola objetos: "+ge.getSize());

//                System.out.println("-> "+(iteraciones*1000/(System.currentTimeMillis()-tiempoanterior))+" iteraciones/segundo");
//                System.out.println("     - "+(frames*1000/(System.currentTimeMillis()-tiempoanterior))+" frames/segundo");

//                tiempoanterior=System.currentTimeMillis();
//                frames=iteraciones=0;
//            }
            estado=midlet.getEstado();
        }
    }

    public void iniciaPartida() {
        midlet.setEstado(DemoJuego.ESTADO_JUEGO);
        GestorElementos.singleton().cargaFase(1);
        musicaGameOver=GestorSonidos.singleton().cargaSonido("/gameover.mid","audio/midi",false);
        Thread thread=new Thread(this);
        thread.setPriority(Thread.MAX_PRIORITY);
        thread.start();
    }

    public void commandAction(Command command, Displayable displayable) {
        if(command==cmdPausar) {
            midlet.setEstado(DemoJuego.ESTADO_PAUSA);
        } else if(command==cmdContinuar) {
            midlet.setEstado(DemoJuego.ESTADO_JUEGO);
        }
    }

    public void pausar(boolean pausa) {
        if(gg==null) return;
        if(pausa) {
            gg.removeCommand(cmdPausar);
            gg.addCommand(cmdContinuar);
            sonido_tocando=GestorSonidos.singleton().getIndexSonidoActual();
            GestorSonidos.singleton().paraSonido();
        } else {
            gg.removeCommand(cmdContinuar);
            gg.addCommand(cmdPausar);
            if(sonido_tocando!=null) {
                GestorSonidos.singleton().tocaSonido(sonido_tocando.intValue());
            }
        }
    }

    private class LetreroGameOver extends ObjetoMovil {
        public LetreroGameOver() {
            super(cargaImagen("/gameover.png"));
            this.setTipo(ObjetoMovil.TIPO_OTRO);
            setPosition(Pantalla.getAnchoTablero()/2-getWidth()/2,Pantalla.getAltoTablero()/2-getHeight()/2);

        }
        public void mover() {
        }        
    }

    private void mover() {
        addPuntos(GestorElementos.singleton().desacumulaPuntuacion());

        Protagonista prota=ge.getProtagonista();

        int estado=midlet.getEstado();
        if(estado!=DemoJuego.ESTADO_JUEGO &&
                (estado!=DemoJuego.ESTADO_GAMEOVER || !prota.isMuriendo())) {
            if(!letrero_puesto) {
                letrero_puesto=true;
                prota.setVisible(false);
                GestorElementos.singleton().anyade(new LetreroGameOver());
                GestorSonidos.singleton().tocaSonido(musicaGameOver);
                tiempo=0;
            } else {
                tiempo++;
                if(tiempo>98) {
                    midlet.setEstado(DemoJuego.ESTADO_PRESENTACION);
                }
            }
        } else {
            prota.mover();
        }


        Fase fase=ge.getFase();
        fase.mover();

        if(ge.reinicia(0)) {
            do {
                //comprobar las colisiones
                //es un poco lioso... se intenta minimizar al maximo el numero de comprobaciones
                ObjetoMovil obj1=ge.getObjetoMovil(0);
                int tipobj1=obj1.getTipo();
                switch(tipobj1) {
                    case ObjetoMovil.TIPO_ENEMIGO:
                        if(ge.reinicia(1)) {
                            do {
                                ObjetoMovil obj2=ge.getObjetoMovil(1);
                                if(obj2.getTipo()==ObjetoMovil.TIPO_DISPARO) {
                                    if(obj1.collidesWith(obj2,false)) {
                                        ((Enemigo) obj1).tocar((Disparo) obj2);
                                        obj2.matar();
                                        break;
                                    }
                                }
                            } while(ge.siguiente(1));
                        }

                        if(obj1.collidesWith(prota,false)) {
                            matarProtagonista();
                            break;
                        }
                        break;
                    case ObjetoMovil.TIPO_BALAMALA:
                        if(obj1.collidesWith(prota,false)) {
                            obj1.matar();
                            matarProtagonista();
                            break;
                        }
                        break;
                    case ObjetoMovil.TIPO_OPTION:
                        if(obj1.collidesWith(prota,false)) {
                            ((Option) obj1).actua();
                        }
                }
                obj1.mover();
            } while(ge.siguiente(0));
        }
    }

    private void matarProtagonista() {
        Protagonista prota=ge.getProtagonista();
        if(prota.puedeMorir()) {
            prota.matar();
            if(--vidas<=0) {
                gg.removeCommand(cmdPausar);
                gg.removeCommand(cmdContinuar);
                midlet.setEstado(DemoJuego.ESTADO_GAMEOVER);
            }
        }
    }

    public int getPuntos() {
        return puntos;
    }

    private void addPuntos(int puntos) {
        this.puntos+=puntos;
    }

    public int getVidas() {
        return vidas;
    }

}
