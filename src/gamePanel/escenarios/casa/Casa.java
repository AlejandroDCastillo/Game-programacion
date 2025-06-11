package gamePanel.escenarios.casa;

import gamePanel.GamePanel;
import recursos.imagenes.Spritesheet;
import recursos.teclado.DetectorTeclas;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Casa extends JPanel {
    //gamepanel
    GamePanel gp;
    private DetectorTeclas teclado;
    //estados del lovi
    public int estadoCasa;
    public final int SALIR = 0;
    public final int START = 1;
    public final int GRANJA = 6;
    public final int CAMA = 2;
    public final int MESA = 3;
    public final int POCIONES = 4;
    public final int FORJA = 5;

    public Casa(GamePanel gp) {
        this.gp = gp;
        this.teclado = gp.getTeclado();
        this.setFocusable(true);
        this.setDoubleBuffered(true);
        this.setPreferredSize(new Dimension(gp.getWidth(), gp.getHeight()));
        setLayout(null);
        setBackground(Color.WHITE);
        setVisible(false);
        //iniciamos el estado de la casa.
        this.estadoCasa = 1;
    }

    public BufferedImage fondoPanel(String imagePath) {
        try {
            // Cargar la imagen
            BufferedImage casa = ImageIO.read(new File(imagePath));
            Spritesheet plantillaCasa = new Spritesheet(casa, 5, 2);
            return plantillaCasa.getImg(0, 0, gp.getTamañofinalBaldosa());
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

    }


}
