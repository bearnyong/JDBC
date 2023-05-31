package com.greedy.section01.insert;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.InvalidPropertiesFormatException;
import java.util.Properties;
import java.util.Scanner;

import com.greedy.common.JDBCTemplate;
import com.greedy.model.dto.EmployeetestDTO;

public class Application04 {

	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		System.out.println("메뉴를 선택해 주세요 (1:사원등록, 2:사원조회) : ");
		String no = sc.nextLine();
		Properties prop = new Properties();
		Connection con = JDBCTemplate.getConnection();
		PreparedStatement pstmt = null;
		
		try {
			prop.loadFromXML(new FileInputStream("mapper/employee-query.xml"));
			
			switch(no) {
				case"1":
					EmployeetestDTO emptest = new EmployeetestDTO();
				
					System.out.print("사원의 이름을 입력해 주세요 : ");
					emptest.setEmpName(sc.nextLine());
				
					System.out.print("\n 사원의 부서코드를 입력해 주세요 : ");
					emptest.setDeptCode(sc.nextLine());
					
					System.out.print("\n 사원의 월급을 입력해 주세요 : ");
					emptest.setSalary(sc.nextInt());
				
					sc.nextLine();  //sc.nextInt() 다음에는 sc.nextLine()으로 날려주기...
					System.out.println("\n 사원의 주민번호를 입력해 주세요 : ");
					emptest.setEmpNo(sc.nextLine());
				
					System.out.print("\n 사원의 이메일을 입력해 주세요 : ");
					emptest.setEmail(sc.nextLine());
				
					System.out.print("\n 사원의 직급코드 입력해 주세요 : ");
					emptest.setJobCode(sc.nextLine());
				
					System.out.print("\n 사원의 급여등급을 입력해 주세요 : ");
					emptest.setSalLevel(sc.nextLine());
				
					String query = prop.getProperty("employeetestnyong");
					con = JDBCTemplate.getConnection();
				
					pstmt = con.prepareStatement(query);
				
					pstmt.setString(1, emptest.getEmpName());
					pstmt.setString(2, emptest.getDeptCode());
					pstmt.setInt(3, emptest.getSalary());
					pstmt.setString(4, emptest.getEmpNo());
					pstmt.setString(5, emptest.getEmail());
					pstmt.setString(6, emptest.getJobCode());
					pstmt.setString(7, emptest.getSalLevel());
				
					int result = pstmt.executeUpdate();
				
					if(result == 1) {
						System.out.println("\n 사원 등록 성공");
					} else {
						System.out.println("\n 사원 등록 실패");
					}
				
					break;
					
				case"2":
					break;
				
				default : System.out.println("메뉴를 다시 선택해 주세요.");
					break;
					
					
			}
				
		} catch (InvalidPropertiesFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(con);
			JDBCTemplate.close(pstmt);
		}
		
		
			
			
			
			
			
			
			
		

	}

}
