package item.objetos;

import gamePanel.GamePanel;
import recursos.imagenes.Spritesheet;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Cofre extends Objetos {
        int i;
    /**
     * constructor de cofre
     *
     * @param gp
     */
    public Cofre(GamePanel gp) {
        super(gp);
        this.nombre = "cofre1";
        objetoInteractuado = 2;
        try {
            BufferedImage imagenPlantillaBuffered = ImageIO.read(new File("src/recursos/imagenes/AssetsDeInventario.png"));
            plantillaSprite = new Spritesheet(imagenPlantillaBuffered, 13, 9);
            imagen = plantillaSprite.getImg(9, 1, 48);
        } catch (IOException e) {
            e.printStackTrace();
        }
        colision = true;
    }

    public void update(){
        if(abrirCofre){
            imagen = plantillaSprite.getImg(9, 0, 48);
            abrirCofre = false;
            if (contadorUpdates % 18 == 0) {
                if (gp.getJugador().cofreAbierto){
                    if (gp.arrayobjetos[i].getNombre().equals(nombre)) {
                        gp.arrayobjetos[i] = null;
                        gp.getJugador().cofreAbierto=false;
                    }
                }
            }
        }
        if (i>=gp.arrayobjetos.length-1){
            i=0;
        }
        i++;
    }

}

