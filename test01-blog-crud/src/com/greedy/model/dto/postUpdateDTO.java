package com.greedy.model.dto;

import java.sql.Date;

public class postUpdateDTO {
	
	private int postNum;
	private String postTitle;
	private java.sql.Date postTime;
	private String state;
	public postUpdateDTO() {
		super();
		// TODO Auto-generated constructor stub
	}
	public postUpdateDTO(int postNum, String postTitle, Date postTime, String state) {
		super();
		this.postNum = postNum;
		this.postTitle = postTitle;
		this.postTime = postTime;
		this.state = state;
	}
	public int getPostNum() {
		return postNum;
	}
	public void setPostNum(int postNum) {
		this.postNum = postNum;
	}
	public String getPostTitle() {
		return postTitle;
	}
	public void setPostTitle(String postTitle) {
		this.postTitle = postTitle;
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
		return "postUpdateDTO [postNum=" + postNum + ", postTitle=" + postTitle + ", postTime=" + postTime + ", state="
				+ state + "]";
	}
	
	
	
}