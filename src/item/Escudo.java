package item;


public class Escudo extends Item implements Defensa
{
    protected int defensa;

    public Escudo(String idNombre, int cantidad, Tipo tipo, int defensa,String ruta)
    {
        super(idNombre, cantidad, tipo,ruta);
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
