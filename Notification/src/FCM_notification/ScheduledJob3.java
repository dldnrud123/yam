package FCM_notification;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.TimerTask;

class ScheduledJob3 extends TimerTask {

	public void run() {

		FcmPush push = new FcmPush();

		Calendar daycal = Calendar.getInstance();
		int day = daycal.get(Calendar.DAY_OF_WEEK)-1;

		DAO dao = new DAO();
		ArrayList<DTO> dto3 = dao.select3();

		FCM_client_key fcm = new FCM_client_key();
		ArrayList<token_list> tokenlist = fcm.night_Select();
		for(token_list token : tokenlist) {
			try {
				push.pushFCMNotification(token.getToken(), dto3.get(day));
			} catch (Exception e) {
				System.out.println("dinner no menu!!");
			}
		}
	}
}