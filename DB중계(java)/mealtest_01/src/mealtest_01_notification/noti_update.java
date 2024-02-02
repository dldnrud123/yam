package mealtest_01_notification;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mealtest_01_DAO.DAO;
import mealtest_01_DAO.FCM_client_key;

/**
 * Servlet implementation class noti_update
 */
@WebServlet("/noti_update")
public class noti_update extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public noti_update() {
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
		String key = request.getParameter("key");
		String morning = request.getParameter("morning");
		String sun = request.getParameter("sun");
		String night = request.getParameter("night");
		
		System.out.println(key+"better success!");
		
		if(!key.equals(null)) {
		String query = "update FCM_CLIENT_KEY set morning = "+morning+", sun = "+sun+", night = "+night+" where token = '"+key+"'";
		FCM_client_key dao = new FCM_client_key();
		dao.updateNoti(query);
		}
		
	}
}