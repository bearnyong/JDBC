package com.greedy.section01;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class Application03 {
	
	/*접속 정보를 파일로 따로 관리하는 방법*/
	public static void main(String[] args) {
		
		Properties prop = new Properties();
		Connection con = null;
		
		try {
			/* FileReader로 파일 읽어오기 그리고 prop에 저장 */
			prop.load(new FileReader("src/com/greedy/section01/connection/jdbc-config.properties"/*src부터 상대경로*/)); //properties 파일을 깃에서 제외하고 업로드 가능.. 외부에서 개인정보 확인x
			System.out.println(prop);
			
			//driver = oracle.jdbc.driver.OracleDriver //키=밸류

			String driver = prop.getProperty("driver"); /*getProperty 파일 읽어오기*/ /*텍스트 파일이면 다른 방식... 어쩌구..*/
			String user = prop.getProperty("user");
			String pass = prop.getProperty("pass");
			String url = prop.getProperty("url");
			
			Class.forName(driver);
			con = DriverManager.getConnection(url, user, pass);
			
			System.out.println("con : " + con);
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if(con!=null) {
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		
		
		
	}

}
