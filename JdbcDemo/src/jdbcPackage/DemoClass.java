package jdbcPackage;
import java.sql.*;
public class DemoClass {
	public static void main(String [] args) throws Exception
	{
		String url = "jdbc:mysql://localhost:3306/publications";
		String uname = "root";
		String pass = "";
		String query = "select * from users";
		String query2 = "insert into users (forename, surname, username, password) values ('John','Frank','Acocella',123)";
		String forename = "Michael";
		String surname = "Johnson";
		String username = "Michael Johnson";
		int pass2 = 123;
		String query3 = "insert into users values(?,?,?,?)";		
				
		Class.forName("com.mysql.jdbc.Driver"); // Class.forName runs the static block of the Driver class w/o creating an instance
		//Class.forName is the same as...
		//DriveManager.registerDriver(new com.mysql.jdbc.Driver());
		
		Connection con = DriverManager.getConnection(url,uname,pass);  //gives instance of connection.  getConnection is static. DriverManager is class
		PreparedStatement st2 = con.prepareStatement(query3);
		st2.setString(1, forename);
		st2.setString(2, surname);
		st2.setString(3, username);
		st2.setInt(4, pass2);
		
		int count2 = st2.executeUpdate();
		
		System.out.println(count2 + " rows affected");
		
		Statement st = con.createStatement();  //Statement is an interface, createStatement returns a Statement object
		
		int count = st.executeUpdate(query2);
		ResultSet rs = st.executeQuery(query);
		
		System.out.println(count + " rows affected");
		
		String userData = "";
		
		while (rs.next()) { //move to first record
			userData = rs.getString(1) + " " + rs.getString(2);
			System.out.println(userData);
		}
		st.close();
		con.close();
	}

}
