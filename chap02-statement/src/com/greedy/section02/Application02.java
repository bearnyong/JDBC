package com.greedy.section02;

import static com.greedy.common.JDBCTemplate.getConnection;
import static com.greedy.common.JDBCTemplate.close;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.greedy.model.dto.EmpNameDTO;

public class Application02 {

	public static void main(String[] args) {
		
		Connection con = getConnection();
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		List<EmpNameDTO> empList = null;
		
		
		Scanner sc = new Scanner(System.in);
		System.out.print("조회할 성씨를 입력하세요 : ");
		String empName = sc.nextLine();
		
		String query = "SELECT EMP_ID, EMP_NAME FROM EMPLOYEE WHERE EMP_NAME LIKE ? || '%'";
		
		try {
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, empName);
			
			rset = pstmt.executeQuery();
			empList = new ArrayList<>();
			
			while(rset.next()) {
				EmpNameDTO row = new EmpNameDTO();
				
				row.setEmpId(rset.getString("EMP_ID"));
				row.setEmpName(rset.getString("EMP_NAME"));
				
				empList.add(row);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(con);
			close(pstmt);
			close(rset);
		}
		
		for(EmpNameDTO name : empList) {
			System.out.println(name);
		}
		
	}

}
