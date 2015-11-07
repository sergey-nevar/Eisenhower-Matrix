package sample;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.util.Calendar;
import java.util.Date;

public class Task {
	private SimpleStringProperty name;
	private SimpleStringProperty creationTime;
	private SimpleStringProperty creationDate;
	private SimpleStringProperty termTime;
	private SimpleStringProperty termDate;
	private SimpleStringProperty priority;

	Task(String name, String termTime, String termDate, String priority){
		this.name = new SimpleStringProperty(name);
		Calendar calendar = Calendar.getInstance();
		String currentHour = String.valueOf(calendar.get(Calendar.HOUR)),
				currentMinute = String.valueOf(calendar.get(Calendar.MINUTE));
		creationTime = new SimpleStringProperty(currentHour + ':' + currentMinute);
		String currentYear = String.valueOf(calendar.get(Calendar.YEAR)),
				currentMonth = String.valueOf(calendar.get(Calendar.MONTH)),
				currentDate = String.valueOf(calendar.get(Calendar.DATE));
		creationDate = new SimpleStringProperty(currentYear + '-' + currentMonth + '-' + currentDate);
		this.termTime = new SimpleStringProperty(termTime);
		this.termDate = new SimpleStringProperty(termDate);
		this.priority = new SimpleStringProperty(priority);
	}
	public StringProperty nameProperty(){
		return name;
	}
	public String getName(){
		return name.getValue();
	}
	public StringProperty termTimeProperty(){
		return termTime;
	}
	public String getTermTime(){
		return termTime.getName();
	}
	public StringProperty termDateProperty(){
		return termDate;
	}
	public String getTermDate(){
		return termDate.getName();
	}
	public StringProperty creationTimeProperty(){
		return creationTime;
	}
	public String getCreationTime(){
		return creationTime.getName();
	}
	public StringProperty creationDateProperty(){
		return creationDate;
	}
	public String getCreationDate(){
		return creationDate.getName();
	}
	public StringProperty priorityProperty(){
		return priority;
	}
	public String getPriotrity(){
		return priority.getName();
	}
}
