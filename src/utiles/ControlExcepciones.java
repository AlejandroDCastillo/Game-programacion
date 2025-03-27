package utiles;

import java.util.Scanner;

public class ControlExcepciones {
    private Scanner teclado;

    /**
     * Constructor inicializamos el teclado
     */
    public ControlExcepciones() {
        teclado = new Scanner(System.in);
    }

    /**
     * verifica enteros
     *
     * @return
     */
    public int enteros() {
        boolean verificarEntero = false;
        int numero = 0;
        do {
            try {
                numero = teclado.nextInt();
                return numero;
            } catch (Exception e) {
                System.out.println("Solo se admiten numeros enteros");
                teclado.nextLine();//limpiamos el buffer
            }
        } while (!verificarEntero);
        return numero;
    }

    /**
     * verifica enteros positivos
     * @return
     */
    public int enteroPosi() {
        boolean verificarNumero=false;
        int numero=0;
        do {
            numero = enteros();
            if (numero >= 0) {
                return numero;
            } else {
                System.out.println("el número debe ser positivo");
            }
        } while (!verificarNumero);
        return numero;
    }

    /**
     * verifica entero negativo
     * @return
     */
    public int enteroPositivo(){
        boolean verificarNumero=false;
        int numero=0;
        do {
            numero = enteros();
            if (numero < 0) {
                return numero;
            } else {
                System.out.println("el número debe ser negativo");
            }
        } while (!verificarNumero);
        return numero;
    }

    /**
     * verifica decimales
     * @return
     */
    public double decimales(){
        boolean verificarDecimal = false;
        double numero = 0;
        do {
            try {
                numero = teclado.nextDouble();
                return numero;
            } catch (Exception e) {
                System.out.println("Solo se admiten numeros enteros");
                teclado.nextLine();//limpiamos el buffer
            }
        } while (!verificarDecimal);
        return numero;
    }

    /**
     * verifica decimal positivo
     * @return
     */
    public double decimalPosi(){
        boolean verificarNumero=false;
        double numero=0;
        do {
            numero = decimales();
            if (numero >= 0) {
                return numero;
            } else {
                System.out.println("el número debe ser positivo");
            }
        } while (!verificarNumero);
        return numero;
    }

    /**
     * verifica decimal negativo
     * @return
     */
    public double decimalNegativo(){
        boolean verificarNumero=false;
        double numero=0;
        do {
            numero = decimales();
            if (numero >= 0) {
                return numero;
            } else {
                System.out.println("el número debe ser negativo");
            }
        } while (!verificarNumero);
        return numero;
    }

    /**
     * verifica texto
     * @return
     */
    public String texto(){
        boolean verificarTexto=false;
        String texto="";
        do{
            try{
                texto=teclado.nextLine();
            } catch (Exception e) {
                System.out.println("Solo de admite texto");
                teclado.nextLine();//limpiamos buffer
            }
        }while(!verificarTexto);
        return texto;
    }


}
