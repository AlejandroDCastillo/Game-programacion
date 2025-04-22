package utiles;
import entidades.Jugador;
import item.Inventario;
import item.Item;
import item.Tipo;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;

public class Util {

    /**
     * metodo para limpiar la consola
     */
    public static void limpiarPantalla() {
        for (int i = 0; i < 50; i++) {
            System.out.println();
        }
    }

    /**
     * metodo que espera los milisegundos que le indique
     * este metodo utiliza thread.sleep
     *
     * @param milisegundos
     */
    public static void esperar(int milisegundos) {
        try {
            Thread.sleep(milisegundos); //detiene o pausa el hilo de flujo durante un tiempo
        } catch (InterruptedException e) {
            //se realiza para evitar la excepcion InterruptedException. se da cuenado otro hilo actua por este
            //con esta linea logramos evitar que continue el codigo.
            Thread.currentThread().interrupt();
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
                            nodo.setTextContent(String.valueOf(jugador.getVida()));
                            break;
                        case "armadura":
                            nodo.setTextContent(String.valueOf(jugador.getDefensa()));
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

            for (Item item : Inventario.getInstancia().getInventario()) {
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

//    public void cargarJugador(String rutaArchivo) {
//        try {
//            File archivo = new File(rutaArchivo);
//            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
//            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
//            Document doc = dBuilder.parse(archivo);
//            doc.getDocumentElement().normalize();
//
//            Jugador jugador = null;
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
//            Inventario inventario= Inventario.getInstance();
//            //vaciar el inventario actual
//            //clear borra los punteros del arraaylist, no los objetos
//            inventario.getInventario().clear();
//
//            for (int i = 0; i < items.getLength(); i++) {
//                Node itemNode = items.item(i);
//                if (itemNode.getNodeType() == Node.ELEMENT_NODE) {
//                    Element itemElement = (Element) itemNode;
//
//                    String nombre = itemElement.getElementsByTagName("nombre").item(0).getTextContent();
//                    int cantidad = Integer.parseInt(itemElement.getElementsByTagName("cantidad").item(0).getTextContent());
//                    Tipo tipo = Tipo.valueOf(itemElement.getElementsByTagName("tipo").item(0).getTextContent());
//                    String ruta ;
//
//                    Item item = new Item(nombre, cantidad, tipo,ruta);
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


}
