package entidades;

import gamePanel.GamePanel;
import gestores.GestorCombate;
import item.Inventario;
import item.Item;
import item.armadura.Armadura;
import item.armas.Arma;
import recursos.imagenes.Spritesheet;
import recursos.teclado.DetectorTeclas;
import utiles.UtilDiego;

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
    public int objetoInteractuado = 0;
    private boolean opcionAtacar = false;
    private boolean opcionHuir = false;

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
        this.nombre = nombre;
        this.raza = raza;
        this.clase = clase;
        this.nivel = nivel;
        iniciarRaza(raza);
        iniciarClase(clase);
        estadisticasNivel(nivel);
        this.vida = vidaMax;
        this.velocidad = velocidadMax / 2;
        this.velocidadDiagonal = Math.hypot(this.velocidad, this.velocidad) / 2;
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
        //sprite
        try {
            String imagePath = "src/recursos/imagenes/caballero.png";
            BufferedImage imagenPlantillaBuffered = ImageIO.read(new File(imagePath));
            plantillaSprite = new Spritesheet(imagenPlantillaBuffered, 6, 4);
        } catch (IOException e) {
            e.printStackTrace();
        }
        //boolean del combate
        opcionAtacar = false;
        opcionHuir = false;
        calcularEXPsubirNivel();
    }

    /**
     * metodo para beber agua suma +30 a la sed
     */
    public boolean beberMana() {
        Item i = inventario.buscarObjeto("agua");
        if (i.getCantidad() > 0) {
            gp.efectoSonido(6);
            if (sed >= 70) {
                i.setCantidad(i.getCantidad() - 1);
                mana = 100;
            } else {
                i.setCantidad(i.getCantidad() - 1);
                mana = mana + 30;
            }
            return true;
        } else {
            System.out.println("No queda mana");
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
            gp.efectoSonido(6);
            if (vida >= 70) {
                System.out.println("comiendo");
                i.setCantidad(i.getCantidad() - 1);
                vida = 100;
                return true;
            } else {
                System.out.println("comiendo");
                i.setCantidad(i.getCantidad() - 1);
                vida = vida + 30;
                return true;
            }
        } else {
            System.out.println("No queda comida");
            return false;
        }
    }

    /**
     * metodo boolean que sirve para equipar un objeto y modificar las stats
     * @param objeto
     * @return
     */
    public boolean equiparObjeto(String objeto) {
        Item obj = inventario.buscarObjeto(objeto);
        if (obj != null) {
            if (obj.getCantidad() > 0) {
                if (obj instanceof Armadura) {
                    Armadura objArmadura = (Armadura) obj;
                    switch (objeto) {
                        case "escudo", "escudo_oro", "talismanSecreto": {
                            if (objArmadura.getCantidad() > 0) {
                                setEscudo(objArmadura);
                                return true;
                            } else {
                                return false;
                            }
                        }
                        case "yelmo": {
                            if (obj.getCantidad() > 0) {
                                setCabeza(objArmadura);
                                return true;
                            } else {
                                return false;
                            }
                        }
                        case "peto": {
                            if (obj.getCantidad() > 0) {
                                setArmadura(objArmadura);
                                return true;
                            } else {
                                return false;
                            }
                        }
                    }
                } else if (obj instanceof Arma) {
                    Arma objArma = (Arma) inventario.buscarObjeto(objeto);
                    switch (objeto) {
                        case "espadaFuego": {
                            if (objArma.getCantidad() > 0) {
                                objArma.aumentar(this, 8);
                                setArma(objArma);
                                return true;
                            } else {
                                return false;
                            }
                        }
                        case "espada": {
                            if (objArma.getCantidad() > 0) {
                                objArma.aumentar(this, 8);
                                setArma(objArma);
                                return true;
                            } else {
                                return false;
                            }
                        }
                        case "varaMago": {
                            if (objArma.getCantidad() > 0) {
                                objArma.aumentar(this, 20);
                                setArma(objArma);
                                return true;
                            } else {
                                return false;
                            }
                        }
                    }
                }
            }
        }
        return false;
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
    @Override
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
        int monstruoIndex = gp.detectorDeColisiones.comprobaEntidad(this, gp.arrayEnemigos);
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
        colisionMonstruo(monstruoIndex);
        calcularEXPsubirNivel();
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
     * metodo heredado para atacar, es usado solo por jugador
     * @return
     */
    @Override
    public int atacar() {
        gp.efectoSonido(5);
        int dañoBase = arma.getDañoBase();
        switch (arma.getTipoataque()) {
            case ArmaPesada -> dañoAtaque = dañoBase * fuerza / 2 + (destreza / 10);
            case ArmaLigera -> dañoAtaque = dañoBase * destreza / 2 + (fuerza / 10);
            case ArmaMágica -> {
                dañoAtaque = dañoBase / 3 * magia;
                mana = mana - arma.getCoste();
            }
            case Desarmado -> {
                dañoAtaque=5;
            }
        }
        int random = UtilDiego.numRandomentero(1, 5);
        if (random <= 2) {
            dañoAtaque = dañoAtaque * 2;
            gp.getInterfaz().enseñarMensaje("CRITICO!!!! Has hecho un ataque de un total de " + dañoAtaque + " daño!");
        } else {
            gp.getInterfaz().enseñarMensaje("Ha hecho un ataque de un total de " + dañoAtaque + " daño!");
        }
        return dañoAtaque;
    }

    /**
     * tomar el sprite correspondiente al jugador
     *
     * @return
     */
    public BufferedImage obtenerImagenPlayer() {
        if (gp.estadoJuego == gp.menuInicio) {
            return sprite = plantillaSprite.getImg(numSprite, 2, gp.getTamañofinalBaldosa());
        }
        return switch (direccion) {
            case "izquierda", "arriba-izquierda", "abajo-izquierda" ->
                    sprite = plantillaSprite.getImg(2, numSprite, gp.getTamañofinalBaldosa());
            case "derecha", "arriba-derecha", "abajo-derecha" ->
                    sprite = plantillaSprite.getImg(3, numSprite, gp.getTamañofinalBaldosa());
            case "arriba" -> sprite = plantillaSprite.getImg(numSprite, 1, gp.getTamañofinalBaldosa());
            case "abajo" -> sprite = plantillaSprite.getImg(numSprite, 0, gp.getTamañofinalBaldosa());
            default -> sprite = plantillaSprite.getImg(1, 3, gp.getTamañofinalBaldosa());
        };
    }

    /**
     * metodo para recoger los objetos q esten en el suelo
     * @param index
     */
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
                    gp.arrayobjetos[index].setImagen(gp.arrayobjetos[index].plantillaSprite.getImg(9, 0, gp.getTamañofinalBaldosa()));
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
     * metodo que comprueba si hay colision con enemigo para iniciar estado de juego combate
     * @param index
     */
    public void colisionMonstruo(int index) {
        if (index != 999)
            if (gp.arrayEnemigos[index] != null) {
                //efecto sonido de combate
                gp.pararMusica();
                gp.efectoSonido(3);
                gp.getGraphics().setColor(Color.black);
                gp.getGraphics().fillRect(0, 0, gp.getWidth(), gp.getHeight());
                try {
                    Thread.sleep(2000); // Espera 2000 milisegundos (2 segundos)
                } catch (InterruptedException e) {
                    System.out.println("La espera fue interrumpida.");
                }
                gp.empezarMusica(4);
                gp.estadoJuego = gp.combate;
                gp.gc = new GestorCombate(this, gp.arrayEnemigos[index], gp);
            }
    }

    /**
     * metodo que randomiza la huida de una batalla
     * @return
     */
    public boolean huir() {
        int random = UtilDiego.numRandomentero(1, 5);
        if (random <= 2) {
            gp.estadoJuego = gp.continuar;
            gp.getInterfaz().enseñarMensaje("CONSEGUISTE HUIR!!");
            if (gp.getJugador().isOpcionHuir()) {
                gp.pararMusica();
                gp.empezarMusica(0);
            }
            return true;
        } else {
            gp.getInterfaz().enseñarMensaje("Fracaste al huir, pierdes el turno");
            return false;
        }
    }

    /**
     * metodo que dibuja el sprite en pantalla
     *
     * @param g2d
     * @throws IOException
     */
    @Override
    public void dibujar(Graphics2D g2d) {
        g2d.setColor(Color.black);
        g2d.fillRect((int) (x + 8), (int) (y + 16), 32, 32);
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

    public boolean isOpcionHuir() {
        return opcionHuir;
    }

    public void setOpcionHuir(boolean opcionHuir) {
        this.opcionHuir = opcionHuir;
    }

    public boolean isOpcionAtacar() {
        return opcionAtacar;
    }

    public void setOpcionAtacar(boolean opcionAtacar) {
        this.opcionAtacar = opcionAtacar;
    }
}
