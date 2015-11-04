package sample;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;

import java.util.ArrayList;
import java.util.List;

public class TaskTable {
	private List<Task> taskList;
	private TableView tableView;
	private Button addButton;
	private Button deleteButton;
	private Label tableName;
	private HBox buttonLayout;
	private VBox textLayout;
	private VBox mainLayout;

	TaskTable(){
		tableView = new TableView();
		taskList = new ArrayList<>();

		tableName = new Label("Some table");
		textLayout = new VBox(tableName);
		textLayout.setAlignment(Pos.CENTER);

		addButton = new Button("Add");
		deleteButton = new Button("Delete");
		buttonLayout = new HBox(addButton, deleteButton);
		buttonLayout.setAlignment(Pos.CENTER);

		mainLayout = new VBox(textLayout, tableView, buttonLayout);
	}

	VBox getMainLayout(){
		return mainLayout;
	}
}
