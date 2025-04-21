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
                System.out.println("Baldosa1:" + baldosa1);
                System.out.println("Baldosa2:" + baldosa2);
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
                System.out.println("Baldosa1:"+ baldosa1);
                System.out.println("Baldosa2:"+ baldosa2);
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
                    System.out.println("Baldosa 1 posicion"+ entidadLadoArribaFila +" y "+entidadLadoDerechoColumna);
                    System.out.println("baldosa 2 posicion"+ entidadLadoAbajoFila +" y "+entidadLadoDerechoColumna);
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
                System.out.println("Baldosa 1 arriba izquierda"+ baldosa1);
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
                System.out.println("Baldosa 2 abajo izquierda"+ baldosa2);
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
                System.out.println("Baldosa2 abajo derecha"+ baldosa2);
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
                System.out.println("Baldosa 1 arriba derecha"+ baldosa1);
                if (gp.getGestorBaldosas().baldosa[baldosa1].colision||gp.getGestorBaldosas().baldosa[baldosa2].colision){
                    entidad.setColision(true);
                }
                break;


        }

    }


}
