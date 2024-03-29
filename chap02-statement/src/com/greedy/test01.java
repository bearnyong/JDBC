package com.greedy;

import static com.greedy.common.JDBCTemplate.close;
import static com.greedy.common.JDBCTemplate.getConnection;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class test01 {
	
	/* 1. 사원의 이름을 입력 받고 사원의 부서 직급 근무 지역을 조회하세요*/
	public static void main(String[] args) {
		
		Connection con = getConnection();
	    Statement stmt = null;
	    ResultSet rset = null;
	      
	    try {
	       stmt = con.createStatement();
	         
	       System.out.print("사원의 이름을 입력해 주세요 : ");
	       Scanner sc = new Scanner(System.in);
	         
	       String empname = sc.nextLine();
	         
	       String query = "SELECT D.DEPT_TITLE, J.JOB_NAME, L.LOCAL_NAME FROM EMPLOYEE E\r\n" + 
	         			  "JOIN DEPARTMENT D ON(E.DEPT_CODE = D.DEPT_ID)\r\n" + 
	         			  "JOIN JOB J ON(E.JOB_CODE = J.JOB_CODE)\r\n" + 
	         			  "JOIN LOCATION L ON(L.LOCAL_CODE = D.LOCATION_ID)\r\n" + 
	         			  "WHERE EMP_NAME =  '"+ empname +"'";
	       System.out.println(query);
	       rset = stmt.executeQuery(query);
	         
	       if(rset.next()) {
	          System.out.println(rset.getString("DEPT_TITLE")+" , "+rset.getString("JOB_NAME")+" , " + rset.getString("LOCAL_NAME"));
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
