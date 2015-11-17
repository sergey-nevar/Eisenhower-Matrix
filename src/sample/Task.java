package sample;

import javafx.beans.property.SimpleStringProperty;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Task {
	private SimpleStringProperty name;
	private SimpleStringProperty creationTime;
	private SimpleStringProperty creationDate;
	private SimpleStringProperty termTime;
	private SimpleStringProperty termDate;
	private SimpleStringProperty priority;

	Task(String name, String termTime, String termDate, String priority){
		Date date = new Date();
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		creationDate = new SimpleStringProperty(df.format(date));
		df = new SimpleDateFormat("HH:mm");
		creationTime = new SimpleStringProperty(df.format(date));

		this.name = new SimpleStringProperty(name);
		this.termTime = new SimpleStringProperty(termTime);
		this.termDate = new SimpleStringProperty(termDate);
		this.priority = new SimpleStringProperty(priority);
	}
	Task(String name, String termDate, String termTime, String crDate, String crTime, String priority){
		creationDate = new SimpleStringProperty(crDate);
		creationTime = new SimpleStringProperty(crTime);
		this.name = new SimpleStringProperty(name);
		this.termTime = new SimpleStringProperty(termTime);
		this.termDate = new SimpleStringProperty(termDate);
		this.priority = new SimpleStringProperty(priority);
	}
	public String getName(){
		return name.getValue();
	}
	public String getTermTime(){
		return termTime.getValue();
	}
	public String getTermDate(){
		return termDate.getValue();
	}
	public String getCreationTime(){
		return creationTime.getValue();
	}
	public String getCreationDate(){
		return creationDate.getValue();
	}
	public String getPriority(){
		return priority.getValue();
	}
	public void setName(String name){
		this.name.set(name);
	}
	public void setTermDate(String tDate){
		termDate.set(tDate);
	}
	public void setTermTime(String tTime){
		termTime.set(tTime);
	}
	public void setPriority(String priority){
		this.priority.set(priority);
	}
}
