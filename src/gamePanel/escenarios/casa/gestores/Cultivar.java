package gamePanel.escenarios.casa.gestores;

import gamePanel.GamePanel;
import gamePanel.escenarios.casa.clases.Cultivo;
import item.Inventario;
import item.Item;

import java.util.ArrayList;

public class Cultivar {
    public ArrayList<Cultivo> plantados;
    public GamePanel gp;

    public Cultivar(GamePanel gp){
        this.gp=gp;
        plantados=new ArrayList<>();
    }
    public Cultivo buscarcultivo(String idNombre){
        Item item = Inventario.getInstancia().buscarObjeto(idNombre);
        Cultivo cultivo= (Cultivo) item;
        if(item != null){
            return cultivo;
        }
        return null;
    }

    public boolean añadirCultivo(String idNombre){
        Cultivo cultivo= buscarcultivo(idNombre);
        boolean plantado = cultivo.plantarse();
        if(plantado){
            return true;
        }else{
            return false;
        }
    }

    public boolean quitarCultivo(String idNombre){
        Cultivo cultivo= buscarcultivo(idNombre);
        boolean recogido= cultivo.recogerse();
        if(recogido){
            return true;
        }else{
            return false;
        }
    }
}
