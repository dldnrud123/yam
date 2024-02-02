package mealtest_01_DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Calendar;

import mealtest_01_DTO.DTO_com;

public class DAO_com {
	ArrayList<DTO_com> list;

	String time,grade,com,pass;


//	String userid = "bruce92";
//	String passwd = "starrhtn1";
	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:XE";
		String userid = "scott";
		String passwd = "tiger";

	Calendar cal = Calendar.getInstance();
	java.text.SimpleDateFormat fm = new java.text.SimpleDateFormat("yyyyMMdd"); //시간 포멧 yyyyMMdd로 셋팅

	//URL셋팅을 위한 시간값 캘린더에서 가져오기
	int YEAR = cal.get(Calendar.YEAR);
	int MONTH = cal.get(Calendar.MONTH);
	int DATE = cal.get(Calendar.DATE);

	Connection con;
	Statement stmt;
	PreparedStatement psmt;
	ResultSet rs;

	public DAO_com() {

		try {
			list = new ArrayList<DTO_com>();
			Class.forName(driver);

		} catch (ClassNotFoundException e) {

			e.printStackTrace();

		}
	}


	public ArrayList<DTO_com> select() {
		//data 셋팅
		cal.set(Calendar.YEAR,  YEAR);
		cal.set(Calendar.MONTH,  MONTH);
		cal.set(Calendar.DATE,  DATE);
		cal.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);

		String SUN = fm.format(cal.getTime());
		String SAT = Integer.toString((Integer.parseInt(SUN)+6));
		String where = "WHERE today >='" + SUN +"' AND today <= '" + SAT +"' ";


		try {
			String query = "select distinct * from communication order by timedata DESC"; 
			//			String query = "select distinct to_char(today,'YYMMDD') today,rice,soup,side1,side2,side3,side4,dessert,good from breakfast "+where+"order by today ASC";
			System.out.println(query);
			Class.forName("oracle.jdbc.driver.OracleDriver");
			con = DriverManager.getConnection(url, userid, passwd);
			stmt = con.createStatement();
			rs = stmt.executeQuery(query);

			while (rs.next())	{
				time = rs.getString("time");
				grade = rs.getString("grade");
				com = rs.getString("com");
				pass = rs.getString("pass");
				if(time == null) continue;
				System.out.println(time+','+grade+','+com+','+pass);
				list.add(new DTO_com(time,grade,com,pass));
			}//end while

		} catch (Exception e) {	
			e.printStackTrace();
		} //end try~catch
		finally {
			try {

				rs.close();
				stmt.close();
				con.close();

			} catch (SQLException se) {
				se.printStackTrace();
			}

		}
		return list;
	}

	public void insert(String time, String grade, String com, String pass){
		try { 
			String query = "insert into communication (timedata,time,grade,com,pass) values(sysdate,?,?,?,?)";
			System.out.println("SQL start"+ query);
			Class.forName("oracle.jdbc.driver.OracleDriver");
			con = DriverManager.getConnection(url, userid, passwd);
			psmt = con.prepareStatement(query);
			psmt.setString(1, time);
			psmt.setString(2, grade);
			psmt.setString(3, com);
			psmt.setString(4, pass);
			psmt.executeUpdate();

		} catch (Exception e) {	
			e.printStackTrace();
		} //end try~catch
		finally {
			try {

				psmt.close();
				con.close();
				System.out.println("SQL success!");
			} catch (SQLException se) {
				se.printStackTrace();
			}

		}

	}

	public void delete(String time, String grade, String com, String pass) {
		try { 
			String query = "delete from COMMUNICATION where time = ? and grade = ? and com = ? and pass = ?";
			System.out.println("SQL start"+ query);
			Class.forName("oracle.jdbc.driver.OracleDriver");
			con = DriverManager.getConnection(url, userid, passwd);
			psmt = con.prepareStatement(query);
			psmt.setString(1, time);
			psmt.setString(2, grade);
			psmt.setString(3, com);
			psmt.setString(4, pass);
			psmt.executeUpdate();

		} catch (Exception e) {	
			e.printStackTrace();
		} //end try~catch
		finally {
			try {

				psmt.close();
				con.close();
				System.out.println("SQL success!");
			} catch (SQLException se) {
				se.printStackTrace();
			}

		}

	}
}
