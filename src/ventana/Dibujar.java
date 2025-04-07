
package clases;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.*;

public class Dibujar extends JPanel implements ActionListener {
    Player player= new Player();
    Timer timer = new Timer(32,this);
    int height;
    int width;
    public Dibujar(int width, int height) {
        this.width = width;
        this.height=height;
        setBackground(Color.BLACK);
        addKeyListener(new teclado());
        this.timer.start();
        setFocusable(true);
        setBounds(0,0,500,500);
        setPreferredSize(new Dimension(500,500));
        setBorder(BorderFactory.createLineBorder(Color.BLUE));
        setLayout(new BorderLayout());

    }

    public void paint(Graphics g){
        super.paint(g);
        Graphics2D g2D = (Graphics2D)g;
        g2D.drawImage(player.tenerImagen(),player.tenerX(),player.tenerY(),null);
        System.out.println("X: "+player.tenerX()+" y: "+player.tenerY());
    }

    public void actionPerformed(ActionEvent e){
         player.getBounds();
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