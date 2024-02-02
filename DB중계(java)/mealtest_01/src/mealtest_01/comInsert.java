package mealtest_01;

import java.io.IOException;
import java.net.HttpURLConnection;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mealtest_01_DAO.DAO_com;

/**
 * Servlet implementation class comInsert
 */
@WebServlet("/comInsert")
public class comInsert extends HttpServlet {
	private static final long serialVersionUID = 1L;
	String query;
    HttpURLConnection conn;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public comInsert() {
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
	
	private void actionDo (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String time = request.getParameter("time");
		String grade = request.getParameter("grade");
		String com = request.getParameter("com");
		String pass = request.getParameter("pass");
		
		try {
		
		if(!time.equals(null)) {
		System.out.println("in. if ,"+time+","+grade+","+com+","+pass);
		DAO_com dao = new DAO_com();
		dao.insert(time,grade,com,pass);
		}
			
		}catch(NullPointerException e) {
		
	}
}

}
