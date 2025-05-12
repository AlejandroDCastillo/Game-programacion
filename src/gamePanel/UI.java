package gamePanel;

import entidades.Clase;
import entidades.Entidad;
import entidades.Jugador;
import entidades.Raza;
import item.Item;
import recursos.imagenes.Spritesheet;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class UI {

    GamePanel gp;
    Font fonte = new Font("Arial", Font.ITALIC, 30);
    String mensaje = "";
    boolean hayMensaje = false;
    public boolean juegoTerminado = false;
    Graphics2D g2d;
    int contadorMensaje = 0;
    BufferedImage imagen_llave;
    public int numeroMenu = 0;
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
            //pausa
            if (gp.estadoJuego == gp.pausa) {
                dibujarPausa();
            }
            //inventario
            if (gp.getTeclado().menuBoolean) {
                dibujarInventario(gp.getTeclado().craftear);
                if (gp.getTeclado().craftear) {
                    dibujarMenuCrafteo(gp.getTeclado().craftear);
                } else if (gp.getTeclado().menuEquipar) {
                    dibujarMenuEquipar(gp.getTeclado().menuEquipar);
                }
            }

            //combate
            if (gp.estadoJuego == gp.combate) {
                dibujarCombate();
            }
        }
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
            g2d.drawImage(imagenequipados, 600, 250, null);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        // objetos equipados
        Jugador pj = gp.getJugador();
        Item item = null;
        if (pj.getArma() != null) {
            item = pj.getArma();
            g2d.drawImage(item.getPlantillaInventario().getImg(item.getX(), item.getY()), 622
                    , 325, 45, 45, null);
        }
        if (pj.getArmadura() != null) {
            item = pj.getArmadura();
            g2d.drawImage(item.getPlantillaInventario().getImg(item.getX(), item.getY()), 622
                    , 375, 45, 45, null);
        }
        if (pj.getCabeza() != null) {
            item = pj.getCabeza();
            g2d.drawImage(item.getPlantillaInventario().getImg(item.getX(), item.getY()), 622
                    , 427, 45, 45, null);

        }
        if (pj.getEscudo() != null) {
            item = pj.getEscudo();
            g2d.drawImage(item.getPlantillaInventario().getImg(item.getX(), item.getY()), 622
                    , 470, 45, 45, null);
        }
        //menu de batalla
        int x = 50;
        String texto;
        //  dibujarVentanaGenerica(2, 110, 140, 280);
        texto = "Atacar";
        dibujarTextoSombreado(texto, x, 350, 25);
//        texto = "Defender";
//        dibujarTextoSombreado(texto, x, 410, 25);
        texto = "Consumibles";
        dibujarTextoSombreado(texto, x, 470, 25);
        texto = "Huir";
        dibujarTextoSombreado(texto, x, 530, 25);
        if (numeroMenu == 0) {
            g2d.drawString(">", x - 25, 350);
        }
        if (numeroMenu == 1) {
            g2d.drawString(">", x - 25, 470);
        }
        if (numeroMenu == 2) {
            g2d.drawString(">", x - 25, 530);
        }

        if (gp.getTeclado().consumible) {
            dibujarVentanaGenerica(250, gp.getHeight() / 2 + 30, 200, 250);
            dibujarTextoSombreado("P.Mana", 290, 400, 25);
            dibujarTextoSombreado("P.Vida", 290, 500, 25);
        }
        if (gp.getTeclado().atacar) {
            dibujarVentanaGenerica(250, gp.getHeight() / 2 + 30, 200, 250);
            dibujarTextoSombreado("Basico", 290, 400, 25);
            dibujarTextoSombreado("Critico", 290, 500, 25);
        }

    }

    /**
     * metodo que dibuja el menu de equipar
     *
     * @param equipar
     */
    public void dibujarMenuEquipar(boolean equipar) {
        dibujarVentanaGenerica(580, 110, 180, 280);
        String texto;
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
        if (equipar) {
            int x = 130, y = 50;
            dibujarVentanaGenerica(120, 0, 480, 110);
            if (numeroMenu == 0) {
                dibujarTextoSombreado(">", 590, 155, 25);
                texto = "Un yelmo se crea con un hiero y un hierro.";
                dibujarTextoSombreado(texto, x, y, 15);
            }
            if (numeroMenu == 1) {
                dibujarTextoSombreado(">", 590, 175, 25);
                texto = "Un yelmo se crea con un hiero y un hierro.";
                dibujarTextoSombreado(texto, x, y, 15);
            }
            if (numeroMenu == 2) {
                dibujarTextoSombreado(">", 590, 195, 25);
                texto = "Un yelmo se crea con un hiero y un hierro.";
                dibujarTextoSombreado(texto, x, y, 15);
            }
            if (numeroMenu == 3) {
                dibujarTextoSombreado(">", 590, 215, 25);
                texto = "Un yelmo se crea con un hiero y un hierro.";
                dibujarTextoSombreado(texto, x, y, 15);
            }
            if (numeroMenu == 4) {
                dibujarTextoSombreado(">", 590, 235, 25);
                texto = "Un yelmo se crea con un hiero y un hierro.";
                dibujarTextoSombreado(texto, x, y, 15);
            }
            if (numeroMenu == 5) {
                dibujarTextoSombreado(">", 590, 255, 25);
                texto = "Un yelmo se crea con un hiero y un hierro.";
                dibujarTextoSombreado(texto, x, y, 15);
            }
            if (numeroMenu == 6) {
                dibujarTextoSombreado(">", 590, 275, 25);
                texto = "Un yelmo se crea con un hiero y un hierro.";
                dibujarTextoSombreado(texto, x, y, 15);
            }
            if (numeroMenu == 7) {
                dibujarTextoSombreado(">", 590, 295, 25);
                texto = "Un yelmo se crea con un hiero y un hierro.";
                dibujarTextoSombreado(texto, x, y, 15);
            }
            if (numeroMenu == 8) {
                dibujarTextoSombreado(">", 590, 315, 25);
                texto = "Un yelmo se crea con un hiero y un hierro.";
                dibujarTextoSombreado(texto, x, y, 15);
            }
        }
    }

    /**
     * metodo que dibuja el menu de craftear
     *
     * @param craftear
     */
    public void dibujarMenuCrafteo(boolean craftear) {
        dibujarVentanaGenerica(580, 110, 180, 280);
        String texto;
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
        if (craftear) {


            dibujarVentanaGenerica(120, 0, 480, 110);
            int x = 130, y = 50;
            if (numeroMenu == 0) {
                dibujarTextoSombreado(">", 590, 155, 25);
                texto = "Un escudo se crea con una madera y dos de hierro.";
                dibujarTextoSombreado(texto, x, y, 15);
            }
            if (numeroMenu == 1) {
                dibujarTextoSombreado(">", 590, 175, 25);
                texto = "Un escudoOro se crea con una madera y dos de oro.";
                dibujarTextoSombreado(texto, x, y, 15);
            }
            if (numeroMenu == 2) {
                dibujarTextoSombreado(">", 590, 195, 25);
                texto = "Una espada se crea con una madera y un hierro.";
                dibujarTextoSombreado(texto, x, y, 15);
            }
            if (numeroMenu == 3) {
                dibujarTextoSombreado(">", 590, 215, 25);
                texto = "Una espadaFuego se crea con una madera,";
                dibujarTextoSombreado(texto, x, y, 15);
                texto = "un hierro, un oro y un carbón.";
                dibujarTextoSombreado(texto, x, y + 20, 15);
            }
            if (numeroMenu == 4) {
                dibujarTextoSombreado(">", 590, 235, 25);
                texto = "Una varaMago se crea con dos de madera,";
                dibujarTextoSombreado(texto, x, y, 15);
                texto = "un oro y un hierro.";
                dibujarTextoSombreado(texto, x, y + 20, 15);
            }

            if (numeroMenu == 5) {
                dibujarTextoSombreado(">", 590, 255, 25);
                texto = "Un talismanSecreto se crea con un hierro y dos de oro.";
                dibujarTextoSombreado(texto, x, y, 15);

            }
            if (numeroMenu == 6) {
                dibujarTextoSombreado(">", 590, 275, 25);
                texto = "Un yelmo se crea con un hiero y un hierro.";
                dibujarTextoSombreado(texto, x, y, 15);
            }
            if (numeroMenu == 7) {
                dibujarTextoSombreado(">", 590, 295, 25);
                texto = "Un peto se crea con dos de hierro y un oro.";
                dibujarTextoSombreado(texto, x, y, 15);

            }
            if (numeroMenu == 8) {
                dibujarTextoSombreado(">", 590, 315, 25);
                texto = "Un oro se crea con una mena_oro y un carbón.";
                dibujarTextoSombreado(texto, x, y, 15);
            }
            if (numeroMenu == 9) {
                dibujarTextoSombreado(">", 590, 335, 25);
                texto = "Un hierro se crea con una mena_hierro y un carbón.";
                dibujarTextoSombreado(texto, x, y, 15);
            }
            if (numeroMenu == 10) {
                dibujarTextoSombreado(">", 590, 355, 25);
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
        texto = "Destruir";
        dibujarTextoSombreado(texto, 25, 250, 20);
        if (!craftear && !gp.getTeclado().menuEquipar) {
            if (numeroMenu == 0) {
                dibujarTextoSombreado(">", 8, 150, 25);
            }
            if (numeroMenu == 1) {
                dibujarTextoSombreado(">", 8, 200, 25);
            }
            if (numeroMenu == 2) {
                dibujarTextoSombreado(">", 8, 250, 25);
            }
        }
    }

    /**
     * metodo para dibujar el menu de pausa
     */
    public void dibujarPausa() {
        g2d.setFont(g2d.getFont().deriveFont(Font.BOLD, 95F));
        g2d.setColor(Color.DARK_GRAY);
        g2d.drawString("PAUSA", 250, gp.getHeight() / 4);

        g2d.setFont(g2d.getFont().deriveFont(Font.BOLD, 40F));
        String texto = "Guerdar Partida";
        int x = 250;
        int y = 250;
        g2d.drawString(texto, x, y);
        if (numeroMenu == 0) {
            g2d.drawString(">", x - 25, y);
        }

        g2d.setFont(g2d.getFont().deriveFont(Font.BOLD, 40F));
        texto = "Música";
        x = 250;
        y = 300;
        g2d.drawString(texto, x, y);
        if (numeroMenu == 1) {
            g2d.drawString(">", x - 25, y);
        }

        g2d.setFont(g2d.getFont().deriveFont(Font.BOLD, 40F));
        texto = "Salir";
        x = 250;
        y = 350;
        g2d.drawString(texto, x, y);
        if (numeroMenu == 2) {
            g2d.drawString(">", x - 25, y);
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
            g2d.setFont(g2d.getFont().deriveFont(Font.BOLD, 65F));
            String texto = "SURVIVAL DUNGEONS";
            int x = 5;
            int y = gp.getHeight() / 4;
            g2d.setColor(Color.WHITE);
            g2d.drawString(texto, x, y);
            //sombreado
            g2d.setColor(Color.BLACK);
            g2d.drawString(texto, x + 3, y + 3);
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
            texto = "Cargar Aventura";
            x = 240;
            y = 300;
            g2d.drawString(texto, x, y);
            if (numeroMenu == 1) {
                g2d.drawString(">", x - 25, y);
            }

            g2d.setFont(g2d.getFont().deriveFont(Font.BOLD, 40F));
            texto = "Salir";
            x = 350;
            y = 350;
            g2d.drawString(texto, x, y);
            if (numeroMenu == 2) {
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
        for (String line : texto.split("\n")) {
            g2d.setFont(g2d.getFont().deriveFont(Font.BOLD, tamaño));
            g2d.setColor(Color.BLACK);
            g2d.drawString(texto, x, y);
            //sombreado
            g2d.setColor(Color.WHITE);
            g2d.drawString(texto, x + 3, y + 3);
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
