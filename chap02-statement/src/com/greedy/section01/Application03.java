package com.greedy.section01;

import static com.greedy.common.JDBCTemplate.getConnection;
import static com.greedy.common.JDBCTemplate.close;


import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

import com.greedy.model.dto.EmployeeDTO;

public class Application03 {

	public static void main(String[] args) {
		Connection con = getConnection();
		Statement stmt = null;
		ResultSet rset = null;
		
		EmployeeDTO selectEmp = null; //Emplyee DTO 만들기
		
		Scanner sc = new Scanner(System.in);
		System.out.print("조회할 사번을 입력해주세요 : ");
		String empId = sc.nextLine();
		
		String query = "SELECT * FROM EMPLOYEE WHERE EMP_ID = '" + empId + "'";
		
		try {
			
			stmt = con.createStatement();
			rset = stmt.executeQuery(query);
			
			if(rset.next()) {
				
				selectEmp = new EmployeeDTO();
				
				selectEmp.setEmpId(rset.getString("EMP_ID"));
				selectEmp.setEmpName(rset.getString("EMP_NAME"));
				selectEmp.setEmpNo(rset.getString("EMP_NO"));
				selectEmp.setEmail(rset.getString("EMAIL"));
				selectEmp.setPhone(rset.getString("PHONE"));
				selectEmp.setDeptCode(rset.getString("DEPT_CODE"));
				selectEmp.setJobCode(rset.getString("JOB_CODE"));
				selectEmp.setSalary(rset.getInt("SALARY"));
				selectEmp.setBonus(rset.getDouble("BONUS"));
				selectEmp.setHireDate(rset.getDate("HIRE_DATE"));
				selectEmp.setEntDate(rset.getDate("ENT_DATE"));
				selectEmp.setEntYn(rset.getString("ENT_YN"));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(stmt);
			close(con);
		}
		
//		System.out.println(selectEmp.getEmpId() + " , \n" +
//						   selectEmp.getEmpName() + " , \n" +
//						   selectEmp.getEmpNo() + " , \n" +
//						   selectEmp.getEmail() + " , \n" +
//						   selectEmp.getPhone()+ " , \n" +
//						   selectEmp.getDeptCode() + " , \n" +
//						   selectEmp.getJobCode() + " , \n" +
//						   selectEmp.getSalary() + " , \n" +
//						   selectEmp.getBonus() + " , \n" +
//						   selectEmp.getHireDate() + " , \n" +
//						   selectEmp.getEntDate() + " , \n" +
//						   selectEmp.getEntYn());
		
		System.out.println(selectEmp); //toSting이 기본값이 되어 출력된다.
		
	}

}
