package menjacnica.sistemskeoperacije;

import java.io.FileWriter;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import menjacnica.service.URLConnectionUtil;

public class SOKonvertuj {

	public static double izvrsi(String from, String to) throws Exception {
		String conversion = from + "_" + to;
		String url = "http://free.currencyconverterapi.com/api/v3/convert?q=" + conversion;
		
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		
		String content = URLConnectionUtil.getContent(url);
		JsonObject jsonData = gson.fromJson(content, JsonObject.class);	
		
		JsonElement jsonQuery = jsonData.get("query");
		JsonObject querryObjectJson = jsonQuery.getAsJsonObject();
		
		if (querryObjectJson.get("count").getAsInt() == 0)
			throw new Exception("Ne postoje podaci o konverziji");
		
		JsonElement jsonResults = jsonData.get("results");
		JsonObject resultsObjectJson = jsonResults.getAsJsonObject();
		
		JsonElement jsonConversion = resultsObjectJson.get(conversion);
		JsonObject conversionObjectJson = jsonConversion.getAsJsonObject();
		
		return conversionObjectJson.get("val").getAsDouble();
	}

}
