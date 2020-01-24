package calculations;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.awt.Label;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class SelectApp {

	ConnectionApp co = new ConnectionApp();
	Connection conn = co.connect();

	public String getAddress(int restaurantID) {
		String sql = "SELECT Address FROM Restaurants WHERE RestaurantID = ?";

		try (PreparedStatement pstmt = conn.prepareStatement(sql)) {

			// set the value
			pstmt.setInt(1, restaurantID);
			//
			ResultSet rs = pstmt.executeQuery();
			Restaurant rest = null;
			while (rs.next()) {
				rest = new Restaurant();

				rest.address = rs.getString(1);
			}
			return rest.address;
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			return null;
		}

	}

	public double readLat(int restaurantID) {
		String sql = "SELECT Latitude FROM Restaurants WHERE RestaurantID = ?";
		try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
			// set the value
			pstmt.setInt(1, restaurantID);
			ResultSet rs = pstmt.executeQuery();
			Restaurant rest = null;
			while (rs.next()) {
				rest = new Restaurant();

				rest.latitude = rs.getDouble(1);
			}
			return rest.latitude;
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			return (Double) null;
		}

	}

	public double readLon(int restaurantID) {
		String sql = "SELECT Longitude FROM Restaurants WHERE RestaurantID = ?";
		try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
			// set the value
			pstmt.setInt(1, restaurantID);
			ResultSet rs = pstmt.executeQuery();
			Restaurant rest = null;
			while (rs.next()) {
				rest = new Restaurant();

				rest.longitude = rs.getDouble(1);
			}
			return rest.longitude;
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			return (Double) null;
		}

	}

	public Restaurant readLatLon(int restaurantID) {
		String sql = "SELECT Latitude,Longitude FROM Restaurants WHERE RestaurantID = ?";
		try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
			// set the value
			pstmt.setInt(1, restaurantID);
			ResultSet rs = pstmt.executeQuery();
			Restaurant rest = null;
			while (rs.next()) {
				rest = new Restaurant();

				rest.latitude = rs.getDouble(1);
				rest.longitude = rs.getDouble(2);
			}
			return rest;
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			return null;
		}

	}

	public String getMinValueOf(String lableValue) {
		String resultString = "";
		String sql = "SELECT \r\n" + "    Name,\r\n" + "    Address,\r\n" + lableValue + "\r\n" + "FROM \r\n"
				+ "    Restaurants\r\n" + "WHERE \r\n" + lableValue + " = (\r\n" + "        SELECT \r\n"
				+ "            MIN(" + lableValue + ")\r\n" + "        FROM\r\n" + "            Restaurants)";
		try (Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {

			// loop through the result set
			while (rs.next()) {
				resultString += rs.getString("Name") + " ";
				resultString += rs.getString("Address") + " ";
				if (lableValue == "MinOrderValue") {
					resultString += "€ " + rs.getString(lableValue) + "\n";
				} else if (lableValue == "Distance") {
					resultString += rs.getString("Distance") + " km \n";
				} else {
					resultString += rs.getString(lableValue) + "\n";
				}

				// System.out.println(rs.getString("Name") + "\t" + rs.getString("Address") +
				// "\t" + rs.getDouble(lableValue));
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		return resultString;
	}

	public String getMaxValueOf(String lableValue) {
		String resultString = "";
		String sql = "SELECT \r\n" + "    Name,\r\n" + "    Address,\r\n" + lableValue + "\r\n" + "FROM \r\n"
				+ "    Restaurants\r\n" + "WHERE \r\n" + lableValue + " = (\r\n" + "        SELECT \r\n"
				+ "            MAX(" + lableValue + ")\r\n" + "        FROM\r\n" + "            Restaurants)";
		try (Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {

			// loop through the result set
			while (rs.next()) {
				resultString += rs.getString("Name") + " ";
				resultString += rs.getString("Address") + " ";
				if (lableValue == "MinOrderValue") {
					resultString += "€ " + rs.getString(lableValue) + "\n";
				} else if (lableValue == "Distance") {
					resultString += rs.getString("Distance") + " km \n";
				} else {
					resultString += rs.getString(lableValue) + "\n";
				}

				// System.out.println(rs.getString("Name") + "\t" + rs.getString("Address") +
				// "\t" + rs.getDouble(lableValue));
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		return resultString;
	}

	public Restaurant getRandomRestaurant(double distance, double rating) {
		ArrayList<String> resultListDistance = new ArrayList<>();
		ArrayList<String> resultListRatingValue = new ArrayList<>();
		ArrayList<String> resultList = new ArrayList<>();
		Restaurant rest= new Restaurant();
		int randomIndex = 0;
		// filter distance
		resultListDistance = storeRestaurantsBy("Distance", distance);
		//System.out.println("1 list:"+ resultListDistance);
		// filter ranking
		resultListRatingValue = storeRestaurantsBy("RatingValue", rating);
		//System.out.println("2 list:"+ resultListRatingValue);
		// making combined list
		for (String item : resultListDistance) {
			if (resultListRatingValue.contains(item)) {
				resultList.add(item);
			}
		}
		
		//System.out.println("3 list:"+ resultList);
		// randomize name from list
		randomIndex = (int) (Math.random() * resultList.size());
		//System.out.println("Random number:"+randomIndex);
		//System.out.println("Radom name:"+resultList.get(randomIndex));
		// query to DB
		rest.restaurantsNumber=resultList.size();
		rest.name= getRestaurantBy("Name",resultList.get(randomIndex));

		// get restaurant

		return rest;
	}

	public ArrayList<String> storeRestaurantsBy(String lableValue, double criteriaValue) {
		ArrayList<String> resultList = new ArrayList<>();
		// String resultString = "";
		String operator = "";
		if (lableValue == "Distance") {
			operator = "<=";
		} else if (lableValue == "RatingValue") {
			operator = ">=";
		} else {
			operator = "=";
		}
		String sql = "SELECT \r\n" + "    RestaurantID,\r\n" + "    Name,\r\n" + "    Address,\r\n" + lableValue
				+ "\r\n" + "FROM \r\n" + "    Restaurants\r\n" + "WHERE \r\n" + lableValue + operator + criteriaValue;

		try (Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {

			// loop through the result set
			while (rs.next()) {
				resultList.add(rs.getString("Name"));
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		return resultList;

	}
	
	public String getRestaurantBy(String lableValue, String criteriaValue) {
		
		String resultString = "";
	
		String sql = "SELECT \r\n" + "    Name,\r\n" + "    Address,\r\n" + "    Distance,\r\n"+"    RatingValue\r\n"
				+ "\r\n" + "FROM \r\n" + "    Restaurants\r\n" + "WHERE \r\n" + lableValue + "="+ "\""+criteriaValue+"\"";

		try (Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {

			// loop through the result set
			while (rs.next()) {
				resultString += rs.getString("Name") + " ";
				resultString += rs.getString("Address") + " ";
				resultString += rs.getString("Distance") + " km ";
				resultString += rs.getString("RatingValue") + "/5 ";
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		return resultString;

	}
}