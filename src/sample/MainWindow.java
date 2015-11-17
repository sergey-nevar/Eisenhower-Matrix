package sample;

import javafx.scene.layout.GridPane;

import java.util.ArrayList;

public class MainWindow {
	private GridPane gridPane;
	private static final String tableName1 = "A";
	private static final String tableName2 = "B";
	private static final String tableName3 = "C";
	private static final String tableName4 = "D";

	MainWindow(){
		TaskTable tableA = new TaskTable(tableName1);
		TaskTable tableB = new TaskTable(tableName2);
		TaskTable tableC = new TaskTable(tableName3);
		TaskTable tableD = new TaskTable(tableName4);

		gridPane = new GridPane();
		gridPane.add(tableA.getMainLayout(), 0, 0);
		gridPane.add(tableB.getMainLayout(), 1, 0);
		gridPane.add(tableC.getMainLayout(), 0, 1);
		gridPane.add(tableD.getMainLayout(), 1, 1);
	}

	public static ArrayList<String> getTableNames(){
		ArrayList<String> resulList = new ArrayList<String>();
		resulList.add(tableName1);
		resulList.add(tableName2);
		resulList.add(tableName3);
		resulList.add(tableName4);
		return resulList;
	}
	public GridPane getMainLayout(){
		return gridPane;
	}
}
