import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;


public class GetDabase {

	public static void main(String[] args) throws SQLException, ClassNotFoundException {
		int age = 30;
		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection  con = DriverManager.getConnection("jdbc:mysql://localhost:3306/gogo?useSSL=false", "root", "root");
		
		String query = "{CALL Age5(?)}";
		CallableStatement stmt = con.prepareCall(query);
		stmt.setInt(1,age);
		
		ResultSet rs1 = stmt.executeQuery();
		 while(rs1.next()) {
	         System.out.print("Name: "+rs1.getString("NAME")+", ");       
	         System.out.println();
	      }
	      
		 stmt.getMoreResults();

		 ResultSet rs2 = stmt.getResultSet();
		 while(rs2.next()) {
	         System.out.print("Older Name: "+rs2.getString("NAME")+", ");       
	         System.out.println();
	      }
		 
	}

}
