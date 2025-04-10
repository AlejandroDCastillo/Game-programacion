package item;

public class Espada extends Item
{
    protected double damage;
    protected double velocidad_ataque;

    public Espada(String idNombre, int cantidad, Tipo tipo, double damage, double velocidad_ataque)
    {
        super(idNombre, cantidad, tipo);
        this.damage = damage;
        this.velocidad_ataque = velocidad_ataque;
    }



}
