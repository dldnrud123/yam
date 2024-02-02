package mealtest_01_notification;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import mealtest_01_DAO.FCM_client_key;
import mealtest_01_DTO.token_list;

/**
 * Servlet implementation class menu_push
 */
@WebServlet("/noti_select")
public class noti_select extends HttpServlet {
	private static final long serialVersionUID = 1L;
	String token;
	int morning,sun,night;
	

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public noti_select() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		actionDo(request,response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		actionDo(request,response);
	}

	@SuppressWarnings("unchecked")
	private void actionDo (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//시간값 세팅
		
		response.setCharacterEncoding("UTF-8");
		String key = request.getParameter("token");
		
		FCM_client_key fcm = new FCM_client_key();
		ArrayList<token_list> list = fcm.select_noti(key);
		JSONObject jsonMain = new JSONObject(); // 객체 
		JSONObject jObject = new JSONObject(); // JSON내용을 담을 객체. 

		for(token_list client : list) {
			token =client.getToken();
			morning = client.getMorning();
			sun = client.getSun();
			night = client.getNight();

			System.out.println("test2 :"+ token+','+morning+','+sun+','+night);
			
			jObject.put("token", token);
			jObject.put("morning", morning); 
			jObject.put("sun", sun);
			jObject.put("night", night);
			
		}
			
		jsonMain.put("client_key", jObject);  // JSON의 제목 지정 

		PrintWriter out = response.getWriter();
		System.out.println(jsonMain);
		out.println(jsonMain);
		out.flush();

	}
	
}

