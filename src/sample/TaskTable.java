package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Popup;

import java.util.ArrayList;
import java.util.List;

public class TaskTable {
	private List<Task> taskList;
	private TableView<Task> tableView;
	private Button addButton;
	private Button deleteButton;
	private Label tableName;
	private HBox buttonLayout;
	private VBox textLayout;
	private VBox mainLayout;

	TaskTable(){
		taskList = new ArrayList<>();
		ObservableList observableTaskList = FXCollections.observableList(taskList);

		tableView = new TableView<>();
		tableView.setItems(observableTaskList);
		TableColumn taskNameColumn = new TableColumn("Task");
		taskNameColumn.setCellValueFactory(new PropertyValueFactory("name"));
		TableColumn creationDateColumn = new TableColumn("Creation date");
		creationDateColumn.setCellValueFactory(new PropertyValueFactory("creationDate"));
		TableColumn creationTimeColumn = new TableColumn("Creation time");
		creationTimeColumn.setCellValueFactory(new PropertyValueFactory("creationTime"));
		TableColumn deadlineDateColumn = new TableColumn("Deadline date");
		deadlineDateColumn.setCellValueFactory(new PropertyValueFactory("termDate"));
		TableColumn deadlineTimeColumn = new TableColumn("Deadline time");
		deadlineTimeColumn.setCellValueFactory(new PropertyValueFactory("termTime"));
		TableColumn priorityColumn = new TableColumn("Priority");
		priorityColumn.setCellValueFactory(new PropertyValueFactory("priority"));
		tableView.getColumns().setAll(taskNameColumn, deadlineDateColumn, deadlineTimeColumn);
		tableView.getColumns().addAll(creationDateColumn, creationTimeColumn, priorityColumn);

		tableName = new Label("Some table");
		textLayout = new VBox(tableName);
		textLayout.setAlignment(Pos.CENTER);

		addButton = new Button("Add");
		addButton.setOnAction(event -> {
			TaskCreationWindow cr = new TaskCreationWindow();
			taskList.add(cr.getResultTask());
			ObservableList observableTaskList2 = FXCollections.observableList(taskList);
			tableView.setItems(observableTaskList2);
		});
		deleteButton = new Button("Delete");
		buttonLayout = new HBox(addButton, deleteButton);
		buttonLayout.setAlignment(Pos.CENTER);

		mainLayout = new VBox(textLayout, tableView, buttonLayout);
	}

	public VBox getMainLayout(){
		return mainLayout;
	}
	public void setLabel(String str){
		tableName.setText(str);
	}
}
