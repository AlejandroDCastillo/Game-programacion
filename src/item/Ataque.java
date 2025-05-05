package item;

public interface Ataque
{
    /**
     * Metodo que devuelve el daño que produce un ataque.
     * @return valor numérico del daño
     */

    double getDamage();

    /**
     * Metodo que devuelve la velocidad de ataque del arma u objeto.
     * @return valor numérico que representa cuán rápido ataca
     */
    double getVelocidad_ataque(); //Para que el arma tenga el metodo velocidad de ataque

}
