package item;

import entidades.Jugador;
import gamePanel.escenarios.MenuInventario;
import item.armadura.Armadura;
import item.armas.Arma;
import item.armas.Elemento;
import item.armas.TipoAtaque;
import org.w3c.dom.*;

import javax.xml.parsers.*;
import java.awt.*;
import java.io.File;
import java.util.ArrayList;

//clase singleton.
public class Inventario {
    private static Inventario instancia;
    private ArrayList<Item> inventario;

    /**
     * contructor privado para ervitar la creacion de más de un objeto
     */
    private Inventario() {
        this.inventario = iniciarInventario();
        ;
    }

    /**
     * metodo get para inicializar la instancia en caso de no estar iniciada
     *
     * @return
     */
    public static Inventario getInstance() {
        if (instancia == null) {
            instancia = new Inventario();
        }
        return instancia;
    }

    /**
     * inicializa el inventario con los objetos del juego
     *
     * @return
     */
    private ArrayList<Item> iniciarInventario() {
        ArrayList<Item> inventario = new ArrayList<>();
        Item agua = new Item("agua", 3, 4, 0);
        Item comida = new Item("comida", 3, 3, 0);
        Item madera = new Item("madera", 1, 12, 8);
        Item carbon = new Item("carbon", 1, 9, 2);
        Item mena_hierro = new Item("mena_hierro", 1, 8, 3);
        Item mena_oro = new Item("mena_oro", 1, 6, 3);
        Item hierro = new Item("hierro", 2, 8, 4);
        Item oro = new Item("oro", 1, 6, 4);
        Arma espada = new Arma("espada", 1, 11, 1, 5, null, TipoAtaque.ArmaLigera, 0, 2);
        Arma varaMago = new Arma("varaMago", 1, 11, 5, 6, Elemento.RAYO, TipoAtaque.ArmaMágica, 10, 4);
        Arma espadaFuego = new Arma("espadaFuego", 1, 11, 2, 9, Elemento.FUEGO, TipoAtaque.ArmaLigera, 0, 2);
        Armadura escudo = new Armadura("escudo", 1, 12, 6, 6);
        Armadura escudoOro = new Armadura("escudo_oro", 1, 11, 6, 12);
        Armadura talisman = new Armadura("talismanSecreto", 1, 10, 7, 4);
        Armadura yelmo = new Armadura("yelmo", 1, 1, 7, 5);
        Armadura peto = new Armadura("peto", 1, 6, 7, 5);
        //faltan yelmo, peto,escudo oro, talisman

        inventario.add(escudoOro);
        inventario.add(agua);
        inventario.add(comida);
        inventario.add(madera);
        inventario.add(carbon);
        inventario.add(mena_hierro);
        inventario.add(mena_oro);
        inventario.add(hierro);
        inventario.add(oro);
        inventario.add(escudo);
        inventario.add(espada);
        inventario.add(espadaFuego);
        inventario.add(varaMago);
        inventario.add(talisman);
        inventario.add(yelmo);
        inventario.add(peto);

        return inventario;
    }

    /**
     * metodo que muestra las imagenes del inventario llamado desde el UI.
     *
     * @param g2d
     * @param menuInventario clase para imprimir el esqueleto del inventario
     */
    public void mostrarInventario(Graphics2D g2d, MenuInventario menuInventario, Jugador jugador) {
        int espacioAltura = 0;
        int contInventario = 0;
        ordenarInventario();
        for (int i = 0; i < 4; i++) {
            int espacio = 0;
            for (int j = 0; j < 4; j++) {
                for (int k = contInventario; k < inventario.size(); k++) {
                    Item item = inventario.get(k);
                    if (item.getCantidad() > 0) {
                        g2d.drawImage(item.getPlantillaInventario().getImg(item.getX(), item.getY()),
                                275 + espacio,
                                138 + espacioAltura,
                                55, 55, null);
                        //mostrar cantidad
                        g2d.getFont().deriveFont(20f);
                        String cantidad = String.valueOf(item.getCantidad());
                        g2d.setColor(new Color(200, 200, 200, 180));
                        g2d.drawString(cantidad, 322 + espacio, 192 + espacioAltura);
                        //sombreado
                        g2d.setColor(new Color(0, 0, 0, 180));
                        g2d.drawString(cantidad, 320 + espacio, 190 + espacioAltura);
                        espacio = espacio + menuInventario.getGp().getTamañofinalBaldosa() + menuInventario.getGp().getTamañofinalBaldosa() / 2;
                        break;
                    }
                }
                contInventario++;
            }
            espacioAltura = espacioAltura + 64;
        }
        Arma arma = jugador.getArma();
        Armadura escudo = jugador.getEscudo();
        Armadura armadura = (Armadura) jugador.getArmadura();
        Item cabeza = jugador.getCabeza();
        if (arma != null) {
            g2d.drawImage(arma.getPlantillaInventario().getImg(arma.getX(), arma.getY()),
                    165,
                    138,
                    55, 55, null);
        }
        if (escudo != null) {
            g2d.drawImage(escudo.getPlantillaInventario().getImg(escudo.getX(), escudo.getY()),
                    165,
                    138 + 64,
                    55, 55, null);
        }
        if (armadura != null) {
            g2d.drawImage(armadura.getPlantillaInventario().getImg(armadura.getX(), armadura.getY()),
                    165,
                    138 + (64 * 2),
                    55, 55, null);
        }
        if (cabeza != null) {
            g2d.drawImage(cabeza.getPlantillaInventario().getImg(cabeza.getX(), cabeza.getY()),
                    165,
                    138 + (64 * 3),
                    55, 55, null);
        }
    }

    /**
     * metodo de ordenación por el metodo burbujas
     */
    public void ordenarInventario() {
        for (int i = 0; i < inventario.size() - 1; i++) {
            for (int j = 0; j < inventario.size() - i - 1; j++) {
                if (inventario.get(j).getCantidad() < inventario.get(j + 1).getCantidad()) {
                    Item aux = inventario.get(j);
                    inventario.set(j, inventario.get(j + 1));
                    inventario.set(j + 1, aux);
                }
            }
        }
    }

    /**
     * metodo para encontrar un objeto para usar
     *
     * @param nombre
     * @return
     */
    public Item buscarObjeto(String nombre) {
        for (Item i : inventario) {
            if (nombre.equalsIgnoreCase(i.getIdNombre().toLowerCase())) {
                System.out.println("Encontrado");
                return i;
            }
        }
        System.out.println("No encontrado");
        return null;
    }

    /**
     * metodo que crea una lista de items necesarios para un crafteo a partir de un XML.
     * para ello usamos File, Document y Node
     *
     * @param nombreReceta
     * @return
     */
    public ArrayList<Item> recetaCrafteo(String nombreReceta) {
        ArrayList<String> recetaIngrediente = new ArrayList<>();
        try {
            // Cargar el archivo XML
            File archivo = new File("src/item/RecetasCrafteos.xml");
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document doc = builder.parse(archivo);
            // Normalizar el documento XML
            doc.getDocumentElement().normalize();
            // Obtener todos los nodos <receta>
            NodeList listaRecetas = doc.getElementsByTagName("nombre");
            for (int i = 0; i < listaRecetas.getLength(); i++) {
                Node nodoReceta = listaRecetas.item(i);
                // Verificar si el nodo es de tipo ELEMENT_NODE
                if (nodoReceta.getNodeType() == Node.ELEMENT_NODE) {
                    String nombre = nodoReceta.getTextContent();
                    // Filtrar por el contenido del nodo para encontrar el que queremos
                    if (nombre.equalsIgnoreCase(nombreReceta)) {
                        System.out.println("Encontrado: " + nombre);
                        // Obtener el nodo padre <receta>
                        Element receta = (Element) nodoReceta.getParentNode();
                        // Obtener los elementos hijos dentro de <ingredientes>
                        NodeList hijos = receta.getElementsByTagName("ingredientes").item(0).getChildNodes();
                        // Recorrer los elementos dentro de <ingredientes> y los añadimos a un array de String
                        for (int j = 0; j < hijos.getLength(); j++) {
                            Node ingrediente = hijos.item(j);
                            if (ingrediente.getNodeType() == Node.ELEMENT_NODE) {
                                recetaIngrediente.add(ingrediente.getTextContent());
                            }
                        }
                    }
                }
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        ArrayList<Item> recetaFinal = new ArrayList<>();
        for (int j = 0; j < recetaIngrediente.size(); j++) {
            //usamos buscar objeto para encontrar lo necesario
            Item item = buscarObjeto(recetaIngrediente.get(j));
            recetaFinal.add(item);
        }

        System.out.println(recetaFinal);
        //devolvemos un array de items necesarios
        return recetaFinal;
    }

    //getters y setters

    public ArrayList<Item> getInventario() {
        return inventario;
    }

    public void setInventario(ArrayList<Item> inventario) {
        this.inventario = inventario;
    }

    public static Inventario getInstancia() {
        return instancia;
    }

    public static void setInstancia(Inventario instancia) {
        Inventario.instancia = instancia;
    }
}