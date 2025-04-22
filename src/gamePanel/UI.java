package gamePanel;

import item.objetos.Llave;
import item.objetos.Objetos;

import java.awt.*;
import java.awt.image.BufferedImage;

public class UI {

    GamePanel gp;
    Font fonte = new Font("Arial", Font.ITALIC, 30);
    String mensaje = "";
    boolean hayMensaje=false;
    public boolean juegoTerminado=false;
    Graphics2D g2d;
    int contadorMensaje =0;
    BufferedImage imagen_llave;

    public UI(GamePanel gp) {
        Objetos llave = new Llave();
        imagen_llave = llave.getImagen();
        this.gp=gp;
    }

    public void enseñarMensaje(String texto) {
        mensaje=texto;
        hayMensaje=true;
    }

    public void dibujar(Graphics2D g2d) {
        this.g2d=g2d;
        g2d.setFont(fonte);
        g2d.setColor(Color.WHITE);
        g2d.drawImage(imagen_llave, gp.getTamañofinalBaldosa()/2, gp.getTamañofinalBaldosa()/2, gp.getTamañofinalBaldosa(), gp.getTamañofinalBaldosa(), null);
        g2d.drawString("x"+ gp.getJugador().getLlaves(),60,65);
        if (hayMensaje) {
            g2d.drawString(mensaje, gp.getTamañoAnchuraPantalla()/4, 30);
            System.out.print(mensaje);
            contadorMensaje++;
        }
        if (contadorMensaje >= 100) {
            contadorMensaje=0;
            hayMensaje=false;
        }

        if (gp.estadoJuego == gp.pausa){
            Font fuentePausa = new Font("Arial", Font.ITALIC, 50);
            g2d.setColor(Color.WHITE);
            g2d.setFont(fuentePausa);
            g2d.drawString("JUEGO EN PAUSA", gp.getTamañoAnchuraPantalla()/4,gp.getTamañoAlturaPantalla()/2);

        }
    }
}
