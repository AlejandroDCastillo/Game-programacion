package gamePanel;
import entidades.*;
import gamePanel.escenarios.MenuInventario;
import gestores.*;
import item.objetos.Objetos;
import recursos.mapas.GeneradorMapa;
import recursos.musica.Musica;
import recursos.teclado.DetectorTeclas;
import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class GamePanel extends JPanel implements Runnable{
    //Creamos el hilo para que no suscedan conflictos con los procesos
     Thread threadJuego;
     //creamos el detector de teclas(KeyListener)
     private DetectorTeclas teclado = new DetectorTeclas(this);
     //creamos una bandera para indicar que el juego esta funcionando
    private boolean running = true;
    //taamaño inicial de baldosa
    private int tamañoBaldosa=16;
    //escalado para que se proporcional todo
    private int escala =3;
    //especificamos tamaños altura y anchura de baldosa
    protected int tamañofinalBaldosa=tamañoBaldosa*escala;
    //especificamos cantidad de baldosas a lo largo y ancho de la pantalla
    protected int cantidadBaldosaAnchura=16;
    protected int getCantidadBaldosaAltura=12;
    //especificamoos el tamaño total del gamePANEL
    private int tamañoAnchuraPantalla=cantidadBaldosaAnchura*tamañofinalBaldosa;
    private int tamañoAlturaPantalla=getCantidadBaldosaAltura*tamañofinalBaldosa;
    //Frames por segundo
    protected int FPS = 60;
    //MUSICA
    Musica musica=new Musica();
    //Cosas de la pantalla
    private Jugador jugador;
    private MenuInventario menuInventario = new MenuInventario(this);
    public GeneradorMapa generadorMapa = new GeneradorMapa(this);
    private GestorBaldosas gestorBaldosas = new GestorBaldosas(this);
    public DetectorDeColisiones detectorDeColisiones = new DetectorDeColisiones(this);
    public GestorAssets gestorAssets = new GestorAssets(this);
    public Objetos arrayobjetos[] = new Objetos[4];
    public Entidad arrayEnemigos[]= new Entidad[10];
    private UI interfaz= new UI(this);
    private ArrayList<Entidad> arrayEntidad= new ArrayList<>();
    //ESTADO DEL JUEGO
    public final int menuInicio=0;
    public int estadoJuego=1;
    public final int continuar=2;
    public final int pausa=3;
    public final int cargarPartida=4;
    public final int inventario=5;
    public final int craftear=6;
    public final int combate=7;
    public int gameOver=8;
    public GestorDeEventos gestorDeEventos = new GestorDeEventos(this);
    public GestorCombate gc;


    public GamePanel() {
        this.setBackground(Color.BLACK);
        this.setFocusable(true);
        this.setDoubleBuffered(true);
        this.setPreferredSize(new Dimension(tamañoAnchuraPantalla,tamañoAlturaPantalla));
        this.addKeyListener(teclado);
        this.add(menuInventario);
        startThreadDelJuego();
        establecerJuego();
    }


    public void establecerJuego(){
        gestorAssets.establecerObjetos();
        gestorAssets.establecerEnemigos();
        empezarMusica(1);
        estadoJuego=menuInicio;
    }

    public void startThreadDelJuego() {
        threadJuego = new Thread(this);
        threadJuego.start();
    }

    /**
     * Nuestro game loop (bucle del juego infinito)
     */
    public void run(){
        double delta =0;
        double intervaloDeDibujo=1000000000/FPS;
        long ultimoTiempo=System.nanoTime();
        long tiempoActual; long temporizador=0; int contadorDeVecesDibujado=0; int contadorTiempoDibujado=0;
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
                  //System.out.printf("X:" +jugador.getX() +"Y:"+jugador.getY());
                  contadorDeVecesDibujado=0;

              }
              contadorDeVecesDibujado++;

          }


        }
    }

    private void update() {
        if (estadoJuego==continuar) {
            jugador.update();
            for (int i =0;i<arrayEnemigos.length;i++){
                if (arrayEnemigos[i]!=null){
                    arrayEnemigos[i].update();
                }
            }
        }else if (estadoJuego==pausa) {
            //no sucede nada
        }else if(estadoJuego==inventario){
            menuInventario.update();
        }
    }

    public void paint(Graphics g) {
        super.paint(g);
        Graphics2D g2d = (Graphics2D) g;
            //menu inicio
            if(estadoJuego==menuInicio) {
                //dibuja el menu de inicio
                interfaz.dibujar(g2d);
            }else {
                //el resto de cosas
                gestorBaldosas.dibujar(g2d);
                arrayEntidad.add(jugador);
                for (int i = 0; i < arrayEnemigos.length; i++) {
                    if (arrayEnemigos[i] != null) {
                        arrayEntidad.add(arrayEnemigos[i]);
                    }
                }
                for (int i = 0; i < arrayobjetos.length; i++) {
                    if (arrayobjetos[i] != null) {
                        arrayEntidad.add( arrayobjetos[i]);
                    }
                }

                //Ordenar la arrayList de entidad
                Collections.sort(arrayEntidad, new Comparator<Entidad>() {
                    @Override
                    public int compare(Entidad o1, Entidad o2) {

                        int resultado = Integer.compare((int) o1.getY(), (int) o2.getY());
                        return resultado;
                    }
                });
                //Dibujar Entidades
                for (int i = 0; i < arrayEntidad.size(); i++) {
                    arrayEntidad.get(i).dibujar(g2d);
                }
                //Vaciar lista Entidad
                for (int i = 0; i < arrayEntidad.size(); i++) {
                    arrayEntidad.remove(i);
                }
                interfaz.dibujar(g2d);
                menuInventario.dibujar(g2d);
            }
        g2d.dispose();


    }

    /**
     * sirve para iniciar la musica que deseemos de la coleccion URL
     * @param i indice que deseamos escoger
     */
    public void empezarMusica(int i){
        musica.setArchivo(i);
        musica.empezar();
        musica.bucle();
    }

    public void pararMusica(){
        musica.parar();
    }

    /**
     * sirve para efectos de sonido
     * @param i
     */
    public void efectoSonido(int i){
        musica.setArchivo(i);
        musica.empezar();
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

    public MenuInventario getMenuInventario() {
        return menuInventario;
    }

    public Musica getMusica() {
        return musica;
    }

    public void setMusica(Musica musica) {
        this.musica = musica;
    }

    public void setMenuInventario(MenuInventario menuInventario) {
        this.menuInventario = menuInventario;
    }

    public DetectorDeColisiones getDetectorDeColisiones() {
        return detectorDeColisiones;
    }

    public void setDetectorDeColisiones(DetectorDeColisiones detectorDeColisiones) {
        this.detectorDeColisiones = detectorDeColisiones;
    }

    public GestorBaldosas getGestorBaldosas() {
        return gestorBaldosas;
    }

    public void setGestorBaldosas(GestorBaldosas gestorBaldosas) {
        this.gestorBaldosas = gestorBaldosas;
    }

    public UI getInterfaz() {
        return interfaz;
    }

    public void setInterfaz(UI interfaz) {
        this.interfaz = interfaz;
    }
}
