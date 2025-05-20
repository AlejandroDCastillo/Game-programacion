package item.armas;

import entidades.Entidad;
import item.Item;
import item.armadura.Aumento;

public class Arma extends Item implements Aumento {
    protected int dañoBase;
    protected Elemento elemento;
    protected TipoAtaque tipoataque;
    protected int tipoAumento;
    protected int coste;

    /**
     * constructor de arma
     *
     * @param idNombre
     * @param cantidad
     * @param spriteX
     * @param spriteY
     * @param dañoBase
     * @param elemento
     * @param tipoataque
     * @param coste
     * @param tipoAumento
     */
    public Arma(String idNombre, int cantidad, int spriteX, int spriteY, int dañoBase, Elemento elemento, TipoAtaque tipoataque, int coste, int tipoAumento) {
        super(idNombre, cantidad, spriteX, spriteY);
        this.dañoBase = dañoBase;
        this.elemento = elemento;
        this.tipoataque = tipoataque;
        this.coste = coste;
        this.tipoAumento = tipoAumento;
    }

    /**
     * metodo heredado de la interfaz aumento, sirve para actualizar stats de entidad
     *
     * @param entidad
     * @param numero
     */
    @Override
    public void aumentar(Entidad entidad, int numero) {
        switch (tipoAumento) {
            case 1:
                entidad.setFuerza(numero + entidad.getFuerza());
                break;
            case 2:
                entidad.setDestreza(numero + entidad.getDestreza());
                break;
            case 3:
                entidad.setDefensa(numero + entidad.getDefensa());
                break;
            case 4:
                entidad.setMagia(numero + entidad.getMagia());
                break;
            case 5:
                entidad.setMana(numero + entidad.getMana());
                break;
            case 6:
                if (entidad.getVida() <= 10) {
                    entidad.setFuerza(numero + entidad.getFuerza());
                    entidad.setDestreza(numero + entidad.getDestreza());
                    entidad.setMagia(numero + entidad.getMagia());
                    entidad.setMana(numero + entidad.getMana());
                }
                break;
        }
    }

//getters y setters

    public int getCoste() {
        return coste;
    }

    public void setCoste(int coste) {
        this.coste = coste;
    }

    public int getDañoBase() {
        return dañoBase;
    }

    public void setDañoBase(int dañoBase) {
        this.dañoBase = dañoBase;
    }

    public TipoAtaque getTipoataque() {
        return tipoataque;
    }

    public void setTipoataque(TipoAtaque tipoataque) {
        this.tipoataque = tipoataque;
    }

    public Elemento getElemento() {
        return elemento;
    }

    public void setElemento(Elemento elemento) {
        this.elemento = elemento;
    }

}
