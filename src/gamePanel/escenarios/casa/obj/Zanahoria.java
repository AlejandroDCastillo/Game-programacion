package gamePanel.escenarios.casa.obj;

import item.Inventario;
import item.Item;
import gamePanel.escenarios.casa.Cultivos;
import gamePanel.escenarios.casa.clases.Cultivo;

public class Zanahoria extends Cultivo implements Cultivos {


    public Zanahoria(int x, int y, int estados) {
        //iniciamos el loot
        Item loot = Inventario.getInstance().buscarObjeto("Zanahoria");
        super(x, y, estados, "Zanahoria", loot);
        //iniciamos el tiempo que tarda la zanahoria en crecer
        this.tiempo = 200;


    }

    @Override
    public void plantarse() {

    }

    @Override
    public boolean recogerse() {
        return false;
    }

    @Override
    public int crecer() {
        return 0;
    }

    @Override
    public Item darItem() {
        return null;
    }
}
