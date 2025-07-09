package gamePanel.escenarios.casa.obj;

import entidades.Entidad;
import gamePanel.escenarios.casa.clases.Cultivo;
import gamePanel.escenarios.casa.clases.Cultivos;
import item.Inventario;
import item.Item;
import recursos.imagenes.Spritesheet;

public class Patata extends Cultivo implements Cultivos {


    public Patata(String idNombre,int cantidad,int x, int y, int estados) {
        //buscamos la patata
        super(idNombre,cantidad,x,y,estados,4,"src/recursos/imagenes/AssetsDeCultivo.png");
        //iniciamos el tiempo que tarda la patata en crecer
        this.tiempo = 200;
        this.loot = 4;
    }

    @Override
    public boolean plantarse() {
        Inventario inv = Inventario.getInstance();
        Item item = inv.buscarObjeto("patata");
        if (item.getCantidad() > 1) {
            System.out.println("Plantando patata");
            item.setCantidad(item.getCantidad() - 1);
            return true;
        } else {
            System.out.println("No se pudo plantar");
            return false;
        }
    }

    @Override
    public boolean recogerse() {
        if (estado == estados) {
            System.out.println("Se puede recoger");
//            añadimos el loot
            Item item = Inventario.getInstance().buscarObjeto("patata");
            item.setCantidad(item.getCantidad() + loot);
            return true;
        } else {
            System.out.println("Aun no esta preparada para recogerse");
            return false;
        }
    }

    @Override
    public void crecer() {

    }

    @Override
    public Item darItem() {
        return null;
    }

    @Override
    public int getTiempo() {
        return super.getTiempo();
    }

    @Override
    public void setTiempo(int tiempo) {
        super.setTiempo(tiempo);
    }

    @Override
    public int getEstado() {
        return super.getEstado();
    }

    @Override
    public void setEstado(int estado) {
        super.setEstado(estado);
    }

    @Override
    public void setEstados(int estados) {
        super.setEstados(estados);
    }

    @Override
    public int getEstados() {
        return super.getEstados();
    }

    @Override
    public int getLoot() {
        return super.getLoot();
    }

    @Override
    public void setLoot(int loot) {
        super.setLoot(loot);
    }

    @Override
    public String getIdNombre() {
        return super.getIdNombre();
    }

    @Override
    public void setIdNombre(String idNombre) {
        super.setIdNombre(idNombre);
    }

    @Override
    public int getCantidad() {
        return super.getCantidad();
    }

    @Override
    public void setCantidad(int cantidad) {
        super.setCantidad(cantidad);
    }

    @Override
    public Spritesheet getPlantillaInventario() {
        return super.getPlantillaInventario();
    }

    @Override
    public void setPlantillaInventario(Spritesheet plantillaInventario) {
        super.setPlantillaInventario(plantillaInventario);
    }

    @Override
    public int getX() {
        return super.getX();
    }

    @Override
    public void setX(int x) {
        super.setX(x);
    }

    @Override
    public int getY() {
        return super.getY();
    }

    @Override
    public void setY(int y) {
        super.setY(y);
    }

    @Override
    public void aumentar(Entidad entidad, int numero) {
        super.aumentar(entidad, numero);
    }
}
