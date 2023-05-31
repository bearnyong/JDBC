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
		PreparedStatement pstmt = null; //쿼리의 물음표에 값을 넣어주기 위한 PreparedStatement
		Properties prop = new Properties();
		ResultSet result1 = null;
		
		int result = 0;
		String query = null;
		
		Scanner sc = new Scanner(System.in);
		
		
		blog:
		while(true) {
			
			System.out.print("----- 원하시는 옵션의 번호를 입력해 주세요 -----\n"
					   + "----- 보기: [1.게시글 등록][2.게시글 조회][3.게시글 제목 수정][4.게시글 비활성화][0.종료]"
					   + "\n 옵션 선택 : ");
			String sel = sc.nextLine();
			
			switch(sel) {
				case "1" :	//create
					try {
						prop.loadFromXML(new FileInputStream("mapper/post-create.xml"));
					
						String insertquery = prop.getProperty("insertNewPost");
						postCreateDTO postcreat = new postCreateDTO();
					
						System.out.print("블로그 번호(B00)를 입력해 주세요 : ");
						postcreat.setBlogNum(sc.nextLine());
				
						System.out.print("\n 카테고리 번호(C00)를 입력해 주세요 : ");
						postcreat.setCateNum(sc.nextLine());
					
						System.out.print("\n 게시글 제목을 입력해 주세요 : ");
						postcreat.setPostTitle(sc.nextLine());
					
						System.out.print("\n 게시글 내용을 입력해 주세요 : ");
						postcreat.setPostCon(sc.nextLine());
					
						pstmt = con.prepareStatement(insertquery);
						pstmt.setString(1, postcreat.getBlogNum());
						pstmt.setString(2, postcreat.getCateNum());
						pstmt.setString(3, postcreat.getPostTitle());
						pstmt.setString(4, postcreat.getPostCon());
					
						result = pstmt.executeUpdate();
					
						if(result == 1) {
							System.out.println("게시글이 추가되었습니다.\n\n");
						} else {
							System.out.println("오류 발생... 직원 정보가 변경되지 않았습니다.\n\n");
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
						
						System.out.print("[단일조회]와 [전체조회] 중 원하시는 옵션을 입력해 주세요 : ");
						String opt = sc.nextLine();
						
						switch(opt) {
							case "단일조회" :
								query = prop.getProperty("blogselectRead");
								
								System.out.print("조회하고 싶은 게시글 번호(000)를 입력해 주세요 : ");
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
										System.out.println("해당 게시글은...비활성화 된 게시글 입니다...");
									}
								} else {
									System.out.println("조회할 수 없는 게시글 입니다...\n\n");
								}
								
								break ;
								
								
							case "전체조회" :
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
						
						System.out.print("제목을 변경하고자 하는 게시글 번호(000)를 입력해 주세요 : ");
						int postNum = sc.nextInt();
						sc.nextLine();
					
						System.out.print("변경할 제목을 입력해 주세요 : ");
						String postTitle = sc.nextLine();
					
						pstmt.setString(1, postTitle);
						pstmt.setInt(2, postNum);
				
						result = pstmt.executeUpdate();
				
						if(result == 1) {
							System.out.println("수정이 완료되었습니다.\n\n");
						} else {
							System.out.println("수정이 완료되지 않았습니다....\n\n");
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
					
						System.out.print("비활성화 시킬 게시글 번호를 입력해 주세요 : ");
						int delId = sc.nextInt();
						pstmt.setInt(1, delId);
					
						result = pstmt.executeUpdate();
					
						if(result == 1) {
							System.out.println("비활성화가 완료되었습니다..\n\n");
						} else {
							System.out.println("비활성화.... 실패...");
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
					System.out.println("프로그램을 종료합니다...\n\n");
					break blog;
				 
				
				default : System.out.println("올바른 값이 입력되지 않았습니다... 다시 입력해 주세요...\n\n");
				 	break;
			}
			
			
		}

	}

}
