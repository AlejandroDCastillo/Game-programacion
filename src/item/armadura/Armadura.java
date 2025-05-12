package item.armadura;

import entidades.Entidad;
import item.Item;

public class Armadura extends Item implements Aumento {
    private int proteccionBase;
    public Armadura(String idNombre, int cantidad, int spriteX, int spriteY,int proteccionBase) {
        super(idNombre, cantidad, spriteX, spriteY);
        this.proteccionBase = proteccionBase;
    }

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

    public void proteccion(Entidad entidad) {
        entidad.setDefensa(this.proteccionBase+entidad.getDefensa());
    }
}
