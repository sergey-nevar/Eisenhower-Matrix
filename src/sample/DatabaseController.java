package sample;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseController {
	private final String url = "jdbc:mysql://localhost:3306";
	private final String user = "root";
	private final String password = "1234";

	private Connection connection;
	private Statement statement;
	private ResultSet resultSet;

	DatabaseController() {
		try {
			connection = DriverManager.getConnection(url, user, password);
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
			try {
				if(resultSet != null)
					resultSet.close();
			} catch(SQLException se) { /*can't do anything */ }
		}
	}
}
