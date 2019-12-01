package application;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
public class SQLite {
	private static Connection con;
	public ResultSet displayUsers() throws ClassNotFoundException, SQLException {
		if(con == null) {
			getConnection();
		}
		Statement state = con.createStatement();
		ResultSet res = state.executeQuery("SELECT name FROM info");
		return res;
	}
	public void getConnection() throws ClassNotFoundException, SQLException {
		Class.forName("org.sqlite.JDBC");
		con = DriverManager.getConnection("jdbc:sqlite:database.db");
		intitalize();
	}
	public void intitalize() throws SQLException {
		Statement state = con.createStatement();
		ResultSet res = state.executeQuery("SELECT name FROM sqlite_master WHERE type='table' AND name='info'");
		if(!res.next()) {
//			System.out.println("populateing info tables...");
			Statement state3 = con.createStatement();
			state3.execute("CREATE TABLE info (info_ID INTEGER PRIMARY KEY AUTOINCREMENT,"
							+ "name VARCHAR(50),"
							+ "DOB DATE,"
							+ "Height INT,"
							+ "Gender INT)");
//							+ "primary key(info_ID));");
		}
		
		res = state.executeQuery("SELECT name FROM sqlite_master WHERE type='table' AND name='stuff'");
		if(!res.next()) {
//			System.out.println("populateing stuff tables...");
			Statement state4 = con.createStatement();
			state4.execute("CREATE TABLE stuff (stuff_ID INTEGER PRIMARY KEY AUTOINCREMENT,"
							+ "date DATE,"
							+ "budget INT,"
							+ "consumed INT)");
//							+ "primary key(stuff_ID)");
		}
		res = state.executeQuery("SELECT name FROM sqlite_master WHERE type='table' AND name='weight'");
		if(!res.next()) {
//			System.out.println("populateing weight tables...");
			Statement state5 = con.createStatement();
			state5.execute("CREATE TABLE weight (weight_ID INTEGER PRIMARY KEY AUTOINCREMENT,"
							+ "date DATE,"
							+ "weight INT)");
//							+ "primary key(weight_ID));");
		}
	}
	public void execute(String str) throws SQLException {
		System.out.println("SQLite.java EXECUTED: " + str);
		Statement state = con.createStatement();
		System.out.println("MAde it past the first");
		state.execute(str);
		System.out.println("We made it past the query");
	}
	public ResultSet query(String str) throws SQLException {
		System.out.println("SQLite.java QUERIED: " + str);
		Statement state = con.createStatement();
		ResultSet res = state.executeQuery(str);
		return res;
	}
	public void closeConn() {
		try {
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
