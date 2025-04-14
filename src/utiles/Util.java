

public class Util {

    /**
     * metodo para limpiar la consola
     */
    public static void limpiarPantalla() {
        for (int i = 0; i < 50; i++) {
            System.out.println();
        }
    }

    /**
     * metodo que espera los milisegundos que le indique
     * este metodo utiliza thread.sleep
     *
     * @param milisegundos
     */
    public static void esperar(int milisegundos) {
        try {
            Thread.sleep(milisegundos); //detiene o pausa el hilo de flujo durante un tiempo
        } catch (InterruptedException e) {
            //se realiza para evitar la excepcion InterruptedException. se da cuenado otro hilo actua por este
            //con esta linea logramos evitar que continue el codigo.
            Thread.currentThread().interrupt();
        }
    }



}
