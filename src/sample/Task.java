package sample;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.util.Calendar;
import java.util.Date;

public class Task {
	private SimpleStringProperty name;
	private SimpleStringProperty creationTime;
	private  SimpleStringProperty creationDate;
	private SimpleStringProperty termTime;
	private SimpleStringProperty termDate;
	private SimpleIntegerProperty priority;

	Task(String name, String termTime, String termDate, int priority){
		this.name = new SimpleStringProperty(name);
		Calendar calendar = Calendar.getInstance();
		String currentHour = String.valueOf(calendar.get(Calendar.HOUR)),
				currentMinute = String.valueOf(calendar.get(Calendar.MINUTE)),
				currentSecond = String.valueOf(calendar.get(Calendar.SECOND));
		creationTime = new SimpleStringProperty(currentHour + ':' + currentMinute + ':' + currentSecond);
		String currentYear = String.valueOf(calendar.get(Calendar.YEAR)),
				currentMonth = String.valueOf(calendar.get(Calendar.MONTH)),
				currentDate = String.valueOf(calendar.get(Calendar.DATE));
		creationDate = new SimpleStringProperty(currentDate + '.' + currentMonth + '.' + currentYear);
		this.termTime = new SimpleStringProperty(termTime);
		this.termDate = new SimpleStringProperty(termDate);
		this.priority = new SimpleIntegerProperty(priority);
	}
	public StringProperty nameProperty(){
		return name;
	}
	public String getName(){
		return name.getValue();
	}
}
