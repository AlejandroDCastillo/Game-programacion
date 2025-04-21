package item.objetos;

import item.Item;
import recursos.imagenes.Spritesheet;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Puerta extends Objetos {
    public Puerta() {
        this.nombre = "Puerta";
        try {
            BufferedImage imagenPlantillaBuffered =  ImageIO.read(new File("src/recursos/imagenes/AssetsDeInventario.png"));
            Spritesheet plantillaInventario = new Spritesheet(imagenPlantillaBuffered,13,9);
            imagen = plantillaInventario.getImg(12,8,48);
        }catch (IOException e){
            e.printStackTrace();
        }
        colision=true;
    }
}
