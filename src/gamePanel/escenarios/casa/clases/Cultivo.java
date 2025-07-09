package gamePanel.escenarios.casa.clases;

import item.Item;

public abstract class Cultivo extends Item implements Cultivos {

    protected int tiempo;
    protected int estado;
    protected int estados;
    protected int loot;

    public Cultivo(String idNombre, int cantidad, int spriteX, int spriteY, int estados,String imagePath) {
        super(idNombre, cantidad, spriteX, spriteY,imagePath);
        this.tiempo = 0;
        this.estado = 0;
        this.estados = estados;
        this.loot = loot;
    }
    public Cultivo(String idNombre, int cantidad, int spriteX, int spriteY,int estados,int loot,String imagePath){
        super(idNombre,cantidad,spriteX,spriteY,imagePath);
        this.tiempo = 0;
        this.estado = 0;
        this.estados = estados;
        this.loot = loot;
    }

//    public Cultivo(int x, int y, int estados, String nombre, Item loot) {
//        this.estado = 0;
//        this.estados = estados;
//        this.loot=loot;
//        //inicializamos el icono
//        try {
//            String imagePath = "src/recursos/imagenes/AssetsDeCultivo.png";
//            BufferedImage imagenPlantaBuffered = ImageIO.read(new File(imagePath));
//            this.icono  = new Spritesheet(imagenPlantaBuffered,13,9);
//            imagen=icono.getImg(x,y);
//        } catch (IOException e) {
//            e.printStackTrace();
//            System.err.println("Error al cargar la imagen: " + e.getMessage());
//        }
//    }

    @Override
    public abstract boolean plantarse();

    @Override
    public abstract boolean recogerse();

    @Override
    public abstract void crecer();

    @Override
    public abstract Item darItem();

    public int getTiempo() {
        return tiempo;
    }

    public void setTiempo(int tiempo) {
        this.tiempo = tiempo;
    }

    public int getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }

    public int getEstados() {
        return estados;
    }

    public void setEstados(int estados) {
        this.estados = estados;
    }

    public int getLoot() {
        return loot;
    }

    public void setLoot(int loot) {
        this.loot = loot;
    }
}
