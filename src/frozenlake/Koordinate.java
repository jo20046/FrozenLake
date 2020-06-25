//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package frozenlake;

public class Koordinate {
    private int zeile;
    private int spalte;

    public Koordinate(int zeile, int spalte) {
        this.zeile = zeile;
        this.spalte = spalte;
    }

    public int getZeile() {
        return this.zeile;
    }

    public void setZeile(int zeile) {
        this.zeile = zeile;
    }

    public int getSpalte() {
        return this.spalte;
    }

    public void setSpalte(int spalte) {
        this.spalte = spalte;
    }

    public String toString() {
        return "Koordinate[" + this.zeile + ", " + this.spalte + "]";
    }
}
