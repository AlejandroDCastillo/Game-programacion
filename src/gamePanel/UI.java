package gamePanel;

import item.objetos.Llave;
import item.objetos.Objetos;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.nio.BufferOverflowException;

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
    public UI(GamePanel gp) {
        Objetos llave = new Llave();
        imagen_llave = llave.getImagen();
        this.gp=gp;
    }

    public void enseñarMensaje(String texto) {
        mensaje=texto;
        hayMensaje=true;
    }

    public void dibujar(Graphics2D g2d) {
        this.g2d=g2d;
        //menu inicio
        if (gp.estadoJuego==gp.menuInicio){
            dibujarMenuInicio();
        }else {
            //llave
            g2d.setFont(fonte);
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
                Font fuentePausa = new Font("Arial", Font.ITALIC, 50);
                g2d.setColor(Color.WHITE);
                g2d.setFont(fuentePausa);
                g2d.drawString("JUEGO EN PAUSA", gp.getTamañoAnchuraPantalla() / 4, gp.getTamañoAlturaPantalla() / 2);

            }
        }


    }

    /**
     * metodo que dibuja el menu de iniciao
     */
    public void dibujarMenuInicio(){
        //fondo
        g2d.setColor(new Color(0,0,0));
        g2d.fillRect(0,0,gp.getWidth(),gp.getHeight());
        //titulo
        g2d.setFont(g2d.getFont().deriveFont(Font.BOLD,90F));
        String texto = "Survival Dangeon";
        int x=5;
        int y=gp.getHeight()/4;
        g2d.setColor(Color.WHITE);
        g2d.drawString(texto,x,y);
        //sombreado
        g2d.setColor(Color.GRAY);
        g2d.drawString(texto,x+3,y+3);
        //imagen del personaje
        try {
            //cargamos la imagen del personaje
            BufferedImage img=gp.getJugador().obtenerImagenPlayer(0, 0);
            //la escalamos usando el metodo por defecto
            Image imgEscalada = img.getScaledInstance(100, 100, Image.SCALE_SMOOTH);
//            dibujamos en pantalla
            g2d.drawImage(imgEscalada, 350, 400, null);
        }catch (Exception e){
            System.out.println("ERROR");
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
}
