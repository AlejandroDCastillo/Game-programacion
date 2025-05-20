package item;

import entidades.Entidad;
import item.armadura.Aumento;
import recursos.imagenes.Spritesheet;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.Buffer;

public class Item implements Aumento {
    protected String idNombre;
    protected int cantidad;

    protected Spritesheet plantillaInventario;
    protected BufferedImage imagen;
    protected int x;
    protected int y;

    /**
     * constructor de Item
     * @param idNombre
     * @param cantidad
     * @param spriteX
     * @param spriteY
     */
    public Item(String idNombre, int cantidad,int spriteX,int spriteY) {
        this.idNombre = idNombre;
        this.cantidad = cantidad;
        //posicion del sprite para cada item
        this.x=spriteX;
        this.y=spriteY;
        //cargams la imagen para guardarla en cada uno de los item
        try {
            String imagePath = "src/recursos/imagenes/AssetsDeInventario.png";
            BufferedImage imagenPlantillaBuffered = ImageIO.read(new File(imagePath));
            this.plantillaInventario  = new Spritesheet(imagenPlantillaBuffered,13,9);
            imagen=plantillaInventario.getImg(spriteX,spriteY);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * metodo toString para comprobaciones por consola
     * @return
     */
    @Override
    public String toString() {
        return "Item{" +
                "idNombre='" + idNombre + '\'' +
                ", cantidad=" + cantidad +
                '}';
    }

    //getters y setters

    public String getIdNombre() {
        return idNombre;
    }

    public void setIdNombre(String idNombre) {
        this.idNombre = idNombre;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public Spritesheet getPlantillaInventario() {
        return plantillaInventario;
    }

    public void setPlantillaInventario(Spritesheet plantillaInventario) {
        this.plantillaInventario = plantillaInventario;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void aumentar(Entidad entidad, int numero) {

    }
}