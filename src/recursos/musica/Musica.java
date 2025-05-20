package recursos.musica;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.net.URL;

public class Musica {
    public Clip clip;
    public Clip clipEfecto;
    URL URLMusica[]=new URL[30];
    private boolean estado;

    /**
     * constructor de la musica, en la cual pasamos con getresource los archivos que usemos
     */
    public Musica() {
        System.out.println("SE INICIA MUSICA");
        URLMusica[0] = getClass().getResource("/recursos/musica/MusicaJuego.wav");
        URLMusica[1] = getClass().getResource("/recursos/musica/comarca.wav");
        URLMusica[2] = getClass().getResource("/recursos/musica/cofre.wav");
        URLMusica[3] = getClass().getResource("/recursos/musica/batalla.wav");
        URLMusica[4] = getClass().getResource("/recursos/musica/combate.wav");
        URLMusica[5] = getClass().getResource("/recursos/musica/daño.wav");
        URLMusica[6] = getClass().getResource("/recursos/musica/vida.wav");
    }

    /**
     * sirve para selecionar el archivo dentro de la coleccion URL
     * @param i
     */
    public void setArchivo(int i) {
        try {
            AudioInputStream ais = AudioSystem.getAudioInputStream(URLMusica[i]);
            this.clip = AudioSystem.getClip();
            this.clip.open(ais);
        } catch (Exception e) {
            System.out.println("ERROR");
        }
    }

    /**
     * metodo que empieza el clip cargado previamente
     */
    public void empezar() {
        clip.start();
        estado=true;
    }

    /**
     * metodo que reproduce un clip en bucle
     */
    public void bucle() {
        clip.loop(clip.LOOP_CONTINUOUSLY);
    }

    /**
     * metodo que para el clip activo
     */
    public void parar() {
        detener();
        estado=false;
    }

    /**
     * fuerza el cierre del clip para evitar errores en la musica
     */
    public void detener() {
        if (clip != null) {
            if (clip.isRunning()) {
                clip.stop();
                System.out.println("Clip detenido");
            }
            clip.close();
            System.out.println("Clip cerrado");
            clip = null;
        }
    }

    /**
     * usa clip efecto para reproducir un sonido corto
     * @param i
     */
    public void reproducirEfecto(int i) {
        try {
            AudioInputStream ais = AudioSystem.getAudioInputStream(URLMusica[i]);
            clipEfecto = AudioSystem.getClip();
            clipEfecto.open(ais);
            clipEfecto.start();
        } catch (Exception e) {
            System.out.println("ERROR al cargar efecto: " + e.getMessage());
        }
    }
//getter y setters

    public boolean getEstado(){
        return estado;
    }
}
