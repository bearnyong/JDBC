package com.greedy.section01.insert;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.InvalidPropertiesFormatException;
import java.util.Properties;

import com.greedy.common.JDBCTemplate;
import static com.greedy.common.JDBCTemplate.close;


public class Application01 {

	public static void main(String[] args) {
		
		Connection con = JDBCTemplate.getConnection();
		PreparedStatement pstmt = null;
		int result = 0;
		
		Properties prop = new Properties();
		
		try {
			prop.loadFromXML(new FileInputStream("mapper/menu-query.xml"));
			String query = prop.getProperty("insertMenu");
			System.out.println(query);
			
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, "짜장면");
			pstmt.setInt(2,  8000);
			pstmt.setInt(3, 5);
			pstmt.setString(4, "Y");
			
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
			close(con);
			close(pstmt);
		}
		
		if(result == 1) {
			System.out.println("메뉴 등록에 성공하였습니다.");
		} else {
			System.out.println("메뉴가 정상적으로 등록되지 않았습니다.");
		}
		System.out.println("result : " + result);

	}

}
