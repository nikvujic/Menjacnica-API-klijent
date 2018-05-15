package menjacnica;

import java.util.LinkedList;

import menjacnica.sistemskeoperacije.SOAzurirajZapise;
import menjacnica.sistemskeoperacije.SODodajZemlje;
import menjacnica.sistemskeoperacije.SOKonvertuj;
import menjacnica.sistemskeoperacije.SOVratiListuZapisa;

public class Menjacnica {
	private static final String putanjaZaLogJson = "data/log.json";
	private static final String linkZaZemlje = "http://free.currencyconverterapi.com/api/v3/countries";
	private static LinkedList<Zapis> zapisi = new LinkedList<Zapis>();
	
	public static void dodajZemlje() {
		SODodajZemlje.izvrsi(linkZaZemlje);
	}
	
	public static double getKurs(String from, String to) throws Exception {
		return SOKonvertuj.izvrsi(from, to);
	}

	public static void azurirajZapise(LinkedList<Zapis> zapisi) {
		SOAzurirajZapise.izvrsi(zapisi, putanjaZaLogJson);
	}

	public static LinkedList<Zapis> vratiListuZapisa() {
		return SOVratiListuZapisa.izvrsi(zapisi, putanjaZaLogJson);
	}
}
