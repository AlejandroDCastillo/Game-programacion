/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package ventana;


import ventana.Dibujar;

import javax.swing.*;
import java.awt.*;

public class Ventana extends javax.swing.JFrame {


    public Ventana() {
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        int width = 1920;
        int height = 700;
        Dibujar dibus = new Dibujar(1920,700);
        add(dibus);
        setSize(1920,1080);
    }

    public static void main(String args[]) {

                new Ventana().setVisible(true);

    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}