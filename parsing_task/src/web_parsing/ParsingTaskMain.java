package web_parsing;

import java.util.Calendar;
import java.util.Timer;
import java.util.TimerTask;


public class ParsingTaskMain {

	public static void main(String[] args) {
		Calendar now = Calendar.getInstance();
		Calendar taskcal = Calendar.getInstance();

		taskcal.set(Calendar.DAY_OF_WEEK, Calendar.SATURDAY);
		taskcal.set(Calendar.HOUR_OF_DAY, 23);
		taskcal.set(Calendar.MINUTE, 59);
		taskcal.set(Calendar.SECOND, 59);

		System.out.println("pasing tesk start!!");
		System.out.println("wait please...");
		Timer jobScheduler = new Timer();
		ScheduledJob job = new ScheduledJob();
		System.out.println(taskcal.getTime());
		jobScheduler.scheduleAtFixedRate(job, taskcal.getTime(), 1000*60*60*24*7);
		
		

	}
}


