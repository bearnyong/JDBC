package com.greedy.model.dto;

public class postCreateDTO {
	
	private String blogNum;
	private String cateNum;
	private String postTitle;
	private String postCon;
	
	public postCreateDTO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public postCreateDTO(String blogNum, String cateNum, String postTitle, String postCon) {
		super();
		this.blogNum = blogNum;
		this.cateNum = cateNum;
		this.postTitle = postTitle;
		this.postCon = postCon;
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

	@Override
	public String toString() {
		return "postCreateDTO [blogNum=" + blogNum + ", cateNum=" + cateNum + ", postTitle=" + postTitle + ", postCon="
				+ postCon + "]";
	}
	
	
	

}
