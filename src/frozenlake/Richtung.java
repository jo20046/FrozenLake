//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package frozenlake;

import java.util.Random;

public enum Richtung {
    HOCH,
    RECHTS,
    RUNTER,
    LINKS;

    private static Random zufall = new Random();

    private Richtung() {
    }

    public int deltaZ() {
        switch(Richtung.values()[this.ordinal()]) {
            case HOCH:
                return -1;
            case RECHTS:
            case LINKS:
            default:
                return 0;
            case RUNTER:
                return 1;
        }
    }

    public int deltaS() {
        switch(Richtung.values()[this.ordinal()]) {
            case RECHTS:
                return 1;
            case HOCH:
            case RUNTER:
            default:
                return 0;
            case LINKS:
                return -1;
        }
    }

    public static Richtung zufaelligeRichtung() {
        int wahl = zufall.nextInt(10);
        if (wahl < 4) {
            return RUNTER;
        } else if (wahl < 8) {
            return RECHTS;
        } else {
            return wahl < 9 ? HOCH : LINKS;
        }
    }
}
