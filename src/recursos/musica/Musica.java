package recursos.musica;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.net.URL;

public class Musica {
    Clip clip;
    URL URLMusica[]=new URL[30];
    private boolean estado;

    /**
     * constructor de la musica, en la cual pasamos con getresource los archivos que usemos
     */
    public Musica() {
        URLMusica[0] = getClass().getResource("/recursos/musica/MusicaJuego.wav");
        URLMusica[1] = getClass().getResource("/recursos/musica/comarca.wav");
        URLMusica[2] = getClass().getResource("/recursos/musica/cofre.wav");
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

    public void empezar() {
        clip.start();
        estado=true;
    }

    public void bucle() {
        clip.loop(clip.LOOP_CONTINUOUSLY);
    }

    public void parar() {
        clip.stop();
        estado=false;
    }
    public boolean getEstado(){
        return estado;
    }
}
