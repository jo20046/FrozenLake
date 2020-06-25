//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package frozenlake;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Random;

public class See {
    private String id;
    private frozenlake.Zustand[][] see;
    private Koordinate spielfigur;
    private Koordinate start;
    private Koordinate ziel;
    private int groesse;
    private Random zufall = new Random();

    public See(String id, int groesse, Koordinate start, Koordinate ziel) {
        this.id = id;
        this.start = start;
        this.ziel = ziel;
        groesse = Math.max(4, groesse);
        this.groesse = groesse;
        this.see = new frozenlake.Zustand[groesse][groesse];

        for(int zeile = 0; zeile < groesse; ++zeile) {
            for(int spalte = 0; spalte < groesse; ++spalte) {
                this.see[zeile][spalte] = frozenlake.Zustand.Unbekannt;
            }
        }

        this.see[start.getZeile()][start.getSpalte()] = frozenlake.Zustand.Start;
        this.see[ziel.getZeile()][ziel.getSpalte()] = frozenlake.Zustand.Ziel;
        this.spielfigur = new Koordinate(start.getZeile(), start.getSpalte());
    }

    public void wegErzeugen() {
        this.wegGenerieren(this.start.getZeile(), this.start.getSpalte());
        this.seeVervollstaendigen();
    }

    public String getId() {
        return this.id;
    }

    public int getGroesse() {
        return this.groesse;
    }

    public frozenlake.Zustand geheNach(Richtung r) throws RuntimeException {
        int neueZeile = this.spielfigur.getZeile() + r.deltaZ();
        int neueSpalte = this.spielfigur.getSpalte() + r.deltaS();
        if (neueZeile >= 0 && neueZeile < this.groesse && neueSpalte >= 0 && neueSpalte < this.groesse) {
            this.spielfigur.setZeile(neueZeile);
            this.spielfigur.setSpalte(neueSpalte);
            this.see[neueZeile][neueSpalte] = this.see[neueZeile][neueSpalte].sichtbar();
            return this.see[neueZeile][neueSpalte];
        } else {
            throw new RuntimeException("IPfadfinder verlässt erlaubten Bereich!");
        }
    }

    public void anzeigen() {
        for(int zeile = 0; zeile < this.groesse; ++zeile) {
            for(int spalte = 0; spalte < this.groesse; ++spalte) {
                if (this.spielfigur.getZeile() == zeile && this.spielfigur.getSpalte() == spalte) {
                    System.out.print("P ");
                } else {
                    System.out.print(this.see[zeile][spalte].ausgabe() + " ");
                }
            }

            System.out.println();
        }

    }

    public void neustartSpielfigur() {
        this.spielfigur.setZeile(this.start.getZeile());
        this.spielfigur.setSpalte(this.start.getSpalte());

        for(int zeile = 0; zeile < this.groesse; ++zeile) {
            for(int spalte = 0; spalte < this.groesse; ++spalte) {
                this.see[zeile][spalte] = this.see[zeile][spalte].unsichtbar();
            }
        }

    }

    public void speichereSee(String filepath) throws IOException {
        PrintWriter pw = new PrintWriter(filepath + this.id);
        pw.println(this.start.getZeile());
        pw.println(this.start.getSpalte());
        pw.println(this.ziel.getZeile());
        pw.println(this.ziel.getSpalte());

        for(int zeile = 0; zeile < this.groesse; ++zeile) {
            for(int spalte = 0; spalte < this.groesse; ++spalte) {
                pw.print(this.see[zeile][spalte]);
            }

            pw.println();
        }

        pw.close();
    }

    public Koordinate spielerPosition() {
        return this.spielfigur;
    }

    public frozenlake.Zustand zustandAn(Koordinate k) {
        return this.see[k.getZeile()][k.getSpalte()];
    }

    public static See ladeSee(String verzeichnis, String id) throws IOException {
        FileReader reader = new FileReader(verzeichnis + id);
        BufferedReader inBuffer = new BufferedReader(reader);
        int startZeile = Integer.parseInt(inBuffer.readLine());
        int startSpalte = Integer.parseInt(inBuffer.readLine());
        int zielZeile = Integer.parseInt(inBuffer.readLine());
        int zielSpalte = Integer.parseInt(inBuffer.readLine());
        String fuenfteZeile = inBuffer.readLine();
        int groesse = fuenfteZeile.length();
        See ergebnis = new See(id, groesse, new Koordinate(startZeile, startSpalte), new Koordinate(zielZeile, zielSpalte));
        ergebnis.liesZeile(0, fuenfteZeile);

        for(int zeile = 1; zeile < groesse; ++zeile) {
            ergebnis.liesZeile(zeile, inBuffer.readLine());
        }

        reader.close();
        return ergebnis;
    }

    private void liesZeile(int zeile, String zk) {
        for(int spalte = 0; spalte < this.groesse; ++spalte) {
            this.see[zeile][spalte] = frozenlake.Zustand.fromCharacter(zk.charAt(spalte));
        }

    }

    private boolean wegGenerieren(int startZeile, int startSpalte) {
        if ((startZeile != this.groesse - 2 || startSpalte != this.groesse - 1) && (startZeile != this.groesse - 1 || startSpalte != this.groesse - 2)) {
            ArrayList<Richtung> versucht = new ArrayList();

            for(int versuch = 1; versuch <= 4; ++versuch) {
                Richtung neu;
                do {
                    neu = Richtung.zufaelligeRichtung();
                } while(versucht.contains(neu));

                versucht.add(neu);
                int neueZeile = startZeile + neu.deltaZ();
                int neueSpalte = startSpalte + neu.deltaS();
                if (neueZeile >= 0 && neueSpalte >= 0 && neueZeile != this.groesse && neueSpalte != this.groesse && this.see[neueZeile][neueSpalte] == frozenlake.Zustand.Unbekannt) {
                    this.see[neueZeile][neueSpalte] = frozenlake.Zustand.UEis;
                    if (this.wegGenerieren(neueZeile, neueSpalte)) {
                        return true;
                    }

                    this.see[neueZeile][neueSpalte] = frozenlake.Zustand.Unbekannt;
                }
            }

            return false;
        } else {
            return true;
        }
    }

    private void seeVervollstaendigen() {
        for(int zeile = 0; zeile < this.groesse; ++zeile) {
            for(int spalte = 0; spalte < this.groesse; ++spalte) {
                if ((zeile != 0 || spalte != 0) && (zeile != this.groesse - 1 || spalte != this.groesse - 1) && this.see[zeile][spalte] == frozenlake.Zustand.Unbekannt) {
                    if (this.zufall.nextInt(10) < 7) {
                        this.see[zeile][spalte] = frozenlake.Zustand.UWasser;
                    } else {
                        this.see[zeile][spalte] = frozenlake.Zustand.UEis;
                    }
                }
            }
        }

    }
}
