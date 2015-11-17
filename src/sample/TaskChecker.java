package sample;

import javafx.application.Platform;
import javafx.scene.control.Alert;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

public class TaskChecker {
	private Timer taskChecker;

	TaskChecker(){
		taskChecker = new Timer();
		DatabaseController dbc = DatabaseController.getInstance();
		taskChecker.schedule(new TimerTask() {
			@Override
			public void run() {
				ArrayList<Task> taskList = dbc.getOverdueTasks("A");
				if (!taskList.isEmpty()) {
					for(Task task : taskList) {
						Platform.runLater(() -> {
							Alert alert = new Alert(Alert.AlertType.INFORMATION);
							alert.setTitle("Reminder Window");
							alert.setHeaderText("Deadline...");
							alert.setContentText("You missed deadline of " + task.getName());
							alert.showAndWait();
						});
					}
				}
			}
		}, 5000, 60000);
	}

	public void stopChecking(){
		taskChecker.cancel();
	}
}
