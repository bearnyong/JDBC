package com.greedy.section03;

import static com.greedy.common.JDBCTemplate.getConnection;
import static com.greedy.common.JDBCTemplate.close;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Application01 {
	
	private static String empId = "200";
	private static String empName = "'OR 1=1 and emp_id = '201";

	public static void main(String[] args) {
		
		Connection con = getConnection();
		
		Statement stmt = null;
		ResultSet rset = null;
		
		String query = "SELECT * FROM EMPLOYEE WHERE EMP_ID='" + empId+"' AND EMP_NAME='"+empName+"'";
		
		System.out.println(query);
		
		try {
			stmt = con.createStatement();
			rset = stmt.executeQuery(query);
			
			if(rset.next()) {
				System.out.println(rset.getString("EMP_NAME")+"님 환영합니다.");
			} else {
				System.out.println("회원정보가 없습니다.");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			close(con);
			close(stmt);
			close(rset);
		}

	}

}
