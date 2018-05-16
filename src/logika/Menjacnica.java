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
	private static final String putanjaZaLogJson = "data/log.json"; // TODO: da li staviti u sistemske opracije, jer
																	// mora na dva mesta?
	/**
	 * Azurura zemlje.json fajl
	 */
	public void dodajZemlje() {
		SODodajZemlje.izvrsi();
	}

	public static double getKurs(String from, String to) throws Exception {
		return SOKonvertuj.izvrsi(from, to);
	}

	/**
	 * Azurira log.json fajl po prosledjenoj listi
	 * 
	 * @param zapisi
	 */
	public static void azurirajZapise(LinkedList<Zapis> zapisi) {
		SOAzurirajZapise.izvrsi(zapisi, putanjaZaLogJson);
	}

	/**
	 * Vraca listu zapisa citajuci ih iz log.json fajla
	 * 
	 * @return lista sa svim zapisima iz fajla
	 */
	public static LinkedList<Zapis> vratiListuZapisa() {
		return SOVratiListuZapisa.izvrsi(putanjaZaLogJson);
	}

	public static String getContent(String url) throws IOException {
		return SOGetContent.izvrsi(url);
	}
}
