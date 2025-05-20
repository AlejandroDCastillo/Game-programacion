package gamePanel;

public class Ventana extends javax.swing.JFrame {
    /**
     * constructor de la clase ventana, este es el metodo llamado en el main
     */
    public Ventana() {
        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);
        GamePanel ventanaDeJuego = new GamePanel();
        add(ventanaDeJuego);
        pack();
    }
}
