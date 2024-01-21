package FCM_notification;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

public class menu_push {
	
	public static void main(String[] args) {
		
	System.out.println("notification ready!");
	
	Calendar taskcal1 = Calendar.getInstance();
	taskcal1.set(Calendar.HOUR_OF_DAY, 7);
	taskcal1.set(Calendar.MINUTE, 30);
	taskcal1.set(Calendar.SECOND,0);
	
	
	Calendar taskcal2 = Calendar.getInstance();
	taskcal2.set(Calendar.HOUR_OF_DAY, 11);
	taskcal2.set(Calendar.MINUTE, 0);
	taskcal2.set(Calendar.SECOND,0);
	
	Calendar taskcal3 = Calendar.getInstance();
	taskcal3.set(Calendar.HOUR_OF_DAY, 16);
	taskcal3.set(Calendar.MINUTE, 45);
	taskcal3.set(Calendar.SECOND,0);
	
	// thread
	ScheduledJob1 job1 = new ScheduledJob1();
	ScheduledJob2 job2 = new ScheduledJob2();
	ScheduledJob3 job3 = new ScheduledJob3();
	
	//Good_tesk
	
	Calendar Good_taskcal1 = Calendar.getInstance();
	Good_taskcal1.set(Calendar.HOUR_OF_DAY, 7);
	Good_taskcal1.set(Calendar.MINUTE, 31);
	Good_taskcal1.set(Calendar.SECOND,0);
	
	Calendar Good_taskcal2 = Calendar.getInstance();
	Good_taskcal2.set(Calendar.HOUR_OF_DAY, 11);
	Good_taskcal2.set(Calendar.MINUTE, 1);
	Good_taskcal2.set(Calendar.SECOND,0);
	
	Calendar Good_taskcal3 = Calendar.getInstance();
	Good_taskcal3.set(Calendar.HOUR_OF_DAY, 16);
	Good_taskcal3.set(Calendar.MINUTE, 46);
	Good_taskcal3.set(Calendar.SECOND,0);
	
	// thread
		Good_ScheduledJob1 Good_job1 = new Good_ScheduledJob1();
		Good_ScheduledJob2 Good_job2 = new Good_ScheduledJob2();
		Good_ScheduledJob3 Good_job3 = new Good_ScheduledJob3();
	
	//scheduler setting
	Timer jobScheduler = new Timer();
	
	jobScheduler.scheduleAtFixedRate(job1, taskcal1.getTime(), 1000*60*60*24);
	jobScheduler.scheduleAtFixedRate(job2, taskcal2.getTime(), 1000*60*60*24);
	jobScheduler.scheduleAtFixedRate(job3, taskcal3.getTime(), 1000*60*60*24);
	
	//Good scheduler
	jobScheduler.scheduleAtFixedRate(Good_job1, Good_taskcal1.getTime(), 1000*60*60*24);
	jobScheduler.scheduleAtFixedRate(Good_job2, Good_taskcal2.getTime(), 1000*60*60*24);
	jobScheduler.scheduleAtFixedRate(Good_job3, Good_taskcal3.getTime(), 1000*60*60*24);

}

}

