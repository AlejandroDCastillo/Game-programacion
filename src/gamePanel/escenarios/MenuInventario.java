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
            g2d.drawImage(fondoPanel("src/imagenes/inventario.png"), (gp.getTamañofinalBaldosa()*3)+15,(gp.getTamañofinalBaldosa()*3)+15, 572, 316, null);
            Inventario.getInstance().mostrarInventario(g2d);
        }
        g2d.dispose();
    }
}
