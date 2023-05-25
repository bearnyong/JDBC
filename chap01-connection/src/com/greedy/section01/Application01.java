package com.greedy.section01;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Application01 {

	public static void main(String[] args) {
		
		// 객체 지향 설계 원칙
		// 의존성을 줄이고 유지보수성 업
		// 수정이 자주 일어나는 상황을 줄이기 위함.
		// 내부에서 수정해도 외부에서 알지 못하도록 함
		// DBMS-JAVA와의 연결을 위해 JDBC 사용
		
		
		/* DB 접속을 위한 Connection 인스턴스 생성을 위한 래퍼런스 변수 선언
		 * 나중에 finally 블럭에서 사용하기 위해 try 블럭 밖에 선언함*/

		
		Connection con = null;
		
		try {
			
			Class.forName("oracle.jdbc.driver.OracleDriver"); //값을 넣어줌... 드라이브 연결 (lib)
			con = DriverManager/*클래스?*/.getConnection("jdbc:oracle:thin:@localhost:1521:xe"/*localhost : 내 자신에게 요정 받기, 기본 내장 1521포트로 제힌, xe : 오라클에서 정한 접속 규칙*/
											 		  ,"C##EMPLOYEE"/*사용할 계정(id)*/
											 		  ,"EMPLOYEE"/*pwd*/);
			System.out.println("con : " + con); // 여기서 레일
			
		} catch (ClassNotFoundException e) { //forName을 찾지 못할 때, ..
			e.printStackTrace();
			
		} catch (SQLException e) { //쿼리를 돌리거나 로그인을 할 때 발생할 수 있는 에러
			e.printStackTrace();
			
		} finally { //try 예외처리, 예외를 처리하는 구문 cacth가 실행됨, 그리고 finally...
			if(con != null) { //con에 값이 있을 때...(연결됐을떄)
				try {
					con.close(); //알지 못하는 에러가 발생할 수 있으므로 꼭 닫아주기..
				} catch(SQLException e) {
					e.printStackTrace();
				}
			}
		}
		
		
	}

}
