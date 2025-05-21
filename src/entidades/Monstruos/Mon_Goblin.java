package entidades.Monstruos;

import entidades.Clase;
import entidades.Entidad;
import entidades.Raza;
import gamePanel.GamePanel;
import recursos.imagenes.Spritesheet;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Mon_Goblin extends Monstruo {
    public Mon_Goblin(GamePanel gp, String nombre, Raza raza, Clase clase, int nivel) {
        super(gp, nombre, raza, clase, nivel);
    }
    }
