package item;

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
    protected BufferedImage img;
    protected String ruta;


    public Item(String idNombre, int cantidad,Tipo tipo,String ruta) {
     //   ruta="img/imagenes/"+idNombre+".jpeg";
        this.idNombre = idNombre;
        this.cantidad = cantidad;
        this.tipo=tipo;
        try {
            this.img = ImageIO.read(new File(ruta));
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

    public BufferedImage getImg() {
        return img;
    }

    public void setImg(BufferedImage img) {
        this.img = img;
    }
}
