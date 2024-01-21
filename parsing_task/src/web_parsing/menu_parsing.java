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
	java.text.SimpleDateFormat fm = new java.text.SimpleDateFormat("yyyyMMdd"); //�ð� ���� yyyyMMdd�� ����

	//URL������ ���� �ð��� Ķ�������� ��������
	int YEAR = cal.get(Calendar.YEAR);
	int MONTH = cal.get(Calendar.MONTH);
	int DATE = cal.get(Calendar.DATE);

	public void parsing() {
		String TODAY;

		//url����

		String URL = "http://tdorm.gnu.ac.kr/sub/04_04.jsp?"+
				"year=" + YEAR + "&month=" + (MONTH+1) + "&date=" + DATE;
		// �Ĵ� 7�� �� 21���� �Ļ� 0~6 ���� 7~13 ���� 14~20 ����
		String[][] menu = new String[21][7];

		String[] a = new String[30];
		String[] row = new String[7];
		//jsoup lib tag => �� �Ľ�
		try {
			Document doc = Jsoup.connect(URL).get();
			Elements table = doc.select("table.tbl-type01 td");
			int num = 0;

			for (Element element : table) {
				a[num] = element.text();
				num++;
			}

			for (int i = 0; i < 21; i++) {
				row = StringUtils.split(a[i], ' '); //String_utils lib => �������� ������ �ڸ���
				for (int j = 0; j < row.length; j++) {
					menu[i][j] = row[j];
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		//-------------------------------------------------------------------------------- DB
		try{
			Class.forName("oracle.jdbc.driver.OracleDriver"); //oracle ���� ��
			System.out.println("����̹� �ε� ����...");
		}catch(Exception e) {
			e.printStackTrace();
		}

		try {
			con = DriverManager.getConnection(url,userid,passwd);

			System.out.println("DB ���� ����!");

			String[] sql = new String[3]; // multi query ����� ���� �迭
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
					int n = psmt.executeUpdate(); // Ŀ�� �ѰͰ� ���� ��� => n=1�ϰ�� ����
					if(n == 1) {
						System.out.println("update success!");
					}
					psmt.clearParameters(); // �Ķ���͸� Ŭ�����Ͽ� psmt ����
				}

				psmt.close(); //������ �ݱ�
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
			System.out.println("����̹� �ε� ����...");
		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		try { 
			String[] sql = new String[3]; // multi query ����� ���� �迭

			sql[0]="delete from breakfast "+where;
			sql[1]="delete from lunch "+where;
			sql[2]="delete from dinner "+where;

			con = DriverManager.getConnection(url, userid, passwd);
			System.out.println("DB ���� ����!");

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



