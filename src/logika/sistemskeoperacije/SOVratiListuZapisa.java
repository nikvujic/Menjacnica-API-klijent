package logika.sistemskeoperacije;

import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.LinkedList;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import klase.Zapis;

public class SOVratiListuZapisa {

	/**
	 * Puni listu zapisa sa zapisima iz data/log.json fajla
	 * 
	 * @param zapisi predstavlja listu zapisa
	 */
	public static LinkedList<Zapis> izvrsi(String putanjaZaLogJson) {
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		LinkedList<Zapis> zapisi = new LinkedList<Zapis>

		JsonArray jsonNiz = new JsonArray();

		try (FileReader input = new FileReader(putanjaZaLogJson)) {
			jsonNiz = gson.fromJson(input, JsonArray.class);
		} catch (Exception e) {
			try (PrintWriter output = new PrintWriter(new BufferedWriter(new FileWriter(putanjaZaLogJson)));) {
				output.print("[]");  //pravljenje novog json fajla sa praznom lisom
			} catch (Exception e1) {
				e1.printStackTrace();
			}
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
