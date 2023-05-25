package com.greedy.section01;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Application02 { // Application02과 비교해보기...

	public static void main(String[] args) {
		
		/* 바뀌지 않는 값들이기에 finally로 관리한다..(?) */
		// 변수명 직관적으로 적어주기.. 이건 driver 값이 들어가는거야~~
		String driver = "oracle.jdbc.driver.OracleDriver"; 
		String url = "jdbc:oracle:thin:@localhost:1521:xe";
		String user = "C##EMPLOYEE";
		String pass = "EMPLOYEE"; //개인정보가 들어가있기 때문에 properties 자료 이용...
		
		/*파일을 열어두면 언제 어디서 실행될 지 모르기에 닫아줘야함.. 그래서 try catch 쓰는거...? */
		try(Connection con = DriverManager.getConnection(url,user,pass)) { //커넥션이 없으면 예외처리 후 자동으로 닫아준다(close)
			Class.forName(driver);
			System.out.println("con : " +con);
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		
	}

}
