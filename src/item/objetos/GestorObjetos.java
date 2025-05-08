package item.objetos;

import gamePanel.GamePanel;

public class GestorObjetos {
    GamePanel gp;
    public GestorObjetos(GamePanel gp) {
        this.gp = gp;
    }
    public void establecerObjetos(){
        gp.arrayobjetos[0]= new Llave(gp);
        gp.arrayobjetos[0].x = 6 * gp.getTamañofinalBaldosa();
        gp.arrayobjetos[0].y = 5 * gp.getTamañofinalBaldosa();

        gp.arrayobjetos[2]= new Puerta(gp);
        gp.arrayobjetos[2].x = 8 * gp.getTamañofinalBaldosa();
        gp.arrayobjetos[2].y = 6 * gp.getTamañofinalBaldosa();

        gp.arrayobjetos[1]= new Cofre(gp);
        gp.arrayobjetos[1].x = 8 * gp.getTamañofinalBaldosa();
        gp.arrayobjetos[1].y = 7 * gp.getTamañofinalBaldosa();
    }
}
