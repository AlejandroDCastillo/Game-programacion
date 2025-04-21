package item;

import gamePanel.escenarios.MenuInventario;
import org.w3c.dom.*;

import javax.xml.parsers.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.sql.Array;
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
        Item agua = new Item("agua", 3, Tipo.AGUA,"src/recursos/imagenes/agua.png");
        Item comida = new Item("comida", 3, Tipo.COMIDA,"src/recursos/imagenes/comida.png");
        Item madera = new Item("madera", 1, Tipo.MADERA,"src/recursos/imagenes/madera.png");
        Item carbon = new Item("carbon", 1, Tipo.CARBON,"src/recursos/imagenes/carbon.png");
        Item mena_hierro = new Item("mena_hierro", 2, Tipo.MENA_HIERRO,"src/recursos/imagenes/mena_hierro.png");
        Item mena_oro = new Item("mena_oro", 1, Tipo.MENA_ORO,"src/recursos/imagenes/mena_oro.png");
        Item hierro = new Item("hierro", 1, Tipo.HIERRO,"src/recursos/imagenes/hierro.png");
        Item oro = new Item("mena_oro", 1, Tipo.ORO,"src/recursos/imagenes/Oro.png");
        Escudo escudo = new Escudo("escudo", 1, Tipo.ESCUDO,4,"src/recursos/imagenes/escudo.png");
        Espada espada = new Espada("espada", 1, Tipo.ESPADA,5,5.5,"src/recursos/imagenes/espada.png");
        EspadaFuego espadaFuego = new EspadaFuego("espada_fuego",0, Tipo.ESPADA,6.5,5,0.9,"src/recursos/imagenes/espada_Infernal.png");
        Espada daga = new Espada("daga",1,Tipo.DAGA,2,8,"src/recursos/imagenes/daga.png");
        VaraMago vara = new VaraMago("vara_de_mago", 1,Tipo.VARA_DE_MAGO,3,7,5,Hechizo.PERDER_TURNO,"src/recursos/imagenes/daga_Mago.png");

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
            for (int j = 0; j < 8; j++) {
                for (int k =contInventario;k<inventario.size();k++) {
                    if (inventario.get(k).getCantidad() > 0) {
                        g2d.drawImage(inventario.get(k).getImg(),menuInventario.getGp().getTamañofinalBaldosa()+espacio+menuInventario.getGp().getTamañofinalBaldosa()/3, menuInventario.getGp().getTamañofinalBaldosa()*2+espacioAltura+menuInventario.getGp().getTamañofinalBaldosa()/2, 64, 64, null);
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
        System.out.println(inventario);
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
