package calculations;

//URL builder to insert address and transform to JSON format
public class UrlBuilder {


	public String getUrl(String address) {

		StringBuffer query;
		String[] split = address.split(" ");
		query = new StringBuffer();

		query.append("https://nominatim.openstreetmap.org/search?q=");

		if (split.length == 0) {
			return null;
		}

		for (int i = 0; i < split.length; i++) {
			query.append(split[i]);
			if (i < (split.length - 1)) {
				query.append("+");
			}
		}
		query.append("&format=json&addressdetails=1");


		String url = query.toString();

		return url;
	}
}
