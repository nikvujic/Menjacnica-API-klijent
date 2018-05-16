package logika.sistemskeoperacije;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;

import logika.Menjacnica;

public class SODodajZemlje {
	private final static String linkZaZemlje = "http://free.currencyconverterapi.com/api/v3/countries";
	private final static String lokacijaZemljeJson = "data/countries.json";

	public static void izvrsi() {
		try {
			String content = Menjacnica.getContent(linkZaZemlje);

			Gson gson = new GsonBuilder().setPrettyPrinting().create();
			JsonObject jsonData = gson.fromJson(content, JsonObject.class);

			try (FileWriter output = new FileWriter(lokacijaZemljeJson)) {
				output.write(gson.toJson(jsonData));
			} catch (Exception e) {
				try (PrintWriter output = new PrintWriter(new BufferedWriter(new FileWriter(lokacijaZemljeJson)));) {
					output.print("[]");
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
