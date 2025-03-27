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

    public Item() {
    }

    @Override
    public String toString() {
        return "Item{" +
                "idNombre='" + idNombre + '\'' +
                ", cantidad=" + cantidad +
                ", tipo=" + tipo +
                '}';
    }

    public Tipo getTipo() {
        return tipo;
    }

    public void setTipo(Tipo tipo) {
        this.tipo = tipo;
    }

    public String getIdNombre() {
        return idNombre;
    }

    public void setIdNombre(String idNombre) {
        this.idNombre = idNombre;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }
}
