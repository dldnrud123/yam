package FCM_notification;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.TimerTask;

class Good_ScheduledJob1 extends TimerTask {


	public void run() {

		FcmPush push = new FcmPush();

		Calendar daycal = Calendar.getInstance();
		int day = daycal.get(Calendar.DAY_OF_WEEK)-1;

		DAO dao = new DAO();
		ArrayList<DTO> dto1 = dao.select1();

		FCM_client_key fcm = new FCM_client_key();
		ArrayList<token_list> tokenlist = fcm.good_Select();
		for(token_list token : tokenlist) {
			try {
				push.Good_pushFCMNotification(token.getToken(), dto1.get(day));
			} catch (Exception e) {
				e.printStackTrace();
				System.out.println("breakfast no menu!!");
			}
		}
	}
}