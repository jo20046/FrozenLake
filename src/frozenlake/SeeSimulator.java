package frozenlake;

import frozenlake.pfadfinder.IPfadfinder;

public class SeeSimulator {
	
	public static void main(String[] args) {


		System.out.println(Zustand.Start.toString());


		int anzahlSchritte = 0;
		try {

		IPfadfinder joe = new frozenlake.pfadfinder.mustergruppe.Pfadfinder(); //Hier muss eine konkrete Implementierung hin!

		See testsee = new See("Testsee",10, new Koordinate(0,0), new Koordinate(9,9));
		testsee.wegErzeugen();
		testsee.speichereSee("Testsee");


		//Trainieren mit QMatrix, ohne NN, OffPolicy
		joe.lerneSee(testsee, true, false, true);

		//Testdurchlauf mit dem trainierten IPfadfinder
		joe.starteUeberquerung(testsee, true, false, true);
		testsee.anzeigen();

		Zustand naechsterZustand=Zustand.Start;
		do {
			Richtung r = joe.naechsterSchritt(naechsterZustand);
			System.out.println("Gehe " + r);
			naechsterZustand = testsee.geheNach(r);
			anzahlSchritte++;
			testsee.anzeigen();
		} while (!((naechsterZustand==Zustand.Ziel) || (naechsterZustand==Zustand.Wasser)));

		if (naechsterZustand==Zustand.Ziel) {
			System.out.println("Sie haben Ihr Ziel erreicht! Anzahl Schritte: " + anzahlSchritte);
		}
		else {
			System.out.println("Sie sind im Wasser gelandet. Anzahl Schritte bis dahin: " + anzahlSchritte);
		}
		}
		catch (Exception ex) {
			System.err.println("Exception nach " + anzahlSchritte + " Schritten!");
			ex.printStackTrace();
		}
	}
}
