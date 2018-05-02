package menjacnica;

import menjacnica.sistemskeoperacije.SODodajZemlje;
import menjacnica.sistemskeoperacije.SOKonvertuj;

public class Menjacnica {
	public static void dodajZemlje(String url) {
		SODodajZemlje.izvrsi(url);
	}
	
	public static double getKurs(String from, String to) throws Exception {
		return SOKonvertuj.izvrsi(from, to);
	}
}
