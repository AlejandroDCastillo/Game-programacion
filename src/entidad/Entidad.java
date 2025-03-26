package entidad;

public class Entidad {
    protected int vida;
    protected int defensa;
    protected Raza raza;
    protected Clase calse;

    public Entidad(int vida, int defensa, Raza raza, Clase calse) {
        this.vida = vida;
        this.defensa = defensa;
        this.raza = raza;
        this.calse = calse;
    }

    @Override
    public String toString() {
        return "Entidad{" +
                "vida=" + vida +
                ", defensa=" + defensa +
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
        return defensa;
    }

    public void setDefensa(int defensa) {
        this.defensa = defensa;
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
