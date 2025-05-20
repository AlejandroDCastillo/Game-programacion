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
                if(j==8 && i==0){
                    numeros.append(12);
                    numeros.append(" ");
                }
                if ((j==canidadNumeroFilas-1||j==0)&&(i<=6&&i>3)){
                    numeros.append(1);
                }else{
                    if ((j==canidadNumeroFilas-1||j==0)||(i==canidadNumeroColumnas-1||i==0)){
                        numeros.append(1);
                    } else{
                        if ((j==canidadNumeroFilas-2||j==1)||(i==canidadNumeroColumnas-2||i==1)){
                            numeros.append(0);
                        }else{
                            int numero = UtilDiego.numRandomentero(2,6);
                            if ((j==canidadNumeroFilas-1||j==0)&&(i<=9&&i>6)){
                                numeros.append(3);
                            }else{
                                if ((i >= 3&&i<10) && numero == 5){
                                    numeros.append(2);
                                }else{
                                    numeros.append(numero);
                                }
                            }
                        }
                    }
                }
                if (j < canidadNumeroFilas - 1) {
                    numeros.append(" ");
                }
            }
            if (i < canidadNumeroColumnas -1) {
                numeros.append("\n");
            }
        }


        // Escribir en el archivo
        FileWriter escritorFile=null;
        try {
            escritorFile = new FileWriter("src/recursos/mapas/mapa_prueba.txt");
            PrintWriter salida = new PrintWriter(escritorFile);
            salida.println(numeros.toString());
            salida.flush();
            System.out.println("Números generados y guardados en numeros.txt");
        } catch (IOException e) {
            System.out.println("Ocurrió un error al escribir en el archivo: " + e.getMessage());
        }
        finally {
            try {
                if (escritorFile != null)
                    escritorFile.close();
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
        }
    }
}
