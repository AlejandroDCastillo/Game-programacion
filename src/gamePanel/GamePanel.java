package gamePanel;
import entidades.*;
import recursos.teclado.DetectorTeclas;
import javax.swing.*;
import java.awt.*;
import java.io.IOException;

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
    private Jugador jugador = new Jugador(this.teclado,this,"alex",Raza.HUMANO,Clase.MAGO,2);

    public GamePanel() {
        this.setBackground(Color.BLACK);
        this.setFocusable(true);
        this.setDoubleBuffered(true);
        this.setPreferredSize(new Dimension(tamañoAnchuraPantalla,tamañoAlturaPantalla));
        this.addKeyListener(teclado);
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
                  System.out.println(jugador.getX());
                  contadorDeVecesDibujado=0;
                  temporizador=0;
              }
              contadorDeVecesDibujado++;
          }
        }
    }

    private void update() {
        jugador.update();
    }

    public void paint(Graphics g) {
        super.paint(g);
        Graphics2D g2d = (Graphics2D) g;
        try {
            jugador.dibujar(g2d);
        }catch (IOException e){
            throw new RuntimeException(e);
        }
        g2d.dispose();
    }


    public Thread getThreadJuego() {
        return threadJuego;
    }

    public void setThreadJuego(Thread threadJuego) {
        this.threadJuego = threadJuego;
    }

    public DetectorTeclas getTeclado() {
        return teclado;
    }

    public void setTeclado(DetectorTeclas teclado) {
        this.teclado = teclado;
    }

    public boolean isRunning() {
        return running;
    }

    public void setRunning(boolean running) {
        this.running = running;
    }

    public int getTamañoBaldosa() {
        return tamañoBaldosa;
    }

    public void setTamañoBaldosa(int tamañoBaldosa) {
        this.tamañoBaldosa = tamañoBaldosa;
    }

    public int getEscala() {
        return escala;
    }

    public void setEscala(int escala) {
        this.escala = escala;
    }

    public int getTamañofinalBaldosa() {
        return tamañofinalBaldosa;
    }

    public void setTamañofinalBaldosa(int tamañofinalBaldosa) {
        this.tamañofinalBaldosa = tamañofinalBaldosa;
    }

    public int getCantidadBaldosaAnchura() {
        return cantidadBaldosaAnchura;
    }

    public void setCantidadBaldosaAnchura(int cantidadBaldosaAnchura) {
        this.cantidadBaldosaAnchura = cantidadBaldosaAnchura;
    }

    public int getGetCantidadBaldosaAltura() {
        return getCantidadBaldosaAltura;
    }

    public void setGetCantidadBaldosaAltura(int getCantidadBaldosaAltura) {
        this.getCantidadBaldosaAltura = getCantidadBaldosaAltura;
    }

    public int getTamañoAnchuraPantalla() {
        return tamañoAnchuraPantalla;
    }

    public void setTamañoAnchuraPantalla(int tamañoAnchuraPantalla) {
        this.tamañoAnchuraPantalla = tamañoAnchuraPantalla;
    }

    public int getTamañoAlturaPantalla() {
        return tamañoAlturaPantalla;
    }

    public void setTamañoAlturaPantalla(int tamañoAlturaPantalla) {
        this.tamañoAlturaPantalla = tamañoAlturaPantalla;
    }

    public int getFPS() {
        return FPS;
    }

    public void setFPS(int FPS) {
        this.FPS = FPS;
    }

    public Jugador getJugador() {
        return jugador;
    }

    public void setJugador(Jugador jugador) {
        this.jugador = jugador;
    }
}
