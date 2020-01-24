package calculations;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 *
 * @author sqlitetutorial.net
 */
public class UpdateApp {
	
	ConnectionApp co= new ConnectionApp();
	Connection conn = co.connect();



	/**
	 * Update data specified by the id
	 */
	public void updateCoordinates(int id, double lat, double lon) {
		String sql = "VACUUM;\r\n" + 
				"PRAGMA synchronous = NORMAL;\r\n" + 
				"PRAGMA journal_mode = WAL; \r\n" + 
				"PRAGMA page_size = 4096;"
				+ "UPDATE Restaurants SET Latitude = ? , " + "Longitude = ? " + "WHERE RestaurantID = ?";

		try ( PreparedStatement pstmt = conn.prepareStatement(sql)) {

			// set the corresponding param
			pstmt.setDouble(1, lat);
			pstmt.setDouble(2, lon);
			pstmt.setInt(3, id);
			// update
			pstmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}

	// TODO
	public void updateDistance(int id, double distance) {
		String sql ="UPDATE Restaurants SET Distance = ?  " + "WHERE RestaurantID = ?";

		try (PreparedStatement pstmt = conn.prepareStatement(sql)) {

			// set the corresponding param
			pstmt.setDouble(1, distance);
			pstmt.setInt(2, id);
			// update

			
			final long startTime = System.currentTimeMillis();
			pstmt.executeUpdate();
			
			final long endTime = System.currentTimeMillis();
			System.out.println(startTime - endTime);
			
			
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}
	
	public void updateMinOrderValue (String name, double orderValue) {
		//update by name
		String sql ="UPDATE Restaurants SET MinOrderValue = ?  " + "WHERE Name = ?";

		try (PreparedStatement pstmt = conn.prepareStatement(sql)) {

			// set the corresponding param
			pstmt.setDouble(1, orderValue);
			pstmt.setString(2, name);
			// update

			
			final long startTime = System.currentTimeMillis();
			pstmt.executeUpdate();
			
			final long endTime = System.currentTimeMillis();
			System.out.println(startTime - endTime);
			
			
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}
	
	public void updateRatingValue (String name, double ratingValue) {
		//update by name
		String sql ="UPDATE Restaurants SET RatingValue = ?  " + "WHERE Name = ?";

		try (PreparedStatement pstmt = conn.prepareStatement(sql)) {

			// set the corresponding param
			pstmt.setDouble(1, ratingValue);
			pstmt.setString(2, name);
			// update

			
			final long startTime = System.currentTimeMillis();
			pstmt.executeUpdate();
			
			final long endTime = System.currentTimeMillis();
			System.out.println(startTime - endTime);
			
			
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}

}