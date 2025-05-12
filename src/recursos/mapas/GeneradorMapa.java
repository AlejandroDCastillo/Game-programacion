package recursos.mapas;

import gamePanel.GamePanel;
import utiles.UtilDiego;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class GeneradorMapa {
    GamePanel gp;
    int canidadNumeroFilas;
    int canidadNumeroColumnas;
    StringBuilder numeros = new StringBuilder();
    public GeneradorMapa(GamePanel gp) {
        this.gp = gp;
        canidadNumeroColumnas=gp.getGetCantidadBaldosaAltura();
        canidadNumeroFilas=gp.getCantidadBaldosaAnchura();
        generarMapa();
    }

    public void generarMapa() {
        for (int i = 0; i < canidadNumeroColumnas; i++) {
            for (int j = 0; j < canidadNumeroFilas; j++) {
                int numero = UtilDiego.numRandomentero(1,2); // Números aleatorios entre 0 y 99
                numeros.append(numero);
                if (j < canidadNumeroFilas - 1) {
                    numeros.append(" ");
                }
            }
            if (i < canidadNumeroColumnas -1){
                numeros.append("\n");
            }

        }

        // Escribir en el archivo
        try {
            FileWriter escritorFile = new FileWriter("src/recursos/mapas/mapa_prueba.txt");
            PrintWriter salida = new PrintWriter(escritorFile);
            salida.write(numeros.toString());
            System.out.println("Números generados y guardados en numeros.txt");
        } catch (IOException e) {
            System.out.println("Ocurrió un error al escribir en el archivo: " + e.getMessage());
        }
    }
}
