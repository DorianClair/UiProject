package application;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class SQLite {
	private static Connection con;
	private static boolean hasData = false;
	
//	public ResultSet displayUsers() throws ClassNotFoundException, SQLException {
//		if(con == null) {
//			getConnection();
//		}
//		Statement state = con.createStatement();
//		ResultSet res = state.executeQuery("SELECT fname, lname FROM user");
//		return res;
//	}
	
	

	public void getConnection() throws ClassNotFoundException, SQLException {
		Class.forName("org.sqlite.JDBC");
		con = DriverManager.getConnection("jdbc:sqlite:database.db");
		intitalize(); //change this
	}
	
	private void intitalize() throws SQLException {
		if(!hasData) {
			hasData = true;
		}
		Statement state = con.createStatement();
		ResultSet res = state.executeQuery("SELECT name FROM sqlite_master WHERE type='table' AND name='user'");
		if(!res.next()) {
			System.out.println("populate tables");
			Statement state2 = con.createStatement();
			state2.execute("CREATE TABLE user(id integer,"
							+ "fname varchar(50),"
							+ "lname varchar(50),"
							+ "primary key(id));");
			
			PreparedStatement prep = con.prepareStatement("INSERT INTO user values(?,?,?)");
			prep.setString(2, "John");
			prep.setString(3, "Dave");
			prep.execute();
		}
	}
	
	
	
//	public void addUser(String fname, String lname) throws ClassNotFoundException, SQLException {
//		if(con == null) {
//			getConnection();
//		}
//		PreparedStatement prep = con.prepareStatement("INSERT INTO user values(?,?,?);");
//		prep.setString(2, fname);
//		prep.setString(3, lname);
//		prep.execute();
//	}
}
