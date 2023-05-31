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
import com.greedy.model.dto.MenuDTO;

public class Application03 {
	
public static void main(String[] args) {
		
		Connection con = JDBCTemplate.getConnection();
		PreparedStatement pstmt = null;
		int result = 0;
		
		Properties prop = new Properties();
		MenuDTO menu = new MenuDTO();
		
		Scanner sc = new Scanner(System.in);
		
		System.out.print("�޴��� �̸��� �Է����ּ��� : ");
		menu.setMenuName(sc.nextLine());
		
		System.out.print("\n �޴��� ������ �Է��� �ּ��� : ");
		menu.setMenuPrice(sc.nextInt());
		
		System.out.print("\n ī�װ� �ڵ带 �Է����ּ��� : ");
		menu.setCategory(sc.nextInt());
		
		sc.nextLine();  //sc.nextInt() �������� sc.nextLine()���� �����ֱ�...
		System.out.print("\n �Ǹ� ���θ� �Է��� �ּ���(Y/N) : ");
		menu.setOrderableStatus(sc.nextLine().toUpperCase());
		
		try {
			prop.loadFromXML(new FileInputStream("mapper/menu-query.xml"));
			String query = prop.getProperty("insertMenu");
			
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, menu.getMenuName());
			pstmt.setInt(2, menu.getMenuPrice());
			pstmt.setInt(3, menu.getCategory());
			pstmt.setString(4, menu.getOrderableStatus());
			
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
			System.out.println("\n �޴� ��� ���� *_*!!");
		} else {
			System.out.println("\n �޴� ��� ���� ��_��");
		}

	}

}
