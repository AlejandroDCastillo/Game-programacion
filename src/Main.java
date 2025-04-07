import entidad.Clase;
import entidad.Jugador;
import entidad.Raza;
import item.Inventario;

public class Main {
    public static void main(String[] args) {

        Jugador jp=new Jugador(100,50,29,20,24, Raza.ELFO, Clase.CLERIGO,100,100);
        Inventario n= Inventario.getInstance();
        n.recetaCrafteo("Escudo");
        n.buscarObjeto("agua");
        jp.craftear("escudo");
        jp.craftear("escudo");
        n.mostrarInventario();
        jp.guardarJugador(jp,"C:\\Users\\diego\\IdeaProjects\\Survival_Dungeons\\src\\gestores\\guardarPartida.xml\\");

    }
}
