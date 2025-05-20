package item.armadura;

import entidades.Entidad;
import item.Item;

public class Armadura extends Item implements Aumento {
    private int proteccionBase;

    /**
     * constructor de armadura
     *
     * @param idNombre
     * @param cantidad
     * @param spriteX
     * @param spriteY
     * @param proteccionBase
     */
    public Armadura(String idNombre, int cantidad, int spriteX, int spriteY, int proteccionBase) {
        super(idNombre, cantidad, spriteX, spriteY);
        this.proteccionBase = proteccionBase;
    }

    /**
     * metodo que modifica stats del jugador en funcion del objeto
     *
     * @param entidad
     * @param numero
     */
    @Override
    public void aumentar(Entidad entidad, int numero) {
        switch (numero) {
            case 1:
                entidad.setDefensa(entidad.getDefensa() + this.proteccionBase);
                break;
            case 2:
                entidad.setDefensa(entidad.getDefensa() + this.proteccionBase);
                entidad.setFuerza(entidad.getFuerza() + this.proteccionBase);
                entidad.setVelocidad(entidad.getVelocidad() - 2);
                break;
            case 3:
                entidad.setDefensa(entidad.getDefensa() + this.proteccionBase);
                entidad.setVidaMax(entidad.getVidaMax() + this.proteccionBase);
                entidad.setVelocidad(entidad.getVelocidad() - 2);
                break;
        }
    }

    /**
     * metodo que actualiza la defensa de la entidad que tiene un obj equipado
     * @param entidad
     */
    public void proteccion(Entidad entidad) {
        entidad.setDefensa(this.proteccionBase + entidad.getDefensa());
    }
}
