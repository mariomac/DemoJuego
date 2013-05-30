package com.ulpgc.mmacias;

import com.ulpgc.mmacias.util.Configuracion;
import javax.microedition.lcdui.*;
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


public class Presentacion implements CommandListener {
    
    public final static int ESTADO_IMAGEN=0;
    public final static int ESTADO_PRINCIPAL=1;
    public final static int ESTADO_OPCIONES=2;
    public final static int ESTADO_AYUDA=3;

    private int estado;
    private DemoJuego midlet=null;

    public final static String OPCION_JUGAR="Jugar";
    public final static String OPCION_OPCIONES="Opciones";

    private String[] opcionesMenu={OPCION_JUGAR,OPCION_OPCIONES};
    List principal=null;

    private Presentacion() {
        principal=new List("Men� principal",List.IMPLICIT,opcionesMenu,null);
        principal.setCommandListener(this);
    }
    public Presentacion(DemoJuego midlet) {
        this();
        this.midlet=midlet;
    }

    public void setEstado(int nuevoestado) {
        estado=nuevoestado;
        switch(estado) {
            case ESTADO_IMAGEN:
                Display.getDisplay(midlet).setCurrent(new ImagenPresentacion());
                break;
            case ESTADO_PRINCIPAL:
                Display.getDisplay(midlet).setCurrent(principal);
                break;
            case ESTADO_OPCIONES:
                Display.getDisplay(midlet).setCurrent(new FormularioOpciones());
                break;
        }
    }

    public void commandAction(Command command, Displayable displayable) {
        if(displayable==principal) {
            if(command==List.SELECT_COMMAND) {
                String label=principal.getString(principal.getSelectedIndex());
                if(label.equals(OPCION_JUGAR)) {
                    midlet.iniciaPartida();
                } else if(label.equals(OPCION_OPCIONES)) {
                    setEstado(ESTADO_OPCIONES);
                }
            }
        }
    }

    private class FormularioOpciones extends Form implements ItemStateListener , CommandListener {
        private ChoiceGroup sonido;
        private ChoiceGroup vidas;

        public FormularioOpciones() {
            super("Formulario de opciones");
            Configuracion config=Configuracion.singleton();

            sonido=new ChoiceGroup("Sonido",Choice.POPUP,new String[]{"On", "Off"},null);
            sonido.setSelectedIndex(0,config.isMusic());
            sonido.setSelectedIndex(1,!config.isMusic());
            this.append(sonido);

            vidas=new ChoiceGroup("Numero de vidas",Choice.POPUP,new String[]{"3","5","99"},null);
            switch(config.getVidasIniciales()) {
                case 3:
                    vidas.setSelectedIndex(0,true);
                    break;
                case 5:
                    vidas.setSelectedIndex(1,true);
                    break;
                default:
                    vidas.setSelectedIndex(2,true);
                    break;
            }
            this.append(vidas);

            this.addCommand(new Command("Volver",Command.SCREEN,1));
            this.setItemStateListener(this);
            this.setCommandListener(this);
        }

        public void itemStateChanged(Item item) {
            Configuracion config=Configuracion.singleton();
            if(item.getLabel().equals("Sonido")) {
                ChoiceGroup cg=(ChoiceGroup) item;
                config.setMusic(cg.getSelectedIndex()==0);
            } else if(item.getLabel().equals("Numero de vidas")) {
                ChoiceGroup cg=(ChoiceGroup) item;
                config.setVidasIniciales(Integer.parseInt(cg.getString(cg.getSelectedIndex())));
            }
        }

        public void commandAction(Command command, Displayable displayable) {
            if(command.getLabel().equals("Volver")) {
                setEstado(ESTADO_PRINCIPAL);
            }
        }
    }

    //Clases auxiliares para la presentaci�n
    private class ImagenPresentacion extends Canvas {
        private Image imagen_presentacion=null;
        public ImagenPresentacion() {
            try {
                imagen_presentacion=Image.createImage("/presentacion.png");
            } catch(IOException e) {
                System.err.println("Error cargando imagen_presentacion 'presentacion.png': "+e.getMessage());
            }
        }
        protected void paint(Graphics graphics) {
            graphics.setColor(210,180,255);
            graphics.fillRect(0,0,getWidth(),getHeight());
            graphics.drawImage(imagen_presentacion,getWidth()/2,getHeight()/2,Graphics.VCENTER|Graphics.HCENTER);
        }
        protected void keyPressed(int i) {
            super.keyPressed(i);
            if(estado==ESTADO_IMAGEN) {
                setEstado(ESTADO_PRINCIPAL);
            }
        }
    }
}
