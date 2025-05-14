package recursos.teclado;

import entidades.Clase;
import entidades.Entidad;
import entidades.Jugador;
import entidades.Raza;
import gamePanel.GamePanel;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Scanner;

public class DetectorTeclas implements KeyListener {
    public boolean arriba;
    public boolean abajo;
    public boolean izquierda;
    public boolean derecha;
    public boolean enterPulsado=false;
    public boolean menuBoolean = false;
    public boolean consumible=true;
    public boolean atacar=false;
    public boolean huir=false;
    //boolean que esconde el desplegable de crafteos
    public boolean craftear = false;
    //bolean para desplegable de equipar
    public boolean menuEquipar = false;
    public StringBuilder textoIngresado = new StringBuilder();
    Raza raza = null;
    Clase clase = null;
    private GamePanel gp;

    public DetectorTeclas(GamePanel gamePanel) {
        this.gp = gamePanel;
    }

    @Override
    public void keyTyped(KeyEvent e) {
        if (gp.estadoJuego == gp.menuInicio) {
            if (gp.getInterfaz().getPantallaDelTitulo() == 3) {
                char c = e.getKeyChar();
                // Permitir letras, números, espacio y borrar
                if (Character.isLetter(c)) {
                    textoIngresado.append(c);//suma el caracter al String final
                } else if (c == '\n' && !textoIngresado.isEmpty()) {
                    gp.getInterfaz().setPantallaDelTitulo(4);
                } else if (!Character.isLetter(c) && !textoIngresado.isEmpty()) {
                    textoIngresado.deleteCharAt(textoIngresado.length() - 1);
                }
            }
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {
        //movimiento
        int tecla = e.getKeyCode();
        if (gp.estadoJuego == gp.continuar) {
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
            if (tecla == KeyEvent.VK_ENTER) {
                enterPulsado = true;
            }

        }
        //inventario
        if (tecla == KeyEvent.VK_E) {
            if (gp.estadoJuego == gp.continuar) {
                menuBoolean = true;
                gp.estadoJuego = gp.inventario;
            } else if (gp.estadoJuego == gp.inventario) {
                menuBoolean = false;
                gp.estadoJuego = gp.continuar;
            }
        }
        //menus inventario
        if (gp.estadoJuego == gp.inventario) {
            if (!craftear && !menuEquipar) {
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
                    //PARA craftear
                    if (gp.getInterfaz().numeroMenu == 0) {
                        System.out.println("craftear");
                        if (craftear) {
                            craftear = false;
                        } else if (!craftear) {
                            craftear = true;
                        }
                    }
                    //para equipar
                    if (gp.getInterfaz().numeroMenu == 1) {
                        if (menuEquipar) {
                            menuEquipar = false;
                        } else if (!menuEquipar) {
                            menuEquipar = true;
                        }
                    }
                    //para destruir
                    if (gp.getInterfaz().numeroMenu == 2) {
                        //para destruir
                        System.out.println("destruir");
                    }
                }
            } else if (craftear && !menuEquipar) {
                if (tecla == KeyEvent.VK_W) {
                    gp.getInterfaz().setNumeroMenu(gp.getInterfaz().getNumeroMenu() - 1);
                    if (gp.getInterfaz().getNumeroMenu() < 0) {
                        gp.getInterfaz().setNumeroMenu(10);
                    }
                }
                if (tecla == KeyEvent.VK_S) {
                    gp.getInterfaz().setNumeroMenu(gp.getInterfaz().getNumeroMenu() + 1);
                    if (gp.getInterfaz().numeroMenu > 10) {
                        gp.getInterfaz().setNumeroMenu(0);
                    }
                }
                if (tecla == KeyEvent.VK_ENTER) {
                    boolean crafteo;
                    switch (gp.getInterfaz().getNumeroMenu()) {
                        case 0: {
                            crafteo = gp.getJugador().craftear("escudo");
                            //mensaje (no se porq no sale hayq ue arreglarlo)
                            if (crafteo) {
                                gp.getInterfaz().dibujarTextoSombreado("Crafteo realizado con existo", 150, 50, 35);
                            } else {
                                gp.getInterfaz().dibujarTextoSombreado("Crafteo no realizado", 150, 50, 35);
                            }
                            break;
                        }
                        case 1: {
                            crafteo = gp.getJugador().craftear("escudoOro");
                            if (crafteo) {
                                gp.getInterfaz().dibujarTextoSombreado("Crafteo realizado con existo", 150, 100, 35);
                            } else {
                                gp.getInterfaz().dibujarTextoSombreado("Crafteo no realizado", 150, 100, 35);
                            }
                            break;
                        }
                        case 2: {
                            crafteo = gp.getJugador().craftear("espada");
                            if (crafteo) {
                                gp.getInterfaz().dibujarTextoSombreado("Crafteo realizado con existo", 150, 100, 35);
                            } else {
                                gp.getInterfaz().dibujarTextoSombreado("Crafteo no realizado", 150, 100, 35);
                            }
                            ;
                        }
                        case 3: {
                            crafteo = gp.getJugador().craftear("espadaFuego");
                            if (crafteo) {
                                gp.getInterfaz().dibujarTextoSombreado("Crafteo realizado con existo", 150, 100, 35);
                            } else {
                                gp.getInterfaz().dibujarTextoSombreado("Crafteo no realizado", 150, 100, 35);
                            }
                            break;
                        }
                        case 4: {
                            crafteo = gp.getJugador().craftear("varamago");
                            if (crafteo) {
                                gp.getInterfaz().dibujarTextoSombreado("Crafteo realizado con existo", 150, 100, 35);
                            } else {
                                gp.getInterfaz().dibujarTextoSombreado("Crafteo no realizado", 150, 100, 35);
                            }
                            break;
                        }
                        case 5: {
                            crafteo = gp.getJugador().craftear("talismanSecreto");
                            if (crafteo) {
                                gp.getInterfaz().dibujarTextoSombreado("Crafteo realizado con existo", 150, 100, 35);
                            } else {
                                gp.getInterfaz().dibujarTextoSombreado("Crafteo no realizado", 150, 100, 35);
                            }
                            break;
                        }
                        case 6: {
                            crafteo = gp.getJugador().craftear("yelmo");
                            if (crafteo) {
                                gp.getInterfaz().dibujarTextoSombreado("Crafteo realizado con existo", 150, 100, 35);
                            } else {
                                gp.getInterfaz().dibujarTextoSombreado("Crafteo no realizado", 150, 100, 35);
                            }
                            break;
                        }
                        case 7: {
                            crafteo = gp.getJugador().craftear("peto");
                            if (crafteo) {
                                gp.getInterfaz().dibujarTextoSombreado("Crafteo realizado con existo", 150, 100, 35);
                            } else {
                                gp.getInterfaz().dibujarTextoSombreado("Crafteo no realizado", 150, 100, 35);
                            }
                            break;
                        }
                        case 8: {
                            crafteo = gp.getJugador().craftear("oro");
                            if (crafteo) {
                                gp.getInterfaz().dibujarTextoSombreado("Crafteo realizado con existo", 150, 50, 35);
                            } else {
                                gp.getInterfaz().dibujarTextoSombreado("Crafteo no realizado", 150, 50, 35);
                            }
                            break;
                        }
                        case 9: {
                            crafteo = gp.getJugador().craftear("hierro");
                            if (crafteo) {
                                gp.getInterfaz().dibujarTextoSombreado("Crafteo realizado con existo", 150, 50, 35);
                            } else {
                                gp.getInterfaz().dibujarTextoSombreado("Crafteo no realizado", 150, 50, 35);
                            }
                            break;
                        }
                        case 10: {
                            craftear = false;
                            break;
                        }
                    }
                }
            } else if (!craftear && menuEquipar) {
                if (tecla == KeyEvent.VK_W) {
                    gp.getInterfaz().setNumeroMenu(gp.getInterfaz().getNumeroMenu() - 1);
                    if (gp.getInterfaz().getNumeroMenu() < 0) {
                        gp.getInterfaz().setNumeroMenu(8);
                    }
                }
                if (tecla == KeyEvent.VK_S) {
                    gp.getInterfaz().setNumeroMenu(gp.getInterfaz().getNumeroMenu() + 1);
                    if (gp.getInterfaz().numeroMenu > 8) {
                        gp.getInterfaz().setNumeroMenu(0);
                    }
                }
                if (tecla == KeyEvent.VK_ENTER) {
                    boolean equipar;
                    switch (gp.getInterfaz().numeroMenu){
                        case 0:
                            equipar=gp.getJugador().equiparObjeto("escudo");
                            if (equipar) {
                                System.out.println("Equipadoo");
                                gp.getInterfaz().dibujarTextoSombreado("Se ha equipado", 150, 50, 35);
                            } else {
                                System.out.println("No equipado");
                                gp.getInterfaz().dibujarTextoSombreado("No se ha equipado", 150, 50, 35);
                            }
                            break;
                        case 1:
                            equipar=gp.getJugador().equiparObjeto("escudoOro");
                            if (equipar) {
                                gp.getInterfaz().dibujarTextoSombreado("Se ha equipado", 150, 50, 35);
                            } else {
                                gp.getInterfaz().dibujarTextoSombreado("No se ha equipado", 150, 50, 35);
                            }
                            break;
                        case 2:
                            equipar=gp.getJugador().equiparObjeto("espada");
                            if (equipar) {
                                gp.getInterfaz().dibujarTextoSombreado("Se ha equipado", 150, 50, 35);
                            } else {
                                gp.getInterfaz().dibujarTextoSombreado("No se ha equipado", 150, 50, 35);
                            }
                            break;
                        case 3:
                            equipar=gp.getJugador().equiparObjeto("espadaFuego");
                            if (equipar) {
                                gp.getInterfaz().dibujarTextoSombreado("Se ha equipado", 150, 50, 35);
                            } else {
                                gp.getInterfaz().dibujarTextoSombreado("No se ha equipado", 150, 50, 35);
                            }
                            break;
                        case 4:
                            equipar=gp.getJugador().equiparObjeto("varaMago");
                            if (equipar) {
                                gp.getInterfaz().dibujarTextoSombreado("Se ha equipado", 150, 50, 35);
                            } else {
                                gp.getInterfaz().dibujarTextoSombreado("No se ha equipado", 150, 50, 35);
                            }
                            break;
                        case 5:
                            equipar=gp.getJugador().equiparObjeto("talismanSecreto");
                            if (equipar) {
                                gp.getInterfaz().dibujarTextoSombreado("Se ha equipado", 150, 50, 35);
                            } else {
                                gp.getInterfaz().dibujarTextoSombreado("No se ha equipado", 150, 50, 35);
                            }
                            break;
                        case 6:
                            equipar=gp.getJugador().equiparObjeto("yelmo");
                            if (equipar) {
                                gp.getInterfaz().dibujarTextoSombreado("Se ha equipado", 150, 50, 35);
                            } else {
                                gp.getInterfaz().dibujarTextoSombreado("No se ha equipado", 150, 50, 35);
                            }
                            break;
                        case 7:
                            equipar=gp.getJugador().equiparObjeto("peto");
                            if (equipar) {
                                gp.getInterfaz().dibujarTextoSombreado("Se ha equipado", 150, 50, 35);
                            } else {
                                gp.getInterfaz().dibujarTextoSombreado("No se ha equipado", 150, 50, 35);
                            }
                            break;
                        case 8:
                            menuEquipar=false;
                    }


                }
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
                    if (gp.getMusica().getEstado()) {
                        gp.pararMusica();
                    } else {
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
                        gp.getInterfaz().pantallaDelTitulo = 1;
                        gp.getInterfaz().setNumeroMenu(0);
                    }
                    if (gp.getInterfaz().getNumeroMenu() == 1) {
                        //para cargar
                        gp.estadoJuego = 3;
                    }
                    if (gp.getInterfaz().getNumeroMenu() == 2) {
                        System.exit(0);
                    }
                }
            } else if (gp.getInterfaz().getPantallaDelTitulo() == 1) {

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
                        gp.getInterfaz().pantallaDelTitulo = 2;
                        gp.getInterfaz().setNumeroMenu(0);

                    }
                    if (gp.getInterfaz().getNumeroMenu() == 1) {
                        clase = Clase.GUERRERO;
                        gp.getInterfaz().pantallaDelTitulo = 2;
                        gp.getInterfaz().setNumeroMenu(0);
                    }
                    if (gp.getInterfaz().getNumeroMenu() == 2) {
                        clase = Clase.CLERIGO;
                        gp.getInterfaz().pantallaDelTitulo = 2;
                        gp.getInterfaz().setNumeroMenu(0);
                    }
                    if (gp.getInterfaz().getNumeroMenu() == 3) {
                        clase = Clase.PICARO;
                        gp.getInterfaz().pantallaDelTitulo = 2;
                        gp.getInterfaz().setNumeroMenu(0);
                    }
                    if (gp.getInterfaz().getNumeroMenu() == 4) {
                        System.exit(0);
                        gp.getInterfaz().setNumeroMenu(0);
                    }
                }
            } else if (gp.getInterfaz().getPantallaDelTitulo() == 2) {
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
                        gp.getInterfaz().setNumeroMenu(0);
                    }
                    if (gp.getInterfaz().getNumeroMenu() == 1) {
                        raza = Raza.ORCO;
                        gp.getInterfaz().pantallaDelTitulo = 3;
                        gp.getInterfaz().setNumeroMenu(0);
                    }
                    if (gp.getInterfaz().getNumeroMenu() == 2) {
                        raza = Raza.ELFO;
                        gp.getInterfaz().pantallaDelTitulo = 3;
                        gp.getInterfaz().setNumeroMenu(0);
                    }
                    if (gp.getInterfaz().getNumeroMenu() == 3) {
                        raza = Raza.ENANO;
                        gp.getInterfaz().pantallaDelTitulo = 3;
                        gp.getInterfaz().setNumeroMenu(0);

                    }
                    if (gp.getInterfaz().getNumeroMenu() == 4) {
                        System.exit(0);
                        gp.getInterfaz().setNumeroMenu(0);
                    }
                }
            } else if (gp.getInterfaz().getPantallaDelTitulo() == 4) {
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
                        gp.getInterfaz().setNumeroMenu(0);
                        gp.pararMusica();
                        gp.empezarMusica(0);
                        gp.estadoJuego = gp.continuar;
                    }
                    if (gp.getInterfaz().getNumeroMenu() == 1) {
                        gp.getInterfaz().setNumeroMenu(0);
                        gp.getInterfaz().pantallaDelTitulo = 3;
                        textoIngresado.delete(0, textoIngresado.length());
                    }
                }
            }
        }
        //combate
        if(gp.estadoJuego==gp.combate){
            //sonido de batalla
                atacar=false;
                huir=false;
                consumible=false;
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
                    if (gp.getInterfaz().getNumeroMenu() == 0&&gp.gc.jugador.isTurno()) {
                        gp.gc.monstruo.recibirDaño(gp.gc.jugador.atacar());

                    }if (gp.getInterfaz().getNumeroMenu() == 1&&gp.gc.jugador.isTurno()) {
                        consumible=true;
                    }if (gp.getInterfaz().getNumeroMenu() == 2&&gp.gc.jugador.isTurno()) {
                       huir = true;
                    }
                }
                if (atacar) {
                    if (tecla == KeyEvent.VK_W) {
                        gp.getInterfaz().setNumeroMenu(gp.getInterfaz().getNumeroMenu() - 1);
                        if (gp.getInterfaz().getNumeroMenu() < 0) {
                            gp.getInterfaz().setNumeroMenu(1);
                        }
                    }
                    if (tecla == KeyEvent.VK_S) {
                        gp.getInterfaz().setNumeroMenu(gp.getInterfaz().getNumeroMenu() + 1);
                        if (gp.getInterfaz().getNumeroMenu() > 1) {
                            gp.getInterfaz().setNumeroMenu(0);
                        }
                    }
                    if (tecla == KeyEvent.VK_ENTER) {
                        if (gp.getInterfaz().getNumeroMenu() == 0 && gp.gc.jugador.isTurno()) {
                        }
                        if (gp.getInterfaz().getNumeroMenu() == 1 && gp.gc.jugador.isTurno()) {
                            consumible = true;
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
        if (tecla == KeyEvent.VK_ENTER) {
            enterPulsado = false;
        }
    }
    public static void combate(Entidad monstruo, Entidad jugador) {
        if (monstruo.getVelocidad()>jugador.getVelocidad()) {
            monstruo.setTurno(true);
            if (monstruo.turno(monstruo, jugador) <= 0) {
                monstruo.setTurno(false);
                jugador.setTurno(true);
                jugador.turno(monstruo, jugador);
            } else {
                jugador.setTurno(true);
                if (jugador.turno(monstruo, jugador) <= 0) {
                    monstruo.setTurno(true);
                    jugador.setTurno(false);
                    monstruo.turno(monstruo, jugador);
                }

            }
        }else{
            jugador.setTurno(true);
            if (jugador.turno(monstruo,jugador)<=0){
                jugador.setTurno(false);
                monstruo.setTurno(true);
                monstruo.turno(monstruo,jugador);
            }else {
                monstruo.setTurno(true);
                if (monstruo.turno(monstruo, jugador) <= 0) {
                    monstruo.setTurno(false);
                    jugador.setTurno(true);
                    jugador.turno(monstruo, jugador);
                }
            }
        }
    }
}
