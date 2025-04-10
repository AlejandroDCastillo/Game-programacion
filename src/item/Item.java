package item;

import javax.swing.*;
import java.awt.*;

public class Item {
    protected String idNombre;
    protected int cantidad;
    protected Tipo tipo;
//    protected Image img;
//    protected String ruta;


    public Item(String idNombre, int cantidad,Tipo tipo) {
//        ruta="img/imagenes/...";
        this.idNombre = idNombre;
        this.cantidad = cantidad;
        this.tipo=tipo;
//        ImageIcon icon=new ImageIcon(getClass().getResource(ruta));
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
}
