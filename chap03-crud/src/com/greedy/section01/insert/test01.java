package com.greedy.section01.insert;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.InvalidPropertiesFormatException;
import java.util.Properties;
import java.util.Scanner;

import com.greedy.common.JDBCTemplate;
import com.greedy.model.dto.EmployeetestDTO;
import com.greedy.model.dto.SelectEmpDTO;

/* DTO를 이용한 사원 등록 프로그램 만들기
사용자에게 입력을 받아 아래와 같이 동작한다.

"등록" 을 입력하면 사원을 등록한다.
사원이름 , 사원부서, 사원 월급, 주민번호, 이메일 , 직급 입사일 오늘
사원번호는 시퀀스를 이용한다.

조회를 입력하면 사원의 번호를 조회한다.

조회의 조건은 다음과 같다

사원번호 이름  번호 이메일 급여 입사일

모든 데이터는 DTO를 활용한다.
*/

public class test01 {

	public static void main(String[] args) {
		
		/*데이터베이스 커넥션에 관련된 자료형...(?)*/
		Connection con = JDBCTemplate.getConnection();
		PreparedStatement pstmt = null; //쿼리의 물음표에 값을 넣어주기 위한 PreparedStatement
		Properties prop = new Properties();
		
		int result = 0;
		ResultSet result1 = null;
		
		Scanner sc = new Scanner(System.in);
		
		System.out.print("----- 원하시는 옵션을 아래 보기와 같이 입력해 주세요 -----\n----- 보기: [등록][조회][업데이트][퇴사처리]\n 옵션 선택 : ");
		String sel = sc.nextLine();
		String query = null;
		
		try {
			prop.loadFromXML(new FileInputStream("mapper/employee-query.xml")); //★★★
			
			switch(sel) {
				case "등록" :
					String insertquery = prop.getProperty("employeetestnyong");
					
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
					
					pstmt = con.prepareStatement(insertquery);
					pstmt.setString(1, emptest.getEmpName());
					pstmt.setString(2, emptest.getDeptCode());
					pstmt.setInt(3, emptest.getSalary());
					pstmt.setString(4, emptest.getEmpNo());
					pstmt.setString(5, emptest.getEmail());
					pstmt.setString(6, emptest.getJobCode());
					pstmt.setString(7, emptest.getSalLevel());
					
					result = pstmt.executeUpdate();
					
					if(result == 1) {
						System.out.println("\n 사원 등록 성공");
					} else {
						System.out.println("\n 사원 등록 실패");
					}
					
					break; //--- case "등록" 종료 ---
					
					
				case "조회" :
					SelectEmpDTO selectEmpDto = new SelectEmpDTO();
					
					query = prop.getProperty("selectEmployeeOne");
					System.out.print("사원의 번호를 입력해 주세요 : ");
					String empNo = sc.nextLine();
					
					con = JDBCTemplate.getConnection();
					pstmt = con.prepareStatement(query);
					
					pstmt.setString(1,  empNo); // 쿼리의 첫 번째 물음표에 입력받은 empNo의 값을 넣어준다.
					
					result1 = pstmt.executeQuery();
					if(result1.next()) {
						selectEmpDto.setEmpName(result1.getString("EMP_NAME"));
						selectEmpDto.setEmail(result1.getString("EMAIL"));
						selectEmpDto.setPhone(result1.getString("PHONE"));
						selectEmpDto.setSalary(result1.getInt("SALARY"));
						selectEmpDto.setHireDate(result1.getDate("HIRE_DATE"));
						selectEmpDto.setEntYn(result1.getString("ENT_YN"));
					} else {
						System.out.println("등록되지 않은 사원입니다.");
					}
					
					System.out.println(selectEmpDto);
					break; //--- case "조회" 종료 ---
					
					
				case "업데이트" :
					query = prop.getProperty("updateEmployee");
					con = JDBCTemplate.getConnection();
					
					pstmt = con.prepareStatement(query);
					
					System.out.print("변경할 사원의 사원번호를 입력해 주세요 : ");
					String empId = sc.nextLine();
					
					System.out.print("입력한 이름으로 사원의 이름이 변경됩니다 : ");
					String empName = sc.nextLine();
					
					System.out.print("사원의 재직 여부를 입력해 주세요 : ");
					String empYn = sc.nextLine();
					
					pstmt.setString(1, empName);
					pstmt.setString(2, empYn);
					pstmt.setString(3, empId);
					
					result = pstmt.executeUpdate();
					
					if(result == 1) {
						System.out.println("직원 정보가 변경되었습니다.");
					} else {
						System.out.println("직원 정보가 변경되지 않았습니다.");
					}
					
					break; //--- case "업데이트" 종료 ---
					
					
				case "퇴사처리" :
					query = prop.getProperty("deleteEmployee");
					con = JDBCTemplate.getConnection();
					pstmt = con.prepareStatement(query);
					
					System.out.print("퇴사한 직원의 직원 번호를 입력해 주세요 : ");
					String delId = sc.nextLine();
					pstmt.setString(1, delId);
					
					result = pstmt.executeUpdate();
					if(result == 1) {
						System.out.println("퇴사 처리가 완료되었습니다.");
					} else {
						System.out.println("퇴사 처리가 완료되지 않았습니다.");
					}
					
					break; //--- case "업데이트" 종료 ---
					
					
				default : System.out.println("올바른 값이 입력되지 않아 프로그램이 종료됩니다...");
					break; //--- default 종료 ---
					
			}
			
		}
		 catch (InvalidPropertiesFormatException e) {
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
