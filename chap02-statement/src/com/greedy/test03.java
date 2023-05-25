package com.greedy;

import static com.greedy.common.JDBCTemplate.close;
import static com.greedy.common.JDBCTemplate.getConnection;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class test03 {

	public static void main(String[] args) {
		
		/*부서를 입력받아 해당 부서에 근무하는 사원의 수를 조회하세요*/
		Connection con = getConnection();
	    Statement stmt = null;
	    ResultSet rset = null;
	      
	    try {
	       stmt = con.createStatement();
	         
	       System.out.println("조회할 사번을 입력하세요 : ");
	       Scanner sc = new Scanner(System.in);
	         
	       String deptcode = sc.nextLine();
	         
	       String query = "SELECT COUNT(*)\r\n" + 
	         			  "FROM EMPLOYEE E\r\n" + 
	         			  "JOIN DEPARTMENT D ON(D.DEPT_ID = E.DEPT_CODE)\r\n" + 
	         			  "WHERE DEPT_TITLE = '"+ deptcode +"'";
	       System.out.println(query);
	       rset = stmt.executeQuery(query);
	         
	       if(rset.next()) {
	          System.out.println(rset.getString("COUNT(*)"));
	       }
	    }catch (SQLException e) {
	       e.printStackTrace();
	    } finally {
	       close(con);
	       close(rset);
	       close(stmt);
	    }
		
	}

}
