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
		
		System.out.print("메뉴의 이름을 입력해주세요 : ");
		menu.setMenuName(sc.nextLine());
		
		System.out.print("\n 메뉴의 가격을 입력해 주세요 : ");
		menu.setMenuPrice(sc.nextInt());
		
		System.out.print("\n 카테고리 코드를 입력해주세요 : ");
		menu.setCategory(sc.nextInt());
		
		sc.nextLine();  //sc.nextInt() 다음에는 sc.nextLine()으로 날려주기...
		System.out.print("\n 판매 여부를 입력해 주세요(Y/N) : ");
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
			System.out.println("\n 메뉴 등록 성공 *_*!!");
		} else {
			System.out.println("\n 메뉴 등록 실패 ㅠ_ㅠ");
		}

	}

}
