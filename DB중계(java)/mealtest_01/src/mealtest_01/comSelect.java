package mealtest_01;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import mealtest_01_DAO.DAO_com;
import mealtest_01_DTO.DTO_com;

/**
 * Servlet implementation class comSelect
 */
@WebServlet("/comSelect")
public class comSelect extends HttpServlet {
	private static final long serialVersionUID = 1L;
	HttpURLConnection conn;
	String time, grade, com, pass;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public comSelect() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		actionDo(request,response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		actionDo(request,response);
	}
	@SuppressWarnings("unchecked")
	private void actionDo (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		response.setCharacterEncoding("UTF-8");
		DAO_com dao = new DAO_com();
		ArrayList<DTO_com> list = dao.select();
		JSONObject jsonMain = new JSONObject(); // 객체 
		JSONArray jArray = new JSONArray(); // 배열 
		JSONObject jObject = new JSONObject(); // JSON내용을 담을 객체. 

		for(DTO_com dto : list) {
			time = dto.getTime();
			grade = dto.getGrade();
			com = dto.getCom();
			pass = dto.getPass();

			System.out.println("test2 :"+ time+','+grade+','+com+','+pass);
			
			jObject.put("time", time);
			jObject.put("grade", grade); 
			jObject.put("com", com);
			jObject.put("pass", pass);
			
			jArray.add(jObject.clone()); // 복제해야 중복이안됨
		}
			
		jsonMain.put("communication", jArray);  // JSON의 제목 지정 

		PrintWriter out = response.getWriter();
		System.out.println(jsonMain);
		out.println(jsonMain);
		out.flush();
	
		
}
}
