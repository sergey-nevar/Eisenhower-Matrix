package sample;

import javafx.scene.layout.GridPane;

public class MainWindow {
	private TaskTable tableA;
	private TaskTable tableB;
	private TaskTable tableC;
	private TaskTable tableD;
	private GridPane gridPane;

	MainWindow(){
		tableA = new TaskTable("A");
		tableB = new TaskTable("B");
		tableC = new TaskTable("C");
		tableD = new TaskTable("D");

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
