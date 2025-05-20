package gestores;

import entidades.Entidad;
import entidades.Jugador;
import gamePanel.GamePanel;

public class GestorCombate {
    public Jugador jugador;
    public Entidad monstruo;
    GamePanel gp;
    public int contadorUpdates = 1;

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
            jugador.setEXP(exp);
            gp.pararMusica();
            gp.empezarMusica(0);
            gp.getInterfaz().enseñarMensaje("Has recibido " + exp + " de experiencia");
            if (contadorUpdates >= 80) {
                gp.estadoJuego = gp.continuar;
                for (int i = 0; i < gp.arrayEnemigos.length; i++) {
                    if (monstruo.getNombre().equals(gp.arrayEnemigos[i].getNombre())) {
                        gp.arrayEnemigos[i] = null;
                        break;
                    }
                }
            }
        }
        //turno del enemigo
        if (monstruo.isTurno()) {
            if (contadorUpdates >= 80) {
                gp.efectoSonido(5);
                jugador.recibirDaño(monstruo.atacar());
                monstruo.setTurno(false);
                jugador.setTurno(true);
                jugador.setOpcionAtacar(false);
                jugador.setOpcionHuir(false);
                monstruo.setOpcionAtacar(true);

            }
        }
    }

    /**
     * update del combate para generar el bucle de los turnos
     */
    public void update() {
        combate();
        //estipulamos la duracion de cada turno
        if (contadorUpdates >= 80) {
            contadorUpdates = 0;
        }
        contadorUpdates++;
    }
}