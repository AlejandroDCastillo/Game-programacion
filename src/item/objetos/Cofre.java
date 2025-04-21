package item.objetos;

import recursos.imagenes.Spritesheet;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Cofre extends Objetos {
    public Cofre() {
        this.nombre = "cofre";
        try {
            BufferedImage imagenPlantillaBuffered =  ImageIO.read(new File("src/recursos/imagenes/AssetsDeInventario.png"));
            Spritesheet plantillaInventario = new Spritesheet(imagenPlantillaBuffered,13,9);
            imagen = plantillaInventario.getImg(9,1,48);
        }catch (IOException e){
            e.printStackTrace();
        }
        colision = true;
    }
}
