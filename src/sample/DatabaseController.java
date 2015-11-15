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
	private final String addTaskQuery =
			"INSERT INTO %s (taskName, dDate, dTime, cDate, cTime, priority) " +
					"VALUES ('%s', '%s', '%s', '%s', '%s', '%s')";

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

	public void addTask(String tableName, Task task){
		try {
			connection = DriverManager.getConnection(url, user, password);
			statement = connection.createStatement();
			String name = task.getName();
			String priority = task.getPriotrity();
			String cDate = task.getCreationDate();
			String cTime = task.getCreationTime();
			String dDate = task.getTermDate();
			String dTime = task.getTermTime();
			String query = String.format(addTaskQuery, tableName, name, dDate, dTime, cDate, cTime, priority);
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
}
