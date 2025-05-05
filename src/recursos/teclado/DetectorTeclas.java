package recursos.teclado;
import entidades.Clase;
import entidades.Jugador;
import entidades.Raza;
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
    public StringBuilder textoIngresado=new StringBuilder();
    Raza raza = null;
    Clase clase = null;
    private GamePanel gp;

    public DetectorTeclas(GamePanel gamePanel) {
        this.gp = gamePanel;
    }
    @Override
    public void keyTyped(KeyEvent e) {
        if(gp.estadoJuego== gp.menuInicio){
            if(gp.getInterfaz().getPantallaDelTitulo() == 3){
                char c = e.getKeyChar();
                // Permitir letras, números, espacio y borrar
                if (Character.isLetter(c)) {
                    textoIngresado.append(c);
                } else if (c == '\b' && !textoIngresado.isEmpty()) {
                    textoIngresado.deleteCharAt(textoIngresado.length() - 1);
                }
                if (c == '\n'&& !textoIngresado.isEmpty()){
                    gp.getInterfaz().setPantallaDelTitulo(4);
                }
            }
        }
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
            if (gp.estadoJuego==gp.continuar) {
                if (menuBoolean) {
                    menuBoolean = false;
                } else if (!menuBoolean)
                    menuBoolean = true;
            }
            }

        //memu de pausa
        if (gp.estadoJuego == gp.pausa) {
            if (tecla == KeyEvent.VK_W) {
                gp.getInterfaz().setNumeroMenu(gp.getInterfaz().getNumeroMenu() - 1);
                if (gp.getInterfaz().getNumeroMenu() < 0) {
                    gp.getInterfaz().setNumeroMenu(2);
                }
            }
            if (tecla == KeyEvent.VK_S) {
                gp.getInterfaz().setNumeroMenu(gp.getInterfaz().getNumeroMenu() + 1);
                if (gp.getInterfaz().getNumeroMenu() > 2) {
                    gp.getInterfaz().setNumeroMenu(0);
                }
            }
            if (tecla == KeyEvent.VK_ENTER) {
                if (gp.getInterfaz().getNumeroMenu() == 0) {
                    //PARA GUARDAR PARTIDA
                }
                if (gp.getInterfaz().getNumeroMenu() == 1) {
                    if(gp.getMusica().getEstado()){
                        gp.pararMusica();
                    }else{
                        gp.empezarMusica(0);
                    }
                }
                if (gp.getInterfaz().getNumeroMenu() == 2) {
                    System.exit(0);
                }
            }
        }
        //tecla pausa
        if (tecla == KeyEvent.VK_ESCAPE) {
            if (gp.estadoJuego == gp.continuar) {
                gp.estadoJuego = gp.pausa;
            } else if (gp.estadoJuego == gp.pausa) {
                gp.estadoJuego = gp.continuar;
            }
        }
        //tecla de menu
        if (gp.estadoJuego == gp.menuInicio) {

            if (gp.getInterfaz().getPantallaDelTitulo() == 0) {
                if (tecla == KeyEvent.VK_W) {
                    gp.getInterfaz().setNumeroMenu(gp.getInterfaz().getNumeroMenu() - 1);
                    if (gp.getInterfaz().getNumeroMenu() < 0) {
                        gp.getInterfaz().setNumeroMenu(2);
                    }
                }
                if (tecla == KeyEvent.VK_S) {
                    gp.getInterfaz().setNumeroMenu(gp.getInterfaz().getNumeroMenu() + 1);
                    if (gp.getInterfaz().getNumeroMenu() > 2) {
                        gp.getInterfaz().setNumeroMenu(0);
                    }
                }
                if (tecla == KeyEvent.VK_ENTER) {
                    if (gp.getInterfaz().getNumeroMenu() == 0) {
                        gp.getInterfaz().pantallaDelTitulo=1;
                    }
                    if (gp.getInterfaz().getNumeroMenu() == 1) {
                        //para cargar
                        gp.estadoJuego = 3;
                    }
                    if (gp.getInterfaz().getNumeroMenu() == 2) {
                        System.exit(0);
                    }
                }
            }
            else if (gp.getInterfaz().getPantallaDelTitulo() == 1) {

                if (tecla == KeyEvent.VK_W) {
                    gp.getInterfaz().setNumeroMenu(gp.getInterfaz().getNumeroMenu() - 1);
                    if (gp.getInterfaz().getNumeroMenu() < 0) {
                        gp.getInterfaz().setNumeroMenu(4);
                    }
                }
                if (tecla == KeyEvent.VK_S) {
                    gp.getInterfaz().setNumeroMenu(gp.getInterfaz().getNumeroMenu() + 1);
                    if (gp.getInterfaz().getNumeroMenu() > 4) {
                        gp.getInterfaz().setNumeroMenu(0);
                    }
                }
                if (tecla == KeyEvent.VK_ENTER) {
                    if (gp.getInterfaz().getNumeroMenu() == 0) {
                        clase = Clase.MAGO;
                        gp.getInterfaz().pantallaDelTitulo=2;

                    }
                    if (gp.getInterfaz().getNumeroMenu() == 1) {
                        clase = Clase.GUERRERO;
                        gp.getInterfaz().pantallaDelTitulo=2;

                    }
                    if (gp.getInterfaz().getNumeroMenu() == 2) {
                        clase = Clase.CLERIGO;
                        gp.getInterfaz().pantallaDelTitulo=2;
                    }
                    if (gp.getInterfaz().getNumeroMenu() == 3) {
                        clase = Clase.PICARO;
                        gp.getInterfaz().pantallaDelTitulo=2;

                    }
                    if (gp.getInterfaz().getNumeroMenu() == 4) {
                        System.exit(0);
                    }
                }
            }else if (gp.getInterfaz().getPantallaDelTitulo() == 2) {
                if (tecla == KeyEvent.VK_W) {
                    gp.getInterfaz().setNumeroMenu(gp.getInterfaz().getNumeroMenu() - 1);
                    if (gp.getInterfaz().getNumeroMenu() < 0) {
                        gp.getInterfaz().setNumeroMenu(4);
                    }
                }
                if (tecla == KeyEvent.VK_S) {
                    gp.getInterfaz().setNumeroMenu(gp.getInterfaz().getNumeroMenu() + 1);
                    if (gp.getInterfaz().getNumeroMenu() > 4) {
                        gp.getInterfaz().setNumeroMenu(0);
                    }
                }
                if (tecla == KeyEvent.VK_ENTER) {
                    if (gp.getInterfaz().getNumeroMenu() == 0) {
                        raza = Raza.HUMANO;
                        gp.getInterfaz().pantallaDelTitulo = 3;

                    }
                    if (gp.getInterfaz().getNumeroMenu() == 1) {
                        raza = Raza.ORCO;
                        gp.getInterfaz().pantallaDelTitulo = 3;
                    }
                    if (gp.getInterfaz().getNumeroMenu() == 2) {
                        raza = Raza.ELFO;
                        gp.getInterfaz().pantallaDelTitulo = 3;
                    }
                    if (gp.getInterfaz().getNumeroMenu() == 3) {
                        raza = Raza.ENANO;
                        gp.getInterfaz().pantallaDelTitulo = 3;

                    }
                    if (gp.getInterfaz().getNumeroMenu() == 4) {
                        System.exit(0);
                    }
                }
            }else if (gp.getInterfaz().getPantallaDelTitulo() == 4) {
                if (tecla == KeyEvent.VK_A) {
                    gp.getInterfaz().setNumeroMenu(gp.getInterfaz().getNumeroMenu() - 1);
                    if (gp.getInterfaz().getNumeroMenu() < 0) {
                        gp.getInterfaz().setNumeroMenu(1);
                    }
                }
                if (tecla == KeyEvent.VK_D) {
                    gp.getInterfaz().setNumeroMenu(gp.getInterfaz().getNumeroMenu() + 1);
                    if (gp.getInterfaz().getNumeroMenu() > 1) {
                        gp.getInterfaz().setNumeroMenu(0);
                    }
                }
                if (tecla == KeyEvent.VK_ENTER) {
                    if (gp.getInterfaz().getNumeroMenu() == 0) {
                        gp.setJugador(new Jugador(gp.getTeclado(), gp, textoIngresado.toString(), raza, clase, 1));
                        gp.estadoJuego=gp.continuar;
                    }
                    if (gp.getInterfaz().getNumeroMenu() == 1) {
                        gp.getInterfaz().pantallaDelTitulo = 3;
                        textoIngresado.delete(0, textoIngresado.length());
                    }
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
