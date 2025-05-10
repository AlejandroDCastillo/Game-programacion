package entidades;

import gamePanel.GamePanel;
import item.Inventario;
import item.Item;
import recursos.imagenes.Spritesheet;
import recursos.teclado.DetectorTeclas;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class Jugador extends Entidad {
    //atributos relacionados con movimiento
    private DetectorTeclas teclado;
    protected BufferedImage sprite;
    //stats del jugador
    private int hambre;
    private int sed;
    private Inventario inventario;
    private int llaves = 0;
    //obj equipados
    private Item arma;
    private Item armadura;
    private Item escudo;
    private Item cabeza;
    public int objetoInteractuado=0;

    /**
     * constructor del jugador
     *
     * @param teclado
     * @param gp
     * @param nombre
     * @param raza
     * @param clase
     * @param nivel
     */
    public Jugador(DetectorTeclas teclado, GamePanel gp, String nombre, Raza raza, Clase clase, int nivel) {
        super(gp);
        this.nombre=nombre;
        this.raza=raza;
        this.clase=clase;
        this.nivel=nivel;
        iniciarRaza(raza);
        iniciarClase(clase);
        estadisticasNivel(nivel);
        this.velocidad=velocidadMax;
        this.velocidadDiagonal = Math.hypot(this.velocidad,this.velocidad)/2;
        this.teclado = teclado;
        this.direccion = "";
        x = 200;
        y = 200;
        this.hambre = 100;
        this.sed = 100;
        this.inventario = Inventario.getInstance();
        zonaDeColision = new Rectangle(8, 16, 32, 32);
        zonaDeColisionDefectoX = zonaDeColision.x;
        zonaDeColisionDefectoY = zonaDeColision.y;
        try{
            String imagePath = "src/recursos/imagenes/caballero.png";
            BufferedImage imagenPlantillaBuffered = ImageIO.read(new File(imagePath));
            plantillaSprite = new Spritesheet(imagenPlantillaBuffered, 6, 4);
        }catch(IOException e){
            e.printStackTrace();
        }
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

    public boolean equiparObjeto(String objeto) {
        Item obj = inventario.buscarObjeto(objeto);
        if (obj != null) {
            switch (objeto) {
                case "escudo", "escudoOro", "talismanSecreto": {
                    if (obj.getCantidad() > 0) {
                        setEscudo(obj);
                        return true;
                    } else {
                        return false;
                    }
                }
                case "espada", "espadaFuego", "varaMago": {
                    if (obj.getCantidad() > 0) {
                        setArma(obj);
                        return true;
                    } else {
                        return false;
                    }
                }
                case "yelmo": {
                    if (obj.getCantidad() > 0) {
                        setCabeza(obj);
                        return true;
                    } else {
                        return false;
                    }
                }
                case "peto": {
                    if (obj.getCantidad() > 0) {
                        setArmadura(obj);
                        return true;
                    } else {
                        return false;
                    }
                }
                default:
                    return false;
            }
        }else{
            return false;
        }
    }

    /**
     * metodo que sirve para craftear un objeto, utiliza buscar objeto y recetaCrafteo
     *
     * @param crafteo
     * @return
     */
    public boolean craftear(String crafteo) {
        ArrayList<Item> receta = inventario.recetaCrafteo(crafteo);
        boolean hayItem = true;
        ArrayList<Integer> cantidad = new ArrayList<>();
        for (int j = 0; j < receta.size(); j++) {
            Item ingrediente = receta.get(j);
            cantidad.add(ingrediente.getCantidad());
        }
        for (int i = 0; i < receta.size() && hayItem; i++) {
            Item ingrediente = receta.get(i);
            for (Item it : inventario.getInventario()) {
                if (ingrediente.getIdNombre().equalsIgnoreCase(it.getIdNombre())) {
                    if (it.getCantidad() > 0) {
                        it.setCantidad(it.getCantidad() - 1);
                    } else {
                        hayItem = false;
                    }
                }
            }
        }
        if (!hayItem) {
            System.out.println("No se puede craftear" + crafteo);
            //reseteo de valores iniciales, ya q no puede craftearse
            for (int j = 0; j < receta.size(); j++) {
                Item ingrediente = receta.get(j);
                ingrediente.setCantidad(cantidad.get(j));
            }
            return false;
        } else {
            System.out.println("objeto " + crafteo + " creado con exito");
            Item ingrediente = inventario.buscarObjeto(crafteo);
//            Añadimos el objeto al inventario
            ingrediente.setCantidad(ingrediente.getCantidad() + 1);
            return true;
        }
    }

    /**
     * metodo para actualizar la imagen del jugador en el JPanel
     */
    public void update() {
        if (!teclado.abajo && !teclado.izquierda && !teclado.arriba && !teclado.derecha) {
            return;
        } else if (teclado.abajo && teclado.derecha) {
            direccion = "abajo-derecha";

        } else if (teclado.abajo && teclado.izquierda) {
            direccion = "abajo-izquierda";

        } else if (teclado.arriba && teclado.izquierda) {
            direccion = "arriba-izquierda";

        } else if (teclado.arriba && teclado.derecha) {
            direccion = "arriba-derecha";

        } else if (teclado.izquierda) {
            direccion = "izquierda";

        } else if (teclado.derecha) {
            direccion = "derecha";

        } else if (teclado.arriba) {
            direccion = "arriba";

        } else if (teclado.abajo) {
            direccion = "abajo";

        }
        colision = false;
        gp.detectorDeColisiones.comprobarBaldosa(this);
        int objetoIndex = gp.detectorDeColisiones.comprobarObjetos(this, true);
        gp.gestorDeEventos.comprobarEventos();
        recogerObjetos(objetoIndex);
        if (!colision) {
            switch (direccion) {
                case "arriba":
                    y = y - velocidad;
                    break;
                case "abajo":
                    y = y + velocidad;
                    break;
                case "izquierda":
                    x = x - velocidad;
                    break;
                case "derecha":
                    x = x + velocidad;
                    break;
                case "abajo-izquierda":
                    x = x - velocidadDiagonal;
                    y = y + velocidadDiagonal;
                    break;
                case "abajo-derecha":
                    x = x + velocidadDiagonal;
                    y = y + velocidadDiagonal;
                    break;
                case "arriba-izquierda":
                    x = x - velocidadDiagonal;
                    y = y - velocidadDiagonal;
                    break;
                case "arriba-derecha":
                    x = x + velocidadDiagonal;
                    y = y - velocidadDiagonal;
                    break;


            }
        }
        contadorUpdates++;
        if (contadorUpdates % 8 == 0) {
            numSprite++;
        }
        if (numSprite >= 2) {
            numSprite = 0;
            contadorUpdates = 0;
        }
    }

    /**
     * tomar el sprite correspondiente al jugador
     *
     * @return
     *
     */
    public BufferedImage obtenerImagenPlayer()  {
        if (gp.estadoJuego==gp.menuInicio) {
            return sprite = plantillaSprite.getImg(numSprite, 2, gp.getTamañofinalBaldosa());
        }
        return switch (direccion) {
            case "izquierda", "arriba-izquierda", "abajo-izquierda" ->
                    sprite = plantillaSprite.getImg(2, numSprite, gp.getTamañofinalBaldosa());
            case "derecha", "arriba-derecha", "abajo-derecha" ->
                    sprite = plantillaSprite.getImg(3, numSprite, gp.getTamañofinalBaldosa());
            case "arriba" -> sprite = plantillaSprite.getImg(numSprite, 1, gp.getTamañofinalBaldosa());
            case "abajo" -> sprite = plantillaSprite.getImg(numSprite, 2, gp.getTamañofinalBaldosa());
            default -> sprite = plantillaSprite.getImg(1, 3, gp.getTamañofinalBaldosa());
        };
    }

    public void recogerObjetos(int index) {
        String nombreObjeto = "";
        if (index != 999) {
            nombreObjeto = gp.arrayobjetos[index].getNombre();

        }
        switch (nombreObjeto) {
            case "llave":
                objetoInteractuado = 1;
                gp.arrayobjetos[index] = null;
                llaves++;
                break;
            case "cofre":
                if (llaves > 0) {
                    gp.arrayobjetos[index].setImagen(gp.arrayobjetos[index].plantillaSprite.getImg(9,0,gp.getTamañofinalBaldosa()));
                    if (contadorUpdates % 18 == 0) {
                        gp.arrayobjetos[index] = null;
                    }
                    gp.getInterfaz().enseñarMensaje("ENHORABUENA \n has conseguido...");
                } else {
                    gp.getInterfaz().enseñarMensaje("No tienes LLaves");
                }
                break;
        }


    }

    /**
     * metodo que dibuja el sprite en pantalla
     *
     * @param g2d
     * @throws IOException
     */
    @Override
    public void dibujar(Graphics2D g2d)  {
        g2d.fillRect((int) (x + 8), (int) (y + 16), 32, 32);
        g2d.drawImage(obtenerImagenPlayer(), (int) x, (int) y, gp.getTamañofinalBaldosa(), gp.getTamañofinalBaldosa(), null);

    }

    /**
     * metodo para iniciar por raza las estats
     *
     * @param raza
     */
    @Override
    public void iniciarRaza(Raza raza) {
        switch (raza) {
            case HUMANO -> {
                this.destreza = 10;
                this.fuerza = 10;
                this.velocidadMax = 3;
                this.magia = 10;
                this.vidaMax = 100;
                this.mana=50;
            }
            case ELFO -> {
                this.destreza = 20;
                this.fuerza = 5;
                this.velocidadMax = 5;
                this.magia = 15;
                this.vidaMax = 70;
                this.mana=50;
            }
            case ENANO -> {
                this.destreza = 5;
                this.fuerza = 20;
                this.velocidadMax = 2;
                this.magia = 5;
                this.vidaMax = 120;
                this.mana=50;
            }
            case ORCO -> {
                this.destreza = 15;
                this.fuerza = 15;
                this.velocidadMax = 2;
                this.magia = 5;
                this.vidaMax = 100;
                this.mana=50;
            }

        }
    }

    /**
     * metodo para modificar stats segun clase
     *
     * @param clase
     */
    @Override
    public void iniciarClase(Clase clase) {
        switch (clase) {
            case MAGO -> {
                setMagia(getMagia() + 10);
                setVidaMax(getVidaMax() - 20);
                setMana(getMana()+20);

            }
            case PICARO -> {
                setDestreza(getDestreza() + 10);
                setFuerza(getFuerza() - 5);
            }
            case CLERIGO -> {
                setMagia(getMagia() + 5);
                setVidaMax(getVidaMax() + 10);
                setMana(getMana()+10);
                setDestreza(getDestreza() - 5);
            }
            case GUERRERO -> {
                setFuerza(getFuerza() + 10);
                setVidaMax(getVidaMax() + 5);
                setMagia(0);
            }
        }
    }

    /**
     * metodo para establecer una subida de nivel
     * @param nivel
     */
    @Override
    public void estadisticasNivel(int nivel) {
        Clase c=getClase();
        //incremento proporcional al nivel
        int incremento = 5 * nivel;
        switch (c) {
            case MAGO -> {
                setMagia(getMagia() + incremento);
                setMana(getMana()+incremento);
                setDestreza(getDestreza() + (int) (incremento / 4));
                setFuerza(getFuerza() + (int) (incremento / 4));
            }
            case PICARO -> {
                setDestreza(getDestreza() + incremento);
                setFuerza(getFuerza() + (int) (incremento / 4));
                setMagia(getMagia() + (int) (incremento / 4));
                setMana(getMana()+(int) (incremento / 4));
            }
            case CLERIGO -> {
                setMagia(getMagia() + incremento);
                setMana(getMana()+(int) (incremento / 2));
                setDestreza(getDestreza() + (int) (incremento / 4));
                setFuerza(getFuerza() + (int) (incremento / 4));
            }
            case GUERRERO -> {
                setFuerza(getFuerza() + incremento);
                setDestreza(getDestreza() + (int) (incremento / 4));
                setMagia(getMagia() + (int) (incremento / 4));
                setMana(getMana()+(int) (incremento / 4));
            }

        }
        setVidaMax(getVidaMax() + (int) (incremento / 2));

    }
    //GETTERS SETTERS Y TO STRING

    public Item getArma() {
        return arma;
    }

    public void setArma(Item arma) {
        this.arma = arma;
    }

    @Override
    public Item getArmadura() {
        return armadura;
    }

    @Override
    public void setArmadura(Item armadura) {
        this.armadura = armadura;
    }

    public Item getEscudo() {
        return escudo;
    }

    public void setEscudo(Item escudo) {
        this.escudo = escudo;
    }

    public Item getCabeza() {
        return cabeza;
    }

    public void setCabeza(Item cabeza) {
        this.cabeza = cabeza;
    }

    public DetectorTeclas getTeclado() {
        return teclado;
    }

    public void setTeclado(DetectorTeclas teclado) {
        this.teclado = teclado;
    }

    public GamePanel getGp() {
        return gp;
    }

    public void setGp(GamePanel gp) {
        this.gp = gp;
    }

    public int getHambre() {
        return hambre;
    }

    public void setHambre(int hambre) {
        this.hambre = hambre;
    }

    public int getSed() {
        return sed;
    }

    public void setSed(int sed) {
        this.sed = sed;
    }

    public Inventario getInventario() {
        return inventario;
    }

    public void setInventario(Inventario inventario) {
        this.inventario = inventario;
    }

    public int getLlaves() {
        return llaves;
    }

    public void setLlaves(int llaves) {
        this.llaves = llaves;
    }
}
