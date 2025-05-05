package item.objetos;

import recursos.imagenes.Spritesheet;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Llave extends Objetos {
    public Llave() {
        this.nombre = "llave";
        try {
            BufferedImage imagenPlantillaBuffered =  ImageIO.read(new File("src/recursos/imagenes/AssetsDeInventario.png"));
            Spritesheet plantillaInventario = new Spritesheet(imagenPlantillaBuffered,13,9);
            imagen = plantillaInventario.rotarImagen(plantillaInventario.getImg(6,5,48),180);
        }catch (IOException e){
            e.printStackTrace();
        }
        colision=true;
    }
}
