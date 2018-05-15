package menjacnica;

import java.util.LinkedList;

import menjacnica.sistemskeoperacije.SOAzurirajZapise;
import menjacnica.sistemskeoperacije.SODodajZemlje;
import menjacnica.sistemskeoperacije.SOKonvertuj;
import menjacnica.sistemskeoperacije.SONapuniListuZapisa;

public class Menjacnica {
	public static void dodajZemlje(String url) {
		SODodajZemlje.izvrsi(url);
	}
	
	public static double getKurs(String from, String to) throws Exception {
		return SOKonvertuj.izvrsi(from, to);
	}

	public static void azurirajZapise(LinkedList<Zapis> zapisi) {
		SOAzurirajZapise.izvrsi(zapisi);
	}

	public static void napuniListuZapisa(LinkedList<Zapis> zapisi) {
		SONapuniListuZapisa.izvrsi(zapisi);
	}
}
