package gestores;

import entidades.Entidad;
import entidades.Jugador;
import gamePanel.GamePanel;

public class GestorCombate {
    public Jugador jugador;
    public Entidad monstruo;
    GamePanel gp;
    public int contadorUpdates=1;
    public GestorCombate(Jugador jugador, Entidad monstruo, GamePanel gp) {
        this.jugador = jugador;
        this.monstruo = monstruo;
        this.gp = gp;
        if (monstruo.getVelocidad()>jugador.getVelocidad()&&!jugador.isTurno()) {
            monstruo.setTurno(true);
            jugador.setTurno(false);
        }else{
            monstruo.setTurno(false);
            jugador.setTurno(true);
        }
    }

    public void combate(){

        if (jugador.getVida()<=0) {
            gp.pararMusica();
            gp.empezarMusica(0);
            gp.estadoJuego=gp.gameOver;
        }else if (monstruo.getVida()<=0) {
            int exp=monstruo.calcularExperiencia(monstruo.getNivel());
            jugador.setEXP(exp);
            gp.pararMusica();
            gp.empezarMusica(0);
            gp.getInterfaz().enseñarMensaje("Has recibido "+exp+" de experiencia");
            if (contadorUpdates>=80) {
                gp.estadoJuego=gp.continuar;
                for (int i =0;i<gp.arrayEnemigos.length;i++){
                    if (monstruo.getNombre().equals(gp.arrayEnemigos[i].getNombre())){
                        gp.arrayEnemigos[i]=null;
                        break;
                    }
                }
            }

        } if (monstruo.isTurno()){

            if (contadorUpdates>=80){
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

    public void update(){
        combate();

        if (contadorUpdates>=80){
            contadorUpdates=0;
        }
        contadorUpdates++;
    }
}