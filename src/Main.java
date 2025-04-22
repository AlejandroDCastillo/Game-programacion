import gamePanel.escenarios.MenuInicio;

import javax.swing.*;


public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new MenuInicio());
//        Ventana v=new Ventana();
//        v.setTitle("Survival Dungeons");
//        v.setVisible(true);
    }
}