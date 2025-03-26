
package clases;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JPanel;
import javax.swing.Timer;

public class Dibujar extends JPanel implements ActionListener {
    Player player= new Player();
    Timer timer = new Timer(5,this);
    public Dibujar(){
        setBackground(Color.BLACK);
        addKeyListener(new teclado());
        this.timer.start();
        setFocusable(true);
    }
    public void paint(Graphics g){
        super.paint(g);
        Graphics2D g2D = (Graphics2D)g;
       g2D.drawImage(player.tenerImagen(),player.tenerX(),player.tenerY(),null);
    }
    public void actionPerformed(ActionEvent e){
        player.mover();
        repaint();
    }
    private class teclado extends KeyAdapter
    {
        public void keyPressed(KeyEvent e){
            player.keyPressed(e);
        }
         public void keyReleased(KeyEvent e){
            player.keyReleased(e);
        }
    }
}
