package logika;

import java.io.IOException;

import java.util.LinkedList;

import klase.Zapis;
import logika.sistemskeoperacije.SOAzurirajZapise;
import logika.sistemskeoperacije.SODodajZemlje;
import logika.sistemskeoperacije.SOGetContent;
import logika.sistemskeoperacije.SOKonvertuj;
import logika.sistemskeoperacije.SOVratiListuZapisa;

/**
 * Klasa menjacnica sadrzi pozive sistekskih operacija
 * 
 * @author Nikola Vujic
 *
 */
public class Menjacnica {
	private static final String putanjaZaLogJson = "data/log.json";
	private static final String lokacijaZemljeJson = "data/countries.json";
	private static final String linkZaZemlje = "http://free.currencyconverterapi.com/api/v3/countries";
	private static LinkedList<Zapis> zapisi = new LinkedList<Zapis>();
	
	public static void dodajZemlje() {
		SODodajZemlje.izvrsi(linkZaZemlje, lokacijaZemljeJson);
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
	
	public static String getContent(String url) throws IOException {
		return SOGetContent.izvrsi(url);
	}
}
