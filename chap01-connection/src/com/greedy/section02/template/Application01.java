package com.greedy.section02.template;

import java.sql.Connection;

import static com.greedy.section02.template.JDBCTemplate.getConnection; /*JDBCTemplateŬ������ �ִ� getConnection�޼��带 ���ڴٰ� static���� �ѹ��� �����س��� ����...(?)*/
import static com.greedy.section02.template.JDBCTemplate.close;

public class Application01 {

	public static void main(String[] args) {

		Connection con = getConnection(); /**/
		System.out.println(con);
		
		close(con);
		
	}

}