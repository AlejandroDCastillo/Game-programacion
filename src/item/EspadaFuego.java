package item;

public class EspadaFuego extends Espada
{
    protected double quemadura;

    public EspadaFuego(String idNombre, int cantidad, Tipo tipo, double damage, double velocidad_ataque, double quemadura,String ruta) {
        super(idNombre, cantidad, tipo, damage, velocidad_ataque,ruta);
        this.quemadura = quemadura;
    }

    public double getQuemadura() {
        return quemadura;
    }

    public void setQuemadura(double quemadura) {
        this.quemadura = quemadura;
    }
}
