package mealtest_01;

import mealtest_01_DAO.DAO;
import mealtest_01_DTO.DTO;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLEncoder;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.jasper.tagplugins.jstl.core.Out;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

/**
 * Servlet implementation class LoginOk
 */
@WebServlet("/LC")
public class LC extends HttpServlet {
	private static final long serialVersionUID = 1L;
	//	Connection con;
	//	Statement stmt;
	//	ResultSet rs;
	String today, rice, soup, side1, side2, side3, side4, dessert, good;


	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public LC() {
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


		response.setCharacterEncoding("UTF-8");
		DAO dao = new DAO();
		ArrayList<DTO> list = dao.select2();	
		System.out.println(list.get(0).getRice());
		JSONObject jsonMain = new JSONObject(); // 객체 
		JSONArray jArray = new JSONArray(); // 배열 
		JSONObject jObject = new JSONObject(); // JSON내용을 담을 객체. 

		for(DTO dto : list) {
			today = dto.getToday();
			rice = dto.getRice();
			soup = dto.getSoup();
			side1 = dto.getSide1();
			side2 = dto.getSide2();
			side3 = dto.getSide3();
			side4 = dto.getSide4();
			dessert = dto.getDessert();
			good = dto.getGood();

			jObject.put("today", today);
			jObject.put("rice", rice); 
			jObject.put("soup", soup);
			jObject.put("side1", side1);
			jObject.put("side2", side2);
			jObject.put("side3", side3);
			jObject.put("side4", side4);
			jObject.put("dessert", dessert);
			jObject.put("good", good);

			jArray.add(jObject.clone()); // 복제해야 중복이안됨
		}

		jsonMain.put("LC", jArray); // JSON의 제목 지정 

		PrintWriter out = response.getWriter();
		System.out.println(jsonMain);
		out.println(jsonMain);
		out.flush();



	}
}

