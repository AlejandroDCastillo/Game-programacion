package item;

public class Item {
    protected String idNombre;
    protected int cantidad;
    protected Tipo tipo;

    public Item(String idNombre, int cantidad,Tipo tipo) {
        this.idNombre = idNombre;
        this.cantidad = cantidad;
        this.tipo=tipo;
    }
}
