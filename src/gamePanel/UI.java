package gamePanel;

import entidades.Clase;
import entidades.Entidad;
import entidades.Jugador;
import entidades.Raza;
import item.Item;
import item.armadura.Armadura;
import recursos.imagenes.Spritesheet;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;

public class UI {

    GamePanel gp;
    Font fonte;
    String mensaje = "";
    boolean hayMensaje = false;
    public boolean juegoTerminado = false;
    Graphics2D g2d;
    int contadorMensaje = 0;
    BufferedImage imagen_llave;
    public int numeroMenu = 0;
    public int numeroMenuCons = 0;
    public String[] Dialogos = new String[3];
    public int pantallaDelTitulo = 0;// pantalla del titulo

    /**
     * constructor de la interfaz
     *
     * @param gp
     */
    public UI(GamePanel gp) {
        this.gp = gp;
        try {
            //lectura de la fuente de un archivo.
            File archivoFuente = new File("src/recursos/font/Minecraft.ttf");
            System.out.printf(archivoFuente.getAbsolutePath());
            fonte = Font.createFont(Font.TRUETYPE_FONT, archivoFuente);
        } catch (FontFormatException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * @param texto
     */
    public void enseñarMensaje(String texto) {
        mensaje = texto;
        hayMensaje = true;
//        dibujarTextoSombreado(texto,150,100,20);
    }

    /**
     * metodo que dibuja en bucle la interfaz
     *
     * @param g2d
     */
    public void dibujar(Graphics2D g2d) {
        this.g2d = g2d;
        g2d.setFont(fonte);
        //menu inicio
        if (gp.estadoJuego == gp.menuInicio) {
            dibujarMenuInicio();
        } else { //resto de cosas
            //ui Principal
            if (gp.estadoJuego == gp.continuar) {
                dibujarInterfaz();
            }
            //pausa
            if (gp.estadoJuego == gp.pausa) {
                dibujarPausa();
            }
            //inventario
            if (gp.getTeclado().menuBoolean) {
                dibujarInventario(gp.getTeclado().craftear);
                if (gp.getTeclado().craftear) {
                    dibujarMenuCrafteo();
                } else if (gp.getTeclado().menuEquipar) {
                    dibujarMenuEquipar(gp.getTeclado().menuEquipar);
                } else if(gp.getTeclado().menuStats){
                    dibujarMenuStats();
                }else if(gp.getTeclado().consumir){
                    dibujarMenuConsumir();
                }
            }

            //combate
            if (gp.estadoJuego == gp.combate) {
                dibujarCombate();
            }
            if (gp.estadoJuego == gp.gameOver) {
                dibujarGamover();
            }
        }

    }

    public void dibujarMenuConsumir(){
        dibujarVentanaGenerica(580, 110, 200, 280);
        dibujarTextoSombreado("P.Vida", 600, 150, 20);
        dibujarTextoSombreado("P.Mana", 600, 250, 20);
        dibujarTextoSombreado("volver", 600, 350, 20);
        //cantidad de pociones
        //mana
        Item pocion=gp.getJugador().getInventario().buscarObjeto("comida");
        dibujarTextoSombreado("X"+String.valueOf(pocion.getCantidad()),660, 150, 20);
        //vida
         pocion=gp.getJugador().getInventario().buscarObjeto("agua");
        dibujarTextoSombreado("X"+String.valueOf(pocion.getCantidad()),660, 250, 20);

        if (gp.getTeclado().consumir) {
            if (numeroMenuCons == 0) {
                dibujarTextoSombreado(">", 660 - 70, 150,20);
            }
            if (numeroMenuCons == 1) {
                dibujarTextoSombreado(">", 660 - 70, 250,20);
            }
            if (numeroMenuCons == 2) {
                dibujarTextoSombreado(">", 660 - 70, 350,20);
            }
        }
    }

    /**
     * metodo para enseñar las stats del jugador
     */
    public void dibujarMenuStats(){
        dibujarVentanaGenerica(580, 110, 200, 280);
        int y=160;
        String stat = String.valueOf(gp.getJugador().getFuerza());
        dibujarTextoSombreado("Dano: " + stat, 590, y, 20);
        stat = String.valueOf(gp.getJugador().getDestreza());
        dibujarTextoSombreado("Destreza: " + stat, 590, y+30, 20);
        stat = String.valueOf(gp.getJugador().getMagia());
        dibujarTextoSombreado("Magia: " + stat, 590, y+60, 20);
        stat = String.valueOf(gp.getJugador().getDefensa());
        dibujarTextoSombreado("Defensa: " + stat, 590,  y+90, 20);
        gp.getInterfaz().dibujarTextoSombreado("EXP:", 590, y+120, 20);
         stat = String.valueOf(gp.getJugador().getEXPSubirNivel());
        gp.getInterfaz().dibujarTextoSombreado("/" + stat,590+(gp.getJugador().getEXPSubirNivel())+20 , y+140, 20);
        stat = String.valueOf(gp.getJugador().getEXP());
        gp.getInterfaz().dibujarTextoSombreado(stat, 590+(gp.getJugador().getEXPSubirNivel()) , y+140, 20);
        g2d.setColor(Color.white);
        int x = 590;
        //bucle para dibujar el cuadrado rojo
        for (int i = 0; i < gp.getJugador().getEXP(); i++) {
            //cuadrado rojo por cada punto de vida
            g2d.fillRect(x, y+140, 2, 4);
            x += 1;
        }
        dibujarTextoSombreado("Salir", 590, 370, 20);
        if (numeroMenu == 12) {
            dibujarTextoSombreado(">", 590 - 25, 370,20);
        }

    }

    /**
     * metodo para dibujar la interfaz del juego para saber info del jugador
     */
    public void dibujarInterfaz() {
        dibujarVentanaGenerica(50, 480, 650, 100);
        String stat = String.valueOf(gp.getJugador().getVidaMax());
        gp.getInterfaz().dibujarTextoSombreado("/" + stat, gp.getJugador().getVidaMax() * 2 + 100, 520, 15);
        stat = String.valueOf(gp.getJugador().getVida());
        gp.getInterfaz().dibujarTextoSombreado(stat, gp.getJugador().getVidaMax() * 2 + 80, 520, 15);
        stat = "Vida:";
        gp.getInterfaz().dibujarTextoSombreado(stat, 80, 500, 15);
        g2d.setColor(Color.RED);
        int x = 80, y = 520;
        //bucle para dibujar el cuadrado rojo
        for (int i = 0; i < gp.getJugador().getVida(); i++) {
            //cuadrado rojo por cada punto de vida
            g2d.fillRect(x, y, 2, 4);
            x += 2;
        }
        stat="Mana:";
        dibujarTextoSombreado(stat, 80, 540, 15);
        stat=String.valueOf(gp.getJugador().getMana());
        dibujarTextoSombreado(stat, gp.getJugador().getMana()*2+90, 560, 15);
        stat="/100";
        dibujarTextoSombreado(stat, gp.getJugador().getMana()*2+120,560 , 15);
        g2d.setColor(Color.blue);
        y = 560;
        x=80;
        //bucle para dibujar el cuadrado rojo
        for (int i = 0; i < gp.getJugador().getMana(); i++) {
            //cuadrado rojo por cada punto de vida
            g2d.fillRect(x, y, 2, 4);
            x += 2;
        }
        //tutorial
        dibujarTextoSombreado("Movimiento: con A W S D", 450, 5200, 15);
        dibujarTextoSombreado("Pausa y volver: ESC", 450, 540, 15);
        dibujarTextoSombreado("Inventario: E", 450, 560, 15);

    }


    public void dibujarGamover() {
        g2d.setColor(Color.black);
        g2d.fillRect(0,0,gp.getWidth(),gp.getHeight());
        dibujarTextoSombreado("GAME OVER", 220, 220, 50);

    }

    public void dibujarCombate() {

        g2d.setColor(Color.DARK_GRAY);
        g2d.fillRect(0, 0, gp.getWidth(), gp.getHeight() / 2);
        g2d.setColor(new Color(100, 0, 0));
        g2d.fillRect(0, gp.getHeight() / 2, gp.getWidth(), gp.getHeight() / 2);
        try {
            //cargamos la imagen del personaje
            String imagePath = "src/recursos/imagenes/caballero.png";
            BufferedImage imagenPlantillaBuffered = ImageIO.read(new File(imagePath));
            Spritesheet plantillaJugador = new Spritesheet(imagenPlantillaBuffered, 6, 4);
            BufferedImage img = plantillaJugador.getImg(3, 0);
            //la escalamos usando el metodo por defecto
            Image imgEscalada = img.getScaledInstance(100, 100, Image.SCALE_SMOOTH);
//            dibujamos en pantalla
            g2d.drawImage(imgEscalada, 100, 100, null);
        } catch (Exception e) {
            System.out.println("ERROR: No se encuentra la imagen del jugador");
        }
        try {
            //cargamos la imagen del enemigo
            String imagePath = "src/recursos/imagenes/caballero.png";
            BufferedImage imagenPlantillaBuffered = ImageIO.read(new File(imagePath));
            Spritesheet plantillaJugador = new Spritesheet(imagenPlantillaBuffered, 6, 4);
            BufferedImage img = plantillaJugador.getImg(2, 0);
            //la escalamos usando el metodo por defecto
            Image imgEscalada = img.getScaledInstance(100, 100, Image.SCALE_SMOOTH);
//            dibujamos en pantalla
            g2d.drawImage(imgEscalada, 550, 100, null);
        } catch (Exception e) {
            System.out.println("ERROR: No se encuentra la imagen del jugador");
        }
        //pantalla de equipacion y stats
        try {
            //cargamos la imagen del personaje
            String imagePath = "src/recursos/imagenes/caballero.png";
            BufferedImage imagenPlantillaBuffered = ImageIO.read(new File(imagePath));
            Spritesheet plantillaJugador = new Spritesheet(imagenPlantillaBuffered, 6, 4);
            BufferedImage img = plantillaJugador.getImg(1, 0);
            //la escalamos usando el metodo por defecto
            Image imgEscalada = img.getScaledInstance(150, 150, Image.SCALE_SMOOTH);
//            dibujamos en pantalla
            g2d.drawImage(imgEscalada, 450, 350, null);
        } catch (Exception e) {
            System.out.println("ERROR: No se encuentra la imagen del jugador");
        }
        //inventario equipar
        try {
            String imagePath = "src/recursos/imagenes/equipar.png";
            BufferedImage imagenequipados = ImageIO.read(new File(imagePath));
//            Image imgEscaladaequipar = imagenequipados.getScaledInstance(-100, -100, Image.SCALE_SMOOTH);
            g2d.drawImage(imagenequipados, 570, 250, null);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        // objetos equipados
        Jugador pj = gp.getJugador();
        Item item = null;
        if (pj.getArma() != null) {
            item = pj.getArma();
            g2d.drawImage(item.getPlantillaInventario().getImg(item.getX(), item.getY()), 592
                    , 325, 45, 45, null);
        }
        if (pj.getArmadura() != null) {
            item = pj.getArmadura();
            g2d.drawImage(item.getPlantillaInventario().getImg(item.getX(), item.getY()), 592
                    , 375, 45, 45, null);
        }
        if (pj.getCabeza() != null) {
            item = pj.getCabeza();
            g2d.drawImage(item.getPlantillaInventario().getImg(item.getX(), item.getY()), 592
                    , 427, 45, 45, null);

        }
        if (pj.getEscudo() != null) {
            item = pj.getEscudo();
            g2d.drawImage(item.getPlantillaInventario().getImg(item.getX(), item.getY()), 592
                    , 470, 45, 45, null);
        }
        //menu de batalla
        int x = 50;
        String texto;
        //  dibujarVentanaGenerica(2, 110, 140, 280);
        texto = "Atacar";
        dibujarTextoSombreado(texto, x, 350, 25);
        texto = "Consumibles";
        dibujarTextoSombreado(texto, x, 410, 25);
        texto = "Huir";
        dibujarTextoSombreado(texto, x, 470, 25);
        if (!gp.getTeclado().consumir) {
            if (numeroMenu == 0) {
                g2d.drawString(">", x - 25, 350);
            }
            if (numeroMenu == 1) {
                g2d.drawString(">", x - 25, 410);
            }
            if (numeroMenu == 2) {
                g2d.drawString(">", x - 25, 470);
            }
        }

        dibujarVentanaGenerica(250, gp.getHeight() / 2 + 30, 200, 250);
        dibujarTextoSombreado("P.Mana", 290, 350, 25);
        dibujarTextoSombreado("P.Vida", 290, 450, 25);
        dibujarTextoSombreado("volver", 290, 550, 25);
        //cantidad de pociones
        //vida
        Item pocion=gp.getJugador().getInventario().buscarObjeto("comida");
        dibujarTextoSombreado("X"+String.valueOf(pocion.getCantidad()),380, 450, 25);
        //mana
        pocion=gp.getJugador().getInventario().buscarObjeto("agua");
        dibujarTextoSombreado("X"+String.valueOf(pocion.getCantidad()),380, 350, 25);

        if (gp.getTeclado().consumir) {
            if (numeroMenuCons == 0) {
                g2d.drawString(">", 290 - 25, 350);
            }
            if (numeroMenuCons == 1) {
                g2d.drawString(">", 290 - 25, 450);
            }
            if (numeroMenuCons == 2) {
                g2d.drawString(">", 290 - 25, 550);
            }
        }
        //vida de entidades
        //jugador
        String stat = String.valueOf(gp.getJugador().getVidaMax());
        gp.getInterfaz().dibujarTextoSombreado("/" + stat, gp.getJugador().getVida() * 2 + 140, 250, 15);
        stat = String.valueOf(gp.getJugador().getVida());
        gp.getInterfaz().dibujarTextoSombreado(stat, gp.getJugador().getVida() * 2 + 110, 250, 15);
        stat = "Vida:";
        gp.getInterfaz().dibujarTextoSombreado(stat, 70, 250, 15);
        g2d.setColor(Color.RED);
         x = 110;
         int y = 250;
        //bucle para dibujar el cuadrado rojo
        for (int i = 0; i < gp.getJugador().getVida(); i++) {
            //cuadrado rojo por cada punto de vida
            g2d.fillRect(x, y, 2, 4);
            x += 2;
        }
        stat="Mana:";
        dibujarTextoSombreado(stat, 60, 270, 15);
        stat=String.valueOf(gp.getJugador().getMana());
        dibujarTextoSombreado(stat, gp.getJugador().getMana()*2+110, 270, 15);
        stat="/100";
        dibujarTextoSombreado(stat, gp.getJugador().getMana()*2+140,270 , 15);
        g2d.setColor(Color.blue);
        y = 270;
        x=110;
        //bucle para dibujar el cuadrado azul
        for (int i = 0; i < gp.getJugador().getMana(); i++) {
            //cuadrado rojo por cada punto de mana
            g2d.fillRect(x, y, 2, 4);
            x += 2;
        }

        //enemigo
        stat = String.valueOf(gp.gc.monstruo.getVidaMax());
        gp.getInterfaz().dibujarTextoSombreado("/" + stat, gp.gc.monstruo.getVida() * 2 + 520, 250, 15);
        stat = String.valueOf(gp.gc.monstruo.getVida());
        gp.getInterfaz().dibujarTextoSombreado(stat, gp.gc.monstruo.getVida() * 2 + 500, 250, 15);
        stat = "Vida:";
        gp.getInterfaz().dibujarTextoSombreado(stat, 430, 250, 15);
        g2d.setColor(Color.RED);
        x = 480;
        y = 250;
        //bucle para dibujar el cuadrado rojo
        for (int i = 0; i < gp.gc.monstruo.getVida(); i++) {
            //cuadrado rojo por cada punto de vida
            g2d.fillRect(x, y, 2, 4);
            x += 2;
        }

        //stats
        stat = String.valueOf(gp.gc.jugador.getFuerza());
        dibujarTextoSombreado("Dano:" + stat, 660, 320, 15);
        stat = String.valueOf(gp.gc.jugador.getDestreza());
        dibujarTextoSombreado("Destreza:" + stat, 660, 360, 15);
        stat = String.valueOf(gp.gc.jugador.getMagia());
        dibujarTextoSombreado("Magia:" + stat, 660, 400, 15);
        stat = String.valueOf(gp.gc.jugador.getDefensa());
        dibujarTextoSombreado("Defensa:" + stat, 660, 440, 15);

        if (gp.gc.jugador.isOpcionAtacar()) {
            dibujarVentanaGenerica(100, 30, 600, 100);
            dibujarTextoSombreado(mensaje, 110, 80, 20);
        }
        if (gp.gc.monstruo.isOpcionAtacar()) {
            dibujarVentanaGenerica(100, 30, 600, 100);
            dibujarTextoSombreado(mensaje, 110, 80, 20);
        }

        if (gp.gc.jugador.isOpcionHuir()) {
            dibujarVentanaGenerica(100, 30, 600, 100);
            dibujarTextoSombreado(mensaje, 110, 80, 20);
        }

        if (gp.gc.monstruo.getVida()<=0){
            dibujarVentanaGenerica(100, 30, 600, 100);
            dibujarTextoSombreado(mensaje, 110, 80, 20);
        }
    }

    /**
     * metodo que dibuja el menu de equipar
     *
     * @param equipar
     */
    public void dibujarMenuEquipar(boolean equipar) {

        //indica en pantalla si se consigue equipar o no (no sé porque lo hacemos si siempre se puede equipar)
        dibujarVentanaGenerica(580, 110, 180, 280);
        dibujarVentanaGenerica(120, 0, 480, 110);
        int x = 130, y = 50;
        String texto;
        if (!gp.getTeclado().intentoEquipar) {
            if (numeroMenu == 0) {

                texto = "Ofrece +6 defensa.";
                dibujarTextoSombreado(texto, x, y + 20, 20);
            }
            if (numeroMenu == 1) {
                texto = "Ofrece +12 defensa.";
                dibujarTextoSombreado(texto, x, y + 20, 20);
            }
            if (numeroMenu == 2) {
                texto = "Ofrece +5 daño.";
                dibujarTextoSombreado(texto, x, y + 20, 20);
            }
            if (numeroMenu == 3) {
                texto = "Ofrece +9 daño.";
                dibujarTextoSombreado(texto, x, y + 20, 20);
            }
            if (numeroMenu == 4) {
                texto = "Ofrece +6 daño.";
                dibujarTextoSombreado(texto, x, y + 20, 20);
            }
            if (numeroMenu == 5) {
                texto = "Ofrece +4 defensa.";
                dibujarTextoSombreado(texto, x, y + 20, 20);
            }
            if (numeroMenu == 6) {
                texto = "Ofrece +5 defensa.";
                dibujarTextoSombreado(texto, x, y + 20, 20);
            }
            if (numeroMenu == 7) {
                texto = "Ofrece +5 defensa.";
                dibujarTextoSombreado(texto, x, y + 20, 20);
            }
            if (numeroMenu == 8) {
                texto = "Volver";
                dibujarTextoSombreado(texto, x, y + 20, 20);
            }
        }
        texto = "Escudo";
        dibujarTextoSombreado(texto, 605, 150, 20);
        texto = "Esc Oro";
        dibujarTextoSombreado(texto, 605, 170, 20);
        texto = "Espada";
        dibujarTextoSombreado(texto, 605, 190, 20);
        texto = "Esp Fuego";
        dibujarTextoSombreado(texto, 605, 210, 20);
        texto = "Vara Mago";
        dibujarTextoSombreado(texto, 605, 230, 20);
        texto = "Talisman";
        dibujarTextoSombreado(texto, 605, 250, 20);
        texto = "Yelmo";
        dibujarTextoSombreado(texto, 605, 270, 20);
        texto = "peto";
        dibujarTextoSombreado(texto, 605, 290, 20);
        texto = "salir";
        dibujarTextoSombreado(texto, 605, 310, 20);
        switch (numeroMenu) {
            case 0:
                dibujarTextoSombreado(">", 590, 155, 20);
                break;
            case 1:
                dibujarTextoSombreado(">", 590, 175, 20);
                break;
            case 2:
                dibujarTextoSombreado(">", 590, 195, 20);
                break;
            case 3:
                dibujarTextoSombreado(">", 590, 215, 20);
                break;
            case 4:
                dibujarTextoSombreado(">", 590, 235, 20);
                break;
            case 5:
                dibujarTextoSombreado(">", 590, 255, 20);
                break;
            case 6:
                dibujarTextoSombreado(">", 590, 275, 20);
                break;
            case 7:
                dibujarTextoSombreado(">", 590, 295, 20);
                break;
            case 8:
                dibujarTextoSombreado(">", 590, 315, 20);
                break;
        }
        if (gp.getTeclado().intentoEquipar) {
            if (numeroMenu == 0) {

                dibujarTextoSombreado(mensaje, x, y, 20);
            }
            if (numeroMenu == 1) {
                dibujarTextoSombreado(mensaje, x, y, 20);
            }
            if (numeroMenu == 2) {
                dibujarTextoSombreado(mensaje, x, y, 20);
            }
            if (numeroMenu == 3) {
                dibujarTextoSombreado(mensaje, x, y, 20);
            }
            if (numeroMenu == 4) {
                dibujarTextoSombreado(mensaje, x, y, 20);

            }
            if (numeroMenu == 5) {
                dibujarTextoSombreado(mensaje, x, y, 20);
            }
            if (numeroMenu == 6) {
                dibujarTextoSombreado(mensaje, x, y, 20);
            }
            if (numeroMenu == 7) {
                dibujarTextoSombreado(mensaje, x, y, 20);
            }
            if (numeroMenu == 8) {
                dibujarTextoSombreado(mensaje, x, y, 20);
            }
        }
    }

    /**
     * metodo que dibuja el menu de craftear
     */
    public void dibujarMenuCrafteo() {
        String texto;
        int x = 130, y = 50;
        dibujarVentanaGenerica(580, 110, 180, 280);
        dibujarVentanaGenerica(120, 0, 480, 110);
        if (!gp.getTeclado().intentocrafteo) {
            switch (numeroMenu) {
                case 0:
                    texto = "Un escudo se crea con una madera ";
                    dibujarTextoSombreado(texto, x, y, 20);
                    texto = "y dos de hierro.";
                    dibujarTextoSombreado(texto, x, y + 20, 20);
                    break;
                case 1:
                    texto = "Un escudo de oro se crea con una madera ";
                    dibujarTextoSombreado(texto, x, y, 20);
                    texto = "y dos de oro.";
                    dibujarTextoSombreado(texto, x, y + 20, 20);
                    break;
                case 2:
                    texto = "Una espada se crea con una madera ";
                    dibujarTextoSombreado(texto, x, y, 20);
                    texto = "y un hierro.";
                    dibujarTextoSombreado(texto, x, y + 20, 20);
                    break;
                case 3:
                    texto = "Una espadaFuego se crea con una madera,";
                    dibujarTextoSombreado(texto, x, y, 20);
                    texto = "un hierro, un oro y un carbón.";
                    dibujarTextoSombreado(texto, x, y + 20, 20);
                    break;
                case 4:
                    texto = "Una varaMago se crea con dos de madera,";
                    dibujarTextoSombreado(texto, x, y, 20);
                    texto = "un oro y un hierro.";
                    dibujarTextoSombreado(texto, x, y + 20, 20);
                    break;
                case 5:
                    texto = "Un talismanSecreto se crea con un hierro ";
                    dibujarTextoSombreado(texto, x, y, 20);
                    texto = "y dos de oro.";
                    dibujarTextoSombreado(texto, x, y + 20, 20);
                    break;
                case 6:
                    texto = "Un yelmo se crea con un hiero y un hierro.";
                    dibujarTextoSombreado(texto, x, y, 20);
                    break;
                case 7:
                    texto = "Un peto se crea con dos de hierro y un oro.";
                    dibujarTextoSombreado(texto, x, y, 20);
                    break;
                case 8:
                    texto = "Un oro se crea con una mena_oro y ";
                    dibujarTextoSombreado(texto, x, y, 20);
                    texto = "un carbón.";
                    dibujarTextoSombreado(texto, x, y + 20, 20);
                    break;
                case 9:
                    texto = "Un hierro se crea con una mena de hierro y ";
                    dibujarTextoSombreado(texto, x, y, 20);
                    texto = "un carbón.";
                    dibujarTextoSombreado(texto, x, y + 20, 20);
                    break;
            }
        }
        texto = "Escudo";
        dibujarTextoSombreado(texto, 605, 150, 20);
        texto = "Esc Oro";
        dibujarTextoSombreado(texto, 605, 170, 20);
        texto = "Espada";
        dibujarTextoSombreado(texto, 605, 190, 20);
        texto = "Esp Fuego";
        dibujarTextoSombreado(texto, 605, 210, 20);
        texto = "Vara Mago";
        dibujarTextoSombreado(texto, 605, 230, 20);
        texto = "Talisman";
        dibujarTextoSombreado(texto, 605, 250, 20);
        texto = "Yelmo";
        dibujarTextoSombreado(texto, 605, 270, 20);
        texto = "Peto";
        dibujarTextoSombreado(texto, 605, 290, 20);
        texto = "Oro";
        dibujarTextoSombreado(texto, 605, 310, 20);
        texto = "Hierro";
        dibujarTextoSombreado(texto, 605, 330, 20);
        texto = "salir";
        dibujarTextoSombreado(texto, 605, 350, 20);
        switch (numeroMenu) {
            case 0:
                dibujarTextoSombreado(">", 590, 155, 20);
                break;
            case 1:
                dibujarTextoSombreado(">", 590, 175, 20);
                break;
            case 2:
                dibujarTextoSombreado(">", 590, 195, 20);
                break;
            case 3:
                dibujarTextoSombreado(">", 590, 215, 20);
                break;
            case 4:
                dibujarTextoSombreado(">", 590, 235, 20);
                break;
            case 5:
                dibujarTextoSombreado(">", 590, 255, 20);
                break;
            case 6:
                dibujarTextoSombreado(">", 590, 275, 20);
                break;
            case 7:
                dibujarTextoSombreado(">", 590, 295, 20);
                break;
            case 8:
                dibujarTextoSombreado(">", 590, 315, 20);
                break;
            case 9:
                dibujarTextoSombreado(">", 590, 335, 20);
                break;
            case 10:
                dibujarTextoSombreado(">", 590, 355, 20);
                break;
        }

        if (gp.getTeclado().intentocrafteo) {
            if (numeroMenu == 0) {
                dibujarTextoSombreado(mensaje, x, y, 20);
            }
            if (numeroMenu == 1) {
                dibujarTextoSombreado(mensaje, x, y, 20);
            }
            if (numeroMenu == 2) {
                dibujarTextoSombreado(mensaje, x, y, 20);
            }
            if (numeroMenu == 3) {
                dibujarTextoSombreado(mensaje, x, y, 20);
            }
            if (numeroMenu == 4) {
                dibujarTextoSombreado(mensaje, x, y, 20);

            }
            if (numeroMenu == 5) {
                dibujarTextoSombreado(mensaje, x, y, 20);
            }
            if (numeroMenu == 6) {
                dibujarTextoSombreado(mensaje, x, y, 20);
            }
            if (numeroMenu == 7) {
                dibujarTextoSombreado(mensaje, x, y, 20);
            }
            if (numeroMenu == 8) {
                dibujarTextoSombreado(mensaje, x, y, 20);
            }
            if (numeroMenu == 9) {
                dibujarTextoSombreado(mensaje, x, y, 20);
            }
            if (numeroMenu == 10) {
                dibujarTextoSombreado(mensaje, x, y, 20);
            }
        }
    }

    /**
     * metodo que dibuja el inventario
     *
     * @param craftear
     */
    public void dibujarInventario(boolean craftear) {
        String texto;
        dibujarVentanaGenerica(2, 110, 140, 280);
        texto = "Craftear";
        dibujarTextoSombreado(texto, 25, 150, 20);
        texto = "Equipar";
        dibujarTextoSombreado(texto, 25, 200, 20);
        texto = "Stats";
        dibujarTextoSombreado(texto, 25, 250, 20);
        texto = "Consumir";
        dibujarTextoSombreado(texto, 25, 300, 20);
        if (!craftear && !gp.getTeclado().menuEquipar&&!gp.getTeclado().menuStats&&!gp.getTeclado().consumir) {
            if (numeroMenu == 0) {
                dibujarTextoSombreado(">", 8, 150, 20);
            }
            if (numeroMenu == 1) {
                dibujarTextoSombreado(">", 8, 200, 20);
            }
            if (numeroMenu == 2) {
                dibujarTextoSombreado(">", 8, 250, 20);
            }
            if (numeroMenu == 3) {
                dibujarTextoSombreado(">", 8, 300, 20);
            }
        }
    }

    /**
     * metodo para dibujar el menu de pausa
     */
    public void dibujarPausa() {

        dibujarTextoSombreado("PAUSA", 250, gp.getHeight() / 4,95);

        String texto = "Musica";
        int x = 250;
        int y = 300;
        dibujarTextoSombreado(texto, x, y,40);
        if (numeroMenu == 0) {
            dibujarTextoSombreado(">", x - 25, y,40);
        }

        texto = "Salir";
        x = 250;
        y = 350;
        dibujarTextoSombreado(texto, x, y,40);
        if (numeroMenu == 1) {
            dibujarTextoSombreado(">", x - 25, y,40);
        }

    }

    /**
     * metodo que dibuja el menu de iniciao
     */
    public void dibujarMenuInicio() {
        int xWindow = 300;
        int yWindow = 130;
        //fondo
        if (pantallaDelTitulo == 0) {
            g2d.setColor(new Color(35, 164, 187));
            g2d.fillRect(0, 0, gp.getWidth(), gp.getHeight());
            //titulo
            String texto = "SURVIVAL DUNGEONS";
            int x = 5;
            int y = gp.getHeight() / 4;
            dibujarTextoSombreado(texto, x, y, 65);
            //sombreado
            g2d.setColor(Color.BLACK);
            g2d.drawString(texto, x + 3, y + 3);
            //instrucciones
            texto = "Interactua dentro de menus con W, S y ENTER";
            x = 50;
            y = 550;
            dibujarTextoSombreado(texto, x, y, 20);
            //imagen del personaje
            try {
                //cargamos la imagen del personaje
                String imagePath = "src/recursos/imagenes/caballero.png";
                BufferedImage imagenPlantillaBuffered = ImageIO.read(new File(imagePath));
                Spritesheet plantillaJugador = new Spritesheet(imagenPlantillaBuffered, 6, 4);
                BufferedImage img = plantillaJugador.getImg(0, 2);
                //la escalamos usando el metodo por defecto
                Image imgEscalada = img.getScaledInstance(100, 100, Image.SCALE_SMOOTH);
//            dibujamos en pantalla
                g2d.drawImage(imgEscalada, 350, 400, null);
            } catch (Exception e) {
                System.out.println("ERROR: No se encuentra la imagen del jugador");
            }
            //menu
            g2d.setFont(g2d.getFont().deriveFont(Font.BOLD, 40F));
            texto = "Nueva aventura";
            x = 250;
            y = 250;
            g2d.drawString(texto, x, y);
            if (numeroMenu == 0) {
                g2d.drawString(">", x - 25, y);
            }

            g2d.setFont(g2d.getFont().deriveFont(Font.BOLD, 40F));
            texto = "Salir";
            x = 240;
            y = 300;
            g2d.drawString(texto, x, y);
            if (numeroMenu == 1) {
                g2d.drawString(">", x - 25, y);
            }
        } else if (pantallaDelTitulo == 1) {
            g2d.setColor(new Color(35, 164, 187));
            g2d.fillRect(0, 0, gp.getWidth(), gp.getHeight());
            g2d.setColor(Color.BLACK);
            g2d.setFont(g2d.getFont().deriveFont(Font.BOLD, 60F));
            String texto = "Seleeciona tu clase";
            int x = 35;
            int y = gp.getTamañofinalBaldosa() * 2;
            g2d.drawString(texto, x, y);
            texto = Clase.MAGO.toString();
            g2d.setFont(g2d.getFont().deriveFont(Font.BOLD, 40F));
            y = y + gp.getTamañofinalBaldosa() * 3;
            g2d.setColor(Color.BLACK);
            g2d.drawString(texto, x, y);
            g2d.setColor(Color.WHITE);
            g2d.drawString(texto, x + 3, y + 3);
            if (numeroMenu == 0) {
                g2d.drawString("______", x, y + 15);
                dibujarVentanaGenerica(xWindow, yWindow, gp.getWidth() / 2 + gp.getTamañofinalBaldosa(), gp.getHeight() / 2 + (gp.getTamañofinalBaldosa() * 2));
                texto = "Eres un inteligente mago, un\nser que busca los misterios de\nla vida y como" +
                        "funciona el\nmundo,utilizas el don que te \nconcedieron para ello \n\nMagia+10        Mana+20\nVidaMax+5";
                for (String line : texto.split("\n")) {
                    g2d.setFont(g2d.getFont().deriveFont(Font.BOLD, 23F));
                    g2d.setColor(Color.BLACK);
                    g2d.drawString(line, xWindow + 15, yWindow + 28);
                    g2d.setColor(Color.WHITE);
                    g2d.drawString(line, xWindow + 18, yWindow + 31);
                    yWindow += 40;
                }
                yWindow = 130;
            }
            texto = Clase.GUERRERO.toString();
            g2d.setFont(g2d.getFont().deriveFont(Font.BOLD, 40F));
            y = y + gp.getTamañofinalBaldosa();
            g2d.setColor(Color.BLACK);
            g2d.drawString(texto, x, y);
            g2d.setColor(Color.WHITE);
            g2d.drawString(texto, x + 3, y + 3);
            if (numeroMenu == 1) {
                g2d.drawString("______", x, y + 15);
                dibujarVentanaGenerica(xWindow, yWindow, gp.getWidth() / 2 + gp.getTamañofinalBaldosa(), gp.getHeight() / 2 + (gp.getTamañofinalBaldosa() * 2));
                texto = "Eres un valiente guerrero, un \nsoldado que vive por y para la\nbatalla" +
                        " y siempre en busca de ser\nel mejor guerrero jamas visto,\nno hay quien pueda contigo\n\nFuerza+10        VidaMax+5\nMagia=0";
                for (String line : texto.split("\n")) {
                    g2d.setFont(g2d.getFont().deriveFont(Font.BOLD, 23F));
                    g2d.setColor(Color.BLACK);
                    g2d.drawString(line, xWindow + 15, yWindow + 28);
                    g2d.setColor(Color.WHITE);
                    g2d.drawString(line, xWindow + 18, yWindow + 31);
                    yWindow += 40;
                }
                yWindow = 130;
            }
            texto = Clase.CLERIGO.toString();
            g2d.setFont(g2d.getFont().deriveFont(Font.BOLD, 40F));
            y = y + gp.getTamañofinalBaldosa();
            g2d.setColor(Color.BLACK);
            g2d.drawString(texto, x, y);
            g2d.setColor(Color.WHITE);
            g2d.drawString(texto, x + 3, y + 3);
            if (numeroMenu == 2) {
                g2d.drawString("______", x, y + 15);
                dibujarVentanaGenerica(xWindow, yWindow, gp.getWidth() / 2 + gp.getTamañofinalBaldosa(), gp.getHeight() / 2 + (gp.getTamañofinalBaldosa() * 2));
                texto = "Eres un clerigo, un siervo de dios\ndiste tu vida en pos de ayudar al\n projimo" +
                        " y servir segun tu dios\n dice, debido a la ayuda de tu dios\nno hay quien pueda contigo\n\nDestreza-5        VidaMax+10\nMagia+5        Mana+10";
                for (String line : texto.split("\n")) {
                    g2d.setFont(g2d.getFont().deriveFont(Font.BOLD, 23F));
                    g2d.setColor(Color.BLACK);
                    g2d.drawString(line, xWindow + 15, yWindow + 28);
                    g2d.setColor(Color.WHITE);
                    g2d.drawString(line, xWindow + 18, yWindow + 31);
                    yWindow += 40;
                }
                yWindow = 130;
            }
            texto = Clase.PICARO.toString();
            g2d.setFont(g2d.getFont().deriveFont(Font.BOLD, 40F));
            y = y + gp.getTamañofinalBaldosa();
            g2d.setColor(Color.BLACK);
            g2d.drawString(texto, x, y);
            g2d.setColor(Color.WHITE);
            g2d.drawString(texto, x + 3, y + 3);
            if (numeroMenu == 3) {
                g2d.drawString("______", x, y + 15);
                dibujarVentanaGenerica(xWindow, yWindow, gp.getWidth() / 2 + gp.getTamañofinalBaldosa(), gp.getHeight() / 2 + (gp.getTamañofinalBaldosa() * 2));
                texto = "Eres un ladron y un bandido\nte adentras en las sombras y\nacechas con sigilo" +
                        " expertos en\nsituaciones peliagudas tus\nenemigos caeran sin haberte\n visto\n\nDestreza+10        Fuerza+5";
                for (String line : texto.split("\n")) {
                    g2d.setFont(g2d.getFont().deriveFont(Font.BOLD, 23F));
                    g2d.setColor(Color.BLACK);
                    g2d.drawString(line, xWindow + 15, yWindow + 28);
                    g2d.setColor(Color.WHITE);
                    g2d.drawString(line, xWindow + 18, yWindow + 31);
                    yWindow += 40;
                }
                yWindow = 130;
            }
            texto = "SALIR";
            g2d.setFont(g2d.getFont().deriveFont(Font.BOLD, 40F));
            y = y + gp.getTamañofinalBaldosa();
            g2d.setColor(Color.BLACK);
            g2d.drawString(texto, x, y);
            g2d.setColor(Color.WHITE);
            g2d.drawString(texto, x + 3, y + 3);
            if (numeroMenu == 4) {
                g2d.drawString("______", x, y + 15);
            }
        } else if (pantallaDelTitulo == 2) {
            g2d.setColor(new Color(35, 164, 187));
            g2d.fillRect(0, 0, gp.getWidth(), gp.getHeight());
            g2d.setColor(Color.BLACK);
            g2d.setFont(g2d.getFont().deriveFont(Font.BOLD, 60F));
            String texto = "Seleeciona tu raza";
            int x = 35;
            int y = gp.getTamañofinalBaldosa() * 2;
            g2d.drawString(texto, x, y);

            texto = Raza.HUMANO.toString();
            g2d.setFont(g2d.getFont().deriveFont(Font.BOLD, 40F));
            y = y + gp.getTamañofinalBaldosa() * 3;
            g2d.setColor(Color.BLACK);
            g2d.drawString(texto, x, y);
            g2d.setColor(Color.WHITE);
            g2d.drawString(texto, x + 3, y + 3);
            if (numeroMenu == 0) {
                g2d.drawString("______", x, y + 15);
                dibujarVentanaGenerica(xWindow, yWindow, gp.getWidth() / 2 + gp.getTamañofinalBaldosa(), gp.getHeight() / 2 + (gp.getTamañofinalBaldosa() * 2));
                texto = "Eres un humano la raza mas\ncomunen todo el mundo\ntu raza no destaca en nada\npero no se queda atras" +
                        "\nes por eso que prosperasteis\n\nFuerza=10        Magia=10\nVida=100        Velocidad=3\nDestreza=10";
                for (String line : texto.split("\n")) {
                    g2d.setFont(g2d.getFont().deriveFont(Font.BOLD, 23F));
                    g2d.setColor(Color.BLACK);
                    g2d.drawString(line, xWindow + 15, yWindow + 28);
                    g2d.setColor(Color.WHITE);
                    g2d.drawString(line, xWindow + 18, yWindow + 31);
                    yWindow += 40;
                }
                yWindow = 130;
            }
            texto = Raza.ORCO.toString();
            g2d.setFont(g2d.getFont().deriveFont(Font.BOLD, 40F));
            y = y + gp.getTamañofinalBaldosa();
            g2d.setColor(Color.BLACK);
            g2d.drawString(texto, x, y);
            g2d.setColor(Color.WHITE);
            g2d.drawString(texto, x + 3, y + 3);
            if (numeroMenu == 1) {
                g2d.drawString("______", x, y + 15);
                dibujarVentanaGenerica(xWindow, yWindow, gp.getWidth() / 2 + gp.getTamañofinalBaldosa(), gp.getHeight() / 2 + (gp.getTamañofinalBaldosa() * 2));
                texto = "Eres un temible orco que vive en\nlas llanuras, tu raza posee una\ngran fuerza y destreza por\n" +
                        "vuestra cultura guerrera\nos consideran unos barbaros \n\nFuerza=15        Magia=5\nVida=100        Velocidad=2\nDestreza=15";
                for (String line : texto.split("\n")) {
                    g2d.setFont(g2d.getFont().deriveFont(Font.BOLD, 23F));
                    g2d.setColor(Color.BLACK);
                    g2d.drawString(line, xWindow + 15, yWindow + 28);
                    g2d.setColor(Color.WHITE);
                    g2d.drawString(line, xWindow + 18, yWindow + 31);
                    yWindow += 40;
                }
                yWindow = 130;
            }
            texto = Raza.ELFO.toString();
            g2d.setFont(g2d.getFont().deriveFont(Font.BOLD, 40F));
            y = y + gp.getTamañofinalBaldosa();
            g2d.setColor(Color.BLACK);
            g2d.drawString(texto, x, y);
            g2d.setColor(Color.WHITE);
            g2d.drawString(texto, x + 3, y + 3);
            if (numeroMenu == 2) {
                g2d.drawString("______", x, y + 15);
                dibujarVentanaGenerica(xWindow, yWindow, gp.getWidth() / 2 + gp.getTamañofinalBaldosa(), gp.getHeight() / 2 + (gp.getTamañofinalBaldosa() * 2));
                texto = "Eres un elegante Elfo que vive en\nlos bosques, tu raza posee gran\nafinidad al mana y mucha destreza\n" +
                        "por ello os considerais superiores \n\nFuerza=5        Magia=15\nVida=70        Velocidad=5\nDestreza=20";
                for (String line : texto.split("\n")) {
                    g2d.setFont(g2d.getFont().deriveFont(Font.BOLD, 23F));
                    g2d.setColor(Color.BLACK);
                    g2d.drawString(line, xWindow + 15, yWindow + 28);
                    g2d.setColor(Color.WHITE);
                    g2d.drawString(line, xWindow + 18, yWindow + 31);
                    yWindow += 40;
                }
                yWindow = 130;
            }
            texto = Raza.ENANO.toString();
            g2d.setFont(g2d.getFont().deriveFont(Font.BOLD, 40F));
            y = y + gp.getTamañofinalBaldosa();
            g2d.setColor(Color.BLACK);
            g2d.drawString(texto, x, y);
            g2d.setColor(Color.WHITE);
            g2d.drawString(texto, x + 3, y + 3);
            if (numeroMenu == 3) {

                g2d.drawString("______", x, y + 15);
                dibujarVentanaGenerica(xWindow, yWindow, gp.getWidth() / 2 + gp.getTamañofinalBaldosa(), gp.getHeight() / 2 + (gp.getTamañofinalBaldosa() * 2));
                texto = "Eres un orgulloso Enano que\n vive en las minas, tu raza \n posee gran fuerza y resitencia \n " +
                        "a pesar de tu estatura\n\nFuerza=20        Magia=5\nVida=120        Velocidad=2\nDestreza=5";
                for (String line : texto.split("\n")) {
                    g2d.setFont(g2d.getFont().deriveFont(Font.BOLD, 23F));
                    g2d.setColor(Color.BLACK);
                    g2d.drawString(line, xWindow + 15, yWindow + 28);
                    g2d.setColor(Color.WHITE);
                    g2d.drawString(line, xWindow + 18, yWindow + 31);
                    yWindow += 40;
                }
                yWindow = 130;
            }
            texto = "SALIR";
            g2d.setFont(g2d.getFont().deriveFont(Font.BOLD, 40F));
            y = y + gp.getTamañofinalBaldosa();
            g2d.setColor(Color.BLACK);
            g2d.drawString(texto, x, y);
            g2d.setColor(Color.WHITE);
            g2d.drawString(texto, x + 3, y + 3);
            if (numeroMenu == 4) {
                g2d.drawString("______", x, y + 15);
            }
        } else if (pantallaDelTitulo == 3) {
            g2d.setColor(new Color(35, 164, 187));
            g2d.fillRect(0, 0, gp.getWidth(), gp.getHeight());
            g2d.setColor(Color.BLACK);
            g2d.setFont(g2d.getFont().deriveFont(Font.BOLD, 40F));
            String texto = "Dinos tu nombre";
            int x = 35;
            int y = gp.getTamañofinalBaldosa() * 2;
            g2d.drawString(texto, x, y);
            texto = String.valueOf(gp.getTeclado().textoIngresado);
            y += gp.getTamañofinalBaldosa() * 2;
            g2d.setColor(Color.BLACK);
            g2d.drawString(texto, x, y);
            g2d.setColor(Color.WHITE);
            g2d.drawString(texto, x + 3, y + 3);
            y += gp.getTamañofinalBaldosa();
            if (texto.isEmpty()) {
                dibujarTextoSombreado("Debe escribir un nombre", 200, 500, 25);
            }
        } else if (pantallaDelTitulo == 4) {
            g2d.setColor(new Color(35, 164, 187));
            g2d.fillRect(0, 0, gp.getWidth(), gp.getHeight());
            g2d.setColor(Color.BLACK);
            g2d.setFont(g2d.getFont().deriveFont(Font.BOLD, 60F));
            String texto = "Estas seguro?";
            int x = gp.getWidth() / 5;
            int y = gp.getTamañofinalBaldosa() * 2;
            g2d.drawString(texto, x, y);
            texto = "SI";
            g2d.setFont(g2d.getFont().deriveFont(Font.BOLD, 50F));
            y = y + gp.getTamañofinalBaldosa() * 3;
            x = x - 20;
            g2d.setColor(Color.BLACK);
            g2d.drawString(texto, x, y);
            g2d.setColor(Color.WHITE);
            g2d.drawString(texto, x + 3, y + 3);
            if (numeroMenu == 0) {
                g2d.drawString(">", x - 25, y);
            }
            texto = "NO";
            g2d.setFont(g2d.getFont().deriveFont(Font.BOLD, 50F));
            x = x + gp.getTamañofinalBaldosa() * 8;
            g2d.setColor(Color.BLACK);
            g2d.drawString(texto, x, y);
            g2d.setColor(Color.WHITE);
            g2d.drawString(texto, x + 3, y + 3);
            if (numeroMenu == 1) {
                g2d.drawString(">", x - 25, y);
            }
        }
    }

    /**
     * metodo para generar una ventana mas desarrollada que un rectangulo
     *
     * @param x
     * @param y
     * @param width
     * @param height
     */
    public void dibujarVentanaGenerica(int x, int y, int width, int height) {
        Color c = new Color(0, 0, 0, 150);
        g2d.setColor(c);
        g2d.fillRoundRect(x, y, width, height, 35, 35);
        c = new Color(255, 255, 255);
        g2d.setColor(c);
        g2d.setStroke(new BasicStroke(5));
        g2d.drawRoundRect(x + 5, y + 5, width - 10, height - 10, 25, 25);
    }

    /**
     * metodo para dibujar con sombreado
     *
     * @param texto
     * @param x
     * @param y
     * @param tamaño
     */
    public void dibujarTextoSombreado(String texto, int x, int y, float tamaño) {
        //bucle que trata los saltos de linea.
        for (String linea : texto.split("\n")) {
            g2d.setFont(g2d.getFont().deriveFont(Font.BOLD, tamaño));
            g2d.setColor(Color.BLACK);
            g2d.drawString(linea, x, y);
            //sombreado
            g2d.setColor(Color.WHITE);
            g2d.drawString(linea, x + 3, y + 3);
        }
    }

    public GamePanel getGp() {
        return gp;
    }

    public void setGp(GamePanel gp) {
        this.gp = gp;
    }

    public Font getFonte() {
        return fonte;
    }

    public void setFonte(Font fonte) {
        this.fonte = fonte;
    }

    public int getNumeroMenu() {
        return numeroMenu;
    }

    public void setNumeroMenu(int numeroMenu) {
        this.numeroMenu = numeroMenu;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public boolean isHayMensaje() {
        return hayMensaje;
    }

    public void setHayMensaje(boolean hayMensaje) {
        this.hayMensaje = hayMensaje;
    }

    public boolean isJuegoTerminado() {
        return juegoTerminado;
    }

    public void setJuegoTerminado(boolean juegoTerminado) {
        this.juegoTerminado = juegoTerminado;
    }

    public Graphics2D getG2d() {
        return g2d;
    }

    public void setG2d(Graphics2D g2d) {
        this.g2d = g2d;
    }

    public int getContadorMensaje() {
        return contadorMensaje;
    }

    public void setContadorMensaje(int contadorMensaje) {
        this.contadorMensaje = contadorMensaje;
    }

    public BufferedImage getImagen_llave() {
        return imagen_llave;
    }

    public void setImagen_llave(BufferedImage imagen_llave) {
        this.imagen_llave = imagen_llave;
    }

    public int getPantallaDelTitulo() {
        return pantallaDelTitulo;
    }

    public void setPantallaDelTitulo(int pantallaDelTitulo) {
        this.pantallaDelTitulo = pantallaDelTitulo;
    }


}