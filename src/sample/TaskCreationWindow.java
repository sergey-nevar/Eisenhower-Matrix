package sample;

import javafx.scene.control.Dialog;

public class TaskCreationWindow{
	private Dialog<Task> creationDialog;
	TaskCreationWindow(){
		creationDialog = new Dialog<>();
		creationDialog.show();
	}
}
