package gestores;

import entidades.Entidad;
import entidades.Jugador;
import gamePanel.GamePanel;

public class GestorCombate {
    public Jugador jugador;
    public Entidad monstruo;
    GamePanel gp;
    public int contadorUpdates = 1;
    boolean expRecibida = false;
    private int numSpritesEnemigo = 0;
    private int numSpritesJugador = 0;
    boolean finalAnimacion;

    /**
     * constructor del gestor
     *
     * @param jugador
     * @param monstruo
     * @param gp
     */
    public GestorCombate(Jugador jugador, Entidad monstruo, GamePanel gp) {
        this.jugador = jugador;
        this.monstruo = monstruo;
        this.gp = gp;
        //decidimos el orden de los turnos en funcion de la velocidad de cada entidad
        if (monstruo.getVelocidad() > jugador.getVelocidad() && !jugador.isTurno()) {
            monstruo.setTurno(true);
            jugador.setTurno(false);
        } else {
            monstruo.setTurno(false);
            jugador.setTurno(true);
        }
    }

    /**
     * metodo que gestiona el bucle del combate por turnos
     */
    public void combate() {

        //muere el jugador
        if (jugador.getVida() <= 0) {
            gp.pararMusica();
            gp.empezarMusica(0);
            gp.estadoJuego = gp.gameOver;
            //muere el enemigo
        } else if (monstruo.getVida() <= 0) {
            int exp = monstruo.calcularExperiencia(monstruo.getNivel());
            if (!expRecibida)
            jugador.subirEXP(exp);
            expRecibida = true;
            gp.pararMusica();
            gp.empezarMusica(0);
            gp.getInterfaz().enseñarMensaje("Has recibido " + exp + " de experiencia");
            if (contadorUpdates >= 80) {
                gp.estadoJuego = gp.continuar;
                monstruo.setTurno(false);
                jugador.setTurno(false);
                jugador.setOpcionAtacar(false);
                jugador.setOpcionHuir(false);
                monstruo.setOpcionAtacar(false);
                for (int i = 0; i < gp.arrayEnemigos.length; i++) {
                    if (gp.arrayEnemigos[i]!=null){
                    if (monstruo.getNombre().equals(gp.arrayEnemigos[i].getNombre())) {
                            gp.arrayEnemigos[i] = null;
                            break;
                        }
                    }
                }
            }
        }
        //turno del enemigo
        if (monstruo.isTurno()) {
            monstruo.setOpcionAtacar(true);
            if (monstruo.isOpcionAtacar()) {
                if (contadorUpdates==1){
                    jugador.recibirDaño(monstruo.atacar());
                }
                if (contadorUpdates>=80){
                    monstruo.setOpcionAtacar(false);
                    monstruo.setTurno(false);
                    gp.efectoSonido(5);
                    jugador.setTurno(true);
                    jugador.setOpcionAtacar(false);
                    jugador.setOpcionHuir(false);
                    finalAnimacion = false;
                }
                if (numSpritesEnemigo>=4){
                    numSpritesEnemigo=0;
                    finalAnimacion = true;
                }

            }
        }if (jugador.isTurno()) {

            if (jugador.isOpcionAtacar()) {
                if (contadorUpdates>=80){
                    gp.efectoSonido(5);
                    jugador.setOpcionAtacar(false);
                    jugador.setOpcionHuir(false);
                    gp.gc.jugador.setTurno(false);
                    gp.gc.monstruo.setTurno(true);
                    monstruo.setOpcionAtacar(false);
                    finalAnimacion = false;
                }
                if (numSpritesJugador>=3){
                    numSpritesJugador=0;
                    finalAnimacion = true;
                }
            }
        }
    }

    /**
     * update del combate para generar el bucle de los turnos
     */
    public void update() {
        combate();
        //estipulamos la duracion de cada turno
        if (contadorUpdates%8==0&&(monstruo.isOpcionAtacar())&&!finalAnimacion) {
            numSpritesEnemigo++;
        }

       if (contadorUpdates%8==0&&(jugador.isOpcionAtacar())&&!finalAnimacion) {
            numSpritesJugador++;
        }
        if (contadorUpdates >= 80) {
            contadorUpdates = 0;
        }

        contadorUpdates++;
    }

    public int getNumSpritesEnemigo() {
        return numSpritesEnemigo;
    }

    public void setNumSpritesEnemigo(int numSpritesEnemigo) {
        this.numSpritesEnemigo = numSpritesEnemigo;
    }

    public int getNumSpritesJugador() {
        return numSpritesJugador;
    }

    public void setNumSpritesJugador(int numSpritesJugador) {
        this.numSpritesJugador = numSpritesJugador;
    }
}