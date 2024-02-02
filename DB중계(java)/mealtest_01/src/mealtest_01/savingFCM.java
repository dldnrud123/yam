package mealtest_01;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mealtest_01_DAO.DAO;
import mealtest_01_DAO.FCM_client_key;
import mealtest_01_DTO.DTO_com;
import mealtest_01_DTO.token_list;

/**
 * Servlet implementation class sendFCM
 */
@WebServlet("/savingFCM")
public class savingFCM extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public savingFCM() {
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
		request.setCharacterEncoding("UTF-8");
		String token = request.getParameter("token");
		System.out.println("token_DATA :"+ token);

		if(token != null) {
			FCM_client_key fcm = new FCM_client_key();
			fcm.insert(token);
		}
		PrintWriter out = response.getWriter();
		try {
		out.write("token : " + token);
		}catch(NullPointerException e) {
			out.write("token : 정보가 없습니다.");
		}
		
	}
}


