package entidades.Monstruos;

import entidades.Clase;
import entidades.Entidad;
import entidades.Raza;
import gamePanel.GamePanel;
import recursos.imagenes.Spritesheet;
import utiles.Util;
import utiles.UtilDiego;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Mon_Esqueleto extends Monstruo {
    /**
     * constructor de monstruo todo nace de entidad
     *
     * @param gp
     * @param nombre
     * @param raza
     * @param clase
     * @param nivel
     */
    public Mon_Esqueleto(GamePanel gp, String nombre, Raza raza, Clase clase, int nivel) {
        super(gp, nombre, raza, clase, nivel);
    }


}

