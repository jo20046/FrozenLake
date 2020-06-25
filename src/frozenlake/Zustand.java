//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package frozenlake;

public enum Zustand {
    Start,
    Ziel,
    Eis,
    Wasser,
    UEis,
    UWasser,
    Unbekannt;

    private Zustand() {
    }



    public String toString() {
        switch(Zustand.values()[this.ordinal()]) {
            case Start:
                return "S";
            case Ziel:
                return "Z";
            case Eis:
            case UEis:
                return "E";
            case Wasser:
            case UWasser:
                return "W";
            default:
                return "X";
        }
    }

    public String ausgabe() {
        switch(Zustand.values()[this.ordinal()]) {
            case Start:
                return "S";
            case Ziel:
                return "Z";
            case Eis:
            case UEis:
                return "E";
            case Wasser:
            case UWasser:
                return "W";
            default:
                return "X";
        }
    }

    public Zustand sichtbar() {
        switch(Zustand.values()[this.ordinal()]) {
            case UEis:
                return Eis;
            case UWasser:
                return Wasser;
            default:
                return this;
        }
    }

    public Zustand unsichtbar() {
        switch(Zustand.values()[this.ordinal()]) {
            case Eis:
                return UEis;
            case Wasser:
                return UWasser;
            default:
                return this;
        }
    }

    public static Zustand fromCharacter(char c) {
        switch(c) {
            case 'E':
                return UEis;
            case 'S':
                return Start;
            case 'W':
                return UWasser;
            case 'Z':
                return Ziel;
            default:
                return Unbekannt;
        }
    }
}
