package com.greedy.section01;

import static com.greedy.common.JDBCTemplate.getConnection;
import static com.greedy.common.JDBCTemplate.close;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class Application02 {
   
   public static void main(String[] args) {
      
      /*1. Connection 생성*/
      Connection con = getConnection();
      Statement stmt = null;
      ResultSet rset = null;
      
      try {
         stmt = con.createStatement();
         
         //executeQuery()로 쿼리문실행하고 result로 반환 받음
         
         System.out.println("조회할 사번을 입력하세요 : ");
         Scanner sc = new Scanner(System.in);
         
         String empId = sc.nextLine();
         
         String query = "SELECT EMP_ID, EMP_NAME FROM EMPLOYEE WHERE EMP_ID = '"+ empId +"'";
         System.out.println(query);
         rset = stmt.executeQuery(query);
         
         if(rset.next()) {
            System.out.println(rset.getString("EMP_ID")+" , "+rset.getString("EMP_NAME"));
         }
      }catch (SQLException e) {
         e.printStackTrace();
      } finally {
         close(con);
         close(rset);
         close(stmt);
      }
   }
}
