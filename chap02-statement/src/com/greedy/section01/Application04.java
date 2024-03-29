package com.greedy.section01;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.greedy.common.JDBCTemplate;
import com.greedy.model.dto.EmployeeDTO;

public class Application04 {

	public static void main(String[] args) {
		
		Connection con = JDBCTemplate.getConnection();
		Statement stmt = null;
		ResultSet rset = null;
		
		List<EmployeeDTO> empList = null;  // <> 제네릭타입 : 타입을 일반화 시킴 , 컴파일 시 타입이 결정됨. EmployeeDTO자료를 참고하는 List를 생성한거임..
		
		String query = "SELECT * FROM EMPLOYEE";
		
		
		try {
			stmt = con.createStatement();
			rset = stmt.executeQuery(query);
			
			empList = new ArrayList<>();
			
			while(rset.next()){
				EmployeeDTO row = new EmployeeDTO();
				
				row.setEmpId(rset.getString("EMP_ID"));
				row.setEmpName(rset.getString("EMP_NAME"));
				row.setEmpNo(rset.getString("EMP_NO"));
				row.setEmail(rset.getString("EMAIL"));
				row.setPhone(rset.getString("PHONE"));
				row.setDeptCode(rset.getString("DEPT_CODE"));
				row.setJobCode(rset.getString("JOB_CODE"));
				row.setSalary(rset.getInt("SALARY"));
				row.setBonus(rset.getDouble("BONUS"));
				row.setHireDate(rset.getDate("HIRE_DATE"));
				row.setEntDate(rset.getDate("ENT_DATE"));
				row.setEntYn(rset.getString("ENT_YN"));
				
				empList.add(row);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(con);
			JDBCTemplate.close(stmt);
			JDBCTemplate.close(rset);
		}
		
		for(EmployeeDTO emp : empList) {
			System.out.println(emp);
		}
		
	}

}
