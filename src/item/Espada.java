package item;

import java.io.Serializable;

public class Espada extends Item implements Ataque
{
    protected double damage;
    protected double velocidad_ataque;

    public Espada(String idNombre, int cantidad, Tipo tipo, double damage, double velocidad_ataque,int spriteX,int spriteY)
    {
        super(idNombre, cantidad, tipo,spriteX,spriteY);
        this.damage = damage;
        this.velocidad_ataque = velocidad_ataque;
    }

    @Override
    public double getDamage() {
        return damage;
    }

    public void setDamage(double damage) {
        this.damage = damage;
    }

    public double getVelocidad_ataque() {
        return velocidad_ataque;
    }

    public void setVelocidad_ataque(double velocidad_ataque) {
        this.velocidad_ataque = velocidad_ataque;
    }
}
