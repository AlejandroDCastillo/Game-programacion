package gamePanel;

import java.awt.*;

public class UI {

    GamePanel gp;
    Font fonte = new Font("Arial", Font.BOLD, 20);
    String mensaje = "";
    boolean hayMensaje=false;
    public boolean juegoTerminado=false;
    int contadorMensaje =0;
    public UI(GamePanel gp) {
        this.gp=gp;
    }

    public void enseñarMensaje(String texto) {
        mensaje=texto;
        hayMensaje=true;
    }

    public void draw(Graphics2D g2d) {

        g2d.setFont(fonte);
        g2d.setColor(Color.WHITE);
        if (hayMensaje) {
            g2d.drawString(mensaje, 10, 20);
            contadorMensaje++;
        }
        if (contadorMensaje >= 120) {
            contadorMensaje=0;
            hayMensaje=false;
        }
    }
}
