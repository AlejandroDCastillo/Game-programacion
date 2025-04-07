package entidades;
import recursos.teclado.DetectorTeclas;
import gamePanel.GamePanel;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;

public class Jugador extends Entidad {
    private DetectorTeclas teclado;
    private GamePanel gp;
    protected BufferedImage sprite;
    public Jugador(DetectorTeclas teclado, GamePanel gp) {
        this.teclado = teclado;
        this.gp = gp;
    }
    public void establecerValoresPredeterminados(){
        //Aqui estableceré tambien fuerza,destreza,etc...
        x= 100;
        y= 100;
        velocidadMax = 5;
        velocidad = 3;
    }
    public void update() {
        if (!teclado.abajo || !teclado.izquierda || !teclado.arriba || !teclado.derecha) {
            return;
        }

        if (teclado.izquierda == true) {
            direccion = "izquierda";
            x = x - velocidad;
        }
        if (teclado.derecha == true) {
            direccion = "derecha";
            x = x + velocidad;
        }
        if (teclado.arriba == true) {
            direccion = "arriba";
            y = y - velocidad;
        }
        if (teclado.abajo == true) {
            direccion = "arriba";
            y = y - velocidad;
        }
        contadorUpdates++;
        if (contadorUpdates == 10) {
            if (numSprite <= 1) {
                numSprite++;
            } else if (numSprite >= 10) {
                numSprite = 1;
            }
        }
    }
    public void obtenerImagenPlayer(){
       String ruta = "../recursos/imagenes/"+direccion+numSprite+".png";
       sprite.getClass().getResourceAsStream(ruta);
    }


    public void dibujar(Graphics2D g2d){

    }
}
