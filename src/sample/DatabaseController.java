package sample;

import java.sql.*;
import java.util.ArrayList;

public class DatabaseController {
	private final String url = "jdbc:mysql://localhost:3306/EMatrix";
	private final String user = "root";
	private final String password = "root";

	private static final DatabaseController instance = new DatabaseController();

	private final String createTableQuery = "CREATE TABLE IF NOT EXISTS %s (id serial, taskName TEXT, " +
			"dDate DATE, dTime TIME, cDate DATE, cTime TIME, priority TEXT, PRIMARY KEY (id))";
	private final String addTaskQuery = "INSERT INTO %s (taskName, dDate, dTime, cDate, cTime, priority) " +
			"VALUES (?, ?, ?, ?, ?, ?)";
	private final String removeTaskQuery = "DELETE FROM %s WHERE taskName=? AND dDate=? AND" +
			" dTime=? AND cDate=? AND cTime=? AND priority=?";
	private final String editTaskQuery = "UPDATE %s SET taskName=?, dDate=?, dTime=?, priority=?" +
			" WHERE taskName=? AND dDate=? AND dTime=? AND priority=?";
	private final String getOverdueTasksQuery = "SELECT * FROM %s WHERE dDate=CURDATE() AND dTime<CURTIME()";

	private DatabaseController() {
		try(Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306", user, password);
		    Statement statement = connection.createStatement()) {
			statement.executeUpdate("CREATE DATABASE IF NOT EXISTS EMatrix");
		} catch (SQLException sqlEx) {
			sqlEx.printStackTrace();
		}
	}

	public void createTable(String tableName){
		try(Connection connection = DriverManager.getConnection(url, user, password);
		    Statement statement = connection.createStatement()) {
			statement.executeUpdate(String.format(createTableQuery, tableName));
		}
		catch (SQLException sqlEx) {
			sqlEx.printStackTrace();
		}
	}

	public static DatabaseController getInstance(){
		return instance;
	}

	public void addTask(String tableName, Task task){
		try(Connection connection = DriverManager.getConnection(url, user, password);
			PreparedStatement statement = connection.prepareStatement(String.format(addTaskQuery, tableName))) {
			statement.setString(1, task.getName());
			statement.setString(2, task.getTermDate());
			statement.setString(3, task.getTermTime());
			statement.setString(4, task.getCreationDate());
			statement.setString(5, task.getCreationTime());
			statement.setString(6, task.getPriority());
			statement.executeUpdate();
		}
		catch (SQLException sqlEx) {
			sqlEx.printStackTrace();
		}
	}

	public ArrayList<Task> getTasks(String tableName){
		ArrayList<Task> resultList = new ArrayList<>();
		try(Connection connection = DriverManager.getConnection(url, user, password);
		    Statement statement = connection.createStatement()) {
			try(ResultSet resultSet = statement.executeQuery(String.format("SELECT * FROM %s", tableName))){
				while (resultSet.next()) {
					resultList.add(new Task(resultSet.getString(2), resultSet.getString(3),
							resultSet.getString(4), resultSet.getString(5),
							resultSet.getString(6), resultSet.getString(7)));
				}
			}
		}
		catch (SQLException sqlEx) {
			sqlEx.printStackTrace();
		}
		return resultList;
	}

	public void removeTask(String tableName, Task task){
		try(Connection connection = DriverManager.getConnection(url, user, password);
		    PreparedStatement statement = connection.prepareStatement(String.format(removeTaskQuery, tableName))) {
			statement.setString(1, task.getName());
			statement.setString(2, task.getTermDate());
			statement.setString(3, task.getTermTime());
			statement.setString(4, task.getCreationDate());
			statement.setString(5, task.getCreationTime());
			statement.setString(6, task.getPriority());
			statement.executeUpdate();
		}
		catch (SQLException sqlEx) {
			sqlEx.printStackTrace();
		}
	}

	public void editTask(String tableName, Task oldTask, Task newTask){
		try(Connection connection = DriverManager.getConnection(url, user, password);
		    PreparedStatement statement = connection.prepareStatement(String.format(editTaskQuery, tableName))) {
			statement.setString(1, newTask.getName());
			statement.setString(2, newTask.getTermDate());
			statement.setString(3, newTask.getTermTime());
			statement.setString(4, newTask.getPriority());
			statement.setString(5, oldTask.getName());
			statement.setString(6, oldTask.getTermDate());
			statement.setString(7, oldTask.getTermTime());
			statement.setString(8, oldTask.getPriority());
			statement.executeUpdate();
		}
		catch (SQLException sqlEx) {
			sqlEx.printStackTrace();
		}
	}

	public ArrayList<Task> getOverdueTasks(ArrayList<String> tableNames){
		ArrayList<Task> resultList = new ArrayList<>();
		try(Connection connection = DriverManager.getConnection(url, user, password);
		    Statement statement = connection.createStatement()) {
			for(String tableName : tableNames) {
				try(ResultSet resultSet = statement.executeQuery(String.format(getOverdueTasksQuery, tableName))) {
					while (resultSet.next()) {
						resultList.add(new Task(resultSet.getString(2), resultSet.getString(3),
								resultSet.getString(4), resultSet.getString(5),
								resultSet.getString(6), resultSet.getString(7)));
					}
				}
			}
		}
		catch (SQLException sqlEx) {
			sqlEx.printStackTrace();
		}
		return resultList;
	}
}
