package entidad;

import java.awt.*;

public class Entidad {
    protected int vida;
    protected int armadura;
    protected int magia;
    protected int mana;
    protected int destreza;
    protected Raza raza;
    protected Clase calse;
    protected int dx;
    private int dy;
    private int y;
    private int x;
    private Image imagen;

    public Entidad(int vida, int armadura, Raza raza, Clase calse,int magia,int mana, int destreza) {
        this.vida = vida;
        this.armadura = armadura;
        this.raza = raza;
        this.calse = calse;
        this.magia=magia;
        this.mana=mana;
        this.destreza=destreza;
    }
    public Entidad(){

    }

//    public Entidad() {
//    }

    @Override
    public String toString() {
        return "Entidad{" +
                "vida=" + vida +
                ", defensa=" + armadura +
                ", raza=" + raza +
                ", calse=" + calse +
                '}';
    }

    //getter y setter

    public int getVida() {
        return vida;
    }

    public void setVida(int vida) {
        this.vida = vida;
    }

    public int getArmadura() {
        return armadura;
    }

    public void setArmadura(int armadura) {
        this.armadura = armadura;
    }

    public int getMagia() {
        return magia;
    }

    public void setMagia(int magia) {
        this.magia = magia;
    }

    public int getMana() {
        return mana;
    }

    public void setMana(int mana) {
        this.mana = mana;
    }

    public int getDestreza() {
        return destreza;
    }

    public void setDestreza(int destreza) {
        this.destreza = destreza;
    }

    public Raza getRaza() {
        return raza;
    }

    public void setRaza(Raza raza) {
        this.raza = raza;
    }

    public Clase getCalse() {
        return calse;
    }

    public void setCalse(Clase calse) {
        this.calse = calse;
    }

    public int getDx() {
        return dx;
    }

    public void setDx(int dx) {
        this.dx = dx;
    }
}
