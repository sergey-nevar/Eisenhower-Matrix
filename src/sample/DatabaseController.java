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
			"VALUES ('%s', '%s', '%s', '%s', '%s', '%s')";
	private final String removeTaskQuery = "DELETE FROM %s WHERE taskName='%s' AND dDate='%s' AND" +
			" dTime='%s' AND cDate='%s' AND cTime='%s' AND priority='%s'";
	private final String editTaskQuery = "UPDATE %s SET taskName='%s', dDate='%s', dTime='%s', priority='%s'" +
			" WHERE taskName='%s' AND dDate='%s' AND dTime='%s' AND priority='%s'";
	private final String getOverdueTasksQuery = "SELECT * FROM %s" +
			" WHERE dDate=CURDATE() AND dTime<CURTIME()";

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
		Statement statement = connection.createStatement()) {
			String query = String.format(addTaskQuery, tableName, task.getName(), task.getTermDate(),
					task.getTermTime(), task.getCreationDate(), task.getCreationTime(), task.getPriority());
			statement.executeUpdate(query);
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
		    Statement statement = connection.createStatement()) {
			String query = String.format(removeTaskQuery, tableName, task.getName(), task.getTermDate(),
					task.getTermTime(), task.getCreationDate(), task.getCreationTime(), task.getPriority());
			statement.executeUpdate(query);
		}
		catch (SQLException sqlEx) {
			sqlEx.printStackTrace();
		}
	}

	public void editTask(String tableName, Task oldTask, Task newTask){
		try(Connection connection = DriverManager.getConnection(url, user, password);
		    Statement statement = connection.createStatement()) {
			String query = String.format(editTaskQuery, tableName, newTask.getName(), newTask.getTermDate(),
					newTask.getTermTime(), newTask.getPriority(), oldTask.getName(), oldTask.getTermDate(),
					oldTask.getTermTime(), oldTask.getPriority());
			statement.executeUpdate(query);
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
