package sample;

import javafx.scene.layout.GridPane;

public class MainWindow {
	private GridPane gridPane;

	MainWindow(){
		TaskTable tableA = new TaskTable("A");
		TaskTable tableB = new TaskTable("B");
		TaskTable tableC = new TaskTable("C");
		TaskTable tableD = new TaskTable("D");

		gridPane = new GridPane();
		gridPane.add(tableA.getMainLayout(), 0, 0);
		gridPane.add(tableB.getMainLayout(), 1, 0);
		gridPane.add(tableC.getMainLayout(), 0, 1);
		gridPane.add(tableD.getMainLayout(), 1, 1);
	}

	public GridPane getMainLayout(){
		return gridPane;
	}
}
