package gamePanel.escenarios.casa;

import item.Item;

public interface Cultivos {
    abstract void plantarse();
    abstract boolean recogerse();
    abstract int crecer();
    abstract Item darItem();
}
