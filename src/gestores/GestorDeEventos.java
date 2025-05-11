package gestores;

import gamePanel.GamePanel;

import java.awt.*;

public class GestorDeEventos {
    Rectangle hitboxEvento;
    int hitboxEventoXDefecto,hitboxEventoYDefecto;
    GamePanel gp;
    public GestorDeEventos(GamePanel gp) {
        this.gp = gp;
        hitboxEvento=new Rectangle(23,23,2,2);
        hitboxEventoXDefecto=hitboxEvento.x;
        hitboxEventoYDefecto=hitboxEvento.y;
    }

    public void comprobarEventos(){
        if (hit(11,4,"cualquiera")){
            velocidadMomentanea();
        }
    }
    public boolean hit(int columnaEvento,int filaEvento, String direccion) {
        boolean hit = false;
        gp.getJugador().zonaDeColision.x= (int) (gp.getJugador().zonaDeColision.x+gp.getJugador().getX());
        gp.getJugador().zonaDeColision.y=(int) (gp.getJugador().zonaDeColision.y+gp.getJugador().getY());
        hitboxEvento.x=columnaEvento*gp.getTamañofinalBaldosa()+hitboxEvento.x;
        hitboxEvento.y=filaEvento*gp.getTamañofinalBaldosa()+hitboxEvento.y;
        if (gp.getJugador().zonaDeColision.intersects(hitboxEvento)) {
            if (gp.getJugador().getDireccion().contentEquals(direccion)|| direccion.contentEquals("cualquiera")) {
                hit = true;
            }
        }
        gp.getJugador().zonaDeColision.x=gp.getJugador().getZonaDeColisionDefectoX();
        gp.getJugador().zonaDeColision.y=gp.getJugador().getZonaDeColisionDefectoY();
        hitboxEvento.x=hitboxEventoXDefecto;
        hitboxEvento.y=hitboxEventoYDefecto;
        return hit;
    }

    public void  velocidadMomentanea(){
        if (gp.getTeclado().enterPulsado){
            gp.getJugador().setVelocidad(4);
            gp.getJugador().setVelocidadDiagonal(Math.hypot(gp.getJugador().getVelocidad(),gp.getJugador().getVelocidad())/2);
        }

    }
}
