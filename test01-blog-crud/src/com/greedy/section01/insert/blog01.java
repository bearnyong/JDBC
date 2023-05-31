package com.greedy.section01.insert;

import static com.greedy.common.JDBCTemplate.close;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.InvalidPropertiesFormatException;
import java.util.List;
import java.util.Properties;
import java.util.Scanner;

import com.greedy.common.JDBCTemplate;
import com.greedy.model.dto.postCreateDTO;
import com.greedy.model.dto.postReadDTO;
import com.greedy.model.dto.postUpdateDTO;

public class blog01 {

	public static void main(String[] args) {
		
		Connection con = JDBCTemplate.getConnection();
		PreparedStatement pstmt = null; //������ ����ǥ�� ���� �־��ֱ� ���� PreparedStatement
		Properties prop = new Properties();
		ResultSet result1 = null;
		
		int result = 0;
		String query = null;
		
		Scanner sc = new Scanner(System.in);
		
		
		blog:
		while(true) {
			
			System.out.print("----- ���Ͻô� �ɼ��� ��ȣ�� �Է��� �ּ��� -----\n"
					   + "----- ����: [1.�Խñ� ���][2.�Խñ� ��ȸ][3.�Խñ� ���� ����][4.�Խñ� ��Ȱ��ȭ][0.����]"
					   + "\n �ɼ� ���� : ");
			String sel = sc.nextLine();
			
			switch(sel) {
				case "1" :	//create
					try {
						prop.loadFromXML(new FileInputStream("mapper/post-create.xml"));
					
						String insertquery = prop.getProperty("insertNewPost");
						postCreateDTO postcreat = new postCreateDTO();
					
						System.out.print("��α� ��ȣ(B00)�� �Է��� �ּ��� : ");
						postcreat.setBlogNum(sc.nextLine());
				
						System.out.print("\n ī�װ� ��ȣ(C00)�� �Է��� �ּ��� : ");
						postcreat.setCateNum(sc.nextLine());
					
						System.out.print("\n �Խñ� ������ �Է��� �ּ��� : ");
						postcreat.setPostTitle(sc.nextLine());
					
						System.out.print("\n �Խñ� ������ �Է��� �ּ��� : ");
						postcreat.setPostCon(sc.nextLine());
					
						pstmt = con.prepareStatement(insertquery);
						pstmt.setString(1, postcreat.getBlogNum());
						pstmt.setString(2, postcreat.getCateNum());
						pstmt.setString(3, postcreat.getPostTitle());
						pstmt.setString(4, postcreat.getPostCon());
					
						result = pstmt.executeUpdate();
					
						if(result == 1) {
							System.out.println("�Խñ��� �߰��Ǿ����ϴ�.\n\n");
						} else {
							System.out.println("���� �߻�... ���� ������ ������� �ʾҽ��ϴ�.\n\n");
						}
					
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
				 
					break;
			
				 
				case "2" : //read
					
					try {
						prop.loadFromXML(new FileInputStream("mapper/post-read.xml"));
						postReadDTO postread = new postReadDTO();
						
						System.out.print("[������ȸ]�� [��ü��ȸ] �� ���Ͻô� �ɼ��� �Է��� �ּ��� : ");
						String opt = sc.nextLine();
						
						switch(opt) {
							case "������ȸ" :
								query = prop.getProperty("blogselectRead");
								
								System.out.print("��ȸ�ϰ� ���� �Խñ� ��ȣ(000)�� �Է��� �ּ��� : ");
								int postNum = sc.nextInt();
								sc.nextLine();

								
								pstmt = con.prepareStatement(query);
								pstmt.setInt(1,  postNum);
								
								
								result1 = pstmt.executeQuery();
								if(result1.next()) {
									postread.setPostNum(result1.getInt("POST_NUM"));
									postread.setFileNum(result1.getString("POST_TITLE"));
									postread.setPostCon(result1.getString("POST_CON"));
									postread.setState(result1.getString("STATE"));
									System.out.println(postread + "\n\n");
									
									System.out.println(postread.getState());
									if(postread.getState() == "N") {
										System.out.println("�ش� �Խñ���...��Ȱ��ȭ �� �Խñ� �Դϴ�...");
									}
								} else {
									System.out.println("��ȸ�� �� ���� �Խñ� �Դϴ�...\n\n");
								}
								
								break ;
								
								
							case "��ü��ȸ" :
								query = prop.getProperty("blogAllRead");
								
								List<postReadDTO> postList = null;
								pstmt = con.prepareStatement(query);
								
								result1 = pstmt.executeQuery();
								postList = new ArrayList<>();
								
								while(result1.next()) {
									postReadDTO postread1 = new postReadDTO();
									
									postread1.setPostNum(result1.getInt("POST_NUM"));
									postread1.setBlogNum(result1.getString("BLOG_NUM"));
									postread1.setFileNum(result1.getString("FILE_NUM"));
									postread1.setPostTitle(result1.getString("POST_TITLE"));
									postread1.setPostCon(result1.getString("POST_CON"));
									postread1.setPostTime(result1.getDate("POST_TIME"));
									postread1.setState(result1.getString("STATE"));
									postread1.setCateNum(result1.getString("CATE_NUM"));
									
									postList.add(postread1);
								}
								
								for(postReadDTO pri : postList) {
									System.out.println(pri);
								}
								
								break ;
						}
						
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
					}  finally {
						close(con);
						close(pstmt);
					}
					
					break;
				 
				 
				case "3" :
					
					try {
					
						postUpdateDTO updDto = new postUpdateDTO();
					
						prop.loadFromXML(new FileInputStream("mapper/post-update.xml"));
						query = prop.getProperty("upBlogName");
						pstmt = con.prepareStatement(query);
						
						System.out.print("������ �����ϰ��� �ϴ� �Խñ� ��ȣ(000)�� �Է��� �ּ��� : ");
						int postNum = sc.nextInt();
						sc.nextLine();
					
						System.out.print("������ ������ �Է��� �ּ��� : ");
						String postTitle = sc.nextLine();
					
						pstmt.setString(1, postTitle);
						pstmt.setInt(2, postNum);
				
						result = pstmt.executeUpdate();
				
						if(result == 1) {
							System.out.println("������ �Ϸ�Ǿ����ϴ�.\n\n");
						} else {
							System.out.println("������ �Ϸ���� �ʾҽ��ϴ�....\n\n");
						}
					
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
					}
					
 					break;
			
 					
				case "4" :
					try {
						prop.loadFromXML(new FileInputStream("mapper/post-delete.xml"));
						query = prop.getProperty("deleteBlog");
						pstmt = con.prepareStatement(query);
					
						System.out.print("��Ȱ��ȭ ��ų �Խñ� ��ȣ�� �Է��� �ּ��� : ");
						int delId = sc.nextInt();
						pstmt.setInt(1, delId);
					
						result = pstmt.executeUpdate();
					
						if(result == 1) {
							System.out.println("��Ȱ��ȭ�� �Ϸ�Ǿ����ϴ�..\n\n");
						} else {
							System.out.println("��Ȱ��ȭ.... ����...");
						}
					
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
					}
					
					break;
			
				case "0" :
					System.out.println("���α׷��� �����մϴ�...\n\n");
					break blog;
				 
				
				default : System.out.println("�ùٸ� ���� �Էµ��� �ʾҽ��ϴ�... �ٽ� �Է��� �ּ���...\n\n");
				 	break;
			}
			
			
		}

	}

}
