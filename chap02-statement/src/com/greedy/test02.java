package com.greedy;

import static com.greedy.common.JDBCTemplate.close;
import static com.greedy.common.JDBCTemplate.getConnection;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class test02 {
	
	/* 2. 부서별 근무하는 사원의 수를 알고 싶다.부서별 근무하는 사원의 수를 조회하는 쿼리를 만들어주세요*/
	public static void main(String[] args) {
		
		Connection con = getConnection();
		Statement stmt = null;
	      
	    ResultSet rset = null;
	      
	    try {
	       stmt = con.createStatement();
	       rset = stmt.executeQuery("SELECT D.DEPT_TITLE, COUNT(*)\r\n" + 
	         						"FROM EMPLOYEE E\r\n" + 
	         						"JOIN DEPARTMENT D ON(E.DEPT_CODE = D.DEPT_ID)\r\n" + 
	         						"GROUP BY DEPT_TITLE");
	          
	       while(rset.next()) {
	          System.out.println(rset.getString("DEPT_TITLE") + " , " + rset.getString("COUNT(*)"));
	       }
	    } catch (SQLException e) {
	       e.printStackTrace();
	    } finally{
	       close(con);
	       close(stmt);
	       close(rset);
	    }  
	      
	}

}
