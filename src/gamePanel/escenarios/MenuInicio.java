package gamePanel.escenarios;

import gamePanel.Ventana;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MenuInicio extends JFrame{

    /**
     * contructor con los listener de botones y los tres botones
     */
    public MenuInicio() {
        setTitle("Survival Dangeons");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(500, 500);
        setResizable(false);
        setLocationRelativeTo(null); // Centrar en pantalla
        JPanel panel = new JPanel();
        // Tres botones verticales
        panel.setLayout(new GridLayout(3, 1, 10, 10));
        JButton btnNuevaPartida = new JButton("Nueva Aventura");
        JButton btnContinuar = new JButton("Continuar");
        JButton btnSalir = new JButton("Salir");
        // Acción para Nueva Partida
        btnNuevaPartida.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose(); // Cierra el menú
                iniciarNuevaPartida();
            }
        });
        // Acción para Continuar
        btnContinuar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose();
                cargarPartida();
            }
        });

        // Acción para Salir
        btnSalir.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        panel.add(btnNuevaPartida);
        panel.add(btnContinuar);
        panel.add(btnSalir);
        add(panel);
        setVisible(true);
    }

    /**
     * metodo para el boton iniciar nueva partida, aqui tendremos nuestro generador de jugador
     */
    private void iniciarNuevaPartida() {
        Ventana v=new Ventana();
        v.setTitle("Survival Dungeons");
        v.setVisible(true);
        System.out.println("Nueva partida iniciada");
    }

    /**
     * metodo para cargar una partida ya guardada
     */
    private void cargarPartida() {
        System.out.println("Partida cargada");

    }
}
