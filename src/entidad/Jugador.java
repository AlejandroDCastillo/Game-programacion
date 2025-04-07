package entidad;

import item.Inventario;
import item.Item;

import java.util.ArrayList;

public class Jugador extends Entidad {
    private int hambre;
    private int sed;
    private Inventario inventario;

    public Jugador(int vida, int defensa, int magia, int mana, int destreza, Raza raza, Clase calse, int hambre, int sed) {
        super(vida, defensa, raza, calse, magia, mana, destreza);
        this.hambre = hambre;
        this.sed = sed;
        this.inventario= Inventario.getInstance();
        this.dx=0;
    }

    /**
     * metodo para beber agua suma +30 a la sed
     */
    public boolean beberAgua() {
        Item i = inventario.buscarObjeto("agua");
        if (i.getCantidad() > 0) {
            if (sed >= 70) {
                i.setCantidad(i.getCantidad() - 1);
                sed = 100;
            } else {
                i.setCantidad(i.getCantidad() - 1);
                sed = sed + 30;
            }
            return true;
        } else {
            System.out.println("No queda agua");
            return false;
        }
    }

    /**
     * metodo para comer suma +30 al hambre
     *
     * @return
     */
    public boolean comer() {
        Item i = inventario.buscarObjeto("comida");
        if (i.getCantidad() > 0) {
            if (hambre >= 70) {
                System.out.println("comiendo");
                i.setCantidad(i.getCantidad() - 1);
                hambre = 100;
                return true;
            } else {
                System.out.println("comiendo");
                i.setCantidad(i.getCantidad() - 1);
                hambre = hambre + 30;
                return true;
            }
        } else {
            System.out.println("No queda comida");
            return false;
        }
    }

    /**
     * metodo que sirve para craftear un objeto, utiliza buscar objeto y recetaCrafteo
     * @param crafteo
     * @return
     */
    public boolean craftear(String crafteo){
        ArrayList<Item> receta=inventario.recetaCrafteo(crafteo);
        boolean hayItem=true;
        ArrayList<Integer> cantidad=new ArrayList<>();
        for(int j=0;j<receta.size();j++){
             Item ingrediente=receta.get(j);
            cantidad.add(ingrediente.getCantidad());
        }
        for (int i=0;i<receta.size() && hayItem;i++){
             Item ingrediente=receta.get(i);
            for(Item it: inventario.getInventario()){
                if(ingrediente.getIdNombre().equalsIgnoreCase(it.getIdNombre())){
                    if(it.getCantidad()>0){
                        it.setCantidad(it.getCantidad()-1);
                    }else{
                        hayItem=false;
                    }
                }
            }
        }
        if(!hayItem) {
            System.out.println("No se puede craftear" + crafteo);
            //reseteo de valores iniciales, ya q no puede craftearse
            for(int j=0;j<receta.size();j++){
                 Item ingrediente=receta.get(j);
                ingrediente.setCantidad(cantidad.get(j));
            }
            return false;
        }else{
            System.out.println("objeto "+crafteo+" creado con exito");
            Item ingrediente= inventario.buscarObjeto(crafteo);
//            Añadimos el objeto al inventario
            ingrediente.setCantidad(ingrediente.getCantidad()+1);
            return true;
        }
    }

    /**
     * metodo para descansar en la cama
     */
    public void descansar(){
        super.setVida(100);
    }



}
