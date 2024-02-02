package mealtest_01_DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Calendar;
import mealtest_01_DTO.DTO;


public class DAO {

	ArrayList<DTO> list;
	String today, rice, soup, side1, side2, side3, side4, dessert, good;


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
	ResultSet rs;


	public DAO() {

		try {
			list = new ArrayList<DTO>();
			Class.forName(driver);

		} catch (ClassNotFoundException e) {
			e.printStackTrace();

		}
	}


	public ArrayList<DTO> select1() {
		//data 셋팅
		cal.set(Calendar.YEAR,  YEAR);
		cal.set(Calendar.MONTH,  MONTH);
		cal.set(Calendar.DATE,  DATE);
		cal.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);
		String SUN = fm.format(cal.getTime());
		cal.set(Calendar.DAY_OF_WEEK, Calendar.SATURDAY);
		String SAT = fm.format(cal.getTime());
		String where = "WHERE today >='" + SUN +"' AND today <= '" + SAT +"' ";


		try {
			//			String query = "select distinct to_char(today,'YYMMDD') today,rice,soup,side1,side2,side3,side4,dessert,good from breakfast order by today ASC"; 
			String query = "select distinct to_char(today,'YYMMDD') today,rice,soup,side1,side2,side3,side4,dessert,good from breakfast "+where+"order by today ASC";
			System.out.println(query);
			Class.forName("oracle.jdbc.driver.OracleDriver");
			con = DriverManager.getConnection(url, userid, passwd);
			stmt = con.createStatement();
			rs = stmt.executeQuery(query);


			while (rs.next())	{
				today = rs.getString("today");
				rice = rs.getString("rice");
				soup = rs.getString("soup");
				side1 = rs.getString("side1");
				side2 = rs.getString("side2");
				side3 = rs.getString("side3");
				side4 = rs.getString("side4");
				dessert = rs.getString("dessert");
				good = rs.getString("good");
				if(rice == null) {
					list.add(new DTO("", "", "", "학식","정보가","없습니다", "", "", ""));
				}
				else {
					System.out.println(today+','+rice+','+soup+','+side1+','+side2+','+side3+','+side4+','+dessert+','+good);
					list.add(new DTO(today, rice, soup, side1, side2, side3, side4, dessert, good));
				}
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
	}//end select()


	public ArrayList<DTO> select2() {
		//data 셋팅
		cal.set(Calendar.YEAR,  YEAR);
		cal.set(Calendar.MONTH,  MONTH);
		cal.set(Calendar.DATE,  DATE);
		cal.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);
		String SUN = fm.format(cal.getTime());
		cal.set(Calendar.DAY_OF_WEEK, Calendar.SATURDAY);
		String SAT = fm.format(cal.getTime());
		String where = "WHERE today >='" + SUN +"' AND today <= '" + SAT +"' ";

		try {
			//			String query = "select distinct to_char(today,'YYMMDD') today,rice,soup,side1,side2,side3,side4,dessert,good from lunch order by today ASC"; 
			String query = "select distinct to_char(today,'YYMMDD') today,rice,soup,side1,side2,side3,side4,dessert,good from lunch "+where+"order by today ASC";

			Class.forName("oracle.jdbc.driver.OracleDriver");
			con = DriverManager.getConnection(url, userid, passwd);
			stmt = con.createStatement();
			rs = stmt.executeQuery(query);

			while (rs.next())	{

				today = rs.getString("today");
				rice = rs.getString("rice");
				soup = rs.getString("soup");
				side1 = rs.getString("side1");
				side2 = rs.getString("side2");
				side3 = rs.getString("side3");
				side4 = rs.getString("side4");
				dessert = rs.getString("dessert");
				good = rs.getString("good");
				//				if(rice == null) continue;
				if(rice == null) {
					list.add(new DTO("", "", "", "학식","정보가","없습니다", "", "", ""));
				}
				else {
					System.out.println(today+','+rice+','+soup+','+side1+','+side2+','+side3+','+side4+','+dessert+','+good);
					list.add(new DTO(today, rice, soup, side1, side2, side3, side4, dessert, good));
				}
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
	}//end select()

	public ArrayList<DTO> select3() {
		//data 셋팅
		cal.set(Calendar.YEAR,  YEAR);
		cal.set(Calendar.MONTH,  MONTH);
		cal.set(Calendar.DATE,  DATE);
		cal.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);
		String SUN = fm.format(cal.getTime());
		cal.set(Calendar.DAY_OF_WEEK, Calendar.SATURDAY);
		String SAT = fm.format(cal.getTime());
		String where = "WHERE today >='" + SUN +"' AND today <= '" + SAT +"' ";

		try {
			//			String query = "select distinct to_char(today,'YYMMDD') today,rice,soup,side1,side2,side3,side4,dessert,good from dinner order by today ASC"; 
			String query = "select distinct to_char(today,'YYMMDD') today,rice,soup,side1,side2,side3,side4,dessert,good from dinner "+where+"order by today ASC";

			Class.forName("oracle.jdbc.driver.OracleDriver");
			con = DriverManager.getConnection(url, userid, passwd);
			stmt = con.createStatement();
			rs = stmt.executeQuery(query);

			while (rs.next())	{

				today = rs.getString("today");
				rice = rs.getString("rice");
				soup = rs.getString("soup");
				side1 = rs.getString("side1");
				side2 = rs.getString("side2");
				side3 = rs.getString("side3");
				side4 = rs.getString("side4");
				dessert = rs.getString("dessert");
				good = rs.getString("good");
				//				if(rice == null) continue;
				if(rice == null) {
					list.add(new DTO("", "", "", "학식","정보가","없습니다", "", "", ""));
				}
				else {
					System.out.println(today+','+rice+','+soup+','+side1+','+side2+','+side3+','+side4+','+dessert+','+good);
					list.add(new DTO(today, rice, soup, side1, side2, side3, side4, dessert, good));
				}
			}//end while

		} catch (Exception e) {	
			e.printStackTrace();
		} //end try~catch
		finally {
			try {
				System.out.println("Select SQL success!");
				rs.close();
				stmt.close();
				con.close();

			} catch (SQLException se) {
				se.printStackTrace();
			}

		}
		return list;
	}//end select()

	public void insertHeart(String query) {
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
				System.out.println("SQL success!");
			} catch (SQLException se) {
				se.printStackTrace();
			}

		}

	}
}
