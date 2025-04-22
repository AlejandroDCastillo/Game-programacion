package item;


public class Escudo extends Item implements Defensa
{
    protected int defensa;

    public Escudo(String idNombre, int cantidad, Tipo tipo, int defensa,int spriteX,int spriteY)
    {
        super(idNombre, cantidad, tipo,spriteX,spriteY);
        this.defensa = defensa;
    }

    @Override
    public int getDefensa() {
        return defensa;
    }

    public void setDefensa(int defensa) {
        this.defensa = defensa;
    }
}
