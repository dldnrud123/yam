package mealtest_01;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.jasper.tagplugins.jstl.core.Out;

import mealtest_01_DAO.DAO;

/**
 * Servlet implementation class BF_heart
 */
@WebServlet("/BF_heart")
public class BF_heart extends HttpServlet {
	private static final long serialVersionUID = 1L;
    String query;
    HttpURLConnection conn;
    int num;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BF_heart() {
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
		String query = "update breakfast set good = good + (1*"+num+") where today = '"+date+"'";
		System.out.println("in. if ,"+num);
		DAO dao = new DAO();
		dao.insertHeart(query);
		}
			
		}catch(NullPointerException e) {
		
	}
}
}
