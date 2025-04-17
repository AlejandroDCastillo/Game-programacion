package gamePanel.escenarios;

import gamePanel.GamePanel;
import item.Inventario;
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
    public BufferedImage fondoPanel(String imagePath) {
        try {
            // Cargar la imagen
            return ImageIO.read(new File(imagePath));
        } catch (IOException e) {
            e.printStackTrace();
            System.err.println("Error al cargar la imagen: " + e.getMessage());
        }
        return null;
    }

    public void update() {
        setVisible(teclado.menuBoolean);
    }

    public void dibujar(Graphics g) {
        super.paint(g);
        Graphics2D g2d = (Graphics2D) g;
        if (teclado.menuBoolean) {
            g2d.drawImage(fondoPanel("src/recursos/imagenes/inventario.png"), (gp.getTamañofinalBaldosa()),(gp.getTamañofinalBaldosa()*2), 572, 316, null);

            Inventario.getInstance().mostrarInventario(g2d,this);
        }
        g2d.dispose();
    }

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
