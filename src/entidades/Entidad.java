package entidades;

import gamePanel.GamePanel;
import item.Item;
import item.armadura.Armadura;
import item.armas.Arma;
import item.armas.Elemento;
import item.armas.TipoAtaque;
import recursos.imagenes.Spritesheet;
import utiles.UtilDiego;

import java.awt.*;
import java.awt.image.BufferedImage;

import static item.armas.TipoAtaque.ArmaPesada;

public abstract class Entidad {
    protected GamePanel gp;
    //stats de movimiento
    protected double x;
    protected double y;
    protected boolean turno=false;
    protected int contadorAccion;
    //direccion tratada com ostring para un futuro switch
    protected String direccion;
    //contador para regular frames por segundo FPS
    protected int contadorUpdates;
    //cantidad de imagenes de la animacion
    protected int numSprite;
    //imagen(animación) de la entidad
    protected Spritesheet plantillaSprite;
    protected BufferedImage sprite;
    //stats de entidad
    protected Clase clase;
    protected Raza raza;
    protected String nombre;
    protected int vida;
    protected int vidaMax;
    protected int velocidad;
    protected int velocidadMax;
    protected int fuerza;
    protected int destreza;
    protected int magia;
    protected int nivel;
    protected int mana;
    protected int defensa;
    public Rectangle zonaDeColision;
    protected int zonaDeColisionDefectoX, zonaDeColisionDefectoY;
    protected boolean colision;
    protected double velocidadDiagonal;
    protected int dañoAtaque;
    protected Elemento elemento;
    //obj equipados
    protected Arma arma = new Arma("desarmado",1,0,0,1,null,TipoAtaque.ArmaLigera,0,0);
    protected Armadura armadura;
    protected Armadura escudo;
    protected Armadura cabeza;


    public Entidad(GamePanel gp) {
        this.vida = getVidaMax();
        this.velocidad=getVelocidadMax()/2;
        this.velocidadDiagonal = Math.hypot(this.velocidad,this.velocidad)/2;
        this.gp=gp;
    }


    /**
     * metodo para iniciar por raza las estats
     *
     * @param raza
     */
    public void iniciarRaza(Raza raza) {
        switch (raza) {
            case HUMANO -> {
                this.destreza = 10;
                this.fuerza = 10;
                this.velocidadMax = 7;
                this.magia = 10;
                this.vidaMax = 100;
                this.mana=50;
                this.defensa=1;
            }
            case ELFO -> {
                this.destreza = 20;
                this.fuerza = 5;
                this.velocidadMax = 8;
                this.magia = 15;
                this.vidaMax = 70;
                this.mana=50;
                this.defensa=0;
            }
            case ENANO -> {
                this.destreza = 5;
                this.fuerza = 20;
                this.velocidadMax = 4;
                this.magia = 5;
                this.vidaMax = 120;
                this.mana=50;
                this.defensa=3;
            }
            case ORCO -> {
                this.destreza = 15;
                this.fuerza = 15;
                this.velocidadMax = 6;
                this.magia = 5;
                this.vidaMax = 100;
                this.mana=50;
                this.defensa=2;
            }

        }
    }

    /**
     * metodo para modificar stats segun clase
     *
     * @param clase
     */
    public void iniciarClase(Clase clase) {
        switch (clase) {
            case MAGO -> {
                setMagia(getMagia() + 10);
                setVidaMax(getVidaMax() - 20);
                setMana(getMana()+20);
            }
            case PICARO -> {
                setDestreza(getDestreza() + 10);
                setFuerza(getFuerza() - 5);
            }
            case CLERIGO -> {
                setMagia(getMagia() + 5);
                setVidaMax(getVidaMax() + 10);
                setMana(getMana()+10);
                setDestreza(getDestreza() - 5);
                setDefensa(getDefensa()+1);
            }
            case GUERRERO -> {
                setFuerza(getFuerza() + 10);
                setVidaMax(getVidaMax() + 5);
                setMagia(0);
                setDefensa(getDefensa()+3);
            }
        }
    }

    /**
     * metodo para establecer una subida de nivel
     * @param nivel
     */
    public void estadisticasNivel(int nivel) {
        Clase c=getClase();
        //incremento proporcional al nivel
        int incremento = 5 * nivel;
        switch (c) {
            case MAGO -> {
                setMagia(getMagia() + incremento);
                setMana(getMana()+incremento);
                setDestreza(getDestreza() + (int) (incremento / 4));
                setFuerza(getFuerza() + (int) (incremento / 4));
            }
            case PICARO -> {
                setDestreza(getDestreza() + incremento);
                setFuerza(getFuerza() + (int) (incremento / 4));
                setMagia(getMagia() + (int) (incremento / 4));
                setMana(getMana()+(int) (incremento / 4));
            }
            case CLERIGO -> {
                setMagia(getMagia() + incremento);
                setMana(getMana()+(int) (incremento / 2));
                setDestreza(getDestreza() + (int) (incremento / 4));
                setFuerza(getFuerza() + (int) (incremento / 4));
            }
            case GUERRERO -> {
                setFuerza(getFuerza() + incremento);
                setDestreza(getDestreza() + (int) (incremento / 4));
                setMagia(getMagia() + (int) (incremento / 4));
                setMana(getMana()+(int) (incremento / 4));
            }

        }
        setVidaMax(getVidaMax() + (int) (incremento / 2));

    }


    //GETTERS Y SETTERS Y TOSTRING


    public int getDefensa() {
        return defensa;
    }

    public void setDefensa(int defensa) {
        this.defensa = defensa;
    }

    public int getMana() {
        return mana;
    }

    public void setMana(int mana) {
        this.mana = mana;
    }

    public void dibujar(Graphics2D g2d) {
        g2d.drawImage(sprite,(int)x,(int)y,gp.getTamañofinalBaldosa(),gp.getTamañofinalBaldosa(),null);
    }


    public void update() {
        establecerAccion();
        colision=false;
        gp.detectorDeColisiones.comprobarBaldosa(this);
        gp.detectorDeColisiones.comprobarObjetos(this,false);
        gp.detectorDeColisiones.comprobaEntidad(this,gp.arrayEnemigos);
        gp.detectorDeColisiones.comprobarJugador(this);
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
            }
        }
        contadorUpdates++;
        if (contadorUpdates % 8 == 0) {
            numSprite++;
        }
        if (numSprite >= 2) {
            numSprite = 0;
            contadorUpdates = 0;
        }
    }

    public void establecerAccion(){
        contadorAccion++;
        if (contadorAccion==120){
            int i =UtilDiego.numRandomentero(1,100);

            if (i <= 25) {
                direccion = "izquierda";
            }
            if (i>25&&i<=50){
                direccion = "derecha";
            }
            if (i>50&&i<=75){
                direccion = "arriba";
            }
            if (i>75&&i<=100){
                direccion = "abajo";
            }
            contadorAccion=0;
        }
    }

    @Override
    public String toString() {
        return "Entidad{" +
                "x=" + x +
                ", y=" + y +
                ", direccion='" + direccion + '\'' +
                ", contadorUpdates=" + contadorUpdates +
                ", numSprite=" + numSprite +
                ", sprite=" + sprite +
                ", clase=" + clase +
                ", raza=" + raza +
                ", nombre='" + nombre + '\'' +
                ", vida=" + vida +
                ", vidaMax=" + vidaMax +
                ", velocidad=" + velocidad +
                ", velocidadMax=" + velocidadMax +
                ", fuerza=" + fuerza +
                ", destreza=" + destreza +
                ", magia=" + magia +
                ", nivel=" + nivel +
                '}';
    }

    public int turno(Entidad monstruo, Entidad jugador) {
        //Cuando funcionen los consumibles estableceré el random
        // int random = UtilDiego.numRandomentero(0,1);
        int random=0;
        int accion=1;
        switch(random){
            case 0:
                jugador.recibirDaño(monstruo.atacar());
                accion=0;
                break;
            case 1:
                //consumibles
                accion=0;
                break;
        }
        return accion;
    }



    public int atacar() {
        gp.efectoSonido(5);
        int dañoBase = arma.getDañoBase();
        switch (arma.getTipoataque()){
            case ArmaPesada -> dañoAtaque = dañoBase *fuerza/2+(destreza/10);
            case ArmaLigera -> dañoAtaque = dañoBase *destreza/2+(fuerza/10);
            case ArmaMágica -> {
                dañoAtaque = dañoBase/3 *magia;
                mana=mana - arma.getCoste();
            }
        }
    return dañoAtaque;
    }

    public int recibirDaño(int dañoTotal) {
       int vida = dañoTotal - defensa;
       if (vida > 0) {
           this.vida-=vida;
       }else{
           vida=0;
       }
       return vida;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    public double getX() {
        return x;
    }

    public void setX (double x) {
        this.x = x;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public int getContadorUpdates() {
        return contadorUpdates;
    }

    public void setContadorUpdates(int contadorUpdates) {
        this.contadorUpdates = contadorUpdates;
    }

    public int getNumSprite() {
        return numSprite;
    }

    public void setNumSprite(int numSprite) {
        this.numSprite = numSprite;
    }

    public void setSprite(BufferedImage sprite) {
        this.sprite = sprite;
    }

    public Clase getClase() {
        return clase;
    }

    public void setClase(Clase clase) {
        this.clase = clase;
    }

    public Raza getRaza() {
        return raza;
    }

    public void setRaza(Raza raza) {
        this.raza = raza;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getVida() {
        return vida;
    }

    public void setVida(int vida) {
        this.vida = vida;
    }

    public int getVidaMax() {
        return vidaMax;
    }

    public void setVidaMax(int vidaMax) {
        this.vidaMax = vidaMax;
    }

    public int getVelocidad() {
        return velocidad;
    }

    public void setVelocidad(int velocidad) {
        this.velocidad = velocidad;
    }

    public int getVelocidadMax() {
        return velocidadMax;
    }

    public void setVelocidadMax(int velocidadMax) {
        this.velocidadMax = velocidadMax;
    }

    public int getFuerza() {
        return fuerza;
    }

    public void setFuerza(int fuerza) {
        this.fuerza = fuerza;
    }

    public int getDestreza() {
        return destreza;
    }

    public void setDestreza(int destreza) {
        this.destreza = destreza;
    }

    public int getMagia() {
        return magia;
    }

    public void setMagia(int magia) {
        this.magia = magia;
    }

    public int getNivel() {
        return nivel;
    }

    public void setNivel(int nivel) {
        this.nivel = nivel;
    }

    public Item getArmadura() {
        return armadura;
    }

    public Rectangle getZonaDeColision() {
        return zonaDeColision;
    }

    public void setZonaDeColision(Rectangle zonaDeColision) {
        this.zonaDeColision = zonaDeColision;
    }

    public boolean isColision() {
        return colision;
    }

    public void setColision(boolean colision) {
        this.colision = colision;
    }

    public double getVelocidadDiagonal() {
        return velocidadDiagonal;
    }

    public void setVelocidadDiagonal(double velocidadDiagonal) {
        this.velocidadDiagonal = velocidadDiagonal;
    }

    public int getZonaDeColisionDefectoX() {
        return zonaDeColisionDefectoX;
    }

    public void setZonaDeColisionDefectoX(int zonaDeColisionDefectoX) {
        this.zonaDeColisionDefectoX = zonaDeColisionDefectoX;
    }

    public int getZonaDeColisionDefectoY() {
        return zonaDeColisionDefectoY;
    }

    public void setZonaDeColisionDefectoY(int zonaDeColisionDefectoY) {

    }

    public Spritesheet getPlantillaSprite() {
        return plantillaSprite;
    }

    public void setPlantillaSprite(Spritesheet plantillaSprite) {
        this.plantillaSprite = plantillaSprite;
    }

    public BufferedImage getSprite() {
        return sprite;
    }

    public int getDañoAtaque() {
        return dañoAtaque;
    }

    public void setDañoAtaque(int dañoAtaque) {
        this.dañoAtaque = dañoAtaque;
    }

    public boolean isTurno() {
        return turno;
    }

    public void setTurno(boolean turno) {
        this.turno = turno;
    }

    public Elemento getElemento() {
        return elemento;
    }

    public void setElemento(Elemento elemento) {
        this.elemento = elemento;
    }

    public Arma getArma() {
        return arma;
    }

    public void setArma(Arma arma) {
        this.arma = arma;
    }

    public void setArmadura(Armadura armadura){
        this.armadura=armadura;
    };

    public Armadura getEscudo() {
        return escudo;
    }

    public void setEscudo(Armadura escudo) {
        this.escudo = escudo;
    }

    public Armadura getCabeza() {
        return cabeza;
    }

    public void setCabeza(Armadura cabeza) {
        this.cabeza = cabeza;
    }
}

