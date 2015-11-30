package sample;

import junit.framework.TestCase;
import org.junit.Test;

import java.util.ArrayList;

public class DatabaseControllerTest extends TestCase {
	private DatabaseController dbc = DatabaseController.getInstance();
	private Task testTask = new Task("testTask", "2012-10-10", "16:41:00", "2015-11-29", "18:19:21", "5");
	private String testTableName = "testTable";
	@Test(timeout = 1000)
	public void testAddTask() throws Exception {
		dbc.addTask(testTableName, testTask);
	}

	@Test(timeout = 1000)
	public void testGetTasks() throws Exception {
		ArrayList<Task> taskList = dbc.getTasks("testTable");
	}

	@Test(timeout = 1000)
	public void testRemoveTask() throws Exception {
		dbc.removeTask(testTableName, testTask);
	}

	@Test(timeout = 1000)
	public void testEditTask() throws Exception {
		dbc.editTask(testTableName, testTask, new Task("testTask2", "2015-10-23", "16:41:07", "2016-11-29", "18:19:21", "5"));
	}
}