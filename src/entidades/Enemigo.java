package entidades;

import gamePanel.GamePanel;
import item.Inventario;
import item.Item;
import item.Tipo;
import recursos.imagenes.Spritesheet;
import utiles.UtilDiego;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class Enemigo extends Entidad{
    public String imagePath;
    private int loot;//en funcion de la altura del numero salen unos objetos u otros.
    /**
     * constructor de enemigo
     * @param nombre
     * @param raza
     * @param clase
     * @param nivel
     */
    public Enemigo(String nombre, Raza raza, Clase clase, int nivel, GamePanel gp,String imagePath) {
        super(gp);
        this.nombre=nombre;
        this.raza=raza;
        this.clase=clase;
        this.nivel=nivel;
        iniciarLoot(nivel);
        super.colision=true;
        //colisiones
        super.zonaDeColision = new Rectangle(8, 16, 32, 32);
        super.zonaDeColisionDefectoX = zonaDeColision.x;
        super.zonaDeColisionDefectoY = zonaDeColision.y;
        this.imagePath=imagePath;
        //donde aparece
        super.x=100;
        super.y=300;
        obtenerImagenEnemigo(imagePath);//iniciar el sprite de entidad a enemigo
    }

    /**
     * metodo que randomiza el nivel de loot
     * @param nivel
     */
    public void iniciarLoot(int nivel){
        this.loot=nivel*(UtilDiego.numRandomentero(1,3));
    }

    /**
     * metodo que indica que devuelve
     * @return
     */
    public ArrayList<Item> generarLoot(){
        ArrayList<Item> objetosLoot=null;
        switch (loot){
            case 1:
                objetosLoot.add(new Item("comida",2, Tipo.COMIDA,3,0));
                objetosLoot.add(new Item("madera", 1, Tipo.MADERA,12,8));
                return objetosLoot;
            case 2:
                objetosLoot.add(new Item("mena_hierro", 2, Tipo.MENA_HIERRO,8,3));
                objetosLoot.add(new Item("carbon", 2, Tipo.CARBON,10,3));
                return objetosLoot;

            default:
                objetosLoot.add(new Item("mena_oro", 2, Tipo.ORO,6,4));
                objetosLoot.add(new Item("hierro", 3, Tipo.HIERRO,8,4));
                return objetosLoot;
        }
    }

    /**
     * obtener imagen del sprite del enemigo
     * @param imagePath
     * @return
     * @throws IOException
     */
    public BufferedImage obtenerImagenEnemigo(String imagePath) {
        try {
            BufferedImage imagenPlantillaBuffered = ImageIO.read(new File(imagePath));
            Spritesheet plantillaJugador = new Spritesheet(imagenPlantillaBuffered, 17, 1);
//            return switch (direccion) {
//                case "izquierda", "arriba-izquierda", "abajo-izquierda" ->
//                        super.sprite = plantillaJugador.getImg(2, 0, gp.getTamañofinalBaldosa());
//                case "derecha", "arriba-derecha", "abajo-derecha" ->
//                        super.sprite = plantillaJugador.getImg(3, 0, gp.getTamañofinalBaldosa());
//                case "arriba" -> super.sprite = plantillaJugador.getImg(numSprite, 0, gp.getTamañofinalBaldosa());
//                case "abajo" -> super.sprite = plantillaJugador.getImg(numSprite, 0, gp.getTamañofinalBaldosa());
//                default -> super.sprite = plantillaJugador.getImg(1, 0, gp.getTamañofinalBaldosa());
//            };
            return super.sprite = plantillaJugador.getImg(1, 0, gp.getTamañofinalBaldosa());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * metodo para actualizar la imagen del enemigo en el JPanel
     */
    public void update() {

    }

    public void dibujar(Graphics2D g2d){
        g2d.drawImage(obtenerImagenEnemigo(this.imagePath), (int) super.x, (int) super.y, gp.getTamañofinalBaldosa(), gp.getTamañofinalBaldosa(), null);
    }


}
