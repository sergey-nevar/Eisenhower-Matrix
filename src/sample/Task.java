package sample;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.util.Date;

public class Task {
	private SimpleStringProperty name;
	private Date creationTime;
	private Date term;
	private int priority;

	Task(String nameOfTask){
		name = new SimpleStringProperty(nameOfTask);
	}
	public StringProperty nameProperty(){
		return name;
	}
	public String getName(){
		return name.getValue();
	}
}
