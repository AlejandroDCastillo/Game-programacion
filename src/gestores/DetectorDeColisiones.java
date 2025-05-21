package gestores;

import entidades.Entidad;
import entidades.Monstruos.Mon_Esqueleto;
import entidades.Monstruos.Monstruo;
import gamePanel.GamePanel;
import utiles.UtilDiego;

import javax.swing.text.html.parser.Entity;
import java.awt.*;

public class DetectorDeColisiones {
    GamePanel gp;

    /**
     * constructor de la clase
     *
     * @param gp
     */
    public DetectorDeColisiones(GamePanel gp) {
        this.gp = gp;
    }

    /**
     * metodo que verifica las baldosas para verificar sus colisiones
     *
     * @param entidad
     */
    public void comprobarBaldosa(Entidad entidad) {
        int entidadladoIzquierdo = (int) (entidad.getX() + (entidad.zonaDeColision.x));
        int entidadladoDerecho = (int) (entidad.getX() + (entidad.zonaDeColision.x) + (entidad.zonaDeColision.width));
        int entidadoladoArriba = (int) (entidad.getY() + entidad.zonaDeColision.y);
        int entidadoladoAbajo = (int) (entidad.getY() + entidad.zonaDeColision.y + entidad.zonaDeColision.height);
        int entidadLadoIzquierdoColumna = entidadladoIzquierdo / gp.getTamañofinalBaldosa();
        int entidadLadoDerechoColumna = entidadladoDerecho / gp.getTamañofinalBaldosa();
        int entidadLadoArribaFila = entidadoladoArriba / gp.getTamañofinalBaldosa();
        int entidadLadoAbajoFila = entidadoladoAbajo / gp.getTamañofinalBaldosa();
        int baldosa1, baldosa2;
        //switch para verificar la direccion y poder ver donde se encuentra el jugador
        switch (entidad.getDireccion()) {
            case "arriba":
                if (entidad.getY() + entidad.getVelocidad() < 0) {
                    entidad.setColision(true);
                }
                entidadLadoArribaFila = (entidadoladoArriba - entidad.getVelocidad()) / gp.getTamañofinalBaldosa();
                baldosa1 = gp.getGestorBaldosas().numeroMapaBaldosa[entidadLadoArribaFila][entidadLadoDerechoColumna];
                baldosa2 = gp.getGestorBaldosas().numeroMapaBaldosa[entidadLadoArribaFila][entidadLadoIzquierdoColumna];
                if (gp.getGestorBaldosas().baldosa[baldosa1].colision || gp.getGestorBaldosas().baldosa[baldosa2].colision) {
                    entidad.setColision(true);
                }
                break;
            case "abajo":
                if (entidad.getY() + entidad.getVelocidad() > gp.getTamañoAlturaPantalla() - gp.getTamañofinalBaldosa()) {
                    entidad.setColision(true);
                }
                entidadLadoAbajoFila = (entidadoladoAbajo + entidad.getVelocidad()) / gp.getTamañofinalBaldosa();
                if (entidadLadoAbajoFila % 12 == 0) {
                    entidadLadoAbajoFila = 0;
                }

                baldosa1 = gp.getGestorBaldosas().numeroMapaBaldosa[entidadLadoAbajoFila][entidadLadoIzquierdoColumna];
                baldosa2 = gp.getGestorBaldosas().numeroMapaBaldosa[entidadLadoAbajoFila][entidadLadoDerechoColumna];
                if (gp.getGestorBaldosas().baldosa[baldosa1].colision || gp.getGestorBaldosas().baldosa[baldosa2].colision) {
                    entidad.setColision(true);
                }
                break;
            case "izquierda":
                if (entidad.getX() + entidad.getVelocidad() < 0)
                    entidad.setColision(true);
                if (entidadLadoAbajoFila % 12 == 0) {
                    entidadLadoAbajoFila = 0;
                }
                entidadLadoIzquierdoColumna = (entidadladoIzquierdo - entidad.getVelocidad()) / gp.getTamañofinalBaldosa();
                baldosa1 = gp.getGestorBaldosas().numeroMapaBaldosa[entidadLadoArribaFila][entidadLadoIzquierdoColumna];
                baldosa2 = gp.getGestorBaldosas().numeroMapaBaldosa[entidadLadoAbajoFila][entidadLadoIzquierdoColumna];
                if (gp.getGestorBaldosas().baldosa[baldosa1].colision || gp.getGestorBaldosas().baldosa[baldosa2].colision) {
                    entidad.setColision(true);
                }
                break;
            case "derecha":
                if (entidad.getX() + entidad.getVelocidad() > gp.getTamañoAnchuraPantalla() - gp.getTamañofinalBaldosa())
                    entidad.setColision(true);
                if (entidadLadoAbajoFila % 12 == 0) {
                    entidadLadoAbajoFila = 0;
                }
                entidadLadoDerechoColumna = (entidadladoDerecho + entidad.getVelocidad()) / gp.getTamañofinalBaldosa();
                baldosa1 = gp.getGestorBaldosas().numeroMapaBaldosa[entidadLadoArribaFila][entidadLadoDerechoColumna];
                baldosa2 = gp.getGestorBaldosas().numeroMapaBaldosa[entidadLadoAbajoFila][entidadLadoDerechoColumna];
                if (gp.getGestorBaldosas().baldosa[baldosa1].colision || gp.getGestorBaldosas().baldosa[baldosa2].colision) {
                    entidad.setColision(true);
                }
                break;
            case "arriba-izquierda":
                if (entidad.getX() + entidad.getVelocidadDiagonal() < 0 || entidad.getY() + entidad.getVelocidadDiagonal() < 0)
                    entidad.setColision(true);
                if (entidadLadoAbajoFila % 12 == 0) {
                    entidadLadoAbajoFila = 0;
                }
                entidadLadoIzquierdoColumna = (int) ((entidadladoIzquierdo - entidad.getVelocidadDiagonal()) / gp.getTamañofinalBaldosa());
                entidadLadoArribaFila = (int) ((entidadoladoArriba - entidad.getVelocidadDiagonal()) / gp.getTamañofinalBaldosa());
                baldosa1 = gp.getGestorBaldosas().numeroMapaBaldosa[entidadLadoArribaFila][entidadLadoIzquierdoColumna];
                baldosa2 = gp.getGestorBaldosas().numeroMapaBaldosa[entidadLadoAbajoFila][entidadLadoIzquierdoColumna];
                if (gp.getGestorBaldosas().baldosa[baldosa1].colision || gp.getGestorBaldosas().baldosa[baldosa2].colision) {
                    entidad.setColision(true);
                }
                break;

            case "abajo-izquierda":
                if (entidad.getX() + entidad.getVelocidadDiagonal() < 0 || entidad.getY() + entidad.getVelocidad() > gp.getTamañoAlturaPantalla() - gp.getTamañofinalBaldosa())
                    entidad.setColision(true);
                entidadLadoAbajoFila = (int) ((entidadoladoAbajo + entidad.getVelocidadDiagonal()) / gp.getTamañofinalBaldosa());
                if (entidadLadoAbajoFila % 12 == 0) {
                    entidadLadoAbajoFila = 0;
                }
                entidadLadoIzquierdoColumna = (int) ((entidadladoIzquierdo - entidad.getVelocidadDiagonal()) / gp.getTamañofinalBaldosa());
                baldosa1 = gp.getGestorBaldosas().numeroMapaBaldosa[entidadLadoArribaFila][entidadLadoIzquierdoColumna];
                baldosa2 = gp.getGestorBaldosas().numeroMapaBaldosa[entidadLadoAbajoFila][entidadLadoIzquierdoColumna];
                if (gp.getGestorBaldosas().baldosa[baldosa1].colision || gp.getGestorBaldosas().baldosa[baldosa2].colision) {
                    entidad.setColision(true);
                }
                break;

            case "abajo-derecha":
                if (entidad.getX() + entidad.getVelocidad() > gp.getTamañoAnchuraPantalla() - gp.getTamañofinalBaldosa() || entidad.getY() + entidad.getVelocidad() > gp.getTamañoAlturaPantalla() - gp.getTamañofinalBaldosa())
                    entidad.setColision(true);
                entidadLadoAbajoFila = (int) ((entidadoladoAbajo + entidad.getVelocidadDiagonal()) / gp.getTamañofinalBaldosa());
                if (entidadLadoAbajoFila % 12 == 0) {
                    entidadLadoAbajoFila = 0;
                }
                entidadLadoDerechoColumna = (int) ((entidadladoDerecho + entidad.getVelocidadDiagonal()) / gp.getTamañofinalBaldosa());
                baldosa1 = gp.getGestorBaldosas().numeroMapaBaldosa[entidadLadoArribaFila][entidadLadoDerechoColumna];
                baldosa2 = gp.getGestorBaldosas().numeroMapaBaldosa[entidadLadoAbajoFila][entidadLadoDerechoColumna];
                if (gp.getGestorBaldosas().baldosa[baldosa1].colision || gp.getGestorBaldosas().baldosa[baldosa2].colision) {
                    entidad.setColision(true);
                }
                break;

            case "arriba-derecha":
                if (entidad.getX() + entidad.getVelocidad() > gp.getTamañoAnchuraPantalla() - gp.getTamañofinalBaldosa() || entidad.getY() + entidad.getVelocidadDiagonal() < 0)
                    entidad.setColision(true);
                if (entidadLadoAbajoFila % 12 == 0) {
                    entidadLadoAbajoFila = 0;
                }
                entidadLadoArribaFila = (int) ((entidadoladoArriba - entidad.getVelocidadDiagonal()) / gp.getTamañofinalBaldosa());
                entidadLadoDerechoColumna = (int) ((entidadladoDerecho + entidad.getVelocidadDiagonal()) / gp.getTamañofinalBaldosa());
                baldosa1 = gp.getGestorBaldosas().numeroMapaBaldosa[entidadLadoArribaFila][entidadLadoDerechoColumna];
                baldosa2 = gp.getGestorBaldosas().numeroMapaBaldosa[entidadLadoAbajoFila][entidadLadoDerechoColumna];
                if (gp.getGestorBaldosas().baldosa[baldosa1].colision || gp.getGestorBaldosas().baldosa[baldosa2].colision) {
                    entidad.setColision(true);
                }
                break;


        }

    }

    /**
     * metodo que verifica si el jugador pasa por un obj en el suelo
     *
     * @param entidad
     * @param jugador
     * @return
     */
    public int comprobarObjetos(Entidad entidad, boolean jugador) {

        int index = 999;
        //recorremos el array de posibles objetos del suelo
        for (int i = 0; i < gp.arrayobjetos.length; i++) {
            //filtramos cuando encuentre uno
            if (gp.arrayobjetos[i] != null) {
                //Saber la hitbox del player
                entidad.zonaDeColision.x = (int) (entidad.getX() + entidad.zonaDeColision.x);
                entidad.zonaDeColision.y = (int) (entidad.getY() + entidad.zonaDeColision.y);
                //Saber la hitbox del objeto
                gp.arrayobjetos[i].zonaDeColision.x = (int) (gp.arrayobjetos[i].getX() + gp.arrayobjetos[i].zonaDeColision.x);
                gp.arrayobjetos[i].zonaDeColision.y = (int) (gp.arrayobjetos[i].getY() + gp.arrayobjetos[i].zonaDeColision.y);

                switch (entidad.getDireccion()) {
                    case "arriba":
                        entidad.zonaDeColision.y -= entidad.getVelocidad();
                        break;
                    case "derecha":
                        entidad.zonaDeColision.x += entidad.getVelocidad();
                        break;
                    case "abajo":
                        entidad.zonaDeColision.y += entidad.getVelocidad();
                        break;
                    case "izquierda":
                        entidad.zonaDeColision.x -= entidad.getVelocidad();
                        break;
                    case "arriba-derecha":
                        entidad.zonaDeColision.y -= (int) entidad.getVelocidadDiagonal();
                        entidad.zonaDeColision.x += (int) entidad.getVelocidadDiagonal();
                        break;
                    case "arriba-izquierda":
                        entidad.zonaDeColision.y -= (int) entidad.getVelocidadDiagonal();
                        entidad.zonaDeColision.x -= (int) entidad.getVelocidadDiagonal();
                        break;
                    case "abajo-derecha":
                        entidad.zonaDeColision.y += (int) entidad.getVelocidadDiagonal();
                        entidad.zonaDeColision.x += (int) entidad.getVelocidadDiagonal();
                        break;
                    case "abajo-izquierda":
                        entidad.zonaDeColision.y += (int) entidad.getVelocidadDiagonal();
                        entidad.zonaDeColision.x -= (int) entidad.getVelocidadDiagonal();
                        break;
                }
                if (entidad.zonaDeColision.intersects(gp.arrayobjetos[i].zonaDeColision)) {
                    if (gp.arrayobjetos[i].isColision()) {
                        entidad.setColision(true);
                        if (jugador) {
                            index = i;
                        }
                    }
                }
                entidad.zonaDeColision.x = entidad.getZonaDeColisionDefectoX();
                entidad.zonaDeColision.y = entidad.getZonaDeColisionDefectoY();
                gp.arrayobjetos[i].zonaDeColision.x = gp.arrayobjetos[i].zonaDeColisionDefectoX;
                gp.arrayobjetos[i].zonaDeColision.y = gp.arrayobjetos[i].zonaDeColisionDefectoY;
            }
        }
        return index;
    }

    /**
     * metodo que gestiona la colision con una entidad para el combate
     *
     * @param entidad
     * @param objetivo
     * @return
     */
    public int comprobaEntidad(Entidad entidad, Entidad[] objetivo) {

        int index = 999;

        for (int i = 0; i < objetivo.length; i++) {
            if (objetivo[i] != null) {
                if (entidad.getNombre().equalsIgnoreCase(objetivo[i].getNombre())) {
                    return index;
                }
                //Saber la hitbox del player
                entidad.zonaDeColision.x = (int) (entidad.getX() + entidad.zonaDeColision.x);
                entidad.zonaDeColision.y = (int) (entidad.getY() + entidad.zonaDeColision.y);
                //Saber la hitbox de la Entidad
                objetivo[i].zonaDeColision.x = (int) (objetivo[i].getX() + objetivo[i].zonaDeColision.x);
                objetivo[i].zonaDeColision.y = (int) (objetivo[i].getY() + objetivo[i].zonaDeColision.y);

                switch (entidad.getDireccion()) {
                    case "arriba":
                        entidad.zonaDeColision.y -= entidad.getVelocidad();
                        break;
                    case "derecha":
                        entidad.zonaDeColision.x += entidad.getVelocidad();
                        break;
                    case "abajo":
                        entidad.zonaDeColision.y += entidad.getVelocidad();
                        break;
                    case "izquierda":
                        entidad.zonaDeColision.x -= entidad.getVelocidad();
                        break;
                    case "arriba-derecha":
                        entidad.zonaDeColision.y -= (int) entidad.getVelocidadDiagonal();
                        entidad.zonaDeColision.x += (int) entidad.getVelocidadDiagonal();
                        break;
                    case "arriba-izquierda":
                        entidad.zonaDeColision.y -= (int) entidad.getVelocidadDiagonal();
                        entidad.zonaDeColision.x -= (int) entidad.getVelocidadDiagonal();
                        break;
                    case "abajo-derecha":
                        entidad.zonaDeColision.y += (int) entidad.getVelocidadDiagonal();
                        entidad.zonaDeColision.x += (int) entidad.getVelocidadDiagonal();
                        break;
                    case "abajo-izquierda":
                        entidad.zonaDeColision.y += (int) entidad.getVelocidadDiagonal();
                        entidad.zonaDeColision.x -= (int) entidad.getVelocidadDiagonal();
                        break;
                }

                if (entidad.zonaDeColision.intersects(objetivo[i].zonaDeColision)) {
                    if (objetivo[i] != null) {
                        entidad.setColision(true);
                        index = i;
                    }
                }
                entidad.zonaDeColision.x = entidad.getZonaDeColisionDefectoX();
                entidad.zonaDeColision.y = entidad.getZonaDeColisionDefectoY();
                objetivo[i].zonaDeColision.x = objetivo[i].getZonaDeColisionDefectoX();
                objetivo[i].zonaDeColision.y = objetivo[i].getZonaDeColisionDefectoY();
            }
        }
        return index;
    }

    /**
     * metodo que gestiona las colisiones del jugador
     *
     * @param entidad
     */
    public void comprobarJugador(Entidad entidad) {
        entidad.zonaDeColision.x = (int) (entidad.getX() + entidad.zonaDeColision.x);
        entidad.zonaDeColision.y = (int) (entidad.getY() + entidad.zonaDeColision.y);
        //Saber la hitbox del JUGADOR
        gp.getJugador().zonaDeColision.x = (int) (gp.getJugador().getX() + gp.getJugador().zonaDeColision.x);
        gp.getJugador().zonaDeColision.y = (int) (gp.getJugador().getY() + gp.getJugador().zonaDeColision.y);

        switch (entidad.getDireccion()) {
            case "arriba":
                entidad.zonaDeColision.y -= entidad.getVelocidad();
                break;
            case "derecha":
                entidad.zonaDeColision.x += entidad.getVelocidad();
                break;
            case "abajo":
                entidad.zonaDeColision.y += entidad.getVelocidad();
                break;
            case "izquierda":
                entidad.zonaDeColision.x -= entidad.getVelocidad();
                break;
            case "arriba-derecha":
                entidad.zonaDeColision.y -= (int) entidad.getVelocidadDiagonal();
                entidad.zonaDeColision.x += (int) entidad.getVelocidadDiagonal();
                break;
            case "arriba-izquierda":
                entidad.zonaDeColision.y -= (int) entidad.getVelocidadDiagonal();
                entidad.zonaDeColision.x -= (int) entidad.getVelocidadDiagonal();
                break;
            case "abajo-derecha":
                entidad.zonaDeColision.y += (int) entidad.getVelocidadDiagonal();
                entidad.zonaDeColision.x += (int) entidad.getVelocidadDiagonal();
                break;
            case "abajo-izquierda":
                entidad.zonaDeColision.y += (int) entidad.getVelocidadDiagonal();
                entidad.zonaDeColision.x -= (int) entidad.getVelocidadDiagonal();
                break;
        }

        if (entidad.zonaDeColision.intersects(gp.getJugador().zonaDeColision)) {
            entidad.setColision(true);
            if (entidad instanceof Monstruo) {
                gp.pararMusica();
                gp.efectoSonido(3);
                gp.getGraphics().setColor(Color.black);
                gp.getGraphics().fillRect(0, 0, gp.getWidth(), gp.getHeight());
                try {
                    Thread.sleep(2000); // Espera 2000 milisegundos (2 segundos)
                } catch (InterruptedException e) {
                    System.out.println("La espera fue interrumpida.");
                }
                gp.empezarMusica(4);
                gp.estadoJuego = gp.combate;
                gp.gc = new GestorCombate(gp.getJugador(), entidad, gp);
            }
        }
        entidad.zonaDeColision.x = entidad.getZonaDeColisionDefectoX();
        entidad.zonaDeColision.y = entidad.getZonaDeColisionDefectoY();
        gp.getJugador().zonaDeColision.x = gp.getJugador().getZonaDeColisionDefectoX();
        gp.getJugador().zonaDeColision.y = gp.getJugador().getZonaDeColisionDefectoY();
    }
}

