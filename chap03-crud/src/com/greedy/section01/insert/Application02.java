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
			System.out.println("메뉴의 이름을 입력해주세요 : ");
			String menuName = sc.nextLine();
			
			System.out.print("\n 메뉴의 가격을 입력해 주세요 : ");
			int price = sc.nextInt();
			System.out.print("\n 카테고리 코드를 입력해주세요 : ");
			int categoryCode = sc.nextInt();
			System.out.print(" 판매 여부를 입력해 주세요(Y/N) : ");
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
			System.out.println("메뉴 등록 성공 *_*!!");
		} else {
			System.out.println("메뉴 등록 실패 ㅠ_ㅠ");
		}

	}

}
