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
import java.nio.file.FileAlreadyExistsException;

public class Monstruo extends Entidad {
    public Monstruo(GamePanel gp, String nombre, Raza raza, Clase clase, int nivel) {
        super(gp);
        this.nombre=nombre;
        this.raza=raza;
        this.clase=clase;
        this.nivel=nivel;
        iniciarRaza(raza);
        iniciarClase(clase);
        estadisticasNivel(nivel);
        this.vida=vidaMax;
        this.mana=manaMax;
        this.velocidad=velocidadMax/2;
        this.velocidadDiagonal = Math.hypot(this.velocidad,this.velocidad)/2;
        this.direccion = "";
        x = 100;
        y = 100;
        zonaDeColision = new Rectangle(8, 16, 32, 32);
        zonaDeColisionDefectoX = zonaDeColision.x;
        zonaDeColisionDefectoY = zonaDeColision.y;
        try{
            String imagePath = "src/recursos/imagenes/caballero.png";
            BufferedImage imagenPlantillaBuffered = ImageIO.read(new File(imagePath));
            plantillaSprite = new Spritesheet(imagenPlantillaBuffered, 6, 4);
        }catch(
                IOException e){
            e.printStackTrace();
        }
    }



public BufferedImage tomarImagen(){
    if (gp.estadoJuego==gp.menuInicio) {
        return sprite = plantillaSprite.getImg(numSprite, 2, gp.getTamañofinalBaldosa());
    }
    return switch (direccion) {
        case "izquierda" ->
                sprite = plantillaSprite.getImg(2, numSprite+2, gp.getTamañofinalBaldosa());
        case "derecha" ->
                sprite = plantillaSprite.getImg(3, numSprite+2, gp.getTamañofinalBaldosa());
        case "arriba" -> sprite = plantillaSprite.getImg(numSprite, 3, gp.getTamañofinalBaldosa());
        case "abajo" -> sprite = plantillaSprite.getImg(numSprite, 2, gp.getTamañofinalBaldosa());
        default -> sprite = plantillaSprite.getImg(1, 3, gp.getTamañofinalBaldosa());
    };
}

@Override
    public void update() {
        establecerAccion();
        colision=false;
        if (gp.getJugador().isOpcionHuir()){
            if (contadorUpdates>=24){
                gp.getJugador().setOpcionHuir(false);
            }
        }else{
            gp.detectorDeColisiones.comprobarJugador(this);
        }
        gp.detectorDeColisiones.comprobarBaldosa(this);
        gp.detectorDeColisiones.comprobarObjetos(this,false);
        gp.detectorDeColisiones.comprobaEntidad(this,gp.arrayEnemigos);
        if (!colision) {
            switch (direccion) {
                case "arriba":
                    y = y - velocidad;
                    break;
                case "abajo":
                    y = y + velocidad;
                    break;
                case "izquierda":
                    x = x - velocidad;
                    break;
                case "derecha":
                    x = x + velocidad;
                    break;
            }
        }
        contadorUpdates++;
        if (contadorUpdates % 8 == 0) {
            numSprite++;
        }
        if (numSprite >= 2) {
            numSprite = 0;
            contadorUpdates = 0;
        }
    }



@Override
public void dibujar(Graphics2D g2d){
    g2d.setColor(Color.red);
    g2d.fillRect((int) (x + 8), (int) (y + 16), 32, 32);
    g2d.drawImage(tomarImagen(),(int) x,(int) y,gp.getTamañofinalBaldosa(),gp.getTamañofinalBaldosa(),null);
}
}


