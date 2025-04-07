package item;

import org.w3c.dom.*;

import javax.xml.parsers.*;
import java.io.File;
import java.util.ArrayList;
import java.util.ArrayList;

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
        Item agua = new Item("agua", 3, Tipo.AGUA);
        Item comida = new Item("comida", 3, Tipo.COMIDA);
        Item madera = new Item("madera", 1, Tipo.MADERA);
        Item carbon = new Item("carbon", 0, Tipo.CARBON);
        Item mena_hierro = new Item("mena_hierro", 0, Tipo.MENA_HIERRO);
        Item mena_oro = new Item("mena_oro", 0, Tipo.MENA_ORO);
        Item hierro = new Item("hierro", 2, Tipo.HIERRO);
        Item oro = new Item("mena_oro", 0, Tipo.ORO);
        Item escudo = new Item("escudo", 0, Tipo.ESCUDO);
        inventario.add(agua);
        inventario.add(comida);
        inventario.add(madera);
        inventario.add(carbon);
        inventario.add(mena_hierro);
        inventario.add(mena_oro);
        inventario.add(hierro);
        inventario.add(oro);
        inventario.add(escudo);
        return inventario;
    }

    public void mostrarInventario(){
        for(Item i:inventario){
            if(i.getCantidad()>0){
                System.out.println(i.getIdNombre()+": "+i.getCantidad());
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
}
