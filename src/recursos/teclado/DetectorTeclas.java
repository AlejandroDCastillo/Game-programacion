package recursos.teclado;

import entidades.Clase;
import entidades.Jugador;
import entidades.Raza;
import gamePanel.GamePanel;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class DetectorTeclas implements KeyListener {
    public boolean arriba;
    public boolean abajo;
    public boolean izquierda;
    public boolean derecha;
    public boolean enterPulsado = false;
    public boolean menuBoolean = false;
    public boolean primerTurno = false;
    //boolean que esconde el desplegable de crafteos
    public boolean craftear = false;
    public boolean intentocrafteo = false;
    public boolean intentoEquipar = false;
    //bolean para desplegable de equipar
    public boolean menuEquipar = false;
    //bolean despliegue consumir
    public boolean consumir = false;
    //bolean para menu de stats
    public boolean menuStats = false;
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
            if (craftear) {
                intentocrafteo = false;
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
                    intentocrafteo = true;
                    boolean crafteo;
                    String string1 = "Crafteo realizado con exito";
                    String string2 = "Crafteo no realizado";
                    switch (gp.getInterfaz().getNumeroMenu()) {
                        case 0:
                            crafteo = gp.getJugador().craftear("escudo");
                            //mensaje (no se porq no sale hayq ue arreglarlo)
                            if (crafteo) {
                                gp.getInterfaz().enseñarMensaje(string1);
                            } else {
                                gp.getInterfaz().enseñarMensaje(string2);
                            }
                            break;
                        case 1:
                            crafteo = gp.getJugador().craftear("escudo_oro");
                            if (crafteo) {
                                gp.getInterfaz().enseñarMensaje(string1);
                            } else {
                                gp.getInterfaz().enseñarMensaje(string2);
                            }
                            break;

                        case 2:
                            crafteo = gp.getJugador().craftear("espada");
                            if (crafteo) {
                                gp.getInterfaz().enseñarMensaje(string1);
                            } else {
                                gp.getInterfaz().enseñarMensaje(string2);
                            }
                        case 3:
                            crafteo = gp.getJugador().craftear("espadaFuego");
                            if (crafteo) {
                                gp.getInterfaz().enseñarMensaje(string1);
                            } else {
                                gp.getInterfaz().enseñarMensaje(string2);
                            }
                            break;

                        case 4:
                            crafteo = gp.getJugador().craftear("varamago");
                            if (crafteo) {
                                gp.getInterfaz().enseñarMensaje(string1);
                            } else {
                                gp.getInterfaz().enseñarMensaje(string2);
                            }
                            break;
                        case 5:
                            crafteo = gp.getJugador().craftear("talismanSecreto");
                            if (crafteo) {
                                gp.getInterfaz().enseñarMensaje(string1);
                            } else {
                                gp.getInterfaz().enseñarMensaje(string2);
                            }
                            break;

                        case 6:
                            crafteo = gp.getJugador().craftear("yelmo");
                            if (crafteo) {
                                gp.getInterfaz().enseñarMensaje(string1);
                            } else {
                                gp.getInterfaz().enseñarMensaje(string2);
                            }
                            break;
                        case 7:
                            crafteo = gp.getJugador().craftear("peto");
                            if (crafteo) {
                                gp.getInterfaz().enseñarMensaje(string1);
                            } else {
                                gp.getInterfaz().enseñarMensaje(string2);
                            }
                            break;
                        case 8:
                            crafteo = gp.getJugador().craftear("oro");
                            if (crafteo) {
                                gp.getInterfaz().enseñarMensaje(string1);
                            } else {
                                gp.getInterfaz().enseñarMensaje(string2);
                            }
                            break;
                        case 9:
                            crafteo = gp.getJugador().craftear("hierro");
                            if (crafteo) {
                                gp.getInterfaz().enseñarMensaje(string1);
                            } else {
                                gp.getInterfaz().enseñarMensaje(string2);
                            }
                            break;
                        case 10:
                            craftear = false;
                            intentocrafteo = false;
                            break;
                    }
                }if (tecla ==KeyEvent.VK_ESCAPE){
                    craftear = false;
                    intentocrafteo = false;
                    tecla=0;
                }
            }
            if (menuEquipar) {
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
                    intentoEquipar = true;
                    boolean equipar;
                    String string1 = "Se ha equipado";
                    String string2 = "No se ha equipado";
                    switch (gp.getInterfaz().numeroMenu) {
                        case 0:
                            equipar = gp.getJugador().equiparObjeto("escudo");
                            if (equipar) {
                                gp.getInterfaz().enseñarMensaje(string1);
                            } else {
                                gp.getInterfaz().enseñarMensaje(string2);
                            }
                            break;
                        case 1:
                            equipar = gp.getJugador().equiparObjeto("escudo_oro");
                            if (equipar) {
                                gp.getInterfaz().enseñarMensaje(string1);
                            } else {
                                gp.getInterfaz().enseñarMensaje(string2);
                            }
                            break;
                        case 2:
                            equipar = gp.getJugador().equiparObjeto("espada");
                            if (equipar) {
                                gp.getInterfaz().enseñarMensaje(string1);
                            } else {
                                gp.getInterfaz().enseñarMensaje(string2);
                            }
                            break;
                        case 3:
                            equipar = gp.getJugador().equiparObjeto("espadaFuego");
                            if (equipar) {
                                gp.getInterfaz().enseñarMensaje(string1);
                            } else {
                                gp.getInterfaz().enseñarMensaje(string2);
                            }
                            break;
                        case 4:
                            equipar = gp.getJugador().equiparObjeto("varaMago");
                            if (equipar) {
                                gp.getInterfaz().enseñarMensaje(string1);
                            } else {
                                gp.getInterfaz().enseñarMensaje(string2);
                            }
                            break;
                        case 5:
                            equipar = gp.getJugador().equiparObjeto("talismanSecreto");
                            if (equipar) {
                                gp.getInterfaz().enseñarMensaje(string1);
                            } else {
                                gp.getInterfaz().enseñarMensaje(string2);
                            }
                            break;
                        case 6:
                            equipar = gp.getJugador().equiparObjeto("yelmo");
                            if (equipar) {
                                gp.getInterfaz().enseñarMensaje(string1);
                            } else {
                                gp.getInterfaz().enseñarMensaje(string2);
                            }
                            break;
                        case 7:
                            equipar = gp.getJugador().equiparObjeto("peto");
                            if (equipar) {
                                gp.getInterfaz().enseñarMensaje(string1);
                            } else {
                                gp.getInterfaz().enseñarMensaje(string2);
                            }
                            break;
                        case 8:
                            menuEquipar = false;
                            intentoEquipar = false;
                    }
                }if (tecla ==KeyEvent.VK_ESCAPE){
                    menuEquipar = false;
                    intentoEquipar = false;
                    tecla=0;
                }
            }
            if (menuStats) {
                gp.getInterfaz().numeroMenu = 12;
                if (tecla == KeyEvent.VK_ENTER) {
                    menuStats = false;
                }
                if (tecla ==KeyEvent.VK_ESCAPE){
                    menuStats = false;
                    tecla=0;
                }
            }
            if(consumir){
                if (tecla == KeyEvent.VK_W) {
                        gp.getInterfaz().numeroMenuCons = gp.getInterfaz().numeroMenuCons - 1;
                        if (gp.getInterfaz().numeroMenuCons < 0) {
                            gp.getInterfaz().numeroMenuCons=2;
                        }
                }
                if (tecla == KeyEvent.VK_S) {
                        gp.getInterfaz().numeroMenuCons = gp.getInterfaz().numeroMenuCons + 1;
                        if (gp.getInterfaz().numeroMenuCons > 2) {
                            gp.getInterfaz().numeroMenuCons=0;
                        }
                }
                if(tecla== KeyEvent.VK_ENTER) {
                    if (gp.getInterfaz().numeroMenuCons == 0) {
                        //pocion de vida
                        gp.getJugador().comer();
                    }
                    if (gp.getInterfaz().numeroMenuCons == 1) {
                        //pocion de mana
                        boolean bebe = gp.getJugador().beberMana();
                    }
                    if (gp.getInterfaz().numeroMenuCons == 2) {
                        //volver
                        consumir = false;
                        tecla=0;
                    }
                }
                if (tecla ==KeyEvent.VK_ESCAPE){
                    consumir = false;
                    tecla=0;
                }
            }
            if (!menuEquipar && !menuStats && !craftear && !consumir) {
                if (tecla == KeyEvent.VK_W ) {
                    gp.getInterfaz().setNumeroMenu(gp.getInterfaz().getNumeroMenu() - 1);
                    if (gp.getInterfaz().getNumeroMenu() < 0) {
                        gp.getInterfaz().setNumeroMenu(3);
                    }
                }
                if (tecla == KeyEvent.VK_S) {
                    gp.getInterfaz().setNumeroMenu(gp.getInterfaz().getNumeroMenu() + 1);
                    if (gp.getInterfaz().getNumeroMenu() > 3) {
                        gp.getInterfaz().setNumeroMenu(0);
                    }
                }
                if (tecla == KeyEvent.VK_ENTER) {
                    //PARA craftear
                    if (gp.getInterfaz().numeroMenu == 0) {
                        System.out.println("craftear");
                        if (!craftear) {
                            craftear = true;
                        }
                    }
                    //para equipar
                    if (gp.getInterfaz().numeroMenu == 1) {
                        if (!menuEquipar) {
                            menuEquipar = true;
                        }
                    }
                    //para para stats
                    if (gp.getInterfaz().numeroMenu == 2) {
                        //para ver stats de personaje
                        if (!menuStats) {
                            menuStats = true;
                        }
                    }
                    //para consumir
                    if(gp.getInterfaz().numeroMenu == 3) {
                        if(!consumir){
                            consumir=true;
                        }
                    }
                }
                if (tecla == KeyEvent.VK_ESCAPE) {
                    menuBoolean=false;
                    gp.estadoJuego=gp.continuar;
                    tecla=0;
                }

            }
        }
        //memu de pausa
        if (gp.estadoJuego == gp.pausa) {
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
                if (gp.getInterfaz().getNumeroMenu() == 0) {
                    if (gp.getMusica().getEstado()) {
                        gp.pararMusica();
                    } else {
                        gp.empezarMusica(0);
                    }
                }
                if (gp.getInterfaz().getNumeroMenu() == 1) {
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
                    if (gp.getInterfaz().getNumeroMenu() == 0) {
                        gp.getInterfaz().pantallaDelTitulo = 1;
                        gp.getInterfaz().setNumeroMenu(0);
                    }
                    if (gp.getInterfaz().getNumeroMenu() == 1) {
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
        if (gp.estadoJuego == gp.combate) {
            if (gp.gc.jugador.isTurno()) {
                if (tecla == KeyEvent.VK_W) {
                    gp.getInterfaz().setNumeroMenu(gp.getInterfaz().getNumeroMenu() - 1);
                    if (gp.getInterfaz().getNumeroMenu() < 0) {
                        gp.getInterfaz().setNumeroMenu(2);
                    }
                    if (consumir) {
                        gp.getInterfaz().numeroMenuCons = gp.getInterfaz().numeroMenuCons - 1;
                        if (gp.getInterfaz().getNumeroMenu() > 0) {
                            gp.getInterfaz().setNumeroMenu(2);
                        }
                    }
                }
                if (tecla == KeyEvent.VK_S) {
                    gp.getInterfaz().setNumeroMenu(gp.getInterfaz().getNumeroMenu() + 1);
                    if (gp.getInterfaz().getNumeroMenu() > 2) {
                        gp.getInterfaz().setNumeroMenu(0);
                    }
                    if (consumir) {
                        gp.getInterfaz().numeroMenuCons = gp.getInterfaz().numeroMenuCons + 1;
                        if (gp.getInterfaz().getNumeroMenu() > 2) {
                            gp.getInterfaz().setNumeroMenu(0);
                        }
                    }
                }
                if (tecla == KeyEvent.VK_ENTER) {
                    if (!consumir) {
                        if (gp.getInterfaz().getNumeroMenu() == 0 && gp.gc.jugador.isTurno()) {
                            gp.gc.monstruo.recibirDaño(gp.gc.jugador.atacar());
                            gp.gc.jugador.setOpcionAtacar(true);
                            gp.gc.jugador.setTurno(false);
                            gp.gc.monstruo.setTurno(true);
                            gp.gc.contadorUpdates = 0;
                        }
                        if (gp.getInterfaz().getNumeroMenu() == 1 && gp.gc.jugador.isTurno()) {
                            //consumible
                            if (!consumir) {
                                consumir = true;
                            } else {
                                consumir = false;
                            }

                        }
                        if (gp.getInterfaz().getNumeroMenu() == 2 && gp.gc.jugador.isTurno()) {
                            gp.gc.jugador.huir();
                            gp.gc.jugador.setOpcionHuir(true);
                            gp.gc.jugador.setTurno(false);
                            gp.gc.monstruo.setTurno(true);
                            gp.gc.contadorUpdates = 0;
                            if(gp.getJugador().isOpcionHuir()){
                                gp.pararMusica();
                                gp.empezarMusica(0);
                            }
                        }
                    } else {
                        if (gp.getInterfaz().numeroMenuCons == 0) {
                            //pocion de mana
                            boolean bebe = gp.getJugador().beberMana();
                        }
                        if (gp.getInterfaz().numeroMenuCons == 1) {
                            //pocion de vida
                            gp.getJugador().comer();
                        }
                        if (gp.getInterfaz().numeroMenuCons == 2) {
                            //volver
                            consumir = false;
                        }
                    }
                }if (tecla == KeyEvent.VK_ESCAPE) {
                    if (consumir) {
                        consumir = false;
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
}
