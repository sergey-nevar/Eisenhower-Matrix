package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;

public class TaskCreationWindow{
	private Dialog<Task> creationDialog;
	TaskCreationWindow(){
		creationDialog = new Dialog<>();
		creationDialog.setTitle("Add task");
		creationDialog.setContentText("Add information and press OK");

		Label nameLabel = new Label("Name of task: ");
		Label priorityLabel = new Label("Priority: ");
		TextField nameTextField = new TextField();

		ObservableList<String> country = FXCollections.observableArrayList("-", "1", "2", "3", "4", "5");
		ChoiceBox<String> choiceBox = new ChoiceBox<String>(country);

		GridPane grid = new GridPane();
		grid.add(nameLabel, 1, 1);
		grid.add(nameTextField, 2, 1);
		grid.add(priorityLabel, 1, 2);
		grid.add(choiceBox, 2, 2);

		creationDialog.getDialogPane().setContent(grid);

		ButtonType buttonTypeOk = new ButtonType("Okay", ButtonBar.ButtonData.OK_DONE);
		creationDialog.getDialogPane().getButtonTypes().add(buttonTypeOk);

		creationDialog.show();
	}
}
