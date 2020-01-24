package calculations;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class AddressConverter {

	public static Restaurant getCoordinates(String address) throws MalformedURLException, IOException {
		UrlBuilder ub = new UrlBuilder();
		Restaurant rest = new Restaurant();
		// Connect to the URL using java's native library

		String sURL = ub.getUrl(address);
		// Connect to the URL using java's native library
		URL url = new URL(sURL);
		URLConnection request = url.openConnection();
		request.connect();

		// Convert to a JSON object to print data
		JsonParser jp = new JsonParser(); // from gson
		JsonElement root = jp.parse(new InputStreamReader((InputStream) request.getContent())); // Convert the input
																								// stream to a json
																								// element
		if (root instanceof JsonArray) {

			rest.latitude = root.getAsJsonArray().get(0).getAsJsonObject().get("lat").getAsDouble();
			rest.longitude = root.getAsJsonArray().get(0).getAsJsonObject().get("lon").getAsDouble();

		} else if (root instanceof JsonObject) {
			System.out.println("Root is JsonObject");
		}
		return rest;
	}

}
