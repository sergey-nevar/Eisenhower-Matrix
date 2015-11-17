package sample;

import java.util.Timer;
import java.util.TimerTask;

public class TaskChecker {
	private Timer taskChecker;

	TaskChecker(){
		taskChecker = new Timer();
		taskChecker.schedule(new TimerTask() {
			@Override
			public void run() {
				System.out.println("12345");
			}
		}, 0, 10000);
	}

	public void stopChecking(){
		taskChecker.cancel();
	}
}
