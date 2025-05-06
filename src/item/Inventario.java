package item;

import gamePanel.escenarios.MenuInventario;
import item.objetos.Escudo;
import item.objetos.Espada;
import item.objetos.EspadaFuego;
import item.objetos.VaraMago;
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
        Item agua = new Item("agua", 3, Tipo.AGUA,4,0);
        Item comida = new Item("comida", 3, Tipo.COMIDA,3,0);
        Item madera = new Item("madera", 0, Tipo.MADERA,12,8);
        Item carbon = new Item("carbon", 0, Tipo.CARBON,10,3);
        Item mena_hierro = new Item("mena_hierro", 1, Tipo.MENA_HIERRO,8,3);
        Item mena_oro = new Item("mena_oro", 0, Tipo.MENA_ORO,6,3);
        Item hierro = new Item("hierro", 1, Tipo.HIERRO,8,4);
        Item oro = new Item("oro", 0, Tipo.ORO,6,4);
        Escudo escudo = new Escudo("escudo", 0, Tipo.ESCUDO,4,12,6);
        Espada espada = new Espada("espada", 0, Tipo.ESPADA,5,5.5,11,1);
        EspadaFuego espadaFuego = new EspadaFuego("espada_fuego",0, Tipo.ESPADA,6.5,5,0.9,11,2);
        Espada daga = new Espada("daga",0,Tipo.DAGA,2,8,11,0);
        VaraMago vara = new VaraMago("vara_de_mago", 0,Tipo.VARA_DE_MAGO,3,7,5,Hechizo.PERDER_TURNO,11,5);

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
        inventario.add(daga);
        inventario.add(vara);

        return inventario;
    }

    public void mostrarInventario(Graphics2D g2d, MenuInventario menuInventario) {
        int espacioAltura = 0;
        int contInventario=0;
        ordenarInventario();
        for (int i = 0; i < 4; i++) {
            int espacio = 0;
            for (int j = 0; j < 4; j++) {
                for (int k =contInventario;k<inventario.size();k++) {
                    Item item=inventario.get(k);
                    if (item.getCantidad() > 0) {
                        g2d.drawImage(item.getPlantillaInventario().getImg(item.getX(),item.getY()),
                                275+espacio,
                                138+espacioAltura,
                                55, 55, null);
                        //mostrar cantidad
                        String cantidad= String.valueOf(item.getCantidad());
                        g2d.setColor(new Color(200,200,200,180));
                        g2d.drawString(cantidad,322+espacio,192+espacioAltura);
                        //sombreado
                        g2d.setColor(new Color(0,0,0,180));
                        g2d.drawString(cantidad,320+espacio,190+espacioAltura);
                        espacio = espacio +menuInventario.getGp().getTamañofinalBaldosa()+menuInventario.getGp().getTamañofinalBaldosa()/2;
                        break;
                    }
                }
                contInventario++;
            }
            espacioAltura = espacioAltura + 3;
        }
    }
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
            File archivo = new File("C:\\Users\\diego\\IdeaProjects\\Survival_Dungeons\\src\\item\\RecetasCrafteos.xml");
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
        ArrayList<Item> recetaFinal=new ArrayList<>();
        for(int j=0;j<recetaIngrediente.size();j++) {
            //usamos buscar objeto para encontrar lo necesario
            Item item=buscarObjeto(recetaIngrediente.get(j));
            recetaFinal.add(item);
        }

        System.out.println(recetaFinal);
        //devolvemos un array de items necesarios
        return recetaFinal;
    }

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