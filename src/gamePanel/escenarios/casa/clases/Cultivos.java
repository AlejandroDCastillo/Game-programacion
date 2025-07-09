package gamePanel.escenarios.casa.clases;

import item.Item;

public interface Cultivos {
    abstract boolean plantarse();
    abstract boolean recogerse();
    abstract void crecer();
    abstract Item darItem();
}
