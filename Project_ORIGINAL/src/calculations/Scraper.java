package calculations;

import java.io.IOException;
import java.net.MalformedURLException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

public class Scraper {
	static String userAddress = "Verkių g. 36, Vilnius";

	public static void main(String[] args) throws IOException {

		// Creates DB
		// readNames();//reads data from url and adds to DB
		// storeAddressInfo();//transforms address to lat and lon and stores in DB
		// storeMinOrderValue();

		
		//SelectApp sapp= new SelectApp(); 
		//System.out.println(sapp.getRandomRestaurant(3, 3.8));
		

		//storeRatingValue();

		// userAddress="";

		// Takes too long ~ 25s
		// Finds distance from each restaurant and puts in DB
		try {
			
			final long startTime = System.currentTimeMillis();

			DistanceCalculator.TESTupdateDistancesFromAddress(userAddress);

			final long endTime = System.currentTimeMillis();

			System.out.println("Total execution time: " + (endTime - startTime));
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		
		
		//Finds distance from each
		// restaurant and puts in DB

		/*
		 * Finds min distance SelectApp sapp= new SelectApp();
		 * sapp.getMinDistance();//Finds min distance
		 */

	}

	private static void readNames() throws IOException {
		InsertApp iapp = new InsertApp();
		// Reads restaurant names, addresses and delivery times and puts in database
		String url;

		for (int i = 1; i < 20; i++) {
			url = "https://www.lekste.lt/page/list/c7545b3ae9c82cc4f9b514a0e1f87c20?sort=distance&page=" + i;
			Document page = Jsoup.connect(url).userAgent("Jsoup Scraper").get();
			// Selects restaurant container
			String restaurantSelector = "div.article-container";

			for (org.jsoup.nodes.Element e : page.select(restaurantSelector)) {
				String name = e.select("h2.article-title").text();
				String adress = e.select("div.article-address").text();
				String deliveryTime = e.select("section.article-delivery-section-time").text();
				// add to database
				// TODO add other atributes
				iapp.insert(name, adress);
			}
		}

		System.out.println("Done");
	}

	private static void storeAddressInfo() throws MalformedURLException, IOException {
		UpdateApp uapp = new UpdateApp();
		SelectApp sapp = new SelectApp();
		AddressConverter ac = new AddressConverter();
		// goes through all addresses in DB

		for (int i = 1; i < 151; i++) {
			Restaurant rest = ac.getCoordinates(sapp.getAddress(i));
			uapp.updateCoordinates(i, rest.latitude, rest.longitude);
			System.out.println("Number: " + i);
			System.out.println(rest.latitude);
			System.out.println(rest.longitude);
		}

	}

	private static void storeMinOrderValue() throws IOException {
		UpdateApp uapp = new UpdateApp();
		// Reads restaurant names, addresses and delivery times and puts in database
		String url;

		for (int i = 1; i < 20; i++) {
			url = "https://www.lekste.lt/page/list/c7545b3ae9c82cc4f9b514a0e1f87c20?sort=distance&page=" + i;
			Document page = Jsoup.connect(url).userAgent("Jsoup Scraper").get();
			// Selects restaurant container
			String restaurantSelector = "div.article-container";

			for (org.jsoup.nodes.Element e : page.select(restaurantSelector)) {
				String name = e.select("h2.article-title").text();
				// String adress = e.select("div.article-address").text();
				// String deliveryTime =
				// e.select("section.article-delivery-section-time").text();

				String minOrdValue = e.select("section.min-order").text();// from 2nd character
				minOrdValue = minOrdValue.replace(',', '.');
				double doubleValue = 100;
				if (minOrdValue.length() > 3) {
					minOrdValue = minOrdValue.substring(2);
					doubleValue = Double.parseDouble(minOrdValue);
				}
				System.out.println(doubleValue);
				// add to database
				uapp.updateMinOrderValue(name, doubleValue);
			}
		}

	}

	private static void storeRatingValue() throws IOException {
		UpdateApp uapp = new UpdateApp();
		// Reads restaurant names, addresses and delivery times and puts in database
		String url;

		for (int i = 1; i < 20; i++) {
			url = "https://www.lekste.lt/page/list/c7545b3ae9c82cc4f9b514a0e1f87c20?sort=distance&page=" + i;
			Document page = Jsoup.connect(url).userAgent("Jsoup Scraper").get();
			// Selects restaurant container
			String restaurantSelector = "div.article-container";

			for (org.jsoup.nodes.Element e : page.select(restaurantSelector)) {
				String name = e.select("h2.article-title").text();

				String ratingValue = e.select("div.article-review-count").text();
				double doubleValue = 0;
				if (ratingValue.length() > 3) {
					ratingValue = ratingValue.substring(0,ratingValue.indexOf('/'));
					doubleValue = Double.parseDouble(ratingValue);
				}
				System.out.println(doubleValue);
				// add to database
				uapp.updateRatingValue(name, doubleValue);
			}
		}

	}
}
