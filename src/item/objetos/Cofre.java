package item.objetos;

import gamePanel.GamePanel;
import recursos.imagenes.Spritesheet;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Cofre extends Objetos {
    public Cofre() {
        this.nombre = "cofre";
        try {
            BufferedImage imagenPlantillaBuffered =  ImageIO.read(new File("src/recursos/imagenes/AssetsDeInventario.png"));
             sprite = new Spritesheet(imagenPlantillaBuffered,13,9);
            imagen = sprite.getImg(9,1,48);
        }catch (IOException e){
            e.printStackTrace();
        }
        colision = true;
    }



}
