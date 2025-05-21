package gestores;

import entidades.Clase;
import entidades.Monstruos.Mon_Esqueleto;
import entidades.Monstruos.Mon_Goblin;
import entidades.Raza;
import gamePanel.GamePanel;
import item.armas.Arma;
import item.armas.TipoAtaque;
import item.objetos.Cofre;
import item.objetos.Llave;
import item.objetos.Puerta;

public class GestorAssets {
    GamePanel gp;

    /**
     * constructor
     *
     * @param gp
     */
    public GestorAssets(GamePanel gp) {
        this.gp = gp;
    }

    /**
     *metodo que inicializa el arrayobjetos
     */
    public void establecerObjetos() {
        gp.arrayobjetos[0] = new Llave(gp);
        gp.arrayobjetos[0].setX(6 * gp.getTamañofinalBaldosa());
        gp.arrayobjetos[0].setY(5 * gp.getTamañofinalBaldosa());

        gp.arrayobjetos[2] = new Puerta(gp);
        gp.arrayobjetos[2].setX(8 * gp.getTamañofinalBaldosa());
        gp.arrayobjetos[2].setY(6 * gp.getTamañofinalBaldosa());

        gp.arrayobjetos[1] = new Cofre(gp);
        gp.arrayobjetos[1].setX(8 * gp.getTamañofinalBaldosa());
        gp.arrayobjetos[1].setY(7 * gp.getTamañofinalBaldosa());
    }

    /**
     * inicializa el array de enemigos
     */
    public void establecerEnemigos() {
        gp.arrayEnemigos[0] = new Mon_Esqueleto(gp, "MonstruoPT", Raza.HUMANO, Clase.MAGO, 1);
        gp.arrayEnemigos[0].setArma(new Arma("vara_fuego", 1, 11, 1, 2, null, TipoAtaque.ArmaMágica, 20, 2));
        gp.arrayEnemigos[0].setX(5 * gp.getTamañofinalBaldosa());
        gp.arrayEnemigos[0].setY(3 * gp.getTamañofinalBaldosa());
        gp.arrayEnemigos[1] = new Mon_Goblin(gp, "Goblin_1", Raza.ORCO, Clase.GUERRERO, 1);
        gp.arrayEnemigos[1].setArma(new Arma("Claymore", 1, 11, 1, 2, null, TipoAtaque.ArmaPesada, 0, 2));
        gp.arrayEnemigos[1].setX(5 * gp.getTamañofinalBaldosa());
        gp.arrayEnemigos[1].setY(8 * gp.getTamañofinalBaldosa());


    }
}


