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

/* DTO�� �̿��� ��� ��� ���α׷� �����
����ڿ��� �Է��� �޾� �Ʒ��� ���� �����Ѵ�.

"���" �� �Է��ϸ� ����� ����Ѵ�.
����̸� , ����μ�, ��� ����, �ֹι�ȣ, �̸��� , ���� �Ի��� ����
�����ȣ�� �������� �̿��Ѵ�.

��ȸ�� �Է��ϸ� ����� ��ȣ�� ��ȸ�Ѵ�.

��ȸ�� ������ ������ ����

�����ȣ �̸�  ��ȣ �̸��� �޿� �Ի���

��� �����ʹ� DTO�� Ȱ���Ѵ�.
*/

public class test01 {

	public static void main(String[] args) {
		
		/*�����ͺ��̽� Ŀ�ؼǿ� ���õ� �ڷ���...(?)*/
		Connection con = JDBCTemplate.getConnection();
		PreparedStatement pstmt = null; //������ ����ǥ�� ���� �־��ֱ� ���� PreparedStatement
		Properties prop = new Properties();
		
		int result = 0;
		ResultSet result1 = null;
		
		Scanner sc = new Scanner(System.in);
		
		System.out.print("----- ���Ͻô� �ɼ��� �Ʒ� ����� ���� �Է��� �ּ��� -----\n----- ����: [���][��ȸ][������Ʈ][���ó��]\n �ɼ� ���� : ");
		String sel = sc.nextLine();
		String query = null;
		
		try {
			prop.loadFromXML(new FileInputStream("mapper/employee-query.xml")); //�ڡڡ�
			
			switch(sel) {
				case "���" :
					String insertquery = prop.getProperty("employeetestnyong");
					
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
						System.out.println("\n ��� ��� ����");
					} else {
						System.out.println("\n ��� ��� ����");
					}
					
					break; //--- case "���" ���� ---
					
					
				case "��ȸ" :
					SelectEmpDTO selectEmpDto = new SelectEmpDTO();
					
					query = prop.getProperty("selectEmployeeOne");
					System.out.print("����� ��ȣ�� �Է��� �ּ��� : ");
					String empNo = sc.nextLine();
					
					con = JDBCTemplate.getConnection();
					pstmt = con.prepareStatement(query);
					
					pstmt.setString(1,  empNo); // ������ ù ��° ����ǥ�� �Է¹��� empNo�� ���� �־��ش�.
					
					result1 = pstmt.executeQuery();
					if(result1.next()) {
						selectEmpDto.setEmpName(result1.getString("EMP_NAME"));
						selectEmpDto.setEmail(result1.getString("EMAIL"));
						selectEmpDto.setPhone(result1.getString("PHONE"));
						selectEmpDto.setSalary(result1.getInt("SALARY"));
						selectEmpDto.setHireDate(result1.getDate("HIRE_DATE"));
						selectEmpDto.setEntYn(result1.getString("ENT_YN"));
					} else {
						System.out.println("��ϵ��� ���� ����Դϴ�.");
					}
					
					System.out.println(selectEmpDto);
					break; //--- case "��ȸ" ���� ---
					
					
				case "������Ʈ" :
					query = prop.getProperty("updateEmployee");
					con = JDBCTemplate.getConnection();
					
					pstmt = con.prepareStatement(query);
					
					System.out.print("������ ����� �����ȣ�� �Է��� �ּ��� : ");
					String empId = sc.nextLine();
					
					System.out.print("�Է��� �̸����� ����� �̸��� ����˴ϴ� : ");
					String empName = sc.nextLine();
					
					System.out.print("����� ���� ���θ� �Է��� �ּ��� : ");
					String empYn = sc.nextLine();
					
					pstmt.setString(1, empName);
					pstmt.setString(2, empYn);
					pstmt.setString(3, empId);
					
					result = pstmt.executeUpdate();
					
					if(result == 1) {
						System.out.println("���� ������ ����Ǿ����ϴ�.");
					} else {
						System.out.println("���� ������ ������� �ʾҽ��ϴ�.");
					}
					
					break; //--- case "������Ʈ" ���� ---
					
					
				case "���ó��" :
					query = prop.getProperty("deleteEmployee");
					con = JDBCTemplate.getConnection();
					pstmt = con.prepareStatement(query);
					
					System.out.print("����� ������ ���� ��ȣ�� �Է��� �ּ��� : ");
					String delId = sc.nextLine();
					pstmt.setString(1, delId);
					
					result = pstmt.executeUpdate();
					if(result == 1) {
						System.out.println("��� ó���� �Ϸ�Ǿ����ϴ�.");
					} else {
						System.out.println("��� ó���� �Ϸ���� �ʾҽ��ϴ�.");
					}
					
					break; //--- case "������Ʈ" ���� ---
					
					
				default : System.out.println("�ùٸ� ���� �Էµ��� �ʾ� ���α׷��� ����˴ϴ�...");
					break; //--- default ���� ---
					
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
