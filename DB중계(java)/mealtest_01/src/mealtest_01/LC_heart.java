package mealtest_01;

import java.io.IOException;
import java.net.HttpURLConnection;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mealtest_01_DAO.DAO;

/**
 * Servlet implementation class LC_heart
 */
@WebServlet("/LC_heart")
public class LC_heart extends HttpServlet {
	private static final long serialVersionUID = 1L;
	String query;
	HttpURLConnection conn;
	int num;
	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public LC_heart() {
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
		request.setCharacterEncoding("UTF-8");
		String date = request.getParameter("date");
		String type = request.getParameter("type");
		System.out.println(date+","+type);
		
		try {
		if(type.equals("1")) {
			num = 1;
		}
		else if(type.equals("2")) {
			num = -1;
		}
		if(!date.equals(null)) {
		String query = "update lunch set good = good + (1*"+num+") where today = '"+date+"'";
		System.out.println("in. if ,"+num);
		DAO dao = new DAO();
		dao.insertHeart(query);
		}
			
		}catch(NullPointerException e) {
		
	}
	}
}