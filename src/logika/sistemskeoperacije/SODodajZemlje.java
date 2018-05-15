package logika.sistemskeoperacije;

import java.io.FileWriter;
import java.io.IOException;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;

import logika.Menjacnica;

public class SODodajZemlje {
	public static void izvrsi(String url) {
		try {
			String content = Menjacnica.getContent(url);

			Gson gson = new GsonBuilder().setPrettyPrinting().create();
			JsonObject jsonData = gson.fromJson(content, JsonObject.class);

			try (FileWriter output = new FileWriter("data/countries.json")) {
				output.write(gson.toJson(jsonData));
			} catch (Exception e) {
				e.printStackTrace();
			}

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
