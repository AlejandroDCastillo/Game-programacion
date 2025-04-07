/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package clases;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import javax.swing.*;
import java.net.URL;
import java.util.Timer;
public class Player extends JPanel{
    private String ruta;
    private double dx;
    private double dy;
    private int y;
    private int x;
    private Image imagen;
    boolean bandera;
    public Player() {
        x = 40;
        y = 60;
        ruta = "../imagenes/spritemovimiento.gif";
        URL url = Player.class.getResource(ruta);
        Image imagen = new ImageIcon(url).getImage();
        this.imagen = imagen;
        ImageIcon imgIzq = new ImageIcon(this.getClass().getResource(""));
        setBounds(x, y, imagen.getWidth(null), imagen.getHeight(null));
        setBorder(BorderFactory.createLineBorder(Color.BLUE));
    }
    public Rectangle getBounds() {
        System.out.println("Dimension: Width:"+imagen.getWidth(null)+"Height:"+imagen.getHeight(null));
        return new Rectangle(x, y, imagen.getWidth(null), imagen.getHeight(null));
    }
    //Metodo para escalar la imagen

    //Metodos para detectar teclas
    public void keyPressed(KeyEvent e) {
        int tecla = e.getKeyCode();
        if (tecla == KeyEvent.VK_A) {
            ImageIcon imgIzq = new ImageIcon(this.getClass().getResource("spritemovimientoizquierda.gif"));
            imagen= imgIzq.getImage();
            dx = -10;
        }
        if (tecla == KeyEvent.VK_D) {
            ImageIcon img = new ImageIcon(this.getClass().getResource(ruta));
            imagen= img.getImage();
            dx = 10;
        }
        if (tecla == KeyEvent.VK_W) {
            dy = -10;
        }
        if (tecla == KeyEvent.VK_S) {
            dy = 10;
        }

    }

    //Metodos para detectar que se dejaron de pulsar lsa  teclas
    public void keyReleased(KeyEvent e) {
        int tecla = e.getKeyCode();
        if (tecla == KeyEvent.VK_A) {
            ImageIcon imgparada = new ImageIcon(this.getClass().getResource("img.png"));
            imagen= imgparada.getImage();

            dx = 0;
        }
        if (tecla == KeyEvent.VK_D) {
            ImageIcon imgparada = new ImageIcon(this.getClass().getResource("img.png"));
            imagen= imgparada.getImage();
            dx = 0;
        }
        if (tecla == KeyEvent.VK_W) {
            ImageIcon imgparada = new ImageIcon(this.getClass().getResource("img.png"));
            imagen= imgparada.getImage();
            dy = 0;
        }
        if (tecla == KeyEvent.VK_S) {
            ImageIcon imgparada = new ImageIcon(this.getClass().getResource("img.png"));
            imagen= imgparada.getImage();
            dy = 0;
        }
    }
    //METODO PARA HACER QUE SE MUEVA

    public void mover() {
        if (x+dx<0||y+dy<0||x+dx>500||y+dy>500) {
            return;
        }
       x += dx;
       y += dy;

    }


    public int tenerX() {
        return x;
    }

    public int tenerY() {
        return y;
    }

    public Image tenerImagen() {
        return imagen;
    }
}


