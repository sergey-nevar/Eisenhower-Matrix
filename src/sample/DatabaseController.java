package sample;

import java.sql.*;

public class DatabaseController {
	private final String url = "jdbc:mysql://localhost:3306/EMatrix";
	private final String user = "root";
	private final String password = "1234";

	private Connection connection;
	private Statement statement;
	private ResultSet resultSet;

	private static final DatabaseController instance = new DatabaseController();

	private final String createTableQuery =
			"CREATE TABLE IF NOT EXISTS %s (id serial, taskName TEXT, dDate TEXT," +
					" dTime TEXT, cDate TEXT, cTime TEXT, priority TEXT, PRIMARY KEY (id))";

	private DatabaseController() {
		try {
			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306", user, password);
			statement = connection.createStatement();
			statement.executeUpdate("CREATE DATABASE IF NOT EXISTS EMatrix");
		} catch (SQLException sqlEx) {
			sqlEx.printStackTrace();
		} finally {
			try {
				connection.close();
			} catch(SQLException se) { /*can't do anything */ }
			try {
				statement.close();
			} catch(SQLException se) { /*can't do anything */ }
		}
	}
	public void createTable(String tableName){
		try {
			connection = DriverManager.getConnection(url, user, password);
			statement = connection.createStatement();
			String query = String.format(createTableQuery, tableName);
			statement.executeUpdate(query);
		}
		catch (SQLException sqlEx) {
			sqlEx.printStackTrace();
		}
		finally {
			try {
				connection.close();
			} catch(SQLException se) { /*can't do anything */ }
			try {
				statement.close();
			} catch(SQLException se) { /*can't do anything */ }
		}
	}

	public static DatabaseController getInstance(){
		return instance;
	}
}
