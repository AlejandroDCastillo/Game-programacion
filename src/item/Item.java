package item;

import recursos.imagenes.Spritesheet;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.Buffer;

public class Item {
    protected String idNombre;
    protected int cantidad;
    protected Tipo tipo;
    protected Spritesheet plantillaInventario;
    protected int x;
    protected int y;


    public Item(String idNombre, int cantidad,Tipo tipo,int spriteX,int spriteY) {
        this.idNombre = idNombre;
        this.cantidad = cantidad;
        this.tipo=tipo;
        this.x=spriteX;
        this.y=spriteY;
        try {
            String imagePath = "src/recursos/imagenes/AssetsDeInventario.png";
            BufferedImage imagenPlantillaBuffered = ImageIO.read(new File(imagePath));
            this.plantillaInventario  = new Spritesheet(imagenPlantillaBuffered,spriteX+1,spriteY+1);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public String toString() {
        return "Item{" +
                "idNombre='" + idNombre + '\'' +
                ", cantidad=" + cantidad +
                ", tipo=" + tipo +
                '}';
    }

    public Tipo getTipo() {
        return tipo;
    }

    public void setTipo(Tipo tipo) {
        this.tipo = tipo;
    }

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
}
