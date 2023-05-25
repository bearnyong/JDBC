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
      
      /* �������� �����ϰ� �����ϴ� ����� �ϴ� �뵵�� �������̽�*/
      Statement stmt = null;
      
      /* select ��� ������ �޾ƿ� �뵵�� �������̽�*/
      ResultSet rset = null;
      
      try {
         stmt = con.createStatement();
         rset = stmt.executeQuery("select EMP_ID, EMP_NAME FROM EMPLOYEE"); //���ͷ� �������� ���� ����..
         
         while(rset.next()) { //�ڷᰡ ������ִ� ���¸� �Ʒ� ���.
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