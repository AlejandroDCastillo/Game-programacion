package recursos.baldosas;

import gamePanel.GamePanel;
import recursos.imagenes.Spritesheet;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.TileObserver;
import java.io.*;

public class GestorBaldosas {
    private GamePanel gamePanel;
    public Baldosa[] baldosa;
    public int numeroMapaBaldosa[][];
    public GestorBaldosas(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
        baldosa = new Baldosa[22];
        numeroMapaBaldosa = new int[gamePanel.getGetCantidadBaldosaAltura()][gamePanel.getCantidadBaldosaAnchura()];
        obtenerImagenBaldosa();
        cargarMapaBaldosas();
    }
    public void obtenerImagenBaldosa() {
        try {
            BufferedImage imagenPlantillaBuffered = ImageIO.read(new File("src/recursos/imagenes/baldosas/PlantillaBaldosas.png"));
            Spritesheet plantillaBaldosas = new Spritesheet(imagenPlantillaBuffered,11,2);
            for (int i = 0; i < 2; i++) {
                for (int j = 0; j < 11; j++) {
                    if (i>0){
                        baldosa[i+j+10] = new Baldosa();
                        baldosa [i+j+10].imagen = plantillaBaldosas.getImg(j,i);
                    }else{
                        baldosa[j] = new Baldosa();
                        baldosa [j].imagen = plantillaBaldosas.getImg(j,i);
                        }

                }
            }
            baldosa[16].colision=true;
            baldosa[5].colision=true;
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    public void cargarMapaBaldosas() {
        try{
            FileInputStream is = new FileInputStream("src/recursos/mapas/mapa_prueba.txt");
            BufferedReader br = new BufferedReader(new InputStreamReader(is));
            int columna;
            int fila;
            for (fila = 0; fila < gamePanel.getGetCantidadBaldosaAltura(); fila++) {
                String linea = br.readLine();
                for (columna=0; columna < gamePanel.getCantidadBaldosaAnchura(); columna++) {
                    String[] numeros = linea.split(" ");
                    int num= Integer.parseInt(numeros[columna]);
                    numeroMapaBaldosa[fila][columna] = num;
                }

            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }




    public void dibujar(Graphics2D g2d) {
        int columna=0;
        int fila =0;
        int x=0;
        int y=0;
       for (fila=0;fila < gamePanel.getGetCantidadBaldosaAltura(); fila++) {
           for (columna=0;columna < gamePanel.getCantidadBaldosaAnchura(); columna++) {
               int numero = numeroMapaBaldosa[fila][columna];
               g2d.drawImage(baldosa[numero].imagen,x,y, gamePanel.getTamañofinalBaldosa(),gamePanel.getTamañofinalBaldosa(),null);
               x += gamePanel.getTamañofinalBaldosa();

           }
           x=0;
           y += gamePanel.getTamañofinalBaldosa();
       }


    }
}
