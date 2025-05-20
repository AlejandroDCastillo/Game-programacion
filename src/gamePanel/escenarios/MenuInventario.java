package gamePanel.escenarios;

import gamePanel.GamePanel;
import item.Inventario;
import recursos.imagenes.Spritesheet;
import recursos.teclado.DetectorTeclas;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class MenuInventario extends JPanel {
    private GamePanel gp;
    private DetectorTeclas teclado;

    /**
     * inicializa una clase para el menu de inventario, el resto de menus los hicimos dentro del UI en lugar de como clase
     * @param gp
     */
    public MenuInventario(GamePanel gp) {
        this.gp = gp;
        this.teclado = gp.getTeclado();
        this.setFocusable(true);
        this.setDoubleBuffered(true);
        this.setPreferredSize(new Dimension(gp.getWidth(),gp.getHeight()));
        setLayout(null);
        setBackground(Color.WHITE);
        setVisible(false);
    }

    /**
     * carga la imagen del inventario para cuando inventario este true
     * @param imagePath
     * @return
     */
    public BufferedImage fondoPanel(String imagePath) {
        try {
            // Cargar la imagen
            BufferedImage inventario =  ImageIO.read(new File(imagePath));
            Spritesheet plantillaInventario = new Spritesheet(inventario,5,2);
            return plantillaInventario.getImg(0,0, gp.getTamañofinalBaldosa());
        } catch (IOException e) {
            e.printStackTrace();
            System.err.println("Error al cargar la imagen: " + e.getMessage());
        }
        return null;
    }

    /**
     * metodo que actualiza el inventario para poder visualizar en tiempo real los items disponibles
     */
    public void update() {
        setVisible(teclado.menuBoolean);
    }

    /**
     * dibuja la imagen del inventario en el Jpanel
     * @param g
     */
    public void dibujar(Graphics g) {
        super.paint(g);
        Graphics2D g2d = (Graphics2D) g;
        if (gp.estadoJuego==gp.inventario) {
            g2d.drawImage(fondoPanel("src/recursos/imagenes/menuInventario.png"),143,123, 451, 400, null);
            Inventario.getInstance().mostrarInventario(g2d,this,gp.getJugador());
        }
        g2d.dispose();
    }
//getters and setters
    public DetectorTeclas getTeclado() {
        return teclado;
    }

    public void setTeclado(DetectorTeclas teclado) {
        this.teclado = teclado;
    }

    public GamePanel getGp() {
        return gp;
    }

    public void setGp(GamePanel gp) {
        this.gp = gp;
    }
}
