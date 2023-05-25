package com.greedy.section01;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Application02 { // Application02�� ���غ���...

	public static void main(String[] args) {
		
		/* �ٲ��� �ʴ� �����̱⿡ finally�� �����Ѵ�..(?) */
		// ������ ���������� �����ֱ�.. �̰� driver ���� ���°ž�~~
		String driver = "oracle.jdbc.driver.OracleDriver"; 
		String url = "jdbc:oracle:thin:@localhost:1521:xe";
		String user = "C##EMPLOYEE";
		String pass = "EMPLOYEE"; //���������� ���ֱ� ������ properties �ڷ� �̿�...
		
		/*������ ����θ� ���� ��� ����� �� �𸣱⿡ �ݾ������.. �׷��� try catch ���°�...? */
		try(Connection con = DriverManager.getConnection(url,user,pass)) { //Ŀ�ؼ��� ������ ����ó�� �� �ڵ����� �ݾ��ش�(close)
			Class.forName(driver);
			System.out.println("con : " +con);
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		
	}

}