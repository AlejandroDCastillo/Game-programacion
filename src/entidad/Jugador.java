package entidad;
import item.Inventario;
import item.Item;
import org.w3c.dom.*;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import java.io.File;
import java.util.ArrayList;

public class Jugador extends Entidad {
    private int hambre;
    private int sed;
    private Inventario inventario;

    public Jugador(int vida, int defensa, int magia, int mana, int destreza, Raza raza, Clase calse, int hambre, int sed) {
        super(vida, defensa, raza, calse, magia, mana, destreza);
        this.hambre = hambre;
        this.sed = sed;
        this.inventario = Inventario.getInstance();
        this.dx = 0;
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
     * metodo para descansar en la cama
     */
    public void descansar() {
        super.setVida(100);
    }

    public void guardarJugador(Jugador jugador, String rutaArchivo) {
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
                            nodo.setTextContent(String.valueOf(jugador.getVida()));
                            break;
                        case "armadura":
                            nodo.setTextContent(String.valueOf(getArmadura()));
                            break;
                        case "magia":
                            nodo.setTextContent(String.valueOf(jugador.getMagia()));
                            break;
                        case "mana":
                            nodo.setTextContent(String.valueOf(jugador.getMana()));
                            break;
                        case "destreza":
                            nodo.setTextContent(String.valueOf(jugador.getDestreza()));
                            break;
                        case "hambre":
                            nodo.setTextContent(String.valueOf(jugador.getHambre()));
                            break;
                        case "sed":
                            nodo.setTextContent(String.valueOf(jugador.getSed()));
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
