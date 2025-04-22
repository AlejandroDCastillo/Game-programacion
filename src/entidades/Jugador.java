package entidades;
import gamePanel.GamePanel;
import item.Inventario;
import item.Item;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import recursos.imagenes.Spritesheet;
import recursos.teclado.DetectorTeclas;

import javax.imageio.ImageIO;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class Jugador extends Entidad {
    //atributos relacionados con movimiento
    private DetectorTeclas teclado;
    private GamePanel gp;
    protected BufferedImage sprite;
    //stats del jugador
    private int hambre;
    private int sed;
    private Inventario inventario;
    private int llaves=0;

    /**
     * constructor del jugador
     * @param teclado
     * @param gp
     * @param nombre
     * @param raza
     * @param clase
     * @param nivel
     */
    public Jugador(DetectorTeclas teclado, GamePanel gp,String nombre,Raza raza,Clase clase,int nivel) {
        super(nombre,raza,clase,nivel);
        this.teclado = teclado;
        this.gp = gp;
        this.direccion="";
        x=0;
        y=0;
        this.hambre = 100;
        this.sed = 100;
        this.inventario = Inventario.getInstance();
        zonaDeColision = new Rectangle(8, 16,32,32);
        zonaDeColisionDefectoX = zonaDeColision.x;
        zonaDeColisionDefectoY = zonaDeColision.y;
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
        }
        else if (teclado.abajo&&teclado.derecha) {
            direccion="abajo-derecha";

        }
        else if (teclado.abajo&&teclado.izquierda) {
            direccion="abajo-izquierda";

        }
        else if (teclado.arriba&&teclado.izquierda) {
            direccion="arriba-izquierda";

        }
        else if (teclado.arriba&&teclado.derecha) {
            direccion="arriba-derecha";

        }
        else if (teclado.izquierda) {
            direccion = "izquierda";

        }
        else if (teclado.derecha) {
            direccion = "derecha";

        }
        else if(teclado.arriba) {
            direccion = "arriba";

        }
        else if (teclado.abajo) {
            direccion = "abajo";

        }
        colision = false;
        boolean coli = true;
        gp.detectorDeColisiones.comprobarBaldosa(this);
        int objetoIndex = gp.detectorDeColisiones.comprobarObjetos(this,true);
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
                        x=x-velocidadDiagonal;
                        y=y+velocidadDiagonal;
                        break;
                        case "abajo-derecha":
                            x=x+velocidadDiagonal;
                            y=y+velocidadDiagonal;
                            break;
                            case "arriba-izquierda":
                                x=x-velocidadDiagonal;
                                y=y-velocidadDiagonal;
                                break;
                                case "arriba-derecha":
                                    x=x+velocidadDiagonal;
                                    y=y-velocidadDiagonal;
                                    break;


            }
        }
        contadorUpdates++;
        if (contadorUpdates%8==0){
            numSprite++;
        }
        if (numSprite>=2){
            numSprite=0;
            contadorUpdates=0;
        }
    }

    /**
     * tomar el sprite correspondiente al jugador
     * @return
     * @throws IOException
     */
    public BufferedImage obtenerImagenPlayer() throws IOException {
        String imagePath = "src/recursos/imagenes/caballero.png";
        BufferedImage imagenPlantillaBuffered = ImageIO.read(new File(imagePath));
        Spritesheet plantillaJugador = new Spritesheet(imagenPlantillaBuffered,6,4);
        return switch (direccion) {
            case "izquierda","arriba-izquierda","abajo-izquierda" ->sprite = plantillaJugador.getImg(2,numSprite, gp.getTamañofinalBaldosa());
            case "derecha", "arriba-derecha", "abajo-derecha" -> sprite = plantillaJugador.getImg(3,numSprite, gp.getTamañofinalBaldosa());
            case "arriba" -> sprite = plantillaJugador.getImg(numSprite,1, gp.getTamañofinalBaldosa());
            case "abajo" -> sprite = plantillaJugador.getImg(numSprite,2, gp.getTamañofinalBaldosa());
            default -> sprite = plantillaJugador.getImg(1,3, gp.getTamañofinalBaldosa());
        };
    }

    public void recogerObjetos(int index){
        String nombreObjeto = "";
        if (index!=999){
             nombreObjeto = gp.arrayobjetos[index].getNombre();
        }
        switch (nombreObjeto){
            case "llave":
                gp.arrayobjetos[index]=null;
                llaves++;
                break;
            case "cofre":
                if (llaves>0){
                        gp.arrayobjetos[index].setImagen(gp.arrayobjetos[index].getSprite().getImg(9,0,48));
                        if (contadorUpdates%20==0)
                       gp.arrayobjetos[index]=null;
                       gp.getInterfaz().enseñarMensaje("ENHORABUENA \n has conseguido...");
                }else{
                    gp.getInterfaz().enseñarMensaje("No tienes LLaves");
                }
                break;
        }


    }

    /**
     * metodo que dibuja el sprite en pantalla
     * @param g2d
     * @throws IOException
     */
    public void dibujar(Graphics2D g2d) throws IOException {
        g2d.fillRect((int) (x+8), (int) (y+16),32,32);
        g2d.drawImage(obtenerImagenPlayer(), (int) x, (int) y, gp.getTamañofinalBaldosa(), gp.getTamañofinalBaldosa(), null);

    }


    //GETTERS SETTERS Y TO STRING

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

    @Override
    public BufferedImage getSprite() {
        return sprite;
    }

    @Override
    public void setSprite(BufferedImage sprite) {
        this.sprite = sprite;
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
