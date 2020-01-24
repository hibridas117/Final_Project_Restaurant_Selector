package calculations;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
 
/**
 *
 * @author sqlitetutorial.net
 */
public class InsertApp {
 
	
	ConnectionApp co= new ConnectionApp();
	Connection conn = co.connect();
  
	
    /*private Connection connect() {
        // SQLite connection string
        String url = "jdbc:sqlite:C:\\Users\\Vilius\\Desktop\\Eclipse files\\SQL\\RestaurantDatabase.db";
        //String url = "jdbc:sqlite:C:/Users/Vilius/Desktop/Eclipse files/SQL/RestaurantDatabase.db";
       
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(url);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return conn;
    }*/
 
    public void insert(String name, String address) {
        String sql = "INSERT INTO Restaurants(name,address) VALUES(?,?)";
 //TODO add more fields
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, name);
            pstmt.setString(2, address);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

 
}