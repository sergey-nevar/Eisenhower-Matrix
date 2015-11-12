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
	private Label nameLabel;
	private Label priorityLabel;
	private Label dateLabel;
	private Label timeLabel;
	private Spinner<Integer> hourSpinner;
	private Spinner<Integer> minuteSpinner;
	private TextField nameTextField;
	private ChoiceBox<String> choiceBox;
	private HBox spinnerHBox;
	private DatePicker datePicker;
	private GridPane grid;
	private ButtonType buttonTypeOk;

	TaskCreationWindow(){
		initializeWindow();
		Optional<Task> result = creationDialog.showAndWait();
		if(result.isPresent()){
			resultTask = result.get();
		}
	}

	TaskCreationWindow(Task task){
		initializeWindow();
		nameTextField.setText(task.getName());
		choiceBox.setValue(task.getPriotrity());
	}

	public void initializeWindow(){
		creationDialog = new Dialog<>();
		creationDialog.setTitle("Add task");
		creationDialog.setContentText("Add information and press OK");

		nameLabel = new Label("Name of task: ");
		priorityLabel = new Label("Priority: ");
		dateLabel = new Label("Deadline date:");
		timeLabel = new Label("Deadline time(h/m):");

		hourSpinner = new Spinner<>(0, 23, 12);
		hourSpinner.setMaxWidth(60);

		minuteSpinner = new Spinner<>(0, 55, 30, 5);
		minuteSpinner.setMaxWidth(60);

		spinnerHBox = new HBox(hourSpinner, minuteSpinner);

		nameTextField = new TextField();

		ObservableList<String> priorities = FXCollections.observableArrayList("-", "1", "2", "3", "4", "5");
		choiceBox = new ChoiceBox<String>(priorities);

		datePicker = new DatePicker();
		datePicker.setEditable(false);
		datePicker.setOnAction(event -> {
			LocalDate date = datePicker.getValue();
			System.out.println("Selected date: " + date);
		});

		grid = new GridPane();
		grid.add(nameLabel, 1, 1);
		grid.add(nameTextField, 2, 1);
		grid.add(dateLabel, 1, 2);
		grid.add(datePicker, 2, 2);
		grid.add(priorityLabel, 1, 3);
		grid.add(choiceBox, 2, 3);
		grid.add(timeLabel, 1, 4);
		grid.add(spinnerHBox, 2, 4);
		creationDialog.getDialogPane().setContent(grid);

		buttonTypeOk = new ButtonType("Okay", ButtonBar.ButtonData.OK_DONE);
		creationDialog.getDialogPane().getButtonTypes().add(buttonTypeOk);

		creationDialog.setResultConverter(b -> {
			if (b == buttonTypeOk) {
				StringBuilder time = new StringBuilder();
				if (hourSpinner.getValue() < 10)
					time.append('0');
				time.append(hourSpinner.getValue().toString() + ":");
				if (minuteSpinner.getValue() < 10)
					time.append('0');
				time.append(minuteSpinner.getValue());

				String dateString = new String();
				if (datePicker.getValue() != null)
					dateString = datePicker.getValue().toString();
				return new Task(nameTextField.getText(), time.toString(), dateString, choiceBox.getValue());
			}
			return null;
		});
	}

	public Task getResultTask(){
		return resultTask;
	}
}
