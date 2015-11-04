package sample;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.util.ArrayList;
import java.util.List;

public class TaskTable {
	private List<Task> taskList;
	private TableView tableView;
	private Button addButton;
	private Button deleteButton;
	private Label tableName;
	private HBox buttonLayout;
	private VBox mainLayout;

	TaskTable(){
		tableView = new TableView();
		taskList = new ArrayList<>();
		addButton = new Button("Add");
		deleteButton = new Button("Delete");
		tableName = new Label("Some table");
		buttonLayout = new HBox(addButton, deleteButton);
		mainLayout = new VBox(tableName, tableView, buttonLayout);
	}
}
