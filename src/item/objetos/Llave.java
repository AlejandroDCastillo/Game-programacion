package item.objetos;

import gamePanel.GamePanel;
import recursos.imagenes.Spritesheet;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Llave extends Objetos {

    /**
     * constructor de llave
     *
     * @param gp
     */
    public Llave(GamePanel gp) {
        super(gp);
        objetoInteractuado = 1;
        this.nombre = "llave";
        try {
            BufferedImage imagenPlantillaBuffered = ImageIO.read(new File("src/recursos/imagenes/AssetsDeInventario.png"));
            Spritesheet plantillaInventario = new Spritesheet(imagenPlantillaBuffered, 13, 9);
            imagen = plantillaInventario.rotarImagen(plantillaInventario.getImg(6, 5, 48), 180);
            sprite = plantillaInventario.rotarImagen(plantillaInventario.getImg(6, 5, 48), 90);
        } catch (IOException e) {
            e.printStackTrace();
        }
        colision = true;
    }
}
