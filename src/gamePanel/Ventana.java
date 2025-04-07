package gamePanel;

public class Ventana extends javax.swing.JFrame {
    public Ventana() {
        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);
        GamePanel ventanaDeJuego = new GamePanel();
        add(ventanaDeJuego);
        pack();
    }
}
