package logika.sistemskeoperacije;

import java.io.FileWriter;
import java.util.LinkedList;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import klase.Zapis;

public class SOAzurirajZapise {

	/**
	 * Puni data/log.json fajl zapisima iz liste zapisi
	 * 
	 * @param zapisi predstavlja listu zapisa
	 */
	public static void izvrsi(LinkedList<Zapis> zapisi, String putanjaZaLogJson) {
		Gson gson = new GsonBuilder().setPrettyPrinting().create();

		JsonArray jsonNiz = new JsonArray();

		for (int i = 0; i < zapisi.size(); i++) {
			Zapis zapis = zapisi.get(i);

			JsonObject jsonZapis = new JsonObject();
			jsonZapis.addProperty("datumVreme", zapis.getDatumVreme());
			jsonZapis.addProperty("izValuta", zapis.getIzValuta());
			jsonZapis.addProperty("uValuta", zapis.getuValuta());
			jsonZapis.addProperty("kurs", zapis.getKurs());

			jsonNiz.add(jsonZapis);
		}

		try (FileWriter output = new FileWriter(putanjaZaLogJson)) {
			output.write(gson.toJson(jsonNiz));
		} catch (Exception e) {
			System.err.println("Greska, " + e.getMessage());
		}
	}

}
