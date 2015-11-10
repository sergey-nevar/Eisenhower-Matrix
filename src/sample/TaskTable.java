package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

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
		taskNameColumn.setMinWidth(200);
		TableColumn creationDateColumn = new TableColumn("Creation date");
		creationDateColumn.setCellValueFactory(new PropertyValueFactory("creationDate"));
		creationDateColumn.setMinWidth(100);
		TableColumn creationTimeColumn = new TableColumn("Creation time");
		creationTimeColumn.setCellValueFactory(new PropertyValueFactory("creationTime"));
		creationTimeColumn.setMinWidth(100);
		TableColumn deadlineDateColumn = new TableColumn("Deadline date");
		deadlineDateColumn.setCellValueFactory(new PropertyValueFactory("termDate"));
		deadlineDateColumn.setMinWidth(100);
		TableColumn deadlineTimeColumn = new TableColumn("Deadline time");
		deadlineTimeColumn.setCellValueFactory(new PropertyValueFactory("termTime"));
		deadlineTimeColumn.setMinWidth(100);
		TableColumn priorityColumn = new TableColumn("Priority");
		priorityColumn.setCellValueFactory(new PropertyValueFactory("priority"));
		priorityColumn.setMinWidth(50);
		tableView.getColumns().setAll(taskNameColumn, deadlineDateColumn, deadlineTimeColumn);
		tableView.getColumns().addAll(creationDateColumn, creationTimeColumn, priorityColumn);

		tableView.setEditable(true);
		taskNameColumn.setCellFactory(TextFieldTableCell.forTableColumn());

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
