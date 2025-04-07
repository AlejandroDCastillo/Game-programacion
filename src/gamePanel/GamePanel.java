package gamePanel;
import entidades.*;
import recursos.teclado.DetectorTeclas;

import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel implements Runnable{
     Thread threadJuego;
     private DetectorTeclas teclado = new DetectorTeclas();
    private boolean running = true;
    private int tamañoBaldosa=32;
    private int escala =2;
    protected int tamañofinalBaldosa=tamañoBaldosa*escala;
    protected int cantidadBaldosaAnchura=16;
    protected int getCantidadBaldosaAltura=12;
    private int tamañoAnchuraPantalla=cantidadBaldosaAnchura*tamañofinalBaldosa;
    private int tamañoAlturaPantalla=getCantidadBaldosaAltura*tamañofinalBaldosa;
    protected int FPS = 60;
    private Jugador jugador = new Jugador(this.teclado,this);
    public GamePanel() {

        this.setBackground(Color.BLACK);
        this.setFocusable(true);
        this.setDoubleBuffered(true);
        this.setPreferredSize(new Dimension(tamañoAnchuraPantalla,tamañoAlturaPantalla));
        startThreadDelJuego();
    }

    public void startThreadDelJuego() {
        threadJuego = new Thread(this);
        threadJuego.start();
    }

    public void run(){
        double delta =0;
        double intervaloDeDibujo=1000000000/FPS;
        long ultimoTiempo=System.nanoTime();
        long tiempoActual; long temporizador=0; int contadorDeVecesDibujado=0;
        while(threadJuego!=null) {
          tiempoActual = System.nanoTime();
          delta += (tiempoActual - ultimoTiempo)/intervaloDeDibujo;
          temporizador += (tiempoActual - ultimoTiempo);
          ultimoTiempo = tiempoActual;
          if(delta >= 1) {
              update();
              repaint();
              delta --;
              if (temporizador >= 1000000000) {
                  System.out.println("FPS: " + contadorDeVecesDibujado);
                  contadorDeVecesDibujado=0;
                  temporizador=0;
              }
              contadorDeVecesDibujado++;
          }
        }
    }

    private void update() {
    }

    public void paintComponents(Graphics g) {
        super.paintComponents(g);
        Graphics2D g2d = (Graphics2D) g;
        jugador.dibujar(g2d);
    }

}
