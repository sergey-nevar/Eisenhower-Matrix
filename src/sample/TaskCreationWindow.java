package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.util.Callback;

import java.time.LocalDate;
import java.util.Optional;

public class TaskCreationWindow{
	private Dialog<Task> creationDialog;
	private Task resultTask;
	TaskCreationWindow(){
		creationDialog = new Dialog<>();
		creationDialog.setTitle("Add task");
		creationDialog.setContentText("Add information and press OK");

		Label nameLabel = new Label("Name of task: ");
		Label priorityLabel = new Label("Priority: ");
		Label dateLabel = new Label("Deadline date:");
		Label timeLabel = new Label("Deadline time(h/m):");

		Spinner<Integer> hourSpinner = new Spinner<>(0, 23, 12);
		hourSpinner.setMaxWidth(60);

		Spinner<Integer> minuteSpinner = new Spinner<>(0, 60, 0, 5);
		minuteSpinner.setMaxWidth(60);

		HBox spinnerHBox = new HBox(hourSpinner, minuteSpinner);

		TextField nameTextField = new TextField();

		ObservableList<String> country = FXCollections.observableArrayList("-", "1", "2", "3", "4", "5");
		ChoiceBox<String> choiceBox = new ChoiceBox<String>(country);

		DatePicker datePicker = new DatePicker();
		datePicker.setOnAction(event -> {
			LocalDate date = datePicker.getValue();
			System.out.println("Selected date: " + date);
		});

		GridPane grid = new GridPane();
		grid.add(nameLabel, 1, 1);
		grid.add(nameTextField, 2, 1);
		grid.add(dateLabel, 1, 2);
		grid.add(datePicker, 2, 2);
		grid.add(priorityLabel, 1, 3);
		grid.add(choiceBox, 2, 3);
		grid.add(timeLabel, 1, 4);
		grid.add(spinnerHBox, 2, 4);
		creationDialog.getDialogPane().setContent(grid);

		ButtonType buttonTypeOk = new ButtonType("Okay", ButtonBar.ButtonData.OK_DONE);
		creationDialog.getDialogPane().getButtonTypes().add(buttonTypeOk);

		creationDialog.setResultConverter(b -> {
			if (b == buttonTypeOk) {
				return new Task(nameTextField.getText());
			}
			return null;
		});

		Optional<Task> result = creationDialog.showAndWait();
		if(result.isPresent()){
			resultTask = result.get();
		}
	}

	public Task getResultTask(){
		return resultTask;
	}
}
