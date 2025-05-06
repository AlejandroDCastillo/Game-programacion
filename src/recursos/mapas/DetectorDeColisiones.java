package recursos.mapas;

import entidades.Entidad;
import gamePanel.GamePanel;

import javax.swing.text.html.parser.Entity;

public class DetectorDeColisiones {
    GamePanel gp;

    public DetectorDeColisiones(GamePanel gp) {
        this.gp = gp;
    }

    public void comprobarBaldosa(Entidad entidad) {
        int entidadladoIzquierdo = (int) (entidad.getX() +  (entidad.zonaDeColision.x));
        int entidadladoDerecho = (int) (entidad.getX() +  (entidad.zonaDeColision.x) + (entidad.zonaDeColision.width));
        int entidadoladoArriba = (int) (entidad.getY() + entidad.zonaDeColision.y);
        int entidadoladoAbajo = (int) (entidad.getY() + entidad.zonaDeColision.y + entidad.zonaDeColision.height);
        int entidadLadoIzquierdoColumna = entidadladoIzquierdo/gp.getTamañofinalBaldosa();
        int entidadLadoDerechoColumna = entidadladoDerecho/gp.getTamañofinalBaldosa();
        int entidadLadoArribaFila = entidadoladoArriba/gp.getTamañofinalBaldosa();
        int entidadLadoAbajoFila = entidadoladoAbajo/gp.getTamañofinalBaldosa();
        int baldosa1, baldosa2;



        switch (entidad.getDireccion()) {
            case "arriba":
                if (entidad.getY() + entidad.getVelocidad() < 0)
                    entidad.setColision(true);
//                if (entidadLadoIzquierdoColumna%16==0)
//                    entidadLadoIzquierdoColumna=15;
                entidadLadoArribaFila = (entidadoladoArriba - entidad.getVelocidad()) / gp.getTamañofinalBaldosa();
                baldosa1 = gp.getGestorBaldosas().numeroMapaBaldosa[entidadLadoArribaFila][entidadLadoDerechoColumna];
                baldosa2 = gp.getGestorBaldosas().numeroMapaBaldosa[entidadLadoArribaFila][entidadLadoIzquierdoColumna];
                if (gp.getGestorBaldosas().baldosa[baldosa1].colision || gp.getGestorBaldosas().baldosa[baldosa2].colision) {
                    entidad.setColision(true);
                }
                break;
            case "abajo":
                if (entidad.getY() + entidad.getVelocidad() > gp.getTamañoAlturaPantalla() - gp.getTamañofinalBaldosa()){
                    entidad.setColision(true);
                }
                entidadLadoAbajoFila = (entidadoladoAbajo+entidad.getVelocidad())/gp.getTamañofinalBaldosa();
                if (entidadLadoAbajoFila%12==0){
                    entidadLadoAbajoFila=0;
                }

                baldosa1 = gp.getGestorBaldosas().numeroMapaBaldosa[entidadLadoAbajoFila][entidadLadoIzquierdoColumna];
                baldosa2 = gp.getGestorBaldosas().numeroMapaBaldosa[entidadLadoAbajoFila][entidadLadoDerechoColumna];
                if (gp.getGestorBaldosas().baldosa[baldosa1].colision || gp.getGestorBaldosas().baldosa[baldosa2].colision){
                    entidad.setColision(true);
                }
                break;
                case "izquierda":
                    if (entidad.getX()+entidad.getVelocidad()<0)
                        entidad.setColision(true);
                    if (entidadLadoAbajoFila%12==0){
                        entidadLadoAbajoFila=0;
                    }
                    entidadLadoIzquierdoColumna = (entidadladoIzquierdo-entidad.getVelocidad())/gp.getTamañofinalBaldosa();
                    baldosa1 = gp.getGestorBaldosas().numeroMapaBaldosa[entidadLadoArribaFila][entidadLadoIzquierdoColumna];
                    baldosa2 = gp.getGestorBaldosas().numeroMapaBaldosa[entidadLadoAbajoFila][entidadLadoIzquierdoColumna];
                    if (gp.getGestorBaldosas().baldosa[baldosa1].colision || gp.getGestorBaldosas().baldosa[baldosa2].colision){
                        entidad.setColision(true);
                    }
                    break;
            case "derecha":
                if (entidad.getX()+entidad.getVelocidad()>gp.getTamañoAnchuraPantalla()-gp.getTamañofinalBaldosa())
                    entidad.setColision(true);
                if (entidadLadoAbajoFila%12==0){
                    entidadLadoAbajoFila=0;
                }
                entidadLadoDerechoColumna = (entidadladoDerecho+entidad.getVelocidad())/gp.getTamañofinalBaldosa();
                baldosa1 = gp.getGestorBaldosas().numeroMapaBaldosa[entidadLadoArribaFila][entidadLadoDerechoColumna];
                baldosa2 = gp.getGestorBaldosas().numeroMapaBaldosa[entidadLadoAbajoFila][entidadLadoDerechoColumna];
                if (gp.getGestorBaldosas().baldosa[baldosa1].colision || gp.getGestorBaldosas().baldosa[baldosa2].colision){
                    entidad.setColision(true);
                }
                break;
            case "arriba-izquierda":
                if (entidad.getX()+entidad.getVelocidadDiagonal()<0||entidad.getY()+entidad.getVelocidadDiagonal()<0)
                    entidad.setColision(true);
                if (entidadLadoAbajoFila%12==0){
                    entidadLadoAbajoFila=0;
                }
                entidadLadoIzquierdoColumna = (int) ((entidadladoIzquierdo-entidad.getVelocidadDiagonal())/gp.getTamañofinalBaldosa());
                entidadLadoArribaFila = (int) ((entidadoladoArriba - entidad.getVelocidadDiagonal()) / gp.getTamañofinalBaldosa());
                baldosa1 = gp.getGestorBaldosas().numeroMapaBaldosa[entidadLadoArribaFila][entidadLadoIzquierdoColumna];
                baldosa2 = gp.getGestorBaldosas().numeroMapaBaldosa[entidadLadoAbajoFila][entidadLadoIzquierdoColumna];
                if (gp.getGestorBaldosas().baldosa[baldosa1].colision || gp.getGestorBaldosas().baldosa[baldosa2].colision){
                    entidad.setColision(true);
                }
                break;

            case "abajo-izquierda":
                if (entidad.getX()+entidad.getVelocidadDiagonal()<0||entidad.getY()+entidad.getVelocidad()> gp.getTamañoAlturaPantalla()-gp.getTamañofinalBaldosa())
                    entidad.setColision(true);
                entidadLadoAbajoFila = (int) ((entidadoladoAbajo+entidad.getVelocidadDiagonal())/gp.getTamañofinalBaldosa());
                if (entidadLadoAbajoFila%12==0){
                    entidadLadoAbajoFila=0;
                }
                entidadLadoIzquierdoColumna = (int) ((entidadladoIzquierdo-entidad.getVelocidadDiagonal())/gp.getTamañofinalBaldosa());
                baldosa1 = gp.getGestorBaldosas().numeroMapaBaldosa[entidadLadoArribaFila][entidadLadoIzquierdoColumna];
                baldosa2 = gp.getGestorBaldosas().numeroMapaBaldosa[entidadLadoAbajoFila][entidadLadoIzquierdoColumna];
                if (gp.getGestorBaldosas().baldosa[baldosa1].colision || gp.getGestorBaldosas().baldosa[baldosa2].colision){
                    entidad.setColision(true);
                }
                break;

            case "abajo-derecha":
                if (entidad.getX()+entidad.getVelocidad()>gp.getTamañoAnchuraPantalla()-gp.getTamañofinalBaldosa()||entidad.getY()+entidad.getVelocidad()> gp.getTamañoAlturaPantalla()-gp.getTamañofinalBaldosa())
                    entidad.setColision(true);
                entidadLadoAbajoFila = (int) ((entidadoladoAbajo+entidad.getVelocidadDiagonal())/gp.getTamañofinalBaldosa());
                if (entidadLadoAbajoFila%12==0){
                    entidadLadoAbajoFila=0;
                }
                entidadLadoDerechoColumna = (int) ((entidadladoDerecho+entidad.getVelocidadDiagonal())/gp.getTamañofinalBaldosa());
                baldosa1 = gp.getGestorBaldosas().numeroMapaBaldosa[entidadLadoArribaFila][entidadLadoDerechoColumna];
                baldosa2 = gp.getGestorBaldosas().numeroMapaBaldosa[entidadLadoAbajoFila][entidadLadoDerechoColumna];
                if (gp.getGestorBaldosas().baldosa[baldosa1].colision || gp.getGestorBaldosas().baldosa[baldosa2].colision){
                    entidad.setColision(true);
                }
                break;

            case "arriba-derecha":
                if (entidad.getX()+entidad.getVelocidad()>gp.getTamañoAnchuraPantalla()-gp.getTamañofinalBaldosa()||entidad.getY()+entidad.getVelocidadDiagonal()<0)
                    entidad.setColision(true);
                if (entidadLadoAbajoFila%12==0){
                    entidadLadoAbajoFila=0;
                }
                entidadLadoArribaFila = (int) ((entidadoladoArriba - entidad.getVelocidadDiagonal()) / gp.getTamañofinalBaldosa());
                entidadLadoDerechoColumna = (int) ((entidadladoDerecho+entidad.getVelocidadDiagonal())/gp.getTamañofinalBaldosa());
                baldosa1 = gp.getGestorBaldosas().numeroMapaBaldosa[entidadLadoArribaFila][entidadLadoDerechoColumna];
                baldosa2 = gp.getGestorBaldosas().numeroMapaBaldosa[entidadLadoAbajoFila][entidadLadoDerechoColumna];
                if (gp.getGestorBaldosas().baldosa[baldosa1].colision||gp.getGestorBaldosas().baldosa[baldosa2].colision){
                    entidad.setColision(true);
                }
                break;


        }

    }

    public int comprobarObjetos(Entidad entidad,boolean jugador) {

        int index=999;

        for (int i=0;i<gp.arrayobjetos.length;i++){
            if (gp.arrayobjetos[i]!= null){
                //Saber la hitbox del player
                entidad.zonaDeColision.x = (int) (entidad.getX()+ entidad.zonaDeColision.x);
                entidad.zonaDeColision.y = (int) (entidad.getY()+ entidad.zonaDeColision.y);
                //Saber la hitbox del objeto
                gp.arrayobjetos[i].zonaDeColision.x = (int) ( gp.arrayobjetos[i].getX()+ gp.arrayobjetos[i].zonaDeColision.x);
                gp.arrayobjetos[i].zonaDeColision.y = (int) ( gp.arrayobjetos[i].getY()+ gp.arrayobjetos[i].zonaDeColision.y);

                switch (entidad.getDireccion()){
                    case "arriba":
                        entidad.zonaDeColision.y -= entidad.getVelocidad();
                        if (entidad.zonaDeColision.intersects(gp.arrayobjetos[i].zonaDeColision)){
                            if (gp.arrayobjetos[i].isColision()){
                                entidad.setColision(true);
                                if (jugador){
                                    index = i;
                                }
                            }
                        }
                        break;
                        case "derecha":
                            entidad.zonaDeColision.x += entidad.getVelocidad();
                            if (entidad.zonaDeColision.intersects(gp.arrayobjetos[i].zonaDeColision)){
                                if (gp.arrayobjetos[i].isColision()){
                                    entidad.setColision(true);
                                    if (jugador){
                                        index = i;
                                    }
                                }
                            }
                            break;
                            case "abajo":
                                entidad.zonaDeColision.y += entidad.getVelocidad();
                                if (entidad.zonaDeColision.intersects(gp.arrayobjetos[i].zonaDeColision)){
                                    if (gp.arrayobjetos[i].isColision()){
                                        entidad.setColision(true);
                                        if (jugador){
                                            index = i;
                                        }
                                    }
                                }
                                break;
                                case "izquierda":
                                    entidad.zonaDeColision.x -= entidad.getVelocidad();
                                    if (entidad.zonaDeColision.intersects(gp.arrayobjetos[i].zonaDeColision)){
                                        if (gp.arrayobjetos[i].isColision()){
                                            entidad.setColision(true);
                                            if (jugador){
                                                index = i;
                                            }
                                        }
                                    }
                                    break;
                                    case "arriba-derecha":
                                        entidad.zonaDeColision.y -= (int) entidad.getVelocidadDiagonal();
                                        entidad.zonaDeColision.x += (int) entidad.getVelocidadDiagonal();
                                        if (entidad.zonaDeColision.intersects(gp.arrayobjetos[i].zonaDeColision)){
                                            if (gp.arrayobjetos[i].isColision()){
                                                entidad.setColision(true);
                                                if (jugador){
                                                    index = i;
                                                }
                                            }
                                        }
                                        break;
                    case "arriba-izquierda":
                        entidad.zonaDeColision.y -= (int) entidad.getVelocidadDiagonal();
                        entidad.zonaDeColision.x -= (int) entidad.getVelocidadDiagonal();
                        if (entidad.zonaDeColision.intersects(gp.arrayobjetos[i].zonaDeColision)){
                            if (gp.arrayobjetos[i].isColision()){
                                entidad.setColision(true);
                                if (jugador){
                                    index = i;
                                }
                            }
                        }
                        break;
                    case "abajo-derecha":
                        entidad.zonaDeColision.y += (int) entidad.getVelocidadDiagonal();
                        entidad.zonaDeColision.x += (int) entidad.getVelocidadDiagonal();
                        if (entidad.zonaDeColision.intersects(gp.arrayobjetos[i].zonaDeColision)){
                            if (gp.arrayobjetos[i].isColision()){
                                entidad.setColision(true);
                                if (jugador){
                                    index = i;
                                }
                            }
                        }
                        break;
                    case "abajo-izquierda":
                        entidad.zonaDeColision.y += (int) entidad.getVelocidadDiagonal();
                        entidad.zonaDeColision.x -= (int) entidad.getVelocidadDiagonal();
                        if (entidad.zonaDeColision.intersects(gp.arrayobjetos[i].zonaDeColision)){
                            if (gp.arrayobjetos[i].isColision()){
                                entidad.setColision(true);
                                if (jugador){
                                    index = i;
                                }
                            }
                        }
                        break;
                }
                entidad.zonaDeColision.x =entidad.getZonaDeColisionDefectoX();
                entidad.zonaDeColision.y =entidad.getZonaDeColisionDefectoY();
                gp.arrayobjetos[i].zonaDeColision.x = gp.arrayobjetos[i].zonaDeColisionDefectoX;
                gp.arrayobjetos[i].zonaDeColision.y= gp.arrayobjetos[i].zonaDeColisionDefectoY;
            }
        }
        return index;
    }

    public int comprobarEntidad(Entidad entidad,boolean jugador) {

        int index=999;

        for (int i=0;i<gp.arrayEnemigos.size();i++){
            if (gp.arrayEnemigos.get(i)!= null){
                //Saber la hitbox del player
                entidad.zonaDeColision.x = (int) (entidad.getX()+ entidad.zonaDeColision.x);
                entidad.zonaDeColision.y = (int) (entidad.getY()+ entidad.zonaDeColision.y);
                //Saber la hitbox del objeto
                gp.arrayEnemigos.get(i).zonaDeColision.x = (int) ( gp.arrayEnemigos.get(i).getX()+ gp.arrayEnemigos.get(i).zonaDeColision.x);
                gp.arrayEnemigos.get(i).zonaDeColision.y = (int) ( gp.arrayEnemigos.get(i).getY()+ gp.arrayEnemigos.get(i).zonaDeColision.y);

                switch (entidad.getDireccion()){
                    case "arriba":
                        entidad.zonaDeColision.y -= entidad.getVelocidad();
                        if (entidad.zonaDeColision.intersects(gp.arrayEnemigos.get(i).zonaDeColision)){
                            if (gp.arrayEnemigos.get(i).isColision()){
                                entidad.setColision(true);
                                if (jugador){
                                    index = i;
                                }
                            }
                        }
                        break;
                    case "derecha":
                        entidad.zonaDeColision.x += entidad.getVelocidad();
                        if (entidad.zonaDeColision.intersects(gp.arrayEnemigos.get(i).zonaDeColision)){
                            if (gp.arrayEnemigos.get(i).isColision()){
                                entidad.setColision(true);
                                if (jugador){
                                    index = i;
                                }
                            }
                        }
                        break;
                    case "abajo":
                        entidad.zonaDeColision.y += entidad.getVelocidad();
                        if (entidad.zonaDeColision.intersects(gp.arrayEnemigos.get(i).zonaDeColision)){
                            if (gp.arrayEnemigos.get(i).isColision()){
                                entidad.setColision(true);
                                if (jugador){
                                    index = i;
                                }
                            }
                        }
                        break;
                    case "izquierda":
                        entidad.zonaDeColision.x -= entidad.getVelocidad();
                        if (entidad.zonaDeColision.intersects(gp.arrayEnemigos.get(i).zonaDeColision)){
                            if (gp.arrayEnemigos.get(i).isColision()){
                                entidad.setColision(true);
                                if (jugador){
                                    index = i;
                                }
                            }
                        }
                        break;
                    case "arriba-derecha":
                        entidad.zonaDeColision.y -= (int) entidad.getVelocidadDiagonal();
                        entidad.zonaDeColision.x += (int) entidad.getVelocidadDiagonal();
                        if (entidad.zonaDeColision.intersects(gp.arrayEnemigos.get(i).zonaDeColision)){
                            if (gp.arrayEnemigos.get(i).isColision()){
                                entidad.setColision(true);
                                if (jugador){
                                    index = i;
                                }
                            }
                        }
                        break;
                    case "arriba-izquierda":
                        entidad.zonaDeColision.y -= (int) entidad.getVelocidadDiagonal();
                        entidad.zonaDeColision.x -= (int) entidad.getVelocidadDiagonal();
                        if (entidad.zonaDeColision.intersects(gp.arrayEnemigos.get(i).zonaDeColision)){
                            if (gp.arrayEnemigos.get(i).isColision()){
                                entidad.setColision(true);
                                if (jugador){
                                    index = i;
                                }
                            }
                        }
                        break;
                    case "abajo-derecha":
                        entidad.zonaDeColision.y += (int) entidad.getVelocidadDiagonal();
                        entidad.zonaDeColision.x += (int) entidad.getVelocidadDiagonal();
                        if (entidad.zonaDeColision.intersects(gp.arrayEnemigos.get(i).zonaDeColision)){
                            if (gp.arrayEnemigos.get(i).isColision()){
                                entidad.setColision(true);
                                if (jugador){
                                    index = i;
                                }
                            }
                        }
                        break;
                    case "abajo-izquierda":
                        entidad.zonaDeColision.y += (int) entidad.getVelocidadDiagonal();
                        entidad.zonaDeColision.x -= (int) entidad.getVelocidadDiagonal();
                        if (entidad.zonaDeColision.intersects(gp.arrayEnemigos.get(i).zonaDeColision)){
                            if (gp.arrayEnemigos.get(i).isColision()){
                                entidad.setColision(true);
                                if (jugador){
                                    index = i;
                                }
                            }
                        }
                        break;
                }
                entidad.zonaDeColision.x =entidad.getZonaDeColisionDefectoX();
                entidad.zonaDeColision.y =entidad.getZonaDeColisionDefectoY();
                gp.arrayEnemigos.get(i).zonaDeColision.x = gp.arrayEnemigos.get(i).getZonaDeColisionDefectoX();
                gp.arrayEnemigos.get(i).zonaDeColision.y= gp.arrayEnemigos.get(i).getZonaDeColisionDefectoY();
            }
        }
        return index;
    }



}
