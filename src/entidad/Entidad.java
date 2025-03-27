package entidad;

public class Entidad {
    protected int vida;
    protected int armadura;
    protected int magia;
    protected int mana;
    protected int destreza;
    protected Raza raza;
    protected Clase calse;

    public Entidad(int vida, int armadura, Raza raza, Clase calse,int magia,int mana, int destreza) {
        this.vida = vida;
        this.armadura = armadura;
        this.raza = raza;
        this.calse = calse;
        this.magia=magia;
        this.mana=mana;
        this.destreza=destreza;
    }

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

    public int getDefensa() {
        return armadura;
    }

    public void setArmadura(int armadura) {
        this.armadura = armadura;
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
}
