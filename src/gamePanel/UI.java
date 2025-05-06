package gamePanel;

import entidades.Clase;
import entidades.Raza;
import item.objetos.Llave;
import item.objetos.Objetos;
import recursos.imagenes.Spritesheet;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.BufferOverflowException;
import java.util.LinkedHashMap;
import java.util.Map;

public class UI {

    GamePanel gp;
    Font fonte = new Font("Arial", Font.ITALIC, 30);
    String mensaje = "";
    boolean hayMensaje=false;
    public boolean juegoTerminado=false;
    Graphics2D g2d;
    int contadorMensaje =0;
    BufferedImage imagen_llave;
    public int numeroMenu=0;
    public String[]Dialogos = new String[3];
    public int pantallaDelTitulo=0; // pantalla del titulo
    public UI(GamePanel gp) {
        this.gp=gp;
        try {
            //lectura de la fuente de un archivo.
            File archivoFuente = new File("src/recursos/font/Minecraft.ttf");
            System.out.printf(archivoFuente.getAbsolutePath());
            fonte = Font.createFont(Font.TRUETYPE_FONT,archivoFuente);
        } catch (FontFormatException e) {
            throw new RuntimeException(e);
        }catch (IOException e){
            throw new RuntimeException(e);
        }
    }

    public void enseñarMensaje(String texto) {
        mensaje=texto;
        hayMensaje=true;
    }

    public void dibujar(Graphics2D g2d) {
        this.g2d=g2d;
        g2d.setFont(fonte);
        //menu inicio
        if (gp.estadoJuego==gp.menuInicio){
            dibujarMenuInicio();
        }else {
            //resto de cosas visuales del juego
            //llave
            g2d.setColor(Color.WHITE);
            g2d.drawImage(imagen_llave, gp.getTamañofinalBaldosa() / 2, gp.getTamañofinalBaldosa() / 2, gp.getTamañofinalBaldosa(), gp.getTamañofinalBaldosa(), null);
            g2d.drawString("x" + gp.getJugador().getLlaves(), 60, 65);

            if (hayMensaje) {
                g2d.drawString(mensaje, gp.getTamañoAnchuraPantalla() / 4, 30);
                System.out.print(mensaje);
                contadorMensaje++;
            }
            if (contadorMensaje >= 100) {
                contadorMensaje = 0;
                hayMensaje = false;
            }

            if (gp.estadoJuego == gp.pausa) {
                dibujarPausa();
            }
            if(gp.getTeclado().menuBoolean){
                dibujarInventario();
            }
        }
    }

    public void dibujarInventario(){
        String texto;
        g2d.setFont(g2d.getFont().deriveFont(Font.BOLD,25F));
        g2d.setColor(Color.black);
        texto="Craftear";
        g2d.drawString(texto,15,150);
        if(numeroMenu==0){
            g2d.drawString(">",0,150);
        }
        texto="Equipar";
        g2d.drawString(texto,15,200);
        if(numeroMenu==1){
            g2d.drawString(">",0,200);
        }
        texto="Destruir";
        g2d.drawString(texto,15,250);
        if(numeroMenu==2){
            g2d.drawString(">",0,250);
        }
    }

    /**
     * metodo para dibujar el menu de pausa
     */
    public void dibujarPausa(){
        g2d.setFont(g2d.getFont().deriveFont(Font.BOLD,95F));
        g2d.setColor(Color.DARK_GRAY);
        g2d.drawString("PAUSA",250,gp.getHeight()/4);

        g2d.setFont(g2d.getFont().deriveFont(Font.BOLD,40F));
        String texto="Guerdar Partida";
        int x=250;
        int y=250;
        g2d.drawString(texto,x,y);
        if(numeroMenu==0){
            g2d.drawString(">",x-25,y);
        }

        g2d.setFont(g2d.getFont().deriveFont(Font.BOLD,40F));
        texto="Música";
        x=250;
        y=300;
        g2d.drawString(texto,x,y);
        if(numeroMenu==1){
            g2d.drawString(">",x-25,y);
        }

        g2d.setFont(g2d.getFont().deriveFont(Font.BOLD,40F));
        texto="Salir";
        x=250;
        y=350;
        g2d.drawString(texto,x,y);
        if(numeroMenu==2){
            g2d.drawString(">",x-25,y);
        }

    }

    /**
     * metodo que dibuja el menu de iniciao
     */
    public void dibujarMenuInicio(){
        int xWindow=300;
        int yWindow=130;

        //fondo
        if (pantallaDelTitulo ==0){
            g2d.setColor(new Color(35,164,187));
            g2d.fillRect(0,0,gp.getWidth(),gp.getHeight());
            //titulo
            g2d.setFont(g2d.getFont().deriveFont(Font.BOLD,65F));
            String texto = "SURVIVAL DUNGEONS";
            int x=5;
            int y=gp.getHeight()/4;
            g2d.setColor(Color.WHITE);
            g2d.drawString(texto,x,y);
            //sombreado
            g2d.setColor(Color.BLACK);
            g2d.drawString(texto,x+3,y+3);
            //imagen del personaje
            try {
                //cargamos la imagen del personaje
                String imagePath = "src/recursos/imagenes/caballero.png";
                BufferedImage imagenPlantillaBuffered = ImageIO.read(new File(imagePath));
                Spritesheet plantillaJugador = new Spritesheet(imagenPlantillaBuffered, 6, 4);
                BufferedImage img = plantillaJugador.getImg(0,2);
                //la escalamos usando el metodo por defecto
                Image imgEscalada = img.getScaledInstance(100, 100, Image.SCALE_SMOOTH);
//            dibujamos en pantalla
                g2d.drawImage(imgEscalada, 350, 400, null);
            }catch (Exception e){
                System.out.println("ERROR: No se encuentra la imagen del jugador");
            }
            //menu
            g2d.setFont(g2d.getFont().deriveFont(Font.BOLD,40F));
            texto="Nueva aventura";
            x=250;
            y=250;
            g2d.drawString(texto,x,y);
            if(numeroMenu==0){
                g2d.drawString(">",x-25,y);
            }

            g2d.setFont(g2d.getFont().deriveFont(Font.BOLD,40F));
            texto="Cargar Aventura";
            x=240;
            y=300;
            g2d.drawString(texto,x,y);
            if(numeroMenu==1){
                g2d.drawString(">",x-25,y);
            }

            g2d.setFont(g2d.getFont().deriveFont(Font.BOLD,40F));
            texto="Salir";
            x=350;
            y=350;
            g2d.drawString(texto,x,y);
            if(numeroMenu==2){
                g2d.drawString(">",x-25,y);
            }
        }else if(pantallaDelTitulo ==1){
            g2d.setColor(new Color(35,164,187));
            g2d.fillRect(0,0,gp.getWidth(),gp.getHeight());
            g2d.setColor(Color.BLACK);
            g2d.setFont(g2d.getFont().deriveFont(Font.BOLD,60F));
            String texto = "Seleeciona tu clase";
            int x=35;
            int y=gp.getTamañofinalBaldosa()*2;
            g2d.drawString(texto,x,y);
            texto= Clase.MAGO.toString();
            g2d.setFont(g2d.getFont().deriveFont(Font.BOLD,40F));
            y= y+gp.getTamañofinalBaldosa()*3;
            g2d.setColor(Color.BLACK);
            g2d.drawString(texto,x,y);
            g2d.setColor(Color.WHITE);
            g2d.drawString(texto,x+3,y+3);
            if(numeroMenu==0){
                g2d.drawString("______",x,y+15);
                dibujarVentanaGenerica(xWindow,yWindow,gp.getWidth()/2+gp.getTamañofinalBaldosa(),gp.getHeight()/2+(gp.getTamañofinalBaldosa()*2));
                texto="Eres un inteligente mago, un\nser que busca los misterios de\nla vida y como" +
                        "funciona el\nmundo,utilizas el don que te \nconcedieron para ello \n\nMagia+10        Mana+20\nVidaMax+5";
                for (String line: texto.split("\n")) {
                    g2d.setFont(g2d.getFont().deriveFont(Font.BOLD,23F));
                    g2d.setColor(Color.BLACK);
                    g2d.drawString(line,xWindow+15,yWindow+28);
                    g2d.setColor(Color.WHITE);
                    g2d.drawString(line,xWindow+18,yWindow+31);
                    yWindow+=40;
                }
                yWindow=130;
            }
            texto= Clase.GUERRERO.toString();
            g2d.setFont(g2d.getFont().deriveFont(Font.BOLD,40F));
            y= y+gp.getTamañofinalBaldosa();
            g2d.setColor(Color.BLACK);
            g2d.drawString(texto,x,y);
            g2d.setColor(Color.WHITE);
            g2d.drawString(texto,x+3,y+3);
            if(numeroMenu==1){
                g2d.drawString("______",x,y+15);
                dibujarVentanaGenerica(xWindow,yWindow,gp.getWidth()/2+gp.getTamañofinalBaldosa(),gp.getHeight()/2+(gp.getTamañofinalBaldosa()*2));
                texto="Eres un valiente guerrero, un \nsoldado que vive por y para la\nbatalla" +
                        " y siempre en busca de ser\nel mejor guerrero jamas visto,\nno hay quien pueda contigo\n\nFuerza+10        VidaMax+5\nMagia=0";
                for (String line: texto.split("\n")) {
                    g2d.setFont(g2d.getFont().deriveFont(Font.BOLD,23F));
                    g2d.setColor(Color.BLACK);
                    g2d.drawString(line,xWindow+15,yWindow+28);
                    g2d.setColor(Color.WHITE);
                    g2d.drawString(line,xWindow+18,yWindow+31);
                    yWindow+=40;
                }
                yWindow=130;            }
            texto= Clase.CLERIGO.toString();
            g2d.setFont(g2d.getFont().deriveFont(Font.BOLD,40F));
            y= y+gp.getTamañofinalBaldosa();
            g2d.setColor(Color.BLACK);
            g2d.drawString(texto,x,y);
            g2d.setColor(Color.WHITE);
            g2d.drawString(texto,x+3,y+3);
            if(numeroMenu==2){
                g2d.drawString("______",x,y+15);
                dibujarVentanaGenerica(xWindow,yWindow,gp.getWidth()/2+gp.getTamañofinalBaldosa(),gp.getHeight()/2+(gp.getTamañofinalBaldosa()*2));
                texto="Eres un clerigo, un siervo de dios\ndiste tu vida en pos de ayudar al\n projimo" +
                        " y servir segun tu dios\n dice, debido a la ayuda de tu dios\nno hay quien pueda contigo\n\nDestreza-5        VidaMax+10\nMagia+5        Mana+10";
                for (String line: texto.split("\n")) {
                    g2d.setFont(g2d.getFont().deriveFont(Font.BOLD,23F));
                    g2d.setColor(Color.BLACK);
                    g2d.drawString(line,xWindow+15,yWindow+28);
                    g2d.setColor(Color.WHITE);
                    g2d.drawString(line,xWindow+18,yWindow+31);
                    yWindow+=40;
                }
                yWindow=130;
            }
            texto= Clase.PICARO.toString();
            g2d.setFont(g2d.getFont().deriveFont(Font.BOLD,40F));
            y= y+gp.getTamañofinalBaldosa();
            g2d.setColor(Color.BLACK);
            g2d.drawString(texto,x,y);
            g2d.setColor(Color.WHITE);
            g2d.drawString(texto,x+3,y+3);
            if(numeroMenu==3){
                g2d.drawString("______",x,y+15);
                dibujarVentanaGenerica(xWindow,yWindow,gp.getWidth()/2+gp.getTamañofinalBaldosa(),gp.getHeight()/2+(gp.getTamañofinalBaldosa()*2));
                texto="Eres un ladron y un bandido\nte adentras en las sombras y\nacechas con sigilo" +
                        " expertos en\nsituaciones peliagudas tus\nenemigos caeran sin haberte\n visto\n\nDestreza+10        Fuerza+5";
                for (String line: texto.split("\n")) {
                    g2d.setFont(g2d.getFont().deriveFont(Font.BOLD,23F));
                    g2d.setColor(Color.BLACK);
                    g2d.drawString(line,xWindow+15,yWindow+28);
                    g2d.setColor(Color.WHITE);
                    g2d.drawString(line,xWindow+18,yWindow+31);
                    yWindow+=40;
                }
                yWindow=130;
            }
            texto= "SALIR";
            g2d.setFont(g2d.getFont().deriveFont(Font.BOLD,40F));
            y= y+gp.getTamañofinalBaldosa();
            g2d.setColor(Color.BLACK);
            g2d.drawString(texto,x,y);
            g2d.setColor(Color.WHITE);
            g2d.drawString(texto,x+3,y+3);
            if(numeroMenu==4){
                g2d.drawString("______",x,y+15);
            }
        }else if(pantallaDelTitulo ==2){
            g2d.setColor(new Color(35,164,187));
            g2d.fillRect(0,0,gp.getWidth(),gp.getHeight());
            g2d.setColor(Color.BLACK);
            g2d.setFont(g2d.getFont().deriveFont(Font.BOLD,60F));
            String texto = "Seleeciona tu raza";
            int x=35;
            int y=gp.getTamañofinalBaldosa()*2;
            g2d.drawString(texto,x,y);

            texto= Raza.HUMANO.toString();
            g2d.setFont(g2d.getFont().deriveFont(Font.BOLD,40F));
            y= y+gp.getTamañofinalBaldosa()*3;
            g2d.setColor(Color.BLACK);
            g2d.drawString(texto,x,y);
            g2d.setColor(Color.WHITE);
            g2d.drawString(texto,x+3,y+3);
            if(numeroMenu==0){
                g2d.drawString("______",x,y+15);
                dibujarVentanaGenerica(xWindow,yWindow,gp.getWidth()/2+gp.getTamañofinalBaldosa(),gp.getHeight()/2+(gp.getTamañofinalBaldosa()*2));
                texto="Eres un humano la raza mas\ncomunen todo el mundo\ntu raza no destaca en nada\npero no se queda atras" +
                        "\nes por eso que prosperasteis\n\nFuerza=10        Magia=10\nVida=100        Velocidad=3\nDestreza=10";
                for (String line: texto.split("\n")) {
                    g2d.setFont(g2d.getFont().deriveFont(Font.BOLD,23F));
                    g2d.setColor(Color.BLACK);
                    g2d.drawString(line,xWindow+15,yWindow+28);
                    g2d.setColor(Color.WHITE);
                    g2d.drawString(line,xWindow+18,yWindow+31);
                    yWindow+=40;
                }
                yWindow=130;
            }
            texto= Raza.ORCO.toString();
            g2d.setFont(g2d.getFont().deriveFont(Font.BOLD,40F));
            y= y+gp.getTamañofinalBaldosa();
            g2d.setColor(Color.BLACK);
            g2d.drawString(texto,x,y);
            g2d.setColor(Color.WHITE);
            g2d.drawString(texto,x+3,y+3);
            if(numeroMenu==1){
                g2d.drawString("______",x,y+15);
                dibujarVentanaGenerica(xWindow,yWindow,gp.getWidth()/2+gp.getTamañofinalBaldosa(),gp.getHeight()/2+(gp.getTamañofinalBaldosa()*2));
                texto="Eres un temible orco que vive en\nlas llanuras, tu raza posee una\ngran fuerza y destreza por\n" +
                        "vuestra cultura guerrera\nos consideran unos barbaros \n\nFuerza=15        Magia=5\nVida=100        Velocidad=2\nDestreza=15";
                for (String line: texto.split("\n")) {
                    g2d.setFont(g2d.getFont().deriveFont(Font.BOLD,23F));
                    g2d.setColor(Color.BLACK);
                    g2d.drawString(line,xWindow+15,yWindow+28);
                    g2d.setColor(Color.WHITE);
                    g2d.drawString(line,xWindow+18,yWindow+31);
                    yWindow+=40;
                }
                yWindow=130;
            }
            texto= Raza.ELFO.toString();
            g2d.setFont(g2d.getFont().deriveFont(Font.BOLD,40F));
            y= y+gp.getTamañofinalBaldosa();
            g2d.setColor(Color.BLACK);
            g2d.drawString(texto,x,y);
            g2d.setColor(Color.WHITE);
            g2d.drawString(texto,x+3,y+3);
            if(numeroMenu==2){
                g2d.drawString("______",x,y+15);
                dibujarVentanaGenerica(xWindow,yWindow,gp.getWidth()/2+gp.getTamañofinalBaldosa(),gp.getHeight()/2+(gp.getTamañofinalBaldosa()*2));
                texto="Eres un elegante Elfo que vive en\nlos bosques, tu raza posee gran\nafinidad al mana y mucha destreza\n" +
                        "por ello os considerais superiores \n\nFuerza=5        Magia=15\nVida=70        Velocidad=5\nDestreza=20";
                for (String line: texto.split("\n")) {
                    g2d.setFont(g2d.getFont().deriveFont(Font.BOLD,23F));
                    g2d.setColor(Color.BLACK);
                    g2d.drawString(line,xWindow+15,yWindow+28);
                    g2d.setColor(Color.WHITE);
                    g2d.drawString(line,xWindow+18,yWindow+31);
                    yWindow+=40;
                }
                yWindow=130;
            }
            texto= Raza.ENANO.toString();
            g2d.setFont(g2d.getFont().deriveFont(Font.BOLD,40F));
            y= y+gp.getTamañofinalBaldosa();
            g2d.setColor(Color.BLACK);
            g2d.drawString(texto,x,y);
            g2d.setColor(Color.WHITE);
            g2d.drawString(texto,x+3,y+3);
            if(numeroMenu==3){

                g2d.drawString("______",x,y+15);
                dibujarVentanaGenerica(xWindow,yWindow,gp.getWidth()/2+gp.getTamañofinalBaldosa(),gp.getHeight()/2+(gp.getTamañofinalBaldosa()*2));
                texto="Eres un orgulloso Enano que\n vive en las minas, tu raza \n posee gran fuerza y resitencia \n " +
                        "a pesar de tu estatura\n\nFuerza=20        Magia=5\nVida=120        Velocidad=2\nDestreza=5";
                for (String line: texto.split("\n")) {
                    g2d.setFont(g2d.getFont().deriveFont(Font.BOLD,23F));
                    g2d.setColor(Color.BLACK);
                    g2d.drawString(line,xWindow+15,yWindow+28);
                    g2d.setColor(Color.WHITE);
                    g2d.drawString(line,xWindow+18,yWindow+31);
                    yWindow+=40;
                }
                yWindow=130;
            }
            texto= "SALIR";
            g2d.setFont(g2d.getFont().deriveFont(Font.BOLD,40F));
            y= y+gp.getTamañofinalBaldosa();
            g2d.setColor(Color.BLACK);
            g2d.drawString(texto,x,y);
            g2d.setColor(Color.WHITE);
            g2d.drawString(texto,x+3,y+3);
            if(numeroMenu==4){
                g2d.drawString("______",x,y+15);
            }
        }else if(pantallaDelTitulo ==3){
            g2d.setColor(new Color(35,164,187));
            g2d.fillRect(0,0,gp.getWidth(),gp.getHeight());
            g2d.setColor(Color.BLACK);
            g2d.setFont(g2d.getFont().deriveFont(Font.BOLD,40F));
            String texto = "Dinos tu nombre";
            int x=35;
            int y=gp.getTamañofinalBaldosa()*2;
            g2d.drawString(texto,x,y);
            texto=String.valueOf(gp.getTeclado().textoIngresado);
            y+=gp.getTamañofinalBaldosa()*2;
            g2d.setColor(Color.BLACK);
            g2d.drawString(texto,x,y);
            g2d.setColor(Color.WHITE);
            g2d.drawString(texto,x+3,y+3);
            texto="__________";
            y+=gp.getTamañofinalBaldosa();
        }else if(pantallaDelTitulo ==4) {
            g2d.setColor(new Color(35, 164, 187));
            g2d.fillRect(0, 0, gp.getWidth(), gp.getHeight());
            g2d.setColor(Color.BLACK);
            g2d.setFont(g2d.getFont().deriveFont(Font.BOLD, 60F));
            String texto = "Estas seguro?";
            int x = gp.getWidth()/5;
            int y = gp.getTamañofinalBaldosa() * 2;
            g2d.drawString(texto, x, y);
            texto ="SI";
            g2d.setFont(g2d.getFont().deriveFont(Font.BOLD, 50F));
            y = y + gp.getTamañofinalBaldosa() * 3;
            x=x-20;
            g2d.setColor(Color.BLACK);
            g2d.drawString(texto,x,y);
            g2d.setColor(Color.WHITE);
            g2d.drawString(texto,x+3,y+3);
            if (numeroMenu == 0) {
                g2d.drawString(">",x-25,y);
            }
            texto = "NO";
            g2d.setFont(g2d.getFont().deriveFont(Font.BOLD, 50F));
            x = x + gp.getTamañofinalBaldosa()*8;
            g2d.setColor(Color.BLACK);
            g2d.drawString(texto,x,y);
            g2d.setColor(Color.WHITE);
            g2d.drawString(texto,x+3,y+3);
            if (numeroMenu == 1) {
                g2d.drawString(">",x-25,y);
            }
        }
    }

    public void dibujarVentanaGenerica(int x,int y,int width,int height){
        Color c = new Color(0,0,0,150);
        g2d.setColor(c);
        g2d.fillRoundRect(x,y,width,height,35,35);
        c=new Color(255,255,255);
        g2d.setColor(c);
        g2d.setStroke(new BasicStroke(5));
        g2d.drawRoundRect(x+5,y+5,width-10,height-10,25,25);
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

    public int getNumeroMenu() {
        return numeroMenu;
    }

    public void setNumeroMenu(int numeroMenu) {
        this.numeroMenu = numeroMenu;
    }

    public int getPantallaDelTitulo() {
        return pantallaDelTitulo;
    }

    public void setPantallaDelTitulo(int pantallaDelTitulo) {
        this.pantallaDelTitulo = pantallaDelTitulo;
    }
}
