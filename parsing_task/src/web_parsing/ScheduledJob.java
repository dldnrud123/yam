package web_parsing;

import java.util.Calendar;
import java.util.Timer;
import java.util.TimerTask;

public class ScheduledJob extends TimerTask{
	public void run() {
		System.out.println("MAIN task start!!");
		try {
			Thread.sleep(20000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Calendar now = Calendar.getInstance();
		System.out.println(now.getTime());
		
		Calendar checkcal = Calendar.getInstance();
		checkcal.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);

		menu_parsing mp = new menu_parsing();
		mp.delete_menu();
		mp.parsing();

		java.text.SimpleDateFormat fm = new java.text.SimpleDateFormat("yyyyMMdd");
		String MON = fm.format(checkcal.getTime());
		
		Timer subScheduler = new Timer();

		if(mp.check_DATA(MON)) {
			SubScheduledJob job2 = new SubScheduledJob();
			subScheduler.scheduleAtFixedRate(job2, 0, 1000*60*60*6); //6시간마다
			cancel();
		}

	}


}
