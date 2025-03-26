package entidad;

import item.Item;
import item.Tipo;

import java.util.ArrayList;

public class Jugador extends Entidad {
    private int hambre;
    private int sed;
    private ArrayList<Item> inventario;

    public Jugador(int vida, int defensa, Raza raza, Clase calse, int hambre, int sed) {
        super(vida, defensa, raza, calse);
        this.hambre = hambre;
        this.sed = sed;
        this.inventario = iniciarInventario();
    }

    public ArrayList<Item> iniciarInventario(){
        ArrayList<Item> inventario=new ArrayList<>();
        Item agua=new Item("agua",3, Tipo.AGUA);
        Item comida=new Item("comida",3,Tipo.COMIDA);
        Item madera=new Item("madera",0,Tipo.MADERA);

        return inventario;
    }
}
