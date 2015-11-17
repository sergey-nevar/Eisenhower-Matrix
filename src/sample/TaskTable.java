package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.util.List;

public class TaskTable {
	private List<Task> taskList;
	private TableView<Task> tableView;
	private Button addButton;
	private Button editButton;
	private Button deleteButton;
	private Label nameLabel;
	private HBox buttonLayout;
	private VBox textLayout;
	private VBox mainLayout;

	TaskTable(String nameOfTable){
		DatabaseController dbController = DatabaseController.getInstance();
		dbController.createTable(nameOfTable);
		taskList = dbController.getTasks(nameOfTable);
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

		nameLabel = new Label(nameOfTable);
		textLayout = new VBox(nameLabel);
		textLayout.setAlignment(Pos.CENTER);

		addButton = new Button("Add");
		addButton.setOnAction(event -> {
			TaskCreationWindow cr = new TaskCreationWindow();
			if(cr.getResultTask() != null) {
				taskList.add(cr.getResultTask());
				tableView.setItems(FXCollections.observableList(taskList));
				dbController.addTask(nameOfTable, cr.getResultTask());
			}
		});

		deleteButton = new Button("Delete");
		deleteButton.setOnAction(event ->{
			int delRow = tableView.getSelectionModel().getSelectedIndex();
			if(delRow != -1) {
				dbController.removeTask(nameOfTable, tableView.getSelectionModel().getSelectedItem());
				tableView.getItems().remove(delRow);
			}
		});

		editButton = new Button("Edit");
		editButton.setOnAction(event -> {
			if(tableView.getSelectionModel().getSelectedIndex() != -1) {
				Task editingTask = tableView.getSelectionModel().getSelectedItem();
				TaskCreationWindow cr = new TaskCreationWindow(editingTask);
				if (cr.getResultTask() != null) {
					dbController.editTask(nameOfTable, editingTask, cr.getResultTask());
					editingTask.setName(cr.getResultTask().getName());
					editingTask.setPriority(cr.getResultTask().getPriority());
					editingTask.setTermDate(cr.getResultTask().getTermDate());
					editingTask.setTermTime(cr.getResultTask().getTermTime());
				}
				tableView.getSelectionModel().clearSelection();
			}
		});
		buttonLayout = new HBox(addButton, deleteButton, editButton);
		buttonLayout.setAlignment(Pos.CENTER);

		mainLayout = new VBox(textLayout, tableView, buttonLayout);
	}

	public VBox getMainLayout(){
		return mainLayout;
	}
	public void setLabel(String str){
		nameLabel.setText(str);
	}
}
