package item;

public class VaraMago extends Item implements Ataque, Defensa
{
    protected double damage;
    protected double velocidad_ataque;
    protected int defensa;

    protected Hechizo hechizo;

    public VaraMago(String idNombre, int cantidad, Tipo tipo, double damage, double velocidad_ataque, int defensa, Hechizo hechizo)
    {
        super(idNombre, cantidad, tipo);
        this.damage = damage;
        this.velocidad_ataque = velocidad_ataque;
        this.defensa = defensa;
        this.hechizo = hechizo;
    }

    @Override
    public double getDamage() {
        return damage;
    }

    public void setDamage(double damage) {
        this.damage = damage;
    }

    @Override
    public double getVelocidad_ataque() {
        return velocidad_ataque;
    }

    public void setVelocidad_ataque(double velocidad_ataque) {
        this.velocidad_ataque = velocidad_ataque;
    }

    @Override
    public int getDefensa() {
        return defensa;
    }

    public void setDefensa(int defensa) {
        this.defensa = defensa;
    }

    public Hechizo getHechizo() {
        return hechizo;
    }

    public void setHechizo(Hechizo hechizo) {
        this.hechizo = hechizo;
    }
}
