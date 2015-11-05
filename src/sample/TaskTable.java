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
		taskList.add(new Task("Example Task"));
		taskList.add(new Task("second task"));
		ObservableList observableTaskList = FXCollections.observableList(taskList);

		tableView = new TableView<>();
		tableView.setItems(observableTaskList);
		TableColumn titleCol = new TableColumn("Task");
		titleCol.setCellValueFactory(new PropertyValueFactory("name"));
		tableView.getColumns().setAll(titleCol);

		tableName = new Label("Some table");
		textLayout = new VBox(tableName);
		textLayout.setAlignment(Pos.CENTER);

		addButton = new Button("Add");
		addButton.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				System.out.println("Handle of addButton");
			}
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
