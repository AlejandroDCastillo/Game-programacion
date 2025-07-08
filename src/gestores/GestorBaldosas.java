package gestores;

import gamePanel.GamePanel;
import recursos.baldosas.Baldosa;
import recursos.imagenes.Spritesheet;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;

public class GestorBaldosas {
    private GamePanel gamePanel;
    public Baldosa[] baldosa;
    public int numeroMapaBaldosa[][];

    /**
     * constructor del gestor de baldosas
     *
     * @param gamePanel
     */
    public GestorBaldosas(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
        baldosa = new Baldosa[82];
        numeroMapaBaldosa = new int[gamePanel.getGetCantidadBaldosaAltura()][gamePanel.getCantidadBaldosaAnchura()];
        obtenerImagenBaldosa();
        cargarMapaBaldosas();
    }

    /**
     * carga la imagen de plantillaBaldosa
     */
    public void obtenerImagenBaldosa() {
        try {
            int contador=10;
            BufferedImage imagenPlantillaBuffered = ImageIO.read(new File("src/recursos/imagenes/PlantillaBaldosa_N.png"));
            Spritesheet plantillaBaldosas = new Spritesheet(imagenPlantillaBuffered, 10, 8);
            for (int i = 0; i < 8; i++) {
                for (int j = 0; j < 10; j++) {
                    if (i > 0) {
                        baldosa[contador] = new Baldosa();
                        baldosa[contador].imagen = plantillaBaldosas.getImg(j, i, gamePanel.getTamañofinalBaldosa());
                        contador++;
                    } else {
                        baldosa[j] = new Baldosa();
                        baldosa[j].imagen = plantillaBaldosas.getImg(j, i, gamePanel.getTamañofinalBaldosa());
                    }

                }
            }
            baldosa[5].colision = true;
            baldosa[0].colision = true;
            baldosa[1].colision = true;
            baldosa[16].colision = true;
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * carga el mapa escrito en numeros para cargar cada sprite de baldosa especifico
     */
    public void cargarMapaBaldosas() {
        try {
            FileInputStream is = new FileInputStream("src/recursos/mapas/mapa_prueba.txt");
            BufferedReader br = new BufferedReader(new InputStreamReader(is));
            int columna;
            int fila;
            //recorremos el archivo de mapa numerico y lo cambiamos
            for (fila = 0; fila < gamePanel.getGetCantidadBaldosaAltura(); fila++) {
                String linea = br.readLine();
                for (columna = 0; columna < gamePanel.getCantidadBaldosaAnchura(); columna++) {
                    String[] numeros = linea.split(" ");
                    int num = Integer.parseInt(numeros[columna]);
                    numeroMapaBaldosa[fila][columna] = num;
                }

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * dibuja las baldosas en el JPanel
     *
     * @param g2d
     */
    public void dibujar(Graphics2D g2d) {
        int columna = 0;
        int fila = 0;
        int x = 0;
        int y = 0;
        for (fila = 0; fila < gamePanel.getGetCantidadBaldosaAltura(); fila++) {
            for (columna = 0; columna < gamePanel.getCantidadBaldosaAnchura(); columna++) {
                int numero = numeroMapaBaldosa[fila][columna];
                System.out.println(numero);
                g2d.drawImage( baldosa[numero].imagen, x, y, gamePanel.getTamañofinalBaldosa(), gamePanel.getTamañofinalBaldosa(), null);
                x += gamePanel.getTamañofinalBaldosa();

            }
            x = 0;
            y += gamePanel.getTamañofinalBaldosa();
        }


    }
}
