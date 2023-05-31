package com.greedy.model.dto;

import java.sql.Date;

public class postReadDTO {
	private int postNum;
	private String blogNum;
	private String cateNum;
	private String fileNum;
	private String postTitle;
	private String postCon;
	private java.sql.Date postTime;
	private String state;
	
	public postReadDTO() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public postReadDTO(int postNum, String blogNum, String cateNum, String fileNum, String postTitle, String postCon,
			Date postTime, String state) {
		super();
		this.postNum = postNum;
		this.blogNum = blogNum;
		this.cateNum = cateNum;
		this.fileNum = fileNum;
		this.postTitle = postTitle;
		this.postCon = postCon;
		this.postTime = postTime;
		this.state = state;
	}
	
	public int getPostNum() {
		return postNum;
	}
	public void setPostNum(int postNum) {
		this.postNum = postNum;
	}
	public String getBlogNum() {
		return blogNum;
	}
	public void setBlogNum(String blogNum) {
		this.blogNum = blogNum;
	}
	public String getCateNum() {
		return cateNum;
	}
	public void setCateNum(String cateNum) {
		this.cateNum = cateNum;
	}
	public String getFileNum() {
		return fileNum;
	}
	public void setFileNum(String fileNum) {
		this.fileNum = fileNum;
	}
	public String getPostTitle() {
		return postTitle;
	}
	public void setPostTitle(String postTitle) {
		this.postTitle = postTitle;
	}
	public String getPostCon() {
		return postCon;
	}
	public void setPostCon(String postCon) {
		this.postCon = postCon;
	}
	public java.sql.Date getPostTime() {
		return postTime;
	}
	public void setPostTime(java.sql.Date postTime) {
		this.postTime = postTime;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	
	@Override
	public String toString() {
		return "postReadDTO [postNum=" + postNum + ", blogNum=" + blogNum + ", cateNum=" + cateNum + ", fileNum="
				+ fileNum + ", postTitle=" + postTitle + ", postCon=" + postCon + ", postTime=" + postTime + ", state="
				+ state + "]";
	}
	
}
