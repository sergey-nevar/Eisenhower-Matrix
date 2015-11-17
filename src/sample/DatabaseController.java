package sample;

import java.sql.*;
import java.util.ArrayList;

public class DatabaseController {
	private final String url = "jdbc:mysql://localhost:3306/EMatrix";
	private final String user = "root";
	private final String password = "1234";

	private Connection connection;
	private Statement statement;
	private ResultSet resultSet;

	private static final DatabaseController instance = new DatabaseController();

	private final String createTableQuery =
			"CREATE TABLE IF NOT EXISTS %s (id serial, taskName TEXT, dDate DATE," +
					" dTime TIME, cDate DATE, cTime TIME, priority TEXT, PRIMARY KEY (id))";
	private final String addTaskQuery =
			"INSERT INTO %s (taskName, dDate, dTime, cDate, cTime, priority) " +
					"VALUES ('%s', '%s', '%s', '%s', '%s', '%s')";
	private final String removeTaskQuery = "DELETE FROM %s WHERE taskName='%s' AND dDate='%s' AND" +
			" dTime='%s' AND cDate='%s' AND cTime='%s' AND priority='%s'";
	private final String editTaskQuery = "UPDATE %s SET taskName='%s', dDate='%s', dTime='%s', priority='%s'" +
			" WHERE taskName='%s' AND dDate='%s' AND dTime='%s' AND priority='%s'";
	private final String getOverdueTasksQuery = "SELECT * FROM %s" +
			" WHERE dDate=CURDATE() AND dTime<CURTIME()";

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
			String priority = task.getPriority();
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

	public ArrayList<Task> getTasks(String tableName){
		ArrayList<Task> resultList = new ArrayList<>();
		try {
			connection = DriverManager.getConnection(url, user, password);
			statement = connection.createStatement();
			resultSet = statement.executeQuery(String.format("SELECT * FROM %s", tableName));

			while(resultSet.next()){
				resultList.add(new Task(resultSet.getString(2), resultSet.getString(3),
						resultSet.getString(4), resultSet.getString(5),
						resultSet.getString(6), resultSet.getString(7)));
			}
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
			try {
				resultSet.close();
			} catch(SQLException se) { /*can't do anything */ }
		}
		return resultList;
	}

	public void removeTask(String tableName, Task task){
		try {
			connection = DriverManager.getConnection(url, user, password);
			statement = connection.createStatement();
			String name = task.getName();
			String priority = task.getPriority();
			String cDate = task.getCreationDate();
			String cTime = task.getCreationTime();
			String dDate = task.getTermDate();
			String dTime = task.getTermTime();
			String query = String.format(removeTaskQuery, tableName, name, dDate, dTime, cDate, cTime, priority);
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
	public void editTask(String tableName, Task oldTask, Task newTask){
		try {
			connection = DriverManager.getConnection(url, user, password);
			statement = connection.createStatement();
			String newName = newTask.getName();
			String newPriority = newTask.getPriority();
			String newDDate = newTask.getTermDate();
			String newDTime = newTask.getTermTime();
			String oldName = oldTask.getName();
			String oldPriority = oldTask.getPriority();
			String oldDDate = oldTask.getTermDate();
			String oldDTime = oldTask.getTermTime();
			String query = String.format(editTaskQuery, tableName, newName, newDDate, newDTime, newPriority,
					oldName, oldDDate, oldDTime, oldPriority);
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

	public ArrayList<Task> getOverdueTasks(String tableName){
		ArrayList<Task> resultList = new ArrayList<>();
		try {
			connection = DriverManager.getConnection(url, user, password);
			statement = connection.createStatement();
			resultSet = statement.executeQuery(String.format(getOverdueTasksQuery, tableName));
			while(resultSet.next()){
				resultList.add(new Task(resultSet.getString(2), resultSet.getString(3),
						resultSet.getString(4), resultSet.getString(5),
						resultSet.getString(6), resultSet.getString(7)));
			}
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
			try {
				resultSet.close();
			} catch(SQLException se) { /*can't do anything */ }
		}
		return resultList;
	}
}
