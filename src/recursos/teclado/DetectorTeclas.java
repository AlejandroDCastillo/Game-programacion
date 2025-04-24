package recursos.teclado;
import gamePanel.GamePanel;

import javax.swing.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class DetectorTeclas implements KeyListener {
    public boolean arriba;
    public boolean abajo;
    public boolean izquierda;
    public boolean derecha;
    public boolean menuBoolean=false;
    private GamePanel gp;

    public DetectorTeclas(GamePanel gamePanel) {
        this.gp = gamePanel;
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
            int tecla = e.getKeyCode();
        if (tecla == KeyEvent.VK_A) {
            izquierda = true;
        }
        if (tecla == KeyEvent.VK_D) {
            derecha = true;
        }
        if (tecla == KeyEvent.VK_W) {
            arriba = true;
        }
        if (tecla == KeyEvent.VK_S) {
            abajo = true;
        }
        if (tecla == KeyEvent.VK_E) {
            if (menuBoolean) {
                menuBoolean = false;
            }else if (!menuBoolean)
                menuBoolean = true;
        }

        if (tecla == KeyEvent.VK_ESCAPE) {
            if (gp.estadoJuego==gp.continuar) {
                gp.estadoJuego=gp.pausa;
            }else if (gp.estadoJuego==gp.pausa){
                gp.estadoJuego=gp.continuar;
            };
        }
        //tecla de menu
        if(gp.estadoJuego==gp.menuInicio){
            if (tecla == KeyEvent.VK_W) {
                    gp.getInterfaz().setNumeroMenu(gp.getInterfaz().getNumeroMenu()-1);
                if (gp.getInterfaz().getNumeroMenu() < 0) {
                    gp.getInterfaz().setNumeroMenu(2);
                }


            }
            if (tecla == KeyEvent.VK_S) {
                    gp.getInterfaz().setNumeroMenu(gp.getInterfaz().getNumeroMenu()+1);
                if (gp.getInterfaz().getNumeroMenu() > 2) {
                    gp.getInterfaz().setNumeroMenu(0);
                }
            }
            if(tecla== KeyEvent.VK_ENTER){
                if (gp.getInterfaz().getNumeroMenu() == 0) {
                    gp.pararMusica();
                    gp.empezarMusica(0);
                    gp.estadoJuego=1;
                }
                if (gp.getInterfaz().getNumeroMenu() == 1) {
                    //para cargar
                    gp.estadoJuego=3;
                }
                if (gp.getInterfaz().getNumeroMenu() == 2) {
                    System.exit(0);
                }
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        int tecla = e.getKeyCode();
        if (tecla == KeyEvent.VK_A) {
            izquierda = false;
        }
        if (tecla == KeyEvent.VK_D) {
            derecha = false;
        }
        if (tecla == KeyEvent.VK_W) {
            arriba = false;
        }
        if (tecla == KeyEvent.VK_S) {
            abajo = false;
        }
    }
}
