/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ventana;
import java.awt.Image;
import java.awt.event.KeyEvent;
import javax.swing.ImageIcon;
public class Player {
        private String ruta;
        private int dx;
        private int dy;
        private int y;
        private int x;
        private Image imagen;
        
        public Player(){
            x=40;
            y=60;
            ruta = "3faaf0d97c1093ff47ad544149fc91bd.jpg";
            ImageIcon img = new ImageIcon(this.getClass().getResource(ruta));
            imagen =img.getImage();
            imagen = imagen.getScaledInstance(200, 200, 0);
        }
   //Metodos para detectar teclas
        
        public void keyPressed(KeyEvent e){
            int tecla = e.getKeyCode();
            if (tecla == KeyEvent.VK_A){
                dx = -1;
            }if (tecla == KeyEvent.VK_D){
                dx = 1;
            }
            if (tecla == KeyEvent.VK_W){
                dy = -1;
            }if (tecla == KeyEvent.VK_S){
                dy = 1;
            }
        }
          //Metodos para detectar que se dejaron de pulsar lsa  teclas
        public void keyReleased(KeyEvent e){
            int tecla = e.getKeyCode();
            if (tecla == KeyEvent.VK_A){
                dx = 0;
            }if (tecla == KeyEvent.VK_D){
                dx = 0;
            }
            if (tecla == KeyEvent.VK_W){
                dy = 0;
            }if (tecla == KeyEvent.VK_S){
                dy = 0;
            }
        }
    //METODO PARA HACER QUE SE MUEVA
        
        public void mover(){
            x+=dx;
            y+=dy;
        }
        public int tenerX(){
            return x;
        }
        public int tenerY(){
            return y;
        }
        public Image tenerImagen(){
            return imagen;
        }

}
    

