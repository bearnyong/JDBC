package com.greedy.section01;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Application01 {

	public static void main(String[] args) {
		
		// ��ü ���� ���� ��Ģ
		// �������� ���̰� ���������� ��
		// ������ ���� �Ͼ�� ��Ȳ�� ���̱� ����.
		// ���ο��� �����ص� �ܺο��� ���� ���ϵ��� ��
		// DBMS-JAVA���� ������ ���� JDBC ���
		
		
		/* DB ������ ���� Connection �ν��Ͻ� ������ ���� ���۷��� ���� ����
		 * ���߿� finally ������ ����ϱ� ���� try �� �ۿ� ������*/

		
		Connection con = null;
		
		try {
			
			Class.forName("oracle.jdbc.driver.OracleDriver"); //���� �־���... ����̺� ���� (lib)
			con = DriverManager/*Ŭ����?*/.getConnection("jdbc:oracle:thin:@localhost:1521:xe"/*localhost : �� �ڽſ��� ���� �ޱ�, �⺻ ���� 1521��Ʈ�� ����, xe : ����Ŭ���� ���� ���� ��Ģ*/
											 		  ,"C##EMPLOYEE"/*����� ����(id)*/
											 		  ,"EMPLOYEE"/*pwd*/);
			System.out.println("con : " + con); // ���⼭ ����
			
		} catch (ClassNotFoundException e) { //forName�� ã�� ���� ��, ..
			e.printStackTrace();
			
		} catch (SQLException e) { //������ �����ų� �α����� �� �� �߻��� �� �ִ� ����
			e.printStackTrace();
			
		} finally { //try ����ó��, ���ܸ� ó���ϴ� ���� cacth�� �����, �׸��� finally...
			if(con != null) { //con�� ���� ���� ��...(���������)
				try {
					con.close(); //���� ���ϴ� ������ �߻��� �� �����Ƿ� �� �ݾ��ֱ�..
				} catch(SQLException e) {
					e.printStackTrace();
				}
			}
		}
		
		
	}

}
