package com.greedy;

import static com.greedy.common.JDBCTemplate.close;
import static com.greedy.common.JDBCTemplate.getConnection;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class test02 {
	
	/* 2. �μ��� �ٹ��ϴ� ����� ���� �˰� �ʹ�.�μ��� �ٹ��ϴ� ����� ���� ��ȸ�ϴ� ������ ������ּ���*/
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
	      }finally{
	         close(con);
	         close(stmt);
	         close(rset);
	      }  
	      
	}

}