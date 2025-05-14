package gestores;

import entidades.Entidad;
import entidades.Jugador;
import gamePanel.GamePanel;

public class GestorCombate {
    public Jugador jugador;
    public Entidad monstruo;
    GamePanel gp;
    int accion=1;
    public GestorCombate(Jugador jugador, Entidad monstruo, GamePanel gp) {
        this.jugador = jugador;
        this.monstruo = monstruo;
        this.gp = gp;
    }

    public void combate(){
        if (jugador.getVida()<=0) {
            gp.estadoJuego=gp.gameOver;
        }else if (monstruo.getVida()<=0) {
            gp.estadoJuego=gp.continuar;
            for (int i =0;i<gp.arrayEnemigos.length;i++){
                if (monstruo.getNombre().equals(gp.arrayEnemigos[i].getNombre())){
                    gp.arrayEnemigos[i]=null;
                    break;
                }
            }
        }else{
            if (monstruo.getVelocidad()>jugador.getVelocidad()) {
                monstruo.setTurno(true);
                monstruo.atacar();
                accion =monstruo.turno(monstruo, jugador);
                if (accion <= 0) {
                    monstruo.setTurno(false);
                    jugador.setTurno(true);
                } else {
                    jugador.setTurno(true);
                    if (jugador.isOpcionAtacar()){
                        monstruo.setTurno(true);
                        jugador.setTurno(false);
                        monstruo.turno(monstruo, jugador);
                    }
                }
            }else{
                jugador.setTurno(true);
                if (jugador.isOpcionAtacar()){
                    jugador.setTurno(false);
                    monstruo.setTurno(true);
                    monstruo.turno(monstruo,jugador);
                }else {
                    monstruo.setTurno(true);
                    accion=jugador.turno(monstruo,jugador);
                    if (accion <= 0) {
                        monstruo.setTurno(false);
                        jugador.setTurno(true);
                    }
                }
            }
        }
    }

    public void update(){
        combate();
    }
}
