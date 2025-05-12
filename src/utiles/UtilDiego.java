package utiles;


import entidades.Entidad;

import javax.naming.ldap.Control;
import java.time.LocalDate;
import java.util.Scanner;


public class UtilDiego {


    public static int leerNumEntA_B(Scanner teclado, String texto, int min, int max) {
        int num;
        ControlExcepciones e=new ControlExcepciones();
        do {
            num = e.enteroPosi();
            if (!(num >= min && num <= max)) {
                System.out.println("ERROR NUMERO NO VALIDO");
            }

        } while (!((num >= min) && (num <= max)));
        return num;
    }

    public static double leerNumDeciA_B(Scanner teclado, String texto, double min, double max) {
        double num;
        ControlExcepciones e=new ControlExcepciones();
        do {
            num = e.decimales();
            if (!(num >= min && num <= max)) {
                System.out.println("ERROR NUMERO NO VALIDO");
            }

        } while (!((num >= min) && (num <= max)));
        return num;
    }

    public static int numRandomentero(int min, int max) {
        int num;
        num = (int) (Math.random() * (max - min + 1)) + min;
        return num;
    }

    public static LocalDate leerFecha(Scanner teclado, String texto) {
        int dia, mes, año;
        ControlExcepciones e=new ControlExcepciones();
        System.out.println(texto);
        dia = leerNumEntA_B(teclado, "Escriba el dia del mes", 1, 31);
        mes = leerNumEntA_B(teclado, "Escriba el mes en numeros(1/12)", 1, 12);
        año = e.enteroPositivo();
        LocalDate fecha = LocalDate.of(año, mes, dia);
        return fecha;
    }

    public static String eliminarElementoString(String caracter,String texto){
        String textoFinal=texto.replace(caracter," ");
        return textoFinal;
    }
    public static String juntarFrase(String texto) {
        texto = texto.toLowerCase();
        texto = texto.trim();
        texto = texto.replace("á", "a").replace("é", "e")
                .replace("í", "i").replace("ó", "o")
                .replace("ú", "u").replace(" ", "");
        return texto;
    }
}
