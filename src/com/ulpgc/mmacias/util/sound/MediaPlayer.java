package com.ulpgc.mmacias.util.sound;

import javax.microedition.media.*;
import java.io.IOException;

/* ***************************************
   **  Juego de Demostración *************
   *************************************************************************
   *   Juego creado por Mario Macías Lloret para el proyecto de fín        *
   * de carrera en ingeniería informática: "Creación de juegos para J2ME"  *
   * Universidad de Las Palmas de Gran Canaria                             *
   * Curso 2004-2005                                                       *
   *************************************************************************
*/

public class MediaPlayer implements Player {
    private Player thePlayer=null;

    private long mediaTime; // La idea es pre-cachear el mediaTime y la duración al
                            // inicio de la pantalla, ya que su cálculo requiere muchisimo tiempo
    private long duration;

    public MediaPlayer(String filename, String contenttype, boolean repeat) {
        try {
            thePlayer=Manager.createPlayer(getClass().getResourceAsStream(filename),contenttype);
            mediaTime=thePlayer.getMediaTime();
            duration=thePlayer.getDuration();
            if(repeat) {
                thePlayer.addPlayerListener(
                    new PlayerListener() {
                        public void playerUpdate(Player player, String s, Object o) {
                            if(s.equals(PlayerListener.END_OF_MEDIA)) {
                                try {
                                    player.start();
                                } catch(MediaException e) {
                                    System.out.println("Error re-tocando la musiqueti: "+e.getMessage());
                                }
                            }
                        }
                    }
                );
            }
        } catch(MediaException e) {
            System.out.println(filename+" - El Player no puede ser creado para dicho Stream y tipo: "+e.getMessage());
        } catch(IOException e) {
            System.out.println(filename+" - Hubo un problema leyendo los datos del InputStream: "+e.getMessage());
        }

    }

    public void start() {
        try {
            thePlayer.start();
        } catch(MediaException e) {
            System.out.println("Error tocando la musiqueti: "+e.getMessage());
        }
    }

    public Control[] getControls() {
        return thePlayer.getControls();
    }

    public Control getControl(String s) {
        return thePlayer.getControl(s);
    }

    public void realize() throws MediaException {
        thePlayer.realize();
    }

    public void prefetch() throws MediaException {
        thePlayer.prefetch();
    }

    public void stop() {
        try {
            thePlayer.stop();
        } catch(MediaException e) {
            System.out.println("Error parando la musica: "+e.getMessage());
        }
    }

    public void deallocate() {
        thePlayer.deallocate();
    }

    public void close() {
        thePlayer.close();
    }

    public void setTimeBase(TimeBase timeBase) throws MediaException {
        thePlayer.setTimeBase(timeBase);
    }

    public TimeBase getTimeBase() {
        return thePlayer.getTimeBase();
    }

    public long setMediaTime(long l) throws MediaException {
        return thePlayer.setMediaTime(l);
    }

    public long getMediaTime() {
        return mediaTime;
    }

    public int getState() {
        return thePlayer.getState();
    }

    public long getDuration() {
        return duration;
    }

    public String getContentType() {
        return thePlayer.getContentType();
    }

    public void setLoopCount(int i) {
        thePlayer.setLoopCount(i);
    }

    public void addPlayerListener(PlayerListener playerListener) {
        thePlayer.addPlayerListener(playerListener);
    }

    public void removePlayerListener(PlayerListener playerListener) {
        thePlayer.removePlayerListener(playerListener);
    }
}
