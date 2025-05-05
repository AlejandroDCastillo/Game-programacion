package item.objetos;


import item.Defensa;
import item.Item;
import item.Tipo;

public class Escudo extends Item implements Defensa
{
    // Atributo que representa cuánta defensa proporciona este escudo
    protected int defensa;

    /**
     * Constructor del escudo. Inicializa todos los atributos del objeto escudo.
     *
     * @param idNombre nombre o identificador del escudo
     * @param cantidad número de escudos (por ejemplo, en un inventario)
     * @param tipo tipo del objeto (puede ser un enum o clase Tipo)
     * @param defensa cantidad de defensa que otorga el escudo
     * @param spriteX posición X del sprite (gráfico) del escudo
     * @param spriteY posición Y del sprite (gráfico) del escudo
     */

    public Escudo(String idNombre, int cantidad, Tipo tipo, int defensa, int spriteX, int spriteY)
    {
        // Llamada al constructor de la clase padre (Item)
        super(idNombre, cantidad, tipo,spriteX,spriteY);
        this.defensa = defensa;
    }

    /**
     * Implementación del metodo de la interfaz Defensa.
     * Devuelve la cantidad de defensa del escudo.
     *
     * @return defensa del escudo
     */

    @Override
    public int getDefensa()
    {
        return defensa;
    }

    /**
     * Metodo para cambiar o actualizar la defensa del escudo.
     *
     * @param defensa nuevo valor de defensa
     */

    public void setDefensa(int defensa) {
        this.defensa = defensa;
    }
}
