package calculations;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionApp {
	
	String url = "jdbc:sqlite:C:\\Users\\Vilius\\Desktop\\Eclipse files\\SQL\\RestaurantDatabase.db";
	Connection conn = connect();
	
	public Connection connect() {
       
		if (conn == null) {
			try {
				return conn = DriverManager.getConnection(url);
			} catch (SQLException e) {
				System.out.println(e.getMessage());
			}
		}

		return conn;
	}

}
