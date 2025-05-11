package gestores;

import gamePanel.GamePanel;
import item.objetos.Cofre;
import item.objetos.Llave;
import item.objetos.Puerta;

public class GestorObjetos {
    GamePanel gp;
    public GestorObjetos(GamePanel gp) {
        this.gp = gp;
    }
    public void establecerObjetos(){
        gp.arrayobjetos[0]= new Llave(gp);
        gp.arrayobjetos[0].setX(6 * gp.getTamañofinalBaldosa());
        gp.arrayobjetos[0].setY(5 * gp.getTamañofinalBaldosa());

        gp.arrayobjetos[2]= new Puerta(gp);
        gp.arrayobjetos[2].setX(8 * gp.getTamañofinalBaldosa());
        gp.arrayobjetos[2].setY(6 * gp.getTamañofinalBaldosa());

        gp.arrayobjetos[1]= new Cofre(gp);
        gp.arrayobjetos[1].setX(8 * gp.getTamañofinalBaldosa());
        gp.arrayobjetos[1].setY(7 * gp.getTamañofinalBaldosa());
    }
}
