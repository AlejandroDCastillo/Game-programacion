package item;

// La clase EspadaFuego hereda de la clase Espada y añade un nuevo atributo: quemadura
public class EspadaFuego extends Espada
{
    // Atributo que representa el daño adicional por quemadura que provoca esta espada
    protected double quemadura;

    /**
     * Constructor de la clase EspadaFuego.
     * Inicializa todos los atributos de una espada de fuego, incluyendo la quemadura.
     *
     * @param idNombre nombre o identificador del objeto
     * @param cantidad cantidad de espadas (por ejemplo, en el inventario)
     * @param tipo tipo del objeto (puede ser un enum o clase Tipo)
     * @param damage daño base de la espada
     * @param velocidad_ataque velocidad con la que ataca la espada
     * @param quemadura daño extra por fuego que se aplica con el ataque
     * @param spriteX posición X del sprite gráfico
     * @param spriteY posición Y del sprite gráfico
     */
    public EspadaFuego(String idNombre, int cantidad, Tipo tipo, double damage, double velocidad_ataque, double quemadura,int spriteX,int spriteY)
    {
        // Llama al constructor de la clase padre Espada
        super(idNombre, cantidad, tipo, damage, velocidad_ataque,spriteX,spriteY);
        this.quemadura = quemadura; // Asigna el valor de quemadura
    }

    /**
     * Devuelve el valor de quemadura que tiene la espada.
     *
     * @return daño por quemadura
     */
    public double getQuemadura()
    {
        return quemadura;
    }

    /**
     * Permite cambiar o actualizar el valor de quemadura de la espada.
     *
     * @param quemadura nuevo daño por quemadura
     */

    public void setQuemadura(double quemadura) {
        this.quemadura = quemadura;
    }
}
