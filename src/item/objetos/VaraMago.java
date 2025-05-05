package item.objetos;

import item.*;

// La clase VaraMago hereda de Item e implementa tanto Ataque como Defensa
public class VaraMago extends Item implements Ataque, Defensa
{
    // Daño que provoca la vara mágica
    protected double damage;

    // Velocidad con la que la vara lanza ataques
    protected double velocidad_ataque;

    // Defensa que proporciona la vara mágica (como escudo mágico)
    protected int defensa;

    // Hechizo asociado a la vara (puede ser una clase Hechizo con efectos mágicos)
    protected Hechizo hechizo;

    /**
     * Constructor de la clase VaraMago.
     * Inicializa todos los atributos de una vara mágica.
     *
     * @param idNombre nombre o identificador de la vara
     * @param cantidad cantidad (por ejemplo, en el inventario)
     * @param tipo tipo del objeto (puede ser un enum o clase Tipo)
     * @param damage daño mágico de la vara
     * @param velocidad_ataque velocidad con la que ataca
     * @param defensa valor defensivo de la vara
     * @param hechizo hechizo que contiene o lanza la vara
     * @param spriteX posición X del sprite gráfico
     * @param spriteY posición Y del sprite gráfico
     */
    public VaraMago(String idNombre, int cantidad, Tipo tipo, double damage, double velocidad_ataque, int defensa, Hechizo hechizo, int spriteX, int spriteY)
    {
        // Llama al constructor de la clase base Item
        super(idNombre, cantidad, tipo,spriteX,spriteY);

        // Asignación de atributos específicos de la vara mágica
        this.damage = damage;
        this.velocidad_ataque = velocidad_ataque;
        this.defensa = defensa;
        this.hechizo = hechizo;
    }

    /**
     * Implementación del método getDamage de la interfaz Ataque.
     * Devuelve el daño de la vara mágica.
     *
     * @return daño
     */
    @Override
    public double getDamage() {
        return damage;
    }

    /**
     * Permite cambiar el valor del daño mágico de la vara.
     *
     * @param damage nuevo daño
     */
    public void setDamage(double damage) {
        this.damage = damage;
    }

    /**
     * Implementación del metodo getVelocidad_ataque de la interfaz Ataque.
     * Devuelve la velocidad de ataque de la vara.
     *
     * @return velocidad de ataque
     */
    @Override
    public double getVelocidad_ataque() {
        return velocidad_ataque;
    }

    /**
     * Permite cambiar la velocidad de ataque.
     *
     * @param velocidad_ataque nuevo valor
     */
    public void setVelocidad_ataque(double velocidad_ataque) {
        this.velocidad_ataque = velocidad_ataque;
    }

    /**
     * Implementación del metodo getDefensa de la interfaz Defensa.
     * Devuelve la defensa que proporciona la vara.
     *
     * @return defensa
     */
    @Override
    public int getDefensa() {
        return defensa;
    }

    /**
     * Permite cambiar el valor de defensa de la vara.
     *
     * @param defensa nuevo valor
     */
    public void setDefensa(int defensa) {
        this.defensa = defensa;
    }

    /**
     * Devuelve el hechizo que contiene la vara mágica.
     *
     * @return hechizo
     */
    public Hechizo getHechizo() {
        return hechizo;
    }

    /**
     * Permite cambiar el hechizo de la vara mágica.
     *
     * @param hechizo nuevo hechizo
     */
    public void setHechizo(Hechizo hechizo) {
        this.hechizo = hechizo;
    }
}
