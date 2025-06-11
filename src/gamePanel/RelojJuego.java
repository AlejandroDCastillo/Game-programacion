package gamePanel;

import item.Inventario;

import javax.swing.*;
import java.awt.*;


public class RelojJuego extends JPanel {
    public GamePanel gp;
    private static RelojJuego instancia;
    private long tiempoTotal; // en milisegundos
    private long ultimoTiempo; // tiempo del último update

    private RelojJuego(GamePanel gp) {
        this.tiempoTotal = 0;
        this.ultimoTiempo = System.currentTimeMillis();
        this.gp=gp;
    }

    /**
     * metodo para crear un singleton
     * @return
     */
    public static RelojJuego getInstance(GamePanel gp) {
        if (instancia == null) {
            instancia = new RelojJuego(gp);
        }
        return instancia;
    }


    public void update() {
        long ahora = System.currentTimeMillis();
        long delta = ahora - ultimoTiempo;
        tiempoTotal += delta;
        ultimoTiempo = ahora;
    }
    public void dibujar(Graphics g) {
        super.paint(g);
        Graphics2D g2d = (Graphics2D) g;
        if (gp.estadoJuego!=gp.menuInicio) {
            System.out.println("Escribiendo tiempo");
            gp.getInterfaz().dibujarVentanaGenerica(0,0,160,60);
            gp.getInterfaz().dibujarTextoSombreado(getTiempoFormateado(), 20, 40,40);

        }
        g2d.dispose();
    }

    public String getTiempoFormateado() {
        long segundosTotales = tiempoTotal / 1000;
        long minutos = segundosTotales / 60;
        long segundos = segundosTotales % 60;
        return String.format("%02d:%02d", minutos, segundos);
    }

    public long getTiempoTotal() {
        return tiempoTotal;
    }

    public int getMinutosJuego() {
        return (int) (tiempoTotal / 60000); // opcional, para simplificar comparaciones
    }

    public void reiniciar() {
        this.tiempoTotal = 0;
        this.ultimoTiempo = System.currentTimeMillis();
    }
}

