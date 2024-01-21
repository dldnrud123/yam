package web_parsing;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Calendar;

import org.apache.commons.lang3.StringUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class menu_parsing {
	String userid = "bruce92";
	String passwd = "starrhtn1";
	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:XE";
	//	String userid = "scott";
	//	String passwd = "tiger";

	Connection con;
	Statement stmt;
	PreparedStatement psmt;
	ResultSet rs;

	String rice;

	Calendar cal = Calendar.getInstance();
	Calendar wherecal = Calendar.getInstance();
	java.text.SimpleDateFormat fm = new java.text.SimpleDateFormat("yyyyMMdd"); //시간 포멧 yyyyMMdd로 셋팅

	//URL셋팅을 위한 시간값 캘린더에서 가져오기
	int YEAR = cal.get(Calendar.YEAR);
	int MONTH = cal.get(Calendar.MONTH);
	int DATE = cal.get(Calendar.DATE);

	public void parsing() {
		String TODAY;

		//url셋팅

		String URL = "http://tdorm.gnu.ac.kr/sub/04_04.jsp?"+
				"year=" + YEAR + "&month=" + (MONTH+1) + "&date=" + DATE;
		// 식단 7개 총 21번의 식사 0~6 조식 7~13 점심 14~20 석식
		String[][] menu = new String[21][7];

		String[] a = new String[30];
		String[] row = new String[7];
		//jsoup lib tag => 웹 파싱
		try {
			Document doc = Jsoup.connect(URL).get();
			Elements table = doc.select("table.tbl-type01 td");
			int num = 0;

			for (Element element : table) {
				a[num] = element.text();
				num++;
			}

			for (int i = 0; i < 21; i++) {
				row = StringUtils.split(a[i], ' '); //String_utils lib => 패턴으로 데이터 자르기
				for (int j = 0; j < row.length; j++) {
					menu[i][j] = row[j];
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		//-------------------------------------------------------------------------------- DB
		try{
			Class.forName("oracle.jdbc.driver.OracleDriver"); //oracle 접속 부
			System.out.println("드라이버 로딩 성공...");
		}catch(Exception e) {
			e.printStackTrace();
		}

		try {
			con = DriverManager.getConnection(url,userid,passwd);

			System.out.println("DB 연결 성공!");

			String[] sql = new String[3]; // multi query 사용을 위한 배열
			sql[0]="insert into BREAKFAST(today,rice,soup,side1,side2,side3,side4,dessert,good) values(?,?,?,?,?,?,?,?,0)";
			sql[1]="insert into LUNCH(today,rice,soup,side1,side2,side3,side4,dessert,good) values(?,?,?,?,?,?,?,?,0)";
			sql[2]="insert into DINNER(today,rice,soup,side1,side2,side3,side4,dessert,good) values(?,?,?,?,?,?,?,?,0)";
			int i=0;
			for(int k=0; k<3; k++){
				PreparedStatement psmt = con.prepareStatement(sql[k]); // psmt 
				for ( ; i < 7*(k+1); i++) {

					cal.set(Calendar.DAY_OF_WEEK, (i%7)+1);
					TODAY = fm.format(cal.getTime());
					
					for (int j = 0; j <= 7; j++) {


						if(j==0) { 
							psmt.setString(1,TODAY);
						}
						else {
							if(menu[i][j-1]==null && j==7) {
								psmt.setString(j,"");
								psmt.setString(j+1,menu[i][j-2]);

							}
							else if(menu[i][j-1]==null && j!=7) {
								psmt.setString(j+1,"");
							}

							else {
								psmt.setString(j+1,menu[i][j-1]);
								System.out.println(menu[i][j-1]);
							}
						}

					}
					int n = psmt.executeUpdate(); // 커밋 한것과 같은 결과 => n=1일경우 성공
					if(n == 1) {
						System.out.println("update success!");
					}
					psmt.clearParameters(); // 파라미터를 클리어하여 psmt 재사용
				}

				psmt.close(); //마지막 닫기
			}

			con.close();
		} catch (SQLException e) {

			e.printStackTrace();
		}

	}

	public void delete_menu() {
		wherecal.set(Calendar.YEAR,  YEAR);
		wherecal.set(Calendar.MONTH,  MONTH);
		wherecal.set(Calendar.DATE,  DATE);

		wherecal.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);
		String SUN = fm.format(wherecal.getTime());

		wherecal.set(Calendar.DAY_OF_WEEK, Calendar.SATURDAY);
		String SAT = fm.format(wherecal.getTime());

		String where = "WHERE today >='" + SUN +"' AND today <= '" + SAT +"' ";

		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			System.out.println("드라이버 로딩 성공...");
		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		try { 
			String[] sql = new String[3]; // multi query 사용을 위한 배열

			sql[0]="delete from breakfast "+where;
			sql[1]="delete from lunch "+where;
			sql[2]="delete from dinner "+where;

			con = DriverManager.getConnection(url, userid, passwd);
			System.out.println("DB 연결 성공!");

			for(int k=0; k<3; k++){
				System.out.println("SQL start"+ sql[k]);
				psmt = con.prepareStatement(sql[k]);
				psmt.executeUpdate();
				System.out.println("delete "+k+"success!");
			}

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

	public boolean check_DATA(String MON) {

		String where = "WHERE today ='" + MON +"'";

		try {
			String query = "select * from breakfast "+where;
			System.out.println(query);
			Class.forName("oracle.jdbc.driver.OracleDriver");
			con = DriverManager.getConnection(url, userid, passwd);
			stmt = con.createStatement();
			rs = stmt.executeQuery(query);

			while (rs.next())	{
				rice = rs.getString("rice");
			}
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
		if(rice == null) {
			System.out.println("true");
			return true;
		}
		else {
			System.out.println(rice);
			System.out.println("fail");
			return false;
		}

	}

}



