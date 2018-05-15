package menjacnica.sistemskeoperacije;

import java.io.FileReader;
import java.util.LinkedList;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import menjacnica.Zapis;

public class SOVratiListuZapisa {

	/**
	 * Puni listu zapisa sa zapisima iz data/log.json fajla
	 * 
	 * @param zapisi predstavlja listu zapisa
	 */
	public static LinkedList<Zapis> izvrsi(LinkedList<Zapis> zapisi, String putanjaZaLogJson) {
		Gson gson = new GsonBuilder().setPrettyPrinting().create();

		JsonArray jsonNiz = new JsonArray();

		try (FileReader input = new FileReader(putanjaZaLogJson)) {
			jsonNiz = gson.fromJson(input, JsonArray.class);
		} catch (Exception e) {
			System.err.println("Greska, " + e.getMessage());
		}

		for (int i = 0; i < jsonNiz.size(); i++) {
			JsonObject jsonObject = (JsonObject) jsonNiz.get(i);

			Zapis zapis = new Zapis();

			try {
				zapis.setDatumVreme(jsonObject.get("datumVreme").getAsString());
				zapis.setIzValuta(jsonObject.get("izValuta").getAsString());
				zapis.setuValuta(jsonObject.get("uValuta").getAsString());
				zapis.setKurs(jsonObject.get("kurs").getAsDouble());
			} catch (Exception e) {
				System.err.println("Greska, " + e.getMessage());
			}

			zapisi.add(zapis);
		}
		
		return zapisi;
	}

}
