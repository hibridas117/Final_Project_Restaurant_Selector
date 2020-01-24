package calculations;
import java.util.*;
import java.lang.*;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.net.MalformedURLException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.beans.Statement;
import java.io.*;

public class DistanceCalculator {
	public static void main(String[] args) throws java.lang.Exception {
		
		
		
		
		// UpdateApp uapp= new UpdateApp();
		// uapp.updateDistance(1, getDistance(32.9697, -96.80322, 29.46786, -98.53506));
		// System.out.println(getDistance(32.9697, -96.80322, 29.46786, -98.53506));
		// getDistance(32.9697, -96.80322, 29.46786, -98.53506);

		
		 

		final long startTime = System.currentTimeMillis();

		updateDistancesFromAddress("Žirmūnų g. 2, Vilnius");

		final long endTime = System.currentTimeMillis();

		System.out.println("Total execution time: " + (endTime - startTime));

		// updateDistancesFromAddress("Ulonų g. 2, Vilnius");
		// Ulonų g. 2, Vilnius Verkių g. 29, Vilnius
		// 0.7881426884171502

	}

	public static void updateDistancesFromAddress(String address) throws MalformedURLException, IOException {
		//double distanceArray[];
		AddressConverter ac = new AddressConverter();
		SelectApp sapp = new SelectApp();
		UpdateApp uapp = new UpdateApp();
		Restaurant user = ac.getCoordinates(address);
		for (int i = 1; i < 151; i++) {
			// GOOD BUT TAKES SO LONG
			// TODO unlock
			
			  Restaurant rest = sapp.readLatLon(i); //updates values in DB
			  uapp.updateDistance(i, getDistance(user.latitude, user.longitude,
			  rest.latitude, rest.longitude));
			  
			  System.out.println("Number: " + i);
			  System.out.println(getDistance(user.latitude, user.longitude, sapp.readLat(i), sapp.readLon(i)));
			 

			// uapp.updateDistance(i, getDistance(user.latitude, user.longitude,
			// sapp.readLat(i), sapp.readLon(i)));

		//ONLY CALCULATION
			/*System.out.println("Number: " + i);
			System.out.println(getDistance(user.latitude, user.longitude, sapp.readLat(i), sapp.readLon(i)));*/

		}
	}
	public static void TESTupdateDistancesFromAddress(String address) throws MalformedURLException, IOException, SQLException {
		
		final long startTime = System.currentTimeMillis();
		AddressConverter ac = new AddressConverter();
		SelectApp sapp = new SelectApp();
		UpdateApp uapp = new UpdateApp();
		Restaurant user = ac.getCoordinates(address);
		ConnectionApp co= new ConnectionApp();
		Connection con = co.connect();
		java.sql.Statement stmt = con.createStatement();
		 final long endTime = System.currentTimeMillis();

			System.out.println("NEW CREATION execution time: " + (endTime - startTime));
		
		con.setAutoCommit(false);
		
		for (int i = 1; i < 151; i++) {
			// GOOD BUT TAKES SO LONG
			// TODO unlock
			
			  Restaurant rest = sapp.readLatLon(i); 
			  //updates values in DB
			  double distance = getDistance(user.latitude, user.longitude, sapp.readLat(i), sapp.readLon(i));
			  String sql ="UPDATE Restaurants SET Distance = "+ distance + " WHERE RestaurantID ="+i;
			  
			  stmt.addBatch(sql);
			  //System.out.println(sql);

		}
		

		stmt.executeBatch();
	    con.commit();
		
	   
	}

//Distance in KM
	private static double getDistance(double lat1, double lon1, double lat2, double lon2) {
		if ((lat1 == lat2) && (lon1 == lon2)) {
			return 0;
		} else {
			double theta = lon1 - lon2;
			double dist = Math.sin(Math.toRadians(lat1)) * Math.sin(Math.toRadians(lat2))
					+ Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2)) * Math.cos(Math.toRadians(theta));
			dist = Math.acos(dist);
			dist = Math.toDegrees(dist);
			dist = dist * 60 * 1.1515 * 1.609344;
			BigDecimal bd = new BigDecimal(dist).setScale(3, RoundingMode.HALF_UP);
			dist = bd.doubleValue();
			return (dist);
		}
	}
}