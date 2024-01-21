package web_parsing;

import java.util.Calendar;
import java.util.Timer;
import java.util.TimerTask;

public class SubScheduledJob extends TimerTask{
	public void run() {

		System.out.println("Sub task start!!");

		try {
			Thread.sleep(20000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//날짜의 메뉴가 null인가 확인하기위한 day_data_set
		Calendar checkcal = Calendar.getInstance();
		checkcal.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);

		//다시 delete 후 재parsing
		menu_parsing mp = new menu_parsing();
		mp.delete_menu();
		mp.parsing();

		//월요일의 data_format_set
		java.text.SimpleDateFormat fm = new java.text.SimpleDateFormat("yyyyMMdd");
		String MON = fm.format(checkcal.getTime());

		//스케줄링 시간
		Calendar taskcal = Calendar.getInstance();

		taskcal.set(Calendar.DAY_OF_WEEK, Calendar.SATURDAY);
		taskcal.set(Calendar.HOUR_OF_DAY, 23);
		taskcal.set(Calendar.MINUTE, 59);
		taskcal.set(Calendar.SECOND, 59);

		Timer jobScheduler = new Timer();
		//null->false +! => true 일주일이 모두 나올경우 다시 Main 작업 예약
		if(!mp.check_DATA(MON)) {
			System.out.println("Next week ready!");
			ScheduledJob job3 = new ScheduledJob();
			jobScheduler.scheduleAtFixedRate(job3,taskcal.getTime() , 1000*60*60*24*7); //1000ms*60(1m)*60(1h)*24(day)*7(week)
			cancel();
		}

	}


}
