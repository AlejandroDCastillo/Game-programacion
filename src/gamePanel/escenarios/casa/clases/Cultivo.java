package gamePanel.escenarios.casa.clases;

import item.Item;
import gamePanel.escenarios.casa.Cultivos;
import recursos.imagenes.Spritesheet;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public abstract class Cultivo implements Cultivos {

    protected BufferedImage imagen;
    protected Spritesheet icono;
    protected int tiempo;
    protected int estado;
    protected int estados;
    protected String nombre;
    protected Item loot;
    public Cultivo(int x,int y,int estados, String nombre,Item loot) {
        this.estado = 0;
        this.estados = estados;
        this.nombre = nombre;
        this.loot=loot;
        //inicializamos el icono
        try {
            String imagePath = "src/recursos/imagenes/AssetsDeCultivo.png";
            BufferedImage imagenPlantaBuffered = ImageIO.read(new File(imagePath));
            this.icono  = new Spritesheet(imagenPlantaBuffered,13,9);
            imagen=icono.getImg(x,y);
        } catch (IOException e) {
            e.printStackTrace();
            System.err.println("Error al cargar la imagen: " + e.getMessage());
        }
    }

    @Override
    public abstract void plantarse();

    @Override
    public abstract boolean recogerse();

    @Override
    public abstract int crecer();

    @Override
    public abstract Item darItem();
}
