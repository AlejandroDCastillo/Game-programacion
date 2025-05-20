package gestores;

import gamePanel.GamePanel;

import java.awt.*;

public class GestorDeEventos {
    Rectangle hitboxEvento;
    int hitboxEventoXDefecto, hitboxEventoYDefecto;
    GamePanel gp;

    /**
     * constructor de gestor
     *
     * @param gp
     */
    public GestorDeEventos(GamePanel gp) {
        this.gp = gp;
        hitboxEvento = new Rectangle(23, 23, 2, 2);
        hitboxEventoXDefecto = hitboxEvento.x;
        hitboxEventoYDefecto = hitboxEvento.y;
    }

    /**
     * metodo que verifica los posibles eventos
     */
    public void comprobarEventos() {
        //comprobamos si hay colision
        if (hit(11, 4, "cualquiera")) {
            velocidadMomentanea();
        }
    }

    /**
     * metodo boolean para verificar si hay colision, para iniciar el evento o no
     *
     * @param columnaEvento
     * @param filaEvento
     * @param direccion
     * @return
     */
    public boolean hit(int columnaEvento, int filaEvento, String direccion) {
        boolean hit = false;
        //transforma las coordenadas de colision
        gp.getJugador().zonaDeColision.x = (int) (gp.getJugador().zonaDeColision.x + gp.getJugador().getX());
        gp.getJugador().zonaDeColision.y = (int) (gp.getJugador().zonaDeColision.y + gp.getJugador().getY());
        //posiciona la hitbox en el mundo
        hitboxEvento.x = columnaEvento * gp.getTamañofinalBaldosa() + hitboxEvento.x;
        hitboxEvento.y = filaEvento * gp.getTamañofinalBaldosa() + hitboxEvento.y;
        //comprueba intersecciones
        if (gp.getJugador().zonaDeColision.intersects(hitboxEvento)) {
            if (gp.getJugador().getDireccion().contentEquals(direccion) || direccion.contentEquals("cualquiera")) {
                hit = true;
            }
        }
        //restablece hitbox a su valor pro defecto
        gp.getJugador().zonaDeColision.x = gp.getJugador().getZonaDeColisionDefectoX();
        gp.getJugador().zonaDeColision.y = gp.getJugador().getZonaDeColisionDefectoY();
        hitboxEvento.x = hitboxEventoXDefecto;
        hitboxEvento.y = hitboxEventoYDefecto;
        return hit;
    }

    /**
     * modifica la velocidad al pulsar enter
     */
    public void velocidadMomentanea() {
        if (gp.getTeclado().enterPulsado) {
            gp.getJugador().setVelocidad(4);
            gp.getJugador().setVelocidadDiagonal(Math.hypot(gp.getJugador().getVelocidad(), gp.getJugador().getVelocidad()) / 2);
        }

    }
}
