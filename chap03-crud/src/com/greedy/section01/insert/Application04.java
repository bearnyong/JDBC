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
		System.out.println("�޴��� ������ �ּ��� (1:������, 2:�����ȸ) : ");
		String no = sc.nextLine();
		Properties prop = new Properties();
		Connection con = JDBCTemplate.getConnection();
		PreparedStatement pstmt = null;
		
		try {
			prop.loadFromXML(new FileInputStream("mapper/employee-query.xml"));
			
			switch(no) {
				case"1":
					EmployeetestDTO emptest = new EmployeetestDTO();
				
					System.out.print("����� �̸��� �Է��� �ּ��� : ");
					emptest.setEmpName(sc.nextLine());
				
					System.out.print("\n ����� �μ��ڵ带 �Է��� �ּ��� : ");
					emptest.setDeptCode(sc.nextLine());
					
					System.out.print("\n ����� ������ �Է��� �ּ��� : ");
					emptest.setSalary(sc.nextInt());
				
					sc.nextLine();  //sc.nextInt() �������� sc.nextLine()���� �����ֱ�...
					System.out.println("\n ����� �ֹι�ȣ�� �Է��� �ּ��� : ");
					emptest.setEmpNo(sc.nextLine());
				
					System.out.print("\n ����� �̸����� �Է��� �ּ��� : ");
					emptest.setEmail(sc.nextLine());
				
					System.out.print("\n ����� �����ڵ� �Է��� �ּ��� : ");
					emptest.setJobCode(sc.nextLine());
				
					System.out.print("\n ����� �޿������ �Է��� �ּ��� : ");
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
						System.out.println("\n ��� ��� ����");
					} else {
						System.out.println("\n ��� ��� ����");
					}
				
					break;
					
				case"2":
					break;
				
				default : System.out.println("�޴��� �ٽ� ������ �ּ���.");
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
