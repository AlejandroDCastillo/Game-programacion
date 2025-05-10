package item.objetos;

import entidades.Entidad;
import gamePanel.GamePanel;

import java.awt.*;
import java.awt.image.BufferedImage;

public abstract class Objetos extends Entidad {
    protected String nombre;
    protected BufferedImage imagen;
    protected boolean colision = false;
    protected double x, y;
    public Rectangle zonaDeColision = new Rectangle(0, 0, 48, 48);
    public int zonaDeColisionDefectoX = 0, zonaDeColisionDefectoY = 0;
    protected int objetoInteractuado = 0;

    public Objetos(GamePanel gp) {
        super(gp);
    }

    public void dibujar(Graphics2D g2d) {
        g2d.drawImage(imagen, (int) x, (int) y, gp.getTamañofinalBaldosa(), gp.getTamañofinalBaldosa(), null);
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public BufferedImage getImagen() {
        return imagen;
    }

    public void setImagen(BufferedImage imagen) {
        this.imagen = imagen;
    }

    public boolean isColision() {
        return colision;
    }

    public void setColision(boolean colision) {
        this.colision = colision;
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    public Rectangle getZonaDeColision() {
        return zonaDeColision;
    }

    public void setZonaDeColision(Rectangle zonaDeColision) {
        this.zonaDeColision = zonaDeColision;
    }

    public int getZonaDeColisionDefectoX() {
        return zonaDeColisionDefectoX;
    }

    public void setZonaDeColisionDefectoX(int zonaDeColisionDefectoX) {
        this.zonaDeColisionDefectoX = zonaDeColisionDefectoX;
    }

    public int getZonaDeColisionDefectoY() {
        return zonaDeColisionDefectoY;
    }

    public void setZonaDeColisionDefectoY(int zonaDeColisionDefectoY) {
        this.zonaDeColisionDefectoY = zonaDeColisionDefectoY;
    }

    public int getObjetoInteractuado() {
        return objetoInteractuado;
    }

    public void setObjetoInteractuado(int objetoInteractuado) {
        this.objetoInteractuado = objetoInteractuado;
    }
}
