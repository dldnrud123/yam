package FCM_notification;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

import org.json.simple.JSONObject;

public class FcmPush {

	// Method to send Notifications from server to client end.


	public final static String AUTH_KEY_FCM = "AAAAguTYxhE:APA91bGlDpTL4ZgiPTJ3N-eLVuCjeRsDzUhnhAjpjrjTRADKOYowi3WVkS2TjmjEDQj7p5qmim9X0x0FrSDdV2_7tyJdU5nqHGfR-n7JSKVKtxn7KrKVl8TKNWKH4jcbSZ50LzaJWnUX";
	public final static String API_URL_FCM = "https://fcm.googleapis.com/fcm/send";
	public final static int MIN = 40;
	// userDeviceIdKey is the device id you will query from your database

	public static void pushFCMNotification(String userDeviceIdKey, DTO dto) throws Exception {
		String authKey = AUTH_KEY_FCM; // You FCM AUTH key
		String FMCurl = API_URL_FCM;
		String rice, soup, side1, side2, side3, side4, dessert,good;

		rice = dto.getRice();
		soup = dto.getSoup();
		side1 = dto.getSide1();
		side2 = dto.getSide2();
		side3 = dto.getSide3();
		side4 = dto.getSide4();
		dessert = dto.getDessert();
		good = dto.getGood();
		
		if (Integer.parseInt(good)<MIN){
		URL url = new URL(FMCurl);
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();

		conn.setUseCaches(false);
		conn.setDoInput(true);
		conn.setDoOutput(true);
		conn.setRequestMethod("POST");
		conn.setRequestProperty("Authorization", "key=" + authKey);
		conn.setRequestProperty("Content-Type", "application/json");

		JSONObject json = new JSONObject();
		JSONObject info = new JSONObject();

		info.put("title", "**식단표 알림**");
		if(side4 == null){
			info.put("body", rice+"\n"+soup+"\n"+side1+"\n"+side2+"\n"+side3+"\n"+dessert);
		}else{
			info.put("body", rice+"\n"+soup+"\n"+side1+"\n"+side2+"\n"+side3+"\n"+side4+"\n"+dessert); // Notification body
		}
		json.put("data", info);
		json.put("to", userDeviceIdKey.trim());
		json.put("click_action", "OPEN_ACTIVITY");

		if(rice != ""){
			try(OutputStreamWriter wr = new OutputStreamWriter(conn.getOutputStream(), "UTF-8")){

				wr.write(json.toString());
				wr.flush();
			}catch(Exception e){
			}

			if (conn.getResponseCode() != HttpURLConnection.HTTP_OK) {
				throw new RuntimeException("Failed : HTTP error code : " + conn.getResponseCode());
			}

			BufferedReader br = new BufferedReader(new InputStreamReader(
					(conn.getInputStream())));

			String output;
			System.out.println("Output from Server .... \n");
			while ((output = br.readLine()) != null) {
				System.out.println(output);
			}
		}
		conn.disconnect();

		}
	}

	public static void Good_pushFCMNotification(String userDeviceIdKey, DTO dto) throws Exception {
		String authKey = AUTH_KEY_FCM; // You FCM AUTH key
		String FMCurl = API_URL_FCM;
		String rice, soup, side1, side2, side3, side4, dessert,good;

		rice = dto.getRice();
		soup = dto.getSoup();
		side1 = dto.getSide1();
		side2 = dto.getSide2();
		side3 = dto.getSide3();
		side4 = dto.getSide4();
		dessert = dto.getDessert();
		good = dto.getGood();
		
		if (Integer.parseInt(good)>=MIN){

		URL url = new URL(FMCurl);
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();

		conn.setUseCaches(false);
		conn.setDoInput(true);
		conn.setDoOutput(true);
		conn.setRequestMethod("POST");
		conn.setRequestProperty("Authorization", "key=" + authKey);
		conn.setRequestProperty("Content-Type", "application/json");

		JSONObject json = new JSONObject();
		JSONObject info = new JSONObject();

		info.put("title", "**맛있는 메뉴**");
		if(side4 == null){
			info.put("body", rice+"\n"+soup+"\n"+side1+"\n"+side2+"\n"+side3+"\n"+dessert);
		}else{
			info.put("body", rice+"\n"+soup+"\n"+side1+"\n"+side2+"\n"+side3+"\n"+side4+"\n"+dessert); // Notification body
		}
		json.put("data", info);
		json.put("to", userDeviceIdKey.trim());
		json.put("click_action", "OPEN_ACTIVITY");

		if(rice != ""){
			try(OutputStreamWriter wr = new OutputStreamWriter(conn.getOutputStream(), "UTF-8")){

				wr.write(json.toString());
				wr.flush();
			}catch(Exception e){
			}

			if (conn.getResponseCode() != HttpURLConnection.HTTP_OK) {
				throw new RuntimeException("Failed : HTTP error code : " + conn.getResponseCode());
			}

			BufferedReader br = new BufferedReader(new InputStreamReader(
					(conn.getInputStream())));

			String output;
			System.out.println("Output from Server .... \n");
			while ((output = br.readLine()) != null) {
				System.out.println(output);
			}
		}
		conn.disconnect();

		}
	}
}


