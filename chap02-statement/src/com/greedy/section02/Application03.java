package com.greedy.section02;

import static com.greedy.common.JDBCTemplate.getConnection;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.Scanner;

import com.greedy.model.dto.EmpNameDTO;

public class Application03 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		Connection con = getConnection();
		
		PreparedStatement pstmt = null;
		ResultSet rset = null;

		List<EmpNameDTO> empList = null;
		
		Scanner sc = new Scanner(System.in);
		System.out.print("조회할 성을 입력해 주세요 : ");
		String name = sc.nextLine();
		
		Properties prop = new Properties();
		
		try {
			prop.loadFromXML(new FileInputStream("src/com/greedy/section02/prepared/employee-query.xml"));
			String query = prop.getProperty("selectEmpByFamilName");
			System.out.println(query);
			
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, name);
			
			rset = pstmt.executeQuery();
			empList = new ArrayList<EmpNameDTO>();
			
			while(rset.next()) {
				EmpNameDTO dto = new EmpNameDTO();
				dto.setEmpId(rset.getString("EMP_ID"));
				dto.setEmpName(rset.getString("EMP_NAME"));
				empList.add(dto);
			}
			
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		for(EmpNameDTO name1 : empList) {
			System.out.println(name1);
		}
		
	}

}
