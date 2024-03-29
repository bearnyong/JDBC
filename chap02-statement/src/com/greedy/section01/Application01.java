package com.greedy.section01;

import static com.greedy.common.JDBCTemplate.getConnection;
import static com.greedy.common.JDBCTemplate.close;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class Application01 {

   public static void main(String[] args) {
      Connection con = getConnection();
      
      /* 쿼리문을 저장하고 실행하는 기능을 하는 용도의 인터페이스*/
      Statement stmt = null;
      
      /* select 결과 집합을 받아올 용도의 인터페이스*/
      ResultSet rset = null;
      
      try {
         stmt = con.createStatement();
         rset = stmt.executeQuery("select EMP_ID, EMP_NAME FROM EMPLOYEE"); //리터럴 형식으로 쿼리 전달..
         
         while(rset.next()) { //자료가 담겨져있는 상태면 아래 출력.
            System.out.println(rset.getString("EMP_ID") + " , " + rset.getString("EMP_NAME"));
         }
      } catch (SQLException e) {
         // TODO Auto-generated catch block
         e.printStackTrace();
      }finally{
         close(con);
         close(stmt);
         close(rset);
      }
   }

}