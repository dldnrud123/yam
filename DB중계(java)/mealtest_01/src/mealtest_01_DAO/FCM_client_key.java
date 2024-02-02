package mealtest_01_DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import mealtest_01_DTO.DTO_com;
import mealtest_01_DTO.token_list;

public class FCM_client_key {
	ArrayList<token_list> list;
	String token;
	int morning,sun,night;

//	String userid = "bruce92";
//	String passwd = "starrhtn1";
	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:XE";
		String userid = "scott";
		String passwd = "tiger";

	Connection con;
	PreparedStatement psmt;
	Statement stmt;
	ResultSet rs;

	public FCM_client_key() {
		try {
			list = new ArrayList<token_list>();
			Class.forName(driver);

		} catch (ClassNotFoundException e) {

			e.printStackTrace();

		}
	}



	public void insert(String token){
		try { 
			String query = "insert INTO FCM_CLIENT_KEY (token,morning,sun,night) values (?,?,?,?)";
			System.out.println("FCM key save : "+ query);
			Class.forName("oracle.jdbc.driver.OracleDriver");

			con = DriverManager.getConnection(url, userid, passwd);
			psmt = con.prepareStatement(query);
			psmt.setString(1, token);
			psmt.setInt(2, 1);
			psmt.setInt(3, 1);
			psmt.setInt(4, 1);
			psmt.executeUpdate();


		} catch (Exception e) {	
			e.printStackTrace();
		} //end try~catch
		finally {
			try {

				psmt.close();
				con.close();
				System.out.println("key saving success!");
			} catch (SQLException se) {
				se.printStackTrace();
			}

		}

	}
	public ArrayList<token_list> select() {

		try {
			String query = "select * from FCM_CLIENT_KEY";
			System.out.println(query);
			Class.forName("oracle.jdbc.driver.OracleDriver");

			con = DriverManager.getConnection(url, userid, passwd);
			stmt = con.createStatement();
			rs = stmt.executeQuery(query);

			while (rs.next())	{
				token = rs.getString("token");
				morning = rs.getInt("morning");
				sun = rs.getInt("sun");
				night = rs.getInt("night");
				System.out.println(token+"\n"+morning+"\n"+sun+"\n"+night);
				list.add(new token_list(token,morning,sun,night));
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
	public ArrayList<token_list> select_noti(String key) {

		try {
			String query = "select * from FCM_CLIENT_KEY where token = '"+key+"'";
			System.out.println(query);
			Class.forName("oracle.jdbc.driver.OracleDriver");

			con = DriverManager.getConnection(url, userid, passwd);
			stmt = con.createStatement();
			rs = stmt.executeQuery(query);

			while (rs.next())	{
				token = rs.getString("token");
				morning = rs.getInt("morning");
				sun = rs.getInt("sun");
				night = rs.getInt("night");
				System.out.println(token+"\n"+morning+"\n"+sun+"\n"+night);
				list.add(new token_list(token,morning,sun,night));
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

	public void updateNoti(String query) {
		System.out.println("SQL start"+ query);
		try { 
			Class.forName("oracle.jdbc.driver.OracleDriver");
			con = DriverManager.getConnection(url, userid, passwd);
			stmt = con.createStatement();
			stmt.executeUpdate(query);

		} catch (Exception e) {	
			e.printStackTrace();
		} //end try~catch
		finally {
			try {

				stmt.close();
				con.close();
				System.out.println("Notification update success!");
			} catch (SQLException se) {
				se.printStackTrace();
			}

		}

	}


}
