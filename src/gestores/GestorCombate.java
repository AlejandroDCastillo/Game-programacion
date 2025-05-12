package gestores;

import entidades.Entidad;
import gamePanel.GamePanel;

public class GestorCombate {
    public Entidad jugador;
    public Entidad monstruo;
    GamePanel gp;
    public GestorCombate(Entidad jugador, Entidad monstruo, GamePanel gp) {
        this.jugador = jugador;
        this.monstruo = monstruo;
        this.gp = gp;
    }

    public void combate(){

        if (jugador.getVida()<=0) {
            gp.estadoJuego=gp.gameOver;
        }else if (monstruo.getVida()<=0) {
            gp.estadoJuego=gp.continuar;
        }else{
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
}
