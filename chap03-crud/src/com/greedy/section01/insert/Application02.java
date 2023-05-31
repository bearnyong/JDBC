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

public class Application02 {

	public static void main(String[] args) {
		
		Connection con = JDBCTemplate.getConnection();
		PreparedStatement pstmt = null;
		int result = 0;
		
		Properties prop = new Properties();
		
		try {
			prop.loadFromXML(new FileInputStream("mapper/menu-query.xml"));
			String query = prop.getProperty("insertMenu");
			
			System.out.println(query);
			Scanner sc = new Scanner(System.in);
			System.out.println("�޴��� �̸��� �Է����ּ��� : ");
			String menuName = sc.nextLine();
			
			System.out.print("\n �޴��� ������ �Է��� �ּ��� : ");
			int price = sc.nextInt();
			System.out.print("\n ī�װ� �ڵ带 �Է����ּ��� : ");
			int categoryCode = sc.nextInt();
			System.out.print(" �Ǹ� ���θ� �Է��� �ּ���(Y/N) : ");
			sc.nextLine();
			String orderrableStatus = sc.nextLine().toUpperCase();
			
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, menuName);
			pstmt.setInt(2, price);
			pstmt.setInt(3, categoryCode);
			pstmt.setString(4, orderrableStatus);
			
			result = pstmt.executeUpdate();
			
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
		
		if(result == 1) {
			System.out.println("�޴� ��� ���� *_*!!");
		} else {
			System.out.println("�޴� ��� ���� ��_��");
		}

	}

}
