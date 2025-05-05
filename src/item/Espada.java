package item;

import java.io.Serializable;

// La clase Espada hereda de Item e implementa la interfaz Ataque
public class Espada extends Item implements Ataque
{
    // Daño que causa la espada al atacar
    protected double damage;

    // Velocidad con la que ataca la espada (ataques por segundo, por ejemplo)
    protected double velocidad_ataque;


    /**
     * Constructor de la clase Espada.
     * Inicializa todos los atributos necesarios para crear una espada.
     *
     * @param idNombre nombre o identificador del objeto espada
     * @param cantidad cantidad de espadas (por ejemplo, en el inventario)
     * @param tipo tipo del objeto (puede ser un enum o clase Tipo)
     * @param damage daño que hace la espada
     * @param velocidad_ataque velocidad con la que ataca la espada
     * @param spriteX posición X del sprite gráfico
     * @param spriteY posición Y del sprite gráfico
     */

    public Espada(String idNombre, int cantidad, Tipo tipo, double damage, double velocidad_ataque,int spriteX,int spriteY)
    {
        // Llama al constructor de la clase padre Item
        super(idNombre, cantidad, tipo,spriteX,spriteY);
        this.damage = damage; // Asigna el daño
        this.velocidad_ataque = velocidad_ataque; // Asigna la velocidad de ataque
    }

    /**
     * Implementación del metodo getDamage de la interfaz Ataque.
     * Devuelve el daño que causa la espada.
     *
     * @return daño de la espada
     */

    @Override
    public double getDamage() {
        return damage;
    }

    /**
     * Permite cambiar el daño de la espada.
     *
     * @param damage nuevo valor de daño
     */

    public void setDamage(double damage) {
        this.damage = damage;
    }

    /**
     * Devuelve la velocidad de ataque de la espada.
     *
     * @return velocidad de ataque
     */

    public double getVelocidad_ataque() {
        return velocidad_ataque;
    }

    /**
     * Permite cambiar la velocidad de ataque de la espada.
     *
     * @param velocidad_ataque nuevo valor de velocidad
     */

    public void setVelocidad_ataque(double velocidad_ataque) {
        this.velocidad_ataque = velocidad_ataque;
    }
}
