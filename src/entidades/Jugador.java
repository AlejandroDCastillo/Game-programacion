package entidades;
import gamePanel.GamePanel;
import item.Inventario;
import item.Item;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import recursos.Spritesheet;
import recursos.teclado.DetectorTeclas;

import javax.imageio.ImageIO;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.awt.image.Raster;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.TimerTask;

public class Jugador extends Entidad {
    //atributos relacionados con movimiento
    private DetectorTeclas teclado;
    private GamePanel gp;
    protected BufferedImage sprite;
    //stats del jugador
    private int hambre;
    private int sed;
    private Inventario inventario;

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
        x=100;
        y=100;
        this.hambre = 100;
        this.sed = 100;
        this.inventario = Inventario.getInstance();
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
     * metodo para guardar
     *
     * @param jugador
     * @param rutaArchivo
     */
    public void guardarJugador(entidades.Jugador jugador, String rutaArchivo) {
        try {
            File archivo = new File(rutaArchivo);
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(archivo);
            doc.getDocumentElement().normalize();

            // Actualizar estadísticas
            NodeList estadisticas = doc.getElementsByTagName("estadisticas").item(0).getChildNodes();
            for (int i = 0; i < estadisticas.getLength(); i++) {
                Node nodo = estadisticas.item(i);
                //nos asegurtamenos de tipo elemento para evitar fallos
                if (nodo.getNodeType() == Node.ELEMENT_NODE) {
                    switch (nodo.getNodeName()) {
                        case "vida":
                            nodo.setTextContent(String.valueOf(super.getVida()));
                            break;
                        case "armadura":
                            nodo.setTextContent(String.valueOf(super.getDefensa()));
                            break;
                        case "magia":
                            nodo.setTextContent(String.valueOf(super.getMagia()));
                            break;
                        case "mana":
                            nodo.setTextContent(String.valueOf(super.getMana()));
                            break;
                        case "destreza":
                            nodo.setTextContent(String.valueOf(super.getDestreza()));
                            break;
                        case "hambre":
                            nodo.setTextContent(String.valueOf(getHambre()));
                            break;
                        case "sed":
                            nodo.setTextContent(String.valueOf(getSed()));
                            break;
                    }
                }
            }

            // Actualizar inventario
            Node inventarioNode = doc.getElementsByTagName("inventario").item(0);
            // BorrarTodo el inventario anterior
            while (inventarioNode.hasChildNodes()) {
                inventarioNode.removeChild(inventarioNode.getFirstChild());
            }

            for (Item item : inventario.getInventario()) {
                Element itemElement = doc.createElement("item");

                Element nombre = doc.createElement("nombre");
                nombre.setTextContent(item.getIdNombre());

                Element cantidad = doc.createElement("cantidad");
                cantidad.setTextContent(String.valueOf(item.getCantidad()));

                Element tipo = doc.createElement("tipo");
                tipo.setTextContent(item.getTipo().toString());

                itemElement.appendChild(nombre);
                itemElement.appendChild(cantidad);
                itemElement.appendChild(tipo);

                inventarioNode.appendChild(itemElement);
            }

            // Guardar cambios en el archivo
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource source = new DOMSource(doc);
            StreamResult result = new StreamResult(new File(rutaArchivo));
            transformer.transform(source, result);

            System.out.println("Partida guardada correctamente.");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

//    public Jugador cargarJugador(String rutaArchivo) {
//        try {
//            File archivo = new File(rutaArchivo);
//            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
//            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
//            Document doc = dBuilder.parse(archivo);
//            doc.getDocumentElement().normalize();
//
//            Jugador jugador = new Jugador();
//
//            // Leer estadísticas
//            Node estadisticasNode = doc.getElementsByTagName("estadisticas").item(0);
//            NodeList hijos = estadisticasNode.getChildNodes();
//            for (int i = 0; i < hijos.getLength(); i++) {
//                Node nodo = hijos.item(i);
//                //nos aseguramos de que es de tipo elemento para evitar fallos
//                if (nodo.getNodeType() == Node.ELEMENT_NODE) {
//                    int valor = Integer.parseInt(nodo.getTextContent());
//                    switch (nodo.getNodeName()) {
//                        case "vida": jugador.setVida(valor); break;
//                        case "armadura": jugador.setArmadura(valor); break;
//                        case "magia": jugador.setMagia(valor); break;
//                        case "mana": jugador.setMana(valor); break;
//                        case "destreza": jugador.setDestreza(valor); break;
//                        case "hambre": jugador.setHambre(valor); break;
//                        case "sed": jugador.setSed(valor); break;
//                    }
//                }
//            }
//
//            // Leer inventario
//            NodeList items = doc.getElementsByTagName("item");
//            Inventario inventario = Inventario.getInventario();
//            inventario.getItem().clear(); // Vacía el inventario actual
//
//            for (int i = 0; i < items.getLength(); i++) {
//                Node itemNode = items.item(i);
//                if (itemNode.getNodeType() == Node.ELEMENT_NODE) {
//                    Element itemElement = (Element) itemNode;
//
//                    String nombre = itemElement.getElementsByTagName("nombre").item(0).getTextContent();
//                    int cantidad = Integer.parseInt(itemElement.getElementsByTagName("cantidad").item(0).getTextContent());
//                    Tipo tipo = Tipo.valueOf(itemElement.getElementsByTagName("tipo").item(0).getTextContent());
//
//                    Item item = new Item(nombre, cantidad, tipo);
//                    inventario.getItems().add(item);
//                }
//            }
//
//            jugador.setInventario(inventario);
//
//            System.out.println("Partida cargada correctamente.");
//            return jugador;
//
//        } catch (Exception e) {
//            e.printStackTrace();
//            return null;
//        }
//    }

    /**
     * metodo para actualizar la imagen del jugador en el JPanel
     */
    public void update() {
        if (!teclado.abajo && !teclado.izquierda && !teclado.arriba && !teclado.derecha) {
            return;
        }
        else if (teclado.abajo&&teclado.derecha) {
            direccion="derecha";
            x=x+( Math.hypot(velocidad,velocidad) /2);
            y=y+( Math.hypot(velocidad,velocidad) /2);
        }
        else if (teclado.abajo&&teclado.izquierda) {
            direccion="izquierda";
            x=x-( Math.hypot(velocidad,velocidad) /2);
            y=y+( Math.hypot(velocidad,velocidad) /2);
        }
        else if (teclado.arriba&&teclado.izquierda) {
            direccion="izquierda";
            x=x-( Math.hypot(velocidad,velocidad) /2);
            y=y-( Math.hypot(velocidad,velocidad) /2);
        }
        else if (teclado.arriba&&teclado.derecha) {
            direccion="derecha";
            x=x+( Math.hypot(velocidad,velocidad) /2);
            y=y-( Math.hypot(velocidad,velocidad) /2);
        }
        else if (teclado.izquierda) {
            direccion = "izquierda";
            x = x - velocidad;
        }
        else if (teclado.derecha) {
            direccion = "derecha";
            x = x + velocidad;
        }
        else if(teclado.arriba) {
            direccion = "arriba";
            y = y - velocidad;
        }
        else if (teclado.abajo) {
            direccion = "abajo";
            y = y + velocidad;
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
            case "izquierda" ->sprite = plantillaJugador.getImg(2,numSprite);
            case "derecha" -> sprite = plantillaJugador.getImg(3,numSprite);
            case "arriba" -> sprite = plantillaJugador.getImg(numSprite,1);
            case "abajo" -> sprite = plantillaJugador.getImg(numSprite,2);
            default -> sprite = plantillaJugador.getImg(1,3);
        };
    }

    /**
     * metodo que dibuja el sprite en pantalla
     * @param g2d
     * @throws IOException
     */
    public void dibujar(Graphics2D g2d) throws IOException {
        g2d.drawImage(obtenerImagenPlayer(), (int) x, (int) y, gp.getTamañofinalBaldosa(), gp.getTamañofinalBaldosa(), null);
    }


    //GETTERS SETTERS Y TOSTRING

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
}
